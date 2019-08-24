package com.example.communatio;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.InputStream;
import java.util.ArrayList;


public class notifications extends Fragment {
    private RecyclerView mRecyclerView;
    private View joblist;
    Bitmap bitmap;
    private DatabaseReference Eventref;
    ArrayList<Events1> jobs = new ArrayList<>();
    FirebaseAuth mAuth1;

    public notifications() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        joblist = inflater.inflate(R.layout.fragment_notifications, container, false);
        mRecyclerView = (RecyclerView) joblist.findViewById(R.id.recycler_view_Events);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAuth1 = FirebaseAuth.getInstance();
        Eventref = FirebaseDatabase.getInstance().getReference().child("Events");

        Eventref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String collegename = ds.child("collegename").getValue().toString();
                    String EventType = ds.child("eventtype").getValue().toString();
                    String EventName = ds.child("eventname").getValue().toString();
                    String fee = ds.child("fee").getValue().toString();
                    String url = ds.child("UriImage").getValue().toString();
                    jobs.add(new Events1(EventType,EventName,fee,collegename,url,ds.getKey()));
                }
                EventAdapter adapter=new EventAdapter(getActivity(),jobs);
                mRecyclerView.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        //jobs.add(new Events("EventType","EventName","fee","collegename","url","ds"));

        return joblist;
    }
}

