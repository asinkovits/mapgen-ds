package com.sinkovits.mapgen.random;

public class NopRandomProvider implements RandomProvider {

	@Override
	public double get(int depth) {
		return 0;
	}

}
