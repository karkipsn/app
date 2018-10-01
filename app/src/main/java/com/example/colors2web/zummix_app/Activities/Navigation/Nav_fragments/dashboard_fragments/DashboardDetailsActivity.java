package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.DashboardAdapters.DetailsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Cron_Details.CronDetailsResponse;
import com.example.colors2web.zummix_app.POJO.Cron_Details.Order;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardDetailsActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "SEARCH_FRAGMENT";
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    APIInterface apiInterface;

    @BindView(R.id.recycle_view)
    RecyclerView mrecyclerView;
    DetailsAdapter iadapter;
    main_dashboard_fragment fragment;
    SwipeRefreshLayout mSwipeRefreshLayout;

    List<Order> ItmList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                DashboardDetailsActivity.super.onBackPressed();
//                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//               fragment.refresh();

                finish();

            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);



        iadapter = new DetailsAdapter(ItmList, DashboardDetailsActivity.this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(layoutManager);


        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getApplicationContext(), LinearLayoutManager.HORIZONTAL, 16));
//        mrecyclerView.addItemDecoration(new SimpleItemDecoration(this));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(iadapter);
        loadAdapter();



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadAdapter();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void loadAdapter() {
//        UList
        if (getIntent() != null) {

//            ArrayList<Order> logs1 = this.getIntent().getParcelableArrayListExtra("UList");

            String order_status = this.getIntent().getStringExtra("order_status");
            String customer_id = this.getIntent().getStringExtra("customer_id");
            String order_type = this.getIntent().getStringExtra("order_type");
            String from = this.getIntent().getStringExtra("from");
            String to = this.getIntent().getStringExtra("to");
//            iadapter.updateAnswers(logs1);

            call_details_api(order_status, customer_id, order_type, from, to);
        }
    }

    private void call_details_api(String order_status, String customer_id, String order_type, String from, String to) {

        final ArrayList<Order> UList = new ArrayList<>();

       APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(DashboardDetailsActivity.this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        final ProgressDialog progressDialog = new ProgressDialog(DashboardDetailsActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<CronDetailsResponse> call = apiInterface.getCronJobsDetails
                (email, password, order_status, customer_id, order_type, from, to);

        call.enqueue(new Callback<CronDetailsResponse>() {
            @Override
            public void onResponse(Call<CronDetailsResponse> call, Response<CronDetailsResponse> response) {

                CronDetailsResponse response1 = response.body();
                if (response.isSuccessful()) {

                    if (response1.getReturnType().equals("success")) {

                        List<Order> order = response1.getOrders();
                        for (int i = 0; i < order.size(); i++) {

                            Order o = new Order();

                            String ono = order.get(i).getOrderNumber();
                            String state = order.get(i).getCustomerState();
                            String country = order.get(i).getCustomerCountry();
                            String zip = order.get(i).getCustomerZip();
                            String ord_status = order.get(i).getOrderStatus();
                            String sip_method = order.get(i).getShipMethod();
                            String ord_date = order.get(i).getOrderDate();

                            o.setOrderNumber(ono);
                            o.setCustomerState(state);
                            o.setCustomerCountry(country);
                            o.setCustomerZip(zip);
                            o.setOrderStatus(ord_status);
                            o.setShipMethod(sip_method);
                            o.setOrderDate(ord_date);

                            UList.add(o);
                        }

                        iadapter.updateAnswers(UList);
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(DashboardDetailsActivity.this, " No data for th date Range", Toast.LENGTH_SHORT).show();


                    }

                } else if (response.code() == 401) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(DashboardDetailsActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(DashboardDetailsActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(DashboardDetailsActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                } else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(DashboardDetailsActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }

            }


            @Override
            public void onFailure(Call<CronDetailsResponse> call, Throwable t) {

                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(DashboardDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
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
        if (getFragmentManager().getBackStackEntryCount()>0) {
            getFragmentManager().popBackStack();
            //finish
        } else {
            super.onBackPressed();
        }
    }
}
