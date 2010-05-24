Firemind.namespace("overlay");

Firemind.overlay = {

	_classOfDocument : function(root){
		var clazz = root.getAttribute("class");
		if(clazz && clazz != null){
			clazz = clazz.trim().replace(/\s+/gi, " ");
			var clazzes = clazz.split(" ");
			var i = 0, l = clazzes.length, c;
			for(;i < l; i++){
				c = clazzes[i];
				if(c == "touchEnable" || c == "gestureEnable" || c == "mouseEnable")
					return c;
			}
		}
		return "";
	},

	_insertScript : function(scriptStr, body) {
		var script = content.document.createElement("script");
		script.type = "text/javascript";
		script.innerHTML = scriptStr;
		
		body.appendChild(script);
	},
		
	tryRegisterEvents : function(event){
		
		if (event.originalTarget instanceof HTMLDocument) {
			var win = event.originalTarget.defaultView;
			if (win.frameElement) {
				return;
			}

			var body = win.document.body;
	
			if (!body || body == null){
				Firemind.log("ERROR: Can't find body tag. Maybe you wrote invalid html or xhtml.");
				return;
			}
			
			var clazz = Firemind.overlay._classOfDocument(body);
			var adapter = null;
			
			switch(clazz){
				case "touchEnable": adapter = Firemind.touchAPI.TouchAdapter; break;
				case "gestureEnable": adapter = Firemind.touchAPI.GestureAdapter; break;
				case "mouseEnable": adapter = Firemind.touchAPI.MouseAdapter; break;
				default: return;
			}
			
			function parseDocument(body){
				var walker = document.createTreeWalker(body, NodeFilter.SHOW_ELEMENT, null, false);
				var events = ["onTouchDown", "onTouchUp", "onTouchMove"];
			
				var i, l = events.length, e, fn, f, target;
				do {
					for (i = 0; i < l; i++) {
						e = events[i];
						target = walker.currentNode;
						fn = target.getAttribute(e);
						
						if (fn != null) {
							f = new Function("e", "return " + fn + "(e);");
							target.addEventListener(e, f, false);
						}
					}
				} while (walker.nextNode());
			}
			
			// parse document at startup
			var scriptStr = parseDocument.toString() + "\nparseDocument(document.body);";
		
			// offer addListener function to add events in a dynamic way
			function addListener(eventName, target){
				var fn = new Function("e", "return " + target.getAttribute(eventName) + "(e);");
				target.addEventListener(eventName, fn, false);
			}
			
			scriptStr += "\n" + addListener.toString();
			Firemind.overlay._insertScript(scriptStr, body);
			
			// finally register an adapter to receive event messages
			Firemind.touchAPI.TouchSupport.register(
				win, 
				adapter
			);
		}
	}

};

window.addEventListener("load", function (){
	// Add a callback to be run every time a document loads.
	// note that this includes frames/iframes within the document
	gBrowser.addEventListener("load", Firemind.overlay.tryRegisterEvents, true);
}, false);
