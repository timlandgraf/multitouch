package de.fuberlin.mindmap2d.client.touch;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.ui.Widget;

public class TouchService {
	
	@SuppressWarnings("unused")
	private static JavaScriptObject dispatchTouchEvent;
	
	public static void fireEvent(HasHandlers handlerSource, TouchEvent<?> event){
		assert(handlerSource instanceof Widget) : "Can't fire touch event on anything else as widget";
		Widget handler = (Widget)handlerSource;
		
		event.allowBubbling();
		
		do{
			handler.fireEvent(event);
			handler = handler.getParent();
		}while(handler != null && event.shouldBeBubbled());
	}
	
	//TODO: Refactor
	public static native void initTouchService() /*-{
		@de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent = $entry(function(event) {
			var listener, curElem = event.target;

			while (curElem && !(listener = curElem.__listener)) {
				curElem = curElem.parentNode;
		 	}

			if (curElem && curElem.nodeType != 1) {
				curElem = null;
			}

			if (listener) {
				if (@com.google.gwt.user.client.impl.DOMImpl::isMyListener(Ljava/lang/Object;)(listener)) {
						@de.fuberlin.mindmap2d.client.touch.TouchEvent::fireNativeTouchEvent(Lde/fuberlin/mindmap2d/client/touch/NativeTouchEvent;Lcom/google/gwt/event/shared/HasHandlers;Lcom/google/gwt/dom/client/Element;)(event, listener, listener.@com.google.gwt.user.client.ui.Widget::getElement());
		    	}
		  	}
		});

		$wnd.addEventListener('touchdown', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);
		$wnd.addEventListener('touchup', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);
		$wnd.addEventListener('touchmove', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);

		$wnd.addEventListener('tap', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);
		$wnd.addEventListener('doubletap', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);
		$wnd.addEventListener('move', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);
		$wnd.addEventListener('holdtap', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);
		$wnd.addEventListener('pushin', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);
		$wnd.addEventListener('pullout', @de.fuberlin.mindmap2d.client.touch.TouchService::dispatchTouchEvent, true);
	}-*/;
	
	
}
