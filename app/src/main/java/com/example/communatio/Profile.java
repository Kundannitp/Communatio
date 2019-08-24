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
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.net.URL;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity implements View.OnClickListener {
    private static final Object MAX_IMAGE_SIZE = 1;
    public EditText Name, address, mob, college, courses, email;
    CircleImageView profilepic;
    Button cvpic;
    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int PICK_IMAGE_REQUEST1 = 200;
    public Uri filePath1, filePath2;
    public Button register;
    public FirebaseAuth myAuth;
    public StorageReference storageReference;
    public DatabaseReference myReference, myReference1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Name = (EditText) findViewById(R.id.nameofperson);
        address = (EditText) findViewById(R.id.placeofperson);
        mob = (EditText) findViewById(R.id.callofperson);
        cvpic = (Button) findViewById(R.id.upload_cv);
        email = (EditText) findViewById(R.id.emailofperson);
        cvpic.setOnClickListener(this);
        profilepic = findViewById(R.id.profilepic);
        profilepic.setOnClickListener(this);
        cvpic.setOnClickListener(this);
        myAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        myReference = FirebaseDatabase.getInstance().getReference();
        String d1 = myAuth.getCurrentUser().getUid();
        myReference1 = FirebaseDatabase.getInstance().getReference().child("Profiles").child(d1);
        college = (EditText) findViewById(R.id.Collegename);
        courses = (EditText) findViewById(R.id.courseofperson);
        register = (Button) findViewById(R.id.register_person);
        register.setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();

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
                    String url = dataSnapshot.child("imageurl").getValue().toString();
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
    }

    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void showFileChooser1() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST1);
    }

    @Override
    public void onClick(View v) {
        if (v == register) {
            String d = myAuth.getCurrentUser().getUid();
            String s1 = Name.getText().toString();
            String s2 = address.getText().toString();
            String s3 = mob.getText().toString();
            String s4 = college.getText().toString();
            String s5 = courses.getText().toString();
            String s6 = email.getText().toString();
            biodataok b = new biodataok(s1,s2,s3,s4,s5,s6);
            myReference.child("Profiles").child(d).setValue(b);
            uploadFile(filePath1);
            uploadFile(filePath2);
        } else {
            Toast.makeText(this, "Please select jobtype ", Toast.LENGTH_SHORT).show();
        }
        if(v==profilepic)

    {
        showFileChooser();
    }
        if(v==cvpic)

    {
        showFileChooser1();
    }

}
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filePath1 = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath1);
                profilepic.setImageBitmap(scaleBitmap(bitmap,300,300));
            } catch (IOException e) {
                e.printStackTrace();

            }
        }
        if(requestCode==PICK_IMAGE_REQUEST1&& resultCode==RESULT_OK&&data!=null&&data.getData()!=null){
            filePath2=data.getData();
        }
    }

    public static Bitmap scaleBitmap(Bitmap bitmap, int wantedWidth, int wantedHeight) {
        Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        Matrix m = new Matrix();
        m.setScale((float) wantedWidth / bitmap.getWidth(), (float) wantedHeight / bitmap.getHeight());
        canvas.drawBitmap(bitmap, m, new Paint());
        return output;
    }

    public String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }
    private void uploadFile(final Uri filePath) {
        Toast.makeText(this,filePath.toString(), Toast.LENGTH_SHORT).show();
        //checking if file is available
        if (filePath != null) {
            //displaying progress dialog while image is uploading
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("Uploading");
            progressDialog.show();

            //getting the storage reference
            final String d6=myAuth.getCurrentUser().getUid();
            final StorageReference sRef = storageReference.child(d6 +"/" + System.currentTimeMillis() + "." + getFileExtension(filePath));

            //adding the file to reference
            sRef.putFile(filePath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss();

                            //displaying success toast
//                            Toast.makeText(getApplicationContext(), "File Uploaded ", Toast.LENGTH_LONG).show();

                            //creating the upload object to store uploaded image details
                            sRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    if((filePath.toString()).equals(filePath1.toString())) {
                                        Toast.makeText(Profile.this, "Profile pic Uploaded", Toast.LENGTH_SHORT).show();
                                        myReference.child("Profiles").child(d6).child("imageurl").setValue(uri.toString());
                                    }
                                    else
                                    {
                                        Toast.makeText(Profile.this, "CV Uploaded", Toast.LENGTH_SHORT).show();
                                        myReference.child("Profiles").child(d6).child("cvUrl").setValue(uri.toString());
                                    }
                                }
                            });
                        }
                    })

                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), exception.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            //displaying the upload progress
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "%...");
                        }
                    });
        } else {
            Toast.makeText(this, "Please select a file", Toast.LENGTH_SHORT).show();
        }
//        return u;
    }


}
