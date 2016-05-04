package com.example.sandy.quickcount;

import android.widget.ImageView;

/**
 * Created by Sandy on 4/11/2016.
 */
public class nama {
    String nama,url;
    boolean selected;
    public nama(){

    }

    public nama (String nama,String url)
    {
        this.nama =    nama;
        this.url = url;
    }

    public String getnama(){
        return nama;
    }

    public boolean isSelected() {
        return selected;
    }
    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String geturl (){
        return url;
    }

    public void seturl(String url){
        this.url=url;
    }

    public void setNama(String nama){
        this.nama=nama;
    }


}
