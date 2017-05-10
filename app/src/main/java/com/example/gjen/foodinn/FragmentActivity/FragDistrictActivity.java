package com.example.gjen.foodinn.FragmentActivity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.gjen.foodinn.R;

public class FragDistrictActivity extends Fragment {
    View view;
    String[] district = new String[]{"全部地區","大門","摩斯門","文華道","東門","翰林"};
    ListView lvDistrict;
    String url = "http://140.134.26.71:58080/android-backend/webapi/region/";
    String[] districtUrl = new String[]{"list","gate","MOS","WenHua","East","HanLin"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_district, container,false);
        lvDistrict = (ListView) view.findViewById(R.id.listview_district);
        setAdapter();
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
    }

    void setAdapter(){
        ArrayAdapter<String> adt=new ArrayAdapter<String>(getActivity(),R.layout.list_view_district,district);
        lvDistrict.setAdapter(adt);
        lvDistrict.setOnItemClickListener(onItemClickListener);
    }

    AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            String placeUrl = url + districtUrl[position];
            changePage(placeUrl);
        }
    };

    void changePage(String placeUrl){
        FragDisChoosedActivity fragDisChoosedActivity = new FragDisChoosedActivity();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("placeUrl", placeUrl);
        fragDisChoosedActivity.setArguments(bundle);
        ft.addToBackStack(FragDistrictActivity.class.getName());
        ft.replace(R.id.content, fragDisChoosedActivity);
        ft.commit();
    }
}
