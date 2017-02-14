package com.sinkovits.mapgen;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.sinkovits.mapgen.model.Map;

public class DiamondSquareAlgorithmTest {

	private Map map;
	private DiamondSquareAlgorithm algorithm;

	@Before
	public void setUp() {
		algorithm = new DiamondSquareAlgorithm();
	}

	/*-
	 * Expected value:
	 * 3     3.125 1
	 * 4.875 4.25  3.625
	 * 8     5.75 5
	 */
	@Test
	public void testExampleCalculation3x3() {
		map = new Map(3);
		map.setValue(0, 0, 8.0);
		map.setValue(0, 2, 3.0);
		map.setValue(2, 0, 5.0);
		map.setValue(2, 2, 1.0);

		algorithm.perform(map);

		assertThat(round(map.getValue(0, 0)), equalTo(8.0));
		assertThat(round(map.getValue(1, 0)), equalTo(5.75));
		assertThat(round(map.getValue(2, 0)), equalTo(5.0));

		assertThat(round(map.getValue(0, 1)), equalTo(5.0833));
		assertThat(round(map.getValue(1, 1)), equalTo(4.25));
		assertThat(round(map.getValue(2, 1)), equalTo(3.4166));

		assertThat(round(map.getValue(0, 2)), equalTo(3.0));
		assertThat(round(map.getValue(1, 2)), equalTo(2.75));
		assertThat(round(map.getValue(2, 2)), equalTo(1.0));
	}

	/*-
	 * Expected values:
	 * 16  x  24  x  16
	 *  x  x   x  x   x
	 * 40  x  40  x  40
	 *  x  x   x  x   x
	 * 64  x  56  x  64
	 * 
	 */
	@Test
	public void testOneStepExampleDSCalculation5x5() {
		map = new Map(5);
		map.setValue(0, 0, 64.0);
		map.setValue(4, 0, 64.0);
		map.setValue(0, 4, 16.0);
		map.setValue(4, 4, 16.0);

		algorithm.perform(map);

		assertThat(round(map.getValue(0, 0)), equalTo(64.0));
		assertThat(round(map.getValue(2, 0)), equalTo(56.0));
		assertThat(round(map.getValue(4, 0)), equalTo(64.0));

		assertThat(round(map.getValue(0, 2)), equalTo(40.0));
		assertThat(round(map.getValue(2, 2)), equalTo(40.0));
		assertThat(round(map.getValue(4, 2)), equalTo(40.0));

		assertThat(round(map.getValue(0, 4)), equalTo(16.0));
		assertThat(round(map.getValue(2, 4)), equalTo(24.0));
		assertThat(round(map.getValue(4, 4)), equalTo(16.0));
	}

	/*-
	 * Expected values:
	 * 16       23,3333  24  23,3333  16
	 * 28,6666  30       31  30       28,6666
	 * 40       40       40  40       40
	 * 51,3333  50       49  50       51,3333
	 * 64       56,6666  56  56,6666  64
	 * 
	 */
	@Test
	public void testExampleCalculation5x5() {
		map = new Map(5);
		map.setValue(0, 0, 64.0);
		map.setValue(4, 0, 64.0);
		map.setValue(0, 4, 16.0);
		map.setValue(4, 4, 16.0);

		algorithm.perform(map);

		assertThat(round(map.getValue(0, 0)), equalTo(64.0));
		assertThat(round(map.getValue(1, 0)), equalTo(56.6666));
		assertThat(round(map.getValue(2, 0)), equalTo(56.0));
		assertThat(round(map.getValue(3, 0)), equalTo(56.6666));
		assertThat(round(map.getValue(4, 0)), equalTo(64.0));

		assertThat(round(map.getValue(0, 1)), equalTo(51.3333));
		assertThat(round(map.getValue(1, 1)), equalTo(50.0));
		assertThat(round(map.getValue(2, 1)), equalTo(49.0));
		assertThat(round(map.getValue(3, 1)), equalTo(50.0));
		assertThat(round(map.getValue(4, 1)), equalTo(51.3333));

		assertThat(round(map.getValue(0, 2)), equalTo(40.0));
		assertThat(round(map.getValue(1, 2)), equalTo(40.0));
		assertThat(round(map.getValue(2, 2)), equalTo(40.0));
		assertThat(round(map.getValue(3, 2)), equalTo(40.0));
		assertThat(round(map.getValue(4, 2)), equalTo(40.0));

 		assertThat(round(map.getValue(0, 3)), equalTo(28.6666));
		assertThat(round(map.getValue(1, 3)), equalTo(30.0));
		assertThat(round(map.getValue(2, 3)), equalTo(31.0));
		assertThat(round(map.getValue(3, 3)), equalTo(30.0));
		assertThat(round(map.getValue(4, 3)), equalTo(28.6666));

		assertThat(round(map.getValue(0, 4)), equalTo(16.0));
		assertThat(round(map.getValue(1, 4)), equalTo(23.3333));
		assertThat(round(map.getValue(2, 4)), equalTo(24.0));
		assertThat(round(map.getValue(3, 4)), equalTo(23.3333));
		assertThat(round(map.getValue(4, 4)), equalTo(16.0));
	}

	private double round(double value) {
		return (double) Math.floor(value * 10000d) / 10000d;
	}
}
