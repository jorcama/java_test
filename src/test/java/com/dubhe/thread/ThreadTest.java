package com.dubhe.thread;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

public class ThreadTest
{
	@Test
	public void givenMultiThread_whenNonSyncMethod()
			throws InterruptedException
	{
		ExecutorService service = Executors.newFixedThreadPool(3);
		BaeldungSynchronizedMethods summation = new BaeldungSynchronizedMethods();

		IntStream.range(0, 1000).forEach(count -> service.submit(summation::synchronisedCalculate));

		service.awaitTermination(100, TimeUnit.MILLISECONDS);

		assertEquals(1000, summation.getSum());
	}

	@Test
	public void givenMultiThread_whenStaticSyncMethod()
			throws InterruptedException
	{
		ExecutorService service = Executors.newCachedThreadPool();

		IntStream.range(0, 1000).forEach(count -> service.submit(BaeldungSynchronizedMethods::syncStaticCalculate));

		service.awaitTermination(100, TimeUnit.MILLISECONDS);

		assertEquals(1000, BaeldungSynchronizedMethods.staticSum);
	}

	@Test
	public void givenMultiThread_whenBlockSync()
			throws InterruptedException
	{
		ExecutorService service = Executors.newFixedThreadPool(3);
		BaeldungSynchronizedBlocks synchronizedBlocks = new BaeldungSynchronizedBlocks();

		IntStream.range(0, 1000).forEach(count -> service.submit(synchronizedBlocks::performSynchronisedTask));
		service.awaitTermination(100, TimeUnit.MILLISECONDS);

		assertEquals(1000, synchronizedBlocks.getCount());
	}

	@Test
	public void givenMultiThread_whenStaticSyncBlock()
			throws InterruptedException
	{
		ExecutorService service = Executors.newCachedThreadPool();

		IntStream.range(0, 1000).forEach(count -> service.submit(BaeldungSynchronizedBlocks::performStaticSyncTask));
		service.awaitTermination(100, TimeUnit.MILLISECONDS);

		assertEquals(1000, BaeldungSynchronizedBlocks.getStaticCount());
	}
}
