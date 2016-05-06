package com.example.user.project;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.user.project.webservice.RestClient;
import com.example.user.project.webservice.response.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList = new ArrayList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            prepareMovieData();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void prepareMovieData() throws IOException {

        RestClient restClient = new RestClient();
        restClient.setApiKey("b397f783867b25b429e07b8c775024e7");
        ApiService apiService = restClient.getApiService();

        Call<Movie> movieCall = apiService.summary(550);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Response<Movie> response, Retrofit retrofit) {

                movieList = (List<Movie>) response.body();

                setUpRecyclerView(movieList);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });





      /*  RestClient.instance.getApiService().getMovieList("b397f783867b25b429e07b8c775024e7", new Callback<List<Movie>>() {
            @Override
            public void onResponse(Response<List<Movie>> response, Retrofit retrofit) {
                movieList = response.body();

                setUpRecyclerView(movieList);

            }

            @Override
            public void onFailure(Throwable t) {

            }
        });*/


    }

    private void setUpRecyclerView(List<Movie> movieList) {

        recyclerView = (RecyclerView)findViewById(R.id.rvMoviesList);
        movieAdapter = new MovieAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(movieAdapter);
    }
}
