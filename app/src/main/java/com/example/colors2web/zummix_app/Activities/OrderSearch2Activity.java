package com.example.colors2web.zummix_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Fragments.FiveFragment;
import com.example.colors2web.zummix_app.Fragments.FourFragment;
import com.example.colors2web.zummix_app.Fragments.OneFragment;
import com.example.colors2web.zummix_app.Fragments.SixFragment;
import com.example.colors2web.zummix_app.Fragments.ThreeFragment;
import com.example.colors2web.zummix_app.Fragments.TwoFragment;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response;
import com.example.colors2web.zummix_app.POJO.OrderDetails;
import com.example.colors2web.zummix_app.POJO.OrderSearchResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSearch2Activity extends AppCompatActivity {

    APIInterface apiInterface;

    @BindView(R.id.dis_head)
    TextView head;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.dis_batch)
    TextView batch;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    Long id;
    Bundle bundle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersearch2);
        ButterKnife.bind(this);

        //for toolbarsetup with back arrow
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //pageviewer set up
        setupViewPager(viewPager);

        //  tablayout with pageviewer set up
        tabLayout.setupWithViewPager(viewPager);

        apiInterface = APIClient.getClient().create(APIInterface.class);

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


            final String Path2 = i.getExtras().getString("O_no_by_group");
            if (Path2 != null) {
                call_next(email, password, Long.valueOf(Path2));
            }

        }

    }

    private void call(final String email, final String password, String Path) {

        Call<OrderSearchResponse> call = apiInterface.getOrder1(email, password, Path);
        call.enqueue(new Callback<OrderSearchResponse>() {
            @Override
            public void onResponse(Call<OrderSearchResponse> call, Response<OrderSearchResponse> response) {

                if (response.isSuccessful()) {
                    OrderSearchResponse resp1 = response.body();

                    OrderDetails order = resp1.getOrderDetails();

                    if (order != null) {

//                        head.setText("Order Details Of " + order.getOrderNumber());
//                        batch.setText("Batch Number : " + order.getOrderNumber());
                        id = order.getId();
                        call_next(email, password, id);

                        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(OrderSearch2Activity.this);
                        SharedPreferences.Editor editor = preferences1.edit();
                        editor.putLong("id", id);
                        editor.apply();


                    } else {
                        String d = response.body().getMessage();
                        head.setText(d);
                    }

                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(OrderSearch2Activity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrderSearchResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(OrderSearch2Activity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void call_next(String email, String password, Long id) {

        Call<Order2Response> call = apiInterface.getsecsearch(email, password, id);

        call.enqueue(new Callback<Order2Response>() {
            @Override
            public void onResponse(Call<Order2Response> call, Response<Order2Response> response) {

                if (response.isSuccessful()) {
                    Order2Response resp1 = response.body();

                    com.example.colors2web.zummix_app.POJO.Order2POJO.OrderDetails order = resp1.getOrderDetails();

                    if (order != null) {

                        head.setText(String.valueOf(order.getOrder().getId()));
                        batch.setText("Batch Number : " + order.getOrder().getBatchNumber());

                        //bundle
                        bundle = new Bundle();
                        bundle.putString("store_name", order.getOrder().getCustomerName());
                        bundle.putString("store_email", order.getOrder().getCustomerEmail());
                        bundle.putString("order_no", order.getOrder().getOrderNumber());
                        bundle.putString("special_inst", order.getOrder().getOrderSpecialInstruction());
                        bundle.putString("order_date", order.getOrder().getOrderDate());
                        bundle.putString("ship_method", order.getOrder().getShipMethod());
                        bundle.putString("order_type", order.getOrder().getOrderType());
                        bundle.putString("order_status", order.getOrder().getOrderStatus());
                        bundle.putString("emp_id", order.getOrder().getEmployeeId());


                    } else {
                        String d = response.body().getMessage();
                        head.setText(d);
                    }

                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(OrderSearch2Activity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Order2Response> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(OrderSearch2Activity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new OneFragment(), "Order Details");
        adapter.addFrag(new TwoFragment(), "Shipping Address");
        adapter.addFrag(new ThreeFragment(), "Order Logs");
        adapter.addFrag(new FourFragment(), "Line Items");
        adapter.addFrag(new FiveFragment(), "Boxes");
        adapter.addFrag(new SixFragment(), "Master Box");
        viewPager.setAdapter(adapter);

        ////        Bundle bundle =new Bundle();
////        bundle.putLong("id",id);
//
//        OneFragment frag_one = new OneFragment();
//        frag_one.setArguments(bundle);
////        Log.d("Bundle",bundle.getString("store_name"));
//        adapter.addFrag(frag_one, "Order Details");
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {

            return mFragmentList.get(position);
            //ysma switch optiion rakhera garda ni hunxa

        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
