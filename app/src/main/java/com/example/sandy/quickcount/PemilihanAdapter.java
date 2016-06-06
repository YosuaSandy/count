package com.example.sandy.quickcount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sandy on 5/19/2016.
 */
public class PemilihanAdapter extends RecyclerView.Adapter<PemilihanAdapter.RecycleViewHolder>{
    private List<TPS> tpsList;
    private Context context;
    private String pilih;
    private int flag;



    public PemilihanAdapter(Context context, List<TPS> itemList,String pilih) {
        this.tpsList = itemList;
        this.context = context;
        this.pilih=pilih;

    }

    @Override
    public RecycleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_pemilihan, null);
        RecycleViewHolder rcv = new RecycleViewHolder(layoutView);
        return rcv;
    }

    @Override
    public void onBindViewHolder(RecycleViewHolder holder, int position) {
        TPS tpu = tpsList.get(position);
        holder.nomor.setText(tpu.getNo());
        holder.alamat.setText(tpu.getAlamat());
    }

    @Override
    public int getItemCount() {

        return this.tpsList.size();
    }

    class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView nomor,alamat;
        String pilihan;
        private Context Context;




        public RecycleViewHolder(View itemView) {
            super(itemView);
            Context= itemView.getContext();
            itemView.setOnClickListener(this);
            nomor = (TextView)itemView.findViewById(R.id.nomor);
            alamat = (TextView)itemView.findViewById(R.id.alamat);
            pilihan = pilih;
            itemView.setBackgroundResource(R.color.orange_transparent);
        }




        @Override
        public void onClick(View view) {
//            view.setBackgroundColor(Color.BLUE);
//

            Intent i = new Intent(context, Keterangan.class);
            Bundle extras = new Bundle();
            extras.putString("nomer", nomor.getText().toString());
            extras.putString("alamat", alamat.getText().toString());
            extras.putString("wilayah", pilihan);
            i.putExtras(extras);
            context.startActivity(i);

        }





    }
}
