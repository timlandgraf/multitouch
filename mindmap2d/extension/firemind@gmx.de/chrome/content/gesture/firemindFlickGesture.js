Firemind.namespace("touchAPI");

Firemind.touchAPI.FlickGesture = {
	
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
	
		if(this.touchOne == null || Firemind.touchAPI.TapGesture.isRunning) return;
	
		if(this.touchTwo == null && this.touchOne.id != currentTouch.id){
			
			this.touchTwo = currentTouch;
			
			// calculate start distance
			var diffX = (this.touchOne.x - this.touchTwo.x);
			var diffY = (this.touchOne.y - this.touchTwo.y);
			this.distance = Math.sqrt(diffX * diffX + diffY * diffY);
	
		}else {

			if(this.touchTwo.id == currentTouch.id && touchList[this.touchOne.id] != undefined){
					
					var thr = 15;
					var varX = Math.abs(this.touchTwo.x - currentTouch.x);
					var varY = Math.abs(this.touchTwo.y - currentTouch.y);
			
					if(varX < thr && varY < thr) return;
			
					this.touchTwo = currentTouch;
			
					// calculate distance
					var diffX = (this.touchOne.x - this.touchTwo.x);
					var diffY = (this.touchOne.y - this.touchTwo.y);
					var r = Math.sqrt(diffX * diffX + diffY * diffY);
					
					// calculate angle
					var b = Math.abs(diffX);
					var cos = Math.acos(b / r);
					
					if(r < this.distance){
						Firemind.touchAPI.EventDispatcher.onGestureEvent("onFlickIn", this.touchOne, {radius: r, angle: cos, t2: this.touchTwo.id, t1: this.touchOne.id});
					}else if(r > this.distance){
						Firemind.touchAPI.EventDispatcher.onGestureEvent("onFlickOut", this.touchOne, {radius: r, angle: cos, t2: this.touchTwo.id, t1: this.touchOne.id});
					}
				
					this.distance = r;
				
			}
	
		}

	}
	
};