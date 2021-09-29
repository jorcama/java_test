package com.dubhe.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

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
		long sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 0 });
		assertEquals(0, sum);

		sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 1 });
		assertEquals(0, sum);

		sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 2 });
		assertEquals(0, sum);
	}

	@Test
	public void test357()
			throws Exception {
		long sum = ThreeFiveSevenMultiples.sumMultiplesOf357(new int[] { 3 });
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
		long sum = ThreeFiveSevenMultiples.sumMultiplesOf357(createArray(0, 7));
		assertEquals(3 + 5 + 6 + 7, sum);
	}

	@Test
	public void testUntil10()
			throws Exception {
		long sum = ThreeFiveSevenMultiples.sumMultiplesOf357(createArray(0, 10));
		assertEquals(3 + 5 + 6 + 7 + 9 + 10, sum);
	}

	@Test
	public void testFrom5to10()
			throws Exception {
		long sum = ThreeFiveSevenMultiples.sumMultiplesOf357(createArray(5, 10));
		assertEquals(5 + 6 + 7 + 9 + 10, sum);
	}

	@Test
	public void testFrom8to10()
			throws Exception {
		long sum = ThreeFiveSevenMultiples.sumMultiplesOf357(createArray(8, 10));
		assertEquals(9 + 10, sum);
	}


	@Test
	public void testPerformance()
			throws Exception {

		int numbers[] = createArray(0, 1000000);
		long start = System.nanoTime();
		ThreeFiveSevenMultiples.sumMultiplesOf357forloop(numbers);
		System.out.println("forloop:" + (System.nanoTime() - start));

		start = System.nanoTime();
		ThreeFiveSevenMultiples.sumMultiplesOf357forEach(numbers);
		System.out.println("forEach:" + (System.nanoTime() - start));

		start = System.nanoTime();
		ThreeFiveSevenMultiples.sumMultiplesOf357stream(numbers);
		System.out.println("stream: " + (System.nanoTime() - start));
	}
	
	@After
	public void cleanUpStreams() {
	}
}