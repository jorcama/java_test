package com.dubhe.tests;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Hello world!
 */
public class ThreeFiveSevenMultiples {

	public static long sumMultiplesOf357forloop(int[] numbers) {

		long sum = 0;
		ArrayList<Integer> multiples = new ArrayList<>();

		for (int i = 0; i < numbers.length; i++) {
			if (numbers[i] % 3 == 0 || numbers[i] % 5 == 0 || numbers[i] % 7 == 0) {
				multiples.add(numbers[i]);
			}
		}

		for (int i = 0; i < multiples.size(); i++) {
			sum += multiples.get(i);
		}

		return sum;
	}

	public static long sumMultiplesOf357forEach(int[] numbers) {

		long sum = 0;
		ArrayList<Integer> multiples = new ArrayList<>();

		for (int i : numbers) {
			if (i % 3 == 0 || i % 5 == 0 || i % 7 == 0) {
				multiples.add(i);
			}
		}

		for (int multiple : multiples) {
			sum += multiple;
		}

		return sum;
	}

	public static long sumMultiplesOf357stream(int[] numbers) {

		return Arrays.stream(numbers)
				.filter(i -> (i % 3 == 0 || i % 5 == 0 || i % 7 == 0))
				.sum();
	}

	public static long sumMultiplesOf357(int[] numbers)
			throws Exception {

		long forloop = sumMultiplesOf357forloop(numbers);
		long foreach = sumMultiplesOf357forEach(numbers);
		long filter = sumMultiplesOf357stream(numbers);

		if (forloop != foreach || foreach != filter) {
			throw new Exception("Some algorithm run wrong.");
		}

		return forloop;
	}
}