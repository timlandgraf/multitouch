package de.fuberlin.client.svg.util;

import com.google.gwt.core.client.JavaScriptObject;

public final class SVGBBox extends JavaScriptObject {

	protected SVGBBox() {

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
