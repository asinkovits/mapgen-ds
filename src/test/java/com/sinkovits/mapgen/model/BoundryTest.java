package com.sinkovits.mapgen.model;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.sinkovits.mapgen.model.Boundry;
import com.sinkovits.mapgen.model.Point;

public class BoundryTest {

	@Test
	public void test3x3Boundry() {
		Boundry boundry = new Boundry(Point.create(2, 0), Point.create(4, 2));

		assertThat(boundry.getBL(), equalTo(Point.create(2, 0)));
		assertThat(boundry.getBR(), equalTo(Point.create(4, 0)));
		assertThat(boundry.getTL(), equalTo(Point.create(2, 2)));
		assertThat(boundry.getTR(), equalTo(Point.create(4, 2)));
		assertThat(boundry.getMP(), equalTo(Point.create(3, 1)));

		assertThat(boundry.getSize(), equalTo(3));
	}

	@Test
	public void test5x5Boundry() {
		Boundry boundry = new Boundry(Point.create(0, 0), Point.create(4, 4));

		assertThat(boundry.getBL(), equalTo(Point.create(0, 0)));
		assertThat(boundry.getBR(), equalTo(Point.create(4, 0)));
		assertThat(boundry.getTL(), equalTo(Point.create(0, 4)));
		assertThat(boundry.getTR(), equalTo(Point.create(4, 4)));
		assertThat(boundry.getMP(), equalTo(Point.create(2, 2)));

		assertThat(boundry.getSize(), equalTo(5));
	}

	@Test
	public void test5x5BoundrySplit() {
		Boundry boundry = new Boundry(Point.create(0, 0), Point.create(4, 4));
		
		List<Boundry> split = boundry.split();
		assertThat(split.size(), equalTo(4));
		
		Boundry ll = split.get(0);
		Boundry lr = split.get(1);
		Boundry ul = split.get(2);
		Boundry ur = split.get(3);

		assertThat(ll.getSize(), equalTo(3));
		assertThat(lr.getSize(), equalTo(3));
		assertThat(ul.getSize(), equalTo(3));
		assertThat(ur.getSize(), equalTo(3));
	}
}
