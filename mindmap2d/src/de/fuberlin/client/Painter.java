package de.fuberlin.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;


//import com.google.gwt.widgetideas.graphics.client.GWTCanvas;
import com.google.gwt.widgetideas.graphics.client.Color;

public class Painter{

	private MyCanvas canvas;
	
	public Painter(MyCanvas canvas){
		this.canvas = canvas;
	}
	
	
	public void repaint(Foam foam){
		canvas.clear();
		
		canvas.setup_shadows();
		canvas.setup_text_style(); //needed for initial measureText( )
		
		for(Bubble b: foam.bubbles){
			int text_width = canvas.measureText(b.text);
			b.width = text_width+6;
			b.height = 26;
			canvas.setFillStyle(Color.BLUE);
			canvas.fillRect(b.x, b.y, b.width, b.height);
			canvas.setup_text_style();
			canvas.fillText(b.text, b.x+3, b.y+20);
		}
		
	}
	
  
  
}