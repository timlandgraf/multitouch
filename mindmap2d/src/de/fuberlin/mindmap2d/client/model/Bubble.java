package de.fuberlin.mindmap2d.client.model;

import java.util.*;


public class Bubble{

	private int x, y;
	private String text;
	private Graph graph;
	protected List<Edge> edges;
	private List<BubbleListener> listeners;
	
	
	public boolean position_fixed = false; //used by repulsion
	//==========================================================================
	protected Bubble(Graph g, String text, int x, int y){
		this.x = x;
		this.y = y;
		this.text = text;
		this.graph = g;
		edges = new ArrayList<Edge>();
		listeners = new ArrayList<BubbleListener>();
	}
	
	public String getText(){
		return(text);
	}
	
	public void addListener(BubbleListener l){
		if(!listeners.contains(l))
			listeners.add(l);
	}
	
	public void removeListener(BubbleListener l){
		listeners.remove(l);
	}
	
	public void setText(String t){
		text = t;
		propagateChange();
	}
	
	public int getX(){
		return(x);
	}
	
	public int getY(){
		return(y);
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		
		propagateChange();
	}
	
	public List<Edge> getEdges(){
		return(edges);
	}
	
	public void remove(){
		graph.unregisterBubble(this);
	}
	
	private void propagateChange(){
		for(BubbleListener bl:listeners)
			bl.bubbleChanged(this);
	}
	
}