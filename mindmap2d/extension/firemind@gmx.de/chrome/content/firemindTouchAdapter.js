Firemind.namespace("touchAPI");

Firemind.touchAPI.TouchList = {

	size : 0,
	data : {},

	add : function(touch){
		if(this.data[touch.id] == undefined)
			this.size++;
		this.data[touch.id] = touch;
		
	},
	
	remove : function(touch){
		delete this.data[touch.id];
		this.size--;
	}

};

Firemind.touchAPI.TouchAdapter = {

	EVENT_TOUCHDOWN : 0,
	EVENT_TOUCHUP : 1,
	EVENT_TOUCHMOVE : 2,

	TOUCH_TYPE_ONE : 1,
	TOUCH_TYPE_TWO : 2,
	
	// offers function pointers which are called with raw touch data
	// default: event dispatcher is called which fires events on each touch
	EVENTS : {

		onTouchDown: function(touch){
			Firemind.log("EVENT -> onTouchDown " + touch.id);
			
			Firemind.touchAPI.TouchList.add(touch);
			Firemind.log("EVENT -> touchList: " + Firemind.touchAPI.TouchList.size);
			
			switch(Firemind.touchAPI.TouchList.size){
				case Firemind.touchAPI.TouchAdapter.TOUCH_TYPE_ONE:
					Firemind.touchAPI.TapGesture.onBegin(touch);
					Firemind.touchAPI.FlickGesture.onBegin(touch);
					break;
				case Firemind.touchAPI.TouchAdapter.TOUCH_TYPE_TWO:
					Firemind.touchAPI.TapGesture.execute(
						touch, Firemind.touchAPI.TouchList.data
					);
					Firemind.touchAPI.FlickGesture.execute(
						touch, Firemind.touchAPI.TouchList.data
					);
					break;
				default: 
					Firemind.touchAPI.FlickGesture.onEnd();
					break;
			}
		
			Firemind.touchAPI.EventDispatcher.onTouchEvent("onTouchDown", touch);
		},
		
		onTouchMove: function(touch){
			Firemind.log("EVENT -> onTouchMove " + Firemind.touchAPI.TouchList.size);
		
			switch(Firemind.touchAPI.TouchList.size){
				case Firemind.touchAPI.TouchAdapter.TOUCH_TYPE_TWO:
					Firemind.touchAPI.FlickGesture.execute(
						touch, Firemind.touchAPI.TouchList.data
					);
					break;
				default: break;
			}
	
			Firemind.touchAPI.EventDispatcher.onTouchEvent("onTouchMove", touch);
		},
		
		onTouchUp: function(touch){
			Firemind.log("EVENT -> onTouchUp " + touch.id);
		
			switch(Firemind.touchAPI.TouchList.size){
				case Firemind.touchAPI.TouchAdapter.TOUCH_TYPE_TWO:
					Firemind.touchAPI.TapGesture.execute(
						touch, Firemind.touchAPI.TouchList.data
					);
					break;
				default: break;
			}
	
			Firemind.touchAPI.TapGesture.onEnd();
			Firemind.touchAPI.FlickGesture.onEnd();
	
			Firemind.touchAPI.TouchList.remove(touch);
			Firemind.log("EVENT -> touchList: " + Firemind.touchAPI.TouchList.size);
		
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
