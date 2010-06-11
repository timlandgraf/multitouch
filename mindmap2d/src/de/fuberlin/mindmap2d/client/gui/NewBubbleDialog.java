package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 

public class NewBubbleDialog extends DialogBox implements ClickHandler {
	public void onClick(ClickEvent event){}
	
	/*
	private Bubble parent_bubble;
	private TextBox tb;
	
	public NewBubbleDialog(Bubble parent_bubble) {
		this.parent_bubble = parent_bubble;
		
		setText("My First Dialog");
		
		// Enable animation.
		setAnimationEnabled(true);
		
		// Enable glass background.
		setGlassEnabled(true);
		FlowPanel panel = new FlowPanel();
		Button btn_ok = new Button("OK");
		btn_ok.addClickHandler(this);
		panel.add(btn_ok);
		
		tb = new TextBox();
		tb.setText(parent_bubble.getText());
		panel.add(tb);

		setWidget(panel);
    }
	
	public void onClick(ClickEvent event){
		parent_bubble.setText(tb.getText());
		parent_bubble.setState(UIThing.State.NORMAL);
		hide();
	}
	*/
}
