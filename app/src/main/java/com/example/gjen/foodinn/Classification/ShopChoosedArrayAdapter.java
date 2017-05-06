package com.example.gjen.foodinn.Classification;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;
import com.example.gjen.foodinn.Shop.ShopPhotoAsyncTask;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by GJen on 2017/3/2.
 */

public class ShopChoosedArrayAdapter extends ArrayAdapter<Shop> {

    private static final int mResourceId = R.layout.list_view_home;  //自定的layout
    private LayoutInflater mInflater;

    Shop shop;
    List<Shop> shops = new ArrayList<Shop>();
    Context context;

    public ShopChoosedArrayAdapter(Context context, List<Shop> shops) {
        super(context, mResourceId, shops);
        this.context = context;
        this.shops = shops;
        mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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

    public void showImageByAsyncTask(ImageView imageView,String url){
        new ShopPhotoAsyncTask(imageView,url).execute(url);
    }

    class MyTag{
        TextView tvShopName, tvShopPhone, tvShopTime;
        ImageView ivShopPic;
    }

    private Matrix getMatrix(Bitmap bmp){
        //轉換為圖片指定大小
        //獲得圖片的寬高
        int width = bmp.getWidth();
        int height = bmp.getHeight();
        // 設置想要的大小
        int newWidth = bmp.getWidth();
        int newHeight = 1000;
        // 計算缩放比例
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix參數
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return matrix;
    }

    private void myimageviewsize(ImageView imgid, int evenWidth, int evenHight) {
        // TODO 自動產生的方法 Stub
        ViewGroup.LayoutParams params = imgid.getLayoutParams();  //需import android.view.ViewGroup.LayoutParams;
        params.width = evenWidth;
        params.height = evenHight;
        imgid.setLayoutParams(params);
    }
}
