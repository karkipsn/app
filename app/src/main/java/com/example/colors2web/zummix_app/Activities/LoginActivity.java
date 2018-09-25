package com.example.colors2web.zummix_app.Activities;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.colors2web.zummix_app.Activities.Navigation.HomeActivity;
import com.example.colors2web.zummix_app.POJO.login.Login;
import com.example.colors2web.zummix_app.POJO.login.ResponseLogin;
import com.example.colors2web.zummix_app.POJO.login.UserLogin;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.tv_email)
    EditText email;

    @BindView(R.id.tv_password)
    EditText password;


    @BindView(R.id.btnlogin)
    Button signin;

    String u_email, u_password;
    APIInterface apiInterface;
    ImageView imageView;
    @BindView(R.id.coordinatorLayout)
    CoordinatorLayout coordinatorLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
        imageView = findViewById(R.id.imageview_round);

//        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        Glide.with(this).load(R.mipmap.ic_launcher_zummix)
//                .bitmapTransform(new BlurTransformation())
//                .into(imageView);

        Glide.with(this).load(R.drawable.ic_launcher_voxship).
                apply(RequestOptions.bitmapTransform(new BrightnessFilterTransformation())).into(imageView);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                u_email = email.getText().toString();
                u_password = password.getText().toString();

                login(u_email, u_password);

            }
        });

    }

    private void login(final String u_email, final String u_password) {

        Login login = new Login(u_email, u_password);
        Log.d("email", u_email);
        Log.d("password", u_password);

        signin.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        Call<ResponseLogin> call = apiInterface.dologin(login);
        call.enqueue(new Callback<ResponseLogin>() {
            @SuppressLint("ResourceAsColor")
            @Override
            @Nullable
            public void onResponse(Call<ResponseLogin> call, Response<ResponseLogin> response) {

                if (response.isSuccessful()) {
                    //just check for the status 200,400 and handle it
                    //if u want the same method of extraction for 200 and 401 it wont works

                    ResponseLogin msg = response.body();
                    UserLogin user = msg.getUser();

                    String toks = msg.getToken();
                    String group_type = user.getGroupType();
                    String l_id = String.valueOf(user.getId());



                    if (group_type.equals("Super Admin")) {

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("email", u_email);
                        editor.putString("password", u_password);
                        editor.putString("group_type", group_type);
                        editor.putString("l_id", l_id);
                        Log.d("email", u_email);
                        editor.apply();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        signin.setEnabled(true);

                        Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        Toast.makeText(getApplicationContext(), msg.getType().toString() + "\n" + msg.getMessage().toString(), Toast.LENGTH_SHORT).show();


                    } else {

                        Toast.makeText(getApplicationContext(), "Invalid Credentials", Toast.LENGTH_SHORT).show();

                        final Snackbar snackbar = Snackbar
                                .make(coordinatorLayout, "Invalid Credentials", Snackbar.LENGTH_LONG);

                        // Changing message text color
                        snackbar.setActionTextColor(Color.RED);

                        // Changing action button text color
                        View sbView = snackbar.getView();
                        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setTextColor(Color.YELLOW);
                        snackbar.show();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }
                } else if (response.code() == 401) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    signin.setEnabled(true);

                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    signin.setEnabled(true);

                    final Snackbar snackbar = Snackbar
                            .make(coordinatorLayout, "Link not found!", Snackbar.LENGTH_LONG)
                            .setAction("RETRY", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
//                                    onStart();

//                                    finish();
//                                    startActivity(getIntent());

                                    login(u_email, u_password);
                                }
                            });

                    // Changing message text color
                    snackbar.setActionTextColor(Color.RED);

                    // Changing action button text color
                    View sbView = snackbar.getView();
                    TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
                    textView.setTextColor(Color.YELLOW);
                    snackbar.show();

//                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
//                    Log.d("Error", response.errorBody().toString());


                } else {

                    if (progressDialog.isShowing())
                        progressDialog.dismiss();
                    signin.setEnabled(true);
                    Toast.makeText(LoginActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseLogin> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing())
                    progressDialog.dismiss();
                signin.setEnabled(true);
                Toast.makeText(LoginActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }

        });

    }

}
