package com.example.colors2web.zummix_app.Activities.ProblemSKU_Activity;

import android.app.ProgressDialog;
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
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Admin_Tools_Adapters.ProblemAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemResponse;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemSKUs;
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

public class ProblemSKU extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar mtoolbar ;

    @BindView(R.id.recycle_view)
    RecyclerView mrecycleview ;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout ;

    APIInterface apiInterface;

    ProblemAdapter cadapter;

    List<ProblemSKUs> PList =new ArrayList<>();



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        ButterKnife.bind(this);


        //for toolbarsetup with back arrow
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String group_type = preferences.getString("group_type", "");
        final String l_id = preferences.getString("l_id", "");



        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cadapter = new ProblemAdapter(ProblemSKU.this,PList);

        apiInterface = APIClient.getClient().create(APIInterface.class);


        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager(mlayoutManager);

        mrecycleview.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        mrecycleview.setItemAnimator(new DefaultItemAnimator());

        mrecycleview.setAdapter(cadapter);

        loadAdapter( email, password);


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                loadAdapter( email, password);
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

        super.onBackPressed();

//        moveTaskToBack(true);
    }

    private void loadAdapter( String email, String password) {
        
    final ProgressDialog progressDialog = new ProgressDialog(ProblemSKU.this, R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<ProblemResponse>call = apiInterface.getProblemSKU(email, password);

        call.enqueue(new Callback<ProblemResponse>() {
        @Override
        public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

            if (response.isSuccessful()) {
                ProblemResponse resp1 = response.body();
                Log.d("msg",resp1.getMessage().toString());

                List<ProblemSKUs> order = resp1.getmProblemSKUs();

                Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                if (order != null) {

                    for (int i = 0; i < order.size(); i++) {

                        ProblemSKUs dis = new ProblemSKUs();

                        String mCustomer = order.get(i).getCompanyName();
                        String mpname = order.get(i).getItemName();
                        String mpsku = order.get(i).getItemSku();
                        String moquant = order.get(i).getOrderQty();
                        String mtorder = order.get(i).getTotalOrder();
                        String mweight = order.get(i).getWeight();
                        String mprice = order.get(i).getPrice();
                        String mUOM = order.get(i).getUom();
                        String mCountry = order.get(i).getCountry();
                        Long mcid = order.get(i).getCustomerId();
                        Long mid = order.get(i).getId();



                        dis.setCompanyName(mCustomer);
                        dis.setItemName(mpname);
                        dis.setItemSku(mpsku);
                        dis.setOrderQty(moquant);
                        dis.setTotalOrder(mtorder);
                        dis.setWeight(mweight);
                        dis.setPrice(mprice);
                        dis.setUom(mUOM);
                        dis.setCountry(mCountry);
                        dis.setCustomerId(mcid);
                        dis.setId(mid);

                        PList.add(dis); // must be the object of empty list initiated
                    }
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    cadapter.updateAnswers(PList);//adapter's content is updated and update function is called;
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
                Toast.makeText(ProblemSKU.this, "Operation Failed", Toast.LENGTH_SHORT).show();

            }
        }

        @Override
        public void onFailure(Call<ProblemResponse> call, Throwable t) {
            call.cancel();
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            Log.e("response-failure", t.toString());
            Toast.makeText(ProblemSKU.this, "Network Error", Toast.LENGTH_SHORT).show();
        }
    });
}
}

