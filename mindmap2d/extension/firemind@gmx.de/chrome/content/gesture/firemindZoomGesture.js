Firemind.namespace("touchAPI");

Firemind.touchAPI.ZoomGesture = {
	
	isRunning : false,

	touchOne : null,
	touchTwo : null,

	onZoomIn : null,
	onZoomOut : null,
	distance : null,
	
	moveThreshold : 500,
	
	onBegin : function(touchOne, onZoomIn, onZoomOut){
		this.isRunning = true;

		this.touchOne = touchOne;

		this.onZoomIn = onZoomIn;
		this.onZoomOut = onZoomOut;
	},
	
	onEnd : function(){
		this.isRunning = false;
		this.touchOne = null;
		this.touchTwo = null;
		this.distance = null;
		this.onZoomIn = null;
		this.onZoomOut = null;
	},
	
	execute : function(currentTouch, touchList){
		if(Firemind.touchAPI.ZoomGesture.isRunning){
		
		}
	}
	
};