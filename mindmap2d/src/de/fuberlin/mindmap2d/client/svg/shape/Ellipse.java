package de.fuberlin.mindmap2d.client.svg.shape;

import com.google.gwt.dom.client.Element;

import de.fuberlin.mindmap2d.client.svg.SvgDom;
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
		return SvgDom.createSVGElementNS("path");
	}
	
	/**
	 * Returns the x-axis radius of the ellipse in pixels.
	 * 
	 * @return the x-axis radius of the ellipse in pixels
	 */
	public int getRadiusX() {
		return SvgDom.parseIntValue(getElement(), "rx", 0);
	}

	/**
	 * Returns the y-axis radius of the ellipse in pixels.
	 * 
	 * @return the y-axis radius of the ellipse in pixels
	 */
	public int getRadiusY() {
		return SvgDom.parseIntValue(getElement(), "ry", 0);
	}

	@Override
	public int getX() {
		return SvgDom.parseIntValue(getElement(), "cx", 0);
	}

	@Override
	public int getY() {
		return SvgDom.parseIntValue(getElement(), "cx", 0);
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

	/**
	 * Sets the x-axis radius of the ellipse in pixels.
	 * 
	 * @param radiusX
	 *            the x-axis radius of the ellipse in pixels
	 */
	public void setRadiusX(int radiusX) {
		SvgDom.setAttributeNS(getElement(), "rx", radiusX);
	}

	/**
	 * Sets the y-axis radius of the ellipse in pixels.
	 * 
	 * @param radiusY
	 *            the y-axis radius of the ellipse in pixels
	 */
	public void setRadiusY(int radiusY) {
		SvgDom.setAttributeNS(getElement(), "ry", radiusY);
	}

	@Override
	public void setX(int x) {
		SvgDom.setAttributeNS(getElement(), "cx", x);
	}

	@Override
	public void setY(int y) {
		SvgDom.setAttributeNS(getElement(), "cy", y);
	}
}
