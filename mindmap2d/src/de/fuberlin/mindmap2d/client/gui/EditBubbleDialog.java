package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 
import com.google.gwt.core.client.GWT;
import de.fuberlin.mindmap2d.client.gui.BubbleView;
import de.fuberlin.mindmap2d.client.model.BubbleShape;

public class EditBubbleDialog extends BubbleDialog {
	
	BubbleView bubble;
	
	public EditBubbleDialog(BubbleView bubble) {
		this.bubble = bubble;
		setText("Edit Bubble");
		text_area.setText(bubble.getText());
		
		for(int i=0; i<lb_font_size.getItemCount(); i++)
			if(lb_font_size.getItemText(i).equals(""+bubble.model.getFontSize()))
				lb_font_size.setSelectedIndex(i);
			
			
		lb_shape.setSelectedIndex(bubble.model.getShape().ordinal());
	
				
    }

	@Override
	public void onClick(ClickEvent event) {
		bubble.setText(text_area.getText().trim());
		bubble.model.setFontSize(getFontSize()); 
		bubble.model.setShape(getShape());
		this.hide();
	}
}
