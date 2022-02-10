package com.dubhe.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class DubheTest {

	PrintStream stdout = System.out;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@BeforeEach
	public void setUpStreams() {
		System.setOut(new PrintStream(outContent));
	}

	@Test
	public void testAppConstructor() {
		try {
			new Dubhe();
		} catch (Exception e) {
			fail("Construction failed.");
		}
	}

	@Test
	public void testAppMain() {
		Dubhe.main(null);
		try {
			assertEquals("Hello World!" + System.getProperty("line.separator"), outContent.toString());
		} catch (AssertionError e) {
			fail("\"message\" is not \"Hello World!\"");
		}
	}

	@AfterEach
	public void cleanUpStreams() {
		System.setOut(stdout);
	}

}