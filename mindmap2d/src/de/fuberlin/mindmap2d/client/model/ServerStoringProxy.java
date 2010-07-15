package de.fuberlin.mindmap2d.client.model;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.*;

//import de.fuberlin.mindmap2d.client.model.*;
import de.fuberlin.mindmap2d.client.gui.BubbleView;



public class ServerStoringProxy {
	
	
	public static void storeBubble(Bubble bubble){

		String url = "/bubbles/create";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, URL.encode(url));
		//builder.setRequestData(requestData)
		GWT.log("url: "+builder.getUrl());
		try {
			builder.setHeader("Content-Type", "application/json"); 
			//builder.setHeader("Accept", "application/json"); 
			Request request = builder.sendRequest(
					"{\"bubble\":{\"x\":4, \"y\":6, \"text\":\"" + bubble.getText() + "\"}}"
					, new RequestCallback() {
					public void onError(Request request, Throwable exception) {
//						signalServerError(exception.toString());
					}

					public void onResponseReceived(Request request, Response response) {
						if (200 == response.getStatusCode()) {
							GWT.log("got 200");
							 
							
						} else {
//							signalServerError(response.getStatusText());
							
							GWT.log("got error:"+response.getStatusCode());
							GWT.log("status text:"+response.getStatusText());
							GWT.log("text:"+response.getText());
							GWT.log("header:"+response.getHeadersAsString()); 
							// Handle the error.  Can get the status text from response.getStatusText()
						}
					}       
			});
		} catch (RequestException e) {
//			signalServerError(e.toString());
		}
	}
	
	
//	
//	@Override
//	public void onChange(ChangeEvent event){
//		int i = list_box.getSelectedIndex();
//		String v = list_box.getValue(i);
//		bubble.getGraph().addBubbleTo(bubble, v);
//		this.hide();
//	}
//	
//	private void signalServerError(String txt){
//		list_box.clear();
//		list_box.addItem("Server Error");
//		list_box.addItem(txt);
//		GWT.log("Server-error:"+txt);
//	}
}
