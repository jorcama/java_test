package com.dubhe.security;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

public class RSAKeyPairGenerator {

	private RSAKeyPairGenerator() {
	}
	
	public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
		final int keySize = 2048;
		KeyPairGenerator keyPairGenerator = null;
		KeyPair keyPair = null;
		
		keyPairGenerator = KeyPairGenerator.getInstance("RSA");
		keyPairGenerator.initialize(keySize);
		keyPair = keyPairGenerator.genKeyPair();
		
		return keyPair;
	}
}
