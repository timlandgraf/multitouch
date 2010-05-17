/*
 * DO NOT EDIT.  THIS FILE IS GENERATED FROM TouchSupport.idl
 */

#ifndef __gen_TouchSupport_h__
#define __gen_TouchSupport_h__


#ifndef __gen_nsISupports_h__
#include "nsISupports.h"
#endif

#ifndef __gen_nsIBaseWindow_h__
#include "nsIBaseWindow.h"
#endif

/* For IDL files that don't want to include root IDL files. */
#ifndef NS_NO_VTABLE
#define NS_NO_VTABLE
#endif

/* starting interface:    IJSCallback */
#define IJSCALLBACK_IID_STR "9ffa0bdb-1780-4e39-90b9-4cc7b2deb7d7"

#define IJSCALLBACK_IID \
  {0x9ffa0bdb, 0x1780, 0x4e39, \
    { 0x90, 0xb9, 0x4c, 0xc7, 0xb2, 0xde, 0xb7, 0xd7 }}

class NS_NO_VTABLE NS_SCRIPTABLE IJSCallback : public nsISupports {
 public: 

  NS_DECLARE_STATIC_IID_ACCESSOR(IJSCALLBACK_IID)

  /* void acceptTouch (in long id_, in long x_, in long y_, in long time_, in long type_); */
  NS_SCRIPTABLE NS_IMETHOD AcceptTouch(PRInt32 id_, PRInt32 x_, PRInt32 y_, PRInt32 time_, PRInt32 type_) = 0;

  /* void acceptGesture (in long id_, in long state_, in long x_, in long y_, in long hi_, in long lo_); */
  NS_SCRIPTABLE NS_IMETHOD AcceptGesture(PRInt32 id_, PRInt32 state_, PRInt32 x_, PRInt32 y_, PRInt32 hi_, PRInt32 lo_) = 0;

};

  NS_DEFINE_STATIC_IID_ACCESSOR(IJSCallback, IJSCALLBACK_IID)

/* Use this macro when declaring classes that implement this interface. */
#define NS_DECL_IJSCALLBACK \
  NS_SCRIPTABLE NS_IMETHOD AcceptTouch(PRInt32 id_, PRInt32 x_, PRInt32 y_, PRInt32 time_, PRInt32 type_); \
  NS_SCRIPTABLE NS_IMETHOD AcceptGesture(PRInt32 id_, PRInt32 state_, PRInt32 x_, PRInt32 y_, PRInt32 hi_, PRInt32 lo_); 

/* Use this macro to declare functions that forward the behavior of this interface to another object. */
#define NS_FORWARD_IJSCALLBACK(_to) \
  NS_SCRIPTABLE NS_IMETHOD AcceptTouch(PRInt32 id_, PRInt32 x_, PRInt32 y_, PRInt32 time_, PRInt32 type_) { return _to AcceptTouch(id_, x_, y_, time_, type_); } \
  NS_SCRIPTABLE NS_IMETHOD AcceptGesture(PRInt32 id_, PRInt32 state_, PRInt32 x_, PRInt32 y_, PRInt32 hi_, PRInt32 lo_) { return _to AcceptGesture(id_, state_, x_, y_, hi_, lo_); } 

/* Use this macro to declare functions that forward the behavior of this interface to another object in a safe way. */
#define NS_FORWARD_SAFE_IJSCALLBACK(_to) \
  NS_SCRIPTABLE NS_IMETHOD AcceptTouch(PRInt32 id_, PRInt32 x_, PRInt32 y_, PRInt32 time_, PRInt32 type_) { return !_to ? NS_ERROR_NULL_POINTER : _to->AcceptTouch(id_, x_, y_, time_, type_); } \
  NS_SCRIPTABLE NS_IMETHOD AcceptGesture(PRInt32 id_, PRInt32 state_, PRInt32 x_, PRInt32 y_, PRInt32 hi_, PRInt32 lo_) { return !_to ? NS_ERROR_NULL_POINTER : _to->AcceptGesture(id_, state_, x_, y_, hi_, lo_); } 

#if 0
/* Use the code below as a template for the implementation class for this interface. */

/* Header file */
class _MYCLASS_ : public IJSCallback
{
public:
  NS_DECL_ISUPPORTS
  NS_DECL_IJSCALLBACK

  _MYCLASS_();

private:
  ~_MYCLASS_();

protected:
  /* additional members */
};

/* Implementation file */
NS_IMPL_ISUPPORTS1(_MYCLASS_, IJSCallback)

_MYCLASS_::_MYCLASS_()
{
  /* member initializers and constructor code */
}

_MYCLASS_::~_MYCLASS_()
{
  /* destructor code */
}

/* void acceptTouch (in long id_, in long x_, in long y_, in long time_, in long type_); */
NS_IMETHODIMP _MYCLASS_::AcceptTouch(PRInt32 id_, PRInt32 x_, PRInt32 y_, PRInt32 time_, PRInt32 type_)
{
    return NS_ERROR_NOT_IMPLEMENTED;
}

/* void acceptGesture (in long id_, in long state_, in long x_, in long y_, in long hi_, in long lo_); */
NS_IMETHODIMP _MYCLASS_::AcceptGesture(PRInt32 id_, PRInt32 state_, PRInt32 x_, PRInt32 y_, PRInt32 hi_, PRInt32 lo_)
{
    return NS_ERROR_NOT_IMPLEMENTED;
}

/* End of implementation class template. */
#endif


/* starting interface:    ITouchSupport */
#define ITOUCHSUPPORT_IID_STR "8641dce1-2ec6-4eed-9d72-e4743d6f13e6"

#define ITOUCHSUPPORT_IID \
  {0x8641dce1, 0x2ec6, 0x4eed, \
    { 0x9d, 0x72, 0xe4, 0x74, 0x3d, 0x6f, 0x13, 0xe6 }}

class NS_NO_VTABLE NS_SCRIPTABLE ITouchSupport : public nsISupports {
 public: 

  NS_DECLARE_STATIC_IID_ACCESSOR(ITOUCHSUPPORT_IID)

  /* long checkTouchCapabilities (); */
  NS_SCRIPTABLE NS_IMETHOD CheckTouchCapabilities(PRInt32 *_retval) = 0;

  /* boolean registerWindow (in nsIBaseWindow window, in long type, in IJSCallback observer); */
  NS_SCRIPTABLE NS_IMETHOD RegisterWindow(nsIBaseWindow *window, PRInt32 type, IJSCallback *observer, PRBool *_retval) = 0;

  /* boolean unregisterWindow (in nsIBaseWindow window); */
  NS_SCRIPTABLE NS_IMETHOD UnregisterWindow(nsIBaseWindow *window, PRBool *_retval) = 0;

};

  NS_DEFINE_STATIC_IID_ACCESSOR(ITouchSupport, ITOUCHSUPPORT_IID)

/* Use this macro when declaring classes that implement this interface. */
#define NS_DECL_ITOUCHSUPPORT \
  NS_SCRIPTABLE NS_IMETHOD CheckTouchCapabilities(PRInt32 *_retval); \
  NS_SCRIPTABLE NS_IMETHOD RegisterWindow(nsIBaseWindow *window, PRInt32 type, IJSCallback *observer, PRBool *_retval); \
  NS_SCRIPTABLE NS_IMETHOD UnregisterWindow(nsIBaseWindow *window, PRBool *_retval); 

/* Use this macro to declare functions that forward the behavior of this interface to another object. */
#define NS_FORWARD_ITOUCHSUPPORT(_to) \
  NS_SCRIPTABLE NS_IMETHOD CheckTouchCapabilities(PRInt32 *_retval) { return _to CheckTouchCapabilities(_retval); } \
  NS_SCRIPTABLE NS_IMETHOD RegisterWindow(nsIBaseWindow *window, PRInt32 type, IJSCallback *observer, PRBool *_retval) { return _to RegisterWindow(window, type, observer, _retval); } \
  NS_SCRIPTABLE NS_IMETHOD UnregisterWindow(nsIBaseWindow *window, PRBool *_retval) { return _to UnregisterWindow(window, _retval); } 

/* Use this macro to declare functions that forward the behavior of this interface to another object in a safe way. */
#define NS_FORWARD_SAFE_ITOUCHSUPPORT(_to) \
  NS_SCRIPTABLE NS_IMETHOD CheckTouchCapabilities(PRInt32 *_retval) { return !_to ? NS_ERROR_NULL_POINTER : _to->CheckTouchCapabilities(_retval); } \
  NS_SCRIPTABLE NS_IMETHOD RegisterWindow(nsIBaseWindow *window, PRInt32 type, IJSCallback *observer, PRBool *_retval) { return !_to ? NS_ERROR_NULL_POINTER : _to->RegisterWindow(window, type, observer, _retval); } \
  NS_SCRIPTABLE NS_IMETHOD UnregisterWindow(nsIBaseWindow *window, PRBool *_retval) { return !_to ? NS_ERROR_NULL_POINTER : _to->UnregisterWindow(window, _retval); } 

#if 0
/* Use the code below as a template for the implementation class for this interface. */

/* Header file */
class _MYCLASS_ : public ITouchSupport
{
public:
  NS_DECL_ISUPPORTS
  NS_DECL_ITOUCHSUPPORT

  _MYCLASS_();

private:
  ~_MYCLASS_();

protected:
  /* additional members */
};

/* Implementation file */
NS_IMPL_ISUPPORTS1(_MYCLASS_, ITouchSupport)

_MYCLASS_::_MYCLASS_()
{
  /* member initializers and constructor code */
}

_MYCLASS_::~_MYCLASS_()
{
  /* destructor code */
}

/* long checkTouchCapabilities (); */
NS_IMETHODIMP _MYCLASS_::CheckTouchCapabilities(PRInt32 *_retval)
{
    return NS_ERROR_NOT_IMPLEMENTED;
}

/* boolean registerWindow (in nsIBaseWindow window, in long type, in IJSCallback observer); */
NS_IMETHODIMP _MYCLASS_::RegisterWindow(nsIBaseWindow *window, PRInt32 type, IJSCallback *observer, PRBool *_retval)
{
    return NS_ERROR_NOT_IMPLEMENTED;
}

/* boolean unregisterWindow (in nsIBaseWindow window); */
NS_IMETHODIMP _MYCLASS_::UnregisterWindow(nsIBaseWindow *window, PRBool *_retval)
{
    return NS_ERROR_NOT_IMPLEMENTED;
}

/* End of implementation class template. */
#endif


#endif /* __gen_TouchSupport_h__ */
