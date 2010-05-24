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

	// child hWnd
	HWND hWnd = ::FindWindowEx(phWnd, NULL, NULL, NULL);

	return hWnd;
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

/* void acceptGesture (in long id_, in long state_, in long x_, in long y_, in long hi_, in long lo_); */
NS_IMETHODIMP JSCallback::AcceptGesture(PRInt32 id_, PRInt32 state_, PRInt32 x_, PRInt32 y_, PRInt32 hi_, PRInt32 lo_)
{
    return NS_OK;
}

NS_IMPL_ISUPPORTS1(TouchSupport, ITouchSupport)

TouchSupport::TouchSupport(){}
TouchSupport::~TouchSupport(){}

/* long checkTouchCapabilities (); */
NS_IMETHODIMP TouchSupport::CheckTouchCapabilities(PRInt32 *_retval)
{
	*_retval = GetSystemMetrics(SM_DIGITIZER);
    return NS_OK;
}

/* boolean registerWindow (in nsIBaseWindow window, in long type, in IJSCallback observer); */
NS_IMETHODIMP TouchSupport::RegisterWindow(nsIBaseWindow *window, PRInt32 type, IJSCallback *observer, PRBool *_retval)
{
	try {
		HWND hWnd = getWindowHWND(window);

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
			/* handle the error here */
		}
		delete [] pInputs;
	}else{
		/* handle the error here, probably out of memory */
	}
	if (bHandled){
		// if you handled the message, close the touch input handle and return
		CloseTouchInputHandle((HTOUCHINPUT)lParam);
		return 0;
	}
		
	// if you didn't handle the message, let DefWindowProc handle it
	return DefWindowProc(hWnd, WM_TOUCH, wParam, lParam);
}

tagPOINT _ptFirst;
tagPOINT _ptSecond;
tagPOINT _ptZoomCenter;
UINT _dwArguments;

// handle gesture messages
LRESULT TouchSupport::OnGesture(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	// Create a structure to populate and retrieve the extra message info.
	GESTUREINFO gi;  
    
	ZeroMemory(&gi, sizeof(GESTUREINFO));
    
	gi.cbSize = sizeof(GESTUREINFO);

	BOOL bResult  = GetGestureInfo((HGESTUREINFO)lParam, &gi);
	BOOL bHandled = FALSE;

	TouchSupport * self = (TouchSupport*)::GetProp(hWnd, TOUCHSUPPORT_REF_PROP);

	if (bResult){

		// now interpret the gesture
		switch (gi.dwID){
			case GID_ZOOM:
				
				switch(gi.dwFlags){
					case GF_BEGIN:

						_dwArguments = (gi.ullArguments >> 32) & 0xffffffff;

						_ptFirst.x = gi.ptsLocation.x / 100;
						_ptFirst.y = gi.ptsLocation.y / 100;
						ScreenToClient(hWnd, &_ptFirst);
						break;
					default:
						_ptSecond.x = gi.ptsLocation.x / 100;
						_ptSecond.y = gi.ptsLocation.y / 100;
						ScreenToClient(hWnd, &_ptSecond);

						_ptZoomCenter.x = (_ptFirst.x + _ptSecond.x) / 2;
						_ptZoomCenter.y = (_ptFirst.y + _ptSecond.y) / 2;

						double k = ((gi.ullArguments >> 32) & 0xffffffff) / (double)_dwArguments;

						//call k, _ptZoomCenter.x, _ptZoomCenter.y
						
						//InvalidateRect(hWnd, NULL, TRUE);

						_ptFirst = _ptSecond;
						_dwArguments = (gi.ullArguments >> 32) & 0xffffffff;

						break;
				}

				break;
			case GID_PAN:
				
				switch(gi.dwFlags){
					case GF_BEGIN:
						_ptFirst.x = gi.ptsLocation.x / 100;
						_ptFirst.y = gi.ptsLocation.y / 100;
						ScreenToClient(hWnd, &_ptFirst);
						break;
					default:
						_ptSecond.x = gi.ptsLocation.x / 100;
						_ptSecond.y = gi.ptsLocation.y / 100;
						ScreenToClient(hWnd, &_ptSecond);

						//call _ptSecond.x-_ptFirst.x, _ptSecond.y-_ptFirst.y
						
						//InvalidateRect(hWnd, NULL, TRUE);

						_ptFirst = _ptSecond;
						break;
				}

				break;
			case GID_ROTATE:
				
				switch(gi.dwFlags){
					case GF_BEGIN:

						_dwArguments = 0;

						break;
					default:
						_ptFirst.x = gi.ptsLocation.x / 100;
						_ptFirst.y = gi.ptsLocation.y / 100;
						ScreenToClient(hWnd, &_ptSecond);

						//call
						//GID_ROTATE_ANGLE_FROM_ARGUMENT((gi.ullArguments >> 32) & 0xffffffff) - GID_ROTATE_ANGLE_FROM_ARGUMENT(_dwArguments),
						//_ptFirst.x, _ptFirst.y
						
						//InvalidateRect(hWnd, NULL, TRUE);

						_dwArguments = (gi.ullArguments >> 32) & 0xffffffff;

						break;
				}

				break;
			case GID_TWOFINGERTAP:
				
				_ptFirst.x = gi.ptsLocation.x / 100;
				_ptFirst.y = gi.ptsLocation.y / 100;
				ScreenToClient(hWnd, &_ptSecond);

				break;
			case GID_PRESSANDTAP:
				
				_ptFirst.x = gi.ptsLocation.x / 100;
				_ptFirst.y = gi.ptsLocation.y / 100;
				ScreenToClient(hWnd, &_ptSecond);

				break;
			default:
				// A gesture was not recognized
				break;
		}
	}else{
		DWORD dwErr = ::GetLastError();
		if (dwErr > 0){
			//MessageBoxW(hWnd, L"Error!", L"Could not retrieve a GESTUREINFO structure.", MB_OK);
		}
	}

	if (bHandled){
		return 0;
	}
		
	return DefWindowProc(hWnd, message, wParam, lParam);
}


// proxy wndproc 
LRESULT CALLBACK TouchSupport::WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	TouchSupport * self = (TouchSupport*)::GetProp(hWnd, TOUCHSUPPORT_REF_PROP);

	switch(message){
		case WM_TOUCH:
			return TouchSupport::OnTouch(hWnd, wParam, lParam);
		case WM_GESTURE:
			return TouchSupport::OnGesture(hWnd, message, wParam, lParam);
		default: break;
	}

	return CallWindowProc(self->oldProc, hWnd, message, wParam, lParam);
}
