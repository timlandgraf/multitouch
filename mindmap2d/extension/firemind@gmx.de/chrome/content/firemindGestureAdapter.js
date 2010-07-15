Firemind.namespace("touchAPI");

Firemind.touchAPI.GestureAdapter = {

	isTouchMoving : function(touchOne, touchTwo){
		var varX = Math.abs(touchTwo.clientX - touchOne.clientX);
		var varY = Math.abs(touchTwo.clientY - touchOne.clientY);
	
		return !(
			varX < touchOne.cxContact && 
			varY < touchOne.cyContact
		);
	},
	
	isTouchNotMoving : function(touchOne, touchTwo){
		return !Firemind.touchAPI.GestureAdapter.isTouchMoving(touchOne, touchTwo);
	}

};
