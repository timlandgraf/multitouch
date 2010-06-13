Firemind.namespace("touchAPI");

Firemind.touchAPI.TapGesture = {
	
	isRunning : false,

	touchOne : null,
	
	onTap : null,
	releaseThreshold : 500,
	
	onBegin : function(args){
		this.isRunning = true;
		
		this.touchOne = args.touchList[0];
		
		this.onTap = args.onTap;
	},
	
	onEnd : function(){
		this.isRunning = false;
		this.touchOne = null;
		this.onTap = null;
	},
	
	execute : function(args){
		var currentTime = new Date().getTime();
		if((currentTime - args.touch.time) <= this.releaseThreshold){
			this.onTap(this.touchOne);
		}
	}
	
};