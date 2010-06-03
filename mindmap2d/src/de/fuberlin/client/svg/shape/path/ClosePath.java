package de.fuberlin.client.svg.shape.path;

/**
 * This class represents Path's closePath step.
 * 
 * @author Henri Kerola / IT Mill Ltd
 * 
 */
public class ClosePath extends PathStep {

	/**
	 * Instantiates a new ClosePath step.
	 */
	public ClosePath() {
	}

	@Override
	public String getSVGString() {
		return "z";
	}
}
