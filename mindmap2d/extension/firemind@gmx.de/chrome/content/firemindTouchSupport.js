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
	
	//private:
	_nid : -1,
	_touchSupport : null,
	_baseWindow : null,

	_getBaseWindow : function(_window){
		return _window.QueryInterface(Ci.nsIInterfaceRequestor)
				.getInterface(Ci.nsIWebNavigation)
				.QueryInterface(Ci.nsIDocShellTreeItem)
				.treeOwner
				.QueryInterface(Ci.nsIInterfaceRequestor)
				.getInterface(Ci.nsIXULWindow)
				.docShell
				.QueryInterface(Ci.nsIBaseWindow);
	},
	
	_checkBit : function(pos){
		var shift = this._nid >> (pos - 1);
		return (shift & 1) == 1;
	},
	
	decodeNID : function(){
		try {
			if(this._touchSupport == null){
				var instance = Cc["@firemind.mozdev.org/touchSupport;1"].createInstance();
				this._touchSupport = instance.QueryInterface(Ci.ITouchSupport);
			}
			
			this._nid = this._touchSupport.checkTouchCapabilities();
		} catch(e){
			this._nid = 0;
		}
		
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
	
	register : function(_window, _callback){
		try { 
			if(!_window)
				throw "ERROR: Undefined parameter: _window!";
		
			if(!_callback)
				throw "ERROR: Undefined parameter: _callback!";
				
			// mouse simulator
			if(_callback == Firemind.touchAPI.MouseAdapter){
		
				var body = _window.document.body;
				
				body.addEventListener(
					"mousedown",
					_callback.EVENTS.onMouseDown,
					false
				);
				
				body.addEventListener(
					"mouseup",
					_callback.EVENTS.onMouseUp,
					false
				);
				
				body.addEventListener(
					"mousemove",
					_callback.EVENTS.onMouseMove,
					false
				);

				return true;
			
			}else { // real adapter -> touch or gesture
				
				//TODO
				var type = (_callback == Firemind.touchAPI.TouchAdapter) ? 0 : (_callback == Firemind.touchAPI.GestureAdapter) ? 1 : -1;
				
				if(type == -1)
					throw "ERROR: Unknown adapter, exspected touch or gesture!";
				
				var instance = Cc["@firemind.mozdev.org/touchSupport;1"].createInstance();
				this._touchSupport = instance.QueryInterface(Ci.ITouchSupport);
		
				// top level nsIBaseWindow
				this._baseWindow = this._getBaseWindow(_window);
				
				var rv = this._touchSupport.registerWindow(
					Firemind.touchAPI.TouchSupport._baseWindow, type, _callback
				);
				
				return rv;
				
			}
		} catch(e){
			Firemind.reportError(e);
		}
		
		return false;
	},

	unregister : function(){
		try {
			var rv = false;
			
			if(this._baseWindow){
				rv = this._touchSupport.unregisterWindow(
					Firemind.touchAPI.TouchSupport._baseWindow
				);
			}
			
			if(!rv){
				alert("ERROR: Can't unregister current base window!");
			}
			
			this.touchSupport = null;
			
			return rv;
		} catch(e){
			Firemind.reportError(e);
		}
		
		return false;
	}
};
