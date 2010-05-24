Firemind.namespace("touchAPI");

Firemind.touchAPI.Touch = function(x, y, id, time){

	// The x - coordinate of the touch relative to the client viewport.
	this.x = x;
	
	// The y - coordinate of the touch relative to the client viewport.
	this.y = y;
	
	// The width of the touch contact area.
	this.cxContact = Firemind.prefs().getIntPref("firemind.touch.contactx");
	
	// The height of the touch contact area.
	this.cyContact = Firemind.prefs().getIntPref("firemind.touch.contacty");
	
	// Unique touch identifier.
	this.id = id ? id : -1;
	
	// Time stamp for the touch event.
	this.time = time ? time : new Date().getTime();

};
