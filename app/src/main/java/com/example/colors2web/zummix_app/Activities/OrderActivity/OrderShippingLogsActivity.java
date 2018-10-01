package com.example.colors2web.zummix_app.Activities.OrderActivity;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.OrderSearch2Activity;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.OrderShippingAddressLogs.OrderShippingAddressLog;
import com.example.colors2web.zummix_app.POJO.OrderShippingAddressLogs.ShippingLogResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderShippingLogsActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "SEARCH_FRAGMENT";
    APIInterface apiInterface;
    Toolbar toolbar;
    RecyclerView mrecyclerView;
    ShippingLogsAdapter radapter;
    String Order;
    List<OrderShippingAddressLog> OList = new ArrayList<>();
    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        radapter = new ShippingLogsAdapter( OList);

        mrecyclerView = findViewById(R.id.recycle_view);

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
//        mrecycleView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mlayoutManager);
        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(OrderShippingLogsActivity.this, LinearLayoutManager.HORIZONTAL, 16));

        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(radapter);
        loadAdapter(email, password);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadAdapter(email, password);
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });


    }

// TODO:   OrderDetails Page Bata Order Number Lini
//    For now Hardcoded or initialized value is used

    private void loadAdapter(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(OrderShippingLogsActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

//        String ordernumber = String.valueOf(10044);

        Intent i = getIntent();

        if(i!=null){
            Order = i.getExtras().getString("o_id_logs","");
        }

//        if (i.getExtras() != null) {
//            Bundle b = i.getExtras();
//            String od = b.getString("o_id_logs");
//            Log.d("o_id_logs",od);
////       Both does the same thing . Either can be processed through intent or bundle
//      }

        Call<ShippingLogResponse>call =apiInterface.getOrderShippingLogs(email,password,Order);
        call.enqueue(new Callback<ShippingLogResponse>() {
            @Override
            public void onResponse(Call<ShippingLogResponse> call, Response<ShippingLogResponse> response) {

                if(response.isSuccessful()){

                ShippingLogResponse response1 = response.body();
                String returntype = response1.getReturnType();
                switch (returntype){

                    case "success":
                        List< OrderShippingAddressLog> log = response1.getOrderShippingAddressLogs();
                        ArrayList<OrderShippingAddressLog>LogList = new ArrayList<>();
                        if(log!=null){
                            for(OrderShippingAddressLog os:log) {

                                OrderShippingAddressLog mList = new OrderShippingAddressLog();
                                String fname = os.getCustomerFname();
                                String lname = os.getCustomerLname();
                                String email = os.getCustomerFname();
                                String phone = os.getCustomerFname();
                                String add1 = os.getCustomerFname();
                                String city = os.getCustomerFname();
                                String state = os.getCustomerFname();
                                String zip = os.getCustomerFname();
                                String country = os.getCustomerFname();
                                String comment = os.getCustomerFname();

                                mList.setCustomerFname(fname);
                                mList.setCustomerLname(lname);
                                mList.setCustomerEmail(email);
                                mList.setCustomerPhone1(phone);
                                mList.setCustomerAddress1(add1);
                                mList.setCustomerCity(city);
                                mList.setCustomerState(state);
                                mList.setCustomerZip(zip);
                                mList.setCustomerCountry(country);
                                mList.setComment(comment);
                                LogList.add(mList);
                            }
                            radapter.updateAnswers(LogList);
                            if(progressDialog.isShowing()){
                                progressDialog.dismiss();
                            }

                        }
                        break;

                    case "error":
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getApplicationContext(),response1.getMessage(),Toast.LENGTH_SHORT).show();
                        break;
                }
                } else if (response.code() == 401) {

                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                }  else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(getApplicationContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Toast.makeText(OrderShippingLogsActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ShippingLogResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(OrderShippingLogsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing()){
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
       super.onBackPressed();
//      moveTaskToBack(true);
//
//        Intent back = new Intent(this, OrderSearch2Activity.class);
//        back.putExtra("back_id",Order);
//        Log.d("back",Order);
//        setResult(RESULT_OK, back);
//        finish();
    }
}
