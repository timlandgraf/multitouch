Firemind.namespace("touchAPI");

Firemind.touchAPI.TapGesture = {
	
	isRunning : false,

	touchOne : null,

	releaseThreshold : 200,
	doubleTapReleaseThreshold : 200,
	
	tapCancelId : -1,
	doubleTapCancelId : -1,
	
	tapCount : 0,
	
	onBegin : function(touchOne){
		this.isRunning = true;
		this.touchOne = touchOne;
		
		this.tapCancelId = window.setTimeout(
			Firemind.touchAPI.TapGesture.onEnd,
			Firemind.touchAPI.TapGesture.releaseThreshold
		);
	},
	
	onEnd : function(){
		Firemind.touchAPI.TapGesture.isRunning = false;
		Firemind.touchAPI.TapGesture.touchOne = null;
	},
	
	executeTap : function(){
		
		Firemind.touchAPI.TapGesture.tapCount = 0;
		
		Firemind.touchAPI.EventDispatcher.onGestureEvent("tap", this.touchOne);
		delete window.touchOne;
		
		this.onEnd();
		delete window.onEnd;
		
	},
	
	//touchList.size == 1
	execute : function(currentTouch, touchList){
	
		window.clearTimeout(this.tapCancelId);
		
		if(this.touchOne.id == currentTouch.id && touchList[this.touchOne.id] != undefined && (currentTouch.time - this.touchOne.time) <= this.releaseThreshold){
			
			Firemind.touchAPI.TapGesture.tapCount++;
			
			if(Firemind.touchAPI.TapGesture.tapCount == 1){
				
				window.touchOne = Firemind.touchAPI.TapGesture.touchOne;
				window.onEnd = Firemind.touchAPI.TapGesture.onEnd;
				
				this.doubleTapCancelId = window.setTimeout(
					Firemind.touchAPI.TapGesture.executeTap,
					Firemind.touchAPI.TapGesture.doubleTapReleaseThreshold
				);
				
			}else if(Firemind.touchAPI.TapGesture.tapCount == 2){
				
				window.clearTimeout(this.doubleTapCancelId);
				Firemind.touchAPI.TapGesture.tapCount = 0;
				Firemind.touchAPI.EventDispatcher.onGestureEvent("doubletap", this.touchOne);
				
			}else {
			
				Firemind.touchAPI.TapGesture.onEnd();
				
			}
			
			
		}
				
		this.onEnd();

	}
	
};