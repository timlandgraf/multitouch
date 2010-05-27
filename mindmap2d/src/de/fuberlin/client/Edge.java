package de.fuberlin.client;

import org.vaadin.gwtgraphics.client.*;
import org.vaadin.gwtgraphics.client.shape.*;
import com.google.gwt.event.dom.client.*;
import com.google.gwt.core.client.GWT;

public class Edge {
	
	private Bubble bubble_a, bubble_b;
	private DrawingArea canvas;
	private Line line;
	
	public Edge(Bubble bubble_a, Bubble bubble_b){
		this.bubble_a = bubble_a;
		this.bubble_b = bubble_b;
		bubble_a.addEdge(this);
		bubble_b.addEdge(this);
	}
	
	public void addToCanvas(DrawingArea canvas){
		this.canvas = canvas;
		line = new Line(0, 0, 0, 0);
		update();
		canvas.add(line);
		bubble_a.toFront();
		bubble_b.toFront();
	}
	
	
	public void suicide(){
		canvas.remove(line);
	}
	public void update(){
		line.setX1(bubble_a.x);
		line.setY1(bubble_a.y);
		line.setX2(bubble_b.x);
		line.setY2(bubble_b.y);
	}
}
