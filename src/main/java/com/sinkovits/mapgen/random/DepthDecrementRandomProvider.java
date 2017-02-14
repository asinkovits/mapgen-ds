package com.sinkovits.mapgen.random;

public class DepthDecrementRandomProvider implements RandomProvider {

	@Override
	public double get(int depth) {
		// TODO Auto-generated method stub
		return Math.random() / Math.max(1, depth);
	}

}
