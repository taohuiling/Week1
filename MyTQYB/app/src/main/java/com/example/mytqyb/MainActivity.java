package com.example.mytqyb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.BackingStoreException;
public class MainActivity extends AppCompatActivity {

    City city=new City();
    TextView tvwd;
    TextView tvwc;
    TextView tvtq;
    TextView tvdd;
    ImageView cityswitch;
    ConstraintLayout back;

    ListView listView;
    private List<tianqi> listItems;
    MyAdapter adapter;
    SharedPreferences sharedPreferences;

    Handler handler=new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(@NonNull Message msg) {
            init();
            initViews();
            setBackG();

        }
    };

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                MyHttpConnection http = new MyHttpConnection();
                String jsonString = http.doGet("http://wthrcdn.etouch.cn/weather_mini?city=" + sharedPreferences.getString("cityname", "南平"));
                try {
                    JSONObject weatherJson = new JSONObject(jsonString);
                    JSONObject data = weatherJson.getJSONObject("data");
                    JSONArray forecast = data.getJSONArray("forecast");
                    city.setTianqis(new ArrayList<tianqi>(forecast.length()));
                    city.setCityname(sharedPreferences.getString("cityname", "南平"));
                    for (int i = 0; i < forecast.length(); i++) {
                        JSONObject dayweather = forecast.getJSONObject(i);
                        tianqi t = new tianqi();
                        t.setFengli(dayweather.getString("fengli"));
                        t.setFengli(t.getFengli().replace("<![CDATA[", ""));
                        t.setFengli(t.getFengli().replace("]]>", ""));

                        t.setDate(dayweather.getString("date"));
                        t.setWendu(data.getString("wendu"));
                        t.setType(dayweather.getString("type"));
                        t.setLow(dayweather.getString("low"));
                        t.setHigh(dayweather.getString("high"));
                        t.setHigh(t.getHigh().replace("高温",""));
                        t.setLow(t.getLow().replace("低温",""));
                        city.getTianqis().add(t);
                    }

                    System.out.println(city);

                    Message msg=new Message();
                    msg.what=1;

                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       sharedPreferences = this.getSharedPreferences("CityCub", MODE_PRIVATE);

        setContentView(R.layout.activity_main);
        tvwd = findViewById(R.id.tvwd);
        tvwc = findViewById(R.id.tvwc);
        tvtq = findViewById(R.id.tvtq);
        tvdd = findViewById(R.id.tvdd);
        back=findViewById(R.id.back);




        cityswitch=findViewById(R.id.cityswitch);
        cityswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SwitchCity.class);
                startActivityForResult(intent,1);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                MyHttpConnection http = new MyHttpConnection();
                String jsonString = http.doGet("http://wthrcdn.etouch.cn/weather_mini?city=" + sharedPreferences.getString("cityname", "南平"));
                try {
                    JSONObject weatherJson = new JSONObject(jsonString);
                    JSONObject data = weatherJson.getJSONObject("data");
                    JSONArray forecast = data.getJSONArray("forecast");
                    city.setTianqis(new ArrayList<tianqi>(forecast.length()));
                    city.setCityname(sharedPreferences.getString("cityname", "南平"));
                    for (int i = 0; i < forecast.length(); i++) {
                        JSONObject dayweather = forecast.getJSONObject(i);
                        tianqi t = new tianqi();
                        t.setFengli(dayweather.getString("fengli"));
                        t.setFengli(t.getFengli().replace("<![CDATA[", ""));
                        t.setFengli(t.getFengli().replace("]]>", ""));

                        t.setDate(dayweather.getString("date"));
                        t.setWendu(data.getString("wendu"));
                        t.setType(dayweather.getString("type"));
                        t.setLow(dayweather.getString("low"));
                        t.setHigh(dayweather.getString("high"));
                        t.setHigh(t.getHigh().replace("高温",""));
                        t.setLow(t.getLow().replace("低温",""));
                        city.getTianqis().add(t);
                    }

                    System.out.println(city);

                    Message msg=new Message();
                    msg.what=1;

                    handler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Window window = getWindow();

            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);

            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            window.setStatusBarColor(Color.TRANSPARENT);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            //4.4 全透明状态栏

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setBackG(){
        switch (city.getTianqis().get(0).getType()){
            case "晴":
                back.setBackground(this.getResources().getDrawable(R.mipmap.qintian));
                break;
            case "多云":
                back.setBackground(this.getResources().getDrawable(R.mipmap.duoyun));
                break;
            case "大雨":
                back.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
                break;
            case "小雨":
                back.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
                break;
            case "中雨":
                back.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
                break;
            case "阵雨":
                back.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
                break;
            case "雨":
                back.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
                break;
            case "阴":
                back.setBackground(this.getResources().getDrawable(R.mipmap.yintian));;
                break;
            case "大雪":
                back.setBackground(this.getResources().getDrawable(R.mipmap.xue));
                break;
            case "阵雪":
                back.setBackground(this.getResources().getDrawable(R.mipmap.xue));
                break;
            case "雪":
                back.setBackground(this.getResources().getDrawable(R.mipmap.xue));
                break;
            case "雨夹雪":
                back.setBackground(this.getResources().getDrawable(R.mipmap.yujiaxue));
                break;
            case "霾":
                back.setBackground(this.getResources().getDrawable(R.mipmap.other));
                break;
            default:
                back.setBackground(this.getResources().getDrawable(R.mipmap.other));
                break;
        }

    }
    public  void init(){
        tvwd.setText(city.getTianqis().get(0).getWendu());
        tvwc.setText(city.getTianqis().get(0).getLow()+"/"+city.getTianqis().get(1).getHigh());
        tvtq.setText(city.getTianqis().get(0).getType());
        tvdd.setText(city.getCityname());

    }

    private void initViews() {//将数据导入到listview里
        listView=findViewById(R.id.tvlist);
        listItems=new ArrayList<>();

        adapter=new MyAdapter(this,city.getTianqis());
        listView.setAdapter(adapter);

    }
}
