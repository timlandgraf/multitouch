package de.fuberlin.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.user.client.Timer;

import org.vaadin.gwtgraphics.client.*;
import org.vaadin.gwtgraphics.client.shape.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MindMap2D implements EntryPoint{

  /**
   * This is the entry point method.
   */
   
  
  public void onModuleLoad() {
	  DrawingArea canvas = new DrawingArea(800, 400);
	  canvas.setStyleName("canvas");
	  RootPanel.get().add(canvas);
	  Bubble b1 = new Bubble(10,10,"hallo 1 :-)");
	  b1.addToCanvas(canvas);
	  Bubble b2 = new Bubble(200,10,"hallo 2 :-)");
	  b2.addToCanvas(canvas);
	  Bubble b3 = new Bubble(10,200,"hallo 3 :-)");
	  b3.addToCanvas(canvas);
	  Bubble b4 = new Bubble(400,10,"hallo 4 :-)");
	  b4.addToCanvas(canvas);
	  Bubble b5 = new Bubble(10,400,"hallo 5 :-)");
	  b5.addToCanvas(canvas);
	  Bubble b6 = new Bubble(400,100,"hallo 6 :-)");
	  b6.addToCanvas(canvas);
	  Bubble b7 = new Bubble(200,400,"hallo 7 :-)");
	  b7.addToCanvas(canvas);
  }
  
  
  
  
	  /*addMouseMoveHandler(MouseMoveHandler handler)
	  Timer t1 = new Timer(){
		public void run(){
			circle.setX(circle.getX()+5);
			if(circle.getX() > 300)
				canvas.remove(circle);
		}
	};
	
	t1.scheduleRepeating(100);
	*/
	  
/*  	
	MyCanvas canvas = new MyCanvas(800, 400);
	RootPanel.get().add(canvas);
	canvas.setStyleName("canvas");
	Painter p = new Painter(canvas);
	//Painter p = new PollingPainter(canvas);
	
	Foam foam = new Foam(p);
	
	UserInputHandler uih = new UserInputHandler(foam);
	canvas.addMouseListener(uih);
	
	//adding some nodes for testing
	foam.addBubble(new Bubble(10,50, "some bubble"));
	foam.addBubble(new Bubble(200,180, "Hallo :-)"));
	foam.addBubble(new Bubble(600,190, "yet another one"));
	*/
	/*
	Timer t1 = new Timer(){
		public void run(){
			b.setPosition(b.x+5, b.y);
		}
	};
	
	t1.scheduleRepeating(100);
	
	Timer t2 = new Timer(){
		public void run(){
			canvas.removeMouseListener(uih);
			canvas.addMouseListener(uih);
		}
	};
	
	t2.scheduleRepeating(100);
	*/
  
  
	
}

//EOF