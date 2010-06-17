package de.fuberlin.mindmap2d.client;

import java.util.ArrayList;
import java.util.List;
import com.google.gwt.core.client.GWT;
import de.fuberlin.mindmap2d.client.model.*;

public class Repulsion implements GraphChangeListener, BubbleListener{
	private Graph model;
	private static final int MIN_DIST = 110;
	private static final int MIN_DIST_sqr = MIN_DIST*MIN_DIST;
	
	public Repulsion(Graph model){
		this.model = model;
		model.addListerner(this);
		for(Bubble b: model.getBubbles())
			b.addListener(this);
	}
		
	public void bubbleAdded(Bubble b){
		b.addListener(this);
		do_repulsion(b);
	}
	
	public void bubbleRemoved(Bubble b){
		b.removeListener(this);
	}
	
	public void bubbleChanged(Bubble b){
		do_repulsion(b);
	}
	
	
	private void do_repulsion(Bubble b){
		//GWT.log("Doing repulsion: "+b);
		if(b.position_fixed)
			return;
		
		b.position_fixed = true;
		
		for(Bubble b2: model.getBubbles()){
			if(b2.position_fixed)
				continue;
			int dx = b.getX() - b2.getX();
			int dy = b.getY() - b2.getY();
			double l_sqr = dx*dx + dy*dy;
			if(l_sqr < MIN_DIST_sqr){
				if(l_sqr==0){ //Special-Case: Bubbles laying on top of each other
					dy=10;
					l_sqr = 100;
				}
				double l = Math.sqrt(l_sqr);
				int rx = -(int)Math.round(MIN_DIST*dx/l);
				int ry = -(int)Math.round(MIN_DIST*dy/l);
				//GWT.log("l: "+l);
				//GWT.log("change x:"+rx);
				//GWT.log("change y:"+ry);
				b2.setPosition(b.getX()+rx, b.getY()+ry);
			}
		}
		b.position_fixed = false;
	}
	
	
	//not interessted in those events
	public void edgeAdded(Edge edge){}
	public void edgeRemoved(Edge edge){}
}