package com.example.communatio;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class ConfirmEvent extends AppCompatActivity implements View.OnClickListener {
    public EditText Name, address, mob, college, courses, email;
    CircleImageView profilepic;
    Button pay;
    String url;
    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int PICK_IMAGE_REQUEST1 = 200;
    public Uri filePath1, filePath2;
    public Button confirm;
    String h1,d1;
    public FirebaseAuth myAuth;
    public StorageReference storageReference;
    public DatabaseReference myReference, myReference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_event);
        Intent i=getIntent();
        h1=i.getStringExtra("key");
        Name = (EditText) findViewById(R.id.nameofperson1);
        address = (EditText) findViewById(R.id.placeofperson1);
        mob = (EditText) findViewById(R.id.callofperson1);
        pay = (Button) findViewById(R.id.payupi);
        email = (EditText) findViewById(R.id.emailofperson1);
        profilepic = findViewById(R.id.profilepic1);
        myAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        myReference = FirebaseDatabase.getInstance().getReference();
         d1 = myAuth.getCurrentUser().getUid();
        myReference1 = FirebaseDatabase.getInstance().getReference().child("Profiles").child(d1);
        college = (EditText) findViewById(R.id.Collegename1);
        courses = (EditText) findViewById(R.id.courseofperson1);
        confirm = (Button) findViewById(R.id.register_person);

        myReference1.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    Name.setText(dataSnapshot.child("name").getValue().toString());
                    address.setText(dataSnapshot.child("address").getValue().toString());
                    mob.setText(dataSnapshot.child("mob").getValue().toString());
                    courses.setText(dataSnapshot.child("courses").getValue().toString());
                    college.setText(dataSnapshot.child("college").getValue().toString());
                    email.setText(dataSnapshot.child("email").getValue().toString());
                    url = dataSnapshot.child("imageurl").getValue().toString();
                    //String cvurl1=dataSnapshot.child("cvUrl").getValue().toString();
//                    URL url1 = new URL(cvurl1);
//                    cvurl.setClickable(true);
//                    cvurl.setMovementMethod(LinkMovementMethod.getInstance());
//                    String text = "<a href=url1> CV Link </a>";
//                    cvurl.setText(Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT));
                    //.setText(cvurl1);
                    //Linkify.addLinks(cvurl, Linkify.WEB_URLS);
                    EventAdapter p = new EventAdapter();
                    EventAdapter.GetImageFromUrl2 y = p.new GetImageFromUrl2(profilepic);
                    y.execute(url);
                } catch (Exception e) {

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        myReference.child("Events").child(h1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String a=dataSnapshot.child("fee").getValue().toString();
                if(a.equals("free"))
                {
                    pay.setVisibility(View.INVISIBLE);
                }
                else{
                    confirm.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        confirm.setOnClickListener(this);
        pay.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==confirm){
            String d = myAuth.getCurrentUser().getUid();
            String s1 = Name.getText().toString();
            String s2 = address.getText().toString();
            String s3 = mob.getText().toString();
            String s4 = college.getText().toString();
            String s5 = courses.getText().toString();
            String s6 = email.getText().toString();
            biodataok b = new biodataok(s1,s2,s3,s4,s5,s6);
            myReference.child("Event Responses").child(h1).child(d).setValue(b);
            myReference.child("Students events").child(d1).push().setValue(h1);
            myReference.child("Event Responses").child(h1).child(myAuth.getCurrentUser().getUid()).child("imageurl").setValue(url);
            Toast.makeText(this, "Successfully Applied", Toast.LENGTH_SHORT).show();
            finish();
        }
        if(v==pay){
            Intent i=new Intent(ConfirmEvent.this,UpiPayment.class);
            i.putExtra("key",h1);
            startActivity(i);
            finish();
        }
    }
}
