package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.ui.*; 
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;

import de.fuberlin.mindmap2d.client.model.BubbleShape;
import de.fuberlin.mindmap2d.client.gui.BubbleView;

public abstract class BubbleDialog extends DialogBox implements ClickHandler {
	TextArea text_area;
	ListBox lb_font_size, lb_shape;
	
	public BubbleDialog() {
		setAnimationEnabled(true); // Enable animation
		setGlassEnabled(true); // Enable glass background.
		setAutoHideEnabled(true); //should be automatically hidden when the user clicks outside of it
		setModal(true);
		//setTitle("foo title"); 
		
		Panel panel = new VerticalPanel();
		
		
		text_area = new TextArea();
		panel.add(text_area);
		
		
		
		panel.add(new Label("Font-Size:"));
		lb_font_size = new ListBox();
		lb_font_size.addItem("5");
		lb_font_size.addItem("10");
		lb_font_size.addItem("15");
		lb_font_size.addItem("20");
		lb_font_size.addItem("25");
		lb_font_size.setSelectedIndex(3);
		lb_font_size.setVisibleItemCount(1); // turns into a drop-down list
    	panel.add(lb_font_size);
    	
    	panel.add(new Label("Shape:"));
		lb_shape = new ListBox();
		for(BubbleShape bs: BubbleShape.values())
			lb_shape.addItem(bs.toString().toLowerCase());
		
		//lb_shape.setSelectedIndex(0);
		lb_shape.setVisibleItemCount(1); // turns into a drop-down list
    	panel.add(lb_shape);
		
		Button btn_ok = new Button("Ok");
		btn_ok.addClickHandler(this);
		panel.add(btn_ok);
		
		setWidget(panel);
		
		// timer since GWT will lose it again if we set it in-line here
		DeferredCommand.addCommand(new Command(){
			public void execute(){
				text_area.setFocus(true);
			}
		});

	}

    protected int getFontSize(){
    	return(Integer.parseInt(lb_font_size.getItemText(lb_font_size.getSelectedIndex())));
    }
    
    protected BubbleShape getShape(){
     return( BubbleShape.values()[lb_shape.getSelectedIndex()] );
    }
     
	@Override
	public abstract void onClick(ClickEvent event);
	
}
