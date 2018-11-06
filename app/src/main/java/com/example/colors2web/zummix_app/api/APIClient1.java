package com.example.colors2web.zummix_app.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient1 {
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

//        Gson b_deserializer = new GsonBuilder().setLenient()
//                .registerTypeAdapter(OrderDetails.class, new BoxdeSerializer()).
//                        create();


        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        OkHttpClient.Builder builder = new OkHttpClient.Builder().addInterceptor(interceptor);
        builder.connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        OkHttpClient client = builder.build();


        retrofit = new Retrofit.Builder()
                .baseUrl("http://voxteststores.com/supportportalstaging/public/api/")
//                .baseUrl("http://192.168.0.108:8080/api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
//                .addConverterFactory(GsonConverterFactory.create(b_deserializer))
                .client(client)
                .build();

        return retrofit;
}}
