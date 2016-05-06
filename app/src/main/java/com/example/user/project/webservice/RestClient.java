package com.example.user.project.webservice;

import com.example.user.project.ApiService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;



/**
 * Created by USER on 04-05-2016.
 */
@SuppressWarnings("WeakerAccess")
public class RestClient {

    public static final RestClient instance = new RestClient();

    private ApiService apiService;

    public static final String API_BASE_URL = "https://api.themoviedb.org/3";

    public static final String PARAM_API_KEY = "api_key";

    private Retrofit retrofit;
    private HttpLoggingInterceptor logging;

    private boolean isDebug;
    private String apiKey;

    public RestClient setApiKey(String value) {
        this.apiKey = value;
        retrofit = null;
        return this;
    }

    public RestClient setIsDebug(boolean isDebug) {
        this.isDebug = isDebug;
        if (logging != null) {
            logging.setLevel(isDebug ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        }
        return this;
    }



    public RestClient(){

        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create();

        final OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        okHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);


        Retrofit retrofit = new Retrofit.Builder().baseUrl(API_BASE_URL).client(okHttpClient).addConverterFactory(GsonConverterFactory.create()).build();

        apiService = retrofit.create(ApiService.class);

    }

    public ApiService getApiService(){
        return apiService;
    }




}
