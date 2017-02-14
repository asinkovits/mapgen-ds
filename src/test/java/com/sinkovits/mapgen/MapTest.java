package com.sinkovits.mapgen;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.sinkovits.mapgen.model.Map;

public class MapTest {

	@Test
	public void testNewMap() {
		Map map = new Map(5);
		assertThat(map.getSize(), equalTo(5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNewMapWrongSize() {
		Map map = new Map(4);
	}

	@Test
	public void testMapValuesSize() {
		Map map = new Map(5);
		double[][] values = map.getValues();
		assertThat(values.length, equalTo(5));
		assertThat(values[0].length, equalTo(5));
	}

	@Test
	public void testMapValuesAreZero() {
		Map map = new Map(5);
		double[][] values = map.getValues();
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				assertThat(values[i][j], equalTo(0.0));
			}
		}
	}

	@Test
	public void testMapSetValue() {
		Map map = new Map(5);
		int x = 0;
		int y = 0;
		double v = 12.34;
		map.setValue(x, y, v);
		double[][] values = map.getValues();
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (i == x && j == y) {
					assertThat(values[i][j], equalTo(v));
				} else {
					assertThat(values[i][j], equalTo(0.0));
				}
			}
		}
	}

}
