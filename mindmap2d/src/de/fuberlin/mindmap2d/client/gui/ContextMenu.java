package de.fuberlin.mindmap2d.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ContextMenuEvent;

import de.fuberlin.mindmap2d.client.gui.BubbleView;
import de.fuberlin.mindmap2d.client.gui.UserInterface.Background;
import de.fuberlin.mindmap2d.client.model.ServerStoringProxy;
import de.fuberlin.mindmap2d.client.svg.Animatable;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.shape.Circle;
import de.fuberlin.mindmap2d.client.svg.shape.Text;

public class ContextMenu {
	abstract class ContextMenuButton extends InteractiveElement implements
			Animatable {
		protected ContextMenu menu;
		private Circle circle;

		public ContextMenuButton(int r, ContextMenu menu) {
			this.menu = menu;
			
			circle = new Circle(0, 0, r);
			group.add(circle);
			group.setStyleName("slidingMenuButton");
		}

		abstract public void onClick();

		@Override
		public void onContextMenu(ContextMenuEvent event) {
			event.preventDefault();
			super.onContextMenu(event);
		}

		protected void setPosition(int x, int y) {
			circle.setX(x);
			circle.setY(y);
		}

		public void setPropertyDouble(String property, double value) {
			if (property.equals("opacity"))
				group.setOpacity(value);

			circle.setPropertyDouble(property, value);
		}

		protected void setRadius(int r) {
			if (r >= 0)
				circle.setRadius(r);
		}

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
	}
	abstract class TextContextMenuButton extends ContextMenuButton{
		Text text;

		public void update(){}
		
		public TextContextMenuButton(int r, ContextMenu menu, String text) {
			super(r, menu);
			this.text = new Text(0, 0 + 5, text);
			this.text.setFillColor("black");
			this.text.setStrokeWidth(0);
			this.text.setTextAnchorMiddle();
			group.add(this.text);
		}
		
		@Override
		protected void setPosition(int x, int y) {
			text.setX(x);
			text.setY(y+5);
			super.setPosition(x, y);
		}
	}
	private static int calculateX(int radius, double angle) {
		return (int) (radius * Math.cos(angle));
	}
	private static int calculateY(int radius, double angle) {
		return (int) (radius * Math.sin(angle));
	}

	Group group = new Group();
	
	private List<ContextMenuButton> buttons;
	
	int radius, bubbleRadius;

	double angleFrom, angleTo;
	
	ContextMenu(final Background background, final int x, final int y, final int radius, final int bubbleRadius){
		this(x, y, radius, bubbleRadius);
		buttons = backgroundButtons();
		initButtons(x, y);
	}

	ContextMenu(final BubbleView bubble, final int x, final int y, final int radius, final int bubbleRadius){
		this(x, y, radius, bubbleRadius);
		buttons = bubbleButtons(bubble);
		initButtons(x, y);
	}

	// x = r * cos a
	// y = r * sin a
	ContextMenu(final int x, final int y, final int radius, final int bubbleRadius) {	
		if (radius < 50)
			this.radius = 50;
		else
			this.radius = radius;
		
		this.bubbleRadius = bubbleRadius;
		group.setStyleName("contextMenu");
		UserInterface.getUI().getCanvas().add(group);
	}

	private ArrayList<ContextMenuButton> backgroundButtons() {
		ArrayList<ContextMenuButton> buttons = new ArrayList<ContextMenuButton>();

		buttons.add(new TextContextMenuButton(bubbleRadius, this, "nothing") {
			
			@Override
			public void onClick() {
				GWT.log("Background");
			}
		});
		
		return buttons;
	}

	private ArrayList<ContextMenuButton> bubbleButtons(final BubbleView bubble) {
		ArrayList<ContextMenuButton> buttons = new ArrayList<ContextMenuButton>();

		buttons.add(new TextContextMenuButton(bubbleRadius, this, "add") {
			
			@Override
			public void onClick() {
				NewBubbleDialog d = new NewBubbleDialog(bubble);
				d.center(); //show
			}
		});
		
		buttons.add(new TextContextMenuButton(bubbleRadius, this, "edit") {

			@Override
			public void onClick() {
				EditBubbleDialog d = new EditBubbleDialog(bubble);
				d.center(); //show
			}
		});
		
		buttons.add(new TextContextMenuButton(bubbleRadius, this, "deattach") {
			
			@Override
			public void onClick() {
				GWT.log("deattach");
			}
		});
		
		buttons.add(new TextContextMenuButton(bubbleRadius, this, "open link") {
			
			@Override
			public void onClick() {
				GWT.log("open link");
			}
		});
		
		buttons.add(new TextContextMenuButton(bubbleRadius, this, "suggest") {
			
			@Override
			public void onClick() {
				SuggestionsDialog d = new SuggestionsDialog(bubble);
				d.center(); //show
			}
		});
		
		buttons.add(new TextContextMenuButton(bubbleRadius, this, "remove") {
			
			@Override
			public void onClick() {
				bubble.getModel().remove();
				ServerStoringProxy.removeBubble(bubble.getModel()); 
			}
		});
		
		return buttons;
	}
	
	private void initButtons(int x, int y) {
		angleFrom = 0.0;
		angleTo = 2 * Math.PI;

		double padding = (angleTo - angleFrom) / buttons.size();
		double angle = angleFrom;

		if (buttons.size() == 1)
			 radius = 0;
		
		int totalRadius = radius + bubbleRadius;
		DrawingArea canvas = UserInterface.getUI().getCanvas();
		
		if(x-totalRadius < 0)
			x = totalRadius;
		else if(x+totalRadius > canvas.getWidth())
			x = canvas.getWidth() - totalRadius;
		if(y-totalRadius < 0)
			y = totalRadius;
		else if(y+totalRadius > canvas.getHeight())
			y = canvas.getHeight() - totalRadius;
		
		group.setTranslation(x, y);
		
		for (ContextMenuButton cb : buttons) {
			cb.setPosition(calculateX(radius, angle), calculateY(radius,angle));
			cb.addThisTo(group);
			angle = angle + padding;
		}
	}

	public void remove() {
		UserInterface.getUI().getCanvas().remove(group);
	}
}