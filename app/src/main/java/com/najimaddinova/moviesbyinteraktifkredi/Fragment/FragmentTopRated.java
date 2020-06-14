package com.najimaddinova.moviesbyinteraktifkredi.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.najimaddinova.moviesbyinteraktifkredi.Adapter.MovieListRecyclerViewAdapter;
import com.najimaddinova.moviesbyinteraktifkredi.Listeners.OnItemClickListener;
import com.najimaddinova.moviesbyinteraktifkredi.Messages.Messages;
import com.najimaddinova.moviesbyinteraktifkredi.Model.MovieCollectionModel;
import com.najimaddinova.moviesbyinteraktifkredi.Model.MovieSummaryModel;
import com.najimaddinova.moviesbyinteraktifkredi.Activity.MovieDetail;
import com.najimaddinova.moviesbyinteraktifkredi.R;
import com.najimaddinova.moviesbyinteraktifkredi.Services.ApiClient;
import com.najimaddinova.moviesbyinteraktifkredi.Services.MovieInterface;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentTopRated extends Fragment {

    private View v_toprated;
    private RecyclerView topRatedRecyclerView;
    private List<MovieSummaryModel> lstMovie = new ArrayList<>();
    private MovieListRecyclerViewAdapter recyclerViewAdapter;
    private MovieInterface movieInterface;
    private SearchView searchView;
    private Call<MovieCollectionModel> callMovieCollection;
    // Store a member variable for the listener
    private EndlessRecyclerViewScrollListener scrollListener;

    public FragmentTopRated() {
        movieInterface = ApiClient.getClient().create(MovieInterface.class);
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadNextDataFromApi(1);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        v_toprated = inflater.inflate(R.layout.top_rated_fragment, container, false);
        topRatedRecyclerView = v_toprated.findViewById(R.id.topRatedRecyclerView);
       //,
        // searchView = v_toprated.findViewById(R.id.searchTopRated);


        recyclerViewAdapter = new MovieListRecyclerViewAdapter(lstMovie, new OnItemClickListener() {
            @Override
            public void onItemClicked(MovieSummaryModel movieSummary) {
                Intent i = new Intent(getActivity(), MovieDetail.class);
                i.putExtra("MOVIE", (new Gson()).toJson(movieSummary));
                startActivity(i);
            }
        });
        topRatedRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        topRatedRecyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        topRatedRecyclerView.setLayoutManager(linearLayoutManager);
        // Retain an instance so that you can call `resetState()` for fresh searches
        scrollListener = new EndlessRecyclerViewScrollListener(linearLayoutManager) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                // Add whatever code is needed to append new items to the bottom of the list
                loadNextDataFromApi(page + 1);
            }
        };
        // Adds the scroll listener to RecyclerView
        topRatedRecyclerView.addOnScrollListener(scrollListener);

     /*   searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                callMovieCollection = movieInterface.getSearching("query");
                Log.i("query", s.toString());
                List<MovieSummaryModel> newList = new ArrayList<>();
                if (s.length() > 0) {
                    for (MovieSummaryModel movie : lstMovie) {
                        if (movie.title.contains(s)) {
                            newList.add(movie);
                        }
                    }
                } else {
                    newList = lstMovie;
                }

                recyclerViewAdapter.refreshAndFill(newList);
            }
        });*/
        return v_toprated;
    }
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    public void loadNextDataFromApi(int offset) {
        if (offset == 1) {
            Toast.makeText(getContext(), Messages.MoviesAreLoading(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), Messages.NextMoviesAreLoading(), Toast.LENGTH_SHORT).show();
        }

        try {
            movieInterface = ApiClient.getClient().create(MovieInterface.class);
            final Call<MovieCollectionModel> callMovieCollection;
            callMovieCollection = movieInterface.getTopRated(offset);
            callMovieCollection.enqueue(new Callback<MovieCollectionModel>() {
                @Override
                public void onResponse(Call<MovieCollectionModel> call, Response<MovieCollectionModel> response) {

                    MovieCollectionModel collection = new MovieCollectionModel();
                    collection = response.body();

                    if (collection == null) {
                        Toast.makeText(getContext(), Messages.MoviesCouldntLoad(), Toast.LENGTH_LONG).show();
                    } else {
                        if (collection.status_message != null) {
                            Toast.makeText(getContext(), collection.status_message, Toast.LENGTH_LONG).show();
                        } else {
                            recyclerViewAdapter.newAddedData(collection.results);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MovieCollectionModel> call, Throwable t) {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }
}
