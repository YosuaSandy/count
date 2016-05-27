package com.example.sandy.quickcount;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pemilihan extends AppCompatActivity {
    private Context context;
    String tag_string_req = "req_data";
    private List<TPS> rowListItem;
    private  String pilih,kelurahan;

    public static final String DATA_URL = "http://192.168.42.125:8080/xampp/quickcount/tempat.php";
    public static final String TAG_NO = "no_tps";
    public static final String TAG_ALAMAT = "alamat";
    public static final String TAG_URL = "kode_wilayah";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemilihan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.ic_launcher);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String kode = getIntent().getExtras().getString("pilihan");
        Log.d("log", kode);
        pilih = kode;


        String lurah = getIntent().getExtras().getString("kelurahan");
        Log.d("log", kode);
        kelurahan= lurah;
        setTitle(kelurahan);
        rowListItem = prepareTPS();
        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rView.setLayoutManager(layoutManager);
        final PemilihanAdapter adapter = new PemilihanAdapter(Pemilihan.this, rowListItem,pilih);
        Log.d ("flag","put" +pilih);
        rView.setAdapter(adapter);
//        String kode = getIntent().getExtras().getString("pilihan");
//        Log.d("log", kode);
//        pilih = kode;
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST,DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("flag", response.toString());
                try {
                    JSONObject jsobj = new JSONObject(response);
                    JSONArray noTps = jsobj.getJSONArray("Tempat");
                    if (noTps.length() > 0 || noTps != null) {
                        for (int i = 0; i < noTps.length(); i++) {
                            JSONObject jsonObject = noTps.getJSONObject(i);
                            TPS person = new TPS();
                            Log.d("flag", jsonObject.toString());
                            person.no_tps = jsonObject.getString(TAG_NO);
                            person.alamat= jsonObject.getString(TAG_ALAMAT);
                            rowListItem.add(i, person);
                            Log.d("flag", jsonObject.toString());
                        }
                        adapter.notifyDataSetChanged();
                        Log.d("flag", response.toString());
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(),
                            "Error: " + e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.d("flag",e.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // do something
                VolleyLog.d("flag", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("flag", error.getMessage());

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                Log.d("flag", pilih);
                Log.d("flag", TAG_URL);
                map.put(TAG_URL, pilih);
                Log.d("flag", "put" + pilih);
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
                Intent B = new Intent(Pemilihan.this, MainActivity.class);
                startActivity(B);
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.help:
                setTheme(R.style.AppTheme2);
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    private List<TPS> prepareTPS() {
        List<TPS> allItems = new ArrayList<TPS>();
//        allItems.add( new TPU("13", "Pedurungan"));
//        allItems.add(new TPU("15", "Pedurungan"));
//        allItems.add(new TPU("05", "Pedurungan"));
//        allItems.add(new TPU("07", "Pedurungan"));
//        allItems.add(new TPU("09", "Pedurungan"));
//        allItems.add(new TPU("27", "Pedurungan"));
        return allItems;

    }

}
