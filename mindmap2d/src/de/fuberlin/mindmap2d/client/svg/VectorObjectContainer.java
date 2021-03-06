package de.fuberlin.mindmap2d.client.svg;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasAllMouseHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasContextMenuHandlers;
import com.google.gwt.event.dom.client.HasDoubleClickHandlers;

import de.fuberlin.mindmap2d.client.touch.events.HasAllGestureHandlers;

/**
 * Classes implementing this class are able to contain VectorObjects.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * 
 */
public interface VectorObjectContainer extends HasClickHandlers,
		HasAllMouseHandlers, HasDoubleClickHandlers, HasContextMenuHandlers,
		HasAllGestureHandlers {

	/**
	 * 
	 * Add the given VectorObject to this VectorObjectContainer.
	 * 
	 * @param vo
	 *            VectorObject to be added
	 * @return added VectorObject
	 */
	public abstract VectorObject add(VectorObject vo);

	/**
	 * Removes all contained VectorObjects from this VectorObjectContainer.
	 */
	public abstract void clear();

	/**
	 * Nasty adapter hook to the getElement of Widget
	 * 
	 * @return DOM-Element of this Container
	 */
	public abstract Element getElement();

	/**
	 * Returns the VectorObject element at the specified position.
	 * 
	 * @param index
	 *            index of element to return.
	 * @return the VectorObject element at the specified position.
	 */
	public abstract VectorObject getVectorObject(int index);

	/**
	 * Returns the number of VectorObjects in this VectorObjectContainer.
	 * 
	 * @return the number of VectorObjects in this VectorObjectContainer.
	 */
	public abstract int getVectorObjectCount();

	/**
	 * 
	 * Pops the given VectorObject in this VectorObjectContainer on top.
	 * 
	 * @param vo
	 *            VectorObject to be popped on top
	 * @return the popped VectorObject
	 */
	public abstract VectorObject pop(VectorObject vo);

	/**
	 * Remove the given VectorObject from this VectorObjectContainer.
	 * 
	 * @param vo
	 *            VectorObject to be removed
	 * 
	 * @return removed VectorObject or null if the container doesn't contained
	 *         the given VectorObject
	 */
	public abstract VectorObject remove(VectorObject vo);

}