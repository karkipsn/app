package com.example.colors2web.zummix_app.Activities.CityBins;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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

import com.example.colors2web.zummix_app.Adapter.BinsAdapter.BinAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
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

public class BinBinActivity extends AppCompatActivity {
    BinAdapter binAdapter;
    Toolbar toolbar;
    APIInterface apiInterface;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String cus_id;
    String cus_id_inner;
    RecyclerView mrecycleView;
    TextView create_bins;
    List<CityBins> BinList = new ArrayList<>();
    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citybins_home);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);


        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BinBinActivity.super.onBackPressed();
            }
        });

        mrecycleView = findViewById(R.id.recycle_view_bins);
        binAdapter = new BinAdapter(BinList, getApplicationContext());

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(BinBinActivity.this, LinearLayoutManager.HORIZONTAL, 16));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(binAdapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        loadAdapter(email, password);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadAdapter(email, password);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        create_bins = findViewById(R.id.create_bins_order);

        create_bins.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BinBinActivity.this, CreateCityBinsActivity.class);
                Bundle b = new Bundle();
                b.putString("cus_id_inner", cus_id_inner);
                intent.putExtras(b);
                Log.d("cus_id_inner", cus_id_inner);
                startActivity(intent);
                finish();
            }
        });

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

//       moveTaskToBack(true);
       super.onBackPressed();

    }

    private void loadAdapter(String email, String password) {

        Intent intent = getIntent();
        if (intent != null) {

            cus_id = intent.getStringExtra("customer_id");
            Log.d("customer_id", cus_id);
            cus_id_inner = cus_id;
        }


        final ProgressDialog progressDialog = new ProgressDialog(BinBinActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<CityBinsResponse> call = apiInterface.getBins(email, password, cus_id);
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

                            String city = cus.get(i).getCity();
                            Long id = cus.get(i).getId();
                            String company_name = cus.get(i).getCompanyName();
                            String created_at = cus.get(i).getCreatedAt();
//                            String customer_id = String.valueOf(cus.get(i).getCustomerId());
                            String customer_id = cus.get(i).getCustomerId();
                            String mbin =cus.get(i).getBin();
                            String mship_to_name =cus.get(i).getShipToName();
                            String maddress1 =cus.get(i).getAddress1();
                            String maddress2 =cus.get(i).getAddress2();
                            String mcity =cus.get(i).getCity();
                            String mcountry =cus.get(i).getCountry();
                            String mstate =cus.get(i).getState();
                            String mzip =cus.get(i).getZip();
                            String mmanager1_name =cus.get(i).getManager1Name();
                            String mmanager1_email =cus.get(i).getManager1Email();
                            String mmanager1_phone =cus.get(i).getmManager1Phone();
                            String mmanager2_name =cus.get(i).getManager2Name();
                            String mmanager2_email =cus.get(i).getManager2Email();
                            String mcompany_email =cus.get(i).getCompanyEmail();
                            String mmanager2_phone =cus.get(i).getmManager2Phone();


                            bin.setCity(city);
                            bin.setId(id);
                            bin.setCompanyName(company_name);
                            bin.setCreatedAt(created_at);
                            bin.setCustomerId(customer_id);
                            bin.setBin(mbin);
                            bin.setShipToName(mship_to_name);
                            bin.setAddress1(maddress1);
                            bin.setAddress2(maddress2);
                            bin.setCity(mcity);
                            bin.setCountry(mcountry);
                            bin.setState(mstate);
                            bin.setZip(mzip);
                            bin.setManager1Name(mmanager1_name);
                            bin.setManager1Email(mmanager1_email);
                            bin.setManager2Name(mmanager2_name);
                            bin.setManager2Email(mmanager2_email);
                            bin.setmManager1Phone(mmanager1_phone);
                            bin.setmManager2Phone(mmanager2_phone);

                            BinList.add(bin); // must be the object of empty list initiated

                        }
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        binAdapter.updateAnswers(BinList);//adapter's content is updated and update function is called;

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
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(BinBinActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(BinBinActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}

