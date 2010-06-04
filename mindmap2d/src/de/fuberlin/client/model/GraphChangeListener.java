package de.fuberlin.client.model;

public interface GraphChangeListener{
	public void bubbleAdded(Bubble b);
	public void edgeAdded(Edge e);
	public void edgeRemoved(Edge e);
}
