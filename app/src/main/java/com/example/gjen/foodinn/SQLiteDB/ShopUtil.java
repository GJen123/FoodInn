package com.example.gjen.foodinn.SQLiteDB;

/**
 * Created by GJen on 2017/4/7.
 */

public class ShopUtil {
    public static final int VERSION=1;
    public static final String TABLE_NAME="ShopTable";
    public static final String DATABASE_NAME="FoodInnDB.db";
    public static final String SQL_CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+"  (id, email, intro, latitude, longitude, openTime, password, phone, photoUrl, name)";
}
