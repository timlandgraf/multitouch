package de.fuberlin.mindmap2d.client;

import com.google.gwt.http.client.*;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.*;
import de.fuberlin.mindmap2d.client.model.*;

public class Suggestions{
	static private Graph model = null;
	
	static public void setGraphModel(Graph g){
		model = g;
	}
	
	static public void makeSuggestions(Bubble b){
		GWT.log("should make suggestions for: "+b.getText());
		/*int x = b.getX();
		int y = b.getY();
		model.createBubble("vorschlag 1", x - 50, y);
		model.createBubble("vorschlag 2", x, y +50);
		model.createBubble("vorschlag 3", x + 50, y);
		*/
	}
	
	
	static public void test(){
		GWT.log("test called");
		String url = "/girl.json";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		GWT.log("url: "+builder.getUrl());
		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
					public void onError(Request request, Throwable exception) {
						// Couldn't connect to server (could be timeout, SOP violation, etc.)
						GWT.log("error");
					}

					public void onResponseReceived(Request request, Response response) {
						if (200 == response.getStatusCode()) {
							GWT.log("got 200");
							String json_str = response.getText();
							JSONValue value = JSONParser.parse(json_str);
							JSONArray a = value.isObject().get("synonyms").isArray();
							for(int i=0; i< a.size(); i++){
								String s = a.get(i).isString().stringValue();
								GWT.log(i+": "+s);
							}
							
						} else {
							GWT.log("got error:"+response.getStatusCode());
							GWT.log("status text:"+response.getStatusText());
							GWT.log("text:"+response.getText());
							GWT.log("header:"+response.getHeadersAsString()); 
							// Handle the error.  Can get the status text from response.getStatusText()
						}
					}       
			});
		} catch (RequestException e) {
			GWT.log("s.th. went wrong:"+e);
			// Couldn't connect to server        
		}
	}
}