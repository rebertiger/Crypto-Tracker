package com.reber.cryptotracker.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.reber.cryptotracker.Models.Model;
import com.reber.cryptotracker.R;
import com.reber.cryptotracker.Service.API;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<Model> Model;
    private final String BASE_URL= "https://api.coinpaprika.com/v1/";
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new GsonBuilder().setLenient().create();
        retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create(gson)).build();
        loadfromapi();
    }
    private void loadfromapi(){
        API api = retrofit.create(API.class);
        Call<List<Model>> call = api.getData();
        call.enqueue(new Callback<List<com.reber.cryptotracker.Models.Model>>() {
            @Override
            public void onResponse(Call<List<com.reber.cryptotracker.Models.Model>> call, Response<List<com.reber.cryptotracker.Models.Model>> response) {
                if(response.isSuccessful()){
                    List<Model> responseList = response.body();
                    Model = new ArrayList<>(responseList);
                    for (Model model : Model){
                        System.out.println(model.name);
                        System.out.println(model.symbol);
                        System.out.println(model.price);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<com.reber.cryptotracker.Models.Model>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}