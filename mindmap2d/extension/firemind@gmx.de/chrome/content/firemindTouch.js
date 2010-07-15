Firemind.namespace("touchAPI");

Firemind.touchAPI.Touch = function(clientX, clientY, screenX, screenY, id, time){

	// The x - coordinate of the touch relative to the client viewport.
	this.clientX = clientX;
	
	// The y - coordinate of the touch relative to the client viewport.
	this.clientY = clientY;
	
	// The screens x - coordinate where the touch was executed.
	this.screenX = screenX;
	
	// The screens y - coordinate where the touch was executed.
	this.screenY = screenY;
	
	// Target of the touch event
	this.target = Firemind.content.document.elementFromPoint(clientX, clientY);
	
	// The width of the touch contact area.
	this.cxContact = Firemind.prefs().getIntPref("firemind.touch.contactx");
	
	// The height of the touch contact area.
	this.cyContact = Firemind.prefs().getIntPref("firemind.touch.contacty");
	
	// Unique touch identifier.
	this.id = id ? id : -1;
	
	// Time stamp for the touch event.
	this.time = time ? time : new Date().getTime();

};
