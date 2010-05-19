package de.fuberlin.client;

import java.util.Vector;
import java.util.List;

/** 

This Class Represents a MindMap. So basically its a list of all the Bubbles.
Unfortunately the classname MindMap was already used for the entire project
Therefore a bunch of Bubbles are just Foam :-)

*/

public class Foam{

	public Vector<Bubble> bubbles;
	public boolean needs_repaint;
	private Painter painter;
	
	public Foam(Painter painter){
		this.painter = painter;
		bubbles = new Vector<Bubble>();
	}
	
	public void addBubble(Bubble b){
		bubbles.add(b);
		b.foam = this;
		repaint();
	}
	
	public void repaint(){
		painter.repaint(this);	
	}
	
}