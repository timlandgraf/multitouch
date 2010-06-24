package de.fuberlin.mindmap2d.client.svg;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.Element;

/**
 * Group is a container, which can contain one or more VectorObjects.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * @author flofreud
 */
public class Group extends VectorObject implements VectorObjectContainer {

	private List<VectorObject> childrens = new ArrayList<VectorObject>();
	
	@Override
	protected Element createElement() {
		return SvgDom.createSVGElementNS("g");
	}
	
	public VectorObject add(VectorObject vo) {
		childrens.add(vo);
		getElement().appendChild(vo.getElement());
		vo.setParent(this);
		return vo;
	}

	public VectorObject remove(VectorObject vo) {
		if (vo.getParent() != this) {
			return null;
		}
		vo.setParent(null);
		getElement().removeChild(vo.getElement());
		childrens.remove(vo);
		return vo;
	}

	public VectorObject pop(VectorObject vo) {
		if (vo.getParent() != this) {
			return null;
		}
		getElement().appendChild(vo.getElement());
		return vo;
	}

	public void clear() {
		List<VectorObject> childrensCopy = new ArrayList<VectorObject>();
		childrensCopy.addAll(childrens);
		for (VectorObject vo : childrensCopy) {
			this.remove(vo);
		}
	}

	public VectorObject getVectorObject(int index) {
		return childrens.get(index);
	}

	public int getVectorObjectCount() {
		return childrens.size();
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
}
