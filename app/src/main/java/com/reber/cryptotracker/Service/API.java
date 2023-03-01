package com.reber.cryptotracker.Service;

import com.reber.cryptotracker.Models.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("ticker")
    Call<List<Model>> getData();
}
