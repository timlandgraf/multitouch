Firemind.namespace("touchAPI");

Firemind.touchAPI.GestureAdapter = {

	THR_FINGER_VAR : 15,

	_gestures : [
		Firemind.touchAPI.TapGesture,
		Firemind.touchAPI.FlickGesture
	],

	isGestureInProgress : function(self){
		
		var rv = false, i = 0, l = this._gestures.length, gesture;
		for(;i < l;i++){
			gesture = this._gestures[i];
			if(gesture != self)
				rv = rv || gesture.isRunning;
		}
		
		return rv;
	}

};
