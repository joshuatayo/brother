package com.brother.api;

import java.util.List;

import com.brother.entities.Model;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ModelAPI {
	
	@GET("getmodels")
	Call<List<Model>> getAllModel();
}
