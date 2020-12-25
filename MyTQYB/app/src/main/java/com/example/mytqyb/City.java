package com.example.mytqyb;

import java.util.ArrayList;
import java.util.List;

public class City
{
    private String cityname;
    private String tianqi;
    private ArrayList<tianqi> tianqis;
    private  int id;
    private String cityName;
    private String cityCode;
    private int provinceId;

    public int getId()
    {
        return id;
    }
    public  void setId(int id)
    {
        this.id = id;
    }

    public String getCityName()
    {
        return  cityName;
    }
    public  void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCityCode()
    {
        return  cityCode;
    }
    public  void setCityCode(String cityCode)
    {
        this.cityCode = cityCode;
    }

    public int getProvinceId()
    {
        return provinceId;
    }

    public  void setProvinceId(int provinceId)
    {
        this.provinceId = provinceId;
    }




    public String getCityname() {
        return cityname;
    }

    public void setCityname(String cityname) {
        this.cityname = cityname;
    }

    public ArrayList<tianqi> getTianqis() {
        return tianqis;
    }

    public void setTianqis(ArrayList<tianqi> tianqis) {
        this.tianqis = tianqis;
    }

    public City() {
    }

    public City(String cityname, String tianqi, ArrayList<com.example.mytqyb.tianqi> tianqis, int id, String cityName, String cityCode, int provinceId) {
        this.cityname = cityname;
        this.tianqi = tianqi;
        this.tianqis = tianqis;
        this.id = id;
        this.cityName = cityName;
        this.cityCode = cityCode;
        this.provinceId = provinceId;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityname='" + cityname + '\'' +
                ", tianqi='" + tianqi + '\'' +
                ", tianqis=" + tianqis +
                ", id=" + id +
                ", cityName='" + cityName + '\'' +
                ", cityCode='" + cityCode + '\'' +
                ", provinceId=" + provinceId +
                '}';
    }

    public String getTianqi() {
        return tianqi;
    }

    public void setTianqi(String tianqi) {
        this.tianqi = tianqi;
    }
}
