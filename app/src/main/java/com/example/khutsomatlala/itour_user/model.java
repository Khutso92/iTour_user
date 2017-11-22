package com.example.khutsomatlala.itour_user;

import java.io.Serializable;

/**
 * Created by Khutso Matlala on 8/7/2017.
 */

public class model implements Serializable {



    public String name;
    public String description;
    public String category;
    public String latitude;
    public String longtiude;
    public String call;


    public String urI;


    public model() {

    }

    public model(String name, String urI, String description, String category, String latitude, String longtiude) {
        this.urI = urI;
        this.name = name;
        this.description = description;
        this.category = category;
        this.latitude = latitude;
        this.longtiude = longtiude;


    }


    public String getName() {
        return name;
    }

    public String getUrI() {
        return urI;
    }


    public String getDescription() {
        return description;
    }

    public String getCategory(){
        return category;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public String getLongtiude()
    {
        return longtiude;
    }


    public String getCall() {
        return call;
    }
}
