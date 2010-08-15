package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 
import com.google.gwt.core.client.GWT;
import de.fuberlin.mindmap2d.client.gui.BubbleView;
import de.fuberlin.mindmap2d.client.model.Bubble;

import de.fuberlin.mindmap2d.client.model.ServerStoringProxy;

public class NewBubbleDialog extends BubbleDialog {

	BubbleView neighbour;

	public NewBubbleDialog(BubbleView bubble) {
		this.neighbour = bubble;
		setText("New Bubble");
	}

	@Override
	public void onClick(ClickEvent event) {  
		Bubble b = neighbour.getGraph().addBubbleTo(neighbour, text_area.getText().trim());
		b.setFontSize(getFontSize());
		b.setShape(getShape());
		
		this.hide();
		ServerStoringProxy.storeBubble(b); 
		GWT.log(b.getUuid()); 
	}
}
