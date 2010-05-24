Firemind.namespace("touchAPI");

Firemind.touchAPI.EventDispatcher = {
	
	_createTouchEvent : function(eventName, touch){
		var touchevt = content.document.createEvent("UIEvents").wrappedJSObject;
	
		touchevt.initTouchEvent = function(type, canBubble, cancelable, view, detail, x, y, time, id){
			this.wrappedJSObject.initUIEvent(type, canBubble, cancelable, view, detail);
			this.wrappedJSObject.x = x;
			this.wrappedJSObject.y = y;
			this.wrappedJSObject.time = time;
			this.wrappedJSObject.id = id;
			this.wrappedJSObject.targetEl = content.document.elementFromPoint(x, y);
		};
	
		touchevt.initTouchEvent(
			eventName, true, false, content.window, 1, touch.x, touch.y, touch.time, touch.id	
		);
		
		return touchevt;
	},
	
	onTouchEvent : function(eventName, touch){
		var touchevt = this._createTouchEvent(eventName, touch);

		try { 
			var target = touchevt.targetEl;
			target.dispatchEvent(touchevt);
		} catch (e) {
			Firemind.reportError(e);
		}
	}
	
};
