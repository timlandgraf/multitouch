package de.fuberlin.mindmap2d.client.gui;

import java.util.ArrayList;
import java.util.List;

import de.fuberlin.mindmap2d.client.model.*;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.VectorObject.TransfromValue;

import com.google.gwt.user.client.Random;

public class GraphView implements GraphChangeListener {

	private Graph model;
	protected DrawingArea drawingArea;
	protected Group canvas;

	private List<BubbleView> bubbles = new ArrayList<BubbleView>();

	private List<EdgeView> edges = new ArrayList<EdgeView>();

	public GraphView() {
		this.canvas = new Group();
		this.canvas.setStyleName("graph");
	}
	
	public Bubble addBubbleTo(BubbleView oldBubble,String text){
		// new Bubble gets positioned randomly in the proximity of oldBubble
		double angle = Random.nextDouble()*2*Math.PI;
		int x = (int)Math.round( oldBubble.model.getX()+110*Math.sin(angle) );
		int y = (int)Math.round( oldBubble.model.getY()+110*Math.cos(angle) );
		Bubble newBubble = model.createBubble(text, x, y);
		model.createEdge(oldBubble.model, newBubble);
		return(newBubble);
	}

	public void addThisTo(DrawingArea canvas) {
		this.canvas.setTranslation(canvas.getWidth() / 2,
				canvas.getHeight() / 2);
		canvas.add(this.canvas);
		drawingArea = canvas;
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

	@Override
	public void edgeAdded(Edge edge) {
		BubbleView bubbleA = getViewToBubble(edge.bubbleA);
		BubbleView bubbleB = getViewToBubble(edge.bubbleB);
		if (bubbleA != null && bubbleB != null) {
			EdgeView ev = new EdgeView(bubbleA, bubbleB, edge);
			edges.add(ev);
			ev.addToThis(canvas);
		}
		// TODO: hässlich
		for (BubbleView bv : bubbles)
			bv.toFront();
	}

	@Override
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
			if (ev.model == edge)
				return ev;

		return null;
	}

	public void move(int dx, int dy) {
		TransfromValue value = canvas.getTranslation();
		canvas.setTranslation(value.x + dx, value.y + dy);
	}

	public void removeAll() {
		for (BubbleView b : bubbles)
			b.remove();

		for (EdgeView e : edges)
			e.remove();

		bubbles.clear();
		edges.clear();
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
}
