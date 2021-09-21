package com.dubhe.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class ThreeFiveSevenMultiplesTest {

	@Before
	public void setUpStreams() {

	}

	private int[] createArray(int from, int to) {

		int[] array;

		array = new int[(to - from) + 1];

		for (int i = 0; i <= (to - from); i++) {
			array[i] = from + i;
		}

		return array;
	}

	@Test
	public void testZero()
			throws Exception {
		int sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 0 });
		assertEquals(0, sum);

		sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 1 });
		assertEquals(0, sum);

		sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 2 });
		assertEquals(0, sum);
	}

	@Test
	public void test357()
			throws Exception {
		int sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 3 });
		assertEquals(3, sum);

		sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 5 });
		assertEquals(5, sum);

		sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 7 });
		assertEquals(7, sum);

		sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 3, 5, 7 });
		assertEquals(3 + 5 + 7, sum);
	}

	@Test
	public void testUntil7()
			throws Exception {
		int sum = ThreeFiveSevenMultiples.sumMultiplesOf357(createArray(0, 7));
		assertEquals(3 + 5 + 6 + 7, sum);
	}

	@Test
	public void testUntil10()
			throws Exception {
		int sum = ThreeFiveSevenMultiples.sumMultiplesOf357(createArray(0, 10));
		assertEquals(3 + 5 + 6 + 7 + 9 + 10, sum);
	}

	@Test
	public void testFrom5to10()
			throws Exception {
		int sum = ThreeFiveSevenMultiples.sumMultiplesOf357(createArray(5, 10));
		assertEquals(5 + 6 + 7 + 9 + 10, sum);
	}

	@Test
	public void testFrom8to10()
			throws Exception {
		int sum = ThreeFiveSevenMultiples.sumMultiplesOf357(createArray(8, 10));
		assertEquals(9 + 10, sum);
	}

	@After
	public void cleanUpStreams() {
	}
}