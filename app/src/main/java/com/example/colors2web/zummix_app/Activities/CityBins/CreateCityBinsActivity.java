package com.example.colors2web.zummix_app.Activities.CityBins;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;

import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBins;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBinsResponse;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CreateCityBinsActivity extends AppCompatActivity{

    @BindView(R.id.item_sales_spinner)
    AutoCompleteTextView mspinner;

    @BindView(R.id.city_create_bin)
    TextView mbin;

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
    String cus_id;
    Intent intent;
    String bin_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citybin_create);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String l_id = preferences.getString("l_id", "");


                 intent = getIntent();
                if (intent.getExtras() != null) {
                    Bundle b = intent.getExtras();

//                    String customer_id = intent.getExtras().getString("cus_id_inner");
                    String customer_id = b.getString("cus_id_inner");

                    Log.d("cus_id_inner1",customer_id);
                    mspinner.setVisibility(View.GONE);
                    cus_id = customer_id;

                }else{
                    mspinner.setVisibility(View.VISIBLE);
                    spinner(email, password);
                }


        msubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String bin = mbin.getText().toString();
                String ship_to_name = mshipname.getText().toString();
                String address1 = madd1.getText().toString();
                String address2 = madd2.getText().toString();
                String city = mcity.getText().toString();
                String country = mcountry.getText().toString();
                String state = mstate.getText().toString();
                String zip = mzip.getText().toString();
                String manager1_name = mname.getText().toString();
                String manager1_email = mmemail.getText().toString();
                String manager1_phone = mmphone.getText().toString();
                String manager2_name = mmphone2.getText().toString();
                String manager2_email = mmemail2.getText().toString();
                String manager2_phone = mmphone2.getText().toString();
                String created_by = l_id;
                String updated_by = l_id;
                Log.d("l_id",l_id);
                Log.d("customer_id",cus_id);


               CityBins binny = new CityBins(address1,address2,bin,city,country,cus_id,manager1_email,manager1_name,manager2_email,
                                manager2_name,manager2_phone,manager1_phone,ship_to_name,state,zip,created_by,updated_by);
//                call APi with this constructor

                call_api(email, password, binny);


            }

    private void call_api(String email, String password, CityBins bins) {

        final ProgressDialog progressDialog = new ProgressDialog(CreateCityBinsActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<CityBinsResponse> call = apiInterface.CreateCitybin(email,password,bins);

        call.enqueue(new Callback<CityBinsResponse>() {
            @Override
            public void onResponse(Call<CityBinsResponse> call, Response<CityBinsResponse> response) {

                if (response.isSuccessful()){

                    Log.d("rturntype",response.body().getReturnType());

                    CityBinsResponse resp1 = response.body();

                    if(resp1.getReturnType().equals("success")){

                        Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_LONG).show();
                        Log.d("create","Success");

                        if (intent.getExtras() != null) {

                            Intent intent1 = new Intent(CreateCityBinsActivity.this,BinBinActivity.class);
                            intent1.putExtra("customer_id",cus_id);
                            startActivity(intent1);
                            finish();
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();

                            }


                        }else{
                            Intent intent1 = new Intent(CreateCityBinsActivity.this,BinHomeActivity.class);

                            startActivity(intent1);
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();

                            }

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
                    Toast.makeText(CreateCityBinsActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(CreateCityBinsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }
        });
    }

    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu2, menu);
        ImageView img;

        img = (ImageView) menu.findItem(R.id.image).getActionView();
        img.setImageResource(android.R.drawable.ic_menu_search);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.frame_toolbar, new SearchFragment()).
                        addToBackStack(TAG_FRAGMENT).commit();
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }


    private void spinner(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(CreateCityBinsActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<OrdGrpByCus> call = apiInterface.getOrdersbyGroup(email, password);
        call.enqueue(new Callback<OrdGrpByCus>() {
            @Override
            public void onResponse(Call<OrdGrpByCus> call, Response<OrdGrpByCus> response) {

                if (response.isSuccessful()) {
                    OrdGrpByCus resp1 = response.body();

                    List<Order> order = resp1.getOrders();
                    Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (order != null) {

                        ArrayList<SpinnerPojo> countryList = new ArrayList<>();

//                for spinner try
                        for (int i = 0; i < order.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = order.get(i).getCustomerName();
                            Long cus_id = order.get(i).getCustomerId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            countryList.add(order1);
//
                        }
                        Log.d("spinner_list", countryList.toString());


                        mspinner.setAdapter(new ArrayAdapter<SpinnerPojo>(CreateCityBinsActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, countryList));

                        mspinner.setCursorVisible(false);
                        mspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mspinner.showDropDown();
//                                selection = (String) parent.getItemAtPosition(position);

                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                                cus_id = String.valueOf(sp.getCus_id());


                                Toast.makeText(getApplicationContext(), "Cus ID: " + sp.getCus_id() + ",  " +
                                        " Name : " + sp.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        mspinner.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mspinner.showDropDown();
                            }
                        });

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getApplicationContext(), d, Toast.LENGTH_SHORT).show();
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
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(CreateCityBinsActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrdGrpByCus> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(CreateCityBinsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        });
    }
}
