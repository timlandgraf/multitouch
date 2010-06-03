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

import com.google.gwt.dom.client.*;

import de.fuberlin.client.*;
import de.fuberlin.client.model.*;
import de.fuberlin.client.gui.*;


/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MindMap2D implements EntryPoint{

   
  
   
	private void myCallback()
	{
		GWT.log("myCallback called");
	}
  
	private native void registerMT(Element svgroot) /*-{
		//svgroot.onTouchDown = @de.fuberlin.client.MindMap2D::myCallback();
		//$wnd.addListener("onTouchDown", svgroot);
		//addListener("onTouchDown", svgroot);
		alert($wnd);
	}-*/;
	

  public void onModuleLoad() {
	  DrawingArea canvas = new DrawingArea(800, 400);
	  canvas.setStyleName("canvas");
	  RootPanel.get().add(canvas);
	  
	  // muss noch mit Maik geklärt werden
	  //Element svgroot = (Element)canvas.getElement().getFirstChild();
	  //registerMT(svgroot);
	  
	  GraphView view = new GraphView(canvas);
	  Graph model = new Graph();
	  view.setModel(model);
	  
	  Bubble b1 = model.createBubble("Test 1", 100, 200);
	  Bubble b2 = model.createBubble("Test 2", 400, 100);
	  model.createEdge(b1, b2);
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