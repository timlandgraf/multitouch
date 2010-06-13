Firemind.namespace("touchAPI");

// direct mapping of mouse events to touch events
Firemind.touchAPI.MouseAdapter = {

	EVENTS : {
		onMouseDown: function(e){
			Firemind.touchAPI.TouchAdapter.EVENTS.onTouchDown(
				new Firemind.touchAPI.Touch(e.clientX, e.clientY)
			);
		},
		onMouseMove: function(e){
			Firemind.touchAPI.TouchAdapter.EVENTS.onTouchMove(
				new Firemind.touchAPI.Touch(e.clientX, e.clientY)
			);
		},
		onMouseUp: function(e){
			Firemind.touchAPI.TouchAdapter.EVENTS.onTouchUp(
				new Firemind.touchAPI.Touch(e.clientX, e.clientY)
			);
		}
	}

};
