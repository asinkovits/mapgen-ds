package com.sinkovits.mapgen.random;

public class SimpleRandomProvider implements RandomProvider {

	@Override
	public double get(int depth) {
		return Math.random();
	}

}
