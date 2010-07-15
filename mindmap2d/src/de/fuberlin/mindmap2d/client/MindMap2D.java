package de.fuberlin.mindmap2d.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;

import de.fuberlin.mindmap2d.client.gui.UserInterface;
import de.fuberlin.mindmap2d.client.model.Bubble;
import de.fuberlin.mindmap2d.client.model.Graph;

public class MindMap2D implements EntryPoint {
	UserInterface ui;

	// disables the Same-Origin-Policy
	// Da wir unsern kram nicht signieren muss beim firefox inner config
	// signed.applets.codebase_principal_support=true gesetzt sein.
	private native void disableSOP() /*-{
		if (navigator.userAgent.indexOf("Firefox") != -1) {
			try {
				netscape.security.PrivilegeManager.enablePrivilege("UniversalBrowserRead");
			} 
			catch (e) {
				alert("Permission UniversalBrowserRead denied -- not running Mozilla?");
			}
		}
	}-*/;

	public void onModuleLoad() {

		ui = UserInterface.getUI();

		// disableSOP();
		Graph model = new Graph();

		new Repulsion(model);
		ui.setGraphModel(model);

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				ui.updateUI(event);
			}
		});

		// TODO: remove - just inserts demo-data
		Bubble b1 = model.createBubble("Start", 0, -100);
		Bubble b2 = model.createBubble("Test 1", -100, 0);
		Bubble b3 = model.createBubble("Test 2", +100, 00);
		model.createEdge(b1, b2);
		model.createEdge(b1, b3);
	}
}
