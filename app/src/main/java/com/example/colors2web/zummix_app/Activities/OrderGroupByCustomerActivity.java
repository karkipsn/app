package com.example.colors2web.zummix_app.Activities;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import android.text.Layout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import android.app.SearchManager;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;

import com.example.colors2web.zummix_app.Adapter.GroupByCusADapter;
import com.example.colors2web.zummix_app.ItemDecoration.GridSpacingItemDecoration;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderGroupByCustomerActivity extends AppCompatActivity {
    
    android.support.v7.widget.Toolbar toolbar;

    APIInterface apiInterface;
    RecyclerView mrecyclerView;
    GroupByCusADapter radapter;
    SearchView searchView;

    List<Order> CusList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grp_by_customer);


       toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
       getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        apiInterface = APIClient.getClient().create(APIInterface.class);
        radapter = new GroupByCusADapter(getApplicationContext(),CusList);

        mrecyclerView =  findViewById(R.id.recycler_view_first);

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(layoutManager);


//        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getApplicationContext(), LinearLayoutManager.HORIZONTAL, 16));
//        //mrecyclerView.addItemDecoration(new SimpleItemDecoration(this));
//        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(radapter);
        loadAdapter(email,password);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Spinner spinner = (Spinner)findViewById(R.id.sp);
//                ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
//                spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                spinner.setAdapter(spinnerAdapter);
//                spinnerAdapter.add("value");
//                spinnerAdapter.notifyDataSetChanged();

            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                String OPath = String.valueOf(searchView.getQuery());

                Intent intent = new Intent(getApplicationContext(),OrderSearch2Activity.class);
                intent.putExtra("OPath",OPath);
                startActivity(intent);

               return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }


    });
        return true;}


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }


    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
    private void loadAdapter(String email, String password) {

        Call<OrdGrpByCus> call = apiInterface.getOrdersbyGroup(email,password);
        call.enqueue(new Callback<OrdGrpByCus>() {
            @Override
            public void onResponse(Call<OrdGrpByCus> call, Response<OrdGrpByCus> response) {

                if (response.isSuccessful()) {
                    OrdGrpByCus resp1 = response.body();

                    List<Order> order = resp1.getOrders();
                    Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {

                            Order dis = new Order();


                            String name = order.get(i).getCustomerName();
                            String vip = String.valueOf(order.get(i).getNoOfVipDeliveryOrders());
                            String shipto = String.valueOf(order.get(i).getNoOfShipToOrders());
                            String field = String.valueOf(order.get(i).getNoOfWillCallsOrders());
                            String cus_id = String.valueOf(order.get(i).getCustomerId());

                            dis.setCustomerName(name);
                            dis.setNoOfVipDeliveryOrders(Long.valueOf(vip));
                            dis.setNoOfShipToOrders(Long.valueOf(shipto));
                            dis.setNoOfWillCallsOrders(Long.valueOf(field));
                            dis.setCustomerId(Long.valueOf(cus_id));

                            CusList.add(dis); // must be the object of empty list initiated
                        }


                        radapter.updateAnswers(CusList);//adapter's content is updated and update function is called;
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
                    Toast.makeText(OrderGroupByCustomerActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrdGrpByCus> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(OrderGroupByCustomerActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
