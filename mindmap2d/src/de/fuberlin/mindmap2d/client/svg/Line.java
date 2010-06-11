package de.fuberlin.mindmap2d.client.svg;

import com.google.gwt.dom.client.Element;

/**
 * Line represents a straight line from one point to another. Line can be
 * stroked.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * @author flofreud
 */
public class Line extends VectorObject implements Strokeable, Animatable {

	/**
	 * Creates a new instance of Line. This is a line from one given point to
	 * another. Default stroke width is 1px and stroke color is black.
	 * 
	 * @param x1
	 *            the x-coordinate of the starting point in pixels
	 * @param y1
	 *            the y-coordinate of the starting point in pixels
	 * @param x2
	 *            the x-coordinate of the end point in pixels
	 * @param y2
	 *            the y-coordinate of the end point in pixels
	 */
	public Line(int x1, int y1, int x2, int y2) {
		setX1(x1);
		setY1(y1);
		setX2(x2);
		setY2(y2);
		setStrokeWidth(1);
		setStrokeOpacity(1);
		setStrokeColor("black");
	}

	@Override
	protected Element createElement() {
		return SVGDom.createSVGElementNS("line");
	}

	/**
	 * Returns the x-coordinate position of the starting point of Line.
	 * 
	 * @return the x-coordinate in pixels
	 */
	public int getX1() {
		return SVGDom.parseIntValue(getElement(), "x1", 0);
	}

	/**
	 * Sets the x-coordinate position of the starting point of Line.
	 * 
	 * @param x1
	 *            the new x-coordinate in pixels
	 */
	public void setX1(int x1) {
		SVGDom.setAttributeNS(getElement(), "x1", x1);
	}

	/**
	 * Returns the y-coordinate position of the starting point of Line.
	 * 
	 * @return the y-coordinate in pixels
	 */
	public int getY1() {
		return SVGDom.parseIntValue(getElement(), "y1", 0);
	}

	/**
	 * Sets the y-coordinate position of the starting point of Line.
	 * 
	 * @param y1
	 *            the new y-coordinate in pixels
	 */
	public void setY1(int y1) {
		SVGDom.setAttributeNS(getElement(), "y1", y1);
	}

	/**
	 * Returns the x-coordinate position of the ending point of Line.
	 * 
	 * @return the x-coordinate in pixels
	 */
	public int getX2() {
		return SVGDom.parseIntValue(getElement(), "x2", 0);
	}

	/**
	 * Sets the y-coordinate position of the ending point of Line.
	 * 
	 * @param x2
	 *            the new x-coordinate in pixels
	 */
	public void setX2(int x2) {
		SVGDom.setAttributeNS(getElement(), "x2", x2);
	}

	/**
	 * Returns the y-coordinate position of the ending point of Line.
	 * 
	 * @return the y-coordinate in pixels
	 */
	public int getY2() {
		return SVGDom.parseIntValue(getElement(), "y2", 0);
	}

	/**
	 * Sets the y-coordinate position of the ending point of Line.
	 * 
	 * @param y2
	 *            the new x-coordinate in pixels
	 */
	public void setY2(int y2) {
		SVGDom.setAttributeNS(getElement(), "y2", y2);
	}

	public String getStrokeColor() {
		return getElement().getAttribute("stroke");
	}

	public void setStrokeColor(String color) {
		SVGDom.setAttributeNS(getElement(), "stroke", color);
	}

	public int getStrokeWidth() {
		return SVGDom.parseIntValue(getElement(), "stroke-width", 0);
	}

	public void setStrokeWidth(int width) {
		SVGDom.setAttributeNS(getElement(), "stroke-width", width);
	}

	public double getStrokeOpacity() {
		return SVGDom.parseDoubleValue(getElement()
				.getAttribute("stroke-opacity"), 1);
	}

	public void setStrokeOpacity(double opacity) {
		SVGDom.setAttributeNS(getElement(), "stroke-opacity", "" + opacity);
	}

	//TODO:Opacity
	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("x1".equals(property)) {
			setX1((int) value);
		} else if ("y1".equals(property)) {
			setY1((int) value);
		} else if ("x2".equals(property)) {
			setX2((int) value);
		} else if ("y2".equals(property)) {
			setY2((int) value);
		} else if ("strokeopacity".equals(property)) {
			setStrokeOpacity(value);
		} else if ("strokewidth".equals(property)) {
			setStrokeWidth((int) value);
		} else if ("rotation".equals(property)) {
			setRotation((int) value);
		}
	}
}
