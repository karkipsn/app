package com.example.colors2web.zummix_app.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Fragments.Frag_InActive;
import com.example.colors2web.zummix_app.POJO.InactiveCustomers.StoreInactiveItem;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
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

public class InActiveActivity extends AppCompatActivity {
    private static final String TAG_FRAGMENT = "SEARCH_FRAGMENT";
    private static final String TAG_FRAGMENT2 = "INACTIVE_FRAGMENT";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @BindView(R.id.inactive_spinner)
//    AutoCompleteTextView mspinner;

    @BindView(R.id.real_spinner)
    Spinner spinner1;

    @BindView(R.id.btn_submit_inactive)
    Button mbutton;

    APIInterface apiInterface;

    Long cus_id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inactive);
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        spinner(email, password);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InActiveActivity.super.onBackPressed();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
            }
        });

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("cus_id", String.valueOf(cus_id));
                Frag_InActive inActive = new Frag_InActive();
                inActive.setArguments(bundle);
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

                getSupportFragmentManager().beginTransaction().
                        add(R.id.frame_inactive, inActive).
                        commit();
            }
        });

    }


    private void spinner(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(InActiveActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<OrdGrpByCus> call = apiInterface.getOrdersbyGroup(email, password);
        call.enqueue(new Callback<OrdGrpByCus>() {
            @Override
            public void onResponse(Call<OrdGrpByCus> call, Response<OrdGrpByCus> response) {

                if (response.isSuccessful()) {
                    OrdGrpByCus resp1 = response.body();

                    List<Order> order = resp1.getOrders();
                    Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (order != null) {


                        ArrayList<SpinnerPojo> countryList = new ArrayList<>();

                        for (int i = 0; i < order.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = order.get(i).getCustomerName();
                            Long cus_id = order.get(i).getCustomerId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            countryList.add(order1);
//                            spin.add(name);
//                            spin_value.add(cus_id);
                        }
                        Log.d("spinner_list", countryList.toString());


////                        try autocomplete
//                        ArrayAdapter<SpinnerPojo> adapter1 = new ArrayAdapter<SpinnerPojo>(InActiveActivity.this,
//                                android.R.layout.simple_list_item_single_choice, countryList);
//                         mspinner.setAdapter(adapter1);

                        ArrayAdapter<SpinnerPojo> adapter2 = new ArrayAdapter<SpinnerPojo>(InActiveActivity.this,
                                android.R.layout.simple_spinner_item, countryList);

                        adapter2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                        spinner1.setAdapter(adapter2);

                        spinner1.setPrompt("Select Customers");
                        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                                cus_id = sp.getCus_id();

                                Toast.makeText(getApplicationContext(), "Cus ID: " + sp.getCus_id() + ",  " +
                                        " Name : " + sp.getName(), Toast.LENGTH_SHORT).show();
                                Log.d("cus_id", String.valueOf(cus_id));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(0);
                                cus_id = sp.getCus_id();
                            }
                        });


//                        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//                        Display display = wm.getDefaultDisplay();
//                        int width = display.getWidth();
//                        double width1 = width * 0.8;
//                        int fin = (int) width1;
//
//
//                        mspinner.setDropDownWidth(fin);
//                        mspinner.setCursorVisible(false);
//                        mspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                mspinner.showDropDown();
////                                selection = (String) parent.getItemAtPosition(position);
//
//                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
//                                cus_id = sp.getCus_id();
//
//
//
//                                Toast.makeText(getApplicationContext(), "Cus ID: " + sp.getCus_id() + ",  " +
//                                        " Name : " + sp.getName(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        mspinner.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                mspinner.showDropDown();
//                            }
//                        });

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getApplicationContext(), d, Toast.LENGTH_SHORT).show();
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

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(InActiveActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrdGrpByCus> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(InActiveActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
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
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        super.onBackPressed();
    }
}

