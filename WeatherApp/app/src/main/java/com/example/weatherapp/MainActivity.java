package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {
    EditText inputName;
    TextView dispResult;
    public class getData extends AsyncTask<String,Void,String>{
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
                int data = reader.read();
                while(data!=-1){
                    char current = (char)data;
                    data=reader.read();
                    result+=current;
                }
                return result;
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String result="";
            try {
                JSONObject jsonObj = new JSONObject(s);
                String weatherInfo = jsonObj.getString("weather");
                JSONArray weatherArr = new JSONArray(weatherInfo);
                for(int i=0;i<weatherArr.length();i++){
                    JSONObject obj = weatherArr.getJSONObject(i);
                    result+="\nMain: "+obj.getString("main");
                    result+= "\nDescription: "+obj.getString("description");
                }
                String mainInfo = jsonObj.getString("main");
                JSONObject mainobj = new JSONObject(mainInfo);
                result+="\nTemperature: "+mainobj.getString("temp");
                result+="\nHumidity: "+mainobj.getString("humidity");
                dispResult.setText(result);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
    }
    public void onGo(View view){
        getData info = new getData();
        String name = inputName.getText().toString();
        try {
            info.execute("https://api.openweathermap.org/data/2.5/weather?q="+name+"&appid=48f6e571bfc2db19f98ea6b8ce4812b5");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputName = (EditText)findViewById(R.id.cityName);
        dispResult = (TextView)findViewById(R.id.textView2);
        Log.i("STATUS: ","DONE");
    }
}