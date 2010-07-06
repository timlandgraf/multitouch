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
		return SvgDom.createSVGElementNS("line");
	}

	public String getStrokeColor() {
		return getElement().getAttribute("stroke");
	}

	public double getStrokeOpacity() {
		return SvgDom.parseDoubleValue(getElement()
				.getAttribute("stroke-opacity"), 1);
	}

	public int getStrokeWidth() {
		return SvgDom.parseIntValue(getElement(), "stroke-width", 0);
	}

	/**
	 * Returns the x-coordinate position of the starting point of Line.
	 * 
	 * @return the x-coordinate in pixels
	 */
	public int getX1() {
		return SvgDom.parseIntValue(getElement(), "x1", 0);
	}

	/**
	 * Returns the x-coordinate position of the ending point of Line.
	 * 
	 * @return the x-coordinate in pixels
	 */
	public int getX2() {
		return SvgDom.parseIntValue(getElement(), "x2", 0);
	}

	/**
	 * Returns the y-coordinate position of the starting point of Line.
	 * 
	 * @return the y-coordinate in pixels
	 */
	public int getY1() {
		return SvgDom.parseIntValue(getElement(), "y1", 0);
	}

	/**
	 * Returns the y-coordinate position of the ending point of Line.
	 * 
	 * @return the y-coordinate in pixels
	 */
	public int getY2() {
		return SvgDom.parseIntValue(getElement(), "y2", 0);
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

	public void setStrokeColor(String color) {
		SvgDom.setAttributeNS(getElement(), "stroke", color);
	}

	public void setStrokeOpacity(double opacity) {
		SvgDom.setAttributeNS(getElement(), "stroke-opacity", "" + opacity);
	}

	public void setStrokeWidth(int width) {
		SvgDom.setAttributeNS(getElement(), "stroke-width", width);
	}

	/**
	 * Sets the x-coordinate position of the starting point of Line.
	 * 
	 * @param x1
	 *            the new x-coordinate in pixels
	 */
	public void setX1(int x1) {
		SvgDom.setAttributeNS(getElement(), "x1", x1);
	}

	/**
	 * Sets the y-coordinate position of the ending point of Line.
	 * 
	 * @param x2
	 *            the new x-coordinate in pixels
	 */
	public void setX2(int x2) {
		SvgDom.setAttributeNS(getElement(), "x2", x2);
	}

	/**
	 * Sets the y-coordinate position of the starting point of Line.
	 * 
	 * @param y1
	 *            the new y-coordinate in pixels
	 */
	public void setY1(int y1) {
		SvgDom.setAttributeNS(getElement(), "y1", y1);
	}

	/**
	 * Sets the y-coordinate position of the ending point of Line.
	 * 
	 * @param y2
	 *            the new x-coordinate in pixels
	 */
	public void setY2(int y2) {
		SvgDom.setAttributeNS(getElement(), "y2", y2);
	}
}
