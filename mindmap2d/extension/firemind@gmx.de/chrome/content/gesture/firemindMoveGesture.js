Firemind.namespace("touchAPI");

Firemind.touchAPI.MoveGesture = {
	
	isRunning : false,

	touchOne : null,

	onBegin : function(touchOne){
		this.isRunning = true;
		this.touchOne = touchOne;
	},
	
	onEnd : function(){
		Firemind.touchAPI.MoveGesture.isRunning = false;
		Firemind.touchAPI.MoveGesture.touchOne = null;
	},
	
	//touchList.size == 1
	execute : function(currentTouch, touchList){
		if(Firemind.touchAPI.MoveGesture.isRunning){
			if(this.touchOne.id == currentTouch.id && touchList[this.touchOne.id] != undefined && Firemind.touchAPI.GestureAdapter.isTouchMoving(this.touchOne, currentTouch)){
				Firemind.touchAPI.EventDispatcher.onGestureEvent("move", currentTouch);
			}
		}
	}
	
};