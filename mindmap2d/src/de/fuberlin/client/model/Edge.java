package de.fuberlin.client.model;

import com.google.gwt.core.client.GWT;

public class Edge {
	
	protected Bubble bubble_1, bubble_2;
	
	public Edge(Bubble b1, Bubble b2){
		this.bubble_1 = b1;
		this.bubble_2 = b2;
	}
}
