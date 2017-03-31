package com.example.gjen.foodinn.FragmentActivity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gjen.foodinn.Meal.MealAsyncTask;
import com.example.gjen.foodinn.R;
import com.example.gjen.foodinn.Shop.Shop;

public class FragMealMenuActivity extends Fragment {

    View view;
    ListView lvMealList;
    Shop shop = new Shop();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_frag_meal_menu, container,false);
        findviews();
        getBundle();
        MealAsyncTask mealAsyncTask = new MealAsyncTask(getActivity(), lvMealList, shop);
        mealAsyncTask.execute();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    void findviews(){
        lvMealList = (ListView)view.findViewById(R.id.listview_shop_meallist2);
    }

    void getBundle(){
        Bundle bundle = getArguments();
        shop.setImgURL(bundle.getString("shopImgUrl"));
        shop.setName(bundle.getString("shopName"));
        shop.setTel(bundle.getString("shopTel"));
        shop.setOpenTime(bundle.getString("shopOpenTime"));
        shop.setIntro(bundle.getString("shopIntro"));
        shop.setemail(bundle.getString("shopEmail"));
    }
}
