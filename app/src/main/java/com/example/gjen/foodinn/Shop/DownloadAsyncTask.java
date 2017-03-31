package com.example.gjen.foodinn.Shop;

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
 * Created by GJen on 2017/3/6.
 */

public class DownloadAsyncTask extends AsyncTask<String, Void, List<Shop>> {

    Context context;
    ListView lv ;
    private static final String SERVICE_URL = "http://140.134.26.71:58080/android-backend/webapi/shop/list";
    List<Shop> shopList;

    public DownloadAsyncTask(Context context, ListView lv){
        this.context = context;
        this.lv = lv;
    }

    @Override
    protected List<Shop> doInBackground(String... strings) {
        shopList = getShopListFromServer();
        return shopList;
    }

    // *****
    // 在doInBackground結束後會執行onPostExecute
    // *****
    @Override
    protected void onPostExecute(final List<Shop> shops) {
        super.onPostExecute(shops);
        if(shops!=null || shops.size()!=0){
            ShopArrayAdapter2 shopArrayAdapter2 = new ShopArrayAdapter2(context, shops);
            lv.setAdapter(shopArrayAdapter2);
        }
    }

    private List<Shop> getShopListFromServer() {
        List<Shop> shopList = new ArrayList<Shop>();
        for(int i=0;i<2;i++){
            Shop shop = new Shop();
            shopList.add(shop);
        }
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
                    shop.setImgURL(jsonObject.getString("photo"));
                    shop.setName(jsonObject.getString("shopName"));
                    shop.setTel(jsonObject.getString("phone"));
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
