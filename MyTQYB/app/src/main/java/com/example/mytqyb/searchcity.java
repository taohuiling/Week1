package com.example.mytqyb;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.ArraySet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class searchcity extends AppCompatActivity {
    EditText city_txt;
    SharedPreferences sp;
    SharedPreferences sp1;
    ListView city_list;
    city1Adapter city1Adapter;
    ArrayList<String> citys;
    ArrayList<String> citys1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchcity);
        sp= this.getSharedPreferences("search", MODE_PRIVATE);
        sp1= this.getSharedPreferences("CityCub", MODE_PRIVATE);
        CityCodeManager cityCodeManager=new CityCodeManager();
        cityCodeManager.SwitchCity();
        citys=cityCodeManager.getCitys();
        city_txt=findViewById(R.id.city_txt);
        city_list=findViewById(R.id.city_list);

        city_txt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() > 0) {
                    citys1=search(s.toString(),citys);
                    initViews(citys1);
                }else {
                    initViews(citys);
                }
            }
        });
        search();
    }

    private void search(){
        city_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String city=citys1.get(position);

                SharedPreferences.Editor editor = sp.edit();
                SharedPreferences.Editor editor1 = sp1.edit();
                Set<String> citys2;
                if(sp.getStringSet("citys",null)==null){
                    citys2=new ArraySet<>();
                }else {
                    citys2=sp.getStringSet("citys",null);
                }
                citys2.add(city);

                editor.putStringSet("citys",citys2);
                editor1.putString("cityname",city);

                editor.commit();
                sp.edit().clear().putStringSet("citys",citys2).apply();
                Intent intent=new Intent(searchcity.this,SwitchCity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initViews(ArrayList<String> list) {
        city1Adapter=new city1Adapter(this,list);
        city_list.setAdapter(city1Adapter);
    }

    public ArrayList<String> search(String name,ArrayList<String> list){
        ArrayList<String> results = new ArrayList<String>();
        Pattern pattern = Pattern.compile(name);
        for(int i=0; i < list.size(); i++){
            Matcher matcher = pattern.matcher((list.get(i)));
            if(matcher.find()){
                results.add(list.get(i));
            }
        }
        return results;
    }
    
}
