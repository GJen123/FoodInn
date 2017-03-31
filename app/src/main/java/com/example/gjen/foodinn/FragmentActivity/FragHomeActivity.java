package com.example.gjen.foodinn.FragmentActivity;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;

import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;
import com.example.gjen.foodinn.Shop.ShopArrayAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FragHomeActivity extends Fragment {

    ListView lvShop;
    View view;
    List<Shop> shopList = new ArrayList<>();
    ShopArrayAdapter shopArrayAdapter;
    ScrollView scrollView;

    private static final String SERVICE_URL = "http://140.134.26.71:58080/android-backend/webapi/shop/list";
    private static final int UPDATE_SHOP_LIST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        shopList.clear();
        view = inflater.inflate(R.layout.activity_frag_home, container,false);
        findviews();
        setShopListView();
        new Thread(new Runnable() {
            @Override
            public void run() {
                getShopListFromServer();
            }}).start();
//        handler1.post(runnable);
        //導入Tab分頁的Fragment Layout
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
//        //取得ImageView元件並帶入指定圖片
//        ImageView mImg = (ImageView) getActivity().findViewById(R.id.img);
//        mImg.setImageResource(R.drawable.lesson1_img);
    }

    public void findviews(){
        lvShop = (ListView) view.findViewById(R.id.listview_shop);
        scrollView = (ScrollView)view.findViewById(R.id.scrollview);
    }

    public void setShopListView(){
        shopArrayAdapter = new ShopArrayAdapter(getActivity(), shopList);
        lvShop.setAdapter(shopArrayAdapter);
    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, AbsListView.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
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
                for(int i = 0; i < jsonObis.length(); i++) {
                    Shop shop = new Shop();
//                    shop.setImg(loadImageFromURL(shop.getImgURL()));
                    shop.setName(((JSONObject) jsonObis.get(i)).getString("shopName"));
                    shop.setTel(((JSONObject) jsonObis.get(i)).getString("phone"));
                    shopList.add(shop);
                    handler1.post(runnable2);
                }
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

    private Handler handler1 = new Handler();

    private Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            setShopListView();
//            setListViewHeightBasedOnChildren(lvShop);
        }
    };

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateShopList();
        }
    };

    private void updateShopList() {
        shopArrayAdapter.addAll(shopList);
        setListViewHeightBasedOnChildren(lvShop);
    }

}
