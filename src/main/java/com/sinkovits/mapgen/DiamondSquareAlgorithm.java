package com.sinkovits.mapgen;

import java.util.Iterator;
import java.util.List;
import java.util.stream.DoubleStream;

import com.google.common.collect.Lists;
import com.sinkovits.mapgen.model.Boundry;
import com.sinkovits.mapgen.model.Map;
import com.sinkovits.mapgen.model.Point;
import com.sinkovits.mapgen.random.NopRandomProvider;
import com.sinkovits.mapgen.random.RandomProvider;

public class DiamondSquareAlgorithm {

	private RandomProvider randomProvider = new NopRandomProvider();

	private void performDiamondStep(Map map, Boundry boundry) {
		double ll = map.getValue(boundry.getBL());
		double lr = map.getValue(boundry.getBR());
		double ul = map.getValue(boundry.getTL());
		double ur = map.getValue(boundry.getTR());
		double v = (ll + lr + ul + ur) / 4.0;
		double rand = randomProvider.get(boundry.getDepth());
		map.setValue(boundry.getMP(), v + rand);
	}

	private void setAvgValue(Map map, Point p, Boundry b, double... contributions) {
		// Only calculate each value once!
		if (map.getValue(p) != 0) {
			return;
		}
		Point mp = b.getMP();
		double v = DoubleStream.of(contributions).sum();
		double rand = randomProvider.get(b.getDepth());
		if (map.isBorderPoint(p)) {
			map.setValue(p, (v / 3) + rand);
		} else {
			Point mp2 = calculateMP2(p, mp);

			v += map.getValue(mp2);
			map.setValue(p, (v / 4) + rand);
		}
	}

	private Point calculateMP2(Point p, Point mp) {
		Point result;
		if (p.getX() == mp.getX()) {
			int deltaY = Math.abs(mp.getY() - p.getY());
			if (p.getY() < mp.getY()) {
				result = Point.create(mp.getX(), p.getY() - deltaY);
			} else {
				result = Point.create(mp.getX(), p.getY() + deltaY);
			}
		} else {
			int deltaX = Math.abs(mp.getX() - p.getX());
			if (p.getX() < mp.getX()) {
				result = Point.create(p.getX() - deltaX, mp.getY());
			} else {
				result = Point.create(p.getX() + deltaX, mp.getY());
			}
		}
		return result;
	}

	private void performSquareStep(Map map, Boundry boundry) {
		double ll = map.getValue(boundry.getBL());
		double lr = map.getValue(boundry.getBR());
		double ul = map.getValue(boundry.getTL());
		double ur = map.getValue(boundry.getTR());
		double mp = map.getValue(boundry.getMP());

		setAvgValue(map, boundry.getB(), boundry, ll, lr, mp);
		setAvgValue(map, boundry.getL(), boundry, ll, ul, mp);
		setAvgValue(map, boundry.getT(), boundry, ul, ur, mp);
		setAvgValue(map, boundry.getR(), boundry, ur, lr, mp);
	}

	public void perform(Map map) {
		int size = map.getSize();
		List<Boundry> boundries = Lists.newLinkedList();
		boundries.add(new Boundry(Point.create(0, 0), Point.create(size - 1, size - 1)));

		while (!boundries.isEmpty()) {

			for (Boundry boundry : boundries) {
				performDiamondStep(map, boundry);
			}

			for (Boundry boundry : boundries) {
				performSquareStep(map, boundry);
			}

			List<Boundry> childBoundries = Lists.newLinkedList();
			for (Iterator<Boundry> iterator = boundries.iterator(); iterator.hasNext();) {
				Boundry boundry = (Boundry) iterator.next();
				iterator.remove();
				if (boundry.getSize() > 3) {
					childBoundries.addAll(boundry.split());
				}
			}
			
			boundries.addAll(childBoundries);
		}

	}

	public void perform_(Map map) {
		int size = map.getSize();
		List<Boundry> boundries = Lists.newLinkedList();
		boundries.add(new Boundry(Point.create(0, 0), Point.create(size - 1, size - 1)));

		while (!boundries.isEmpty()) {
			Boundry boundry = boundries.remove(0);
			performDiamondStep(map, boundry);
			performSquareStep(map, boundry);
			if (boundry.getSize() > 3) {
				boundries.addAll(boundry.split());
			}
		}

	}

	public void setRandomProvider(RandomProvider randomProvider) {
		this.randomProvider = randomProvider;
	}

}
