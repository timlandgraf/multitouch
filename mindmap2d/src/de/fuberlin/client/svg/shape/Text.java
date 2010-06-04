package de.fuberlin.client.svg.shape;

import de.fuberlin.client.svg.Shape;
import de.fuberlin.client.svg.VectorObject;
import de.fuberlin.client.svg.util.SVGBase;

/**
 * Embed text into DrawingArea.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * 
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
		setFontFamily("Arial");
		setFontSize(20);
	}

	@Override
	protected Class<? extends VectorObject> getType() {
		return Text.class;
	}

	/**
	 * Returns the rendered text.
	 * 
	 * @return the rendered text
	 */
	public String getText() {
		return SVGBase.getText(getElement());
	}

	/**
	 * Sets the text to be rendered.
	 * 
	 * @param text
	 *            the text
	 */
	public void setText(String text) {
		SVGBase.setText(getElement(), text, isAttached());
	}

	/**
	 * Returns the font family of the text.
	 * 
	 * @return the font family
	 */
	public String getFontFamily() {
		return SVGBase.getTextFontFamily(getElement());
	}

	/**
	 * Sets the font family of the text.
	 * 
	 * @param family
	 *            the font family
	 */
	public void setFontFamily(String family) {
		SVGBase.setTextFontFamily(getElement(), family, isAttached());
	}

	/**
	 * Returns the font size of the text.
	 * 
	 * @return the size
	 */
	public int getFontSize() {
		return SVGBase.getTextFontSize(getElement());
	}

	/**
	 * Sets the font size of the text.
	 * 
	 * @param size
	 *            the size
	 */
	public void setFontSize(int size) {
		SVGBase.setTextFontSize(getElement(), size, isAttached());
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
		if ("fontsize".equals(property)) {
			setFontSize((int) value);
		} else {
			super.setPropertyDouble(property, value);
		}
	}
}
