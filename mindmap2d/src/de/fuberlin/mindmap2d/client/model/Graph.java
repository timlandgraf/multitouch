package de.fuberlin.mindmap2d.client.model;

import java.util.*;


/**
* The model of a Mind-Map.
*/
public class Graph {
	
	private List<Bubble> bubbles;
	private List<Edge> edges;
	private List<GraphChangeListener> listeners;
	
	/**
	*	Construncs an empty Graph
	*/
	public Graph(){
		bubbles = new ArrayList<Bubble>();
		edges = new ArrayList<Edge>();
		listeners = new ArrayList<GraphChangeListener>();
	}
	
	/**
	*	Construncts a Graph from json-data
	*/
	public Graph(String json_data){
		//TODO:Implement
	}
	
	public void addListerner(GraphChangeListener l){
		listeners.add(l);
	}
	
	/**
	*	Official way to create a new Bubble.
	*/
	public Bubble createBubble(String t, int x, int y){
		Bubble b = new Bubble(this, t, x, y);
		bubbles.add(b);
		
		for(GraphChangeListener l: listeners)
			l.bubbleAdded(b);
		
		return(b);
	}
	
	/**
	*	Official way to create a new Edge.
	*/
	public Edge createEdge(Bubble b1, Bubble b2){
		Edge e = new Edge(b1, b2);
		edges.add(e);
		b1.edges.add(e);
		b2.edges.add(e);
		
		for(GraphChangeListener l: listeners)
			l.edgeAdded(e);
		
		return(e);
	}
	
		
	/**
	*	Returns a list of all Bubbles which are currently contained in this Graph.
	*/
	public List<Bubble> getBubbles(){
		return(bubbles);
	}
	
	
	/**
	*	Returns a list of all Edges which are currently contained in this Graph.
	*/
	public List<Edge> getEdges(){
		return(edges);
	}
	
	
	/**
	*	Official way to remove an Edge.
	*/
	public void removeEdge(Edge e){
		edges.remove(e);
		e.bubbleA.edges.remove(e);
		e.bubbleB.edges.remove(e);
		
		for(GraphChangeListener l: listeners)
			l.edgeRemoved(e);
	}
	
	
	public void removeListerner(GraphChangeListener l){
		listeners.remove(l);
	}
	
	
	/**
	*	Official way to remove a Bubble.
	*/
	protected void unregisterBubble(Bubble b){
		ArrayList<Edge> toRemove = new ArrayList<Edge>();
		for(Edge e: b.getEdges())
			toRemove.add(e);
		
		for(Edge e: toRemove)
			removeEdge(e);
		
		bubbles.remove(b);
		
		for(GraphChangeListener l:listeners)
			l.bubbleRemoved(b);
	}

}
