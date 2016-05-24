package com.example.sandy.quickcount;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Keterangan extends AppCompatActivity {

    private String wilayah,no,cowo,cewe,jumlah;
    private TextView lakilaki,perempuan,total;
    public static final String DATA_URL = "http://192.168.42.125:8080/xampp/quickcount/peserta.php";
    public static final String TAG_LAKILAKI = "Laki-laki";
    public static final String TAG_PEREMPUAN = "Perempuan";
    public static final String TAG_TOTAL = "Jumlah";
    public static final String TAG_URL = "kode_wilayah";
    public static final String TAG_NO = "no_tps";
    String tag_string_req = "req_data";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keterangan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        setTitle("");
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        lakilaki = (TextView) findViewById(R.id.lakilaki);
        perempuan = (TextView) findViewById(R.id.perempuan);
        total = (TextView) findViewById(R.id.total);


        String nomor= getIntent().getExtras().getString("nomer");
        no = nomor;
        String kode = getIntent().getExtras().getString("wilayah");
        wilayah = kode;
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST,DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("flag", response.toString());
                try {
                JSONObject jsobj = new JSONObject(response);
                    JSONArray Peserta = jsobj.getJSONArray("Peserta");
                    if (Peserta.length() > 0 || Peserta != null) {
                        for (int i = 0; i < Peserta.length(); i++) {
                            JSONObject jsonObject = Peserta.getJSONObject(i);
                            Log.d("flag", jsonObject.toString());
//                            Peserta person = new Peserta();
                           String cowok = jsonObject.getString(TAG_LAKILAKI);
                            String cewek= jsonObject.getString(TAG_PEREMPUAN);
                            String sum = jsonObject.getString(TAG_TOTAL);
//                            Log.d("flag", jsonObject.toString());

                            cowo = cowok;
                            cewe = cewek;
                            jumlah = sum;


                        }
//                        Log.d("flag", response.toString());
                    } lakilaki.setText(cowo);
                    perempuan.setText(cewe);
                    total.setText(jumlah);
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.d("flag", "error" + e.getMessage());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("flag", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("flag",error.getMessage());

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                Log.d("flag", "send "+wilayah);
                Log.d("flag", TAG_URL);
                map.put(TAG_URL, wilayah);
                map.put(TAG_NO, no);
                Log.d("flag", no);
                Log.d("flag", TAG_NO);
                return map;
            }
        }
                ;
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonArrayRequest);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest, tag_string_req);
        Log.d("flag", jsonArrayRequest.toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar2, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                Intent B = new Intent(Keterangan.this, MainActivity.class);
                startActivity(B);
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.help:
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;
//
//            case R.id.back:
//                onBackPressed();
//
//                return true;

//            case R.id.home:
//                onBackPressed();
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }



}
