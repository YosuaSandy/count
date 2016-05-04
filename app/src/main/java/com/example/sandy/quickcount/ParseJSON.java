package com.example.sandy.quickcount;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Sandy on 4/27/2016.
 */
public class ParseJSON {
    public static String[] id;
    public static String[] nama;
    public static String[] url;

    public static final String JSON_ARRAY = "wilayah";
    public static final String KEY_NAME = "nama";
    public static final String KEY_EMAIL = "url";

    private JSONArray users = null;

    private String json;

    public ParseJSON(String json){
        this.json = json;
    }

    protected void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);
            users = jsonObject.getJSONArray(JSON_ARRAY);

            id = new String[users.length()];
            nama = new String[users.length()];
            url = new String[users.length()];

            for(int i=0;i<users.length();i++){
                JSONObject jo = users.getJSONObject(i);
                nama[i] = jo.getString(KEY_NAME);
                url[i] = jo.getString(KEY_EMAIL);


            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
