Firemind.namespace("touchAPI");

Firemind.touchAPI.TouchSupport = {
	
	//public:
	TABLET_CONFIG_NONE :	0x00000000,
	NID_INTEGRATED_TOUCH :	0x00000001,
	NID_EXTERNAL_TOUCH :	0x00000002,
	NID_INTEGRATED_PEN :	0x00000004,
	NID_EXTERNAL_PEN :		0x00000008,
	NID_MULTI_INPUT :		0x00000040,
	NID_READY :				0x00000080,
	
	REGISTER_TYPE_TOUCH :	0,
	REGISTER_TYPE_GESTURE :	1,
	
	//private:
	_nid : -1,
	_port : -1,
	_touchSupport : null,
	_baseWindow : null,
	_serverSocket : null,

	_getBaseWindow : function(_window){
		return _window.QueryInterface(Components.interfaces.nsIInterfaceRequestor)
				.getInterface(Components.interfaces.nsIWebNavigation)
				.QueryInterface(Components.interfaces.nsIDocShellTreeItem)
				.treeOwner
				.QueryInterface(Components.interfaces.nsIInterfaceRequestor)
				.getInterface(Components.interfaces.nsIXULWindow)
				.docShell
				.QueryInterface(Components.interfaces.nsIBaseWindow);
	},
	
	_checkBit : function(pos){
		var shift = this._nid >> (pos - 1);
		return (shift & 1) == 1;
	},
	
	decodeNID : function(){
		var retVal = {};
		retVal[this.TABLET_CONFIG_NONE] = this._nid == 0;
		retVal[this.NID_INTEGRATED_TOUCH] = this._checkBit(1);
		retVal[this.NID_EXTERNAL_TOUCH] = this._checkBit(2);
		retVal[this.NID_INTEGRATED_PEN] = this._checkBit(3);
		retVal[this.NID_EXTERNAL_PEN] = this._checkBit(4);
		retVal[this.NID_MULTI_INPUT] = this._checkBit(7);
		retVal[this.NID_READY] = this._checkBit(8);
		return retVal;
	},
	
	//STANDARD: gestures = false
	register : function(_window, _callback, gestures){
		gestures = (!gestures)?false:gestures;
		
		try {
			alert(_window.document.documentElement);
		
			var instance = Components.classes["@firemind.mozdev.org/touchSupport;1"].createInstance();
			this._touchSupport = instance.QueryInterface(Components.interfaces.ITouchSupport);
	
			// top level nsIBaseWindow
			this._baseWindow = this._getBaseWindow(_window);
			
			this._nid = this._touchSupport.checkTouchCapabilities();
			
			var rv = this._touchSupport.registerWindow(
				Firemind.touchAPI.TouchSupport._baseWindow,
				((gestures)?
				Firemind.touchAPI.TouchSupport.REGISTER_TYPE_GESTURE:
				Firemind.touchAPI.TouchSupport.REGISTER_TYPE_TOUCH),
				_callback
			);
		
			if(!rv){
				alert("ERROR: Can't register current base window!");
			}else {
				alert("Window registrated: " + rv + "\nNID: " + this._nid);
			}
			
		} catch(e){
			Firemind.reportError(e);
		}
	},
	
	unregister : function(gestures){
		gestures = (!gestures)?false:gestures;
	
		try {
			var rv = false;
			
			if(this._baseWindow){
				rv = this._touchSupport.unregisterWindow(
					Firemind.touchAPI.TouchSupport._baseWindow,
					((gestures)?
					Firemind.touchAPI.TouchSupport.REGISTER_TYPE_GESTURE:
					Firemind.touchAPI.TouchSupport.REGISTER_TYPE_TOUCH)
				);
			}
			
			if(!rv){
				alert("ERROR: Can't unregister current base window!");
			}
			
			this.touchSupport = null;
		} catch(e){
			Firemind.reportError(e);
		}
	}
};
