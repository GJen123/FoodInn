package com.example.gjen.foodinn.Shop;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by GJen on 2017/3/6.
 */

public class ShopPhotoAsyncTask extends AsyncTask<String, Void, Drawable> {

    private ImageView iv;
    private String url;

    public ShopPhotoAsyncTask(ImageView iv, String url){
        this.iv = iv;
        this.url = url;
    }

    @Override
    protected Drawable doInBackground(String... strings) {
        String murl = strings[0];
        Drawable drawable = loadImageFromURL(murl);
        return drawable;
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        super.onPostExecute(drawable);
        iv.setImageDrawable(drawable);
    }

    private Drawable loadImageFromURL(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable draw = Drawable.createFromStream(is, "src");
            return draw;
        } catch (Exception e) {
            return null;
        }
    }
}
