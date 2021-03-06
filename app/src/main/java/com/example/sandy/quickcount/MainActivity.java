package com.example.sandy.quickcount;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String LOGIN_URL = "http://192.168.42.125:8080/xampp/quickcount/login.php";
    public static final String LOGIN_HP = "http://192.168.42.125:8080/xampp/quickcount/loginHp.php";
    public static final String KEY_USERNAME= "username";
    public static final String KEY_PASSWORD= "password";
    public static final String KEY_NOHP= "no_hp";
    public static final String TAG_USER = "username";
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button btn1;

    private String username;
    private String password;
    private String username2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.FullScreen);
        setContentView(R.layout.activity_main);
        setTitle("");
         btn1 = (Button) findViewById(R.id.btnLogin);
        editTextUsername = (EditText) findViewById(R.id.username);
        editTextPassword = (EditText) findViewById(R.id.password);
        btn1 = (Button) findViewById(R.id.btnLogin);
        btn1.setOnClickListener(this);

    }

    private void userLogin() {
        username = editTextUsername.getText().toString().trim();
        password = editTextPassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.trim().equals("success")){
                            openProfile();
                        }else{
                            Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_USERNAME,username);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent intent = new Intent(MainActivity.this, Mainmenu.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        userLogin();
    }

    public void noHp (View view){
        TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        String simNumber = tm.getSimSerialNumber();
        String networkOperator = tm.getNetworkOperator();
        String networkCountryIso = tm.getNetworkCountryIso();
        final String number = simNumber+networkOperator+networkCountryIso;
        Log.d("flag","get" +number );

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_HP,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                            try {
                                JSONObject jsobj = new JSONObject(response);
                                JSONArray Peserta = jsobj.getJSONArray("user");
                                if (Peserta.length() > 0 || Peserta != null) {
                                    for (int i = 0; i < Peserta.length(); i++) {
                                        JSONObject jsonObject = Peserta.getJSONObject(i);
                                        Log.d("flag", jsonObject.toString());
                                        String user = jsonObject.getString(TAG_USER);
                                        username2=user;
                                        Log.d("flag","set" +username2);
                                        Toast.makeText(MainActivity.this,"Selamat datang " +username2 ,Toast.LENGTH_LONG).show();
                                    }
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                                Log.d("flag", "error" + e.getMessage());
                            }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map = new HashMap<String,String>();
                map.put(KEY_NOHP, number);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
//        Toast.makeText(MainActivity.this,"Selamat datang " +username2 ,Toast.LENGTH_LONG).show();
        openProfile();
    }


}




