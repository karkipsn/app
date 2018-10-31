package com.example.colors2web.zummix_app.Activities.MasterBoxSearch;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.TicketActivity.TicketNavigActivity;
import com.example.colors2web.zummix_app.Adapter.BoxAdapters.MBoxAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.CompanyDetails;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.FieldOffice;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBox;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.OrderShippingAddress;
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

public class MasterBoxSearch2Activity extends AppCompatActivity {

    APIInterface apiInterface;
    private String Path;


    @BindView(R.id.toolbar)
    Toolbar ctoolbar;

    @BindView(R.id.m_storename)
    TextView store;

    @BindView(R.id.m_masterbox)
    TextView master_box;

    @BindView(R.id.m_fieldOffice)
    TextView mfield_office;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    MBoxAdapter mBoxAdapter;
    RecyclerView mrecycleview;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    ArrayList<MasterBox> MasterBoxList = new ArrayList<>();
    String cid1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masterbox_final);
        ButterKnife.bind(this);

        //for toolbarsetup with back arrow
        setSupportActionBar(ctoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ctoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//                super.onBackPressed();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                startActivity(new Intent(MasterBoxSearch2Activity.this, TicketNavigActivity.class));
                isDestroyed();

            }
        });


        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        mBoxAdapter = new MBoxAdapter(MasterBoxSearch2Activity.this,MasterBoxList);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        mrecycleview = findViewById(R.id.recycle_view_mbox);
        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager(mlayoutManager);

        mrecycleview.addItemDecoration(new MyDividerItemDecoration(this, LinearLayoutManager.HORIZONTAL, 16));
        mrecycleview.setItemAnimator(new DefaultItemAnimator());

        mrecycleview.setAdapter(mBoxAdapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        Log.d("PathMail", email);

        final Intent i = getIntent();

        if (i != null) {

            Path = i.getExtras().getString("OPath");
            call(email, password, Path);

        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                MasterBoxList.clear();
                call(email, password, Path);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void call(String email, String password, String path) {

        final ProgressDialog progressDialog = new ProgressDialog(MasterBoxSearch2Activity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<MasterBoxResponse> call = apiInterface.getMasterBoxes(email, password, path);
        call.enqueue(new Callback<MasterBoxResponse>() {
            @Override
            public void onResponse(Call<MasterBoxResponse> call, Response<MasterBoxResponse> response) {

                if (response.isSuccessful()) {
                    MasterBoxResponse resp = response.body();

                    OrderShippingAddress add = resp.getOrderShippingAddress();

                    OrderShippingAddress shipping_address = new OrderShippingAddress();

                    if (add != null) {

                        String id = String.valueOf(add.getId());
                        String ord_id = String.valueOf(add.getOrderId());
                        String fname = add.getCustomerFname();
                        String mname = String.valueOf(add.getCustomerMname());
                        String lname = add.getCustomerLname();
                        String email = add.getCustomerEmail();
                        String phone = add.getCustomerPhone1();
                        String add1 = add.getCustomerAddress1();
                        String city = add.getCustomerCity();
                        String state = add.getCustomerState();
                        String zip = add.getCustomerZip();
                        String country = add.getCustomerCountry();

                        shipping_address.setId(Long.valueOf(id));
                        shipping_address.setOrderId(Long.valueOf(ord_id));
                        shipping_address.setCustomerFname(fname);
                        shipping_address.setCustomerMname(mname);
                        shipping_address.setCustomerLname(lname);
                        shipping_address.setCustomerEmail(email);
                        shipping_address.setCustomerPhone1(phone);
                        shipping_address.setCustomerAddress1(add1);
                        shipping_address.setCustomerCity(city);
                        shipping_address.setCustomerState(state);
                        shipping_address.setCustomerZip(zip);
                        shipping_address.setCustomerCountry(country);

                    }

                    FieldOffice field = resp.getFieldOffice();

                    FieldOffice fieldOffice = new FieldOffice();
                    if (field != null) {

                        String id = String.valueOf(field.getId());
                        String bin = field.getBin();
                        String mname = field.getManager1Name();
                        String email = field.getManager1Email();
                        String phone = field.getManager1Phone();
                        String add1 = field.getAddress1();
                        String city = field.getCity();
                        String state = field.getState();
                        String zip = field.getZip();
                        String country = field.getCountry();

                        fieldOffice.setId(Long.valueOf(id));
                        fieldOffice.setBin(bin);
                        fieldOffice.setManager1Name(mname);
                        fieldOffice.setManager1Email(email);
                        fieldOffice.setManager1Phone(phone);
                        fieldOffice.setAddress1(add1);
                        fieldOffice.setCity(city);
                        fieldOffice.setState(state);
                        fieldOffice.setZip(zip);
                        fieldOffice.setCountry(country);

                        String address = add1 +" "+ city +" "+
                                state +" "+ zip+" "+country;

                        mfield_office.setText(address);

                    }else{
                        mfield_office.setText(null);
                    }

                    CompanyDetails comp = resp.getCompanyDetails();
                    CompanyDetails companyDetails = new CompanyDetails();
                    if (comp != null) {


                        String id = String.valueOf(comp.getId());
                        String parent_id = String.valueOf(comp.getParentId());
                        String alais = comp.getCompanyAlias();
                        String fname = comp.getContactFname();
                        String mname = String.valueOf(comp.getContactMname());
                        String lname = comp.getContactLname();
                        String email = comp.getCompanyEmail();
                        String phone = comp.getContactPhonePrimary();
                        String add1 = comp.getCompanyAddress1();
                        String city = comp.getCompanyCity();
                        String state = comp.getCompanyState();
                        String zip = comp.getCompanyZip();
                        String country = comp.getCompanyCountry();
                        String compNy_name = comp.getCompanyName();

                        store.setText(compNy_name);


                        companyDetails.setId(Long.valueOf(id));
                        companyDetails.setParentId(Long.valueOf(parent_id));
                        companyDetails.setCompanyAlias(alais);
                        companyDetails.setContactFname(fname);
                        companyDetails.setContactMname(mname);
                        companyDetails.setContactLname(lname);
                        companyDetails.setCompanyEmail(email);
                        companyDetails.setContactPhonePrimary(phone);
                        companyDetails.setCompanyAddress1(add1);
                        companyDetails.setCompanyCountry(city);
                        companyDetails.setCompanyState(state);
                        companyDetails.setCompanyZip(zip);
                        companyDetails.setCompanyCountry(country);
                    }


                    List<MasterBox> box = resp.getMasterBoxes();

                    if (box != null) {

                        for (int i = 0; i < box.size(); i++) {

                            MasterBox methods = new MasterBox();

                            String ord_id = String.valueOf(box.get(i).getOrderId());
                            String bar_no = String.valueOf(box.get(i).getBarcodeNumber());
                            String box_no = String.valueOf(box.get(i).getBoxNumber());
                            String mbox_no = box.get(i).getMasterBoxCode();
                            String tracking_code = box.get(i).getMasterBoxTrackingCode();
                            String cname = box.get(i).getCompanyName();
                            String email = box.get(i).getCompanyEmail();

                            methods.setOrderId(ord_id);
                            methods.setBarcodeNumber(bar_no);
                            methods.setBoxNumber(box_no);
                            methods.setMasterBoxCode(mbox_no);
                            methods.setMasterBoxTrackingCode(tracking_code);
                            methods.setCompanyName(cname);
                            methods.setCompanyEmail(email);

                            MasterBoxList.add(methods);

                            master_box.setText(box.get(0).getMasterBoxCode());
                        }
                        mBoxAdapter.updateAnswers(MasterBoxList);
                    }

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
                    Toast.makeText(MasterBoxSearch2Activity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<MasterBoxResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(MasterBoxSearch2Activity.this, "Network Error", Toast.LENGTH_SHORT).show();

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
                        replace(R.id.main_toolbar, new SearchFragment()).commit();
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

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_toolbar, new SearchFragment()).addToBackStack(null).commit();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
//       moveTaskToBack(true);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        super.onBackPressed();
    }
}