package com.dubhe.security;

import java.io.IOException;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

import org.junit.jupiter.api.Test;

public class RSAKeyPairGeneratorTest {

	@Test
	public void shouldGenerateKeyPair()
			throws NoSuchAlgorithmException {

		KeyPair keyPair = RSAKeyPairGenerator.getKeyPair();

		//RSAPublicKey rsa = (RSAPublicKey) keyPair.getPublic();
		//rsa.getModulus();
		//rsa.getPublicExponent();

		System.out.println("public key: " + new String(Base64.getEncoder().encode(keyPair.getPublic().getEncoded())));
		System.out.println("public key: " + Base64.getUrlEncoder().encodeToString(keyPair.getPublic().getEncoded()));

		PrivateKey privateKey = keyPair.getPrivate();
		System.out.println("algorithm: " + privateKey.getAlgorithm());
		System.out.println("format: " + privateKey.getFormat());

		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privateKey.getEncoded());
		System.out.println("private key: " + new String(Base64.getEncoder().encode(pkcs8EncodedKeySpec.getEncoded())));
	}

	@Test
	public void shouldGeneratePEMFiles()
			throws NoSuchAlgorithmException, IOException {
		//https://www.txedo.com/blog/java-generate-rsa-keys-write-pem-file/
		KeyPair keyPair = RSAKeyPairGenerator.getKeyPair();

		RSAPrivateKey priv = (RSAPrivateKey) keyPair.getPrivate();
		RSAPublicKey pub = (RSAPublicKey) keyPair.getPublic();

		String tempDir = System.getProperty("java.io.tmpdir");
		PemFile.writePemFile(priv, "RSA PRIVATE KEY", tempDir + "//id_rsa");
		PemFile.writePemFile(pub, "RSA PUBLIC KEY", tempDir + "//id_rsa.pub");
	}
}
