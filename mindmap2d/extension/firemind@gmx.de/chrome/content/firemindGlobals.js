var Cc = Components.classes;
var Ci = Components.interfaces;
var Cr = Components.results;
var Cu = Components.utils;

if(typeof Firemind == "undefined" || !Firemind || Firemind == null){
	var Firemind = {};
}

Firemind.namespace = function(param){
	var ns = null, subspaces = [];

	//force arguments as strings
	subspaces = ("" + param).split('.');
	ns = Firemind;

	//append cascade of objects
	for(var i = 0, l = subspaces.length ;i < l; i++){
		var entry = ns[subspaces[i]];
		if(typeof entry == "undefined" || !entry || entry == null){
			ns[subspaces[i]] = {};
		}
		ns = ns[subspaces[i]];
	}
	
	return ns;
};

Firemind.reportError = function(error, msg){
	try {
		alert(
			(msg ? (msg + "\n\n") : "") + 
			"message: " + error.message + "\n" +
			"fileName: " + error.fileName + "\n" + 
			"lineNumber: " + error.lineNumber + "\n" + 
			"stack: " + error.stack
		);
	} catch(e){
		alert("ERROR: Can't execute exception report (Firemind.reportError)!");
	}
};

Firemind.log = function(msg){
	var console = Cc["@mozilla.org/consoleservice;1"].getService(Ci.nsIConsoleService);
	console.logStringMessage(msg);
};

Firemind.include = function(name){
	var HEADER = Cc["@mozilla.org/moz/jssubscript-loader;1"].getService(Ci.mozIJSSubScriptLoader);
	try {
		HEADER.loadSubScript("chrome://firemind/content/" + name);
	} catch(e){
		Firemind.reportError(e, "ERROR: Can't import " + name + " file (Firemind.include)!");
	}
};

Firemind.prefs = function(){
	return Cc["@mozilla.org/preferences-service;1"].getService(Ci.nsIPrefBranch);
};
