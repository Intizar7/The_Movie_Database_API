package com.najimaddinova.moviesbyinteraktifkredi.Services;


import com.najimaddinova.moviesbyinteraktifkredi.Model.ImageModel;
import com.najimaddinova.moviesbyinteraktifkredi.Model.MovieCollectionModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieInterface {

    @GET("movie/top_rated")
    Call<MovieCollectionModel> getTopRated(@Query("page") Integer page);

    @GET("movie/upcoming")
    Call<MovieCollectionModel> getUpComing(@Query("page") Integer page);

    @GET("movie/now_playing")
    Call<MovieCollectionModel> getNowPlaying(@Query("page") Integer page);

    @GET("movie/{movie_id}/images")
    Call<ImageModel> getPhotosMovie(@Path("movie_id") Integer movie_id);

    @GET("search/movie")
    Call<MovieCollectionModel> getSearching(@Query("query") String query, @Query("page") Integer page);

    @GET("discover/movie")
    Call<MovieCollectionModel> discover(@Query("region") String region);

}
