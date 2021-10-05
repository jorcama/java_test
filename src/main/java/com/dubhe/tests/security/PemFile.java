package com.dubhe.tests.security;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.security.Key;

import org.apache.log4j.Logger;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemWriter;

public class PemFile {

	protected static final Logger LOGGER = Logger.getLogger(PemFile.class);

	private PemFile() {
	}

	private static void write(PemObject pemObject, String filename)
			throws IOException {
		PemWriter pemWriter = new PemWriter(new OutputStreamWriter(new FileOutputStream(filename)));
		try {
			pemWriter.writeObject(pemObject);
		} finally {
			pemWriter.close();
		}
	}

	public static void writePemFile(Key key, String description, String filename)
			throws IOException {
		PemObject pemObject = new PemObject(description, key.getEncoded());

		write(pemObject, filename);

		LOGGER.info(String.format("%s successfully writen in file %s.", description, filename));
	}
}