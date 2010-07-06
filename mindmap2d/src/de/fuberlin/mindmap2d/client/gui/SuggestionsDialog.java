package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 
import com.google.gwt.http.client.*;
import com.google.gwt.json.client.*;

//import de.fuberlin.mindmap2d.client.model.*;
import de.fuberlin.mindmap2d.client.gui.GraphView.BubbleView;



public class SuggestionsDialog extends DialogBox implements ChangeHandler {
	
	BubbleView bubble;
	ListBox list_box;
	
	public SuggestionsDialog(BubbleView bubble) {
		this.bubble = bubble;
		setText("Suggestions");
		setAnimationEnabled(true); 	// Enable animation.
		setGlassEnabled(true); // Enable glass background.
		setAutoHideEnabled(true); //should be automatically hidden when the user clicks outside of it
		setModal(true); 
		//setTitle("foo title");
		
		FlowPanel panel = new FlowPanel();
			
		list_box = new ListBox();
		list_box.setVisibleItemCount(10);

		list_box.addItem("Please Wait");
		
		panel.add(list_box);
		
    
		setWidget(panel);
		askServer(bubble.getText());
		
    }

	private void askServer(String txt){
		txt = txt.replace(" ", "_"); 
		String url = "/get_suggestions/"+txt+".json";
		RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, URL.encode(url));
		GWT.log("url: "+builder.getUrl());
		try {
			Request request = builder.sendRequest(null, new RequestCallback() {
					public void onError(Request request, Throwable exception) {
						signalServerError(exception.toString());
					}

					public void onResponseReceived(Request request, Response response) {
						if (200 == response.getStatusCode()) {
							GWT.log("got 200");
							String json_str = response.getText();
							JSONValue value = JSONParser.parse(json_str);
							JSONArray a = value.isObject().get("synonyms").isArray();
							
							list_box.clear();
							for(int i=0; i< a.size(); i++){
								String s = a.get(i).isString().stringValue();
								list_box.addItem(s);
							}
							list_box.addChangeHandler(SuggestionsDialog.this); // now make it clickable 
							
						} else {
							signalServerError(response.getStatusText());
							
							GWT.log("got error:"+response.getStatusCode());
							GWT.log("status text:"+response.getStatusText());
							GWT.log("text:"+response.getText());
							GWT.log("header:"+response.getHeadersAsString()); 
							// Handle the error.  Can get the status text from response.getStatusText()
						}
					}       
			});
		} catch (RequestException e) {
			signalServerError(e.toString());
		}
	}
	
	@Override
	public void onChange(ChangeEvent event){
		int i = list_box.getSelectedIndex();
		String v = list_box.getValue(i);
		bubble.getGraph().addBubbleTo(bubble, v);
		this.hide();
	}
	
	private void signalServerError(String txt){
		list_box.clear();
		list_box.addItem("Server Error");
		list_box.addItem(txt);
		GWT.log("Server-error:"+txt);
	}
}
