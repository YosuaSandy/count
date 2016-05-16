package com.example.sandy.quickcount;

/**
 * Created by Sandy on 4/12/2016.
 */
public class TPU {
    String nama,kode_wilayah;
    public  TPU () {

    }

    public TPU (String tps,String dpu){
        this.nama=tps;
        this.kode_wilayah=dpu;
    }

    public String getTps(){
        return nama;
    }

    public void setTps(String tps){
        this.nama=tps;
    }

    public String getDps(){
        return kode_wilayah;
    }

    public void setDpu(String dpu){
        this.kode_wilayah=dpu;
    }



}
