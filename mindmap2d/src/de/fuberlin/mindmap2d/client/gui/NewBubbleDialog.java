package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 
import com.google.gwt.core.client.GWT;
import de.fuberlin.mindmap2d.client.gui.BubbleView;


public class NewBubbleDialog extends BubbleDialog {
	
	BubbleView neighbour;
	
	public NewBubbleDialog(BubbleView bubble) {
		this.neighbour = bubble;
		setText("New Bubble");
    }

	@Override
	public void onClick(ClickEvent event) {
		neighbour.getGraph().addBubbleTo(neighbour, text_box.getText());
		this.hide();
	}
}
