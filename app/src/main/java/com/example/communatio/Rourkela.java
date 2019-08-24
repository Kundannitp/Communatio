package com.example.communatio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Rourkela extends AppCompatActivity {
    ExpandableListView expandablelistview;
    List<String> listGroup;
    HashMap<String,List<String>> listItem;
    Rour_Adapt adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rourkela);


        expandablelistview=findViewById(R.id.expendable_listView);
        listGroup=new ArrayList<>();
        listItem=new HashMap<>();
        adapter=new Rour_Adapt(this,listGroup,listItem);
        expandablelistview.setAdapter(adapter);
        initListdata();
    }

    private void initListdata() {
        listGroup.add(getString(R.string.group1));
        listGroup.add(getString(R.string.group2));
        listGroup.add("Hostels");
        listGroup.add("Curriculum");
        listGroup.add("Giant Start-Ups");
        listGroup.add("Contact-Admin");
        listGroup.add("Location");

        String[] array;

        List<String> list1=new ArrayList<>();
        array=getResources().getStringArray(R.array.group1);
        for(String item:array){
            list1.add(item);
        }

        List<String> list2=new ArrayList<>();
        array=getResources().getStringArray(R.array.group2);
        for(String item:array){
            list2.add(item);
        }

        List<String> list3=new ArrayList<>();
        array=getResources().getStringArray(R.array.group3);
        for(String item:array){
            list3.add(item);
        }

        List<String> list4=new ArrayList<>();
        array=getResources().getStringArray(R.array.group4);
        for(String item:array){
            list4.add(item);
        }
        List<String> list5=new ArrayList<>();
        array=getResources().getStringArray(R.array.group5);
        for(String item:array){
            list5.add(item);
        }
        List<String> list6=new ArrayList<>();
        array=getResources().getStringArray(R.array.group6);
        for(String item:array){
            list6.add(item);
        }
        List<String> list7=new ArrayList<>();
        array=getResources().getStringArray(R.array.group7);
        for(String item:array){
            list7.add(item);
        }

        listItem.put(listGroup.get(0),list1);
        listItem.put(listGroup.get(1),list2);
        listItem.put(listGroup.get(2),list3);
        listItem.put(listGroup.get(3),list4);
        listItem.put(listGroup.get(4),list5);
        listItem.put(listGroup.get(5),list6);
        listItem.put(listGroup.get(6),list7);

        adapter.notifyDataSetChanged();
    }


}
