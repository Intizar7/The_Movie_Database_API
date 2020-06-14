package com.najimaddinova.moviesbyinteraktifkredi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreModel {

    @SerializedName("id")
    @Expose
    public Integer id;

    @SerializedName("name")
    @Expose
    public String name;

}
