Firemind.namespace("touchAPI");

Firemind.touchAPI.GestureAdapter = {

	isTouchMoving : function(touchOne, touchTwo){
		var varX = Math.abs(touchTwo.x - touchOne.x);
		var varY = Math.abs(touchTwo.y - touchOne.y);
			
		return !(
			varX < touchOne.cxContact && 
			varY < touchOne.cyContact
		);
	},
	
	isTouchNotMoving : function(touchOne, touchTwo){
		return !Firemind.touchAPI.GestureAdapter.isTouchMoving(touchOne, touchTwo);
	}

};
