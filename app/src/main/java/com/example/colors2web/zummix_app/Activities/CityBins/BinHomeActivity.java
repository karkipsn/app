package com.example.colors2web.zummix_app.Activities.CityBins;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.CityBins.Adapter.HomeAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBins;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBinsResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BinHomeActivity extends AppCompatActivity {
    HomeAdapter homeAdapter;
    Toolbar toolbar;
    APIInterface apiInterface;
    RecyclerView mrecycleView;
    TextView customer_id,create_bins;
    List<CityBins>BinList = new ArrayList<>();
    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citybins_home);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        customer_id = findViewById(R.id.cus_parent_Id);
        create_bins = findViewById(R.id.create_bins_order);
        create_bins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(BinHomeActivity.this,CreateCityBinsActivity.class);
                startActivity(intent);

            }
        });

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mrecycleView = findViewById(R.id.recycle_view_bins);
        homeAdapter = new HomeAdapter(BinList,getApplicationContext());

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new SimpleItemDecoration(this));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(homeAdapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        
        loadAdapter(email, password);

    }
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

    @Override
    public void onBackPressed() {


        moveTaskToBack(true);

    }


    private void loadAdapter(String email, String password) {

        

        final ProgressDialog progressDialog = new ProgressDialog(BinHomeActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<CityBinsResponse> call = apiInterface.getBinCustomers(email,password);
        call.enqueue(new Callback<CityBinsResponse>() {
            @Override
            public void onResponse(Call<CityBinsResponse> call, Response<CityBinsResponse> response) {

                if (response.isSuccessful()) {
                    CityBinsResponse resp1 = response.body();

                    List<CityBins> cus = resp1.getCityBins();

                    Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {

                            CityBins bin = new CityBins();

                            String no_of_bins = String.valueOf(cus.get(i).getmNoOfBins());
                            String company_name = cus.get(i).getCompanyName();
                            String company_email = cus.get(i).getCompanyEmail();
                            String customer_id = String.valueOf(cus.get(i).getCustomerId());


                            bin.setmNoOfBins(Long.valueOf(no_of_bins));
                            bin.setCompanyName(company_name);
                            bin.setCompanyEmail(company_email);
//                            bin.setCustomerId(Long.valueOf(customer_id));
                            bin.setCustomerId(customer_id);

                            BinList.add(bin); // must be the object of empty list initiated

                        }
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        homeAdapter.updateAnswers(BinList);//adapter's content is updated and update function is called;

                        //send parameters according to urs adapter view setup.);


                    } else {
                        String d = response.body().getMessage();
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
                }else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }
                else {
                    Toast.makeText(BinHomeActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(BinHomeActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
