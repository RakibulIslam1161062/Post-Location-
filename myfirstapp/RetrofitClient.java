package com.example.myfirstapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient
{
    private static final String Base_Url = "192.168.1.105:4000/";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;
    private RetrofitClient(){
        retrofit = new Retrofit.Builder().baseUrl(Base_Url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
    public static synchronized RetrofitClient getInstance()
    {
        if(mInstance == null)
            mInstance = new RetrofitClient();
        return mInstance;
    }
    public Api getApi(){
        return retrofit.create(Api.class);
    }


}
