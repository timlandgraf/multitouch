Firemind.namespace("touchAPI");

Firemind.touchAPI.EventDispatcher = {
	
	_createTouchEvent : function(eventName, touch){
		var touchevt = Firemind.content.document.createEvent("UIEvents").wrappedJSObject;
	
		touchevt.initTouchEvent = function(type, canBubble, cancelable, view, detail, x, y, time, id){
			this.wrappedJSObject.initUIEvent(type, canBubble, cancelable, view, detail);
			this.wrappedJSObject.x = x;
			this.wrappedJSObject.y = y;
			this.wrappedJSObject.time = time;
			this.wrappedJSObject.id = id;
			this.wrappedJSObject.targetEl = Firemind.content.document.elementFromPoint(x, y);
		};

		touchevt.initTouchEvent(
			eventName, true, false, Firemind.content, 1, touch.x, touch.y, touch.time, touch.id	
		);
		
		return touchevt;
	},
	
	onTouchEvent : function(eventName, touch){
		var touchevt = this._createTouchEvent(eventName, touch);

		try {  
			var target = touchevt.targetEl;
			if(target)
				target.dispatchEvent(touchevt);
		} catch (e) {
			Firemind.reportError(e);
		}
	}
	
};
