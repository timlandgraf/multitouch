package de.fuberlin.mindmap2d.client.svg;

/**
 * Shape is an abstract upper-class for VectorObjects that support filling,
 * stroking and positioning.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * @author flofreud
 */
public abstract class Shape extends VectorObject implements Strokeable,
		Positionable, Animatable {

	/**
	 * This constructor defines initial fill and stroke properties, which are
	 * common for all subclasses. These properties are:
	 * 
	 * <pre>
	 * setFillColor(&quot;white&quot;);
	 * setFillOpacity(1);
	 * setStrokeColor(&quot;black&quot;);
	 * setStrokeOpacity(1);
	 * setStrokeWidth(1);
	 * </pre>
	 */
	public Shape() {
		setFillColor("white");
		setFillOpacity(1);
		setStrokeColor("black");
		setStrokeOpacity(1);
		setStrokeWidth(1);
	}

	public int getX() {
		return SvgDom.parseIntValue(getElement(), "x", 0);
	}

	public void setX(int x) {
		SvgDom.setAttributeNS(getElement(), "x", x);
	}

	public int getY() {
		return SvgDom.parseIntValue(getElement(), "y", 0);
	}

	public void setY(int y) {
		SvgDom.setAttributeNS(getElement(), "y", y);
	}

	/**
	 * Returns the current fill color of the element.
	 * 
	 * @param color
	 *            the current fill color
	 */
	public String getFillColor() {
		return getElement().getAttribute("fill");
	}

	/**
	 * Sets the fill color of the element. The color value is specified using
	 * one of the CSS2 color notations. For example, the following values are
	 * legal:
	 * <ul>
	 * <li>red
	 * <li>#ff0000
	 * <li>#f00
	 * <li>rgb(255, 0, 0)
	 * <li>rgb(100%, 0%, 0%)
	 * </ul>
	 * 
	 * Setting the color to null disables elements filling.
	 * 
	 * @see http://www.w3.org/TR/CSS2/syndata.html#value-def-color
	 * @param color
	 *            the new fill color
	 */
	public void setFillColor(String color) { 
		SvgDom.setAttributeNS(getElement(), "fill", (color != null)?color:"none");
	}

	/**
	 * Returns the fill opacity of the Shape element.
	 * 
	 * @return the current fill opacity
	 */
	public double getFillOpacity() {
		return SvgDom.parseDoubleValue(
				getElement().getAttribute("fill-opacity"), 1);
	}

	/**
	 * Sets the fill opacity of the Shape element. The initial value 1.0 means
	 * fully opaque fill. On the other hand, value 0.0 means fully transparent
	 * paint.
	 * 
	 * @param opacity
	 *            the new fill opacity
	 */
	public void setFillOpacity(double opacity) {
		SvgDom.setAttributeNS(getElement(), "fill-opacity", "" + opacity);
	}

	public String getStrokeColor() {
		return getElement().getAttribute("stroke");
	}

	public void setStrokeColor(String color) {
		SvgDom.setAttributeNS(getElement(), "stroke", color);
	}

	public int getStrokeWidth() {
		return SvgDom.parseIntValue(getElement(), "stroke-width", 0);
	}

	public void setStrokeWidth(int width) {
		SvgDom.setAttributeNS(getElement(), "stroke-width", width);
	}

	public double getStrokeOpacity() {
		return SvgDom.parseDoubleValue(getElement()
				.getAttribute("stroke-opacity"), 1);
	}

	public void setStrokeOpacity(double opacity) {
		SvgDom.setAttributeNS(getElement(), "stroke-opacity", "" + opacity);
	}

	//TODO: ???
	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("x".equals(property)) {
			setX((int) value);
		} else if ("y".equals(property)) {
			setY((int) value);
		} else if ("fillopacity".equals(property)) {
			setFillOpacity(value);
		} else if ("strokeopacity".equals(property)) {
			setStrokeOpacity(value);
		} else if ("strokewidth".equals(property)) {
			setStrokeWidth((int) value);
		} else if ("rotation".equals(property)) {
			setRotation((int) value);
		}
	}
}
