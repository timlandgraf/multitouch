Firemind.namespace("touchAPI");

Firemind.touchAPI.MouseAdapter = {

	EVENTS : {
		onMouseDown: function(e){
			Firemind.touchAPI.EventDispatcher.onTouchEvent(
				"onTouchDown", new Firemind.touchAPI.Touch(e.clientX, e.clientY)
			);
		},
		onMouseMove: function(e){
			Firemind.touchAPI.EventDispatcher.onTouchEvent(
				"onTouchMove", new Firemind.touchAPI.Touch(e.clientX, e.clientY)
			);
		},
		onMouseUp: function(e){
			Firemind.touchAPI.EventDispatcher.onTouchEvent(
				"onTouchUp", new Firemind.touchAPI.Touch(e.clientX, e.clientY)
			);
		}
	}

};
