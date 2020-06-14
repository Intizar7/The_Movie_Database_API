package com.najimaddinova.moviesbyinteraktifkredi.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.najimaddinova.moviesbyinteraktifkredi.Adapter.ImageGalleryRecyclerViewAdapter;
import com.najimaddinova.moviesbyinteraktifkredi.Model.ImageModel;
import com.najimaddinova.moviesbyinteraktifkredi.R;

import java.util.ArrayList;
import java.util.List;

public class FragmentGalary extends Fragment {

    private View v_galery;
    private RecyclerView photoGaleryRecyclerView;
    private List<ImageModel.PosterModel> lstMovie = new ArrayList<>();
    private ImageGalleryRecyclerViewAdapter recyclerViewAdapter;

    public FragmentGalary() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadNextDataFromApi();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        v_galery = inflater.inflate(R.layout.movie_gallery_fragment, container, false);
        photoGaleryRecyclerView = v_galery.findViewById(R.id.galleryRecyclerView);
        recyclerViewAdapter = new ImageGalleryRecyclerViewAdapter(lstMovie);

        return v_galery;
    }

    public void loadNextDataFromApi() {


    }

}
