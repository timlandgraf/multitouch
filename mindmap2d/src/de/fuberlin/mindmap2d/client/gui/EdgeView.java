package de.fuberlin.mindmap2d.client.gui;

import de.fuberlin.mindmap2d.client.model.*;
import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.Line;

public class EdgeView {
	private BubbleView bubbleA, bubbleB;
	protected Edge model;
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