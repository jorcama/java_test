package com.dubhe.tests;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class FileTest {
	@Before
	public void setUp() {
	}

	private String readFile(File file) throws IOException {
		
		BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		StringBuilder fileContent = new StringBuilder();
		String line = reader.readLine();
		while (line != null) {
			fileContent.append(line);
			line = reader.readLine();
		}
		reader.close();
		
		return fileContent.toString();
	}
	
	@Test
	public void createTemporalFile() throws IOException {
		//String tmpdir = System.getProperty("java.io.tmpdir");
		
		//Path tempFile = Files.createTempFile("dubhe", null);
		File tempFile = File.createTempFile("dubhe", null);
		tempFile.deleteOnExit();
				
		String str = "Hello Dubhe";
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.getAbsolutePath()));
		writer.write(str);
		writer.close();
		
		assertEquals(str, readFile(tempFile));
	}
}
