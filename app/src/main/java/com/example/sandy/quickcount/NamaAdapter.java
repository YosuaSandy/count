package com.example.sandy.quickcount;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.style.BackgroundColorSpan;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.BLUE;
import static android.graphics.Color.RED;
import static com.example.sandy.quickcount.R.drawable.round;

/**
 * Created by Sandy on 4/8/2016.
 */


public class NamaAdapter extends RecyclerView.Adapter<NamaAdapter.RecycleViewHolder> {


    private List<Nama> itemList ;
    private Context context;


    public NamaAdapter(Context context, List<Nama> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_mainmenu, null);
        RecycleViewHolder rcv = new RecycleViewHolder(layoutView);
        return rcv;
    }

        @Override
        public void onBindViewHolder(RecycleViewHolder holder, int position) {
            holder.Nama.setText(itemList.get(position).getNama());
            holder.Url.setText(itemList.get(position).getUrl());


        }

    @Override
    public int getItemCount() {

        if (itemList != null) {
            return  this.itemList.size();
        }
        return 0;
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView Nama,Url;
        private Context Context;




        public RecycleViewHolder(View itemView) {
            super(itemView);
            Context= itemView.getContext();
            itemView.setClickable(true);
            itemView.setOnClickListener(this);
            Nama = (TextView)itemView.findViewById(R.id.nama);
            Url = (TextView)itemView.findViewById(R.id.url);
            itemView.setBackgroundResource(R.color.white);


        }



        @Override
        public void onClick(View view) {
            Intent i = new Intent(context, Profil.class);
                i.putExtra("code", Url.getText().toString());
                context.startActivity(i);
        }
    }
}




