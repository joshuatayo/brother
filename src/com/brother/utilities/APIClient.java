package com.brother.utilities;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class APIClient {
	
	private static Retrofit retrofit = null;
	private static String BASE_URL = "http://127.0.0.1:8000/api/";
	
	public static Retrofit getClient() {
		
		HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
		httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
		OkHttpClient client = new OkHttpClient.Builder()
		  .addInterceptor(httpLoggingInterceptor)
		  .build();
		retrofit = new Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(GsonConverterFactory.create())
				.client(client)
				.build();
		
		return retrofit;
	}

}
