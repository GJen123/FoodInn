package com.example.gjen.foodinn.FragmentActivity;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.gjen.foodinn.Classification.DataHelper;
import com.example.gjen.foodinn.Classification.Type;
import com.example.gjen.foodinn.Classification.TypeAsyncTask;
import com.example.gjen.foodinn.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class FragClassificaitonActivity extends Fragment {

    Button btnNoodle, btnRice, btnPor, btnDrink, btnDessert, btnFried, btnNightMar, btnHotPot, btnPastry, btnSpicy, btnConve, btnSupper, btnBreakFirst, btnTogether;
    View view;
    List<Type> types = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.activity_frag_classificaiton, container,false);
        findviews();
        TypeAsyncTask typeAsyncTask = new TypeAsyncTask(getActivity());
        try {
            types = typeAsyncTask.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return view;
    }

    void findviews(){
        btnNoodle = (Button) view.findViewById(R.id.button3);
        btnNoodle.setOnClickListener(onClickListener);
        btnRice = (Button) view.findViewById(R.id.button4);
        btnRice.setOnClickListener(onClickListener);
        btnPor = (Button) view.findViewById(R.id.button5);
        btnPor.setOnClickListener(onClickListener);
        btnDrink = (Button) view.findViewById(R.id.button6);
        btnDrink.setOnClickListener(onClickListener);
        btnDessert = (Button) view.findViewById(R.id.button7);
        btnDessert.setOnClickListener(onClickListener);
        btnFried = (Button) view.findViewById(R.id.button8);
        btnFried.setOnClickListener(onClickListener);
        btnNightMar = (Button) view.findViewById(R.id.button9);
        btnNightMar.setOnClickListener(onClickListener);
        btnHotPot = (Button) view.findViewById(R.id.button10);
        btnHotPot.setOnClickListener(onClickListener);
        btnPastry = (Button) view.findViewById(R.id.button11);
        btnPastry.setOnClickListener(onClickListener);
        btnSpicy = (Button) view.findViewById(R.id.button12);
        btnSpicy.setOnClickListener(onClickListener);
        btnConve = (Button) view.findViewById(R.id.button13);
        btnConve.setOnClickListener(onClickListener);
        btnSupper = (Button) view.findViewById(R.id.button14);
        btnSupper.setOnClickListener(onClickListener);
        btnBreakFirst = (Button) view.findViewById(R.id.button15);
        btnBreakFirst.setOnClickListener(onClickListener);
        btnTogether = (Button) view.findViewById(R.id.button16);
        btnTogether.setOnClickListener(onClickListener);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }



    View.OnClickListener onClickListener = new View.OnClickListener() {
        int typeNumber = 0;
        List<Type> shopChoosed = new ArrayList<Type>();
        @Override
        public void onClick(View view) {
            typeNumber = getTypeNum(view.getId());
            shopChoosed = getShopFromType(typeNumber);
            changePage(shopChoosed);
        }
    };

    private int getTypeNum(int id){
        int typeNumber = 0;
        switch (id){
            case R.id.button3:
                // type = 1
                typeNumber = 1;
                break;
            case R.id.button4:
                // type = 2
                typeNumber = 2;
                break;
            case R.id.button5:
                // type = 3
                typeNumber = 3;
                break;
            case R.id.button6:
                // type = 4
                typeNumber = 4;
                break;
            case R.id.button7:
                // type = 5
                typeNumber = 5;
                break;
            case R.id.button8:
                // type = 6
                typeNumber = 6;
                break;
            case R.id.button9:
                // type = 7
                typeNumber = 7;
                break;
            case R.id.button10:
                // type = 8
                typeNumber = 8;
                break;
            case R.id.button11:
                // type = 9
                typeNumber = 9;
                break;
            case R.id.button12:
                // type = 10
                typeNumber = 10;
                break;
            case R.id.button13:
                // type = 11
                typeNumber = 11;
                break;
            case R.id.button14:
                // type = 12
                typeNumber = 12;
                break;
            case R.id.button15:
                // type = 13
                typeNumber = 13;
                break;
            case R.id.button16:
                // type = 14
                typeNumber = 14;
                break;
        }
        return typeNumber;
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

    private void changePage(List<Type> shopChoosed){
        Bundle bundle = new Bundle();
        DataHelper dh = new DataHelper(shopChoosed);
        bundle.putSerializable("dh", dh);

        FragClassiTypeChoosedActivity fragClassiTypeChoosedActivity = new FragClassiTypeChoosedActivity();
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        fragClassiTypeChoosedActivity.setArguments(bundle);
        ft.addToBackStack(FragClassificaitonActivity.class.getName());
        ft.replace(R.id.content, fragClassiTypeChoosedActivity);
        ft.commit();
    }
}
