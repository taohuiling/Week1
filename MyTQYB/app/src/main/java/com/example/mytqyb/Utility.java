package com.example.mytqyb;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.example.mytqyb.db.CoolWeatherDB;
import com.example.mytqyb.model.County;
import com.example.mytqyb.model.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    //解析Province返回数据
    public synchronized  static  boolean handleProvince(CoolWeatherDB coolWeatherDB, String response)
    {
        if(!TextUtils.isEmpty(response))
        {
            String allProvince[] = response.split(",");
            if(allProvince != null && allProvince.length > 0)
            {
                for(String p : allProvince)///////////////
                {
                    String array[] = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceName(array[1]);
                    province.setProvinceCode(array[0]);
                    coolWeatherDB.saveProvince(province);
                }
                return  true;
            }

        }
        return false;
    }
    //解析City返回数据
    public synchronized  static boolean handleCity(CoolWeatherDB coolWeatherDB, String response, int provinceId)
    {
        if(!TextUtils.isEmpty(response))
        {
            String allCity[] = response.split(",");
            if(allCity != null && allCity.length > 0)
            {
                for(String p : allCity)
                {
                    String array[] = p.split("\\|");
                    City city = new City();
                    city.setCityName(array[1]);
                    city.setCityCode(array[0]);
                    city.setProvinceId(provinceId);
                    coolWeatherDB.saveCity(city);
                }
                return  true;
            }

        }
        return  false;
    }
    //解析County返回数据
    public synchronized  static boolean handleCounty(CoolWeatherDB coolWeatherDB, String response, int cityId)
    {
        if(!TextUtils.isEmpty(response))
        {
            String allCounty[] = response.split(",");
            if(allCounty != null && allCounty.length > 0)
            {
                for (String p : allCounty) {
                    String array[] = p.split("\\|");
                    County county = new County();
                    county.setCountycode(array[0]);
                    county.setCountyName(array[1]);
                    county.setCityId(cityId);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }

        }
        return  false;
    }
    public static  void handleWeatherResorce(Context context, String response)
    {
        String City = "";
        String tip = "";
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONObject weatherinfo = jsonObject.getJSONObject("data");
            City = weatherinfo.getString("city");
            tip = weatherinfo.getString("ganmao");
            JSONArray forecast = weatherinfo.getJSONArray("forecast");

            for(int i=0;i<forecast.length();i++){
                JSONObject weather_arr = forecast.getJSONObject(i);
                String date = weather_arr.getString("date").replace("日","日\r\n");
                String high = weather_arr.getString("high").replace("高温","").replace("-","零下");
                String low = weather_arr.getString("low").replace("低温","").replace("-","零下");
                String type = weather_arr.getString("type");
                saveWeatherInfo(context, City, tip, date, low, high, type,i);
            }

        }catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
    public  static  void saveWeatherInfo(Context context, String cityName, String tip, String date, String temp1, String temp2, String weather, int i)
    {

        SharedPreferences shared = context.getSharedPreferences(cityName, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("city_name", cityName);
        editor.putString("low_temp"+i, temp1);
        editor.putString("high_temp"+i, temp2);
        editor.putString("weather"+i, weather);
        editor.putString("tip", tip);
        editor.putString("data"+i, date);
        editor.commit();
    }


}