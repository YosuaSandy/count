package com.example.sandy.quickcount;

/**
 * Created by Sandy on 5/19/2016.
 */
public class TPS {
    String no_tps,alamat;
    public  TPS () {

    }

    public TPS (String no_tps,String alamat){
        this.no_tps = no_tps;
        this.alamat=alamat;
    }

    public String getNo(){
        return no_tps ;
    }

    public void setNo(String no_tps){
        this.no_tps = no_tps;
    }

    public String getAlamat(){
        return alamat;
    }

    public void setAlamat(String alamat){
        this.alamat=alamat;
    }

}
