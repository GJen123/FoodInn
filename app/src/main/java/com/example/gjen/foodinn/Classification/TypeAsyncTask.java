package com.example.gjen.foodinn.Classification;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GJen on 2017/4/9.
 */

public class TypeAsyncTask extends AsyncTask<String, Void, List<Type>> {

    Context context;
    String strUrl = "http://140.134.26.71:58080/android-backend/webapi/type/list";

    public TypeAsyncTask(Context context){
        this.context = context;
    }

    @Override
    protected List<Type> doInBackground(String... strings) {
        List<Type> types;
        types = httpGetTypeList();
        return types;
    }

    private List<Type> httpGetTypeList() {
        List<Type> types = new ArrayList<>();
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
            Log.i("abc", "jsonString : " + jsonString);
            if (jsonObis.length() > 0) {
                for (int i = 0; i < jsonObis.length(); i++) {
                    JSONObject jsonObject = jsonObis.getJSONObject(i);
                    Type type = new Type();
                    int id = jsonObject.getInt("id");
                    int shopId = jsonObject.getInt("shopId");
                    int intType = jsonObject.getInt("type");
                    type.setId(id);
                    type.setShopId(shopId);
                    type.setType(intType);
                    types.add(type);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return types;
    }
}
