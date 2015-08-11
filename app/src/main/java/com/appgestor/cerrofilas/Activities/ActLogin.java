package com.appgestor.cerrofilas.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.appgestor.cerrofilas.Entities.Estudiante;
import com.appgestor.cerrofilas.R;
import com.gc.materialdesign.views.ButtonRectangle;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class ActLogin extends AppCompatActivity {

    private EditText usuario;
    private EditText password;
    private Estudiante estudiante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        usuario = (EditText) findViewById(R.id.EditUsuario);
        password = (EditText) findViewById(R.id.EditPassword);

        ButtonRectangle button = (ButtonRectangle) findViewById(R.id.btnIngresar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidNumber(usuario.getText().toString())){
                    usuario.setError(getResources().getString(R.string.login_usuario));
                    usuario.setFocusableInTouchMode(true);
                    usuario.requestFocus();
                }else if(isValidNumber(password.getText().toString())) {
                    password.setError(getResources().getString(R.string.login_password));
                    password.setFocusableInTouchMode(true);
                    password.requestFocus();
                }else{

                    ValidatorLogin();

                    //startActivity(new Intent(, FragmentHome.class));
                    //overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    //finish();
                }

            }
        });
    }

    private void ValidatorLogin() {
        String url = String.format("%1$s%2$s", getString(R.string.url_base),"ValidateLogin");
        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        // response
                        parseJSON(response);
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(ActLogin.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("usuario", usuario.getText().toString());
                params.put("password", password.getText().toString());
                return params;
            }
        };
        rq.add(jsonRequest);
    }

    private boolean parseJSON(String json) {
        try {
            Gson gson = new Gson();
            //Estudiante.setEstudiante(gson.fromJson(json, Estudiante.class));
        }catch (IllegalStateException ex) {
            ex.printStackTrace();
        }

        return true;
    }


    private boolean isValidNumber(String number) {
        return number == null || number.length() == 0;
    }

}
