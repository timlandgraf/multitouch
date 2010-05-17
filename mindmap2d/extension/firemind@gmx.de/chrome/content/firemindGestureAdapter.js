Firemind.namespace("touchAPI");

Firemind.touchAPI.GestureAdapter = {

	EVENT_ONZOOM: 3,
	EVENT_ONPAN: 4,
	EVENT_ONROTATE: 5,
	EVENT_ONTWOFINGERTAP: 6,
	EVENT_ONPRESSANDTAP: 7,

	EVENTS : {
		onZoom : function(gesture){},
		onPan : function(gesture){},
		onRotate : function(gesture){},
		onTwoFingerTap : function(gesture){},
		onPressAndTap : function(gesture){}
	},

	acceptGesture : function(id_, state_, x_, y_, hi_, lo_){
		switch(id_){
			case Firemind.touchAPI.GestureAdapter.EVENT_ONZOOM:
				Firemind.touchAPI.GestureAdapter.EVENTS.onZoom(
					{}
				);
				return;
			case Firemind.touchAPI.GestureAdapter.EVENT_ONPAN:
				Firemind.touchAPI.GestureAdapter.EVENTS.onPan(
					{}
				);
				return;
			case Firemind.touchAPI.GestureAdapter.EVENT_ONROTATE:
				Firemind.touchAPI.GestureAdapter.EVENTS.onRotate(
					{}
				);
				return;
			case Firemind.touchAPI.GestureAdapter.EVENT_ONTWOFINGERTAP:
				Firemind.touchAPI.GestureAdapter.EVENTS.onTwoFingerTap(
					{}
				);
				return;
			case Firemind.touchAPI.GestureAdapter.EVENT_ONPRESSANDTAP:
				Firemind.touchAPI.GestureAdapter.EVENTS.onPressAndTap(
					{}
				);
				return;
			default: throw "ERROR: Undefined event of type: " + id_;
		}
	}

};