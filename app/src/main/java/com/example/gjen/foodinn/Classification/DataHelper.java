package com.example.gjen.foodinn.Classification;

import com.example.gjen.foodinn.Classification.Type;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GJen on 2017/4/8.
 */

public class DataHelper implements Serializable {

    private List<Type> shops = new ArrayList<>();

    public DataHelper(List<Type> shops){
        this.shops = shops;
    }

    public List<Type> getShops(){
        return shops;
    }

}
