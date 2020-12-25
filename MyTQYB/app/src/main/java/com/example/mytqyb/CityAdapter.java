package com.example.mytqyb;

import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class CityAdapter extends BaseAdapter {
    Context scontext;
    List<City>slistItems;
    public CityAdapter(Context context, List<City>listItems){
        scontext=context;
        slistItems=listItems;
    }
    @Override
    public int getCount() {
        return slistItems.size();
    }

    @Override
    public Object getItem(int position) {
        return slistItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v=null;
        ViewHolder sHolder;

       if(convertView==null){
           v=View.inflate(scontext,R.layout.listcity,null);
           sHolder=new ViewHolder();

           sHolder.tvcity=(TextView)v.findViewById(R.id.tvcity);
           sHolder.tvcitywd=(TextView)v.findViewById(R.id.tvcitywd);
           sHolder.listbg=(LinearLayout)v.findViewById(R.id.listbg);
           v.setTag(sHolder);
       }
       else{
           v=convertView;
           sHolder=(ViewHolder)v.getTag();
       }
        City item=slistItems.get(position);

        sHolder.tvcity.setText(item.getCityname());
        sHolder.tvcitywd.setText(item.getTianqis().get(0).getType());
        switch (sHolder.tvcitywd.getText().toString()){
            case "晴":
                sHolder.listbg.setBackgroundResource(R.mipmap.qintian1);
                break;
            case "多云":
                sHolder.listbg.setBackgroundResource(R.mipmap.duoyun1);
                break;
            case "大雨":
                sHolder.listbg.setBackgroundResource(R.mipmap.yutian1);
                break;
            case "小雨":
                sHolder.listbg.setBackgroundResource(R.mipmap.yutian1);
                break;
            case "中雨":
                sHolder.listbg.setBackgroundResource(R.mipmap.yutian1);
                break;
            case "阵雨":
                sHolder.listbg.setBackgroundResource(R.mipmap.yutian1);
                break;
            case "雨":
                sHolder.listbg.setBackgroundResource(R.mipmap.yutian1);
                break;
            case "阴":
                sHolder.listbg.setBackgroundResource(R.mipmap.yintian1);;
                break;
            case "大雪":
                sHolder.listbg.setBackgroundResource(R.mipmap.xue1);
                break;
            case "阵雪":
                sHolder.listbg.setBackgroundResource(R.mipmap.xue1);
                break;
            case "雪":
                sHolder.listbg.setBackgroundResource(R.mipmap.xue1);
                break;
            case "雨夹雪":
                sHolder.listbg.setBackgroundResource(R.mipmap.yujiaxue1);
                break;
            case "霾":
                sHolder.listbg.setBackgroundResource(R.mipmap.other1);
                break;
            default:
                sHolder.listbg.setBackgroundResource(R.mipmap.other1);
                break;
        }
        return v;
    }
    private class ViewHolder {

        TextView tvcity;
        TextView tvcitywd;
        LinearLayout listbg;

    }

}
