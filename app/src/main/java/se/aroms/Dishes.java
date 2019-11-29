package se.aroms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.Serializable;

public class Dishes implements Serializable {

    private  int dish_image;
    private String dish_name;
    private  String cook_time;
    private String dish_price;

    public Dishes() {
    }

    public Dishes(int image_res, String dish_name, String cook_time, String dish_price){

        this.dish_image = image_res;
        this.dish_name = dish_name;
        this.cook_time = cook_time;
        this.dish_price = dish_price;
    }

    public int getDish_image() {
        return dish_image;
    }

    public String getDish_name() {
        return dish_name;
    }

    public String getCook_time() {
        return cook_time;
    }

    public String getDish_price() {
        return dish_price;
    }

}
