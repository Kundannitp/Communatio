package com.example.communatio;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.InputStream;
import java.util.ArrayList;



public class BlogsAdapter extends RecyclerView.Adapter<BlogsAdapter.MyViewHolder> {
    private ArrayList<posts> list1;
    Context context;
    public BlogsAdapter(Context context,ArrayList<posts> list1)
    {
        this.context=context;
        this.list1=list1;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView post1,name1;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            post1 = itemView.findViewById(R.id.post);
            name1=itemView.findViewById(R.id.name);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.blogsitem,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag((list1.get(i)));
        viewHolder.post1.setText(list1.get(i).getPost());
        viewHolder.name1.setText(list1.get(i).getName());
    }


    @Override
    public int getItemCount() {
        return list1.size();
    }
}
