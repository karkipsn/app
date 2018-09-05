package com.example.colors2web.zummix_app.Activities;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.CustomerDetailsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.CusGroupDetails.CustomerGroup;
import com.example.colors2web.zummix_app.POJO.CusGroupDetails.Order;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GroupCusDetails extends AppCompatActivity {

    Toolbar mtoolbar;
    RecyclerView mrecycleview;
    APIInterface apiInterface;
    CustomerDetailsAdapter cadapter;

    List<Order> orderList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);


        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

       mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               finish();
           }
       });

        mrecycleview = findViewById(R.id.recycle_view);

        cadapter = new CustomerDetailsAdapter(getApplicationContext(), orderList);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        apiInterface = APIClient.getClient().create(APIInterface.class);


        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager(mlayoutManager);

//       mrecycleview.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        mrecycleview.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        mrecycleview.setItemAnimator(new DefaultItemAnimator());

        mrecycleview.setAdapter(cadapter);
        String cus_id = getIntent().getStringExtra("cus_id");
        Log.d("cus_id", cus_id);

        loadAdapter(cus_id, email, password);

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

        super.onBackPressed();

//        moveTaskToBack(true);
    }


    private void loadAdapter(String cus_id, String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(GroupCusDetails.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        String Path = cus_id;
        Call<CustomerGroup> call = apiInterface.getCustomerDetails(email, password, Path);
        call.enqueue(new Callback<CustomerGroup>() {
            @Override
            public void onResponse(Call<CustomerGroup> call, Response<CustomerGroup> response) {

                if (response.isSuccessful()) {
                    CustomerGroup resp1 = response.body();

                    List<Order> order = resp1.getOrders();
                    Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {

                            Order dis = new Order();

                            String orders = order.get(i).getOrderNumber();
                            String total_pieces = order.get(i).getTotalPieces();
                            String unique_items = String.valueOf(order.get(i).getTotalUniqueItems());
                            String order_type = order.get(i).getOrderType();
                            String field_office = order.get(i).getCustomerOfficeName();
                            String state = order.get(i).getCustomerState();
                            String country = order.get(i).getCustomerCountry();
                            String zip = order.get(i).getCustomerZip();
                            String ship_method = order.get(i).getShipMethod();
                            String order_date = order.get(i).getOrderDate();
                            String o_id = String.valueOf(order.get(i).getId());
                            String order_status = order.get(i).getOrderStatus();


                            dis.setOrderNumber(orders);
                            dis.setTotalPieces(total_pieces);
                            dis.setTotalUniqueItems(Long.valueOf(unique_items));
                            dis.setOrderType(order_type);
                            dis.setCustomerOfficeName(field_office);
                            dis.setCustomerState(state);
                            dis.setCustomerCity(country);
                            dis.setCustomerZip(zip);
                            dis.setShipMethod(ship_method);
                            dis.setOrderDate(order_date);
                            dis.setId(Long.valueOf(o_id));
                            dis.setOrderStatus(order_status);

                            orderList.add(dis); // must be the object of empty list initiated

                        }
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        cadapter.updateAnswers(orderList);//adapter's content is updated and update function is called;
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

                    Toast.makeText(getApplicationContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    Toast.makeText(GroupCusDetails.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CustomerGroup> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(GroupCusDetails.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
