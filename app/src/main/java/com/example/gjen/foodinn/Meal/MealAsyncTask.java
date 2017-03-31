package com.example.gjen.foodinn.Meal;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

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
 * Created by GJen on 2017/3/11.
 */

public class MealAsyncTask extends AsyncTask<String, Void, List<Meal>> {

    Context context;
    ListView lv;
    Shop shop;

    public MealAsyncTask(Context context, ListView lv, Shop shop){
        this.context = context;
        this.lv = lv;
        this.shop = shop;
    }

    @Override
    protected List<Meal> doInBackground(String... strings) {
        List<Meal> mealList;
        mealList = getMealDetail();
        return mealList;
    }

    @Override
    protected void onPostExecute(List<Meal> meals) {
        super.onPostExecute(meals);
        if(meals!=null || meals.size()!=0){
            MealArrayAdapter mealArrayAdapter = new MealArrayAdapter(context, meals, shop);
            lv.setAdapter(mealArrayAdapter);
        }
    }

    private List<Meal> getMealDetail() {
        List<Meal> mealList = new ArrayList<Meal>();
        HttpURLConnection conn = null;
        String mealUrl = "http://140.134.26.71:58080/android-backend/webapi/menu/email/";
        String shopEmail = shop.getemail();
        mealUrl += shopEmail;
        try {
            // 建立連線
            URL url = new URL(mealUrl);
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
                    Meal meal=new Meal();
                    meal.setMealName(jsonObject.getString("menuName"));
                    meal.setMealPrice("$ "+jsonObject.getString("menuPrice"));
                    mealList.add(meal);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mealList;
    }
}
