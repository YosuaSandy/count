package com.example.sandy.quickcount;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Profil extends AppCompatActivity {
   private RecyclerView recyclerView;
    private TPUAdapter tAdapter;
    private  Context context;
    private static final String TAG = Profil.class.getSimpleName();
    String tag_string_req = "req_data";
    private List<TPU> rowListItem;
    private  String code,kecamatan;

    public static final String DATA_URL = "http://192.168.42.125:8080/xampp/quickcount/kelurahan.php";
    public static final String TAG_NAME = "nama";
    public static final String TAG_URL = "kode_wilayah";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
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
        String kode = getIntent().getExtras().getString("code");
        Log.d("log", kode);
        code = kode;
        String A = getIntent().getExtras().getString("kecamatan");
        Log.d("flag",A);
        kecamatan=A;
        setTitle(kecamatan);
        rowListItem = prepareTPU();
        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        rView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        rView.setLayoutManager(layoutManager);

        final TPUAdapter adapter = new TPUAdapter(Profil.this, rowListItem);
        rView.setAdapter(adapter);
        StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST,DATA_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                try {
                    JSONObject jsobj = new JSONObject(response);
                    JSONArray kelurahan = jsobj.getJSONArray("kelurahan");
                    if (kelurahan.length() > 0 || kelurahan != null) {
                        for (int i = 0; i < kelurahan.length(); i++) {
                            JSONObject jsonObject = kelurahan.getJSONObject(i);
                           TPU person = new TPU();
                            Log.d(TAG, jsonObject.toString());
                            person.nama = jsonObject.getString(TAG_NAME);
                            person.kode_wilayah= jsonObject.getString(TAG_URL);
                            rowListItem.add(i, person);
                            Log.d(TAG, jsonObject.toString());
                        }
                        adapter.notifyDataSetChanged();
                        Log.d(TAG, response.toString());
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
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("flag", error.getMessage());

            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                Log.d("flag", code);
                Log.d("flag", TAG_URL);
                map.put(TAG_URL, code);
                return map;
            }
        }
                ;
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(jsonArrayRequest);
        AppController.getInstance().addToRequestQueue(jsonArrayRequest,tag_string_req);
        Log.d(TAG, jsonArrayRequest.toString());
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
                Intent B = new Intent(Profil.this, MainActivity.class);
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

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private Profil.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final Profil.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }
    private List<TPU> prepareTPU() {
        List<TPU> allItems = new ArrayList<TPU>();
//        allItems.add( new TPU("13", "Pedurungan"));
//        allItems.add(new TPU("15", "Pedurungan"));
//        allItems.add(new TPU("05", "Pedurungan"));
//        allItems.add(new TPU("07", "Pedurungan"));
//        allItems.add(new TPU("09", "Pedurungan"));
//        allItems.add(new TPU("27", "Pedurungan"));
        return allItems;



    }









}