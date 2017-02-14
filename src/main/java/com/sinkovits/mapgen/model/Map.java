package com.sinkovits.mapgen.model;

import com.sinkovits.mapgen.util.MapSizeHelper;

public class Map {

	private int size;
	private double values[][];

	public Map(int size) {
		validateSize(size);
		this.size = size;
		this.values = new double[size][size];
	}

	private void validateSize(int size) {
		if (!MapSizeHelper.isSizeValid(size))
			throw new IllegalArgumentException("Size must be a factor of 2 plus one.");
	}

	public int getSize() {
		return size;
	}

	public double[][] getValues() {
		return values;
	}

	public void setValue(int x, int y, double v) {
		values[x][y] = v;
	}

	public double getValue(int x, int y) {
		return values[x][y];
	}

	public void setValue(Point p, double v) {
		setValue(p.getX(), p.getY(), v);
	}

	public double getValue(Point p) {
		return getValue(p.getX(), p.getY());
	}

	public boolean isBorderPoint(Point p) {
		return p.getX() == 0 || p.getX() == size - 1 || p.getY() == 0 || p.getY() == size - 1;
	}

}
