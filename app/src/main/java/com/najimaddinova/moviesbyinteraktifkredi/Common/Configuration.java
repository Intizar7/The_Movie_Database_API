package com.najimaddinova.moviesbyinteraktifkredi.Common;

import com.najimaddinova.moviesbyinteraktifkredi.Enum.ImageSizes;

public class Configuration {

    private static String ImageBaseUrl = "http://image.tmdb.org/t/p/";
    private static String ThumbSize = "w92";
    private static String PosterSize = "w500";

    //resmler için bir baseurl dönüyor
    public static String getImageBaseUrl(ImageSizes size, String imagePath) {
        String imageSize;
        if (size == ImageSizes.THUMB) {
            imageSize = Configuration.ThumbSize;
        } else {
            imageSize = Configuration.PosterSize;
        }

        return Configuration.ImageBaseUrl + "/" + imageSize + "/" + imagePath;
    }

}
