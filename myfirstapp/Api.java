package com.example.myfirstapp;

//import okhttp3.Call;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.POST;
import retrofit2.Call;

public interface Api {

    @POST("ninjas")

    Call<Point> createPoint(@Body Point point);







    //Call<Point> createPoint(Point point);
}
