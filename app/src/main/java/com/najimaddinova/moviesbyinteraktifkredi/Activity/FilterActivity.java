package com.najimaddinova.moviesbyinteraktifkredi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import com.google.gson.Gson;
import com.najimaddinova.moviesbyinteraktifkredi.Model.MovieSummaryModel;
import com.najimaddinova.moviesbyinteraktifkredi.R;

import java.util.logging.Filter;

public class FilterActivity extends AppCompatActivity {

    private TextView txtLanguage,txtPopularity,txtVoteAverage,txtTitle,txtAdult,txtPrimeryReleaseYear;
    private Button btnFilter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        MovieSummaryModel movie = (new Gson()).fromJson(getIntent().getStringExtra("MOVIE"), MovieSummaryModel.class);

        txtTitle = findViewById(R.id.title);
        txtLanguage = findViewById(R.id.language);
        txtPopularity = findViewById(R.id.popularity);
        txtPrimeryReleaseYear = findViewById(R.id.primary_release_year);
        txtAdult = findViewById(R.id.adult);
        txtVoteAverage = findViewById(R.id.vote_average);

        txtLanguage.setText(movie.originalLanguage);
        txtPopularity.setText(Double.toString(movie.popularity));
        txtPrimeryReleaseYear.setText(movie.releaseDate);
        txtAdult.setText("Adult: " + (movie.adult ? "yes" : "no"));
        txtVoteAverage.setText("Rating: " + movie.voteAverage + "/10");


        btnFilter = findViewById(R.id.btnFilter);
        btnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iBackMain = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(iBackMain);

            }
        });

    }
}
