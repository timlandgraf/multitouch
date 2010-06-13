package de.fuberlin.mindmap2d.client;

import java.util.ArrayList;
import java.util.List;

import de.fuberlin.mindmap2d.client.model.Bubble;

public class Repulsion{
	private static List<Bubble> bubble_list = new ArrayList<Bubble>();
	
	public static void registerBubble(Bubble b){
		bubble_list.add(b);
		do_repulsion();
	}
	
	
	public static void unregisterBubble(Bubble b){
		bubble_list.remove(b);
		do_repulsion();
	}
	
	public static void do_repulsion(){
		//TODO: more efficient/fancy algo
		for(Bubble b1: bubble_list){
			for(Bubble b2: bubble_list){
				int dx = b1.getX() - b2.getX();
				int dy = b1.getY() - b2.getY();
				int l = dx*dx + dy*dy;
				//GWT.log("l: "+l);
				//GWT.log("change x:"+dx/10);
				//GWT.log("change y:"+dy/10);
				if(l < 10000){
					b1.setPosition(b1.getX()+dx/10, b1.getY()+dy/10);
				}
			}
		}
	}
}