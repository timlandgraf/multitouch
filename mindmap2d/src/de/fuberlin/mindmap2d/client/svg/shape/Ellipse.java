package de.fuberlin.mindmap2d.client.svg.shape;

import com.google.gwt.dom.client.Element;

import de.fuberlin.mindmap2d.client.svg.SVGDom;
import de.fuberlin.mindmap2d.client.svg.Shape;


/**
 * Ellipse represents an ellipse.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * 
 */
public class Ellipse extends Shape {

	/**
	 * Creates a new Ellipse with the given position and radius properties.
	 * 
	 * @param x
	 *            the x-coordinate position of the center of the ellipse in
	 *            pixels
	 * @param y
	 *            the y-coordinate position of the center of the ellipse in
	 *            pixels
	 * @param radiusX
	 *            the x-axis radius of the ellipse in pixels
	 * @param radiusY
	 *            the y-axis radius of the ellipse in pixels
	 */
	public Ellipse(int x, int y, int radiusX, int radiusY) {
		setRadiusX(radiusX);
		setRadiusY(radiusY);
		setX(x);
		setY(y);
	}

	@Override
	protected Element createElement() {
		return SVGDom.createSVGElementNS("path");
	}
	
	/**
	 * Returns the x-axis radius of the ellipse in pixels.
	 * 
	 * @return the x-axis radius of the ellipse in pixels
	 */
	public int getRadiusX() {
		return SVGDom.parseIntValue(getElement(), "rx", 0);
	}

	/**
	 * Sets the x-axis radius of the ellipse in pixels.
	 * 
	 * @param radiusX
	 *            the x-axis radius of the ellipse in pixels
	 */
	public void setRadiusX(int radiusX) {
		SVGDom.setAttributeNS(getElement(), "rx", radiusX);
	}

	/**
	 * Returns the y-axis radius of the ellipse in pixels.
	 * 
	 * @return the y-axis radius of the ellipse in pixels
	 */
	public int getRadiusY() {
		return SVGDom.parseIntValue(getElement(), "ry", 0);
	}

	/**
	 * Sets the y-axis radius of the ellipse in pixels.
	 * 
	 * @param radiusY
	 *            the y-axis radius of the ellipse in pixels
	 */
	public void setRadiusY(int radiusY) {
		SVGDom.setAttributeNS(getElement(), "ry", radiusY);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.vaadin.gwtgraphics.client.Shape#setPropertyDouble(java.lang.String,
	 * double)
	 */
	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("radiusx".equals(property)) {
			setRadiusX((int) value);
		} else if ("radiusy".equals(property)) {
			setRadiusY((int) value);
		} else {
			super.setPropertyDouble(property, value);
		}
	}

	@Override
	public int getX() {
		return SVGDom.parseIntValue(getElement(), "cx", 0);
	}

	@Override
	public int getY() {
		return SVGDom.parseIntValue(getElement(), "cx", 0);
	}

	@Override
	public void setX(int x) {
		SVGDom.setAttributeNS(getElement(), "cx", x);
	}

	@Override
	public void setY(int y) {
		SVGDom.setAttributeNS(getElement(), "cy", y);
	}
}
