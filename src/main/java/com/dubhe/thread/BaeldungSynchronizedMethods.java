package com.dubhe.thread;

public class BaeldungSynchronizedMethods
{
	public static int staticSum = 0;
	private int sum = 0;

	public void calculate()
	{
		setSum(getSum() + 1);
	}

	public synchronized void synchronisedCalculate()
	{
		setSum(getSum() + 1);
	}

	int getSum()
	{
		return sum;
	}

	void setSum(int sum)
	{
		this.sum = sum;
	}

	public static synchronized void syncStaticCalculate()
	{
		staticSum = staticSum + 1;
	}
}
