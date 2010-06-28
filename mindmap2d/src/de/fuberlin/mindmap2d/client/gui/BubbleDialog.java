package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;


import de.fuberlin.mindmap2d.client.gui.GraphView.BubbleView;

public abstract class BubbleDialog extends DialogBox implements ClickHandler {
	TextBox text_box;
	
	public BubbleDialog() {
		setAnimationEnabled(true); // Enable animation
		setGlassEnabled(true); // Enable glass background.
		setAutoHideEnabled(true); //should be automatically hidden when the user clicks outside of it
		setModal(true);
		//setTitle("foo title"); 
		
		FlowPanel panel = new FlowPanel();
		
		text_box = new TextBox();
		panel.add(text_box);
		
		Button btn_ok = new Button("Ok");
		btn_ok.addClickHandler(this);
		panel.add(btn_ok);
		
		setWidget(panel);
		
		// timer since GWT will lose it again if we set it in-line here
		DeferredCommand.addCommand(new Command(){
			public void execute(){
				text_box.setFocus(true);
			}
		});

	}

    
	@Override
	public abstract void onClick(ClickEvent event);
	
}
