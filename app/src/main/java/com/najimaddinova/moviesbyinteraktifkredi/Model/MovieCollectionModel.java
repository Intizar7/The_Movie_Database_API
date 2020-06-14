package com.najimaddinova.moviesbyinteraktifkredi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieCollectionModel extends ErrorCollectionModel {

    @SerializedName("page")
    @Expose
    public Integer page;
    @SerializedName("total_results")
    @Expose
    public Integer total_results;
    @SerializedName("total_pages")
    @Expose
    public Integer total_pages;
    @SerializedName("results")
    @Expose
    public List<MovieSummaryModel> results = null;

    public Integer getPage() {
        return page;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public List<MovieSummaryModel> getResults() {
        return results;
    }
}
