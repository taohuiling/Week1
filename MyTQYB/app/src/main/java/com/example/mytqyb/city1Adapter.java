package com.example.mytqyb;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class city1Adapter  extends BaseAdapter{
    Context mcontext;
    ArrayList<String> mlistItems;
    public city1Adapter(Context context, ArrayList<String>listItems){
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
            v=View.inflate(mcontext,R.layout.listsearch,null);
            mHolder=new ViewHolder();

            mHolder.tv1=(TextView)v.findViewById(R.id.tv1);


            v.setTag(mHolder);
        }
        else{
            v=convertView;
            mHolder=(ViewHolder)v.getTag();
        }
        String item=mlistItems.get(position);
//        mHolder.tv2.setImageDrawable(mcontext.getResources().getDrawable(item.ImageId));

        mHolder.tv1.setText(item);


        return v;
    }
    private class ViewHolder {

        TextView tv1;


    }
}
