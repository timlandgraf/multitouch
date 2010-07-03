#ifndef __TouchSupport_H__
#define __TouchSupport_H__

#include "TouchSupport.h"

#include "nsCOMPtr.h"
#include "nsIBaseWindow.h"
#include "nsIWidget.h"

#include <windows.h>
#include <Winuser.h>

#define TOUCHSUPPORT_REF_PROP "TOUCHSUPPORT_REF_PROP"
#define MOUSEEVENTF_FROMTOUCH 0xFF515700

class JSCallback : public IJSCallback
{
public:
  NS_DECL_ISUPPORTS
  NS_DECL_IJSCALLBACK

  JSCallback();

private:
  ~JSCallback();
};

#define TOUCHSUPPORT_CONTRACTID "@firemind.mozdev.org/touchSupport;1"
#define TOUCHSUPPORT_CLASSNAME "TouchSupport"
#define TOUCHSUPPORT_CID   {0x8641dce1, 0x2ec6, 0x4eed, { 0x9d, 0x72, 0xe4, 0x74, 0x3d, 0x6f, 0x13, 0xe6 }}

class TouchSupport : public ITouchSupport
{
public:
  NS_DECL_ISUPPORTS
  NS_DECL_ITOUCHSUPPORT

  PRInt32 type;

  IJSCallback *observer;
  WNDPROC oldProc;

  TouchSupport();

  static LRESULT OnTouch(HWND hWnd, WPARAM wParam, LPARAM lParam);
  static LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam);

private:
  ~TouchSupport();
};

#endif
