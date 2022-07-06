package com.brother.api;

import java.util.List;

import com.brother.entities.Colour;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ColourAPI {
	@GET("getcolours")
	Call<List<Colour>> getAllColour();
}
