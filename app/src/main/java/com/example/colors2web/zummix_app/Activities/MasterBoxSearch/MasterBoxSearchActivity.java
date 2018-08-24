package com.example.colors2web.zummix_app.Activities.MasterBoxSearch;

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

import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.ViewPagerAdapter;
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments.CompanyDetails_frag;
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments.FieldOffice_frag;
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments.MasterBoxes_frag;
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments.ShippingAddress_frag;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.CompanyDetails;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.FieldOffice;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.OrderShippingAddress;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBox;

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


public class MasterBoxSearchActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_customerid);

        ButterKnife.bind(this);


        //for toolbarsetup with back arrow
        setSupportActionBar(ctoolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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
        }
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

    private void call(String email, String password, String path) {

        final ProgressDialog progressDialog = new ProgressDialog(MasterBoxSearchActivity.this,
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

                        cus_id.setText(id);
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


                    ArrayList<MasterBox> MasterBoxList = new ArrayList<>();
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
                        }
                    }

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    setup_viewpager(shipping_address, fieldOffice, companyDetails, MasterBoxList);



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
                    Toast.makeText(MasterBoxSearchActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }


            @Override
            public void onFailure(Call<MasterBoxResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(MasterBoxSearchActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void setup_viewpager(OrderShippingAddress shipping_address, FieldOffice fieldOffice,
                                 CompanyDetails companyDetails, ArrayList<MasterBox> masterBoxList) {



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        ShippingAddress_frag address = new ShippingAddress_frag();
        Bundle b = new Bundle();
        b.putSerializable("shipping_address", shipping_address);
        address.setArguments(b);
        adapter.addFrag(address, "Shipping Address");


        FieldOffice_frag f_frag = new FieldOffice_frag();
        Bundle c = new Bundle();
        c.putSerializable("fieldOffice", fieldOffice);
        f_frag.setArguments(c);
        adapter.addFrag(f_frag, "Field Office");

        CompanyDetails_frag frag = new CompanyDetails_frag();
        Bundle d = new Bundle();
        d.putSerializable("companyDetails", companyDetails);
        frag.setArguments(d);
        adapter.addFrag(frag, "Company Details");

        MasterBoxes_frag mfrag = new MasterBoxes_frag();
        Bundle f = new Bundle();
        f.putSerializable("masterBoxList", masterBoxList);
        mfrag.setArguments(f);
        adapter.addFrag(mfrag, "Master Boxes");

        viewPager.setAdapter(adapter);
    }
}
