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
import de.fuberlin.mindmap2d.client.svg.SvgDom;
import de.fuberlin.mindmap2d.client.svg.VectorObject.TransfromValue;
import de.fuberlin.mindmap2d.client.svg.Shape;
import de.fuberlin.mindmap2d.client.svg.shape.*;

import de.fuberlin.mindmap2d.client.touch.events.MoveGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.MoveGestureHandler;

import com.google.gwt.core.client.GWT;

public class BubbleView extends InteractiveElement implements BubbleListener,
		DoubleClickHandler, MoveGestureHandler {

	public int x, y;

	private GraphView graphView;
	protected Bubble model;
	private Shape shape;
	private Text text;
	private List<EdgeView> edgeList;

	public BubbleView(Bubble model, GraphView graphView) {
		this.model = model;
		this.graphView = graphView;
		edgeList = new ArrayList<EdgeView>();
		model.addListener(this);

		if (model.getShape() == BubbleShape.CIRCLE)
			shape = new Circle(0, 0, 50);
		else if (model.getShape() == BubbleShape.RECTANGLE)
			shape = new Rectangle(0, 0, 70, 45);
		else
			throw (new RuntimeException("Shape not supported" + shape));

		shape.setStrokeColor(Configurator.bubbleStrokeColor);
		shape.setStrokeWidth(3);
		group.add(shape);
		text = new Text(0, 0, "");
		text.setFillColor("black");
		text.setStrokeWidth(0);
		text.setTextAnchorMiddle();
		group.add(text);
		group.addDoubleClickHandler(this);
		group.addMoveGestureHandler(this);

		// update(); - now gets called by InteractivElement.addThisTo(..)
	}

	public void addEdge(EdgeView e) {
		if (!edgeList.contains(e))
			edgeList.add(e);
	}

	@Override
	public void bubbleChanged(Bubble b) {
		update();
	}

	public GraphView getGraph() {
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
		return text.getText();
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
		if (shape instanceof Circle) {
			shape.setX(x);
			shape.setY(y);
		} else if (shape instanceof Rectangle) {
			Rectangle r = (Rectangle) shape;
			shape.setX(x - r.getWidth() / 2);
			shape.setY(y - r.getHeight() / 2);
		} else
			throw (new RuntimeException("Unsupported shape"));

		text.setX(x);
		text.setY(y + 5);

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
		setPosition(model.getX(), model.getY());
		text.setText(model.getText());
		GWT.log("text-legnth: " + text.getTextLength());
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