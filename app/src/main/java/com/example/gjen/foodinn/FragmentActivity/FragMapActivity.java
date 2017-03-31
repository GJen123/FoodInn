package com.example.gjen.foodinn.FragmentActivity;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gjen.foodinn.R;

public class FragMapActivity extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        Log.i("abc", "Map on create view");
        //導入Tab分頁的Fragment Layout
        return inflater.inflate(R.layout.activity_frag_map, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        String text = "This is Map";
        //取得TextView元件並帶入text字串
        TextView mText = (TextView) getView().findViewById(R.id.textView5);
        mText.setText(text);

//        //取得ImageView元件並帶入指定圖片
//        ImageView mImg = (ImageView) getActivity().findViewById(R.id.img);
//        mImg.setImageResource(R.drawable.lesson1_img);
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i("abc", "Map on pause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.i("abc", "Map on stop");
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.i("abc", "Map on hidden changed");
    }
}
