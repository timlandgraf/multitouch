Firemind.namespace("touchAPI");

Firemind.touchAPI.HoldTapGesture = {
	
	isRunning : false,

	touchOne : null,
	touchTwo : null,
	
	releaseThreshold : 500,
	cancelId : -1,
	
	onBegin : function(touchOne){
		this.isRunning = true;
		this.touchOne = touchOne;
	},
	
	onEnd : function(){
		Firemind.touchAPI.HoldTapGesture.isRunning = false;
		Firemind.touchAPI.HoldTapGesture.touchOne = null;
		Firemind.touchAPI.HoldTapGesture.touchTwo = null;
	},
	
	//touchList.size == 2
	execute : function(currentTouch, touchList){
		if(Firemind.touchAPI.HoldTapGesture.isRunning){
			if(this.touchTwo == null){
				
				this.touchTwo = currentTouch;
				this.cancelId = window.setTimeout(
					Firemind.touchAPI.HoldTapGesture.onEnd,
					Firemind.touchAPI.HoldTapGesture.releaseThreshold
				);
				
			}else {
				
				window.clearTimeout(this.cancelId);
				
				if(this.touchTwo.id == currentTouch.id && touchList[this.touchOne.id] != undefined && (currentTouch.time - this.touchTwo.time) <= this.releaseThreshold){
					Firemind.touchAPI.EventDispatcher.onGestureEvent("holdtap", this.touchOne);
				}
					
				this.onEnd();

			}
		}
	}
	
};