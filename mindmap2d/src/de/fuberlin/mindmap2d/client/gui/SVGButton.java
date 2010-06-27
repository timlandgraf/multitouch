package de.fuberlin.mindmap2d.client.gui;

import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.shape.Circle;
import de.fuberlin.mindmap2d.client.svg.shape.Text;


public abstract class SVGButton extends InteractiveElement {

	
	private Circle circle;
	private Text text;
	private String text_str;
	private int x, y;
	
	public SVGButton(int x, int y, String text){
		this.text_str = text;
		this.x = x;
		this.y = y;
	}

	public void addToGroup(Group group){
		circle = new Circle(x, y, 20);
		circle.setFillColor("gray");
		group.add(circle);
		
		text = new Text(x,y, text_str);
		text.setFillColor("black");
		text.setStrokeWidth(0);
		group.add(text);
	}
	
	abstract public void onClick();
	
	void setPosition(int x, int y){ } // should never change
	void setState(State s){
		this.state = s;
		switch (s) {
			case NORMAL: circle.setFillColor("gray"); break;
			case HIGHLIGHTED: circle.setFillColor("green"); break;
			case MOUSEDOWN_1: break;
			case MOUSEDOWN_2: break;
			case MOVING: break;
			case ACTIVATED: onClick(); setState(State.HIGHLIGHTED); break; 
		}
	}
	
}