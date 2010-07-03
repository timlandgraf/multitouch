#include "TouchSupportImpl.h"

//-------------------------------------------------------------------
// hwnd getter (top-level and child)
//-------------------------------------------------------------------

HWND getWindowHWND(nsIBaseWindow *window) 
{
	nativeWindow nWindow;
	nsresult rv = window->GetParentNativeWindow(&nWindow);
	if (NS_FAILED(rv)){ 
		return NULL;
	}
	
	// top level hWnd
	HWND phWnd = (HWND)nWindow;

	// content hWnd
	HWND hWnd = ::FindWindowEx(phWnd, NULL, NULL, NULL);

	// find specific element
	hWnd = ::FindWindowEx(hWnd, NULL, NULL, NULL);
	HWND child = ::FindWindowEx(hWnd, NULL, NULL, NULL);
	child = ::FindWindowEx(hWnd, child, NULL, NULL);

	return child;
}

//-------------------------------------------------------------------
// component implementation
//-------------------------------------------------------------------

NS_IMPL_ISUPPORTS1(JSCallback, JSCallback)

JSCallback::JSCallback(){}
JSCallback::~JSCallback(){}

/* void acceptTouch (in long id_, in long x_, in long y_, in long time_, in long type_); */
NS_IMETHODIMP JSCallback::AcceptTouch(PRInt32 id_, PRInt32 x_, PRInt32 y_, PRInt32 time_, PRInt32 type_)
{
    return NS_OK;
}

NS_IMPL_ISUPPORTS1(TouchSupport, ITouchSupport)

TouchSupport::TouchSupport(){}
TouchSupport::~TouchSupport(){}

/* long checkTouchCapabilities (); */
NS_IMETHODIMP TouchSupport::CheckTouchCapabilities(PRInt32 *_retval)
{
	*_retval = ::GetSystemMetrics(SM_DIGITIZER);
    return NS_OK;
}

/* boolean registerWindow (in nsIBaseWindow window, in long type, in IJSCallback observer); */
NS_IMETHODIMP TouchSupport::RegisterWindow(nsIBaseWindow *window, PRInt32 type, IJSCallback *observer, PRBool *_retval)
{
	try {
		HWND hWnd = ::getWindowHWND(window);

		if (hWnd != NULL){

			this->type = type;
			
			observer->AddRef();
			this->observer = observer;

			// store this reference to used it later in wndproc
			::SetProp(hWnd, TOUCHSUPPORT_REF_PROP, (HANDLE)this);

			// overwrite wndproc of native window to receive window messages
			this->oldProc = (WNDPROC)::SetWindowLongPtr(
				hWnd,
				GWLP_WNDPROC,
				(LONG_PTR)TouchSupport::WndProc
			);

			if(type == 0) // touch
				*_retval = RegisterTouchWindow(hWnd, 0); // WIN32 -> 0
			else // gesture
				*_retval = true;
		}else {
			*_retval = false;
		}
	}catch(...){
		*_retval = false;
	}
    return NS_OK;
}

/* boolean unregisterWindow (in nsIBaseWindow window); */
NS_IMETHODIMP TouchSupport::UnregisterWindow(nsIBaseWindow *window, PRBool *_retval)
{
	try {
		if(this->type == 0){ // touch
			HWND hWnd = getWindowHWND(window);

			if (hWnd != NULL){
				*_retval = UnregisterTouchWindow(hWnd);
			}
		}else { // gesture
			*_retval = true;
		}

		if(this->observer)
			this->observer->Release();

	}catch(...){
		*_retval = false;
	}
    return NS_OK;
}

//-------------------------------------------------------------------
// static methods to handle wndproc messages
//-------------------------------------------------------------------

// handle touch messages
LRESULT TouchSupport::OnTouch(HWND hWnd, WPARAM wParam, LPARAM lParam )
{
	BOOL bHandled = FALSE;
	UINT cInputs = LOWORD(wParam);
	PTOUCHINPUT pInputs = new TOUCHINPUT[cInputs];

	TouchSupport * self = (TouchSupport*)::GetProp(hWnd, TOUCHSUPPORT_REF_PROP);

	if (pInputs){
		if (::GetTouchInputInfo((HTOUCHINPUT)lParam, cInputs, pInputs, sizeof(TOUCHINPUT))){
			for (UINT i=0; i < cInputs; i++){
				TOUCHINPUT ti = pInputs[i];

				PRUint32 type = -1;
               
				if(ti.dwFlags & TOUCHEVENTF_DOWN){
					type = 0;
				}else if(ti.dwFlags & TOUCHEVENTF_UP){
					type = 1;
				}else if(ti.dwFlags & TOUCHEVENTF_MOVE){
					type = 2;
				}

				if(type != -1){
					 tagPOINT point;

					point.x = ti.x / 100;
					point.y = ti.y / 100;

					BOOL b = ScreenToClient(hWnd, &point);

					self->observer->AcceptTouch(
						ti.dwID,
						point.x, // convert to screenX
						point.y, // convert to screenY
						ti.dwTime,
						type
					);
				}
			}
			bHandled = TRUE;
		}else{
			
		}
		delete [] pInputs;
	}else{
		
	}
	if (bHandled){
		// if you handled the message, close the touch input handle and return
		CloseTouchInputHandle((HTOUCHINPUT)lParam);
		return 0;
	}
		
	// if you didn't handle the message, let DefWindowProc handle it
	return DefWindowProc(hWnd, WM_TOUCH, wParam, lParam);
}

// proxy wndproc 
LRESULT CALLBACK TouchSupport::WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	TouchSupport * self = (TouchSupport*)::GetProp(hWnd, TOUCHSUPPORT_REF_PROP);

	switch(message){
		case WM_LBUTTONDOWN:
		case WM_LBUTTONUP:
			if ((GetMessageExtraInfo() & MOUSEEVENTF_FROMTOUCH) == MOUSEEVENTF_FROMTOUCH) { 
				return 0;
			}
		case WM_GESTURE:
			CloseGestureInfoHandle((HGESTUREINFO)lParam);
			return 0;
		case WM_TOUCH:
			if(self->type == 0){
				return TouchSupport::OnTouch(hWnd, wParam, lParam);
			}
			CloseTouchInputHandle((HTOUCHINPUT)lParam);
			return 0;
		default: break;
	}

	return CallWindowProc(self->oldProc, hWnd, message, wParam, lParam);
}
