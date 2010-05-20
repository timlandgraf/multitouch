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

import com.google.gwt.user.client.Timer;

import com.google.gwt.widgetideas.graphics.client.GWTCanvas;
import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.shape.Circle;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MindMap2D implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
	  final DrawingArea canvas = new DrawingArea(400, 400);
	  RootPanel.get().add(canvas);
	  final Circle circle = new Circle(100, 100, 50);
	  circle.setFillColor("red");
	  canvas.add(circle);
	  
	  Timer t1 = new Timer(){
		public void run(){
			circle.setX(circle.getX()+5);
			if(circle.getX() > 300)
				canvas.remove(circle);
		}
	};
	
	t1.scheduleRepeating(100);
	
	  
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
  
	
}

