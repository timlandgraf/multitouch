package de.fuberlin.client;


public class Bubble{

	public int x, y;
	public String text;
	public Foam foam;
	
	public Bubble(int x, int y, String text){
		this.x = x;
		this.y = y;
		this.text = text;
		this.foam = null; //will be set by Foam.addBubble( )
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		foam.needs_repaint = true;
	}
}