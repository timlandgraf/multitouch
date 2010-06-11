package de.fuberlin.mindmap2d.client.gui;

import java.util.ArrayList;
import java.util.List;

import de.fuberlin.mindmap2d.client.svg.Animatable;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.shape.Circle;
import de.fuberlin.mindmap2d.client.svg.shape.Text;

public class ContextMenu {
	Group group = new Group();
	private List<ContextMenuButton> buttons;
	int radius;
	double angleFrom, angleTo;

	// x = r * cos a
	// y = r * sin a
	ContextMenu(int x, int y, int radius, DrawingArea canvas) {
		group.setTranslation(x, y);
		// TODO: Generischer machen
		if (radius < 250)
			this.radius = 250;
		else
			this.radius = radius;

		// TODO: calculateAngle
		angleFrom = 0.0;
		angleTo = 2 * Math.PI;
		buttons = testButtons();
		initButtons();
	}

	private ArrayList<ContextMenuButton> testButtons() {
		ArrayList<ContextMenuButton> buttons = new ArrayList<ContextMenuButton>();

		buttons.add(new ContextMenuButton(0, 0, 40, this) {
			Text text;
			
			{
				text = new Text(0, 0 + 5, "HAHA");
				text.setFillColor("black");
				text.setStrokeWidth(0);
				text.getElement().setAttribute("text-anchor", "middle");
				group.add(text);
				group.setOpacity(0);
			}
			@Override
			public void onClick() {
				menu.remove();
			}

			@Override
			protected void setPosition(int x, int y) {
				text.setX(x);
				text.setY(y);
				super.setPosition(x, y);
			}

			@Override
			public void setPropertyDouble(String property, double value) {
				text.setPropertyDouble(property, value);
				super.setPropertyDouble(property, value);
			}
		});
		
		buttons.add(new ContextMenuButton(0, 0, 40, this) {
			Text text;
			
			{
				text = new Text(0, 0 + 5, "HAHA");
				text.setFillColor("black");
				text.setStrokeWidth(0);
				text.getElement().setAttribute("text-anchor", "middle");
				group.add(text);
				group.setOpacity(0);
			}
			@Override
			public void onClick() {
				menu.remove();
			}

			@Override
			protected void setPosition(int x, int y) {
				text.setX(x);
				text.setY(y);
				super.setPosition(x, y);
			}

			@Override
			public void setPropertyDouble(String property, double value) {
				text.setPropertyDouble(property, value);
				super.setPropertyDouble(property, value);
			}
		});
		
		return buttons;
	}

	private void initButtons() {
		double padding = (angleTo-angleFrom)/buttons.size();
		double angle = angleFrom;
		
		for (ContextMenuButton cb : buttons) {
			cb.setPosition(calculateX(radius,angle), calculateY(radius,angle));
			cb.addThisTo(group);
			angle = angle + padding;
		}
	}
	
	private static int calculateX(int radius, double angle){
		return (int) (radius * Math.cos(angle));
	}
	
	private static int calculateY(int radius, double angle){
		return (int) (radius * Math.sin(angle));
	}

	public void addThisTo(DrawingArea canvas) {
		canvas.add(group);
	}

	public void remove() {
		group.removeFromParent();
	}

	/*
	 * private Bubble parent_bubble;
	 * 
	 * private SVGButton btn1, btn2, btn3, btn4; private DrawingArea canvas;
	 * 
	 * //TODO: einen UIThing-Container erfinden public ContextMenu(Bubble
	 * parent_bubble){ this.parent_bubble = parent_bubble; }
	 * 
	 * public void addToCanvas(DrawingArea canvas){ int x = parent_bubble.x; int
	 * y = parent_bubble.y; btn1 = new SVGButton(x+80, y, "-"){ public void
	 * onClick(){ parent_bubble.suicide();
	 * //parent_bubble.setState(State.NORMAL); not necessary }};
	 * 
	 * btn2 = new SVGButton(x-80, y, "+"){ public void onClick(){ Bubble b = new
	 * Bubble(parent_bubble.x+80, parent_bubble.y+80, "NEW"); Edge e = new
	 * Edge(parent_bubble, b); b.addToCanvas(canvas); e.addToCanvas(canvas);
	 * parent_bubble.setState(State.NORMAL); }};
	 * 
	 * btn3 = new SVGButton(x, y+80, "set"){ public void onClick(){ (new
	 * NewBubbleDialog(parent_bubble)).show(); }};
	 * 
	 * btn4 = new SVGButton(x, y-80, "btn4"){ public void onClick(){
	 * GWT.log("btn4 clicked"); //parent_bubble.setState(State.NORMAL); }};
	 * 
	 * 
	 * btn1.addToCanvas(canvas); btn2.addToCanvas(canvas);
	 * btn3.addToCanvas(canvas); btn4.addToCanvas(canvas);
	 * 
	 * }
	 * 
	 * public void suicide(){ btn1.suicide(); btn2.suicide(); btn3.suicide();
	 * btn4.suicide();
	 * 
	 * }
	 */

	abstract class ContextMenuButton extends InteractiveElement implements
			Animatable {
		public ContextMenu menu;
		private Circle circle;

		public ContextMenuButton(int x, int y, int r, ContextMenu menu) {

			this.menu = menu;

			circle = new Circle(x, y, r);
			group.add(circle);
		}

		protected void setRadius(int r) {
			if (r >= 0)
				circle.setRadius(r);
		}

		protected void setPosition(int x, int y) {
			circle.setX(x);
			circle.setY(y);
			group.setStyleName("slidingMenuButton");
		}

		abstract public void onClick();

		void setState(State s) {
			this.state = s;
			switch (s) {
			case NORMAL:
				circle.setFillColor(Configurator.menuButtonColor);
				break;
			case HIGHLIGHTED:
				break;
			case MOUSEDOWN_1:
			case MOUSEDOWN_2:
			case MOVING:
			case ACTIVATED:
				onClick();
				setState(State.HIGHLIGHTED);
				break;
			}
		}

		public void setPropertyDouble(String property, double value) {
			// TODO: Funktioniert nur bis Opacity auf andere aktuallisiert wurde
			if (property.equals("opacity"))
				group.setOpacity(value);

			circle.setPropertyDouble(property, value);
		}
	}
}