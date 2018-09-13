package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments.Frag_Shipping_method;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments.Frag_customer;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments.frag_shipstation_credential;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments.frag_shopify_credential;
import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.POJO.customers.Customers;
import com.example.colors2web.zummix_app.POJO.customers.ShipStationCredential;
import com.example.colors2web.zummix_app.POJO.customers.ShippingMethods;
import com.example.colors2web.zummix_app.POJO.customers.ShopifyCredential;
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

public class ByCustomerId extends AppCompatActivity {
    APIInterface apiInterface;

    @BindView(R.id.cus_cus_id)
    TextView cus_id;

    @BindView(R.id.toolbar)
    Toolbar ctoolbar;

    @BindView(R.id.tabs_customer)
    TabLayout tabLayout;

    @BindView(R.id.viewpager_customer)
    ViewPager viewPager;
    String cid1;
    String sc_id,sc_email,sc_vendor ,sc_cus_id,sc_c_at,sid,ssid,scid ,sca,sidn;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_customerid);
        ButterKnife.bind(this);


        //for toolbarsetup with back arrow
        setSupportActionBar(ctoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ctoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ByCustomerId.super.onBackPressed();
                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
            }
        });


        //  tablayout with pageviewer set up
        tabLayout.setupWithViewPager(viewPager);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        Intent intent = getIntent();
        if (intent != null) {
            cid1 = intent.getExtras().getString("cid");

            final ProgressDialog progressDialog = new ProgressDialog(ByCustomerId.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<CustomerResponse> call = apiInterface.getCustomer(email, password, cid1);
            call.enqueue(new Callback<CustomerResponse>() {

                @Override
                public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {

                    if (response.isSuccessful()) {
                        CustomerResponse resp1 = response.body();

                        Customers customers = resp1.getM1Customer();
                        if (customers != null) {

                            String cidx = String.valueOf(customers.getId());
                            String cname = customers.getCompanyName();
                            String cemail = customers.getCompanyEmail();
                            String cadd = customers.getCompanyAddress1() + "|" + customers.getCompanyCity() + "|"
                                    + customers.getCompanyState() + "|" + customers.getCompanyZip();
                            String coname = customers.getContactFname() + customers.getContactMname() + customers.getContactLname();
                            String coadd = customers.getContactEmail();
                            cus_id.setText(cidx);

                            ShipStationCredential sp = resp1.getmShipStationCredential();

                            if(sp!=null){

                             sidn = sp.getCompanyIdentifier();
                            Log.d("sidn", sidn);
                             sid = String.valueOf(sp.getId());
                             ssid = sp.getStoreId();
                             scid = String.valueOf(sp.getCustomerId());
                             sca = sp.getCreatedAt();}

                            ShopifyCredential sc = resp1.getmShopifyCredential();

                             if(sc!=null){
                             sc_id = String.valueOf(sc.getId());
                             sc_email = sc.getEmail();
                             sc_vendor = sc.getVendor();
                             sc_cus_id = String.valueOf(sc.getCustomerId());
                             sc_c_at = sc.getCreatedAt();}

//                        Shipping_method_credential
                            ArrayList<ShippingMethods> ShipList = new ArrayList<>();
                            List<ShippingMethods> ship = resp1.getmShippingMethods();

                            for (int i = 0; i < ship.size(); i++) {

                                ShippingMethods methods = new ShippingMethods();

                                String id = String.valueOf(ship.get(i).getId());
                                String cus_id = String.valueOf(ship.get(i).getCustomerId());
                                String ship_ac_type = ship.get(i).getShippingAccountType();
                                String ship_cmp_type = ship.get(i).getShippingCompanyType();
                                String acc_no = ship.get(i).getAccountNumber();

                                methods.setId(Long.valueOf(id));
                                methods.setCustomerId(Long.valueOf(cus_id));
                                methods.setShippingAccountType(ship_ac_type);
                                methods.setShippingCompanyType(ship_cmp_type);
                                methods.setAccountNumber(acc_no);
                                ShipList.add(methods);
                            }
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            //pageviewer set up
                            setupViewPager(viewPager, cidx, cname, cemail, cadd, coname, coadd,
                                    sidn, sid, ssid, scid, sca, sc_id, sc_email, sc_vendor, sc_cus_id, sc_c_at, ShipList);

                        } else {
                            Toast.makeText(getApplicationContext(), "Customer not found", Toast.LENGTH_LONG).show();
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
                        Toast.makeText(ByCustomerId.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onFailure(Call<CustomerResponse> call, Throwable t) {
                    call.cancel();

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Log.e("response-failure", t.toString());
                    Toast.makeText(ByCustomerId.this, "Network Error", Toast.LENGTH_SHORT).show();

                }
            });
        }

    }

    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";


    @Override
    public void finish() {
        super.finish();
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
        overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
        super.onBackPressed();
    }


    private void setupViewPager(ViewPager viewPager, String cidx, String cname, String cemail, String cadd, String coname, String coadd,
                                String sidn, String sid, String ssid, String scid, String sca, String sc_id, String sc_email, String sc_vendor
            , String sc_cus_id, String sc_c_at,
                                ArrayList<ShippingMethods> shipList) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Frag_customer one = new Frag_customer();
        Bundle bundle2 = new Bundle();
        bundle2.putString("cid1", cidx);
//        customers_data
        bundle2.putString("cname", cname);
        bundle2.putString("cemail", cemail);
        bundle2.putString("cadd", cadd);
        bundle2.putString("coname", coname);
        bundle2.putString("coadd", coadd);
        one.setArguments(bundle2);
        adapter.addFrag(one, "Customer");

//        adapter.addFrag(new Frag_Shipping_method(), "Shipping Method");
        Frag_Shipping_method bfarg = new Frag_Shipping_method();
        Bundle b = new Bundle();
        b.putString("cidb", cidx);
        b.putParcelableArrayList("cus_ship_list", shipList);
        bfarg.setArguments(b);
        adapter.addFrag(bfarg, "Shipping Method");

        frag_shopify_credential three = new frag_shopify_credential();
        Bundle bundle3 = new Bundle();

        bundle3.putString("sc_id", sc_id);
        bundle3.putString("sc_c_at", sc_c_at);
        bundle3.putString("sc_email", sc_email);
        bundle3.putString("sc_vendor", sc_vendor);
        bundle3.putString("sc_cus_id", sc_cus_id);

        three.setArguments(bundle3);
        adapter.addFrag(three, "Shopify Credential");


        frag_shipstation_credential two = new frag_shipstation_credential();
        Bundle bundle = new Bundle();
        bundle.putString("cid1", cidx);
//     shipstation data
        bundle.putString("sidn", sidn);
        bundle.putString("sid", sid);
        bundle.putString("ssid", ssid);
        bundle.putString("scid", scid);
        bundle.putString("sca", sca);
        Log.d("cidx", cidx);
        two.setArguments(bundle);
        adapter.addFrag(two, "ShipStation Credential");

        viewPager.setAdapter(adapter);
    }


}
