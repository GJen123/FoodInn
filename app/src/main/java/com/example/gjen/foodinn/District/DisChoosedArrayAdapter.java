package com.example.gjen.foodinn.District;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;
import com.example.gjen.foodinn.Shop.ShopArrayAdapter2;
import com.example.gjen.foodinn.Shop.ShopPhotoAsyncTask;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by GJen on 2017/5/9.
 */

public class DisChoosedArrayAdapter extends ArrayAdapter<Shop> {
    private static final int mResourceId = R.layout.list_view_home;  //自定的layout
    private LayoutInflater mInflater;

    Shop shop;
    List<Shop> shops = new ArrayList<Shop>();
    Context context;

    public DisChoosedArrayAdapter(@NonNull Context context, List<Shop> shops) {
        super(context, mResourceId, shops);
        this.context = context;
        this.shops = shops;
        mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        convertView = mInflater.inflate(mResourceId, parent, false);
        shop = shops.get(position);
        MyTag myTag = new MyTag();
        myTag.ivShopPic = (ImageView)convertView.findViewById(R.id.imageView);
        myTag.tvShopName = (TextView)convertView.findViewById(R.id.textView12);
        myTag.tvShopPhone = (TextView)convertView.findViewById(R.id.textView13);
        myTag.tvShopTime = (TextView)convertView.findViewById(R.id.textView14);

        myTag.ivShopPic.setImageResource(R.drawable.coffee);

        showImageByAsyncTask(myTag.ivShopPic, shop.getphotoUrl());

        myTag.tvShopName.setText(shop.getName());
        myTag.tvShopPhone.setText(shop.getPhone());
        myTag.tvShopTime.setText(shop.getOpenTime());
        return convertView;
    }

    public void showImageByAsyncTask(ImageView imageView, String url){
        new ShopPhotoAsyncTask(imageView,url).execute(url);
    }

    class MyTag{
        TextView tvShopName, tvShopPhone, tvShopTime;
        ImageView ivShopPic;
    }
}
