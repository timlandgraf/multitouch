package de.fuberlin.mindmap2d.client.model;


public class Edge {
	
	public Bubble bubbleA, bubbleB;
	
	public Edge(Bubble b1, Bubble b2){
		this.bubbleA = b1;
		this.bubbleB = b2;
	}
}
