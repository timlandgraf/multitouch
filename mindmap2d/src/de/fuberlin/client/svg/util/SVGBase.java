package de.fuberlin.client.svg.util;

import java.util.List;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DeferredCommand;

import de.fuberlin.client.svg.Group;
import de.fuberlin.client.svg.Image;
import de.fuberlin.client.svg.Line;
import de.fuberlin.client.svg.VectorObject;
import de.fuberlin.client.svg.shape.Circle;
import de.fuberlin.client.svg.shape.Ellipse;
import de.fuberlin.client.svg.shape.Path;
import de.fuberlin.client.svg.shape.Rectangle;
import de.fuberlin.client.svg.shape.Text;
import de.fuberlin.client.svg.shape.path.Arc;
import de.fuberlin.client.svg.shape.path.ClosePath;
import de.fuberlin.client.svg.shape.path.CurveTo;
import de.fuberlin.client.svg.shape.path.LineTo;
import de.fuberlin.client.svg.shape.path.MoveTo;
import de.fuberlin.client.svg.shape.path.PathStep;

/**
 * SVGBase contains all direct JS-accesses of the SVG component.
 * 
 * This class contains helpers for the SVGDOM manipulation.
 * The whole source is from the GWTGraphics project copied&pasted.
 * The reasons to build an own GWT extension are that GWTGraphics
 * does not provide direct access to all SVG 1.1 properties and
 * that we do not want browser sniffing. 
 * 
 * NOTICE: The source was slightly modified.
 * 
 * @author Henri Kerola / IT Mill Ltd
 *
 */
public final class SVGBase {
	
	/**
	 * SVGBase cannot be instantiated,
	 * all methods are static
	 */
	private SVGBase(){};
	
	public static Element createDrawingArea(Element container, int width, int height) {
		Element root = SVGDom.createSVGElementNS("svg");
		container.appendChild(root);
		setWidth(root, width);
		setHeight(root, height);

		Element defs = SVGDom.createSVGElementNS("defs");
		root.appendChild(defs);

		return root;
	}

	public static Element createElement(Class<? extends VectorObject> type) {
		Element element = null;
		if (type == Rectangle.class) {
			element = SVGDom.createSVGElementNS("rect");
		} else if (type == Circle.class) {
			element = SVGDom.createSVGElementNS("circle");
		} else if (type == Ellipse.class) {
			element = SVGDom.createSVGElementNS("ellipse");
		} else if (type == Path.class) {
			element = SVGDom.createSVGElementNS("path");
		} else if (type == Text.class) {
			element = SVGDom.createSVGElementNS("text");
			element.setAttribute("text-anchor", "start");
		} else if (type == Image.class) {
			element = SVGDom.createSVGElementNS("image");
			// Let aspect ration behave like VML's image does
			element.setAttribute("preserveAspectRatio", "none");
		} else if (type == Line.class) {
			element = SVGDom.createSVGElementNS("line");
		} else if (type == Group.class) {
			element = SVGDom.createSVGElementNS("g");
		}

		return element;
	}

	public static int getX(Element element) {
		return parseIntValue(element,
				getPosAttribute(element, true), 0);
	}

	public static void setX(final Element element, final int x, final boolean attached) {
		setXY(element, true, x, attached);
	}

	public static int getY(Element element) {
		return parseIntValue(element,
				getPosAttribute(element, false), 0);
	}

	public static void setY(final Element element, final int y, final boolean attached) {
		setXY(element, false, y, attached);
	}

	private static void setXY(final Element element, boolean x, final int value,
			final boolean attached) {
		final int rotation = getRotation(element);
		final String posAttr = getPosAttribute(element, x);
		SVGDom.setAttributeNS(element, posAttr, value);
		if (rotation != 0) {
			DeferredCommand.addCommand(new Command() {
				public void execute() {
					SVGDom.setAttributeNS(element, "transform", "");
					SVGDom.setAttributeNS(element, posAttr, value);
					setRotateTransform(element, rotation, attached);
				}
			});
		}
	}
 
	private static String getPosAttribute(Element element, boolean x) {
		String tagName = element.getTagName();
		String attr = "";
		if (tagName.equals("rect") || tagName.equals("text")
				|| tagName.equals("image")) {
			attr = x ? "x" : "y";
		} else if (tagName.equals("circle") || tagName.equals("ellipse")) {
			attr = x ? "cx" : "cy";
		} else if (tagName.equals("path")) {

		} else if (tagName.equals("line")) {
			attr = x ? "x1" : "y1";
		}
		return attr;
	}

	public static String getFillColor(Element element) {
		String fill = element.getAttribute("fill");
		return fill.equals("none") ? null : fill;
	}

	public static void setFillColor(Element element, String color) {
		if (color == null) {
			color = "none";
		}
		SVGDom.setAttributeNS(element, "fill", color);
	}

	public static double getFillOpacity(Element element) {
		return parseDoubleValue(
				element.getAttribute("fill-opacity"), 1);
	}

	public static void setFillOpacity(Element element, double opacity) {
		SVGDom.setAttributeNS(element, "fill-opacity", "" + opacity);
	}
	
	public static double getOpacity(Element element) {
		return parseDoubleValue(
				element.getAttribute("opacity"), 1);
	}

	public static void setOpacity(Element element, double opacity) {
		SVGDom.setAttributeNS(element, "opacity", "" + opacity);
	}

	public static String getStrokeColor(Element element) {
		String stroke = element.getAttribute("stroke");
		return stroke.equals("none") ? null : stroke;
	}

	public static void setStrokeColor(Element element, String color) {
		SVGDom.setAttributeNS(element, "stroke", color);
	}

	public static int getStrokeWidth(Element element) {
		return parseIntValue(element, "stroke-width", 0);
	}

	public static void setStrokeWidth(Element element, int width, boolean attached) {
		SVGDom.setAttributeNS(element, "stroke-width", width);
	}

	public static double getStrokeOpacity(Element element) {
		return parseDoubleValue(element
				.getAttribute("stroke-opacity"), 1);
	}

	public static void setStrokeOpacity(Element element, double opacity) {
		SVGDom.setAttributeNS(element, "stroke-opacity", "" + opacity);
	}

	public static int getWidth(Element element) {
		return parseIntValue(element, "width", 0);
	}

	public static void setWidth(Element element, int width) {
		SVGDom.setAttributeNS(element, "width", width);
		if (element.getTagName().equalsIgnoreCase("svg")) {
			element.getParentElement().getStyle().setPropertyPx("width", width);
		}
	}

	public static int getHeight(Element element) {
		return parseIntValue(element, "height", 0);
	}

	public static void setHeight(Element element, int height) {
		SVGDom.setAttributeNS(element, "height", height);
		if (element.getTagName().equalsIgnoreCase("svg")) {
			element.getParentElement().getStyle().setPropertyPx("height",
					height);
		}
	}

	public static int getCircleRadius(Element element) {
		return parseIntValue(element, "r", 0);
	}

	public static void setCircleRadius(Element element, int radius) {
		SVGDom.setAttributeNS(element, "r", radius);
	}

	public static int getEllipseRadiusX(Element element) {
		return parseIntValue(element, "rx", 0);
	}

	public static void setEllipseRadiusX(Element element, int radiusX) {
		SVGDom.setAttributeNS(element, "rx", radiusX);
	}

	public static int getEllipseRadiusY(Element element) {
		return parseIntValue(element, "ry", 0);
	}

	public static void setEllipseRadiusY(Element element, int radiusY) {
		SVGDom.setAttributeNS(element, "ry", radiusY);
	}

	public static void drawPath(Element element, List<PathStep> steps) {
		StringBuilder path = new StringBuilder();
		for (PathStep step : steps) {
			if (step.getClass() == ClosePath.class) {
				path.append(" z");
			} else if (step.getClass() == MoveTo.class) {
				MoveTo moveTo = (MoveTo) step;
				path.append(moveTo.isRelativeCoords() ? " m" : " M").append(
						moveTo.getX()).append(" ").append(moveTo.getY());
			} else if (step.getClass() == LineTo.class) {
				LineTo lineTo = (LineTo) step;
				path.append(lineTo.isRelativeCoords() ? " l" : " L").append(
						lineTo.getX()).append(" ").append(lineTo.getY());
			} else if (step.getClass() == CurveTo.class) {
				CurveTo curve = (CurveTo) step;
				path.append(curve.isRelativeCoords() ? " c" : " C");
				path.append(curve.getX1()).append(" ").append(curve.getY1());
				path.append(" ").append(curve.getX2()).append(" ").append(
						curve.getY2());
				path.append(" ").append(curve.getX()).append(" ").append(
						curve.getY());
			} else if (step.getClass() == Arc.class) {
				Arc arc = (Arc) step;
				path.append(arc.isRelativeCoords() ? " a" : " A");
				path.append(arc.getRx()).append(",").append(arc.getRy());
				path.append(" ").append(arc.getxAxisRotation());
				path.append(" ").append(arc.isLargeArc() ? "1" : "0").append(
						",").append(arc.isSweep() ? "1" : "0");
				path.append(" ").append(arc.getX()).append(",").append(
						arc.getY());
			}
		}

		SVGDom.setAttributeNS(element, "d", path.toString());
	}

	public static String getText(Element element) {
		return element.getInnerText();
	}

	public static void setText(Element element, String text, boolean attached) {
		element.setInnerText(text);
	}

	public static String getTextFontFamily(Element element) {
		return element.getAttribute("font-family");
	}

	public static void setTextFontFamily(Element element, String family,
			boolean attached) {
		SVGDom.setAttributeNS(element, "font-family", family);
	}

	public static int getTextFontSize(Element element) {
		return parseIntValue(element, "font-size", 0);
	}

	public static void setTextFontSize(Element element, int size, boolean attached) {
		SVGDom.setAttributeNS(element, "font-size", size);
	}

	public static String getImageHref(Element element) {
		return element.getAttribute("href");
	}

	public static void setImageHref(Element element, String src) {
		SVGDom.setAttributeNS(SVGDom.XLINK_NS, element, "href", src);
	}

	public static int getRectangleRoundedCorners(Element element) {
		return parseIntValue(element, "rx", 0);
	}

	public static void setRectangleRoundedCorners(Element element, int radius) {
		SVGDom.setAttributeNS(element, "rx", radius);
		SVGDom.setAttributeNS(element, "ry", radius);
	}

	public static int getLineX2(Element element) {
		return parseIntValue(element, "x2", 0);
	}

	public static void setLineX2(Element element, int x2) {
		SVGDom.setAttributeNS(element, "x2", x2);

	}

	public static int getLineY2(Element element) {
		return parseIntValue(element, "y2", 0);
	}

	public static void setLineY2(Element element, int y2) {
		SVGDom.setAttributeNS(element, "y2", y2);

	}

	public static void add(Element root, Element element, boolean attached) {
		root.appendChild(element);
	}

	public static void remove(Element root, Element element) {
		root.removeChild(element);
	}

	public static void pop(Element root, Element element) {
		root.appendChild(element);
	}

	public static void clear(Element root) {
		while (root.hasChildNodes()) {
			root.removeChild(root.getLastChild());
		}
	}

	public static void setStyleName(Element element, String name) {
		//getStyleSuffix was needed by GWTGraphics for browser sniffing
		SVGDom.setClassName(element, name /*+ "-" + getStyleSuffix()*/);
	}

	public static void setRotation(final Element element, final int degree,
			final boolean attached) {
		element.setPropertyInt("_rotation", degree);
		if (degree == 0) {
			SVGDom.setAttributeNS(element, "transform", "");
			return;
		}
		DeferredCommand.addCommand(new Command() {
			public void execute() {
				setRotateTransform(element, degree, attached);
			}
		});
	}

	private static void setRotateTransform(Element element, int degree,
			boolean attached) {
		SVGBBox box = SVGDom.getBBBox(element, attached);
		int x = box.getX() + box.getWidth() / 2;
		int y = box.getY() + box.getHeight() / 2;
		SVGDom.setAttributeNS(element, "transform", "rotate(" + degree + " "
				+ x + " " + y + ")");
	}

	public static int getRotation(Element element) {
		return element.getPropertyInt("_rotation");
	}

	public static void onAttach(Element element, boolean attached) {
	}
	
	/**
	 * 
	 * @param element
	 * @param attr
	 * @param defaultVal
	 * @return
	 */
	public static int parseIntValue(Element element, String attr, int defaultVal) {
		return parseIntValue(element.getAttribute(attr), defaultVal);
	}
	
	/**
	 * Parses a int value from a given String. If this String is null or
	 * doesn't contain a parsable int, defaultVal is returned
	 * 
	 * @param value
	 * @param defaultVal
	 * @return
	 */
	public static int parseIntValue(String value, int defaultVal) {
		if (value == null) {
			return defaultVal;
		}
		if (value.endsWith("px")) {
			value = value.substring(0, value.length() - 2);
		}
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}

	/**
	 * Parses a double value from a given String. If this String is null or
	 * does't contain a parsable double, defaultVal is returned.
	 * 
	 * @param value
	 *            String to be parsed.
	 * @param defaultVal
	 * @return
	 */
	public static double parseDoubleValue(String value, double defaultVal) {
		if (value == null) {
			return defaultVal;
		}
		if (value.endsWith("px")) {
			value = value.substring(0, value.length() - 2);
		}
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			return defaultVal;
		}
	}
}
