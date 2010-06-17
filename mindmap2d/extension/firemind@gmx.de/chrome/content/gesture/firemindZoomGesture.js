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
	
		if(this.touchOne == null) return;
	
		if(this.touchTwo == null){
			
			this.touchTwo = currentTouch;
			
			// calculate start distance
			var diffX = (this.touchOne.x - this.touchTwo.x);
			var diffY = (this.touchOne.y - this.touchTwo.y);
			this.distance = Math.sqrt(diffX * diffX + diffY * diffY);
			
		}else {
		
		}
	
	
		var currentTime = new Date().getTime();
		var exec = (currentTime - args.touch.time) <= this.moveThreshold;
		
		this.touchOne = this.touchOne.id == args.touch.id ? args.touch : this.touchOne;
		this.touchTwo = this.touchTwo.id == args.touch.id ? args.touch : this.touchTwo;
		
		if(exec){
		
			// calculate distance
			var diffX = (this.touchOne.x - this.touchTwo.x);
			var diffY = (this.touchOne.y - this.touchTwo.y);
			var r = Math.sqrt(diffX * diffX + diffY * diffY);
		
			if(r < this.distance){
				this.distance = r;
				this.onZoomIn();
			}else {
				this.distance = r;
				this.onZoomOut();
			}
			
		}

	}
	
};