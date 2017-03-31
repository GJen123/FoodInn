package com.example.gjen.foodinn.Meal;

import android.graphics.drawable.Drawable;

import java.io.Serializable;

/**
 * Created by Lin on 2016/10/7.
 */
public class Meal implements Serializable {

    private String mealName = null;
    private String mealPrice = null;

    public void setMealName(String mealName){
        this.mealName = mealName;
    }
    public String getMealName(){
        return mealName;
    }

    public void setMealPrice (String mealPrice){
        this.mealPrice = mealPrice;
    }
    public String getMealPrice(){
        return mealPrice;
    }
}



