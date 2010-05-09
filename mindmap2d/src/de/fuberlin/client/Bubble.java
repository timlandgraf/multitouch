package de.fuberlin.client;


public class Bubble{

	public int x, y;
	public String text;
	public Foam foam;
	public int width, height;
	public Bubble(int x, int y, String text){
		this.x = x;
		this.y = y;
		
		this.text = text;
		this.foam = null; //will be set by Foam.addBubble( )
		
		//this will be set by the painter - depents on text-length and font
		this.width = -1; 
		this.height = -1;
	}

	public void setPosition(int x, int y){
		this.x = x;
		this.y = y;
		foam.repaint();
	}
	
	public void setText(String text){
		this.text = text;
		width = -1;
		height = -1;
		foam.repaint();
	}
	
	public boolean overlaps(int px, int py){
		if(width==-1 || height==-1)
			return(false);
		return(x < px && px < x+width && y < py && py < y+height);
	}
	
	public String toString(){
		return("Bubble(\""+text+"\")");
	}
}