package com.najimaddinova.moviesbyinteraktifkredi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ConfigurationModel extends ErrorCollectionModel {
    @SerializedName("images")
    @Expose
    private Images images;
    @SerializedName("change_keys")
    @Expose
    private List<String> changeKeys = null;

    public Images getImages() {
        return images;
    }

    public void setImages(Images images) {
        this.images = images;
    }

    public List<String> getChangeKeys() {
        return changeKeys;
    }

    public void setChangeKeys(List<String> changeKeys) {
        this.changeKeys = changeKeys;
    }
    public class Images{
        @SerializedName("base_url")
        @Expose
        private String baseUrl;
        @SerializedName("secure_base_url")
        @Expose
        private String secureBaseUrl;
        @SerializedName("backdrop_sizes")
        @Expose
        private List<String> backdropSizes = null;
        @SerializedName("logo_sizes")
        @Expose
        private List<String> logoSizes = null;
        @SerializedName("poster_sizes")
        @Expose
        private List<String> posterSizes = null;
        @SerializedName("profile_sizes")
        @Expose
        private List<String> profileSizes = null;
        @SerializedName("still_sizes")
        @Expose
        private List<String> stillSizes = null;

    }
}
