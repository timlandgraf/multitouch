package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.ClickEvent;

public class EditBubbleDialog extends BubbleDialog {
	
	BubbleView bubble;
	
	public EditBubbleDialog(BubbleView bubble) {
		this.bubble = bubble;
		setText("Edit Bubble");
		text_box.setText(bubble.getText());
		
    }

	@Override
	public void onClick(ClickEvent event) {
		bubble.setText(text_box.getText());
		this.hide();
	}
}
