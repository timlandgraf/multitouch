package de.fuberlin.mindmap2d.client.svg;

import com.google.gwt.core.client.JavaScriptObject;

public final class SvgBbox extends JavaScriptObject {

	protected SvgBbox() {

	}

	public native int getX() /*-{
		return this.x;
	}-*/;

	public native int getY() /*-{
		return this.y;
	}-*/;

	public native int getWidth() /*-{
		return this.width;
	}-*/;

	public native int getHeight() /*-{
		return this.height;
	}-*/;
}
