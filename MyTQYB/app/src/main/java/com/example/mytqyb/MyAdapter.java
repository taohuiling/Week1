package com.example.mytqyb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    Context mcontext;
    List<tianqi>mlistItems;
    public MyAdapter(Context context, List<tianqi>listItems){
        mcontext=context;
        mlistItems=listItems;
    }
    @Override
    public int getCount() {
        return mlistItems.size();
    }

    @Override
    public Object getItem(int position) {
        return mlistItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View v=null;
        ViewHolder mHolder;
       if(convertView==null){
           v=View.inflate(mcontext,R.layout.list,null);
           mHolder=new ViewHolder();

           mHolder.tv1=(TextView)v.findViewById(R.id.tv1);
           mHolder.tv2=(ImageView)v.findViewById(R.id.tv2);
           mHolder.tv3=(TextView)v.findViewById(R.id.tv3);
           v.setTag(mHolder);
       }
       else{
           v=convertView;
           mHolder=(ViewHolder)v.getTag();
       }
        tianqi item=mlistItems.get(position);

        mHolder.tv2.setImageDrawable(mcontext.getResources().getDrawable(item.Aircon()));



        mHolder.tv1.setText(item.getDate());
        mHolder.tv3.setText(item.getLow()+"/"+item.getHigh());

        return v;
    }
    private class ViewHolder {

        TextView tv1;
        ImageView tv2;
        TextView tv3;

    }
}
