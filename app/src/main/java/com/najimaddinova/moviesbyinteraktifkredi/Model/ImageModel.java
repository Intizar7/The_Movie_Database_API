package com.najimaddinova.moviesbyinteraktifkredi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageModel extends ErrorCollectionModel{

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("backdrops")
    @Expose
    public List<Backdrop> backdrops = null;
    @SerializedName("posters")
    @Expose
    public List<PosterModel> posters = null;

    public class Backdrop {

        @SerializedName("aspect_ratio")
        @Expose
        public Double aspectRatio;
        @SerializedName("file_path")
        @Expose
        public String filePath;
        @SerializedName("height")
        @Expose
        public Integer height;
        @SerializedName("iso_639_1")
        @Expose
        public Object iso6391;
        @SerializedName("vote_average")
        @Expose
        public Double voteAverage;
        @SerializedName("vote_count")
        @Expose
        public Integer voteCount;
        @SerializedName("width")
        @Expose
        public Integer width;

    }


    public static class PosterModel {

        @SerializedName("aspect_ratio")
        @Expose
        public Double aspectRatio;
        @SerializedName("file_path")
        @Expose
        public String filePath;
        @SerializedName("height")
        @Expose
        public Integer height;
        @SerializedName("iso_639_1")
        @Expose
        public Object iso6391;
        @SerializedName("vote_average")
        @Expose
        public Double voteAverage;
        @SerializedName("vote_count")
        @Expose
        public Integer voteCount;
        @SerializedName("width")
        @Expose
        public Integer width;



    }
}
