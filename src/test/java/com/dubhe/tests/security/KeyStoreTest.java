package com.dubhe.tests.security;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.binary.Hex;
import org.junit.jupiter.api.Test;

public class KeyStoreTest {

	private String key = "PaJyn8zpCQKyeqAcgRexG18V2euDZw41b6YNGbeemJoxrbZJ/4JfMw==";
	private String hexString = "50614A796E387A7043514B79657141636752657847313856326575445A7734316236594E476265656D4A6F7872625A4A2F344A664D773D3D";

	@Test
	public void shouldGenerateAStringToken()
			throws Exception {
		final String passwordToOpenP12 = "changeit";

		KeyStore store = KeyStore.getInstance("PKCS12");

		try (InputStream inputStream = KeyStoreTest.class.getResourceAsStream("dubhe.p12")) {
			store.load(inputStream, passwordToOpenP12.toCharArray());
			PrivateKey privateKey = (PrivateKey) store.getKey("dubhe", passwordToOpenP12.toCharArray());
			X509Certificate publicKey = (X509Certificate) store.getCertificate("dubhe");

			System.out.println("public key: " + new String(Base64.getEncoder().encode(publicKey.getEncoded())));

			System.out.println("algorithm: " + privateKey.getAlgorithm());
			System.out.println("format: " + privateKey.getFormat());

			PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
			System.out.println("private key: " + new String(Base64.getEncoder().encode(pkcs8EncodedKeySpec.getEncoded())));
		}
	}

	@Test
	public void shouldDecodeHex()
			throws Exception {

		byte[] bytes = Hex.decodeHex(hexString.toCharArray());

		String str = new String(bytes, "UTF-8");
		System.out.println(str);

		assertEquals(key, str);

		bytes = DatatypeConverter.parseHexBinary(hexString);
		str = new String(bytes, "UTF-8");
		System.out.println(str);

		assertEquals(key, str);
	}

	@Test
	public void shouldEncodeHex()
			throws Exception {

		char[] encode = Hex.encodeHex(key.getBytes("UTF-8"));
		String str = new String(encode);

		System.out.println(str.toUpperCase());
		assertEquals(hexString, str.toUpperCase());

		str = DatatypeConverter.printHexBinary(key.getBytes("UTF-8"));

		System.out.println(str);
		assertEquals(str, hexString);
	}
}
