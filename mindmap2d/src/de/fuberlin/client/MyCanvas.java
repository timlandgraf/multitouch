package de.fuberlin.client;

import com.google.gwt.widgetideas.graphics.client.GWTCanvas;

public class MyCanvas extends GWTCanvas {
	
	public MyCanvas(int coordX, int coordY) {
		super(coordX, coordY);	
	}

	public native int measureText(String text)/*-{
	 	ctx = (this.@com.google.gwt.user.client.ui.UIObject::element).getContext('2d');
		return( ctx.measureText(text).width );
	}-*/;
	
	
	public native void fillText(String text, int x, int y)/*-{
		ctx = (this.@com.google.gwt.user.client.ui.UIObject::element).getContext('2d');
		ctx.fillText(text, x, y);
	}-*/;
	
	
	public  native void setup_shadows()/*-{
		ctx = (this.@com.google.gwt.user.client.ui.UIObject::element).getContext('2d');
  		ctx.shadowOffsetX = 2;
		ctx.shadowOffsetY = 2;
		ctx.shadowBlur = 2;
		ctx.shadowColor = "rgba(0, 0, 0, 0.5)";
	}-*/;
	
	
	public native void setup_text_style()/*-{
  		ctx = (this.@com.google.gwt.user.client.ui.UIObject::element).getContext('2d');
		ctx.font = "20px Times New Roman";
		ctx.fillStyle = "Black";
	}-*/;
  
}
