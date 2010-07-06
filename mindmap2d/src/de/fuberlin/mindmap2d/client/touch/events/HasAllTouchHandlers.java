package de.fuberlin.mindmap2d.client.touch.events;

import com.google.gwt.event.shared.HandlerRegistration;

public interface HasAllTouchHandlers {
	/**
	 * Adds a {@link TouchDownHandler} handler.
	 * 
	 * @param handler
	 *            the touch handler
	 * @return {@link TouchDownHandler} used to remove this handler
	 */
	HandlerRegistration addTouchDownHandler(TouchDownHandler handler);

	/**
	 * Adds a {@link TouchMoveHandler} handler.
	 * 
	 * @param handler
	 *            the touch handler
	 * @return {@link TouchMoveHandler} used to remove this handler
	 */
	HandlerRegistration addTouchMoveHandler(TouchMoveHandler handler);

	/**
	 * Adds a {@link TouchUpHandler} handler.
	 * 
	 * @param handler
	 *            the touch handler
	 * @return {@link TouchUpHandler} used to remove this handler
	 */
	HandlerRegistration addTouchUpHandler(TouchUpHandler handler);
}
