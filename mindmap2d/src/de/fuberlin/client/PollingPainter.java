package de.fuberlin.client;

import com.google.gwt.user.client.Timer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;


//import com.google.gwt.widgetideas.graphics.client.GWTCanvas;
import com.google.gwt.widgetideas.graphics.client.Color;

public class PollingPainter extends Painter {

	private Foam foam;
	private boolean needs_repaint;
	private Timer timer;
	
	public PollingPainter(MyCanvas canvas){
		super(canvas);
		needs_repaint = true;
		timer = new Timer(){
			public void run(){
				GWT.log("wokeup");
				timer_wokeup();
			}
		};
		timer.scheduleRepeating(500);
		Window.alert("Polling");
	}
	
	private void timer_wokeup(){
		if(needs_repaint){
			needs_repaint = false;
			super.repaint(foam);
		}
	}
	
	public void repaint(Foam foam){
		this.foam = foam;
		needs_repaint = true;
	}
	
	
  
}