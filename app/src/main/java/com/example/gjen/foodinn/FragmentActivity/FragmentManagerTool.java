package com.example.gjen.foodinn.FragmentActivity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by GJen on 2017/3/15.
 */

public class FragmentManagerTool {

    /* initial Fragment */
    public static void init(Bundle savedInstanceState, FragmentManager fragmentManager, int layoutID, Fragment fragment) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (savedInstanceState == null) {
            transaction.add(layoutID, fragment).commit();
        }
    }

    /* replace Fragment */
    public static void replaceFragment(FragmentManager fragmentManager, int layout, Fragment f) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.replace(layout, f);
        ft.commit();
    }
}
