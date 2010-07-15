Firemind.namespace("touchAPI");

Firemind.touchAPI.PushPullGesture = {
	
	isRunning : false,

	touchOne : null,
	touchTwo : null,
	
	distance : null,
	distanceThr : 1,
	
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
	
		if(!Firemind.touchAPI.HoldTapGesture.isRunning && Firemind.touchAPI.PushPullGesture.isRunning){
	
			if(this.touchTwo == null){
				if(this.touchOne.id != currentTouch.id){
					this.touchTwo = currentTouch;

					var diffX = (this.touchOne.clientX - this.touchTwo.clientX);
					var diffY = (this.touchOne.clientY - this.touchTwo.clientY);
					this.distance = Math.sqrt(diffX * diffX + diffY * diffY);
					
					Firemind.log("INIT :" + diffX + " " + diffY + " " + this.distance);
				}
			}else {
			
				if(this.touchTwo.id == currentTouch.id && touchList[this.touchOne.id] != undefined){
					
					if(Firemind.touchAPI.GestureAdapter.isTouchMoving(this.touchTwo, currentTouch)){

						this.touchTwo = currentTouch;
						
						var diffX = (this.touchOne.clientX - this.touchTwo.clientX);
						var diffY = (this.touchOne.clientY - this.touchTwo.clientY);
						var r = Math.sqrt(diffX * diffX + diffY * diffY);
						
						Firemind.log("ELSE :" + diffX + " " + diffY + " " + r);
				
						if(r < this.distance - this.distanceThr){
							Firemind.touchAPI.EventDispatcher.onGestureEvent("pushin", this.touchOne, {newX: currentTouch.clientX, newY: currentTouch.clientY});
							this.onEnd();
						}else if(r > this.distance + this.distanceThr){
							Firemind.touchAPI.EventDispatcher.onGestureEvent("pullout", this.touchOne, {newX: currentTouch.clientX, newY: currentTouch.clientY});
							this.onEnd();
						}
						
					}
				}
			}
			
		}
	}
	
};