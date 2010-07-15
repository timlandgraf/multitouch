package de.fuberlin.mindmap2d.client.svg;

import java.util.Iterator;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class ForeignObject extends VectorObject implements HasWidgets {

	private FlowPanel panel;

	@Override
	public void add(Widget widget) {
		panel.add(widget);
	}

	@Override
	public void clear() {
		panel.clear();
	}

	@Override
	protected Element createElement() {
		Element element = SvgDom.createSVGElementNS("foreignObject");
		panel = new FlowPanel();
		//remove the element from style cascade for better embedding
		DOM.setStyleAttribute(panel.getElement(), "position", "relative");
		element.appendChild(panel.getElement());

		return element;
	}

	public ForeignObject(int x, int y, int width, int height) {
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
	}
	
	public int getWidth() {
		return SvgDom.parseIntValue(getElement(), "width", 0);
	}

	public int getX() {
		return SvgDom.parseIntValue(getElement(), "x", 0);
	}
	
	public int getY() {
		return SvgDom.parseIntValue(getElement(), "y", 0);
	}

	@Override
	public Iterator<Widget> iterator() {
		return panel.iterator();
	}

	@Override
	public boolean remove(Widget widget) {
		return panel.remove(widget);
	}

	public void setHeight(int height) {
		SvgDom.setAttributeNS(getElement(), "height", height);
	}
	
	public void setWidth(int width) {
		SvgDom.setAttributeNS(getElement(), "width", width);
	}

	public void setX(int x) {
		SvgDom.setAttributeNS(getElement(), "x", x);
	}

	public void setY(int y) {
		SvgDom.setAttributeNS(getElement(), "y", y);
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
