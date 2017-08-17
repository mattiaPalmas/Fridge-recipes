package com.exerciseapp.mattiapalmas.fridgerecipes;


import android.content.res.Resources;

/**This class is the model of a list item inside the main activity.
 * Created by mattia palmas on 2017-08-11.
 */

public class ListItemModel{
    private String movieTitle;
    private String desc;
    private String movieImage;

    /**
     * Constructor that takes as parameters 2 Strings.
     * @param movieTitle is the string for the movie title.
     * @param desc is  the string for the movie description.
     * @param movieImage
     */
    public ListItemModel(String movieTitle, String desc, String movieImage) {
        this.movieTitle = movieTitle;
        this.desc = desc;
        this.movieImage = movieImage;
    }

    /**
     * @return the title of the movie
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * @return the description of the movie.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * @return the image resourse of the movie.
     */
    public String getMovieImage() {
        return movieImage;
    }

}
