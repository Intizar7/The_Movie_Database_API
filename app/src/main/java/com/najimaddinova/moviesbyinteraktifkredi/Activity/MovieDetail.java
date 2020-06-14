package com.najimaddinova.moviesbyinteraktifkredi.Activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.transition.Explode;
import android.view.Window;
import android.widget.*;
import android.os.Bundle;

import com.google.gson.Gson;
import com.najimaddinova.moviesbyinteraktifkredi.Adapter.ImageGalleryRecyclerViewAdapter;
import com.najimaddinova.moviesbyinteraktifkredi.Messages.Messages;
import com.najimaddinova.moviesbyinteraktifkredi.Model.ImageModel;
import com.najimaddinova.moviesbyinteraktifkredi.Model.MovieSummaryModel;
import com.najimaddinova.moviesbyinteraktifkredi.R;
import com.najimaddinova.moviesbyinteraktifkredi.Services.ApiClient;
import com.najimaddinova.moviesbyinteraktifkredi.Services.MovieInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetail extends AppCompatActivity implements Serializable {

    private TextView title, originalTitle, date, adult, voteAverage;
    private RecyclerView recyclerView;
    private ImageGalleryRecyclerViewAdapter recyclerViewAdapter;
    private List<ImageModel.PosterModel> lstPoster = new ArrayList<>();
    private MovieInterface movieInterface;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        MovieSummaryModel movie = (new Gson()).fromJson(getIntent().getStringExtra("MOVIE"), MovieSummaryModel.class);

        setContentView(R.layout.movie_gallery_fragment);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = findViewById(R.id.txtTitle);
        originalTitle = findViewById(R.id.txtOriginalTitle);
        date = findViewById(R.id.txtDate);
        adult = findViewById(R.id.txtAdult);
        voteAverage = findViewById(R.id.txtVoteAverage);
        recyclerView = findViewById(R.id.galleryRecyclerView);

        recyclerViewAdapter = new ImageGalleryRecyclerViewAdapter(lstPoster);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(recyclerViewAdapter);
        title.setText(movie.title);
        originalTitle.setText("Original Title: " + movie.originalTitle);
        date.setText("Release Date: " + movie.releaseDate);
        adult.setText("Adult: " + (movie.adult ? "yes" : "no"));
        voteAverage.setText("Rating: " + movie.voteAverage + "/10");

        try {
            movieInterface = ApiClient.getClient().create(MovieInterface.class);
            final Call<ImageModel> callImages;
            callImages = movieInterface.getPhotosMovie(movie.id);
            callImages.enqueue(new Callback<ImageModel>() {
                @Override
                public void onResponse(Call<ImageModel> call, Response<ImageModel> response) {
                    ImageModel collection = new ImageModel();
                    collection = response.body();
                    if (collection == null) {
                        Toast.makeText(getApplication(), Messages.MoviesCouldntLoad(), Toast.LENGTH_LONG).show();
                    } else {
                        recyclerViewAdapter.newAddedData(collection.posters);
                    }
                }
                @Override
                public void onFailure(Call<ImageModel> call, Throwable t) {
                    Toast.makeText(getApplication(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
