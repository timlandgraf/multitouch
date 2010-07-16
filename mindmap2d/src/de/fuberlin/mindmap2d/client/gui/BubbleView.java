package de.fuberlin.mindmap2d.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.DOM;

import de.fuberlin.mindmap2d.client.model.*;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.Line;
import de.fuberlin.mindmap2d.client.svg.SvgDom;
import de.fuberlin.mindmap2d.client.svg.VectorObject.TransfromValue;
import de.fuberlin.mindmap2d.client.svg.Shape;
import de.fuberlin.mindmap2d.client.svg.shape.*;

import de.fuberlin.mindmap2d.client.touch.events.MoveGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.MoveGestureHandler;

import com.google.gwt.user.client.Random;
import com.google.gwt.core.client.GWT;



public class BubbleView extends InteractiveElement implements
		BubbleListener, DoubleClickHandler, MoveGestureHandler {

	public int x, y;

	private GraphView graphView;
	protected Bubble model;
	private Shape shape;
	private List<Text> textList;
	private List<EdgeView> edgeList;
	private int bounding_width, bounding_height;
	
	public BubbleView(Bubble model, GraphView graphView) {
		this.model = model;
		this.graphView = graphView;
		edgeList = new ArrayList<EdgeView>();
		model.addListener(this);
		
		
		group.addDoubleClickHandler(this);
		group.addMoveGestureHandler(this);
		
		textList = new ArrayList<Text>();
		
		//update(); - now gets called by InteractivElement.addThisTo(..)
	}

	public void addEdge(EdgeView e) {
		if (!edgeList.contains(e))
			edgeList.add(e);
	}

	@Override
	public void bubbleChanged(Bubble b) {
		update();
	}

	@Override
	public void bubbleMoved(Bubble b) {
		setPosition(model.getX(), model.getY());
	}
	
	public GraphView getGraph(){
		return graphView;
	}

	public Bubble getModel() {
		return model;
	}

	public TransfromValue getRealPosition() {
		TransfromValue trans = graphView.canvas.getTranslation();
		trans.x += this.x;
		trans.y += this.y;
		return trans;
	}

	public String getText() {
		return model.getText();
	}

	public String getUUID() {
		return model.getUUID();
	}

	@Override
	public void onContextMenu(ContextMenuEvent event) {
		event.preventDefault();
		super.onContextMenu(event);
	}

	@Override
	public void onDoubleClick(DoubleClickEvent event) {
		NewBubbleDialog d = new NewBubbleDialog(this);
		d.center(); // show
	}

	@Override
	public void onMouseDown(MouseDownEvent event) {
		if (event.getNativeButton() == NativeEvent.BUTTON_RIGHT) {
			UserInterface.getUI().openContextMenu(this);
			event.stopPropagation();
			return;
		} else {
			DOM.setCapture(group.getElement());
		}
		super.onMouseDown(event);
	}

	@Override
	public void onMouseMove(MouseMoveEvent event) {
		super.onMouseMove(event);

		int id = SvgDom.suspendRedraw(graphView.drawingArea.getSVGElement());

		if (state == State.MOVING) {
			TransfromValue value = graphView.canvas.getTranslation();
			int x = event.getClientX() - (int) value.x;
			int y = event.getClientY() - (int) value.y;
			model.setPosition(x, y);
		}
		SvgDom.unsuspendRedraw(graphView.drawingArea.getSVGElement(), id);
	}

	@Override
	public void onMouseUp(MouseUpEvent event) {
		super.onMouseUp(event);
		DOM.releaseCapture(group.getElement());
	}

	public void remove() {
		super.remove();
	}

	protected void removeEdge(EdgeView e) {
		edgeList.remove(e);
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
		if(shape instanceof Circle || shape instanceof Ellipse){
			shape.setX(x);
			shape.setY(y);
		}else if(shape instanceof Rectangle){
			Rectangle r = (Rectangle)shape;
			shape.setX(x-r.getWidth()/2);
			shape.setY(y-r.getHeight()/2);
		}else
			throw(new RuntimeException("Unsupported shape"));
			
		for(int i=0; i<textList.size(); i++){
			Text t = textList.get(i);
			t.setX(x);
			t.setY((int)(y - bounding_height/2 + (i+1)*model.getFontSize()));
		}
		
		for (EdgeView e : edgeList)
			e.update();
	}

	public void setState(State s) {
		this.state = s;
		switch (s) {
		case NORMAL:
			shape.setFillColor(Configurator.bubbleNormalFillColor);
			break;
		case HIGHLIGHTED:
			shape.setFillColor(Configurator.bubbleHighlightedColor);
			break;
		case MOUSEDOWN:
		case MOVING:
			shape.setFillColor(Configurator.bubbleHighlightedColor);
			break;
		}
	}

	public void setText(String t) {
		model.setText(t);
	}

	public void toFront() {
		graphView.canvas.pop(group);
	}

	public void update() {
		//taking care of the text
		//needs to be done first to determine bounding-rect
		for(Text t: textList)
			group.remove(t);
		textList.clear();
		
		int max_text_width = 0;
		String[] lines = model.getText().split("\n");
		for(String line: lines){
			Text t = new Text(0, 0, line);
			textList.add(t);
			t.setFillColor("black");
			t.setStrokeWidth(0);
			t.setFontSize(model.getFontSize());
			t.setTextAnchorMiddle();
			group.add(t);
			max_text_width = Math.max(max_text_width, t.getTextLength());
		}
		
		
		//taking care of the shape
		if(shape != null)
			group.remove(shape);
					 
		bounding_width = (int)(max_text_width+0.5*model.getFontSize());
		bounding_height = (int)((lines.length+0.5)*model.getFontSize());
		
		
		if(model.getShape() == BubbleShape.CIRCLE){
			int r = (int)Math.sqrt(bounding_width*bounding_width/4 + bounding_height*bounding_height/4); 
			shape = new Circle(0, 0, r);
			model.bounding_width = 2*r;
			model.bounding_height = 2*r;
		}else if(model.getShape() == BubbleShape.ELLIPSE){
			float sqrt2 = 1.4142135f; // sqrt(2)
			int w = (int)(sqrt2*bounding_width/2 + 0.25*model.getFontSize());
			int h = (int)(sqrt2*bounding_height/2 + 0.25*model.getFontSize());
			shape = new Ellipse(0, 0, w, h);
			model.bounding_width = 2*w;
			model.bounding_height = 2*h;
		}else if(model.getShape() == BubbleShape.RECTANGLE){
			shape = new Rectangle(0, 0, bounding_width, bounding_height);
			model.bounding_width = bounding_width;
			model.bounding_height = bounding_height;
		}else
			throw(new RuntimeException("Shape not supported"+shape));
		
		shape.setFillColor(Configurator.bubbleNormalFillColor);
		shape.setStrokeColor(Configurator.bubbleStrokeColor);
		shape.setStrokeWidth(1+ (int)model.getFontSize()/10);
		group.add(shape);
		
		for(Text t: textList)
			group.pop(t);
		
		setPosition(model.getX(), model.getY());
	}

	@Override
	public void onMoveTouch(MoveGestureEvent event) {
		int id = SvgDom.suspendRedraw(graphView.drawingArea.getSVGElement());

		TransfromValue value = graphView.canvas.getTranslation();
		int x = event.getClientX() - (int) value.x;
		int y = event.getClientY() - (int) value.y;
		model.setPosition(x, y);

		SvgDom.unsuspendRedraw(graphView.drawingArea.getSVGElement(), id);
	}
}