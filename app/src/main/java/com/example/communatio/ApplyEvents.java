package com.example.communatio;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class ApplyEvents extends AppCompatActivity {
    CircleImageView logos;
    TextView CollegeName,EventType,EventName,EventDate,Venue,Regisfee,applylast,information;
    Button apply_btn;
    String h1;
    DatabaseReference mrefrence;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_events);
        Intent i=getIntent();
        h1=i.getStringExtra("key");
        mrefrence= FirebaseDatabase.getInstance().getReference().child("Events").child(h1);
        CollegeName=findViewById(R.id.college_name);
        EventType=findViewById(R.id.event_type);
        EventName=findViewById(R.id.event_name);
        EventDate=findViewById(R.id.event_date);
        Venue=findViewById(R.id.event_venue);
        logos=findViewById(R.id.img_btn);
        Regisfee=findViewById(R.id.event_fee);
        applylast=findViewById(R.id.event_lastDate);
        information=findViewById(R.id.event_Information);
        apply_btn=findViewById(R.id.apply_btn);
        mrefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    CollegeName.setText(dataSnapshot.child("collegename").getValue().toString());
                    EventType.setText(dataSnapshot.child("eventtype").getValue().toString());
                    EventName.setText(dataSnapshot.child("eventname").getValue().toString());
                    EventDate.setText(dataSnapshot.child("dateofevent").getValue().toString());
                    Venue.setText(dataSnapshot.child("address").getValue().toString());
                    Regisfee.setText(dataSnapshot.child("fee").getValue().toString());
                    applylast.setText(dataSnapshot.child("lastdate").getValue().toString());
                    information.setText(dataSnapshot.child("information").getValue().toString());
                    String url = dataSnapshot.child("UriImage").getValue().toString();
                    EventAdapter p = new EventAdapter();
                    EventAdapter.GetImageFromUrl2 y = p.new GetImageFromUrl2(logos);
                    y.execute(url);
                }
                catch (Exception e){
                    Toast.makeText(ApplyEvents.this, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        apply_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(ApplyEvents.this,ConfirmEvent.class);
                i.putExtra("key",h1);
                startActivity(i);
                finish();
            }
        });
    }
}
