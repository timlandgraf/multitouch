package de.fuberlin.client.model;

import java.util.*;
import java.util.*;

public class Bubble{

	private int x, y;
	private String text;
	private Graph graph;
	protected List<Edge> edge_list;
	private BubbleListener listener;
	//==========================================================================
	protected Bubble(Graph g, String text, int x, int y){
		this.x = x;
		this.y = y;
		this.text = text;
		this.graph = g;
		edge_list = new ArrayList<Edge>();
	}
	
	public String getText(){
		return(text);
	}
	
	public void setListener(BubbleListener l){
		if(listener != null)
			throw(new RuntimeException("allready a Listener registered"));
		listener = l;
	}
	
	public void setText(String t){
		text = t;
		listener.bubbleChanged(this);
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
		listener.bubbleChanged(this);
	}
	
	public List<Edge> getEdges(){
		return(edge_list);
	}
	
	public void remove(){
		listener.bubbleRemoved(this);
		//TODO: alle Edges bei den "anderen" seite abmelden.
		graph.unregisterBubble(this);
	}
	
}