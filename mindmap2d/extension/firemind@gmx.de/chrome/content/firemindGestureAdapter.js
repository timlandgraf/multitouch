Firemind.namespace("touchAPI");

Firemind.touchAPI.GestureAdapter = {

	EVENTS : {
		onTap : function(touchOne){
			Firemind.log("EVENT -> onTap");
		
			Firemind.touchAPI.EventDispatcher.onTouchEvent(
				"onTap", touchOne
			);
		}
	}

};
