package com.dubhe.tests.word2pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.junit.jupiter.api.Test;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class Word2Pdf {

	@Test
	public void shouldConvertWordToPdfGivenWordWithLinksInHeaderAndFooter()
			throws Exception {

		String wordToConvert = Word2Pdf.class.getResource("test.docx").getFile();
		String pdf = System.getProperty("java.io.tmpdir") + "ConvertWordToPdfGivenWordWithLinksInHeaderAndFooter.pdf";

		word2pdf(wordToConvert, pdf);

		new File(pdf).deleteOnExit();
	}

	public static void word2pdf(String word, String pdf)
			throws Exception {
		word2pdf(new File(word), new File(pdf));
	}

	public static void word2pdf(File word, File pdf)
			throws Exception {

		if (word.exists() == false) throw new Exception("Input file " + word + " does not exist.");

		if (pdf.exists() == true) throw new Exception("Output file " + pdf + " already exists.");

		byte[] docxBytes = FileUtils.readFileToByteArray(word);

		byte[] pdfBytes = generatePDFOutputFromDocx(docxBytes);

		FileOutputStream ospdf = new FileOutputStream(pdf);
		ospdf.write(pdfBytes);
		ospdf.flush();
		ospdf.close();

	}

	private static byte[] generatePDFOutputFromDocx(byte[] docxBytes)
			throws IOException {

		InputStream is = new ByteArrayInputStream(docxBytes);
		XWPFDocument document = new XWPFDocument(is);

		// 2) Prepare Pdf options
		PdfOptions options = PdfOptions.create();

		// 3) Convert XWPFDocument to Pdf
		ByteArrayOutputStream pdfByteOutputStream = new ByteArrayOutputStream();
		PdfConverter.getInstance().convert(document, pdfByteOutputStream, options);

		return pdfByteOutputStream.toByteArray();
	}
}
