package de.fuberlin.mindmap2d.client.svg.shape;

import com.google.gwt.dom.client.Element;

import de.fuberlin.mindmap2d.client.svg.SvgDom;
import de.fuberlin.mindmap2d.client.svg.Shape;


/**
 * Embed text into DrawingArea.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * @author flofreud
 */
public class Text extends Shape {

	/**
	 * Creates a new instance of Text. Position and the text to be rendered are
	 * given as paramaters. Font family is set to "Arial" and font size to 20px.
	 * 
	 * @param x
	 *            the x-coordinate position in pixels
	 * @param y
	 *            the y-coordinate position in pixels
	 * @param text
	 *            the text to be rendered
	 */
	public Text(int x, int y, String text) {
		setX(x);
		setY(y);
		setText(text);
		setTextAnchorStart();
		setFontFamily("Arial");
		setFontSize(20);
	}

	@Override
	protected Element createElement() {
		Element element = SvgDom.createSVGElementNS("text");
		return element;
	}

	/**
	 * Returns the font family of the text.
	 * 
	 * @return the font family
	 */
	public String getFontFamily() {
		return getElement().getAttribute("font-family");
	}

	/**
	 * Returns the font size of the text.
	 * 
	 * @return the size
	 */
	public int getFontSize() {
		return SvgDom.parseIntValue(getElement(), "font-size", 0);
	}

	/**
	 * Returns the rendered text.
	 * 
	 * @return the rendered text
	 */
	public String getText() {
		return getElement().getInnerText();
	}

	/**
	 * Sets the font family of the text.
	 * 
	 * @param family
	 *            the font family
	 */
	public void setFontFamily(String family) {
		SvgDom.setAttributeNS(getElement(), "font-family", family);
	}

	/**
	 * Sets the font size of the text.
	 * 
	 * @param size
	 *            the size
	 */
	public void setFontSize(int size) {
		SvgDom.setAttributeNS(getElement(), "font-size", size);
	}

	public void setPropertyDouble(String property, double value) {
		property = property.toLowerCase();
		if ("fontsize".equals(property)) {
			setFontSize((int) value);
		} else {
			super.setPropertyDouble(property, value);
		}
	}

	/**
	 * Sets the text to be rendered.
	 * 
	 * @param text
	 *            the text
	 */
	public void setText(String text) {
		getElement().setInnerText(text);
	}

	/**
	 * Sets the text-anchor to the start. That means, at the x-y-position should
	 * end the text.
	 */
	public void setTextAnchorEnd() {
		SvgDom.setAttributeNS(getElement(), "text-anchor", "end");
	}

	/**
	 * Sets the text-anchor to the start. That means, at the x-y-position should
	 * be the center of the text.
	 */
	public void setTextAnchorMiddle() {
		SvgDom.setAttributeNS(getElement(), "text-anchor", "middle");
	}

	/**
	 * Sets the text-anchor to the start. That means, at the x-y-position should
	 * start the text.
	 */
	public void setTextAnchorStart() {
		SvgDom.setAttributeNS(getElement(), "text-anchor", "start");
	}
}
