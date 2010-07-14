package de.fuberlin.mindmap2d.client.touch.events;

import de.fuberlin.mindmap2d.client.touch.TouchEvent;

public class TouchUpEvent extends TouchEvent<TouchUpHandler> {

	/**
	 * Event type for touch up events. Represents the meta-data associated with
	 * this event.
	 */
	private static final Type<TouchUpHandler> TYPE = new Type<TouchUpHandler>(
			"touchup", new TouchUpEvent());

	public static Type<TouchUpHandler> getType() {
		return TYPE;
	}

	protected TouchUpEvent() {

	}

	@Override
	protected void dispatch(TouchUpHandler handler) {
		handler.onTouchUp(this);
	}

	@Override
	public final Type<TouchUpHandler> getAssociatedType() {
		return TYPE;
	}

}
