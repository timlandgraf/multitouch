package de.fuberlin.client.model;

public interface GraphChangeListener{
	public void bubbleAdded(Bubble b);
	public void bubbleRemoved(Bubble b);
	public void bubbleChanged(Bubble b);
	
	public void edgeAdded(Edge e);
	public void edgeRemoved(Edge e);
}