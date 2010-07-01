Firemind.namespace("touchAPI");

Firemind.touchAPI.GestureAdapter = {

	THR_FINGER_VAR : 15,
	
	isTouchMoving : function(touchOne, touchTwo){
		var varX = Math.abs(touchTwo.x - touchOne.x);
		var varY = Math.abs(touchTwo.y - touchOne.y);
			
		return !(
			varX < Firemind.touchAPI.GestureAdapter.THR_FINGER_VAR && 
			varY < Firemind.touchAPI.GestureAdapter.THR_FINGER_VAR
		);
	},
	
	isTouchNotMoving : function(touchOne, touchTwo){
		return !Firemind.touchAPI.GestureAdapter.isTouchMoving(touchOne, touchTwo);
	}

};
