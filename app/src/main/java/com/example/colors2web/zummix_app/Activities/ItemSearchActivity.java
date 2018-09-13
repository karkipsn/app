package com.example.colors2web.zummix_app.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.view.GravityCompat;
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

import com.example.colors2web.zummix_app.Adapter.Order_Adapters.ItemSearchAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.ProductSearch.CustomerItem;
import com.example.colors2web.zummix_app.POJO.ProductSearch.ProSearchRes;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemSearchActivity extends AppCompatActivity {

    private static final String TAG_FRAGMENT ="SEARCH_FRAGMENT" ;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    APIInterface apiInterface;
    RecyclerView mrecyclerView;
    ItemSearchAdapter iadapter;

    List<CustomerItem> ItmList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        iadapter = new ItemSearchAdapter(getApplicationContext(), ItmList);
        mrecyclerView = findViewById(R.id.recycle_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(layoutManager);


       mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getApplicationContext(), LinearLayoutManager.HORIZONTAL, 16));
//        mrecyclerView.addItemDecoration(new SimpleItemDecoration(this));
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);

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
                overridePendingTransition(R.anim.search_push_left_in, R.anim.search_push_left_out);

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.image:
                return true;

            case R.id.image_try:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

    private void call(String email, String password, String path) {

        final ProgressDialog progressDialog = new ProgressDialog(ItemSearchActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

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
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    } else if (response.code() == 401) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        Toast.makeText(getApplicationContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                        Log.d("Error", response.errorBody().toString());


                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(ItemSearchActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ProSearchRes> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(ItemSearchActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

}
