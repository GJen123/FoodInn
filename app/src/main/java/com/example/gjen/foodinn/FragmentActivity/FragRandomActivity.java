package com.example.gjen.foodinn.FragmentActivity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.gjen.foodinn.R;

public class FragRandomActivity extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.i("abc", "random on create");
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_frag_random, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("abc", "Random on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("abc", "Random on stop");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("abc", "Random on hidden changed");
    }
}
