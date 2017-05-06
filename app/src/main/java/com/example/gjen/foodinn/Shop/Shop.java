package com.example.gjen.foodinn.Shop;


import android.graphics.drawable.Drawable;

import java.util.Date;

/**
 * Created by Lin on 2016/9/13.
 */
public class Shop {

    private int id;
    private String email;
    private String intro;
    private Double latitude;
    private Double longitude;
    private String openTime;
    private String password;
    private String phone;
    private String photoUrl;
    private String name;
    private Drawable photo;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getemail() {
        return email;
    }
    public void setemail(String email) {
        this.email = email;
    }

    public void setIntro (String intro){
        this.intro = intro;
    }
    public String getIntro(){
        return intro;
    }

    public Double getLatitude() {
        return latitude;
    }
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getOpenTime() {
        return openTime;
    }
    public void setOpenTime(String openTime) {
        this.openTime = openTime;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getphotoUrl() {
        return photoUrl;
    }
    public void setphotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Drawable getPhoto(){
        return photo;
    }
    public void setPhoto(Drawable photo){
        this.photo = photo;
    }

}
