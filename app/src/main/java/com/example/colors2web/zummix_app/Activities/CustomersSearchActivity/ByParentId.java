package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.GroupCusDetails;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.CusGroupDetails.CustomerGroup;
import com.example.colors2web.zummix_app.POJO.CusGroupDetails.Order;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.POJO.customers.Customers;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ByParentId extends AppCompatActivity {
    Toolbar toolbar;
    APIInterface apiInterface;
    RecyclerView mrecycleView;
    TextView parentId;
    ParentAdapter_customer padapter;
    List<Customers> CList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customer_by_parent_id);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        parentId = findViewById(R.id.cus_parent_Id);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mrecycleView = findViewById(R.id.recycler_view_customer);
        padapter = new ParentAdapter_customer(CList);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(this);
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new SimpleItemDecoration(this));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        String p_id = getIntent().getStringExtra("pid");
        Log.d("p_id", p_id);

        loadAdapter(email, password, p_id);

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
//        if (getFragmentManager().getBackStackEntryCount() == 0) {
//            this.finish();
//        } else {
//            getFragmentManager().popBackStack();
//        }
        moveTaskToBack(true);
    }


    private void loadAdapter(String email, String password, String p_id) {

        String Path = p_id;
        Log.d("Path", Path);
        Log.d("Path", email);
        Log.d("Path", p_id);

        final ProgressDialog progressDialog = new ProgressDialog(ByParentId.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<CustomerResponse> call = apiInterface.getParentCustomer(email, password, Path);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {

                if (response.isSuccessful()) {
                    CustomerResponse resp1 = response.body();

                    List<Customers> cus = resp1.getmCustomer();

                    Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {

                            Customers cus1 = new Customers();

                            String p_id = String.valueOf(cus.get(i).getParentId());


                            String cname = cus.get(i).getCompanyName();
                            String cemail = cus.get(i).getCompanyEmail();
                            String add1 = cus.get(i).getCompanyAddress1();
                            String city = cus.get(i).getCompanyCity();
                            String state = cus.get(i).getCompanyState();
                            String country = cus.get(i).getCompanyCountry();
                            String zip = cus.get(i).getCompanyZip();
                            String fname = cus.get(i).getContactFname();
//                            String mname = cus.get(i).getContactMname();
                            String lname = cus.get(i).getContactLname();
                            String coemail = cus.get(i).getContactEmail();

                            parentId.setText("Parent Id:" + p_id);

                            cus1.setCompanyName(cname);
                            cus1.setCompanyEmail(cemail);
                            cus1.setCompanyAddress1(add1);
                            cus1.setCompanyCity(city);
                            cus1.setCompanyState(state);
                            cus1.setCompanyCountry(country);
                            cus1.setCompanyZip(zip);
                            cus1.setContactFname(fname);
                            cus1.setContactMname("");
                            cus1.setContactLname(lname);
                            cus1.setContactEmail(coemail);

                            CList.add(cus1); // must be the object of empty list initiated
                        }
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        padapter.updateAnswers(CList);//adapter's content is updated and update function is called;

                        //send parameters according to urs adapter view setup.);

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getApplicationContext(),d,Toast.LENGTH_LONG).show();
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
                }
                else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(ByParentId.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(ByParentId.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
