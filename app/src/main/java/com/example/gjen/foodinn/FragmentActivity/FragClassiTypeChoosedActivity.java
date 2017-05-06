package com.example.gjen.foodinn.FragmentActivity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.gjen.foodinn.Classification.DataHelper;
import com.example.gjen.foodinn.Classification.ShopChoosedAsyncTask;
import com.example.gjen.foodinn.Classification.Type;
import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FragClassiTypeChoosedActivity extends Fragment {

    List<Type> shopChoosed = new ArrayList<Type>();
    DataHelper dh;
    ListView lvShop;
    View view;
    List<Shop> shopList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag_classi_type_choosed, container,false);
        findviews();
        Bundle bundle = getArguments();
        dh = (DataHelper) bundle.getSerializable("dh");
        shopChoosed = dh.getShops();
        for(Type type : shopChoosed){
            Log.i("abc", "typeChoose : " + type.getShopId());
        }
        ShopChoosedAsyncTask shopChoosedAsyncTask = new ShopChoosedAsyncTask(getActivity(), lvShop, shopChoosed);
        try {
            shopList = shopChoosedAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        lvShop.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String shopImgUrl = shopList.get(position).getphotoUrl();
                String shopName = shopList.get(position).getName();
                String shopTel = shopList.get(position).getPhone();
                String shopOpenTime = shopList.get(position).getOpenTime();
                String shopIntro = shopList.get(position).getIntro();
                String shopEmail = shopList.get(position).getemail();

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
                ft.addToBackStack(FragClassiTypeChoosedActivity.class.getName());
                ft.replace(R.id.content, fragMealMenuActivity);
                ft.commit();
            }
        });

        return view;
    }

    void findviews(){
        lvShop = (ListView) view.findViewById(R.id.listview_typeChoosedShop);
    }
}
