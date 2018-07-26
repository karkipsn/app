package com.example.colors2web.zummix_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.POJO.Login;
import com.example.colors2web.zummix_app.POJO.ResponseLogin;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.tv_email)
    EditText email;

    @BindView(R.id.tv_password)
    EditText password;

    @BindView(R.id.forgot_password)
    TextView fpass;

    @BindView(R.id.btnlogin)
    Button signin;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    String u_email, u_password;
    APIInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                u_email = email.getText().toString();
                u_password = password.getText().toString();
                login(u_email, u_password);

            }
        });

        fpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, ForgotActivity.class);
                startActivity(i);
            }
        });

    }

    private void login(final String u_email, final String u_password) {

        Login login = new Login(u_email, u_password);
        Log.d("email", u_email);
        Log.d("password", u_password);

        Call<ResponseLogin> call = apiInterface.dologin(login);

        call.enqueue(new Callback<ResponseLogin>() {
            @Override
            @Nullable
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (response.isSuccessful()) {
                    //just check for the status 200,400 and handle it
                    //if u want the same method of extraction for 200 and 401 it wont works

                    ResponseLogin msg = response.body();

                    String toks = msg.getToken();

                    Log.d("token", toks);

                    Toast.makeText(getApplicationContext(), msg.getType().toString()+"\n"+msg.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getApplicationContext(), msg.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("email", u_email);
                    editor.putString("password", u_password);
                    Log.d("email",u_email);
                    editor.apply();

//                    Intent i = new Intent(LoginActivity.this, HomePageActivity.class);
//                    startActivity(i);

                    Intent i = new Intent(LoginActivity.this, OrderGroupByCustomerActivity.class);
                    startActivity(i);


                } else if (response.code() == 401) {

//                    ResponseLogin msg1 = response.body();
                   // Toast.makeText(getApplicationContext(), msg1.getType().toString(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), " Authentication Error:"+"\n"+"Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                }
                else if (response.code() == 404) {

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                }else {

                    Toast.makeText(LoginActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
