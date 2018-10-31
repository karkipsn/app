package com.example.colors2web.zummix_app.Activities.OrderActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import com.example.colors2web.zummix_app.Activities.TicketActivity.TicketNavigActivity;
import com.example.colors2web.zummix_app.Adapter.Order_Adapters.TrackingAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.OrderTrack.OrderDetail;
import com.example.colors2web.zummix_app.POJO.OrderTrack.OrdertrackResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrackOrderSearchActivity extends AppCompatActivity {


    APIInterface apiInterface;
    Toolbar toolbar;
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView mrecyclerView;
    TrackingAdapter tadapter;
    private String Path;

    List<OrderDetail> ordList = new ArrayList<>();

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
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                finish();
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                startActivity(new Intent(TrackOrderSearchActivity.this, TicketNavigActivity.class));
                isDestroyed();

            }
        });

        apiInterface = APIClient.getClient().create(APIInterface.class);

        tadapter = new TrackingAdapter(ordList, getApplicationContext());
        mrecyclerView = findViewById(R.id.recycle_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(layoutManager);

        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayout.HORIZONTAL, 16));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setAdapter(tadapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        Log.d("PathMail", email);

        final Intent i = getIntent();

        if (i != null) {

            Path = i.getExtras().getString("OPath");
        }
        call_next(email, password, Path);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ordList.clear();
                call_next(email, password, Path);
                mSwipeRefreshLayout.setRefreshing(false);
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

    @Override
    public void onBackPressed() {
//       moveTaskToBack(true);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        super.onBackPressed();
    }


    private void call_next(String email, String password, String Path) {

        final ProgressDialog progressDialog = new ProgressDialog(TrackOrderSearchActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<OrdertrackResponse> call = apiInterface.getOrderByTrack(email, password, Path);

        call.enqueue(new Callback<OrdertrackResponse>() {
            @Override
            public void onResponse(Call<OrdertrackResponse> call, Response<OrdertrackResponse> response) {

                if (response.isSuccessful()) {

                    OrdertrackResponse resp1 = response.body();
                    List<OrderDetail> List = resp1.getOrderDetails();

                    if (List != null) {

                        for (int i = 0; i < List.size(); i++) {

                            OrderDetail ordT = new OrderDetail();


                            String id = String.valueOf(List.get(i).getId());
                            String email = List.get(i).getCustomerEmail();
                            String ono = List.get(i).getOrderNumber();
                            String ostatus = List.get(i).getOrderStatus();
                            String trac_code = List.get(i).getTrackingCode();
                            String ship_to = List.get(i).getShipTo();
                            String ship_method = List.get(i).getShipMethod();
                            String emp_id = List.get(i).getEmployeeId();
                            String cus_ofc = List.get(i).getCustomerOfficeName();
                            String odate = List.get(i).getOrderDate();


                            ordT.setId(Long.valueOf(id));
                            ordT.setCustomerEmail(email);
                            ordT.setOrderNumber(ono);
                            ordT.setOrderStatus(ostatus);
                            ordT.setTrackingCode(trac_code);
                            ordT.setShipTo(ship_to);
                            ordT.setShipMethod(ship_method);
                            ordT.setEmployeeId(emp_id);
                            ordT.setCustomerOfficeName(cus_ofc);
                            ordT.setOrderDate(odate);

                            ordList.add(ordT);

                        }
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        tadapter.updateAnswers(ordList);


                    } else {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
//                        head.setText(d);
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

                    Toast.makeText(getApplicationContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(TrackOrderSearchActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrdertrackResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(TrackOrderSearchActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
