package de.fuberlin.mindmap2d.client.model;

import java.util.*;
import de.fuberlin.mindmap2d.client.gui.Configurator;
import de.fuberlin.mindmap2d.client.helper.*; 

public class Bubble{
	
	private int x, y;
	private String text;
	private Graph graph;
	protected List<Edge> edges;
	private List<BubbleListener> listeners;
	private BubbleShape shape;
	private int font_size;
	private String uuid; 
	
	public boolean position_fixed = false; //used by repulsion
	//==========================================================================
	protected Bubble(Graph g, String text, int x, int y){
		this.x = x;
		this.y = y;
		this.text = text;
		this.graph = g;
		//this.shape = BubbleShape.CIRCLE;
		this.shape = BubbleShape.RECTANGLE;
		//this.shape = BubbleShape.ELLIPSE;
		edges = new ArrayList<Edge>();
		listeners = new ArrayList<BubbleListener>();
		this.font_size = Configurator.bubbleDefaultFontSize;
		this.uuid = de.fuberlin.mindmap2d.client.helper.UUID.uuid(); 
		
		ServerStoringProxy.storeBubble(this); 
		
	}
	
	public void addListener(BubbleListener l){
		if(!listeners.contains(l))
			listeners.add(l);
	}
	
	
	public void setFontSize(int size){
		this.font_size = size;
		for(BubbleListener bl:listeners)
			bl.bubbleChanged(this);
	}
	
	public int getFontSize(){
		return(font_size);
	}
	
	public void setShape(BubbleShape shape){
		this.shape = shape;
		for(BubbleListener bl:listeners)
			bl.bubbleChanged(this);
	}
	
	public BubbleShape getShape(){
		return(shape);
	}
	
	public List<Edge> getEdges(){
		return(edges);
	}
	
	public String getText(){
		return(text);
	}
	
	public int getX(){
		return(x);
	}
	
	public int getY(){
		return(y);
	}
	
	public void remove(){
		graph.unregisterBubble(this);
	}
	
	public void removeListener(BubbleListener l){
		listeners.remove(l);
	}
	
	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		for(BubbleListener bl:listeners)
			bl.bubbleMoved(this);
	}
	
	public void setText(String t){
		text = t;
		for(BubbleListener bl:listeners)
			bl.bubbleChanged(this);
	}
	
}
