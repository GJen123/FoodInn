package com.example.gjen.foodinn.District;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ListView;

import com.example.gjen.foodinn.Shop.Shop;
import com.example.gjen.foodinn.Shop.ShopArrayAdapter2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GJen on 2017/5/9.
 */

public class DisChoosedAsyncTask extends AsyncTask<String, Void, List<Shop>> {
    Context context;
    ListView lv;
    String placeUrl;
    List<Shop> shopList;

    public DisChoosedAsyncTask (Context context, ListView lv, String placeUrl){
        this.context = context;
        this.lv = lv;
        this.placeUrl = placeUrl;
    }
    @Override
    protected List<Shop> doInBackground(String... strings) {
        shopList = getShopListFromServer();
        return shopList;
    }

    @Override
    protected void onPostExecute(List<Shop> shops) {
        super.onPostExecute(shops);
        if(shops!=null || shops.size()!=0){
            DisChoosedArrayAdapter disChoosedArrayAdapter = new DisChoosedArrayAdapter(context, shops);
            lv.setAdapter(disChoosedArrayAdapter);
        }
    }

    private List<Shop> getShopListFromServer() {
        List<Shop> shopList = new ArrayList<Shop>();
        HttpURLConnection conn = null;
        try {
            // 建立連線
            URL url = new URL(placeUrl);
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

                    shopList.add(shop);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return shopList;
    }
}
