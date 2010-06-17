package de.fuberlin.mindmap2d.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.user.client.DOM;

import de.fuberlin.mindmap2d.client.model.Graph;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.VectorObjectContainer;
import de.fuberlin.mindmap2d.client.svg.shape.Rectangle;
import de.fuberlin.mindmap2d.client.svg.shape.Text;

public class UserInterface {
	DrawingArea canvas;
	Background background;
	Text headline;
	SlidingMenu slidingMenu;
	GraphView graph;

	public UserInterface(DrawingArea canvas) {
		this.canvas = canvas;

		initBackground();
		initGraph();
		initHeadline();
		initSlidingMenu();
	}

	private void initBackground(){
		background = new Background(0, 0, canvas.getWidth(), canvas.getHeight());
		background.setFillColor(Configurator.backgroundColor);
		background.addThisTo(canvas);
	}

	private void initHeadline() {
		headline = new Text(canvas.getWidth() / 2, 40,
				"Mindmap2D feat. FireTouch");
		headline.setFillColor(Configurator.menuButtonColor);
		headline.setStrokeWidth(0);
		headline.setTextAnchorMiddle();
		headline.setOpacity(0.4);
		canvas.add(headline);
	}

	private void initSlidingMenu() {
		slidingMenu = new SlidingMenu();
		slidingMenu.addThisTo(canvas);
	}

	private void initGraph() {
		graph = new GraphView();
		
		graph.addThisTo(canvas);
		background.setGraphView(graph);
		
	
	}

	public void setGraphModel(Graph g){
		graph.setModel(g);
	}
	
	public void updateUI() {
		headline.setX(canvas.getWidth() / 2);
		background.setWidth(canvas.getWidth());
		background.setHeight(canvas.getHeight());
	}

	public class Background extends Rectangle implements MouseDownHandler,
			MouseUpHandler, MouseMoveHandler {
		
		private GraphView graph = null;
		private boolean isDown = false;
		private int x,y;

		public Background(int x, int y, int width, int height) {
			super(x, y, width, height);
		}
		
		public void setGraphView(GraphView graph){
			this.graph = graph;
		}
		
		public void addThisTo(VectorObjectContainer canvas){
			canvas.add(this);
			canvas.addMouseDownHandler(this);
			canvas.addMouseMoveHandler(this);
			canvas.addMouseUpHandler(this);
		}

		@Override
		public void onMouseDown(MouseDownEvent event) {
			event.stopPropagation();
			
			x = event.getClientX();
			y = event.getClientY();
			isDown = true;
			
			GWT.log("Background down");
		}

		@Override
		public void onMouseUp(MouseUpEvent event) {
			isDown = false;
			GWT.log("Background up");
			event.stopPropagation();
		}

		@Override
		public void onMouseMove(MouseMoveEvent event) {
			if(isDown){
				graph.move(event.getClientX()-x, event.getClientY()-y);
				x = event.getClientX();
				y = event.getClientY();
				event.stopPropagation();
			}
		}
	}
}
