package com.dubhe.tests;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

public class CalendarTest
{
	@Test
	public void generateFileName()
			throws IOException
	{
		HashSet<String> fileNames = new HashSet<String>();

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");

		String fileName = "payroll-feb.pdf";
		int iPos = fileName.indexOf(".");

		for (int i = 0; i < 5; i++)
		{
			String tempFileName = fileName.substring(0, iPos) + "-" + dateFormat.format(calendar.getTime()) + fileName.substring(iPos, fileName.length());
			if (i == 0)
			{
				assertTrue(fileNames.add(tempFileName));
			}
			else
			{
				assertFalse(fileNames.add(tempFileName));
			}
		}
	}

	@Test
	public void checkDates()
	{
		Date date = new Date();
		
		System.out.println(date.getTime());
		
		System.out.println(date.toGMTString());
		System.out.println(date.getTimezoneOffset());
		System.out.println(date.toString());
		
		System.out.println(date.toInstant().toString());
	}
}
