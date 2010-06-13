Firemind.namespace("touchAPI");

Firemind.touchAPI.GestureAdapter = {

	EVENTS : {
		onTap : function(touch){
			Firemind.log("EVENT -> onTap");
		
			Firemind.touchAPI.EventDispatcher.onTouchEvent(
				"onTap", touch
			);
		}
	}

};
