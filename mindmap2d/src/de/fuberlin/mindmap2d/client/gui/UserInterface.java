package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import de.fuberlin.mindmap2d.client.gui.BubbleView;
import de.fuberlin.mindmap2d.client.model.Graph;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.VectorObjectContainer;
import de.fuberlin.mindmap2d.client.svg.VectorObject.TransfromValue;
import de.fuberlin.mindmap2d.client.svg.shape.Rectangle;
import de.fuberlin.mindmap2d.client.svg.shape.Text;
import de.fuberlin.mindmap2d.client.touch.TouchService;
import de.fuberlin.mindmap2d.client.touch.events.TapGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.TapGestureHandler;

public class UserInterface {
	public class Background extends Rectangle implements MouseDownHandler,
			MouseUpHandler, MouseMoveHandler, ContextMenuHandler,
			TapGestureHandler {

		private GraphView graph = null;
		private State state = State.NORMAL;
		private int x, y;

		public Background(int x, int y, int width, int height) {
			super(x, y, width, height);
		}

		public void addThisTo(VectorObjectContainer canvas) {
			canvas.add(this);
			canvas.addMouseDownHandler(this);
			canvas.addMouseMoveHandler(this);
			canvas.addMouseUpHandler(this);
			canvas.addContextMenuHandler(this);
			this.addTapGestureHandler(this);
		}

		@Override
		public void onContextMenu(ContextMenuEvent event) {
			event.preventDefault();
			event.stopPropagation();
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			GWT.log("down");
			DOM.setCapture(canvas.getElement());
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
		public void onMouseMove(MouseMoveEvent event) {
			if (state == State.MOUSEDOWN) {
				graph.move(event.getClientX() - x, event.getClientY() - y);
				x = event.getClientX();
				y = event.getClientY();
				event.stopPropagation();
			}
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			DOM.releaseCapture(canvas.getElement());
			GWT.log("up");
			setState(State.NORMAL);
			event.stopPropagation();
		}

		public void setGraphView(GraphView graph) {
			this.graph = graph;
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
		public void onTap(TapGestureEvent event) {
			Window.alert("Background tapped! YEAH!! " + event.getClientX());
		}
	}

	enum State {
		NORMAL, MOUSEDOWN, RIGHTCLICK;
	}

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

		TouchService.initTouchService();

		initBackground();
		initGraph();
		initHeadline();
		initSlidingMenu();
		// new NewBubbleDialog().show();
	}

	public void closeContextMenu() {
		if (contextMenu != null) {
			contextMenu.remove();
			contextMenu = null;
		}
	}

	DrawingArea getCanvas() {
		return canvas;
	}

	private void initBackground() {
		background = new Background(0, 0, getCanvas().getWidth(), getCanvas()
				.getHeight());
		background.setFillColor(Configurator.backgroundColor);
		background.addThisTo(getCanvas());
	}

	private void initGraph() {
		graph = new GraphView();

		graph.addThisTo(getCanvas());
		background.setGraphView(graph);
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

	public void openContextMenu(Background background, int x, int y) {
		closeContextMenu();
		contextMenu = new ContextMenu(background, x, y, 120, 45);
	}

	public void openContextMenu(BubbleView bubble) {
		closeContextMenu();
		TransfromValue trans = bubble.getRealPosition();
		contextMenu = new ContextMenu(bubble, (int) trans.x, (int) trans.y,
				120, 45);
	}

	void setCanvas(DrawingArea canvas) {
		this.canvas = canvas;
	}

	public void setGraphModel(Graph g) {
		graph.setModel(g);
	};

	public void updateUI(ResizeEvent event) {
		getCanvas().setHeight(event.getHeight() - 5);
		getCanvas().setWidth(event.getWidth());
		headline.setX(getCanvas().getWidth() / 2);
		background.setWidth(getCanvas().getWidth());
		background.setHeight(getCanvas().getHeight());
	}
}
