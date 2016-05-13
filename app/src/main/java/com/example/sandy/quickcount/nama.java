package com.example.sandy.quickcount;

import android.widget.ImageView;

/**
 * Created by Sandy on 4/11/2016.
 */
public class Nama {
    String nama,kode_wilayah;
   boolean selected;

    Nama(){

    }

    public Nama (String nama,String url)
    {
        this.nama =    nama;
        this.kode_wilayah = url;
    }

    public String getNama(){
        return nama;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getUrl (){
        return kode_wilayah;
    }

    public void seturl(String url){
        this.kode_wilayah=url;
    }

    public void setNama(String nama){
        this.nama=nama;
    }


}
