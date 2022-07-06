package com.brother.api;

import com.brother.entities.Product;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ProductAPI {
	
	@POST("addproduct")
	Call<Void> addProduct(@Body Product product);
}
