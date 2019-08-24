package com.example.communatio;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Activity_post extends AppCompatActivity {
    DatabaseReference blogrefrence,idrefrence;
    TextView name,post_title;
    FirebaseAuth mauth;
    Button post_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        blogrefrence= FirebaseDatabase.getInstance().getReference();
        mauth=FirebaseAuth.getInstance();
        idrefrence= FirebaseDatabase.getInstance().getReference().child("Profiles").child(mauth.getCurrentUser().getUid());
        name=findViewById(R.id.name);
        post_title=findViewById(R.id.post_title);
        post_btn=findViewById(R.id.post_btn);
        idrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                name.setText(dataSnapshot.child("name").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        post_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1=name.getText().toString();
                String s2=post_title.getText().toString();
                posts p=new posts(s2,s1);
                blogrefrence.child("blogs").push().setValue(p);
                Toast.makeText(Activity_post.this, "Successfully posted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
