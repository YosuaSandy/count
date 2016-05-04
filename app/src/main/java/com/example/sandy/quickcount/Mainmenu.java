package com.example.sandy.quickcount;



import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Mainmenu extends AppCompatActivity  {

    String nama;
    private GridLayoutManager lLayout;
    private RecyclerView rView;
    private List<nama> rowListItem;
    private static final int VERTICAL_ITEM_SPACE = 10;
    private static final int TOP_ITEM_SPACE = 10;
    private static final int LEFT_ITEM_SPACE = 10;
    private static final int HORIZONTAL_ITEM_SPACE = 10;
    //Volley Request Queue
    private RequestQueue requestQueue;
    //The request counter to send ?page=1, ?page=2  requests
    private int requestCount = 1;
    private Context mContext;
    private SearchView mSearchView;
    private TextView mStatusView;


    public static final String DATA_URL = "http://192.168.42.125:8080/xampp/quickcount/wilayah.php";
    public static final String TAG_NAME = "nama";
    public static final String TAG_URL = "url";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setSubtitle(R.string.subjudul);
        setTitle("");
        actionBar.setLogo(R.mipmap.ic_launcher);


        lLayout = new GridLayoutManager(Mainmenu.this, 3);

        rView = (RecyclerView) findViewById(R.id.recycler_view);
        rView.addItemDecoration(new VerticalSpaceDecoration(VERTICAL_ITEM_SPACE, TOP_ITEM_SPACE));
        rView.addItemDecoration(new HorizontalSpaceDecoration(HORIZONTAL_ITEM_SPACE, LEFT_ITEM_SPACE));
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);
        rowListItem = getAllItemList();

//        request_data();
//        final NamaAdapter rcAdapter = new NamaAdapter(Mainmenu.this, rowListItem);
//        rView.setAdapter(rcAdapter);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menubar, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.new_game:
                Intent B = new Intent(Mainmenu.this, MainActivity.class);
                startActivity(B);
                // User chose the "Settings" item, show the app settings UI...
                break;

            case R.id.refresh:

               break;
        }
        return true;
    }


    private List<nama> getAllItemList() {


        List<nama> allItems = new ArrayList<nama>();
        allItems.add(new nama("Joko", "woclass"));
        allItems.add(new nama("Retno", "woclass"));
        allItems.add(new nama("Painem", "woclass"));
        allItems.add(new nama("Paijo", "woclass"));
        allItems.add(new nama("Ratna", "woclass"));
        allItems.add(new nama("Jono", "woclass"));
        allItems.add(new nama("Joni", "woclass"));
        allItems.add(new nama("Indah", "woclass"));
        allItems.add(new nama("Retro", "woclass"));

        return allItems;


    }




//    public void request_data () {
//        final NamaAdapter rcAdapter = new NamaAdapter(Mainmenu.this, rowListItem);
//        rView.setAdapter(rcAdapter);
//
//        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
//        String link = "http://192.168.42.125:8080/xampp/quickcount/wilayah.php";
//
//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(link, new Response.Listener<JSONArray>() {
//
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    if (response.length() == 0 || response == null) {
//                        rowListItem.clear();
//                        for (int i = 0; i < response.length(); i++) {
//                            JSONObject jsonObject = response.getJSONObject(i);
//                            nama person = new nama();
//                            if (!jsonObject.isNull("nama")) {
//                                person.nama = jsonObject.getString("nama");
//                                Log.d("NAMA",nama);
//                            }
//                            if (!jsonObject.isNull("url")) {
//                                person.nama = jsonObject.getString("url");
//                            }
//                            rowListItem.add(i, person);
//                        }
//                        rcAdapter.notifyDataSetChanged();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                // do something
//            }
//        });
//
//        requestQueue.add(jsonArrayRequest);
//
//
// }


    }







