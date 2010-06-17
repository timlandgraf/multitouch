Firemind.namespace("touchAPI");

Firemind.touchAPI.TapGesture = {
	
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
		Firemind.touchAPI.TapGesture.isRunning = false;
		Firemind.touchAPI.TapGesture.touchOne = null;
		Firemind.touchAPI.TapGesture.touchTwo = null;
	},
	
	execute : function(currentTouch, touchList){
	
		if(this.touchTwo == null){
			
			this.touchTwo = currentTouch;
			this.cancelId = window.setTimeout(
				Firemind.touchAPI.TapGesture.onEnd,
				Firemind.touchAPI.TapGesture.releaseThreshold
			);
			
		}else {
			
			window.clearTimeout(this.cancelId);
			
			if(this.touchTwo.id == currentTouch.id && touchList[this.touchOne.id] != undefined && (currentTouch.time - this.touchTwo.time) <= this.releaseThreshold){
				Firemind.touchAPI.EventDispatcher.onGestureEvent("onTap", this.touchOne);
			}
				
			this.onEnd();

		}
	
	}
	
};