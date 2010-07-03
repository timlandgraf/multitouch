Firemind.namespace("touchAPI");

Firemind.touchAPI.RotateGesture = {
	
	isRunning : false,

	touchOne : null,
	touchTwo : null,
	
	distance : null,
	
	onBegin : function(touchOne){
		this.isRunning = true;
		
		this.touchOne = touchOne;
	},
	
	onEnd : function(){
		this.isRunning = false;
		this.touchOne = null;
		this.touchTwo = null;
		this.distance = null;
	},
	
	execute : function(currentTouch, touchList){
		if(Firemind.touchAPI.RotateGesture.isRunning){
		
		}
	}
	
};