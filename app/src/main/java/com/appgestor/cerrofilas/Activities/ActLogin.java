package com.appgestor.cerrofilas.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import com.appgestor.cerrofilas.dark.Accounts;
import com.gc.materialdesign.views.ButtonRectangle;
import com.google.gson.Gson;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

public class ActLogin extends AppCompatActivity {

    private MaterialEditText usuario;
    private MaterialEditText password;
    private Estudiante estudiante;
    private ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);

        progress = new ProgressDialog(this);
        progress.setMessage("Cargando..");
        progress.setCancelable(false);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        usuario = (MaterialEditText) findViewById(R.id.EditUsuario);
        password = (MaterialEditText) findViewById(R.id.EditPassword);

        ButtonRectangle button = (ButtonRectangle) findViewById(R.id.btnIngresar);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidNumber(usuario.getText().toString())) {
                    usuario.setError(getResources().getString(R.string.login_usuario));
                    usuario.setFocusableInTouchMode(true);
                    usuario.requestFocus();
                } else if (isValidNumber(password.getText().toString())) {
                    password.setError(getResources().getString(R.string.login_password));
                    password.setFocusableInTouchMode(true);
                    password.requestFocus();
                } else {
                    ValidatorLogin();
                }

            }
        });
    }

    private void ValidatorLogin() {

        progress.show();

        String url = String.format("%1$s%2$s", getString(R.string.url_base),"ValidateLogin");
        RequestQueue rq = Volley.newRequestQueue(this);

        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        // response
                        if (!parseJSON(response)){
                            Toast.makeText(ActLogin.this, "El usuario no esta registrado.", Toast.LENGTH_LONG).show();
                        }else{
                            startActivity(new Intent(ActLogin.this, Accounts.class));
                            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Toast.makeText(ActLogin.this, "No tiene conexión a internet ", Toast.LENGTH_LONG).show();
                        progress.dismiss();
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

        boolean indicant = false;

        if (!json.equals("[]")){
            try {
                Gson gson = new Gson();
                Estudiante.setEstudiante(gson.fromJson(json, Estudiante.class));
                indicant = true;
            }catch (IllegalStateException ex) {
                ex.printStackTrace();
                indicant = false;
            }
        }

        progress.dismiss();

        return indicant;
    }

    private boolean isValidNumber(String number) {
        return number == null || number.length() == 0;
    }

}
