package com.example.gjen.foodinn;

import android.app.Service;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.gjen.foodinn.SQLiteDB.DBHelper;
import com.example.gjen.foodinn.SQLiteDB.ShopUtil;
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
 * Created by GJen on 2017/5/6.
 */

public class ShopService extends Service {
    private static final String SERVICE_URL = "http://140.134.26.71:58080/android-backend/webapi/shop/list";

    SQLiteDatabase db;
    Cursor cursor;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        setupDatabase();
        getShopListFromServer();
    }


    void setupDatabase(){
        DBHelper dbHelper=new DBHelper(this, ShopUtil.DATABASE_NAME,null, ShopUtil.VERSION);
        db=dbHelper.getWritableDatabase();
        Log.i("abc", "db setup success");
    }

    private void getShopListFromServer() {
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
                    JSONObject jsonObject = jsonObis.getJSONObject(i);
                    Shop shop = new Shop();
                    shop.setphotoUrl(jsonObject.getString("photo"));
                    shop.setName(jsonObject.getString("shopName"));
                    shop.setPhone(jsonObject.getString("phone"));
                    shop.setemail(jsonObject.getString("email"));
                    shop.setIntro(jsonObject.getString("intro"));
                    if(jsonObject.has("openTime")){
                        shop.setOpenTime(jsonObject.getString("openTime"));
                    }else{
                        shop.setOpenTime("無定時營業時間");
                    }
                    insertData(shop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void insertData(Shop shop){
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
        Log.i("abc", "shop " + shop.getName() + "add success");
    }
}
