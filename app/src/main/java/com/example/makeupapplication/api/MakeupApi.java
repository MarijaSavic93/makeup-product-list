package com.example.makeupapplication.api;

import com.example.makeupapplication.models.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MakeupApi {

    @GET("/api/v1/products.json")
    Call<List<Product>> getProducts(@Query("brand")String brand);
}
