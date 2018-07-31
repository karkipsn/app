package com.example.colors2web.zummix_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.ItemSearchAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.CustomerItem;
import com.example.colors2web.zummix_app.POJO.ProSearchRes;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemSearchActivity extends AppCompatActivity {


    android.support.v7.widget.Toolbar toolbar;

    APIInterface apiInterface;
    RecyclerView mrecyclerView;
    ItemSearchAdapter iadapter;

    List<CustomerItem> ItmList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_search_first);

        toolbar = findViewById(R.id.toolbar_item);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        iadapter = new ItemSearchAdapter(getApplicationContext(), ItmList);
        mrecyclerView = findViewById(R.id.recycler_view_item);

       RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(layoutManager);


//        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getApplicationContext(), LinearLayoutManager.HORIZONTAL, 16));
       mrecyclerView.addItemDecoration(new SimpleItemDecoration(this));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(iadapter);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        Log.d("PathMail", email);

        final Intent i = getIntent();

        if (i != null) {

            final String Path = i.getExtras().getString("OPath");
            if (Path != null) {
                call(email, password, Path);
            }
        }

    }

    private void call(String email, String password, String path) {

        Call<ProSearchRes> call = apiInterface.getProduct1(email, password, path);
        call.enqueue(new Callback<ProSearchRes>() {
            @Override
            public void onResponse(Call<ProSearchRes> call, Response<ProSearchRes> response) {

                if (response.isSuccessful()) {
                    ProSearchRes resp = response.body();
                    List<CustomerItem> items = resp.getCustomerItems();
                    Toast.makeText(getApplicationContext(), resp.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (items != null) {

                        for (int i = 0; i < items.size(); i++) {

                            CustomerItem itemz = new CustomerItem();

                            String cus_name = items.get(i).getCompanyName();
                            String sku = items.get(i).getItemSkuNumber();
                            String name = items.get(i).getItemName();

                            itemz.setCompanyName(cus_name);
                            itemz.setItemSkuNumber(sku);
                            itemz.setItemName(name);

                            ItmList.add(itemz);

                        }
                        iadapter.updateAnswers(ItmList);

                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    Toast.makeText(ItemSearchActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }}

            @Override
            public void onFailure(Call<ProSearchRes> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(ItemSearchActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });


    }


}