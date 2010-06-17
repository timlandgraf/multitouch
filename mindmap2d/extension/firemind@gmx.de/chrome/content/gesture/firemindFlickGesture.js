Firemind.namespace("touchAPI");

Firemind.touchAPI.FlickGesture = {
	
	isRunning : false,

	touchOne : null,
	touchTwo : null,
	
	onFlickIn : null,
	onFlickOut : null,
	startDistance : null,
	
	onBegin : function(touchOne, onFlickIn, onFlickOut){
		this.isRunning = true;
		
		this.touchOne = touchOne;
	
		this.onFlickIn = onFlickIn;
		this.onFlickOut = onFlickOut;
	},
	
	onEnd : function(){
		this.isRunning = false;
		this.touchOne = null;
		this.touchTwo = null;
		this.startDistance = null;
		this.onFlickIn = null;
		this.onFlickOut = null;
	},
	
	execute : function(currentTouch, touchList){
	
		if(this.touchOne == null) return;
	
		if(this.touchTwo == null){
			
			this.touchTwo = currentTouch;
			
			// calculate start distance
			var diffX = (this.touchOne.x - this.touchTwo.x);
			var diffY = (this.touchOne.y - this.touchTwo.y);
			this.startDistance = Math.sqrt(diffX * diffX + diffY * diffY);
			
		}else {

			if(this.touchTwo.id == currentTouch.id && touchList[this.touchOne.id] != undefined){
			
				this.touchTwo = currentTouch;
		
				// calculate distance
				var diffX = (this.touchOne.x - this.touchTwo.x);
				var diffY = (this.touchOne.y - this.touchTwo.y);
				var r = Math.sqrt(diffX * diffX + diffY * diffY);
				
				// calculate angle
				var b = Math.abs(diffX);
				var cos = Math.acos(b / r);
				
				if(r < this.startDistance){
					this.onFlickIn(this.touchOne, r, cos);
				}else {
					this.onFlickOut(this.touchOne, r, cos);
				}
			
			}else {
			
				this.onEnd();
				
			}
	
		}

	}
	
};