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
	
	public Foam(){
		bubbles = new Vector<Bubble>();
		needs_repaint = true;
	}
	
	public void addBubble(Bubble b){
		bubbles.add(b);
		needs_repaint = true;
	}
	
}