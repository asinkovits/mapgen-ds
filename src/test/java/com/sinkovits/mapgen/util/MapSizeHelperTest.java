package com.sinkovits.mapgen.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class MapSizeHelperTest {

	@Test
	public void testCalculateSize() {
		assertThat(MapSizeHelper.calculateSize(1), equalTo(3L));
		assertThat(MapSizeHelper.calculateSize(2), equalTo(5L));
		assertThat(MapSizeHelper.calculateSize(3), equalTo(9L));
		assertThat(MapSizeHelper.calculateSize(4), equalTo(17L));
	}

	@Test
	public void testValidSize() {
		assertTrue(MapSizeHelper.isSizeValid(3L));
		assertTrue(MapSizeHelper.isSizeValid(5L));
		assertTrue(MapSizeHelper.isSizeValid(9L));
		assertTrue(MapSizeHelper.isSizeValid(17L));
	}

	@Test
	public void testInvalidSize() {
		assertFalse(MapSizeHelper.isSizeValid(-3L));
		assertFalse(MapSizeHelper.isSizeValid(-2L));
		assertFalse(MapSizeHelper.isSizeValid(-1L));
		assertFalse(MapSizeHelper.isSizeValid(0L));
		assertFalse(MapSizeHelper.isSizeValid(1L));
		assertFalse(MapSizeHelper.isSizeValid(2L));
		assertFalse(MapSizeHelper.isSizeValid(4L));
		assertFalse(MapSizeHelper.isSizeValid(6L));
		assertFalse(MapSizeHelper.isSizeValid(7L));
		assertFalse(MapSizeHelper.isSizeValid(8L));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateSizeErrorZero() {
		MapSizeHelper.calculateSize(0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testCalculateSizeErrorNegative() {
		MapSizeHelper.calculateSize(-1);
	}

	@Test
	public void testCalculateValidSizeArray() {
		long[] size = MapSizeHelper.calculateValidSizeArray(34);
		assertThat(size, equalTo(new long[] { 3L, 5L, 9L, 17L, 33L }));
	}

	@Test
	public void testCalculateInvalidSizeArray() {
		long[] size = MapSizeHelper.calculateInvalidSizeArray(10);
		assertThat(size, equalTo(new long[] { 0L, 1L, 2L, 4L, 6L, 7L, 8L }));
	}
}
