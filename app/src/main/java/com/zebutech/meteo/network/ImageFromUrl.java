package com.zebutech.meteo.network;

import android.graphics.Bitmap;
import android.os.Handler;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import android.graphics.BitmapRegionDecoder;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import java.net.MalformedURLException;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.InputStream;
/*
    RECUPERATION D'IMAGE DEPUIS UN URL POUR
    AFFICHER DANS UNE IMAGEVIEW
 */
public class ImageFromUrl extends AsyncTask
{
    private String url;
    private ImageView img;
    public ImageFromUrl(String new_url, ImageView wv){
        ///initialisation d'url et imageView///
        this.url = new_url;
        this.img = wv;
    }

    @Override
    protected Bitmap doInBackground(Object[] p1){
        String imageURL = url;
        Bitmap bitmap = null;
        try {
            ///Téléchargement d'image depuis l'URL
            InputStream input = new java.net.URL(imageURL).openStream();
            ///Décode en Bitmap
            bitmap = BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }




    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Object result){
        ///Si different de null, Afficher l'image
        if(result != null) img.setImageBitmap((Bitmap)result);    
    }

}
    
