package com.example.colors2web.zummix_app.Activities.CityBins;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.POJO.CityBins.CityBins;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBinsResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCityBinsActivity extends AppCompatActivity {

    @BindView(R.id.item_sales_spinner)
    AutoCompleteTextView mspinner;

    @BindView(R.id.city_create_bin)
    TextView mbin;

    @BindView(R.id.heading_text)
    TextView headings;


    @BindView(R.id.city_create_shipname)
    TextView mshipname;

    @BindView(R.id.city_create_add1)
    TextView madd1;

    @BindView(R.id.city_create_add2)
    TextView madd2;

    @BindView(R.id.city_create_city)
    TextView mcity;

    @BindView(R.id.city_create_country)
    TextView mcountry;

    @BindView(R.id.city_create_state)
    TextView mstate;

    @BindView(R.id.city_create_zip)
    TextView mzip;

    @BindView(R.id.city_create_m1name)
    TextView mname;

    @BindView(R.id.city_create_m1phone)
    TextView mmphone;

    @BindView(R.id.city_create_m2name)
    TextView mname2;

    @BindView(R.id.city_create_m2phone)
    TextView mmphone2;

    @BindView(R.id.city_create_m1email)
    TextView mmemail;

    @BindView(R.id.city_create_m2email)
    TextView mmemail2;

    @BindView(R.id.btn_citybins_create)
    Button msubmit;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    APIInterface apiInterface;
    ImageView img;
    String bin_id,cus_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citybin_create);
        ButterKnife.bind(this);

        mspinner.setVisibility(View.GONE);
        headings.setVisibility(View.GONE);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");


        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = getIntent();
                if (intent != null) {
                     cus_id = intent.getExtras().getString("customer_id");
                     bin_id = intent.getExtras().getString("bin_id");
                     Log.d("bin_id",bin_id);
                }


                String bin =mbin.getText().toString();
               String ship_to_name = mshipname.getText().toString();
               String address1 = madd1.getText().toString();
               String address2 = madd2.getText().toString();
               String city = mcity.getText().toString();
               String country = mcountry.getText().toString();
               String state = mstate.getText().toString();
               String zip = mzip.getText().toString();
               String manager1_name = mname.getText().toString();
                String manager1_email = mname2.getText().toString();
                String manager1_phone = mmphone.getText().toString();
               String manager2_name = mmphone2.getText().toString();
               String manager2_email = mmemail2.getText().toString();
                String manager2_phone = mmemail.getText().toString();


                CityBins cityBins = new CityBins(bin,ship_to_name ,address1 ,address2 ,city ,country ,state ,zip ,
                        manager1_name ,manager1_email ,manager1_phone ,manager2_name ,manager2_email ,manager2_phone);

//                call APi with this constructor
                Call<CityBinsResponse> call = apiInterface.putCitybin(email,password,bin_id,cityBins);
                final ProgressDialog progressDialog = new ProgressDialog(UpdateCityBinsActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();
                call.enqueue(new Callback<CityBinsResponse>() {
                    @Override
                    public void onResponse(Call<CityBinsResponse> call, Response<CityBinsResponse> response) {

                        if (response.isSuccessful()){
                            CityBinsResponse resp1 = response.body();

                            if(resp1.getReturnType().equals("success")){
                                Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();

                                Intent intent1 = new Intent(UpdateCityBinsActivity.this,BinBinActivity.class);
                            intent1.putExtra("customer_id",cus_id);
                            startActivity(intent1);
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();

                                }
                            }
                            else {
                                String d = response.body().getMessage();
                                Toast.makeText(getApplicationContext(),d,Toast.LENGTH_LONG).show();

                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();

                            }
                            }
                        } else if (response.code() == 401) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());


                        } else if (response.code() == 404) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        } else if (response.code() == 500) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        } else {
                            Toast.makeText(UpdateCityBinsActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<CityBinsResponse> call, Throwable t) {
                        call.cancel();
                        Log.e("response-failure", t.toString());
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(UpdateCityBinsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu2, menu);

        img = (ImageView) menu.findItem(R.id.image).getActionView();
        img.setImageResource(android.R.drawable.ic_menu_search);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_toolbar, new SearchFragment()).commit();
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.image:

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_toolbar, new SearchFragment()).addToBackStack(null).commit();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}