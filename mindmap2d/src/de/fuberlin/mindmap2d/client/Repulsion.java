package de.fuberlin.mindmap2d.client;

import de.fuberlin.mindmap2d.client.model.*;
import com.google.gwt.core.client.GWT;


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
	
	public void bubbleChanged(Bubble b){
		do_repulsion(b);
	}
			
	public void bubbleMoved(Bubble b){
		do_repulsion(b);
	}
	
	public void bubbleRemoved(Bubble b){
		b.removeListener(this);
	}
	
	private void do_repulsion(Bubble b){
		if(b.position_fixed)
			return;
		b.position_fixed = true;
		for(Bubble b2: model.getBubbles()){
			if(!b2.position_fixed)
				process(b, b2);
		}
		b.position_fixed = false;
	}
	
	private void process(Bubble b1, Bubble b2){
		int b1_left_edge = b1.getX()-b1.bounding_width/2;
		int b2_left_edge = b2.getX()-b2.bounding_width/2;
		
		int b1_right_edge = b1.getX()+b1.bounding_width/2;
		int b2_right_edge = b2.getX()+b2.bounding_width/2;
		
		int b1_top_edge = b1.getY()-b1.bounding_height/2;
		int b2_top_edge = b2.getY()-b2.bounding_height/2;
		
		int b1_bottom_edge = b1.getY()+b1.bounding_height/2;
		int b2_bottom_edge = b2.getY()+b2.bounding_height/2;
		
		boolean overlaps_x = !( b1_right_edge < b2_left_edge || b2_right_edge < b1_left_edge );
		boolean overlaps_y = !( b1_top_edge > b2_bottom_edge || b2_top_edge > b1_bottom_edge );
		
		if(overlaps_x){
			if(Math.abs(b2_bottom_edge-b1_top_edge) < 5){
				b2.setPosition(b2.getX(), b2.getY() - 5);
			}else if(Math.abs(b1_bottom_edge-b2_top_edge) < 5){
				b2.setPosition(b2.getX(), b2.getY() + 5);
			}
		}
		
		if(overlaps_y){
			if(Math.abs(b2_left_edge-b1_right_edge) < 5){
				b2.setPosition(b2.getX()+5, b2.getY());
			}else if(Math.abs(b1_left_edge-b2_right_edge) < 5){
				b2.setPosition(b2.getX()-5, b2.getY());
			}
		}
	}
	
	private void do_repulsion_old(Bubble b){
		GWT.log(""+b.bounding_height);
		GWT.log(""+b.bounding_width);
		
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