package com.example.mytqyb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SwitchCity extends AppCompatActivity {
    TextView tvcity;
    TextView tvcitywd;


    ImageView addcity;
    CityAdapter adapter;
    SharedPreferences sp,sharedPreferences;
    List<City> cities=new LinkedList<>();
    ListView listView;
//    ConstraintLayout listbg;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        finish();
        super.onActivityResult(requestCode, resultCode, data);
    }

    Handler handler=new Handler(){
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(@NonNull Message msg) {
            initViews();
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String city=cities.get(position).getCityname();

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("cityname",city);
                    editor.commit();
                    Intent intent=new Intent(SwitchCity.this,MainActivity.class);
                    startActivity(intent);
                    finish();

                }
            });

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch_city);
        sp = this.getSharedPreferences("search", MODE_PRIVATE);
        sharedPreferences = this.getSharedPreferences("CityCub", MODE_PRIVATE);
        addcity = findViewById(R.id.addcity);
//       listbg=findViewById(R.id.listbg);

        addcity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SwitchCity.this, searchcity.class);
                startActivityForResult(intent,2);

            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                if(sp.getStringSet("citys",null)!=null){
                    MyHttpConnection http = new MyHttpConnection();



                    List<String> citys =new LinkedList<>();
                    for(String c:sp.getStringSet("citys", null)){
                        citys.add(c);
                    }
                    for(int j=0;j<citys.size();j++){
                        City city=new City();
                        city.setCityname(citys.get(j));
                        String jsonString = http.doGet("http://wthrcdn.etouch.cn/weather_mini?city=" + citys.get(j));
                        try {
                            JSONObject weatherJson = new JSONObject(jsonString);
                            JSONObject data = weatherJson.getJSONObject("data");
                            JSONArray forecast = data.getJSONArray("forecast");
                            city.setTianqis(new ArrayList<tianqi>(forecast.length()));
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
                            cities.add(city);

                        }catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                    Message msg=new Message();
                    msg.what=1;
                    handler.sendMessage(msg);

                }

            }
        }).start();


    }

    private void initViews() {//将数据导入到listview里
        listView=findViewById(R.id.citylist);


        adapter=new CityAdapter(this,cities);

        listView.setAdapter(adapter);

    }
//    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//    public void setListbg(){
//        switch (){
//            case "晴":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.qintian));
//                break;
//            case "多云":
//               listbg.setBackground(this.getResources().getDrawable(R.mipmap.duoyun));
//                break;
//            case "大雨":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
//                break;
//            case "小雨":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
//                break;
//            case "中雨":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
//                break;
//            case "阵雨":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
//                break;
//            case "雨":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.yutian));
//                break;
//            case "阴":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.yintian));;
//                break;
//            case "大雪":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.xue));
//                break;
//            case "阵雪":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.xue));
//                break;
//            case "雪":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.xue));
//                break;
//            case "雨夹雪":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.yujiaxue));
//                break;
//            case "霾":
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.other));
//                break;
//            default:
//                listbg.setBackground(this.getResources().getDrawable(R.mipmap.other));
//                break;
//        }
//    }

}


