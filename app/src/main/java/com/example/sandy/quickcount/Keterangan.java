package com.example.sandy.quickcount;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class Keterangan extends AppCompatActivity implements View.OnClickListener {

    private String wilayah,no,cowo,cewe,jumlah,id,suara1,suara2,rusak,sah;
    private TextView lakilaki,perempuan,total;
    public static final String DATA_URL = "http://192.168.42.125:8080/xampp/quickcount/peserta.php";
    public static final String DATA_URL2 = "http://192.168.42.125:8080/xampp/quickcount/send.php";
    public static final String DATA_URL3 = "http://192.168.42.125:8080/xampp/quickcount/update.php";
    public static final String TAG_ID = "ID";
    public static final String TAG_LAKILAKI = "Laki-laki";
    public static final String TAG_PEREMPUAN = "Perempuan";
    public static final String TAG_TOTAL = "Jumlah";
    public static final String TAG_URL = "kode_wilayah";
    public static final String TAG_NO = "no_tps";
    String tag_string_req = "req_data";
    EditText calon1;
    EditText calon2;
    EditText suaraTidakSah;
    TextView suaraSah;
    private Button buttonSend,buttonUpdate;
    int flag=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keterangan);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
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
        buttonSend = (Button) findViewById(R.id.send);
        buttonSend.setOnClickListener(this);
//        buttonUpdate = (Button) findViewById(R.id.update);
//        buttonUpdate.setOnClickListener(this);

        lakilaki = (TextView) findViewById(R.id.lakilaki);
        perempuan = (TextView) findViewById(R.id.perempuan);
        total = (TextView) findViewById(R.id.total);
        calon1= (EditText) findViewById(R.id.edtlabel3);
        calon2 = (EditText) findViewById(R.id.edtlabel4);
        suaraTidakSah = (EditText) findViewById(R.id.edtlabel6);
        suaraSah = (TextView) findViewById(R.id.edtlabel5);
        TotalSuaraSah();

        String nomor= getIntent().getExtras().getString("nomer");
        no = nomor;
        String kode = getIntent().getExtras().getString("wilayah");
        wilayah = kode;
        final StringRequest jsonArrayRequest = new StringRequest(Request.Method.POST,DATA_URL, new Response.Listener<String>() {
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
                            String no_id = jsonObject.getString(TAG_ID);
//                            Log.d("flag", jsonObject.toString());

                            cowo = cowok;
                            cewe = cewek;
                            jumlah = sum;
                            id =no_id;
                            Log.d("flag", "get "+ id );

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
        setTitle(no);
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

    private String addNumbers() {
        int number1;
        int number2;
        if(calon1.getText().toString() != "" && calon1.getText().length() > 0) {
            number1 = Integer.parseInt(calon1.getText().toString());
        } else {
            number1 = 0;
        }
        if(calon2.getText().toString() != "" && calon2.getText().length() > 0) {
            number2 = Integer.parseInt(calon2.getText().toString());
        } else {
            number2 = 0;
        }

        return Integer.toString(number1 + number2);
    }

    private void TotalSuaraSah () {
        calon1.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
//                 TODO Auto-generated method stub
            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                suaraSah.setText(addNumbers());
            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub
            }
        });

        calon2.addTextChangedListener(new TextWatcher() {

            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // TODO Auto-generated method stub

            }

            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                suaraSah.setText(addNumbers());

            }

            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }


    public void suara() {
        suara1 = calon1.getText().toString().trim();
        suara2 = calon2.getText().toString().trim();
        rusak = suaraTidakSah.getText().toString().trim();
        sah = suaraSah.getText().toString().trim();
        int flag = 1;

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DATA_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(Keterangan.this,"penyimpanan sukses",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Keterangan.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Keterangan.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("id_tps",id);
                map.put("calon1",suara1);
                map.put("calon2",suara2);
                map.put("rusak",rusak);
                map.put("sah",sah);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    public void update(View view) {
        suara1 = calon1.getText().toString().trim();
        suara2 = calon2.getText().toString().trim();
        rusak = suaraTidakSah.getText().toString().trim();
        sah = suaraSah.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, DATA_URL3,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            Toast.makeText(Keterangan.this,"penyimpanan sukses",Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(Keterangan.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Keterangan.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put("id_tps",id);
                map.put("calon1",suara1);
                map.put("calon2",suara2);
                map.put("rusak",rusak);
                map.put("sah",sah);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    @Override
    public void onClick(View v) {
        suara();
        if(flag==1)
        {
            buttonSend.setEnabled(false);
            Log.d("ins", "called");
        }
        flag=0;

    }




}
