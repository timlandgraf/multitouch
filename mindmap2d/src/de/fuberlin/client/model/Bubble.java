package de.fuberlin.client.model;

import java.util.*;
import java.util.*;

public class Bubble{

	private int x, y;
	private String text;
	private Graph graph;
	protected List<Edge> edge_list;
	
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
	
	public void setText(String t){
		text = t;
		for(GraphChangeListener l: graph.listeners)
			l.bubbleChanged(this);
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
		for(GraphChangeListener l: graph.listeners)
			l.bubbleChanged(this);
	}
	
	public List<Edge> getEdges(){
		return(edge_list);
	}
}