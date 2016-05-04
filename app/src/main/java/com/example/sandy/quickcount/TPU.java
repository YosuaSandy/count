package com.example.sandy.quickcount;

/**
 * Created by Sandy on 4/12/2016.
 */
public class TPU {
    String tps,dps;
    public  TPU () {

    }

    public TPU (String tps,String dpu){
        this.tps=tps;
        this.dps=dpu;
    }

    public String getTps(){
        return tps;
    }

    public void setTps(String tps){
        this.tps=tps;
    }

    public String getDps(){
        return dps;
    }

    public void setDpu(String dpu){
        this.dps=dpu;
    }



}
