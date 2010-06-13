package de.fuberlin.mindmap2d.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;

import de.fuberlin.mindmap2d.client.gui.Configurator;
import de.fuberlin.mindmap2d.client.gui.GraphView;
import de.fuberlin.mindmap2d.client.gui.UserInterface;
import de.fuberlin.mindmap2d.client.model.Bubble;
import de.fuberlin.mindmap2d.client.model.Edge;
import de.fuberlin.mindmap2d.client.model.Graph;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.shape.Circle;
import de.fuberlin.mindmap2d.client.svg.shape.Rectangle;

public class MindMap2D implements EntryPoint {
	UserInterface ui;
	DrawingArea canvas;
	Rectangle background;

	public void onModuleLoad() {
		canvas = new DrawingArea(Window.getClientWidth(), Window
				.getClientHeight() - 5);
		RootPanel.get().add(canvas);
		
		ui = new UserInterface(canvas);

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				canvas.setHeight(event.getHeight() - 5);
				canvas.setWidth(event.getWidth());
				ui.updateUI();
			}
		});

		// muss noch mit Maik geklärt werden
		//registerMT(canvas.getElement());
	}
	
	private void myCallback() {
		Window.alert("callback");
	}

	private native void registerMT(Element svgroot) /*-{
		svgroot.onTouchDown = this.@de.fuberlin.mindmap2d.client.MindMap2D::myCallback();
		//$wnd.addListener("onTouchDown", svgroot);
		//addListener("onTouchDown", svgroot);0        
		alert("registerMT called");
		alert(svgroot.onTouchDown);
		alert(svgroot);
	}-*/;
}
