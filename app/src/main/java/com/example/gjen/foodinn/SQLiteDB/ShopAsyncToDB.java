package com.example.gjen.foodinn.SQLiteDB;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

import com.example.gjen.foodinn.Shop.Shop;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GJen on 2017/4/21.
 */

public class ShopAsyncToDB extends AsyncTask<String, Void, List<Shop>> {

    private static final String SERVICE_URL = "http://140.134.26.71:58080/android-backend/webapi/shop/list";
    List<Shop> shopList;
    SQLiteDatabase db;

    public ShopAsyncToDB(SQLiteDatabase db){
        this.db = db;
    }

    @Override
    protected List<Shop> doInBackground(String... strings) {
        shopList = getShopListFromServer();
        return shopList;
    }

    // After doInBackground
    @Override
    protected void onPostExecute(List<Shop> shops) {
        super.onPostExecute(shops);
        insertData(shopList);
    }

    private List<Shop> getShopListFromServer() {
        List<Shop> shopList = new ArrayList<Shop>();
        HttpURLConnection conn = null;
        try {
            // 建立連線
            URL url = new URL(SERVICE_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.connect();
            // 讀取資料
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            String jsonString = reader.readLine();
            reader.close();
            // 解析 json
            JSONArray jsonObis = new JSONArray(jsonString);
            if (jsonObis.length() > 0) {
                for (int i = 0; i < jsonObis.length(); i++) {
                    if(i==10){
                        break;
                    }
                    JSONObject jsonObject = jsonObis.getJSONObject(i);
                    Shop shop = new Shop();
                    shop.setId(jsonObject.getInt("ID"));
                    shop.setemail(jsonObject.getString("email"));
                    shop.setIntro(jsonObject.getString("intro"));
                    shop.setLatitude(jsonObject.getDouble("latitude"));
                    shop.setLongitude(jsonObject.getDouble("longitude"));
                    if(jsonObject.has("openTime")){
                        shop.setOpenTime(jsonObject.getString("openTime"));
                    }else{
                        shop.setOpenTime("無定時營業時間");
                    }
                    shop.setPassword(jsonObject.getString("password"));
                    shop.setPhone(jsonObject.getString("phone"));
                    shop.setphotoUrl(jsonObject.getString("photo"));
                    shop.setName(jsonObject.getString("shopName"));
                    shopList.add(shop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopList;
    }

    private void insertData(List<Shop> shopList){
        for(Shop shop : shopList){
            ContentValues values=new ContentValues();
            values.put("id", shop.getId());
            values.put("email", shop.getemail());
            values.put("intro", shop.getIntro());
            values.put("latitude", shop.getLatitude());
            values.put("longtitude", shop.getLongitude());
            values.put("openTime", shop.getOpenTime());
            values.put("password", shop.getPassword());
            values.put("phone", shop.getPhone());
            values.put("photoUrl", shop.getphotoUrl());
            values.put("name", shop.getName());
            db.insert(ShopUtil.TABLE_NAME, null, values);
            Log.i("abc", "db " + shop.getName() + " insert success");
        }
    }
}
