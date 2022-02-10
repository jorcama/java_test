package com.dubhe.tests.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.junit.jupiter.api.Test;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class OkHttp {

	@Test
	public void shouldGenerateAStringToken()
			throws IOException {

		String sURL = "https://datos.madrid.es/egob/catalogo/format/fmt.json";
		OkHttpClient client = new OkHttpClient().newBuilder().build();
		Request request = new Request.Builder().url(sURL).method("GET", null).addHeader("Accept", "application/json").build();

		Response response = client.newCall(request).execute();

		System.out.println(response.body().string());
		assertEquals(HttpURLConnection.HTTP_OK, response.code());
	}
}
