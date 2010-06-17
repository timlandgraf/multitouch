Firemind.namespace("touchAPI");

Firemind.touchAPI.TapGesture = {
	
	isRunning : false,

	touchOne : null,
	touchTwo : null,
	onTap : null,
	
	releaseThreshold : 500,
	
	onBegin : function(touchOne, onTap){
		this.isRunning = true;
		
		this.touchOne = touchOne;
		this.onTap = onTap;
	},
	
	onEnd : function(){
		this.isRunning = false;
		this.touchOne = null;
		this.touchTwo = null;
		this.onTap = null;
	},
	
	execute : function(currentTouch, touchList){
	
		if(this.touchTwo == null){
			this.touchTwo = currentTouch;
		}else {
			if(this.touchTwo.id == currentTouch.id && touchList[this.touchOne.id] != undefined && (currentTouch.time - this.touchTwo.time) <= this.releaseThreshold){
				this.onTap(this.touchOne);
			}
				
			this.onEnd();
		}
	
	}
	
};