Firemind.namespace("touchAPI");

Firemind.touchAPI.TouchAdapter = {
	
	EVENT_TOUCHDOWN : 0,
	EVENT_TOUCHUP : 1,
	EVENT_TOUCHMOVE : 2,
	
	EVENTS : {
		onTouchDown: function(touch){
			//alert("touchDown, x = " + touch.x + ", y = " + touch.y);
		},
		onTouchMove: function(touch){
			//alert("touchMove, x = " + touch.x + ", y = " + touch.y);
		},
		onTouchUp: function(touch){
			//alert("touchUp, x = " + touch.x + ", y = " + touch.y);
		}
	},
	
	acceptTouch : function(id_, x_, y_, time_, type_){
		switch(type_){
			case Firemind.touchAPI.TouchAdapter.EVENT_TOUCHDOWN : 
				Firemind.touchAPI.TouchAdapter.EVENTS.onTouchDown(
					{id: id_, x: x_, y: y_, time: time_}
				);
				return;
			case Firemind.touchAPI.TouchAdapter.EVENT_TOUCHUP : 
				Firemind.touchAPI.TouchAdapter.onTouchUp(
					{id: id_, x: x_, y: y_, time: time_}
				);
				return;
			case Firemind.touchAPI.TouchAdapter.EVENT_TOUCHMOVE : 
				Firemind.touchAPI.TouchAdapter.onTouchMove(
					{id: id_, x: x_, y: y_, time: time_}
				);
				return;
			default: throw "ERROR: Undefined event of type: " + type_;
		}
	}
	
};
