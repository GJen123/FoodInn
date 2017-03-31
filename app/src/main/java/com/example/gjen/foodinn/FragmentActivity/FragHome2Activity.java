package com.example.gjen.foodinn.FragmentActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;
import com.example.gjen.foodinn.Shop.ShopArrayAdapter2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragHome2Activity extends Fragment{

    View view;
    ListView lvShop;
    List<Shop> shopList = new ArrayList<>();
    ShopArrayAdapter2 shopArrayAdapter;
    private static final int UPDATE_SHOP_LIST = 1;
    private static final String SERVICE_URL = "http://140.134.26.71:58080/android-backend/webapi/shop/list";

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case UPDATE_SHOP_LIST: {
                    updateShopList();
                    break;
                }
            }
        }

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_home2, container,false);
        findviews();
        //導入Tab分頁的Fragment Layout
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    void findviews(){
        lvShop = (ListView)view.findViewById(R.id.listview_shop2);
        setShopListView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getShopListFromServer();
            }}).start();
    }

    void setShopListView(){
        shopArrayAdapter = new ShopArrayAdapter2(getActivity(), shopList);
        lvShop.setAdapter(shopArrayAdapter);
    }

    private void updateShopList() {
        shopArrayAdapter.addAll(shopList);
    }

    //get shoplist from server.
    void getShopListFromServer() {
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
                for(int i = 0; i < jsonObis.length()+2; i++) {
                    if(i==0||i==1){
                        Shop shop = new Shop();
                        shopList.add(shop);
                    }else{
                        Shop shop = new Shop();
                        JSONObject jsonObject = jsonObis.getJSONObject(i-2);
                        shop.setImgURL(jsonObject.getString("photo"));
                        shop.setImg(loadImageFromURL(shop.getImgURL()));
                        shop.setName(jsonObject.getString("shopName"));
                        shop.setTel(jsonObject.getString("phone"));
                        shopList.add(shop);
                    }
                }
                Message m = new Message();
                m.what = UPDATE_SHOP_LIST;
                handler.sendMessage(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Drawable loadImageFromURL(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable draw = Drawable.createFromStream(is, "src");
            return draw;
        } catch (Exception e) {
            return null;
        }
    }
}
