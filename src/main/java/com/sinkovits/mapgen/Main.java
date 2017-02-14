package com.sinkovits.mapgen;

import java.io.IOException;
import java.nio.file.Paths;

import com.sinkovits.mapgen.model.Map;
import com.sinkovits.mapgen.model.Point;
import com.sinkovits.mapgen.random.DepthDecrementRandomProvider;

public class Main {

	private static final int MAP_SIZE = 1025;
	private static final String OUTPUT_PATH = "c:/Users/Anti/Desktop";

	public static void main(String[] args) throws IOException {
		Map map = new Map(MAP_SIZE);
		map.setValue(Point.create(0, 0), Math.random());
		map.setValue(Point.create(0, MAP_SIZE - 1), Math.random());
		map.setValue(Point.create(MAP_SIZE - 1, MAP_SIZE - 1), Math.random());
		map.setValue(Point.create(MAP_SIZE - 1, 0), Math.random());

		DiamondSquareAlgorithm ds = new DiamondSquareAlgorithm();
		ds.setRandomProvider(new DepthDecrementRandomProvider());
		ds.perform(map);

		ImageWriter writer = new ImageWriter();
		writer.write(map, Paths.get(OUTPUT_PATH));
	}

}
