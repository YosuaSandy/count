package com.example.sandy.quickcount;

/**
 * Created by Sandy on 5/23/2016.
 */
public class Peserta {
    String laki_laki,perempuan,jumlah;

    public Peserta() {

    }

    public Peserta (String laki_laki,String perempuan,String jumlah){
        this.laki_laki = laki_laki;
        this.perempuan=perempuan;
        this.jumlah=jumlah;
    }

    public String getLakilaki(){
        return laki_laki ;
    }

    public void setLakilaki(String laki_laki){
        this.laki_laki = laki_laki;
    }

    public String getPerempuan(){
        return perempuan;
    }

    public void setPerempuan(String alamat){
        this.perempuan=alamat;
    }

    public String getJumlah() {return jumlah;

    }

    public void setJumlah (String jumlah){
        this.jumlah =jumlah;
    }
}
