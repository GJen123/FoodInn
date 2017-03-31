package com.example.gjen.foodinn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.gjen.foodinn.FragmentActivity.FragAccountActivity;
import com.example.gjen.foodinn.FragmentActivity.FragClassificaitonActivity;
import com.example.gjen.foodinn.FragmentActivity.FragHome3Activity;
import com.example.gjen.foodinn.FragmentActivity.FragMapActivity;
import com.example.gjen.foodinn.FragmentActivity.FragRandomActivity;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startContent();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void startContent(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, new FragHome3Activity());
        ft.commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        Fragment selectedFrag = null;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    selectedFrag = new FragHome3Activity();
                    break;
                case R.id.navigation_classification:
                    selectedFrag = new FragClassificaitonActivity();
                    break;
                case R.id.navigation_random:
                    selectedFrag = new FragRandomActivity();
                    break;
                case R.id.navigation_map:
                    selectedFrag = new FragMapActivity();
                    break;
                case R.id.navigation_account:
                    selectedFrag = new FragAccountActivity();
                    break;
            }
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content, selectedFrag);
            ft.commit();
            return true;
        }
    };

}
