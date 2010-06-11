package de.fuberlin.mindmap2d.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.DOM;

import de.fuberlin.mindmap2d.client.Repulsion;
import de.fuberlin.mindmap2d.client.model.*;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.Line;
import de.fuberlin.mindmap2d.client.svg.SVGDom;
import de.fuberlin.mindmap2d.client.svg.VectorObject.TransfromValue;
import de.fuberlin.mindmap2d.client.svg.shape.Circle;
import de.fuberlin.mindmap2d.client.svg.shape.Text;

public class GraphView implements GraphChangeListener {
	private Graph model;
	private DrawingArea drawingArea;
	private Group canvas;
	private List<BubbleView> bubbles = new ArrayList<BubbleView>();
	private List<EdgeView> edges = new ArrayList<EdgeView>();

	public GraphView() {
		this.canvas = new Group();
		this.canvas.setStyleName("graph");
	}

	public void addThisTo(DrawingArea canvas){
		this.canvas.setTranslation(canvas.getWidth() / 2,
				canvas.getHeight() / 2);
		canvas.add(this.canvas);
		drawingArea = canvas;
	}
	
	public void move(int dx, int dy){
		TransfromValue value = canvas.getTranslation();
		canvas.setTranslation(value.x + dx, value.y + dy);
	}
	
	public void addBubbleTo(BubbleView oldBubble,String text){
		Bubble newBubble = model.createBubble(text, 0, 0);
		
		model.createEdge(oldBubble.model, newBubble);
	}
	
	public void setModel(Graph model) {
		if (model != null) {
			model.removeListerner(this);
			removeAll();
		}

		this.model = model;
		model.addListerner(this);

		for (Bubble b : model.getBubbles())
			bubbleAdded(b);

		for (Edge e : model.getEdges())
			edgeAdded(e);
	}

	public void removeAll() {
		for (BubbleView b : bubbles)
			b.remove();

		for (EdgeView e : edges)
			e.remove();

		bubbles.clear();
		edges.clear();
	}

	@Override
	public void bubbleAdded(Bubble bubble) {
		if (bubbles.contains(bubble))
			return;

		BubbleView bubbleView = new BubbleView(bubble, this);
		bubbles.add(bubbleView);
		bubbleView.addThisTo(canvas);
	}

	@Override
	public void bubbleRemoved(Bubble bubble) {
		BubbleView bv = getViewToBubble(bubble);

		bv.remove();
		bubbles.remove(bv);
	}

	public void edgeAdded(Edge edge) {
		BubbleView bubbleA = getViewToBubble(edge.bubbleA);
		BubbleView bubbleB = getViewToBubble(edge.bubbleB);
		if (bubbleA != null && bubbleB != null) {
			EdgeView ev = new EdgeView(bubbleA, bubbleB, edge);
			ev.addToThis(canvas);
		}
	}

	public void edgeRemoved(Edge edge) {
		EdgeView e = getViewToEdge(edge);
		if (e != null)
			e.remove();
	}

	/**
	 * Search the view to a bubble
	 * 
	 * @param bubble
	 * @return the view or null, if there is no view
	 */
	private BubbleView getViewToBubble(Bubble bubble) {
		for (BubbleView bv : bubbles)
			if (bv.model == bubble)
				return bv;

		return null;
	}

	/**
	 * Search the view to a bubble
	 * 
	 * @param bubble
	 * @return the view or null, if there is no view
	 */
	private EdgeView getViewToEdge(Edge edge) {
		for (EdgeView ev : edges)
			if (ev.model == edges)
				return ev;

		return null;
	}

	public class BubbleView extends InteractiveElement implements
			BubbleListener, DoubleClickHandler {

		public int x, y;
		public int width, height;

		private GraphView graphView;
		private Bubble model;
		private Circle circle;
		private Text text;
		private List<EdgeView> edgeList;

		public BubbleView(Bubble model, GraphView graphView) {
			this.model = model;
			this.graphView = graphView;
			edgeList = new ArrayList<EdgeView>();
			model.addListener(this);

			circle = new Circle(0, 0, 50);
			circle.setStrokeColor(Configurator.bubbleStrokeColor);
			circle.setStrokeWidth(3);
			group.add(circle);
			text = new Text(0, 0, "");
			text.setFillColor("black");
			text.setStrokeWidth(0);
			text.setTextAnchorMiddle();
			group.add(text);
			group.addDoubleClickHandler(this);

			update();
		}

		public void toFront() {
			graphView.canvas.pop(group);
		}

		public String getText() {
			return text.getText();
		}

		public void setText(String t) {
			model.setText(t);
		}

		public void addEdge(EdgeView e) {
			if (!edgeList.contains(e))
				edgeList.add(e);
		}

		protected void removeEdge(EdgeView e) {
			edgeList.remove(e);
		}

		public void setState(State s) {
			this.state = s;
			switch (s) {
			case NORMAL:
				circle.setFillColor(Configurator.bubbleNormalFillColor);
				break;
			case HIGHLIGHTED:
				circle.setFillColor(Configurator.bubbleHighlightedColor);
				break;
			case MOUSEDOWN_1: // fall through
			case MOUSEDOWN_2:
			case MOVING:
			case ACTIVATED:
				circle.setFillColor(Configurator.bubbleHighlightedColor);
				break;
			}
		}

		public void remove() {
			super.remove();
		}

		public void update() {
			setPosition(model.getX(), model.getY());
			text.setText(model.getText());
		}

		public void setPosition(int x, int y) {
			this.x = x;
			this.y = y;
			circle.setX(x);
			circle.setY(y);
			text.setX(x);
			text.setY(y + 5);

			for (EdgeView e : edgeList)
				e.update();
		}

		@Override
		public void bubbleChanged(Bubble b) {
			update();
		}
		
		@Override
		public void onMouseDown(MouseDownEvent event){
			super.onMouseDown(event);
			DOM.setCapture(group.getElement());
			event.stopPropagation();
		}

		@Override
		public void onMouseMove(MouseMoveEvent event) {
			super.onMouseMove(event);
			
			int id = SVGDom.suspendRedraw(drawingArea.getSVGElement());
			
			if (state == State.MOVING) {
				TransfromValue value = graphView.canvas.getTranslation();
				int x = event.getClientX() - (int) value.x;
				int y = event.getClientY() - (int) value.y;
				model.setPosition(x, y);
				Repulsion.do_repulsion();
				event.stopPropagation();
			}
			SVGDom.unsuspendRedraw(drawingArea.getSVGElement(),id);
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			super.onMouseUp(event);
			DOM.releaseCapture(group.getElement());
			event.stopPropagation();
		}

		@Override
		public void onDoubleClick(DoubleClickEvent event) {
			graphView.addBubbleTo(this, "Neue");
		}
	}

	public class EdgeView {
		private BubbleView bubbleA, bubbleB;
		private Edge model;
		private Group canvas;
		private Line line;

		public EdgeView(BubbleView bubbleA, BubbleView bubbleB, Edge model) {
			this.bubbleA = bubbleA;
			this.bubbleB = bubbleB;
			this.model = model;
			bubbleA.addEdge(this);
			bubbleB.addEdge(this);
		}

		public void addToThis(Group canvas) {
			this.canvas = canvas;
			line = new Line(0, 0, 0, 0);
			line.setStrokeColor(Configurator.edgeColor);
			update();
			canvas.add(line);
			bubbleA.toFront();
			bubbleB.toFront();
		}

		public void remove() {
			canvas.remove(line);
			bubbleA.removeEdge(this);
			bubbleB.removeEdge(this);
		}

		public void update() {
			line.setX1(bubbleA.x);
			line.setY1(bubbleA.y);
			line.setX2(bubbleB.x);
			line.setY2(bubbleB.y);
		}
	}
}
