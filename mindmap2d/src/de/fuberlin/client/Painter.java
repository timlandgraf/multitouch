package de.fuberlin.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;


import com.google.gwt.widgetideas.graphics.client.GWTCanvas;
import com.google.gwt.widgetideas.graphics.client.Color;

public class Painter extends Timer {

	private GWTCanvas canvas;
	private Foam foam;
	
	public Painter(GWTCanvas canvas, Foam foam){
		this.canvas = canvas;
		this.foam = foam;
	}
	
	public void run(){
		//GWT.log("woke up: ");
		if(!foam.needs_repaint)
			return;
		foam.needs_repaint = false;
		
		canvas.clear();
		setup_shadows(canvas.getElement());
		setup_text_style(canvas.getElement()); //needed for initial measureText( )
		
		for(Bubble b: foam.bubbles) {
			int text_width = measureText(canvas.getElement(), b.text);
			canvas.setFillStyle(Color.BLUE);
			canvas.fillRect(b.x, b.y, text_width+6, 26);
			setup_text_style(canvas.getElement());
			fillText(canvas.getElement(), b.x+3, b.y+20, b.text);
		}
	}
	
	private static native int measureText(Element canvas, String text) /*-{
		ctx = canvas.getContext('2d');
		return( ctx.measureText(text).width );
	}-*/;
	
	
	private static native void fillText(Element canvas, int x, int y, String text)/*-{
  		ctx = canvas.getContext('2d');
		ctx.fillText(text, x, y);
    }-*/;
  
	private static native void setup_shadows(Element canvas)/*-{
  		ctx = canvas.getContext('2d');
		ctx.shadowOffsetX = 2;
		ctx.shadowOffsetY = 2;
		ctx.shadowBlur = 2;
		ctx.shadowColor = "rgba(0, 0, 0, 0.5)";
	}-*/;
	
	private static native void setup_text_style(Element canvas)/*-{
  		ctx = canvas.getContext('2d');
		ctx.font = "20px Times New Roman";
		ctx.fillStyle = "Black";
	}-*/;
  
  
}