package de.fuberlin.mindmap2d.client.model;

public interface GraphChangeListener{
	public void bubbleAdded(Bubble bubble);
	public void bubbleRemoved(Bubble bubble);
	public void edgeAdded(Edge edge);
	public void edgeRemoved(Edge edge);
}
