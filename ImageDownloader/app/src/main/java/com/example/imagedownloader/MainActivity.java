package com.example.imagedownloader;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    public class getImg extends AsyncTask<String,Void, Bitmap>{
        @Override
        protected Bitmap doInBackground(String... urls) {
            URL url;
            HttpURLConnection conn;
            try{
                url=new URL(urls[0]);
                conn=(HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream in=conn.getInputStream();
                Bitmap bitmap = BitmapFactory.decodeStream(in);
                return bitmap;
            }
            catch (Exception e){
                Log.i("Exception: ", String.valueOf(e.getStackTrace()));
            }
            return null;
        }
    }
    public void downloadImg(View view){
        try {
        EditText imageLink = (EditText) findViewById(R.id.imageURL);
        String link = imageLink.getText().toString();
        getImg imgDown = new getImg();
        ImageView imgBox=(ImageView) findViewById(R.id.imageView);
        //https://upload.wikimedia.org/wikipedia/en/a/aa/Bart_Simpson_200px.png
            Bitmap img = (Bitmap) imgDown.execute(link).get();
            imgBox.setImageBitmap(img);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}