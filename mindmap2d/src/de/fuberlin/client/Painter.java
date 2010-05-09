package de.fuberlin.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;


//import com.google.gwt.widgetideas.graphics.client.GWTCanvas;
import com.google.gwt.widgetideas.graphics.client.Color;

public class Painter extends Timer {

	private MyCanvas canvas;
	private Foam foam;
	
	public Painter(MyCanvas canvas, Foam foam){
		this.canvas = canvas;
		this.foam = foam;
	}
	
	public void run(){
		//GWT.log("woke up: ");
		if(!foam.needs_repaint)
			return;
		foam.needs_repaint = false;
		
		canvas.clear();
		
		canvas.setup_shadows();
		canvas.setup_text_style(); //needed for initial measureText( )
		
		for(Bubble b: foam.bubbles) {
			int text_width = canvas.measureText(b.text);
			canvas.setFillStyle(Color.BLUE);
			canvas.fillRect(b.x, b.y, text_width+6, 26);
			canvas.setup_text_style();
			canvas.fillText(b.text, b.x+3, b.y+20);
		}
	}
	
  
  
}