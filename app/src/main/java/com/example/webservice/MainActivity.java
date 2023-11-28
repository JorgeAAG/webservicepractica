package com.example.webservice;

import static java.lang.invoke.VarHandle.AccessMode.GET;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.webservice.webservice.Asynchtask;
import com.example.webservice.webservice.WebService;

import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements Asynchtask {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view){
        EditText txtu=findViewById(R.id.txtnombre);
        EditText txtp=findViewById(R.id.txtclave);

        String url="https://revistas.uteq.edu.ec/ws/login.php?usr="+txtu.getText()+"&pass="+txtp.getText();

        Bundle bundle = this.getIntent().getExtras();
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService(
                url,
                datos, MainActivity.this, MainActivity.this);
        ws.execute("GET");


        Toast.makeText(getApplicationContext(),"Funciona", Toast.LENGTH_LONG).show();
    }

    public void loginwithbvolley(View view){

        EditText txtu=findViewById(R.id.txtnombre);
        EditText txtp=findViewById(R.id.txtclave);
        RequestQueue queue = Volley.newRequestQueue(this);

        String url="https://revistas.uteq.edu.ec/ws/login.php?usr="+txtu.getText()+"&pass="+txtp.getText();
        TextView txtresp=findViewById(R.id.txtrespuesta);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtresp.setText("Response is: "+ response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtresp.setText("That didn't work!");
                    }
                });
        queue.add(stringRequest);
    }
    @Override
    public void processFinish(String result) throws JSONException {
        TextView txtresp=findViewById(R.id.txtrespuesta);
        txtresp.setText(result);

    }
}