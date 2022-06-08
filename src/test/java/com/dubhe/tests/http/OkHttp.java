package com.dubhe.tests.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttp {

	@Test
	public void shouldCalldatosmadridREST()
			throws IOException {

		String sURL = "https://datos.madrid.es/egob/catalogo/format/fmt.json";
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		Request request = new Request.Builder().url(sURL).method("GET", null).addHeader("Accept", "application/json").build();

		Response response = client.newCall(request).execute();

		System.out.println(response.body().string());
		assertEquals(HttpURLConnection.HTTP_OK, response.code());
	}

	@Test
	public void shouldTextLimitRequestBody()
			throws IOException {

		final MediaType TEXT_PLAIN = MediaType.parse("text/plain; charset=utf-8");

		String sURL = "https://datos.madrid.es/egob/catalogo/format/fmt.json";

		StringBuffer text = new StringBuffer();
		for (int i = 0; i < 100000; i++) {
			text.append("a");
		}

		RequestBody body = RequestBody.create(new String(text), TEXT_PLAIN);

		OkHttpClient client = new OkHttpClient().newBuilder().connectTimeout(60, TimeUnit.SECONDS).writeTimeout(60, TimeUnit.SECONDS).build();

		Request request = new Request.Builder().url(sURL).method("GET", null).post(body).addHeader("Accept", "text/plain").build();

		Response response = client.newCall(request).execute();

		System.out.println(response.body().string());
		assertEquals(HttpURLConnection.HTTP_OK, response.code());
	}
}
