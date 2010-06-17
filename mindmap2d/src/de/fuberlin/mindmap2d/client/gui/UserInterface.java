package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import de.fuberlin.mindmap2d.client.gui.GraphView.BubbleView;
import de.fuberlin.mindmap2d.client.gui.InteractiveElement.State;
import de.fuberlin.mindmap2d.client.model.Graph;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.VectorObjectContainer;
import de.fuberlin.mindmap2d.client.svg.VectorObject.TransfromValue;
import de.fuberlin.mindmap2d.client.svg.shape.Rectangle;
import de.fuberlin.mindmap2d.client.svg.shape.Text;

public class UserInterface {
	private static UserInterface ui;

	public static UserInterface getUI() {
		if (ui == null)
			ui = new UserInterface();
		return ui;
	}

	private DrawingArea canvas;
	Background background;
	Text headline;
	SlidingMenu slidingMenu;
	GraphView graph;
	ContextMenu contextMenu;

	private UserInterface() {
		setCanvas(new DrawingArea(Window.getClientWidth(), Window
				.getClientHeight() - 5));
		RootPanel.get().add(getCanvas());

		initBackground();
		initGraph();
		initHeadline();
		initSlidingMenu();
		// new NewBubbleDialog().show();
	}

	private void initBackground() {
		background = new Background(0, 0, getCanvas().getWidth(), getCanvas()
				.getHeight());
		background.setFillColor(Configurator.backgroundColor);
		background.addThisTo(getCanvas());
	}

	private void initHeadline() {
		headline = new Text(getCanvas().getWidth() / 2, 40,
				"Mindmap2D feat. FireTouch");
		headline.setFillColor(Configurator.menuButtonColor);
		headline.setStrokeWidth(0);
		headline.setTextAnchorMiddle();
		headline.setOpacity(0.4);
		getCanvas().add(headline);
	}

	private void initSlidingMenu() {
		slidingMenu = new SlidingMenu();
		slidingMenu.addThisTo(getCanvas());
	}

	private void initGraph() {
		graph = new GraphView();

		graph.addThisTo(getCanvas());
		background.setGraphView(graph);

		graph.setModel(new Graph().testInit());
	}

	public void openContextMenu(BubbleView bubble) {
		closeContextMenu();
		TransfromValue trans = bubble.getRealPosition();
		contextMenu = new ContextMenu(bubble, (int) trans.x, (int) trans.y,
				120, 45);
	}

	public void openContextMenu(Background background, int x, int y) {
		closeContextMenu();
		contextMenu = new ContextMenu(background, x, y, 120, 45);
	}

	public void closeContextMenu() {
		if (contextMenu != null) {
			contextMenu.remove();
			contextMenu = null;
		}
	}

	public void updateUI(ResizeEvent event) {
		getCanvas().setHeight(event.getHeight() - 5);
		getCanvas().setWidth(event.getWidth());
		headline.setX(getCanvas().getWidth() / 2);
		background.setWidth(getCanvas().getWidth());
		background.setHeight(getCanvas().getHeight());
	}

	void setCanvas(DrawingArea canvas) {
		this.canvas = canvas;
	}

	DrawingArea getCanvas() {
		return canvas;
	}

	enum State {
		NORMAL, MOUSEDOWN, RIGHTCLICK;
	};

	public class Background extends Rectangle implements MouseDownHandler,
			MouseUpHandler, MouseMoveHandler {

		private GraphView graph = null;
		private State state = State.NORMAL;
		private int x, y;

		public Background(int x, int y, int width, int height) {
			super(x, y, width, height);
			this.deactivateContextMenu();
		}

		public void setGraphView(GraphView graph) {
			this.graph = graph;
		}

		public void addThisTo(VectorObjectContainer canvas) {
			canvas.add(this);
			canvas.addMouseDownHandler(this);
			canvas.addMouseMoveHandler(this);
			canvas.addMouseUpHandler(this);
		}

		private void setState(State s) {
			this.state = s;
			switch (s) {
			case NORMAL:
				break;
			case RIGHTCLICK:
				break;
			case MOUSEDOWN:
				break;
			}
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			GWT.log("down");
			if (event.getNativeButton() == NativeEvent.BUTTON_RIGHT) {
				setState(State.RIGHTCLICK);
				openContextMenu(this, event.getClientX(), event.getClientY());
			} else if (state == State.MOUSEDOWN) {
				setState(State.NORMAL);
			} else {
				x = event.getClientX();
				y = event.getClientY();
				closeContextMenu();
				setState(State.MOUSEDOWN);
			}
			event.stopPropagation();
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			GWT.log("up");
			setState(State.NORMAL);
			event.stopPropagation();
		}

		@Override
		public void onMouseMove(MouseMoveEvent event) {
			if (state == State.MOUSEDOWN) {
				GWT.log("move");
				graph.move(event.getClientX() - x, event.getClientY() - y);
				x = event.getClientX();
				y = event.getClientY();
				event.stopPropagation();
			}
		}
	}
}
