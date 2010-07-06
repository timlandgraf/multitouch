package de.fuberlin.mindmap2d.client.svg;

import com.google.gwt.dom.client.Element;

/**
 * This class contains helpers for the SVGDOM manipulation.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * @author flofreud
 */
public class SvgDom {
	public static final String SVG_NS = "http://www.w3.org/2000/svg";

	public static final String XLINK_NS = "http://www.w3.org/1999/xlink";

	public static void addTransformProperty(Element element, String property,
			String value) {
		String result = "";
		String[] properties = element.getAttribute("transform").concat(" ").split("\\)\\s");

		for (String prop : properties) {
			if (!prop.startsWith(property))
				result = result + prop + " ";
		}
		
		setAttributeNS(element, "transform", result + property + "("
				+ value + ")");
	}

	public static native Element createElementNS(String ns, String tag) /*-{
		return $doc.createElementNS(ns, tag);
	}-*/;

	public static Element createSVGElementNS(String tag) {
		return createElementNS(SVG_NS, tag);
	}

	public static native SvgBbox getBBBox(Element element, boolean attached)
	/*-{
		var bbox = null;
		if (attached) {
			bbox = element.getBBox();
		} else {
			var ns = @de.fuberlin.mindmap2d.client.svg.SvgDom::SVG_NS;
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

	public static String getTransformProperty(Element element, String property) {
		String[] properties = element.getAttribute("transform").concat(" ").split("\\)\\s");
		for (String prop : properties) {
			if (prop.startsWith(property))
				return prop.substring(property.length() + 1, prop.length());
		}

		return "";
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
	 * Parses a int value from a given String. If this String is null or doesn't
	 * contain a parsable int, defaultVal is returned
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

	public static void removeTransformProperty(Element element, String property) {
		String result = "";
		String[] properties = element.getAttribute("transform").concat(" ").split("\\)\\s");

		for (String prop : properties) {
			if (!prop.startsWith(property))
				result = result + prop + " ";
		}
		
		if (result.length() > 1)
			if (result.charAt(result.length() - 1) == ' ')
				result = result.substring(0, result.length() - 1);

		setAttributeNS(element, "transfrom", result);
	}

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
	
	public static native int suspendRedraw(Element svgroot) /*-{
		var suspendID = svgroot.suspendRedraw(5000);
		return(suspendID);
	}-*/;
	
	public static native void unsuspendRedraw(Element svgroot, int suspendID) /*-{
		svgroot.unsuspendRedraw(suspendID);
	}-*/;
}
