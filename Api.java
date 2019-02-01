package com.example.myfirstapp;

import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;

public interface Api {

    @POST("createPoint")
    call<Point> createPoint(@Body Point point);

    );
}
