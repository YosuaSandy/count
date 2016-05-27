package com.example.sandy.quickcount;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sandy on 4/12/2016.
 */
public class TPUAdapter  extends RecyclerView.Adapter<TPUAdapter.RecycleViewHolder> {



    private List<TPU> tpuList;
    private Context context;



    public TPUAdapter(Context context, List<TPU> itemList) {
        this.tpuList = itemList;
        this.context = context;

    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_profil, null);
        RecycleViewHolder rcv = new RecycleViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        TPU tpu = tpuList.get(position);
        holder.TPS.setText(tpu.getTps());
        holder.DPS.setText(tpu.getDps());
    }

    @Override
    public int getItemCount() {

        return this.tpuList.size();
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView TPS,DPS;
        private Context Context;




        public RecycleViewHolder(View itemView) {
            super(itemView);
            Context= itemView.getContext();
            itemView.setOnClickListener(this);
            TPS = (TextView)itemView.findViewById(R.id.TPS);
            DPS = (TextView)itemView.findViewById(R.id.DPS);
            itemView.setBackgroundResource(R.color.orange_transparent);
        }




        @Override
        public void onClick(View view) {
//            view.setBackgroundColor(Color.BLUE);
//
            Intent i = new Intent(context, Pemilihan.class);
            i.putExtra("pilihan", DPS.getText().toString());
            i.putExtra("kelurahan", TPS.getText().toString());
            Log.d("flag", "put " + TPS.getText().toString());
            context.startActivity(i);

        }





    }

}
