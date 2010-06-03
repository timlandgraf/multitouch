package de.fuberlin.client.gui;
import org.vaadin.gwtgraphics.client.*;
import de.fuberlin.client.model.*;

public class GraphView implements GraphChangeListener{
	private Graph model;
	private DrawingArea canvas;
	
	public GraphView(DrawingArea canvas){
		this.canvas = canvas;
	}
	
	public void setModel(Graph model){
		if(model != null){
			model.removeListerner(this);
			//TODO: alten kram weg räumen
		}
		
		this.model = model;
		model.addListerner(this);
		//TODO: den kram darstellen der schon im model drin ist.
	}
	
	
	public void bubbleAdded(Bubble b){}
	public void bubbleRemoved(Bubble b){}
	public void bubbleChanged(Bubble b){}
	public void edgeAdded(Edge e){}
	public void edgeRemoved(Edge e){}
}
