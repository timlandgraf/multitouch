package de.fuberlin.client.svg.util;

import com.google.gwt.dom.client.Element;

/**
 * This class contains helpers for the SVGDOM manipulation.
 * The whole source is from the GWTGraphics project copied&pasted.
 * The reasons to build an own GWT extension are that GWTGraphics
 * does not provide direct access to all SVG 1.1 properties and
 * that we do not want browser sniffing. 
 * 
 * NOTICE: The source was slightly modified.(mainly namespace)
 * 
 * @author Henri Kerola / IT Mill Ltd
 *
 */
public class SVGDom {
	public static final String SVG_NS = "http://www.w3.org/2000/svg";

	public static final String XLINK_NS = "http://www.w3.org/1999/xlink";

	public static Element createSVGElementNS(String tag) {
		return createElementNS(SVG_NS, tag);
	}

	public static native Element createElementNS(String ns, String tag) /*-{
		return $doc.createElementNS(ns, tag);
	}-*/;

	public static void setAttributeNS(Element elem, String attr, int value) {
		setAttributeNS(null, elem, attr, "" + value);
	}

	public static void setAttributeNS(Element elem, String attr, String value) {
		setAttributeNS(null, elem, attr, value);
	}

	public static native void setAttributeNS(String uri, Element elem,
			String attr, String value) /*-{
		elem.setAttributeNS(uri, attr, value);
	}-*/;

	public static native void setClassName(Element element, String name) /*-{
		// See http://newsgroups.cryer.info/mozilla/dev.tech.svg/200803/080318666.html
		element.className.baseVal = name;
	}-*/;

	public static native SVGBBox getBBBox(Element element, boolean attached)
	/*-{
		var bbox = null;
		if (attached) {
			bbox = element.getBBox();
		} else {
			var ns = @de.fuberlin.client.svg.util.SVGDom::SVG_NS;
			var svg = $doc.createElementNS(ns, "svg");
			var parent = element.parentNode;
			svg.appendChild(element);
			$doc.documentElement.appendChild(svg);
			bbox = element.getBBox();
			$doc.documentElement.removeChild(svg);
			if (parent != null) {
				parent.appendChild(element);
			}
		}
		return bbox;
	}-*/;
}
