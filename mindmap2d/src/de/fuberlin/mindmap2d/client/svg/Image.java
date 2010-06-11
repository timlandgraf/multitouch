package de.fuberlin.mindmap2d.client.svg;

import com.google.gwt.dom.client.Element;

/**
 * Image represents a raster image that can be embedded into DrawingArea.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * @author flofreud
 */
public class Image extends VectorObject implements Positionable, Animatable {

	/**
	 * Create a new Image with the given properties.
	 * 
	 * @param x
	 *            the x-coordinate position of the top-left corner of the image
	 *            in pixels
	 * @param y
	 *            the y-coordinate position of the top-left corner of the image
	 *            in pixels
	 * @param width
	 *            the width of the image in pixels
	 * @param height
	 *            the height of the image in pixels
	 * @param href
	 *            URL to an image to be shown.
	 */
	public Image(int x, int y, int width, int height, String href) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		setHref(href);
	}

	@Override
	protected Element createElement() {
		Element element = SVGDom.createSVGElementNS("image");
		element.setAttribute("preserveAspectRatio", "none");
		return element;
	}

	public int getX() {
		return SVGDom.parseIntValue(getElement(), "x", 0);
	}

	public void setX(int x) {
		SVGDom.setAttributeNS(getElement(), "x", x);
	}

	public int getY() {
		return SVGDom.parseIntValue(getElement(), "y", 0);
	}

	public void setY(int y) {
		SVGDom.setAttributeNS(getElement(), "y", y);
	}

	/**
	 * Returns the URL of the image currently shown.
	 * 
	 * @return URL of the image
	 */
	public String getHref() {
		return getElement().getAttribute("href");
	}

	/**
	 * Sets the URL of the image to be shown.
	 * 
	 * @param href
	 *            URL of the image to be shown
	 */
	public void setHref(String href) {
		SVGDom.setAttributeNS(SVGDom.XLINK_NS, getElement(), "href", href);
	}

	public int getWidth() {
		return SVGDom.parseIntValue(getElement(), "width", 0);
	}

	public void setWidth(int width) {
		SVGDom.setAttributeNS(getElement(), "width", width);
	}

	public int getHeight() {
		return SVGDom.parseIntValue(getElement(), "height", 0);
	}

	public void setHeight(int height) {
		SVGDom.setAttributeNS(getElement(), "height", height);
	}

	//TODO: Opacity
	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("x".equals(property)) {
			setX((int) value);
		} else if ("y".equals(property)) {
			setY((int) value);
		} else if ("width".equals(property)) {
			setWidth((int) value);
		} else if ("height".equals(property)) {
			setHeight((int) value);
		} else if ("rotation".equals(property)) {
			setRotation((int) value);
		}
	}
}