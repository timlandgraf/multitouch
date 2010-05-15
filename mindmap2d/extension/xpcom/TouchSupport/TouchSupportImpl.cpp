#include "TouchSupportImpl.h"

WNDPROC _old_proc;
u_short _port;

//-------------------------------------------------------------------
// hwnd getter (top-level and content)
// source: http://mdn.beonex.com/en/Code_snippets/Finding_Window_Handles#Finding_the_content_window_handle
//-------------------------------------------------------------------

HWND getParentWindowHWND(nsIBaseWindow *window) 
{
	nativeWindow hwnd;
	nsresult rv = window->GetParentNativeWindow(&hwnd);
	if(NS_FAILED(rv)) return NULL;
	return (HWND)hwnd;
}

HWND getHWND(nsIBaseWindow *window)
{
	nsCOMPtr<nsIWidget> widget;
	window->GetMainWidget(getter_AddRefs(widget));

	if(widget)
		return (HWND) widget->GetNativeData(NS_NATIVE_WINDOW);

	return NULL;
}

HWND getHWND2(nsIBaseWindow *window)
{
	HWND hContent = NULL;

	HWND hFF = ::getParentWindowHWND(window);
	if (hFF) {
		// next we step down through a fixed structure
		HWND hTemp;
		hTemp = ::FindWindowEx(hFF, 0, "MozillaWindowClass", 0);
		hTemp = ::FindWindowEx(hTemp, 0, "MozillaWindowClass", 0);

		// assume only 1 window at this level has children
		// and the 1 with children is the one we want
		HWND hChild = ::GetWindow(hTemp, GW_CHILD);
		while (hTemp && !hChild) {
			hTemp = ::GetWindow(hTemp, GW_HWNDNEXT);
			hChild = ::GetWindow(hTemp, GW_CHILD);
		}

		// did we find a window with children?
		// that child is hopefully the content window
		if (hTemp) {
			hTemp = ::GetWindow(hTemp, GW_CHILD);
			hContent = ::FindWindowEx(hTemp, 0, "MozillaContentWindowClass", 0);
		}
	}

	return hContent;
 }


//-------------------------------------------------------------------
// component implementation
//-------------------------------------------------------------------

NS_IMPL_ISUPPORTS1(TouchSupport, ITouchSupport)

TouchSupport::TouchSupport(){}
TouchSupport::~TouchSupport(){}

/* long checkTouchCapabilities (); */
NS_IMETHODIMP TouchSupport::CheckTouchCapabilities(PRInt32 *_retval)
{
	*_retval = GetSystemMetrics(SM_DIGITIZER);
    return NS_OK;
}

/* boolean registerWindow (in nsIBaseWindow window, in long type, in long port); */
NS_IMETHODIMP TouchSupport::RegisterWindow(nsIBaseWindow *window, PRInt32 type, PRInt32 port, PRBool *_retval)
{
	try {
		HWND parenthWnd = ::getParentWindowHWND(window);
		HWND hWnd = ::GetWindow(parenthWnd, GW_CHILD);

		if (hWnd != NULL){
			this->type = type;
			_port = (u_short)port;
			
			// overwrite wndproc of native window to receive window messages
			_old_proc = (WNDPROC)::SetWindowLongPtr(
				hWnd,
				GWLP_WNDPROC,
				(LONG_PTR)TouchSupport::WndProc
			);

			if(type == 0)
				*_retval = RegisterTouchWindow(hWnd, 0); // WIN32 -> 0
			else
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
		if(this->type == 0){
			nativeWindow hwnd;
			nsresult rv = window->GetParentNativeWindow(&hwnd);

			HWND myHWND;
			if (NS_FAILED(rv)){
				myHWND = NULL;
			}else {
				myHWND = (HWND)hwnd;

				*_retval = UnregisterTouchWindow(myHWND);
			}
		}else {
			*_retval = true;
		}
	}catch(...){
		*_retval = false;
	}
    return NS_OK;
}

//-------------------------------------------------------------------
// gesture and touch handle
// source: http://msdn.microsoft.com/en-us/library/dd562197%28v=VS.85%29.aspx
//-------------------------------------------------------------------

LRESULT TouchSupport::OnTouch(HWND hWnd, WPARAM wParam, LPARAM lParam )
{
	BOOL bHandled = FALSE;
	UINT cInputs = LOWORD(wParam);
	PTOUCHINPUT pInputs = new TOUCHINPUT[cInputs];
	if (pInputs){
		if (GetTouchInputInfo((HTOUCHINPUT)lParam, cInputs, pInputs, sizeof(TOUCHINPUT))){
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
					initConnection(_port);
					sendTouchData(ti.x, ti.y, ti.dwID, ti.dwTime, type);
					closeConnection();
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
	}else{
		// if you didn't handle the message, let DefWindowProc handle it
		return DefWindowProc(hWnd, WM_TOUCH, wParam, lParam);
	}
}

LRESULT TouchSupport::OnGesture(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam){
    // Create a structure to populate and retrieve the extra message info.
    GESTUREINFO gi;  
    ZeroMemory(&gi, sizeof(GESTUREINFO));
    
    gi.cbSize = sizeof(GESTUREINFO);

    BOOL bResult  = GetGestureInfo((HGESTUREINFO)lParam, &gi);
    BOOL bHandled = FALSE;

    if (bResult){
        // now interpret the gesture
        switch (gi.dwID){
           case GID_ZOOM:
               // Code for zooming goes here     
			  
               bHandled = TRUE;
               break;
           case GID_PAN:
               // Code for panning goes here
               bHandled = TRUE;
               break;
           case GID_ROTATE:
               // Code for rotation goes here
               bHandled = TRUE;
               break;
           case GID_TWOFINGERTAP:
               // Code for two-finger tap goes here
               bHandled = TRUE;
               break;
           case GID_PRESSANDTAP:
               // Code for roll over goes here
               bHandled = TRUE;
               break;
           default:
               // A gesture was not recognized
               break;
        }
    }else{
        DWORD dwErr = GetLastError();
        if (dwErr > 0){
            //MessageBoxW(hWnd, L"Error!", L"Could not retrieve a GESTUREINFO structure.", MB_OK);
        }
    }
    if (bHandled){
        return 0;
    }else{
        return DefWindowProc(hWnd, message, wParam, lParam);
    }
}


// proxy wndproc 
LRESULT CALLBACK TouchSupport::WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	switch(message){
		case WM_TOUCH: {
			return TouchSupport::OnTouch(hWnd, wParam, lParam);
		}
		case WM_GESTURE: {
			return TouchSupport::OnGesture(hWnd, message, wParam, lParam);
		}
		default : break;
	}
	
	return CallWindowProc(_old_proc, hWnd, message, wParam, lParam);
}


