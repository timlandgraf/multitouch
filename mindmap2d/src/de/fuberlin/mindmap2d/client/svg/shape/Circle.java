package de.fuberlin.mindmap2d.client.svg.shape;

import com.google.gwt.dom.client.Element;

import de.fuberlin.mindmap2d.client.svg.SVGDom;
import de.fuberlin.mindmap2d.client.svg.Shape;



/**
 * Circle represents a circle.
 * 
 * @author Henri Kerola / IT Mill Ltd
 */
public class Circle extends Shape {

	/**
	 * Creates a new Circle with the given position and radius properties.
	 * 
	 * @param x
	 *            the x-coordinate position of the center of the circle in
	 *            pixels
	 * @param y
	 *            the y-coordinate position of the center of the circle in
	 *            pixels
	 * @param radius
	 *            the radius of the circle in pixels
	 */
	public Circle(int x, int y, int radius) {
		setRadius(radius);
		setX(x);
		setY(y);
	}
	
	@Override
	protected Element createElement() {
		return SVGDom.createSVGElementNS("circle");
	}
	
	/**
	 * Returns the radius of the circle in pixels.
	 * 
	 * @return the radius of the circle in pixels
	 */
	public int getRadius() {
		return SVGDom.parseIntValue(getElement(), "r", 0);
	}

	/**
	 * Sets the radius of the circle in pixels.
	 * 
	 * @param radius
	 *            the radius of the circle in pixels
	 */
	public void setRadius(int radius) {
		SVGDom.setAttributeNS(getElement(), "r", radius);
	}

	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("radius".equals(property)) {
			setRadius((int) value);
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
