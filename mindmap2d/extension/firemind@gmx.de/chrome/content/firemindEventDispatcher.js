Firemind.namespace("touchAPI");

Firemind.touchAPI.EventDispatcher = {
	
	_createEvent : function(eventName, touch, args){
		var touchevt = Firemind.content.document.createEvent("UIEvents").wrappedJSObject;
	
		touchevt.initTouchEvent = function(
			type, 
			canBubble, 
			cancelable, 
			view, 
			detail, 
			clientX, 
			clientY, 
			screenX, 
			screenY, 
			time, 
			id, 
			args){
			
			this.wrappedJSObject.initUIEvent(type, canBubble, cancelable, view, detail);
			this.wrappedJSObject.clientX = clientX;
			this.wrappedJSObject.clientY = clientY;
			this.wrappedJSObject.screenX = screenX;
			this.wrappedJSObject.screenY = screenY;
			this.wrappedJSObject.time = time;
			this.wrappedJSObject.id = id;
			this.wrappedJSObject.targetEl = Firemind.content.document.elementFromPoint(clientX, clientY);
			
			this.wrappedJSObject.radius = (args && args.radius) ? args.radius : -1;
			this.wrappedJSObject.angle = (args && args.angle) ? args.angle : -1;
			
		};

		touchevt.initTouchEvent(
			eventName, 
			true, 
			false, 
			Firemind.content, 
			1, 
			touch.clientX, 
			touch.clientY, 
			touch.screenX, 
			touch.screenY, 
			touch.time, 
			touch.id, 
			args
		);
		
		return touchevt;
	},
	
	onTouchEvent : function(eventName, touch){
		var touchevt = this._createEvent(eventName, touch);

		try {  
			var target = touchevt.targetEl; 
			
			if(target)
				target.dispatchEvent(touchevt);
		} catch (e) {
			Firemind.reportError(e);
		}
	},
	
	onGestureEvent : function(eventName, touch, args){
		var touchevt = this._createEvent(eventName, touch, args);

		try {  
			var target = touchevt.targetEl; 
			
			if(target)
				target.dispatchEvent(touchevt);
		} catch (e) {
			Firemind.reportError(e);
		}
	}
	
};
