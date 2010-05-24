Firemind.namespace("touchAPI");

Firemind.touchAPI.Gesture = function(x, y, id, args){

	// The x - coordinate of the gesture relative to the client viewport.
	this.x = x;
	
	// The y - coordinate of the gesture relative to the client viewport.
	this.y = y;
	
	// Unique gesture identifier.
	this.id = id ? id : -1;
	
	// Additional arguments depending on the gesture type.
	this.args = args;

};
