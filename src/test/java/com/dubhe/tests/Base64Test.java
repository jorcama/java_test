package com.dubhe.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.Test;

public class Base64Test {
	
	
	@Test
	public void defaultCharsetIsUTF8()
			throws IOException {

		assertEquals(StandardCharsets.UTF_8, Charset.defaultCharset());
	}
	
	@Test
	public void encode()
			throws IOException {

		String toEncode = "ñ";

		byte[] toEncodeBytesUTF8 = toEncode.getBytes(StandardCharsets.UTF_8);
		byte[] bytesUTF8 = Base64.getEncoder().encode(toEncodeBytesUTF8);
		String encodedUTF8 = new String(bytesUTF8, StandardCharsets.UTF_8);

		byte[] toEncodeBytesISO = toEncode.getBytes(StandardCharsets.ISO_8859_1);
		byte[] bytesISO = Base64.getEncoder().encode(toEncodeBytesISO);
		String encodedISO = new String(bytesISO, StandardCharsets.ISO_8859_1);

		assertNotEquals(toEncodeBytesUTF8, toEncodeBytesISO);
		assertNotEquals(bytesUTF8, bytesISO);
		assertNotEquals(encodedUTF8, encodedISO);

		String encodedUTF8ToString = Base64.getEncoder().encodeToString(toEncodeBytesUTF8);
		assertEquals(encodedUTF8, encodedUTF8ToString);
		
		String encodedISOToString = Base64.getEncoder().encodeToString(toEncodeBytesISO);
		assertEquals(encodedISO, encodedISOToString);
	}
}
