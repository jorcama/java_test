package com.dubhe.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

public class StringTest {

	
	@Test
	public void format() throws IOException {
		String value = null;
		String formated = String.format("Format: %s", value );
		
		assertEquals("Format: null", formated);
	}
}
