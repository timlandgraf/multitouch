package de.fuberlin.mindmap2d.client.touch;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;

public abstract class TouchEvent<H extends EventHandler> extends GwtEvent<H> {

	/**
	 * Type class used by touch event subclasses. Type is specialized for touch
	 * in order to carry information about the native event.
	 * 
	 * @param <H>
	 *            handler type
	 */
	public static class Type<H extends EventHandler> extends GwtEvent.Type<H> {
		private TouchEvent<H> flyweight;
		private String name;

		/**
		 * This constructor allows touch event types to be triggered by the
		 * {@link TouchEvent#fireNativeTouchEvent(com.google.gwt.dom.client.NativeTouchEvent, HasHandlers)}
		 * method. It should only be used by implementors supporting new dom
		 * events.
		 * 
		 * <p>
		 * Any such event type must act as a flyweight around a native event
		 * object.
		 * </p>
		 * 
		 * @param eventName
		 *            the raw native event name
		 * @param flyweight
		 *            the instance that will be used as a flyweight to wrap a
		 *            native event
		 */
		public Type(String eventName, TouchEvent<H> flyweight) {
			this.flyweight = flyweight;

			// Until we have eager clinits implemented, we are manually
			// initializing
			// DomEvent here.
			if (registered == null) {
				init();
			}
			registered.unsafePut(eventName, this);
			name = eventName;
		}

		/**
		 * Gets the name associated with this event type.
		 * 
		 * @return the name of this event type
		 */
		public String getName() {
			return name;
		}
	}

	private static PrivateMap<Type<?>> registered;

	/**
	 * Fires the given native touch event on the specified handlers.
	 * 
	 * @param NativeTouchEvent
	 *            the native touch event
	 * @param handlerSource
	 *            the source of the handlers to fire
	 */
	public static void fireNativeTouchEvent(NativeTouchEvent NativeTouchEvent,
			HasHandlers handlerSource) {
		fireNativeTouchEvent(NativeTouchEvent, handlerSource, null);
	}

	/**
	 * Fires the given native touch event on the specified handlers.
	 * 
	 * @param NativeTouchEvent
	 *            the native event
	 * @param handlerSource
	 *            the source of the handlers to fire
	 * @param relativeElem
	 *            the element relative to which event coordinates will be
	 *            measured
	 */
	public static void fireNativeTouchEvent(NativeTouchEvent NativeTouchEvent,
			HasHandlers handlerSource, Element relativeElem) {
		assert (NativeTouchEvent != null) : "NativeTouchEvent must not be null";

		if (registered != null) {
			final TouchEvent.Type<?> typeKey = registered
					.unsafeGet(NativeTouchEvent.getType());
			if (typeKey != null) {
				// Store and restore native event just in case we are in
				// recursive
				// loop.
				NativeTouchEvent currentNative = typeKey.flyweight.nativeTouchEvent;
				Element currentRelativeElem = typeKey.flyweight.relativeElem;
				typeKey.flyweight.setNativeTouchEvent(NativeTouchEvent);
				typeKey.flyweight.setRelativeElement(relativeElem);

				//handlerSource.fireEvent(typeKey.flyweight);
				TouchService.fireEvent(handlerSource, typeKey.flyweight);
				
				typeKey.flyweight.setNativeTouchEvent(currentNative);
				typeKey.flyweight.setRelativeElement(currentRelativeElem);
			}
		}
	}
	
	private boolean bubbling = true;
	
	public void allowBubbling(){
		bubbling = true;
	}
	
	public boolean shouldBeBubbled(){
		return bubbling;
	}
	
	public void stopBubbling(){
		bubbling = false;
	}

	// This method can go away once we have eager clinits.
	static void init() {
		registered = new PrivateMap<Type<?>>();
	}

	private NativeTouchEvent nativeTouchEvent;
	private Element relativeElem;

	/**
	 * Gets the angle to the touch event.
	 * 
	 * @return the angle
	 */
	public final float getAngle() {
		return getNativeTouchEvent().getAngle();
	}

	@Override
	public abstract TouchEvent.Type<H> getAssociatedType();

	/**
	 * Gets the mouse x-position within the browser window's client area.
	 * 
	 * @return the mouse x-position
	 */
	public int getClientX() {
		return getNativeTouchEvent().getClientX();
	}

	/**
	 * Gets the mouse y-position within the browser window's client area.
	 * 
	 * @return the mouse y-position
	 */
	public int getClientY() {
		return getNativeTouchEvent().getClientY();
	}

	/**
	 * Gets the id of the touch event.
	 * 
	 * @return the id
	 */
	public final int getId() {
		return getNativeTouchEvent().getId();
	}

	public final NativeTouchEvent getNativeTouchEvent() {
		assertLive();
		return nativeTouchEvent;
	}

	/**
	 * Gets the radius between first and second finger.
	 * 
	 * @return the radius
	 */
	public final int getRadius() {
		return getNativeTouchEvent().getRadius();
	}

	/**
	 * Gets the element relative to which event coordinates will be measured. If
	 * this element is <code>null</code>, event coordinates will be measured
	 * relative to the window's client area.
	 * 
	 * @return the event's relative element
	 */
	public final Element getRelativeElement() {
		assertLive();
		return relativeElem;
	}

	/**
	 * Gets the mouse x-position relative to a given element.
	 * 
	 * @param target
	 *            the element whose coordinate system is to be used
	 * @return the relative x-position
	 */
	public int getRelativeX(Element target) {
		NativeTouchEvent e = getNativeTouchEvent();

		return e.getClientX() - target.getAbsoluteLeft()
				+ target.getScrollLeft()
				+ target.getOwnerDocument().getScrollLeft();
	}

	/**
	 * Gets the mouse y-position relative to a given element.
	 * 
	 * @param target
	 *            the element whose coordinate system is to be used
	 * @return the relative y-position
	 */
	public int getRelativeY(Element target) {
		NativeTouchEvent e = getNativeTouchEvent();

		return e.getClientY() - target.getAbsoluteTop() + target.getScrollTop()
				+ target.getOwnerDocument().getScrollTop();
	}

	/**
	 * Gets the mouse x-position on the user's display.
	 * 
	 * @return the mouse x-position
	 */
	public int getScreenX() {
		return getNativeTouchEvent().getScreenX();
	}

	/**
	 * Gets the mouse y-position on the user's display.
	 * 
	 * @return the mouse y-position
	 */
	public int getScreenY() {
		return getNativeTouchEvent().getScreenY();
	}

	/**
	 * Gets the time of the touch event.
	 * 
	 * @return the time
	 */
	public final int getTime() {
		return getNativeTouchEvent().getTime();
	}

	/**
	 * Gets the mouse x-position relative to the event's current target element.
	 * 
	 * @return the relative x-position
	 */
	public int getX() {
		Element relativeElem = getRelativeElement();
		if (relativeElem != null) {
			return getRelativeX(relativeElem);
		}
		return getClientX();
	}

	/**
	 * Gets the mouse y-position relative to the event's current target element.
	 * 
	 * @return the relative y-position
	 */
	public int getY() {
		Element relativeElem = getRelativeElement();
		if (relativeElem != null) {
			return getRelativeY(relativeElem);
		}
		return getClientY();
	}
	
	/**
	 * Prevents the wrapped native event's default action.
	 */
	public void preventDefault() {
		assertLive();
		nativeTouchEvent.preventDefault();
	}
	
	/**
	 * Sets the native event associated with this event. In general,
	 * events should be fired using the static firing methods.
	 * 
	 * @param NativeTouchEvent
	 *            the native event
	 */
	public final void setNativeTouchEvent(NativeTouchEvent NativeTouchEvent) {
		this.nativeTouchEvent = NativeTouchEvent;
	}
	
	/**
	 * Gets the element relative to which event coordinates will be measured.
	 * 
	 * @param relativeElem
	 *            the event's relative element
	 */
	public void setRelativeElement(Element relativeElem) {
		this.relativeElem = relativeElem;
	}
	
	/**
	 * Stops the propagation of the underlying native event.
	 */
	public void stopPropagation() {
		assertLive();
		nativeTouchEvent.stopPropagation();
	}
}
