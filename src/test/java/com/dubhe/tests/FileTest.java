package com.dubhe.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FileTest
{
	@BeforeEach
	public void setUp()
	{
	}

	private String readFile(File file)
			throws IOException
	{

		BufferedReader reader = new BufferedReader(new FileReader(file.getAbsolutePath()));
		StringBuilder fileContent = new StringBuilder();
		String line = reader.readLine();
		while (line != null)
		{
			fileContent.append(line);
			line = reader.readLine();
		}
		reader.close();

		return fileContent.toString();
	}

	private static long getChecksumCRC32(InputStream stream, int bufferSize)
			throws IOException
	{
		CheckedInputStream checkedInputStream = new CheckedInputStream(stream, new CRC32());
		byte[] buffer = new byte[bufferSize];
		while (checkedInputStream.read(buffer, 0, buffer.length) >= 0)
		{
		}
		return checkedInputStream.getChecksum().getValue();
	}

	@Test
	public void createTemporalFileDeprecated()
			throws IOException
	{
		//String tmpdir = System.getProperty("java.io.tmpdir");
		File tempFile = File.createTempFile("dubhe", null);
		tempFile.deleteOnExit();

		String str = "Hello Dubhe";
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.getAbsolutePath()));
		writer.write(str);
		writer.close();

		assertEquals(str, readFile(tempFile));
	}

	@Test
	public void checkSum()
			throws IOException
	{

		File tempFile = File.createTempFile("dubhe", null);
		tempFile.deleteOnExit();

		String str = "Hello Dubhe CheckSum";
		BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.getAbsolutePath()));
		writer.write(str);
		writer.close();

		InputStream targetStream = new FileInputStream(tempFile);

		assertEquals(3467181004L, getChecksumCRC32(targetStream, 1024));
	}

	@Test
	public void createTemporalFile()
			throws IOException
	{
		Path csfFile = Files.createTempFile("~csf", ".tmp");	
		Files.delete(csfFile);
	}
}
