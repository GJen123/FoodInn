package com.example.gjen.foodinn.Classification;

/**
 * Created by GJen on 2017/4/7.
 */

public class Type {
    private int id;
    private int shopId;
    private int type;
    String[] strTypes = {"麵", "飯", "粥", "飲料", "冰品甜品", "炸物", "夜市小吃", "火鍋", "西式餐點", "麻辣", "便當", "宵夜", "早餐", "朋友聚會"};
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    public void setShopId(int shopId){
        this.shopId = shopId;
    }
    public int getShopId(){
        return shopId;
    }
    public void setType(int type){
        this.type = type;
    }
    public int getType(){
        return type;
    }
    public String getStrType(int type){
        type+=1;
        String strType = null;
        switch (type){
            case 1:
                strType = strTypes[type];
                break;
            case 2:
                strType = strTypes[type];
                break;
            case 3:
                strType = strTypes[type];
                break;
            case 4:
                strType = strTypes[type];
                break;
            case 5:
                strType = strTypes[type];
                break;
            case 6:
                strType = strTypes[type];
                break;
            case 7:
                strType = strTypes[type];
                break;
            case 8:
                strType = strTypes[type];
                break;
            case 9:
                strType = strTypes[type];
                break;
            case 10:
                strType = strTypes[type];
                break;
            case 11:
                strType = strTypes[type];
                break;
            case 12:
                strType = strTypes[type];
                break;
            case 13:
                strType = strTypes[type];
                break;
            case 14:
                strType = strTypes[type];
                break;
        }
        return strType;
    }

}
