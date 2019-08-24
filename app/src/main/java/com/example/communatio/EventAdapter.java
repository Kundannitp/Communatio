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

import de.hdodenhof.circleimageview.CircleImageView;


public class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private ArrayList<Events1> list1;
    Bitmap bitmap1;
    Context context;

    public EventAdapter() {
    }

    public EventAdapter(Context context, ArrayList<Events1> list1)
    {
        this.context=context;
        this.list1=list1;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvCollegeName;
        TextView tvEventName;
        TextView tvFee;
        TextView tvDaysAgo;
        CircleImageView ibLogo;
        TextView tvEventType;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCollegeName = itemView.findViewById(R.id.Collegename);
            tvEventName = itemView.findViewById(R.id.eventname);
            tvFee = itemView.findViewById(R.id.fee);
            tvDaysAgo = itemView.findViewById(R.id.daysago);
            ibLogo = itemView.findViewById(R.id.logo);
            tvEventType=itemView.findViewById(R.id.Eventtype);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.eventslist,viewGroup,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag((list1.get(i)));
        viewHolder.tvCollegeName.setText(list1.get(i).getCollegename());
        viewHolder.tvEventType.setText(list1.get(i).getEventtype());
        viewHolder.tvEventName.setText(list1.get(i).getEventname());
        viewHolder.tvFee.setText(list1.get(i).getFee());
        final String h=list1.get(i).getUniqueid();
        new GetImageFromUrl2(viewHolder.ibLogo).execute(list1.get(i).getUriImage());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, ApplyEvents.class);
                    i.putExtra("key", h);
                    context.startActivity(i);
                }
            });
        }


    @Override
    public int getItemCount() {
        return list1.size();
    }

    public class GetImageFromUrl2 extends AsyncTask<String,Void, Bitmap> {
        CircleImageView imgv;
        public GetImageFromUrl2(CircleImageView img) {
            imgv=img;
        }

        @Override
        protected Bitmap doInBackground(String... url) {
            String urlDisplay=url[0];
            bitmap1=null;
            try{
                InputStream srt=new java.net.URL(urlDisplay).openStream();
                bitmap1= BitmapFactory.decodeStream(srt);
            }
            catch (Exception e){

            }
            return bitmap1;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imgv.setImageBitmap(scaleBitmap(bitmap,100,100));
        }

        public  Bitmap scaleBitmap(Bitmap bitmap, int wantedWidth, int wantedHeight) {
            Bitmap output = Bitmap.createBitmap(wantedWidth, wantedHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);
            Matrix m = new Matrix();
            m.setScale((float) wantedWidth / bitmap.getWidth(), (float) wantedHeight / bitmap.getHeight());
            canvas.drawBitmap(bitmap, m, new Paint());
            return output;
        }
    }
}
