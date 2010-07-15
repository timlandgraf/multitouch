Firemind.namespace("touchAPI");

Firemind.touchAPI.EventDispatcher = {
	
	_createEvent : function(eventName, touch, args){
		var touchevt = Firemind.content.document.createEvent("MouseEvents");
		
		touchevt.initMouseEvent(
			eventName,
			true,
			false, 
			Firemind.content,
			1,
			(args && args.newX)?args.newX:touch.screenX,
			(args && args.newY)?args.newY:touch.screenY,
            touch.clientX,
			touch.clientY,
			false,
			false,
			false,
			false,
			0,
			touch.target
		);
		
		return touchevt;
	},
	
	onTouchEvent : function(eventName, touch){
		var touchevt = this._createEvent(eventName, touch);

		try {  
			var target = touch.target; 
			
			if(target)
				target.dispatchEvent(touchevt);
		} catch (e) {
			Firemind.reportError(e);
		}
	},
	
	onGestureEvent : function(eventName, touch, args){
		var touchevt = this._createEvent(eventName, touch, args);

		try {  
			var target = touch.target; 
			
			if(target)
				target.dispatchEvent(touchevt);
		} catch (e) {
			Firemind.reportError(e);
		}
	}
	
};
