package com.example.gjen.foodinn.Meal;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gjen.foodinn.Meal.Meal;
import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;
import com.example.gjen.foodinn.Shop.ShopPhotoAsyncTask;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by GJen on 2017/3/11.
 */

public class MealArrayAdapter extends ArrayAdapter<Meal> {

    private static final int mResourceId = R.layout.list_view_meal_list;  //自定的layout
    private LayoutInflater mInflater;

    Meal meal;
    List<Meal> meals = new ArrayList<Meal>();

    Shop shop = new Shop();

    public MealArrayAdapter(Context context, List<Meal> meals, Shop shop) {
        super(context, mResourceId, meals);
        this.meals = meals;
        mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
        this.shop = shop;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(position == 0){
            convertView = mInflater.inflate(R.layout.list_view_meal_list_first, parent, false);
            FirstTag fTag = new FirstTag();
            fTag.ivShopPic = (ImageView)convertView.findViewById(R.id.imageView4);
            fTag.tvShopName = (TextView)convertView.findViewById(R.id.textView15);
            fTag.tvShopTel = (TextView)convertView.findViewById(R.id.textView16);
            fTag.tvShopOpenTime = (TextView)convertView.findViewById(R.id.textView17);
            fTag.tvShopIntro = (TextView)convertView.findViewById(R.id.textView18);

            showImageByAsyncTask(fTag.ivShopPic, shop.getImgURL());

            fTag.tvShopName.setText(shop.getName());
            fTag.tvShopTel.setText(shop.getTel());
            fTag.tvShopOpenTime.setText(shop.getOpenTime());
            fTag.tvShopIntro.setText(shop.getIntro());
        }else{
            convertView = mInflater.inflate(mResourceId, parent, false);
            meal = meals.get(position);
            MyTag myTag = new MyTag();
            myTag.tvMealName = (TextView)convertView.findViewById(R.id.textView19);
            myTag.tvMealPrice = (TextView)convertView.findViewById(R.id.textView20);

            myTag.tvMealName.setText(meal.getMealName());
            myTag.tvMealPrice.setText(meal.getMealPrice());
        }
        return convertView;
    }

    class MyTag{
        TextView tvMealName, tvMealPrice;
    }

    class FirstTag{
        ImageView ivShopPic;
        TextView tvShopName, tvShopTel, tvShopOpenTime, tvShopIntro, tvShopEmail;
    }

    public void showImageByAsyncTask(ImageView imageView,String url){
        new ShopPhotoAsyncTask(imageView,url).execute(url);
    }
}
