package com.example.gjen.foodinn.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gjen.foodinn.Shop.DownloadAsyncTask;
import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FragHome3Activity extends Fragment {

    ListView lvShop;
    List<Shop> shops;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i("abc", "Home on create view");
        View view;
        view = inflater.inflate(R.layout.activity_frag_home3, container,false);
        lvShop = (ListView)view.findViewById(R.id.listview_shop3);
        final DownloadAsyncTask downloadAsyncTask = new DownloadAsyncTask(getActivity(), lvShop);
        try {
            shops = downloadAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        lvShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if(position == 0 || position == 1){

                }else{
                    String shopImgUrl = shops.get(position).getImgURL();
                    String shopName = shops.get(position).getName();
                    String shopTel = shops.get(position).getTel();
                    String shopOpenTime = shops.get(position).getOpenTime();
                    String shopIntro = shops.get(position).getIntro();
                    String shopEmail = shops.get(position).getemail();

                    Bundle bundle = new Bundle();
                    bundle.putString("shopImgUrl", shopImgUrl);
                    bundle.putString("shopName", shopName);
                    bundle.putString("shopTel", shopTel);
                    bundle.putString("shopOpenTime", shopOpenTime);
                    bundle.putString("shopIntro", shopIntro);
                    bundle.putString("shopEmail", shopEmail);

                    FragMealMenuActivity fragMealMenuActivity = new FragMealMenuActivity();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    fragMealMenuActivity.setArguments(bundle);
                    ft.replace(R.id.content, fragMealMenuActivity);
                    ft.commit();
                }

            }
        });

        //導入Tab分頁的Fragment Layout
        return view;
    }
}
