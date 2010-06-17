package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 

public class NewBubbleDialog extends DialogBox implements ClickHandler {
	
	public NewBubbleDialog() {
		
		setText("My First Dialog");
		
		// Enable animation.
		setAnimationEnabled(true);
		
		// Enable glass background.
		setGlassEnabled(true);
		FlowPanel panel = new FlowPanel();
		Button btn_ok = new Button("OK");
		btn_ok.addClickHandler(this);
		panel.add(btn_ok);
		
		TextBox tb = new TextBox();
		panel.add(tb);

		setWidget(panel);
    }

	@Override
	public void onClick(ClickEvent event) {
		// TODO Auto-generated method stub
		
	}
}
