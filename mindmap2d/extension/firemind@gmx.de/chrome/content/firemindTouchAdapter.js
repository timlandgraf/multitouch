Firemind.namespace("touchAPI");

Firemind.touchAPI.TouchAdapter = {
	
	EVENT_TOUCHDOWN : 0,
	EVENT_TOUCHUP : 1,
	EVENT_TOUCHMOVE : 2,
	
	// offers function pointers which are called with raw touch data
	// default: event dispatcher is called which fires events on each touch
	EVENTS : {
		onTouchDown: function(touch){
			Firemind.touchAPI.EventDispatcher.onTouchEvent("onTouchDown", touch);
		},
		onTouchMove: function(touch){
			Firemind.touchAPI.EventDispatcher.onTouchEvent("onTouchMove", touch);
		},
		onTouchUp: function(touch){
			Firemind.touchAPI.EventDispatcher.onTouchEvent("onTouchUp", touch);
		}
	},
	
	// wrapps raw touch data into an touch object
	acceptTouch : function(id_, x_, y_, time_, type_){
		switch(type_){
			case Firemind.touchAPI.TouchAdapter.EVENT_TOUCHDOWN : 
				Firemind.touchAPI.TouchAdapter.EVENTS.onTouchDown(
					new Firemind.touchAPI.Touch(x_, y_, id_, time_)
				);
				return;
			case Firemind.touchAPI.TouchAdapter.EVENT_TOUCHUP : 
				Firemind.touchAPI.TouchAdapter.EVENTS.onTouchUp(
					new Firemind.touchAPI.Touch(x_, y_, id_, time_)
				);
				return;
			case Firemind.touchAPI.TouchAdapter.EVENT_TOUCHMOVE : 
				Firemind.touchAPI.TouchAdapter.EVENTS.onTouchMove(
					new Firemind.touchAPI.Touch(x_, y_, id_, time_)
				);
				return;
			default: throw "ERROR: Undefined event of type: " + type_;
		}
	}
	
};
