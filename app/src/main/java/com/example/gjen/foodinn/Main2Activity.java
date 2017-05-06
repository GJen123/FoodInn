package com.example.gjen.foodinn;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.gjen.foodinn.Classification.DataHelper;
import com.example.gjen.foodinn.Classification.Type;
import com.example.gjen.foodinn.Classification.TypeAsyncTask;
import com.example.gjen.foodinn.FragmentActivity.FragClassiTypeChoosedActivity;
import com.example.gjen.foodinn.FragmentActivity.FragDistrictActivity;
import com.example.gjen.foodinn.SQLiteDB.DBHelper;
import com.example.gjen.foodinn.SQLiteDB.ShopAsyncToDB;
import com.example.gjen.foodinn.SQLiteDB.ShopUtil;
import com.example.gjen.foodinn.FragmentActivity.FragAccountActivity;
import com.example.gjen.foodinn.FragmentActivity.FragClassificaitonActivity;
import com.example.gjen.foodinn.FragmentActivity.FragHome3Activity;
import com.example.gjen.foodinn.FragmentActivity.FragMapActivity;
import com.example.gjen.foodinn.FragmentActivity.FragRandomActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Main2Activity extends AppCompatActivity {

    SQLiteDatabase db;
    Cursor cursor;
    List<Type> types = new ArrayList<>();

//    @Override
//    protected void onStart() {
//        super.onStart();
//        setupDatabase();
//        ShopAsyncToDB shopAsyncToDB = new ShopAsyncToDB(db);
//        shopAsyncToDB.execute();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        startContent();
        getTypesList();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        Drawable drawableHome = getResources().getDrawable(R.drawable.navhome);
//        navigation.getMenu().getItem(R.id.navigation_classification).setIcon(drawableHome);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void startContent(){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, new FragHome3Activity());
        ft.commit();
    }

//    void setupDatabase(){
//        DBHelper dbHelper=new DBHelper(Main2Activity.this, ShopUtil.DATABASE_NAME,null, ShopUtil.VERSION);
//        db=dbHelper.getWritableDatabase();
//        Log.i("abc", "db setup success");
//    }

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
                    selectedFrag = new FragDistrictActivity();
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
    public void onBtnTogether(View v){
        List<Type> shopChoosed;
        shopChoosed = getShopFromType(14);
        changePage(shopChoosed);
        Toast.makeText(this, "onBtnFriend", Toast.LENGTH_SHORT);
    }
    public void onBtnConvenient(View v){
        List<Type> shopChoosed;
        shopChoosed = getShopFromType(11);
        changePage(shopChoosed);
        Toast.makeText(Main2Activity.this, "onBtnConvenient", Toast.LENGTH_SHORT);
    }
    public void onBtnSupper(View v){
        List<Type> shopChoosed;
        shopChoosed = getShopFromType(12);
        changePage(shopChoosed);
        Toast.makeText(Main2Activity.this, "onBtnSupper", Toast.LENGTH_SHORT);
    }
    public void onBtnNightMarket(View v){
        List<Type> shopChoosed;
        shopChoosed = getShopFromType(7);
        changePage(shopChoosed);
        Toast.makeText(Main2Activity.this, "onBtnNightMarket", Toast.LENGTH_SHORT);
    }
    public void onBtnDrink(View v){
        List<Type> shopChoosed;
        shopChoosed = getShopFromType(4);
        changePage(shopChoosed);
        Toast.makeText(Main2Activity.this, "onBtnDrink", Toast.LENGTH_SHORT);
    }
    public void onBtnShopMap(View v){
        FragMapActivity fragMapActivity = new FragMapActivity();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content, fragMapActivity);
        ft.commit();
        Toast.makeText(Main2Activity.this, "onBtnShopMap", Toast.LENGTH_SHORT);
    }

    private void changePage(List<Type> shopChoosed){
        Bundle bundle = new Bundle();
        DataHelper dh = new DataHelper(shopChoosed);
        bundle.putSerializable("dh", dh);

        FragClassiTypeChoosedActivity fragClassiTypeChoosedActivity = new FragClassiTypeChoosedActivity();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        fragClassiTypeChoosedActivity.setArguments(bundle);
        ft.addToBackStack(FragClassificaitonActivity.class.getName());
        ft.replace(R.id.content, fragClassiTypeChoosedActivity);
        ft.commit();
    }

    void getTypesList(){
        TypeAsyncTask typeAsyncTask = new TypeAsyncTask(this);
        try {
            types = typeAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private List<Type> getShopFromType(int typeNum){
        List<Type> shopChoosed = new ArrayList<Type>();
        for(Type type : types){
            if(typeNum == type.getType()){
                shopChoosed.add(type);
            }
        }
        return shopChoosed;
    }
}
