package com.dubhe.tests;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Objects;

import org.junit.jupiter.api.Test;



public class StringTest {

	@Test
	public void format() throws IOException {
		String value = null;
		String formated = String.format("Format: %s", value );
		
		assertEquals("Format: null", formated);
	}
	
	@Test
	public void compare() throws IOException {
		String value = "hello";
		String value2 = "hello";
		String value3 = value2;
		String value4 = value;
		
		assertEquals(true, value == value2);
		assertEquals(true, value3 == value2);
		assertEquals(true, value3 == value4);
		
		String value5 = new String("hello");
		
		assertEquals(false, value == value5);
		assertEquals(true, value.equals(value5));
		
		value=null;
		assertEquals(false, (value!=null && value.equals(value5)));
		value="hello";
		assertEquals(true, (value!=null && value.equals(value5)));
		
		value5=null;
		assertEquals(false, (value!=null && value.equals(value5)));
		
		assertEquals(false, Objects.equals(value, value5));
	}
}
