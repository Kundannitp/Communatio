package com.example.communatio;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.SearchView;

import java.util.ArrayList;

public class Homefragment extends Fragment {
    SearchView searchcollege;
    private RecyclerView mRecyclerView;
    private View joblist;
    int h=0;
    list_adapter myAdapter;
    ArrayList<String> Nitslist1=new ArrayList<>();
    ArrayList<String> Nitslist2=new ArrayList<>();
    ArrayList<String> Nitsnew;
    public Homefragment() {

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        joblist = inflater.inflate(R.layout.fragment_homefragment, container, false);
        searchcollege=joblist.findViewById(R.id.searchNits);
        mRecyclerView = (RecyclerView) joblist.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Nitslist1.add("NIT Patna");
        Nitslist1.add("NIT Rourkela");
        Nitslist1.add("NIT Trichi");
        Nitslist1.add("NIT Agartala");
        Nitslist1.add("NIT Jamshedpur");
        Nitslist1.add("NIT Pudicherry");
        Nitslist1.add("NIT Warangal");
        Nitslist1.add("NIT Hamirpur");
        Nitsnew=new ArrayList<>(Nitslist1);
        myAdapter=new list_adapter(getContext(),Nitslist1);
        mRecyclerView.setAdapter(myAdapter);
        searchcollege.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.length()==5) {
                    //Nitslist1.clear();
                    for (String s : Nitslist1) {
                        if (s.toLowerCase().contains(newText)) {
                            Nitslist2.add(s);
                        }
                    }
                    myAdapter = new list_adapter(getContext(), Nitslist2);
                    mRecyclerView.setAdapter(myAdapter);
                    h=1;
                }
                return true;
            }
        });
            myAdapter = new list_adapter(getContext(), Nitsnew);
            mRecyclerView.setAdapter(myAdapter);
            h=0;
        return joblist;
    }
}