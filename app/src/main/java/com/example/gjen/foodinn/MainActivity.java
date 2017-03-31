package com.example.gjen.foodinn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gjen.foodinn.FragmentActivity.FragAccountActivity;
import com.example.gjen.foodinn.FragmentActivity.FragClassificaitonActivity;
import com.example.gjen.foodinn.FragmentActivity.FragHome3Activity;
import com.example.gjen.foodinn.FragmentActivity.FragMapActivity;
import com.example.gjen.foodinn.FragmentActivity.FragRandomActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    LinearLayout llHome, llClass, llRandom, llMap, llAccount;
    private int currentIndex = 0;
    private Fragment mCurrentFrgment;
    private ArrayList<Fragment> fragmentArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findviews();
        initFragment();
    }
    private void findviews(){
        llHome = (LinearLayout)findViewById(R.id.linearLayoutHome);
        llHome.setOnClickListener(onClickListener);
        llHome.setTag(0);
        llClass = (LinearLayout)findViewById(R.id.linearLayoutClass);
        llClass.setOnClickListener(onClickListener);
        llClass.setTag(1);
        llRandom = (LinearLayout)findViewById(R.id.linearLayoutRandom);
        llRandom.setOnClickListener(onClickListener);
        llRandom.setTag(2);
        llMap = (LinearLayout)findViewById(R.id.linearLayoutMap);
        llMap.setOnClickListener(onClickListener);
        llMap.setTag(3);
        llAccount = (LinearLayout)findViewById(R.id.linearLayoutAcc);
        llAccount.setOnClickListener(onClickListener);
        llAccount.setTag(4);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            changeTab((Integer) view.getTag());
        }
    };

    private void initFragment(){
        fragmentArrayList = new ArrayList<Fragment>(5);
        fragmentArrayList.add(new FragHome3Activity());
        fragmentArrayList.add(new FragClassificaitonActivity());
        fragmentArrayList.add(new FragRandomActivity());
        fragmentArrayList.add(new FragMapActivity());
        fragmentArrayList.add(new FragAccountActivity());

        llHome.setSelected(true);
        changeTab(0);
    }

    private void changeTab(int index){
        for(Fragment fragment : fragmentArrayList){
            Log.i("abc", fragment.getClass().getName());
        }
        currentIndex = index;
        llHome.setSelected(index == 0);
        llClass.setSelected(index == 1);
        llRandom.setSelected(index == 2);
        llMap.setSelected(index == 3);
        llAccount.setSelected(index == 4);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        // *****
        // 判斷當前的fragment是否為空，不是則隱藏
        // *****
        if(mCurrentFrgment != null){
            ft.hide(mCurrentFrgment);
        }

        Fragment fragment = getSupportFragmentManager().findFragmentByTag(fragmentArrayList.get(currentIndex).getClass().getName());

        if(fragment == null){
            fragment = fragmentArrayList.get(index);
        }

        mCurrentFrgment = fragment;

        if(!fragment.isAdded()){
            ft.add(R.id.fragment, fragment, fragment.getClass().getName());
        }else{
            ft.show(fragment);
        }
        ft.commit();
    }
}
