package com.zebutech.meteo.network;
import android.graphics.Bitmap;
import android.os.Handler;
import java.io.InputStream;
import java.io.IOException;
import java.net.URL;
import android.graphics.BitmapRegionDecoder;
import android.graphics.BitmapFactory;
import android.widget.ImageView;

public class FetchImage extends Thread
{
    String URL;
    Bitmap bitmap;
    Handler mainHandler;
    private ImageView vw;
    public FetchImage(String URL, ImageView img){
        this.URL = URL;
        this.vw = img;
    }
    
    @Override
    public void run(){
        mainHandler.post(new Runnable(){
            @Override
            public void run(){
                
            }
        });
        
        InputStream inputStream = null;
        try{
            inputStream = new URL(URL).openStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
        }catch(IOException e){
            e.printStackTrace();
        }
        mainHandler.post(new Runnable(){
            @Override
            public void run(){
                vw.setImageBitmap(bitmap);
            }
        });
    }
}
