package com.example.guessthecelebrity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.security.auth.login.LoginException;

public class MainActivity extends AppCompatActivity {
    ArrayList<String> links = new ArrayList<String>();
    ArrayList<String> names = new ArrayList<String>();
    ImageView img;
    String dispName;
    Button opt1;
    Button opt2;
    Button opt3;
    Button opt4;
    int i=0;
    public class LoadImg extends AsyncTask<String,Void, Bitmap>{

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url;
            HttpURLConnection conn;
            try{
                url=new URL(strings[0]);
                conn=(HttpURLConnection)url.openConnection();
                conn.connect();
                InputStream in = conn.getInputStream();
                Bitmap image = BitmapFactory.decodeStream(in);
                return image;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
    public class downloadTask extends AsyncTask<String,Void,String>{
        @Override
        protected String doInBackground(String... urls) {
            URL url;
            HttpURLConnection conn;
            String result="";
            try{
                url=new URL(urls[0]);
                conn=(HttpURLConnection) url.openConnection();
                conn.connect();
                InputStream in = conn.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                long data = reader.read();
                while(data!=-1){
                    char current=(char)data;
                    data=reader.read();
                    result+=current;
                }
                return result;
            }
            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }
    }
    public void getData(){
        downloadTask down=new downloadTask();
        try {
            String result = (String) down.execute("https://svenskainfluencers.nu/kandisar/").get();
            Log.i("GOT IT?", "YES");
            Log.i("HTML",result);
            Pattern link= Pattern.compile("src=\"(.*?)\" alt=\"\" data-id=");
            Pattern name= Pattern.compile("<figcaption class=\"blocks-gallery-item__caption\">(.*?)</figcaption></figure>");
            Matcher linkMatch = link.matcher(result);
            while(linkMatch.find()){
                links.add(linkMatch.group(1));
            }
            Matcher nameMatch = name.matcher(result);
            while(nameMatch.find()){
                names.add(nameMatch.group(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.i("STATUS: ","DATA SET COMPLETED");
    }
    public void submit(View view) {
        String marked = view.getTag().toString();
        if(marked.equals(dispName)){
            Toast.makeText(this, "CORRECT GUESS!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "WRONG GUESS!", Toast.LENGTH_SHORT).show();
        }
        LoadImg loader = new LoadImg();
        try {
            if(i<links.size()) {
                ArrayList<String> nameList = new ArrayList<String>();
                Bitmap dispImg = loader.execute(links.get(i)).get();
                dispName = names.get(i);
                img.setImageBitmap(dispImg);
                Random rand = new Random();
                int correct = rand.nextInt(4);
                for(int x=0;x<4;x++){
                    if(x==correct){
                        nameList.add(dispName);
                    }
                    else{
                        nameList.add(names.get((i+x+1)%(names.size()-1)));
                    }
                }
                opt1.setText(nameList.get(0));
                opt1.setTag(nameList.get(0));
                opt2.setText(nameList.get(1));
                opt2.setTag(nameList.get(1));
                opt3.setText(nameList.get(2));
                opt3.setTag(nameList.get(2));
                opt4.setText(nameList.get(3));
                opt4.setTag(nameList.get(3));
                i++;
            }
            else{
                img.setVisibility(view.INVISIBLE);
                opt1.setVisibility(view.INVISIBLE);
                opt2.setVisibility(view.INVISIBLE);
                opt3.setVisibility(view.INVISIBLE);
                opt4.setVisibility(view.INVISIBLE);
                TextView txt = (TextView) findViewById(R.id.doneText);
                txt.setVisibility(view.VISIBLE);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.imageView);
        opt1 = (Button) findViewById(R.id.option1);
        opt2 = (Button) findViewById(R.id.option2);
        opt3 = (Button) findViewById(R.id.option3);
        opt4 = (Button) findViewById(R.id.option4);
        getData();
        LoadImg loader = new LoadImg();
        try {
            if(i<links.size()) {
                ArrayList<String> nameList = new ArrayList<String>();
                Bitmap dispImg = loader.execute(links.get(i)).get();
                dispName = names.get(i);
                img.setImageBitmap(dispImg);
                Random rand = new Random();
                int correct = rand.nextInt(4);
                for(int x=0;x<4;x++){
                    if(x==correct){
                        nameList.add(dispName);
                    }
                    else{
                        nameList.add(names.get((i+x+1)%(names.size()-1)));
                    }
                }
                opt1.setText(nameList.get(0));
                opt1.setTag(nameList.get(0));
                opt2.setText(nameList.get(1));
                opt2.setTag(nameList.get(1));
                opt3.setText(nameList.get(2));
                opt3.setTag(nameList.get(2));
                opt4.setText(nameList.get(3));
                opt4.setTag(nameList.get(3));
                i++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}