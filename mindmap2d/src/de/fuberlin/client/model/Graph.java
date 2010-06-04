package de.fuberlin.client.model;

import com.google.gwt.core.client.GWT;
import java.util.*;

/**
* The model of a Mind-Map.
*/
public class Graph {
	
	private List<Bubble> bubble_list;
	private List<Edge> edge_list;
	private List<GraphChangeListener> listeners;
	
	/**
	*	Construncs an empty Graph
	*/
	public Graph(){
		bubble_list = new ArrayList<Bubble>();
		edge_list = new ArrayList<Edge>();
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
	
	public void removeListerner(GraphChangeListener l){
		listeners.remove(l);
	}
	
	/**
	*	Official way to create a new Bubble.
	*/
	public Bubble createBubble(String t, int x, int y){
		Bubble b = new Bubble(this, t, x, y);
		bubble_list.add(b);
		
		for(GraphChangeListener l: listeners)
			l.bubbleAdded(b);
		
		return(b);
	}
	
		
	/**
	*	Official way to remove a Bubble.
	*/
	protected void unregisterBubble(Bubble b){
		bubble_list.remove(b);
		
		for(Edge e: b.getEdges())
			edge_list.remove(e);
		
		//for(GraphChangeListener l: listeners)
		//	l.bubbleRemoved(b);
	}
	
	
	/**
	*	Official way to create a new Edge.
	*/
	public Edge createEdge(Bubble b1, Bubble b2){
		Edge e = new Edge(b1, b2);
		edge_list.add(e);
		b1.edge_list.add(e);
		b2.edge_list.add(e);
		
		for(GraphChangeListener l: listeners)
			l.edgeAdded(e);
		
		return(e);
	}
	
	
	/**
	*	Official way to remove an Edge.
	*/
	public void removeEdge(Edge e){
		edge_list.remove(e);
		e.bubble_1.edge_list.remove(e);
		e.bubble_2.edge_list.remove(e);
		
		for(GraphChangeListener l: listeners)
			l.edgeRemoved(e);
	}
	
	
	/**
	*	Returns a list of all Bubbles which are currently contained in this Graph.
	*/
	public List<Bubble> getBubbles(){
		return(bubble_list);
	}
	
	
	/**
	*	Returns a list of all Edges which are currently contained in this Graph.
	*/
	public List<Edge> getEdges(){
		return(edge_list);
	}
	
}
