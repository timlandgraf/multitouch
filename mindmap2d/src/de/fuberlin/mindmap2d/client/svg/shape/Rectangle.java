package de.fuberlin.mindmap2d.client.svg.shape;

import com.google.gwt.dom.client.Element;

import de.fuberlin.mindmap2d.client.svg.SvgDom;
import de.fuberlin.mindmap2d.client.svg.Shape;



/**
 * Rectangle represents a rectangle.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * @author flofreud
 */
public class Rectangle extends Shape {

	/**
	 * Creates a new Rectangle with the given position and size properties.
	 * 
	 * @param x
	 *            the x-coordinate position of the top-left corner of the
	 *            rectangle in pixels
	 * @param y
	 *            the y-coordinate position of the top-left corner of the
	 *            rectangle in pixels
	 * @param width
	 *            the width of the Rectangle in pixels
	 * @param height
	 *            the height of the Rectangle in pixels
	 */
	public Rectangle(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}

	@Override
	protected Element createElement() {
		return SvgDom.createSVGElementNS("rect");
	}
	
	/**
	 * Returns the width of the Rectangle in pixels.
	 * 
	 * @return the width of the Rectangle in pixels
	 */
	public int getWidth() {
		return SvgDom.parseIntValue(getElement(), "width", 0);
	}

	/**
	 * Sets the width of the Rectangle in pixels.
	 * 
	 * @param width
	 *            the new width in pixels
	 */
	public void setWidth(int width) {
		SvgDom.setAttributeNS(getElement(), "width", width);
	}

	/*@Override
	public void setWidth(String width) {
		if (width != null && width.endsWith("px")) {
			setWidth(Integer.parseInt(width));
		} else {
			throw new UnsupportedOperationException(
					"Only pixel units are supported");
		}
	}*/

	/**
	 * Returns the height of the Rectangle in pixels.
	 * 
	 * @return the height of the Rectangle in pixels
	 */
	public int getHeight() {
		return SvgDom.parseIntValue(getElement(), "height", 0);
	}

	/**
	 * Sets the height of the Rectangle in pixels.
	 * 
	 * @param height
	 *            the new height in pixels
	 */
	public void setHeight(int height) {
		SvgDom.setAttributeNS(getElement(), "height", height);
	}

	/*@Override
	public void setHeight(String height) {
		if (height != null && height.endsWith("px")) {
			setHeight(Integer.parseInt(height));
		} else {
			throw new UnsupportedOperationException(
					"Only pixel units are supported");
		}
	}*/

	/**
	 * Gets the radius of rounded corners in pixels.
	 * Same as getRoundedCornersRX()
	 * 
	 * @return radius of rounded corners in pixels
	 */
	public int getRoundedCorners() {
		return getRoundedCornersRX();
	}
	
	/**
	 * Gets the radius rounded corners in pixels of the RX.
	 * Rounded Corners are elliptic paths.
	 * 
	 * @return radius of x-axis of rounded corners in pixels
	 */
	public int getRoundedCornersRX() {
		return SvgDom.parseIntValue(getElement(), "rx", 0);
	}
	
	/**
	 * Gets the radius rounded corners in pixels of the RY.
	 * Rounded Corners are elliptic paths.
	 * 
	 * @return radius of y-axis of rounded corners in pixels
	 */
	public int getRoundedCornesRY(){
		return SvgDom.parseIntValue(getElement(), "ry", 0);
	}

	/**
	 * Sets the radius of rounded corners in pixels. Value 0 disables rounded
	 * corners.
	 * 
	 * @param radius
	 *            radius of rounded corners in pixels
	 */
	public void setRoundedCorners(int radius) {
		if (radius < 0) {
			radius = 0;
		}
		SvgDom.setAttributeNS(getElement(), "rx", radius);
		SvgDom.setAttributeNS(getElement(), "ry", radius);
	}
	
	/**
	 * Sets the radius of rounded corners in pixels. Value 0 disables rounded
	 * corners. An rounded corner is a elliptic path, therefore radius for
	 * both axis is setable.
	 * 
	 * @param radiusX
	 *            radius of rounded corner in pixels of x-axis
	 * @param radiusY
	 * 			  radius of rounded corner in pixels of y-axis
	 */
	public void setRoundedCornersXY(int radiusX, int radiusY) {
		if (radiusX < 0)
			radiusX = 0;
		if (radiusY < 0)
			radiusY = 0;

		SvgDom.setAttributeNS(getElement(), "rx", radiusX);
		SvgDom.setAttributeNS(getElement(), "ry", radiusY);
	}

	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("width".equals(property)) {
			setWidth((int) value);
		} else if ("height".equals(property)) {
			setHeight((int) value);
		} else if ("roundedcorners".equals(property)) {
			setRoundedCorners((int) value);
		} else {
			super.setPropertyDouble(property, value);
		}
	}
}