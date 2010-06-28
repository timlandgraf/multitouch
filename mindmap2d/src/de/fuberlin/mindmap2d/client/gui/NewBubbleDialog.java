package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 
import com.google.gwt.core.client.GWT;

import de.fuberlin.mindmap2d.client.gui.GraphView.BubbleView;

public class NewBubbleDialog extends DialogBox implements ClickHandler {
	
	BubbleView bubble;
	TextBox tb;
	
	public NewBubbleDialog(BubbleView bubble) {
		this.bubble = bubble;
		setText("Text Input");
		
		// Enable animation.
		setAnimationEnabled(true);
		// Enable glass background.
		setGlassEnabled(true);
		//should be automatically hidden when the user clicks outside of it 
		setAutoHideEnabled(true);
		setModal(true);
		//setTitle("foo title"); 
		
		FlowPanel panel = new FlowPanel();
		Button btn_ok = new Button("OK");
		btn_ok.addClickHandler(this);
		panel.add(btn_ok);
		
		tb = new TextBox();
		panel.add(tb);
		
		tb.setText(bubble.getText());
		
		setWidget(panel);
		
    }

	@Override
	public void onClick(ClickEvent event) {
		GWT.log("newBubDia: clicked");
		bubble.setText(tb.getText());
		this.hide();
	}
}
