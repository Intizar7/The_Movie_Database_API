package com.najimaddinova.moviesbyinteraktifkredi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class ErrorCollectionModel {

    @SerializedName("status_code")
    @Expose
    public Integer status_code;
    @SerializedName("status_message")
    @Expose
    public String status_message;
    @SerializedName("success")
    @Expose
    public Boolean success;
}
