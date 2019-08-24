package com.example.communatio;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class list_adapter extends RecyclerView.Adapter<list_adapter.MyViewHolder> {
    private ArrayList<String> list1;
    Context context;
    public list_adapter(Context context,ArrayList<String> list1)
    {
        this.context=context;
        this.list1=list1;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView companyname1;
        CircleImageView logo;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            companyname1=itemView.findViewById(R.id.NameofCollege);
            logo=itemView.findViewById(R.id.logo);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_nitsdetail,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag((list1.get(i)));
        String collegeName=list1.get(i);
        viewHolder.companyname1.setText(collegeName);
        if(collegeName.equals("NIT Patna"))
            viewHolder.logo.setImageResource(R.drawable.nitplogo);
        if(collegeName.equals("NIT Rourkela"))
            viewHolder.logo.setImageResource(R.drawable.nitrlogo);
        if(collegeName.equals("NIT Trichi"))
            viewHolder.logo.setImageResource(R.drawable.nittrlogo);
        if(collegeName.equals("NIT Agartala"))
            viewHolder.logo.setImageResource(R.drawable.nitagarlogo);
        if(collegeName.equals("NIT Jamshedpur"))
            viewHolder.logo.setImageResource(R.drawable.nitjlogo);
        if(collegeName.equals("NIT Pudicherry"))
            viewHolder.logo.setImageResource(R.drawable.nitpudlogo);
        if(collegeName.equals("NIT Warangal"))
            viewHolder.logo.setImageResource(R.drawable.nitwlogo);
        if(collegeName.equals("NIT Hamirpur"))
            viewHolder.logo.setImageResource(R.drawable.nithlogo);
        if(collegeName.equals("NIT Rourkela")) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, Rourkela.class);
                    context.startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

}

