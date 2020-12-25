package com.example.mytqyb;

public class tianqi {
    private String high;
    private String low;
    private String date;
    private String type;
    private String fengxiang;
    private String fengli;
    private String wendu;

    public tianqi(String high, String low, String date, String type, String fengxiang, String fengli, String wendu) {
        this.high = high;
        this.low = low;
        this.date = date;
        this.type = type;
        this.fengxiang = fengxiang;
        this.fengli = fengli;
        this.wendu = wendu;
    }

    public tianqi() {
    }

    @Override
    public String toString() {
        return "tianqi{" +
                "high='" + high + '\'' +
                ", low='" + low + '\'' +
                ", date='" + date + '\'' +
                ", type='" + type + '\'' +
                ", fengxiang='" + fengxiang + '\'' +
                ", fengli='" + fengli + '\'' +
                ", wendu='" + wendu + '\'' +
                '}';
    }
    public int Aircon(){
        switch (type) {
            case "晴":
                return R.mipmap.sunshine;
            case "多云":
                return R.mipmap.cloudy;
            case "大雨":
                return R.mipmap.rain;
            case "小雨":
                return R.mipmap.srain;
            case "中雨":
                return R.mipmap.xrain;
            case "阵雨":
                return R.mipmap.cloudyrain;
            case "雨":
                return R.mipmap.lrain;
            case "阴":
                return R.mipmap.overcast;
            case "小雪":
                return R.mipmap.snow;
            case "大雪":
                return R.mipmap.snow;
            case "阵雪":
                return R.mipmap.snow;
            case "雪":
                return R.mipmap.snow;
            case "雨夹雪":
                return R.mipmap.rainandsnow;
            case "霾":
                return R.mipmap.fog;
            default:
                return R.mipmap.noweather;
        }
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFengxiang() {
        return fengxiang;
    }

    public void setFengxiang(String fengxiang) {
        this.fengxiang = fengxiang;
    }

    public String getFengli() {
        return fengli;
    }

    public void setFengli(String fengli) {
        this.fengli = fengli;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }
}
