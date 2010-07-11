package de.fuberlin.mindmap2d.client.gui;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.event.dom.client.MouseUpEvent;
import com.google.gwt.user.client.DOM;

import de.fuberlin.mindmap2d.client.model.*;
import de.fuberlin.mindmap2d.client.svg.DrawingArea;
import de.fuberlin.mindmap2d.client.svg.Group;
import de.fuberlin.mindmap2d.client.svg.Line;
import de.fuberlin.mindmap2d.client.svg.SvgDom;
import de.fuberlin.mindmap2d.client.svg.VectorObject.TransfromValue;
import de.fuberlin.mindmap2d.client.svg.Shape;
import de.fuberlin.mindmap2d.client.svg.shape.*;

import de.fuberlin.mindmap2d.client.touch.events.MoveGestureEvent;
import de.fuberlin.mindmap2d.client.touch.events.MoveGestureHandler;

import com.google.gwt.user.client.Random;
import com.google.gwt.core.client.GWT;


public class EdgeView {
		private BubbleView bubbleA, bubbleB;
		protected Edge model;
		private Group canvas;
		private Line line;

		public EdgeView(BubbleView bubbleA, BubbleView bubbleB, Edge model) {
			this.bubbleA = bubbleA;
			this.bubbleB = bubbleB;
			this.model = model;
			bubbleA.addEdge(this);
			bubbleB.addEdge(this);
		}

		public void addToThis(Group canvas) {
			this.canvas = canvas;
			line = new Line(0, 0, 0, 0);
			line.setStrokeColor(Configurator.edgeColor);
			update();
			canvas.add(line);
			bubbleA.toFront();
			bubbleB.toFront();
		}

		public void remove() {
			canvas.remove(line);
			bubbleA.removeEdge(this);
			bubbleB.removeEdge(this);
		}

		public void update() {
			line.setX1(bubbleA.x);
			line.setY1(bubbleA.y);
			line.setX2(bubbleB.x);
			line.setY2(bubbleB.y);
		}
	}