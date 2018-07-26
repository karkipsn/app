package com.example.colors2web.zummix_app.Activities;

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
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.CustomerDetailsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.CusGroupDetails.CustomerGroup;
import com.example.colors2web.zummix_app.POJO.CusGroupDetails.Order;
import com.example.colors2web.zummix_app.R;
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
        setContentView(R.layout.activity_cutomer_grp_details);

        mtoolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mrecycleview = findViewById(R.id.recycler_view_second);
        cadapter = new CustomerDetailsAdapter(orderList) ;

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        apiInterface = APIClient.getClient().create(APIInterface.class);


        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager(mlayoutManager);

//       mrecycleview.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        mrecycleview.addItemDecoration(new SimpleItemDecoration(this));
        mrecycleview.setItemAnimator(new DefaultItemAnimator());

        mrecycleview.setAdapter(cadapter);
        String cus_id = getIntent().getStringExtra("cus_id");
        Log.d("cus_id",cus_id);

       loadAdapter(cus_id,email, password);

    }

    private void loadAdapter(String cus_id,String email, String password) {

        String Path = cus_id;
        Call<CustomerGroup> call = apiInterface.getCustomerDetails(email,password,Path);
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
                            String unique_items= String.valueOf(order.get(i).getTotalUniqueItems());
                            String order_type = order.get(i).getOrderType();
                            String field_office = order.get(i).getCustomerOfficeName();
                            String state = order.get(i).getCustomerState();
                            String country = order.get(i).getCustomerCountry();
                            String zip = order.get(i).getCustomerZip();
                            String ship_method = order.get(i).getShipMethod();
                            String order_date = order.get(i).getOrderDate();


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

                            orderList.add(dis); // must be the object of empty list initiated

                        }
                        cadapter.updateAnswers(orderList);//adapter's content is updated and update function is called;
                        //send parameters according to urs adapter view setup.);


                    } else {
                        String d = response.body().getMessage();
                    }
                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(GroupCusDetails.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CustomerGroup> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(GroupCusDetails.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
