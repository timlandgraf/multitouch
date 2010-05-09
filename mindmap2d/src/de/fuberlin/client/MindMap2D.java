package de.fuberlin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;


import com.google.gwt.user.client.ui.MouseListener;
import com.google.gwt.user.client.ui.Widget;

import com.google.gwt.widgetideas.graphics.client.GWTCanvas;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MindMap2D implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
  	//GWTCanvas canvas = new GWTCanvas(800, 400);
	MyCanvas canvas = new MyCanvas(800, 400);
	RootPanel.get().add(canvas);
	canvas.setStyleName("canvas");
	Foam foam = new Foam();
	
	//adding some nodes for testing
	foam.addBubble(new Bubble(10,50, "some bubble"));
	foam.addBubble(new Bubble(200,180, "Hallo :-)"));
	foam.addBubble(new Bubble(600,190, "yet another one"));
	
	canvas.addMouseListener(new MouseListener(){
			
			public void onMouseDown(Widget sender, int x, int y){}
			public void onMouseEnter(Widget sender){}
			public void onMouseLeave(Widget sender){}
            public void onMouseMove(Widget sender, int x, int y){GWT.log("Mouse moves");}
			public void onMouseUp(Widget sender, int x, int y){} 
			
	});
	
	Painter p = new Painter(canvas, foam);
	p.scheduleRepeating(500); 
	
  }
  
	
}

