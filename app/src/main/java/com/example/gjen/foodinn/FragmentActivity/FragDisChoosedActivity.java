package com.example.gjen.foodinn.FragmentActivity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gjen.foodinn.District.DisChoosedAsyncTask;
import com.example.gjen.foodinn.R;

public class FragDisChoosedActivity extends Fragment {
    View view;
    String placeUrl = null;
    ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_frag_dis_choosed, container,false);
        lv = (ListView) view.findViewById(R.id.listview_district_choosed);
        getBundle();
        DisChoosedAsyncTask disChoosedAsyncTask = new DisChoosedAsyncTask(getActivity(), lv, placeUrl);
        disChoosedAsyncTask.execute();

        return view;
    }

    void getBundle(){
        Bundle bundle = getArguments();
        placeUrl = bundle.getString("placeUrl");
    }
}
