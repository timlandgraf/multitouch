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

<<<<<<< HEAD
=======
	_insertScript : function(scriptStr, body) {
		var script = content.document.createElement("script");
		script.type = "text/javascript";
		script.innerHTML = scriptStr;
		
		body.appendChild(script);
		
		var test = content.document.createElement("div");
		alert(document.location);
		test.setAttribute("style", "width: 100px; height: 100px; background-color: red;");
		body.appendChild(test);
		
	},
		
>>>>>>> 60640c56279e0afbf4f5f35507b57bbc7d5a9085
	tryRegisterEvents : function(event){
		
		if (event.originalTarget instanceof HTMLDocument) {
			var win = event.originalTarget.defaultView;
			
			if (win.frameElement) {
				return;
			}

			var body = Firemind.content.document.body;
	
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

			// finally register an adapter to receive event messages
			Firemind.touchAPI.TouchSupport.register(
				Firemind.content, 
				adapter
			);
		}
	}

};
