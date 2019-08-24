package com.example.communatio;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class UpiPayment extends AppCompatActivity {

    TextView amountEt, noteEt, nameEt, upiIdEt;
    Button send,confirm;
    DatabaseReference myReference,myprof;
    FirebaseAuth mAuth;
    final int UPI_PAYMENT = 0;
    public Uri filePath1;
    String h1;
    public StorageReference storageReference;
    private static final int PICK_IMAGE_REQUEST = 234;
    private static final int PICK_IMAGE_REQUEST1 = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upi_payment);
        confirm=findViewById(R.id.confirm1);
        confirm.setVisibility(View.INVISIBLE);
        initializeViews();
        Intent i=getIntent();
        h1=i.getStringExtra("key");
        mAuth=FirebaseAuth.getInstance();
        myReference= FirebaseDatabase.getInstance().getReference();
        myReference.child("Events").child(h1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                amountEt.setText(dataSnapshot.child("fee").getValue().toString());
                noteEt.setText(dataSnapshot.child("eventname").getValue().toString());
                nameEt.setText(dataSnapshot.child("PIname").getValue().toString());
                upiIdEt.setText(dataSnapshot.child("UPIid").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        myprof= FirebaseDatabase.getInstance().getReference().child("Profiles").child(mAuth.getCurrentUser().getUid());
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Getting the values from the EditTexts
                String amount = amountEt.getText().toString();
                String note = noteEt.getText().toString();
                String name = nameEt.getText().toString();
                String upiId = upiIdEt.getText().toString();
                payUsingUpi(amount, upiId, name, note);
            }
        });
    }

    void initializeViews() {
        send = findViewById(R.id.send);
        amountEt = findViewById(R.id.amount_et);
        noteEt = findViewById(R.id.note);
        nameEt = findViewById(R.id.name);
        upiIdEt = findViewById(R.id.upi_id);
    }

    void payUsingUpi(String amount, String upiId, String name, String note) {

        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("pn", name)
                .appendQueryParameter("tn", note)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();


        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        // will always show a dialog to user to choose an app
        Intent chooser = Intent.createChooser(upiPayIntent, "Pay with");

        // check if intent resolves
        if(null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(UpiPayment.this,"No UPI app found, please install one to continue",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.d("UPI", "onActivityResult: " + trxt);
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add(trxt);
                        upiPaymentDataOperation(dataList);
                    } else {
                        Log.d("UPI", "onActivityResult: " + "Return data is null");
                        ArrayList<String> dataList = new ArrayList<>();
                        dataList.add("nothing");
                        upiPaymentDataOperation(dataList);
                    }
                } else {
                    Log.d("UPI", "onActivityResult: " + "Return data is null"); //when user simply back without payment
                    ArrayList<String> dataList = new ArrayList<>();
                    dataList.add("nothing");
                    upiPaymentDataOperation(dataList);
                }
                break;
        }
    }

    private void upiPaymentDataOperation(final ArrayList<String> data) {
        if (isConnectionAvailable(UpiPayment.this)) {
            String str = data.get(0);
            Log.d("UPIPAY", "upiPaymentDataOperation: "+str);
            String paymentCancel = "";
            if(str == null) str = "discard";
            String status = "";
            String approvalRefNo = "";
            String response[] = str.split("&");
            for (int i = 0; i < response.length; i++) {
                String equalStr[] = response[i].split("=");
                if(equalStr.length >= 2) {
                    if (equalStr[0].toLowerCase().equals("Status".toLowerCase())) {
                        status = equalStr[1].toLowerCase();
                    }
                    else if (equalStr[0].toLowerCase().equals("ApprovalRefNo".toLowerCase()) || equalStr[0].toLowerCase().equals("txnRef".toLowerCase())) {
                        approvalRefNo = equalStr[1];
                    }
                }
                else {
                    paymentCancel = "Payment cancelled by user.";
                }
            }

            if (status.equals("success")) {
                //Code to handle successful transaction here.
                Toast.makeText(UpiPayment.this, "Transaction successful.", Toast.LENGTH_SHORT).show();
                send.setVisibility(View.INVISIBLE);
                confirm.setVisibility(View.VISIBLE);
                myprof.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String d = mAuth.getCurrentUser().getUid();
                        String s1=dataSnapshot.child("name").getValue().toString();
                        String s2=(dataSnapshot.child("address").getValue().toString());
                        String s3=(dataSnapshot.child("mob").getValue().toString());
                        String s4=(dataSnapshot.child("courses").getValue().toString());
                        String s5=(dataSnapshot.child("college").getValue().toString());
                        String s6=(dataSnapshot.child("email").getValue().toString());
                        String url = dataSnapshot.child("imageurl").getValue().toString();
                        biodataok b = new biodataok(s1,s2,s3,s4,s5,s6);
                        myReference.child("Event Responses").child(h1).child(d).setValue(b);
                        myReference.child("Students events").child(d).push().setValue(h1);
                        myReference.child("Event Responses").child(h1).child(d).child("imageurl").setValue(url);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
            else if("Payment cancelled by user.".equals(paymentCancel)) {
                Toast.makeText(UpiPayment.this, "Payment cancelled by user.", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(UpiPayment.this, "Transaction failed.Please try again", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(UpiPayment.this, "Internet connection is not available. Please check and try again", Toast.LENGTH_SHORT).show();
        }
    }

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
