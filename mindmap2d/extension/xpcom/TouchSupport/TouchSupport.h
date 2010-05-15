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

  /* boolean registerWindow (in nsIBaseWindow window, in long type, in long port); */
  NS_SCRIPTABLE NS_IMETHOD RegisterWindow(nsIBaseWindow *window, PRInt32 type, PRInt32 port, PRBool *_retval) = 0;

  /* boolean unregisterWindow (in nsIBaseWindow window); */
  NS_SCRIPTABLE NS_IMETHOD UnregisterWindow(nsIBaseWindow *window, PRBool *_retval) = 0;

};

  NS_DEFINE_STATIC_IID_ACCESSOR(ITouchSupport, ITOUCHSUPPORT_IID)

/* Use this macro when declaring classes that implement this interface. */
#define NS_DECL_ITOUCHSUPPORT \
  NS_SCRIPTABLE NS_IMETHOD CheckTouchCapabilities(PRInt32 *_retval); \
  NS_SCRIPTABLE NS_IMETHOD RegisterWindow(nsIBaseWindow *window, PRInt32 type, PRInt32 port, PRBool *_retval); \
  NS_SCRIPTABLE NS_IMETHOD UnregisterWindow(nsIBaseWindow *window, PRBool *_retval); 

/* Use this macro to declare functions that forward the behavior of this interface to another object. */
#define NS_FORWARD_ITOUCHSUPPORT(_to) \
  NS_SCRIPTABLE NS_IMETHOD CheckTouchCapabilities(PRInt32 *_retval) { return _to CheckTouchCapabilities(_retval); } \
  NS_SCRIPTABLE NS_IMETHOD RegisterWindow(nsIBaseWindow *window, PRInt32 type, PRInt32 port, PRBool *_retval) { return _to RegisterWindow(window, type, port, _retval); } \
  NS_SCRIPTABLE NS_IMETHOD UnregisterWindow(nsIBaseWindow *window, PRBool *_retval) { return _to UnregisterWindow(window, _retval); } 

/* Use this macro to declare functions that forward the behavior of this interface to another object in a safe way. */
#define NS_FORWARD_SAFE_ITOUCHSUPPORT(_to) \
  NS_SCRIPTABLE NS_IMETHOD CheckTouchCapabilities(PRInt32 *_retval) { return !_to ? NS_ERROR_NULL_POINTER : _to->CheckTouchCapabilities(_retval); } \
  NS_SCRIPTABLE NS_IMETHOD RegisterWindow(nsIBaseWindow *window, PRInt32 type, PRInt32 port, PRBool *_retval) { return !_to ? NS_ERROR_NULL_POINTER : _to->RegisterWindow(window, type, port, _retval); } \
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

/* boolean registerWindow (in nsIBaseWindow window, in long type, in long port); */
NS_IMETHODIMP _MYCLASS_::RegisterWindow(nsIBaseWindow *window, PRInt32 type, PRInt32 port, PRBool *_retval)
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
