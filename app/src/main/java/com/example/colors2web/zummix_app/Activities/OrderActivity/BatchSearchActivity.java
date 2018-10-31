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
import com.example.colors2web.zummix_app.Adapter.BatchAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.BatchNumber.BatchOrder;
import com.example.colors2web.zummix_app.POJO.BatchNumber.BatchResponse;
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

public class BatchSearchActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "SEARCH_FRAGMENT";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.recycle_view)
    RecyclerView mrecycleView;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mswipeRefreshLayout;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    APIInterface apiInterface;
    BatchAdapter badapter;
    List<BatchOrder> BatchList = new ArrayList<>();
    String bid;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                startActivity(new Intent(BatchSearchActivity.this, TicketNavigActivity.class));
                isDestroyed();
            }
        });


//        PickAdapter = new Pick_Velocity_BoxAdapter(BoxList,ItemsList);
        badapter = new BatchAdapter(getApplicationContext(), BatchList);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(this, LinearLayout.HORIZONTAL,16));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(badapter);
        mswipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BatchSearchActivity.super.onBackPressed();
            }
        });

        Intent intent = getIntent();
        if (intent != null) {
            bid = intent.getExtras().getString("biid");

        }
        loadAdapter(bid);

        mswipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                BatchList.clear();
                loadAdapter(bid);
                mswipeRefreshLayout.setRefreshing(false);
            }
        });

    }

    private void loadAdapter(String bids) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(BatchSearchActivity.this);
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");

        final ProgressDialog progressDialog = new ProgressDialog(BatchSearchActivity.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<BatchResponse> call = apiInterface.getBatchResponse(email, password, bids);
        call.enqueue(new Callback<BatchResponse>() {
            @Override
            public void onResponse(Call<BatchResponse> call, Response<BatchResponse> response) {


                if (response.isSuccessful()) {
                    BatchResponse resp1 = response.body();

                    List<BatchOrder> cus = resp1.getBatchOrders();

                    Toast.makeText(BatchSearchActivity.this, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        BatchOrder batchOrder = new BatchOrder();
                        String batch_no = null;
                        for (int i = 0; i < cus.size(); i++) {

                            batch_no = String.valueOf(cus.get(0).getBatchNumber());

                            Long id = cus.get(i).getId();
                            String store = cus.get(i).getCustomerName();
                            String order = cus.get(i).getOrderNumber();
                            String ordertype = cus.get(i).getOrderType();
                            String total = cus.get(i).getTotalQty();
                            String remainng = cus.get(i).getTotalRemainingQty();
                            String created = cus.get(i).getCreatedAt();
                            String shipto = cus.get(i).getShipToName();
                            String ord_status = cus.get(i).getOrderStatus();
                            String is_refunded = cus.get(i).getIsRefunded();
                            String cus_ofc = cus.get(i).getCustomerOfficeName();

                            batchOrder.setId(id);
                            batchOrder.setCustomerName(store);
                            batchOrder.setOrderNumber(order);
                            batchOrder.setOrderType(ordertype);
                            batchOrder.setTotalQty(total);
                            batchOrder.setTotalRemainingQty(remainng);
                            batchOrder.setCreatedAt(created);
                            batchOrder.setShipToName(shipto);
                            batchOrder.setOrderStatus(ord_status);
                            batchOrder.setIsRefunded(is_refunded);
                            batchOrder.setCustomerOfficeName(cus_ofc);
                        }

                        toolbar.setTitle("Batch:" + batch_no);
                        BatchList.add(batchOrder);
                        badapter.updateAnswers(BatchList);

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(BatchSearchActivity.this, d, Toast.LENGTH_LONG).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(BatchSearchActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(BatchSearchActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(BatchSearchActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(BatchSearchActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<BatchResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(BatchSearchActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
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

}
