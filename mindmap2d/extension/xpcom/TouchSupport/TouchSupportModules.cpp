#include "nsIGenericFactory.h"

#include "TouchSupportImpl.h"

NS_GENERIC_FACTORY_CONSTRUCTOR(TouchSupport)

static const nsModuleComponentInfo components[] =
{
    {
       TOUCHSUPPORT_CLASSNAME, 
       TOUCHSUPPORT_CID,
       TOUCHSUPPORT_CONTRACTID,
       TouchSupportConstructor,
    }
};

NS_IMPL_NSGETMODULE("TouchSupportModule", components) 
