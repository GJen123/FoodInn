package com.example.gjen.foodinn.Shop;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;

import java.util.List;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

/**
 * Created by GJen on 2017/3/2.
 */

public class ShopArrayAdapter extends ArrayAdapter<Shop> {

    private static final int mResourceId = R.layout.list_view_home;  //自定的layout
    private LayoutInflater mInflater;

    public ShopArrayAdapter(Context context, List<Shop> shops) {
        super(context, mResourceId, shops);
        mInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Shop shop = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(mResourceId, parent, false);
            viewHolder.tvShopName = (TextView) convertView.findViewById(R.id.textView12);
            viewHolder.tvShopTel = (TextView)convertView.findViewById(R.id.textView13);
            viewHolder.tvShopTime = (TextView)convertView.findViewById(R.id.textView14);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }



        viewHolder.tvShopName.setText(shop.getName());
        viewHolder.tvShopTel.setText(shop.getTel());
//        viewHolder.tvShopTime.setText(shop.getTime());

        return convertView;
    }

    class ViewHolder {
        ImageView ivPic;
        TextView tvShopName;
        TextView tvShopTel;
        TextView tvShopTime;
    }
}
