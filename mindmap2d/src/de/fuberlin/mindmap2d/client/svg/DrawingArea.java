package de.fuberlin.mindmap2d.client.svg;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.DivElement;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseMoveHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.event.dom.client.MouseUpHandler;
import com.google.gwt.event.dom.client.MouseWheelEvent;
import com.google.gwt.event.dom.client.MouseWheelHandler;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.Widget;

import de.fuberlin.mindmap2d.client.touch.events.DoubleTapGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.DoubleTapGestureHandler;
import de.fuberlin.mindmap2d.client.touch.events.HoldAndTapGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.HoldAndTapGestureHandler;
import de.fuberlin.mindmap2d.client.touch.events.MoveGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.MoveGestureHandler;
import de.fuberlin.mindmap2d.client.touch.events.PullOutGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.PullOutGestureHandler;
import de.fuberlin.mindmap2d.client.touch.events.PushInGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.PushInGestureHandler;
import de.fuberlin.mindmap2d.client.touch.events.TapGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.TapGestureHandler;
import de.fuberlin.mindmap2d.client.touch.events.ZoomInGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.ZoomInGestureHandler;
import de.fuberlin.mindmap2d.client.touch.events.ZoomOutGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.ZoomOutGestureHandler;

/**
 * The following example shows how a DrawingArea instance is created and added
 * to a GWT application. A rectangle is added to this DrawingArea instance. When
 * the user clicks this rectangle its color changes.
 * 
 * <pre>
 * DrawingArea canvas = new DrawingArea(200, 200);
 * RootPanel.get().add(canvas);
 * 
 * Rectangle rect = new Rectangle(10, 10, 100, 50);
 * canvas.add(rect);
 * rect.setFillColor(&quot;blue&quot;);
 * rect.addClickHandler(new ClickHandler() {
 * 	public void onClick(ClickEvent event) {
 * 		Rectangle rect = (Rectangle) event.getSource();
 * 		if (rect.getFillColor().equals(&quot;blue&quot;)) {
 * 			rect.setFillColor(&quot;red&quot;);
 * 		} else {
 * 			rect.setFillColor(&quot;blue&quot;);
 * 		}
 * 	}
 * });
 * </pre>
 * 
 * @author Henri Kerola / IT Mill Ltd
 * 
 */
public class DrawingArea extends Widget implements VectorObjectContainer {

	private final Element root;
	
	private List<VectorObject> childrens = new ArrayList<VectorObject>();

	/**
	 * Creates a DrawingArea of given width and height.
	 */
	public DrawingArea() {
		DivElement container = Document.get().createDivElement();
		setElement(container);

		root = SvgDom.createSVGElementNS("svg");
		container.appendChild(root);

		Element defs = SvgDom.createSVGElementNS("defs");
		root.appendChild(defs);
	}
	
	/**
	 * Creates a DrawingArea of given width and height.
	 * 
	 * @param width
	 *            the width of DrawingArea in pixels
	 * @param height
	 *            the height of DrawingArea in pixels
	 */
	public DrawingArea(int width, int height) {
		DivElement container = Document.get().createDivElement();
		setElement(container);

		root = SvgDom.createSVGElementNS("svg");
		container.appendChild(root);
		setWidth(width);
		setHeight(height);

		Element defs = SvgDom.createSVGElementNS("defs");
		root.appendChild(defs);
	}

	public VectorObject add(VectorObject vo) {
		vo.setParent(this);
		childrens.add(vo);
		root.appendChild(vo.getElement());
		return vo;
	}

	@Override
	public HandlerRegistration addClickHandler(ClickHandler handler) {
		return addDomHandler(handler, ClickEvent.getType());
	}

	@Override
	public HandlerRegistration addContextMenuHandler(ContextMenuHandler handler) {
		return addDomHandler(handler, ContextMenuEvent.getType());
	}

	@Override
	public HandlerRegistration addDoubleClickHandler(DoubleClickHandler handler) {
		return addDomHandler(handler, DoubleClickEvent.getType());
	}

	@Override
	public HandlerRegistration addDoubleTapGestureHandler(
			DoubleTapGestureHandler handler) {
		return addHandler(handler, DoubleTapGestureEvent.getType());
	}

	@Override
	public HandlerRegistration addHoldAndTapGestureHandler(
			HoldAndTapGestureHandler handler) {
		return addHandler(handler, HoldAndTapGestureEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseDownHandler(MouseDownHandler handler) {
		return addDomHandler(handler, MouseDownEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseMoveHandler(MouseMoveHandler handler) {
		return addDomHandler(handler, MouseMoveEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
		return addDomHandler(handler, MouseOutEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
		return addDomHandler(handler, MouseOverEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseUpHandler(MouseUpHandler handler) {
		return addDomHandler(handler, MouseUpEvent.getType());
	}

	@Override
	public HandlerRegistration addMouseWheelHandler(MouseWheelHandler handler) {
		return addDomHandler(handler, MouseWheelEvent.getType());
	}

	@Override
	public HandlerRegistration addMoveGestureHandler(MoveGestureHandler handler) {
		return addHandler(handler, MoveGestureEvent.getType());
	}
	
	@Override
	public HandlerRegistration addPullOutGestureHandler(
			PullOutGestureHandler handler) {
		return addHandler(handler, PullOutGestureEvent.getType());
	}

	@Override
	public HandlerRegistration addPushInGestureHandler(
			PushInGestureHandler handler) {
		return addHandler(handler, PushInGestureEvent.getType());
	}

	@Override
	public HandlerRegistration addTapGestureHandler(TapGestureHandler handler) {
		return addHandler(handler, TapGestureEvent.getType());
	}

	@Override
	public HandlerRegistration addZoomInGestureHandler(
			ZoomInGestureHandler handler) {
		return addHandler(handler, ZoomInGestureEvent.getType());
	}

	@Override
	public HandlerRegistration addZoomOutGestureHandler(
			ZoomOutGestureHandler handler) {
		return addHandler(handler, ZoomOutGestureEvent.getType());
	}

	public void clear() {
		List<VectorObject> childrensCopy = new ArrayList<VectorObject>();
		childrensCopy.addAll(childrens);
		for (VectorObject vo : childrensCopy) {
			this.remove(vo);
		}
	}

	@Override
	protected void doAttachChildren() {
		for (VectorObject vo : childrens) {
			vo.onAttach();
		}
	}

	@Override
	protected void doDetachChildren() {
		for (VectorObject vo : childrens) {
			vo.onDetach();
		}
	}

	/**
	 * Returns the height of the DrawingArea in pixels.
	 * 
	 * @return the height of the DrawingArea in pixels.
	 */
	public int getHeight() {
		return SvgDom.parseIntValue(root, "height", 0);
	}

	public Element getSVGElement(){
		return root;
	}
	
	public VectorObject getVectorObject(int index) {
		return childrens.get(index);
	}

	public int getVectorObjectCount() {
		return childrens.size();
	}
	
	/**
	 * Returns the width of the DrawingArea in pixels.
	 * 
	 * @return the width of the DrawingArea in pixels.
	 */
	public int getWidth() {
		return SvgDom.parseIntValue(root, "width", 0);
	}

	public VectorObject pop(VectorObject vo) {
		if (vo.getParent() != this) {
			return null;
		}
		root.appendChild(vo.getElement());
		return vo;
	}

	public VectorObject remove(VectorObject vo) {
		if (vo.getParent() != this) {
			return null;
		}
		vo.setParent(null);
		root.removeChild(vo.getElement());
		childrens.remove(vo);
		return vo;
	}

	/**
	 * Sets the height of the DrawingArea in pixels.
	 * 
	 * @param height
	 *            the new height
	 */
	public void setHeight(int height) {
		SvgDom.setAttributeNS(root, "height", height);
		root.getParentElement().getStyle().setPropertyPx("height", height);
	}

	@Override
	public void setHeight(String height) {
		boolean successful = false;
		if (height != null && height.endsWith("px")) {
			try {
				setHeight(Integer.parseInt(height.substring(0,
						height.length() - 2)));
				successful = true;
			} catch (NumberFormatException e) {
			}
		}
		if (!successful) {
			throw new IllegalArgumentException(
					"Only pixel units (px) are supported");
		}
	}

	public void setViewBox(int x, int y, int width, int height){
		if(!(x<0||y<0||width<0||height<0)){
			SvgDom.setAttributeNS(root, "viewBox", x+" "+y+" "+width+" "+height);
		}
	}

	/**
	 * Sets the width of the DrawingArea in pixels.
	 * 
	 * @param width
	 *            the new width in pixels
	 */
	public void setWidth(int width) {
		SvgDom.setAttributeNS(root, "width", width);
		root.getParentElement().getStyle().setPropertyPx("width", width);
	}

	@Override
	public void setWidth(String width) {
		boolean successful = false;
		if (width != null && width.endsWith("px")) {
			try {
				setWidth(Integer.parseInt(width
						.substring(0, width.length() - 2)));
				successful = true;
			} catch (NumberFormatException e) {
			}
		}
		if (!successful) {
			throw new IllegalArgumentException(
					"Only pixel units (px) are supported");
		}
	}
}
