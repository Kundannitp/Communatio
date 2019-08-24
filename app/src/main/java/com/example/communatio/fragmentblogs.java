package com.example.communatio;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class fragmentblogs extends Fragment implements View.OnClickListener {
    View blogs;
    FloatingActionButton posting;
    ImageView like,dislike;
    RecyclerView recy;
    ArrayList<posts> post=new ArrayList<>();
    DatabaseReference mref;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        blogs = inflater.inflate(R.layout.fragment_fragmentblogs, container, false);
        posting=blogs.findViewById(R.id.fab);
        recy=blogs.findViewById(R.id.recyclerblogs);
        recy.setLayoutManager(new LinearLayoutManager(getContext()));
        mref=FirebaseDatabase.getInstance().getReference().child("blogs");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    post.add(new posts(ds.child("post").getValue().toString(),ds.child("name").getValue().toString()));
                }
                BlogsAdapter b=new BlogsAdapter(getActivity(),post);
                recy.setAdapter(b);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        posting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(),Activity_post.class);
                startActivity(i);
            }
        });
        //like=blogs.findViewById(R.id.like_btn);
        //dislike=blogs.findViewById(R.id.dislike_btn);
        //like.setOnClickListener(this);
        //dislike.setOnClickListener(this);
        //post.add(new posts("kkj","kku"));
        //BlogsAdapter b=new BlogsAdapter(getActivity(),post);
        //recy.setAdapter(b);
        return blogs;
    }

    @Override
    public void onClick(View v) {
        /*if(v==like)
        {
            Toast.makeText(getActivity(), "Liked the post", Toast.LENGTH_SHORT).show();
        }
        if(v==dislike)
        {
            Toast.makeText(getActivity(), "Disliked the post", Toast.LENGTH_SHORT).show();
        }*/
    }
}
