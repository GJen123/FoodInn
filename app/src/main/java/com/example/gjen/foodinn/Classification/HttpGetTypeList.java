package com.example.gjen.foodinn.Classification;

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
 * Created by GJen on 2017/4/8.
 */

public class HttpGetTypeList {
    List<Type> types = new ArrayList<Type>();
    String strUrl = "http://140.134.26.71:58080/android-backend/webapi/type/list";

    public List<Type> httpGet(){
        HttpURLConnection conn = null;
        try {
            // 建立連線
            URL url = new URL(strUrl);
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
                    Type type = new Type();
                    int id = jsonObject.getInt("id");
                    int shopId = jsonObject.getInt("shopId");
                    int intType = jsonObject.getInt("type");
                    type.setId(id);
                    type.setShopId(shopId);
                    type.setShopId(intType);
                    types.add(type);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }
}
