package com.example.user.project;

import com.example.user.project.webservice.response.Movie;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface ApiService {

   // @Headers({"Content-Type:application/json"})

    @Headers("Content-Type: application/json")

   /* @GET("/discover/movie/550?")
    //Observable<String> getMovieList(Callback<List<Movie>> responseCallback);

    Call<List<Movie>> getMovieList(@Query("api_key") String api_key, Callback<List<Movie>> callback);
*/
    @GET("/movie/{id}")
    Call<Movie> summary(
            @Path("id") int tmdbId);


}
