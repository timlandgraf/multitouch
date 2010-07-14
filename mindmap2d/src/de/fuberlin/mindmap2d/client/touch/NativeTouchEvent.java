package de.fuberlin.mindmap2d.client.touch;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.dom.client.EventTarget;

/**
 * The native touch event.
 */
public class NativeTouchEvent extends JavaScriptObject {

	/**
	 * Required constructor for GWT compiler to function.
	 */
	protected NativeTouchEvent() {
	}

	private native float eventGetAngle(NativeTouchEvent evt) /*-{
		return evt.angle || 0;
	}-*/;

	private native int eventGetClientX(NativeTouchEvent evt) /*-{
		return evt.clientX || 0;
	}-*/;

	private native int eventGetClientY(NativeTouchEvent evt) /*-{
		return evt.clientY || 0;
	}-*/;

	private native EventTarget eventGetCurrentTarget(NativeTouchEvent event) /*-{
		return event.currentTarget;
	}-*/;

	private native int eventGetId(NativeTouchEvent evt) /*-{
		return evt.id || 0;
	}-*/;

	private native int eventGetRadius(NativeTouchEvent evt) /*-{
		return evt.radius || 0;
	}-*/;

	private native EventTarget eventGetRelatedTarget(NativeTouchEvent evt) /*-{
		return evt.relatedTarget;
	}-*/;

	private native int eventGetScreenX(NativeTouchEvent evt) /*-{
		return evt.screenX || 0;
	}-*/;

	private native int eventGetScreenY(NativeTouchEvent evt) /*-{
		return evt.screenY || 0;
	}-*/;

	private native EventTarget eventGetTarget(NativeTouchEvent evt) /*-{
		return evt.target;
	}-*/;

	private native int eventGetTime(NativeTouchEvent evt) /*-{
		return evt.time || 0;
	}-*/;

	private final native String eventGetType(NativeTouchEvent evt) /*-{
		return evt.type;
	}-*/;

	private native void eventPreventDefault(NativeTouchEvent evt) /*-{
		evt.preventDefault();
	}-*/;

	private native void eventStopPropagation(NativeTouchEvent evt) /*-{
		evt.stopPropagation();
	}-*/;

	private native String eventToString(NativeTouchEvent evt) /*-{
		return evt.toString();
	}-*/;

	/**
	 * Gets the angle to the touch event.
	 * 
	 * @return the angle
	 */
	public final float getAngle() {
		return eventGetAngle(this);
	}

	/**
	 * Gets the mouse x-position within the browser window's client area.
	 * 
	 * @return the mouse x-position
	 */
	public final int getClientX() {
		return eventGetClientX(this);
	}

	/**
	 * Gets the mouse y-position within the browser window's client area.
	 * 
	 * @return the mouse y-position
	 */
	public final int getClientY() {
		return eventGetClientY(this);
	}

	/**
	 * Gets the current target element of this event. This is the element whose
	 * listener fired last, not the element which fired the event initially.
	 * 
	 * @return the event's current target element
	 */
	public final EventTarget getCurrentEventTarget() {
		return eventGetCurrentTarget(this);
	}

	/**
	 * Returns the element that was the actual target of the given event.
	 * 
	 * @return the target element
	 */
	public final EventTarget getEventTarget() {
		return eventGetTarget(this);
	}

	/**
	 * Gets the id of the touch event.
	 * 
	 * @return the id
	 */
	public final int getId() {
		return eventGetId(this);
	}

	/**
	 * Gets the radius.
	 * 
	 * @return the radius
	 */
	public final int getRadius() {
		return eventGetRadius(this);
	}

	/**
	 * Gets the related target for this event.
	 * 
	 * @return the related target
	 */
	public final EventTarget getRelatedEventTarget() {
		return eventGetRelatedTarget(this);
	}

	/**
	 * Gets the mouse x-position on the user's display.
	 * 
	 * @return the mouse x-position
	 */
	public final int getScreenX() {
		return eventGetScreenX(this);
	}

	/**
	 * Gets the mouse y-position on the user's display.
	 * 
	 * @return the mouse y-position
	 */
	public final int getScreenY() {
		return eventGetScreenY(this);
	}

	/**
	 * Gets a string representation of this event.
	 * 
	 * We do not override {@link #toString()} because it is final in
	 * {@link com.google.gwt.core.client.JavaScriptObject }.
	 * 
	 * @return the string representation of this event
	 */
	public final String getString() {
		return eventToString(this);
	}

	/**
	 * Gets the time of the touch event.
	 * 
	 * @return the time
	 */
	public final int getTime() {
		return eventGetTime(this);
	}

	/**
	 * Gets the enumerated type of this event.
	 * 
	 * @return the event's enumerated type
	 */
	public final String getType() {
		return eventGetType(this);
	}

	/**
	 * Prevents the browser from taking its default action for the given event.
	 */
	public final void preventDefault() {
		eventPreventDefault(this);
	}

	/**
	 * Stops the event from being propagated to parent elements.
	 */
	public final void stopPropagation() {
		eventStopPropagation(this);
	}
}
