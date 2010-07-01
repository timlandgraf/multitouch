Firemind.namespace("touchAPI");

Firemind.touchAPI.PushPullGesture = {
	
	isRunning : false,

	touchOne : null,
	touchTwo : null,
	
	distance : 200,
	
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
	
		if(this.touchOne == null || Firemind.touchAPI.HoldTapGesture.isRunning || !Firemind.touchAPI.PushPullGesture.isRunning) return;
	
		if(this.touchTwo == null && this.touchOne.id != currentTouch.id){

			this.touchTwo = currentTouch;

		}else {

			if(this.touchTwo.id == currentTouch.id && touchList[this.touchOne.id] != undefined){
				
				if(Firemind.touchAPI.GestureAdapter.isTouchMoving(this.touchTwo, currentTouch)){

					this.touchTwo = currentTouch;
			
					// calculate distance
					var diffX = (this.touchOne.x - this.touchTwo.x);
					var diffY = (this.touchOne.y - this.touchTwo.y);
					var r = Math.sqrt(diffX * diffX + diffY * diffY);
					
					// calculate angle
					var b = Math.abs(diffX);
					var cos = Math.acos(b / r);
					
					if(r < this.distance){
						Firemind.touchAPI.EventDispatcher.onGestureEvent("pushin", this.touchOne, {radius: r, angle: cos});
					}else if(r > this.distance){
						Firemind.touchAPI.EventDispatcher.onGestureEvent("pullout", this.touchOne, {radius: r, angle: cos});
					}
					
				}
			}
	
		}

	}
	
};