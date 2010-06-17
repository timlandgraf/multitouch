package de.fuberlin.mindmap2d.client.gui;

import java.util.ArrayList;
import java.util.List;

import de.fuberlin.mindmap2d.client.svg.Animatable;
import de.fuberlin.mindmap2d.client.svg.Animate;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.shape.Circle;
import de.fuberlin.mindmap2d.client.svg.shape.Rectangle;
import de.fuberlin.mindmap2d.client.svg.shape.Text;

public class SlidingMenu {
	private Boolean out = false;
	private int x = 50;
	private int y = 50;
	private int r = 40;

	private List<SlidingMenuButton> buttons = new ArrayList<SlidingMenuButton>();
	private Rectangle border;
	private Group group = new Group();

	SlidingMenu() {
		border = new Rectangle(6, 6, 88, 88);
		border.setRoundedCorners(10);
		border.setFillColor("#000000");
		border.setFillOpacity(0.5);
		border.setStrokeOpacity(0.0);
		// reversed order
		buttons.add(button(""));
		buttons.add(button(""));
		buttons.add(buttonMenu());
		group.setStyleName("slidingMenu");
		group.add(border);
		for(SlidingMenuButton b:buttons)
			b.addThisTo(group);
	}

	private SlidingMenuButton buttonMenu() {
		return new SlidingMenuButton(x, y, r, this) {
			Text text;
			
			{
				text = new Text(x, y + 5, "Menu");
				text.setFillColor("black");
				text.setStrokeWidth(0);
				text.getElement().setAttribute("text-anchor", "middle");
				group.add(text);
			}
			@Override
			public void onClick() {
				menu.switchState();
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
		};
	}

	private SlidingMenuButton button(final String input) {
		return new SlidingMenuButton(x, y, r, this) {
			Text text;
			
			{
				text = new Text(x, y + 5, input);
				text.setFillColor("black");
				text.setStrokeWidth(0);
				text.getElement().setAttribute("text-anchor", "middle");
				group.add(text);
				group.setOpacity(0);
			}
			@Override
			public void onClick() {
				menu.switchState();
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
		};
	}

	public void switchState() {
		out = !out;
		int i = buttons.size() - 1;

		if (out) {
			new Animate(border, "width", x + 38, x + 38 + 85 * i, 500).start();
			for (SlidingMenuButton button : buttons) {
				new Animate(button, "x", x, x + 85 * i, 500).start();
				new Animate(button, "opacity", button.group.getOpacity(), 1.0, 500).start();
				i--;
			}
		} else {
			new Animate(border, "width", x + 38 + 85 * i, x + 38, 500).start();
			for (SlidingMenuButton button : buttons) {
				new Animate(button, "x", x + 85 * i, x, 500).start();
				if(i != 0) new Animate(button, "opacity", 1.0, 0.0, 500).start();
				else new Animate(button, "opacity", 1.0, 0.6, 500).start();
				i--;
			}
		}
	}

	public void addThisTo(DrawingArea canvas) {
		canvas.add(group);
	}

	public void remove() {
		border.removeFromParent();
		group.removeFromParent();
		for (SlidingMenuButton button : buttons)
			button.remove();
	}

	abstract class SlidingMenuButton extends InteractiveElement implements
			Animatable {
		public SlidingMenu menu;
		private Circle circle;

		public SlidingMenuButton(int x, int y, int r,
				SlidingMenu menu) {

			this.menu = menu;
			
			circle = new Circle(x, y, r);
			group.add(circle);
			group.setOpacity(0.6);
			group.deactivateContextMenu();
		}

		protected void setRadius(int r){
			if(r >= 0)
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
			case MOUSEDOWN:
			case MOVING:
				onClick();
				setState(State.HIGHLIGHTED);
				break;
			}
		}

		public void setPropertyDouble(String property, double value) {
			//TODO: Funktioniert nur bis Opacity auf andere aktuallisiert wurde
			if(property.equals("opacity"))
				group.setOpacity(value);
				
			circle.setPropertyDouble(property, value);
		}
	}
}
