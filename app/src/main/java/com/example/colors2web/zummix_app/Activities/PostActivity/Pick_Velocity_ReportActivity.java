package com.example.colors2web.zummix_app.Activities.PostActivity;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.POJO.PostSearch.Boxes;
import com.example.colors2web.zummix_app.POJO.PostSearch.CustomerItems;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostResponse;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostServer;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pick_Velocity_ReportActivity extends AppCompatActivity {

    APIInterface apiInterface;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.item_dr_spinner)
    AutoCompleteTextView mspinner;

    @BindView(R.id.pick_from)
    TextView mform;

    @BindView(R.id.pick_to)
    TextView mto;

    @BindView(R.id.btn_pick_submit)
    Button btn_submit;

    Integer mYear, mMonth, mDay;

    ImageView img;
    Long cus_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_pick_velocity);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        spinner(email, password);

        mform.setInputType(InputType.TYPE_NULL);

        mform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);


                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog datePickerDialog = new DatePickerDialog(Pick_Velocity_ReportActivity.this,
                        R.style.AppTheme_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        int mmonth = month + 1;
                        String formattedMonth = "" + mmonth;
                        String formattedDayOfMonth = "" + dayOfMonth;

                        if (mmonth < 10) {

                            formattedMonth = "0" + mmonth;
                        }
                        if (dayOfMonth < 10) {

                            formattedDayOfMonth = "0" + dayOfMonth;
                        }

                        mform.setText(year + "-" + formattedMonth + "-" + formattedDayOfMonth);
                    }

                }, mYear, mMonth, mDay);

                mform.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            datePickerDialog.show();
                        } else {
                            datePickerDialog.hide();
                        }
                    }
                });

            }
        });


        mto.setInputType(InputType.TYPE_NULL);
        mto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mto.requestFocus();
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                final DatePickerDialog datePickerDialog = new DatePickerDialog(Pick_Velocity_ReportActivity.this,
                        R.style.AppTheme_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        int mmonth = month + 1;
                        String formattedMonth = "" + mmonth;
                        String formattedDayOfMonth = "" + dayOfMonth;

                        if (mmonth < 10) {

                            formattedMonth = "0" + mmonth;
                        }
                        if (dayOfMonth < 10) {

                            formattedDayOfMonth = "0" + dayOfMonth;
                        }

                        mto.setText(year + "-" + formattedMonth + "-" + formattedDayOfMonth);
                    }

                }, mYear, mMonth, mDay);

                mto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                    @Override
                    public void onFocusChange(View v, boolean hasFocus) {
                        if (hasFocus) {
                            datePickerDialog.show();
                        } else {
                            datePickerDialog.show();
                        }
                    }
                });


            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String from = mform.getText().toString();
                String to = mto.getText().toString();
                Long customer = cus_id;
                call_pick_velocity(email, password, customer, from, to);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu2, menu);

        img = (ImageView) menu.findItem(R.id.image).getActionView();
        img.setImageResource(android.R.drawable.ic_menu_search);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_toolbar, new SearchFragment()).commit();
            }
        });


//                searchView.onActionViewExpanded();
//                searchView.setQueryHint("Search query");
////                searchView.setSearchableInfo(searchManager
////                        .getSearchableInfo(getComponentName()));
//            }

//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//
//                String OPath = String.valueOf(searchView.getQuery());
//                Log.d("OPath",OPath);
//                if (spine != null) {
//                    Log.d("spinnerkovalue2", spine);
//
//                    switch (spine) {
//
//                        case "Order":
//                            Intent intent = new Intent(getApplicationContext(), OrderSearch2Activity.class);
//                            intent.putExtra("OPath", OPath);
//                            startActivity(intent);
//                            break;
//
//                        case "Item":
//                            Intent intent1 = new Intent(getApplicationContext(), ItemSearchActivity.class);
//                            intent1.putExtra("OPath", OPath);
//                            startActivity(intent1);
//                            break;
//
//                        case "Tracking Number":
//                            Intent intent2 = new Intent(getApplicationContext(), TrackOrderSearchActivity.class);
//                            intent2.putExtra("OPath", OPath);
//                            startActivity(intent2);
//                            break;
//
//                        case "Boxes":
//                            Intent intent3 = new Intent(getApplicationContext(), OrderSearch2Activity.class);
//                            intent3.putExtra("OPath", OPath);
//                            startActivity(intent3);
//                            break;
//
//                        case "Parent_Id":
//                            Intent intent5 = new Intent(getApplicationContext(), ByParentId.class);
//                            intent5.putExtra("pid", OPath);
//                            startActivity(intent5);
//                            break;
//
//                        case "Cus_Id":
//                            Intent intetn6 = new Intent(getApplicationContext(), ByCustomerId.class);
//                            intetn6.putExtra("cid", OPath);
//                            startActivity(intetn6);
//                            break;
//
//                        case "Cus_Items":
//                            Intent intent7 = new Intent(getApplicationContext(), OrderSearch2Activity.class);
//                            intent7.putExtra("ciid", OPath);
//                            startActivity(intent7);
//                            break;
//                    }
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//
//        });

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
        moveTaskToBack(true);
    }

    private void call_pick_velocity(String email, String password, Long customer, String from, String to) {
        if (customer != null) {

            String cus_id = String.valueOf(customer);
            PostServer post = new PostServer(cus_id, from, to);

            final ProgressDialog progressDialog = new ProgressDialog(Pick_Velocity_ReportActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            Call<PostResponse> call = apiInterface.postPickVelocityReport(email, password, post);
            call.enqueue(new Callback<PostResponse>() {
                             @Override
                             public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {

                                 if (response.isSuccessful()) {
                                     PostResponse res = response.body();
                                     List<CustomerItems> shipments = res.getmCustomerItems();

                                     List<Boxes> BList = new ArrayList<>();
                                     List<CustomerItems> CItems = new ArrayList<>();

                                     if (shipments != null) {

                                         for (int i = 0; i < shipments.size(); i++) {

                                             CustomerItems items = new CustomerItems();

                                             String sku = shipments.get(i).getItemSkuNumber();
                                             String name = shipments.get(i).getItemName();
                                             String replenish = shipments.get(i).getReplenish();
                                             Long pick = shipments.get(i).getPick();
                                             String location1 = shipments.get(i).getWarehouseName();
                                             String location2 = shipments.get(i).getPickAisleName();
                                             String location3 = shipments.get(i).getSectionNumber();
                                             String location4 = shipments.get(i).getSectionLevel();
                                             String location5 = shipments.get(i).getPickBin();

                                             items.setItemSkuNumber(sku);
                                             items.setItemName(name);
                                             items.setReplenish(replenish);
                                             items.setPick(pick);
                                             items.setWarehouseName(location1);
                                             items.setPickAisleName(location2);
                                             items.setSectionNumber(location3);
                                             items.setSectionLevel(location4);
                                             items.setPickBin(location5);

                                             CItems.add(items);
                                         }
                                         List<Boxes> box = res.getmBoxes();
                                         if (box != null) {

                                             for (int i = 0; i < box.size(); i++) {

                                                 Boxes boxes = new Boxes();
                                                 String date = box.get(i).getCreatedAt();
                                                 String sku = box.get(i).getItemSku();
                                                 String total = box.get(i).getTotalPickedQty();
                                                 Long unique = box.get(i).getUniqueOrders();

                                                 boxes.setCreatedAt(date);
                                                 boxes.setItemSku(sku);
                                                 boxes.setTotalPickedQty(total);
                                                 boxes.setUniqueOrders(unique);

                                                 BList.add(boxes);
                                             }
                                         } else {

                                             if (progressDialog.isShowing()) {
                                                 progressDialog.dismiss();
                                                 Toast.makeText(getApplicationContext(), "NO boxes found", Toast.LENGTH_LONG).show();
                                             }
                                         }
                                         Intent intent = new Intent(Pick_Velocity_ReportActivity.this, View_Pick_VelocityActivity.class);
                                         intent.putExtra("CList", (Serializable) CItems);
                                         intent.putExtra("BList", (Serializable) BList);
                                         if (progressDialog.isShowing()) {
                                             progressDialog.dismiss();
                                         }
                                         startActivity(intent);

                                     } else {
                                         Toast.makeText(Pick_Velocity_ReportActivity.this, response.body().getmMessage(), Toast.LENGTH_LONG).show();
                                         if (progressDialog.isShowing()) {
                                             progressDialog.dismiss();
                                         }

                                     }
                                 } else if (response.code() == 401) {
                                     if (progressDialog.isShowing()) {
                                         progressDialog.dismiss();
                                     }
                                     Toast.makeText(Pick_Velocity_ReportActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                                     Log.d("Error", response.errorBody().toString());

                                 } else if (response.code() == 404) {

                                     if (progressDialog.isShowing()) {
                                         progressDialog.dismiss();
                                     }

                                     Toast.makeText(Pick_Velocity_ReportActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                                     Log.d("Error", response.errorBody().toString());
                                 } else if (response.code() == 500) {

                                     if (progressDialog.isShowing()) {
                                         progressDialog.dismiss();
                                     }

                                     Toast.makeText(Pick_Velocity_ReportActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                                     Log.d("Error", response.errorBody().toString());
                                 } else {
                                     if (progressDialog.isShowing()) {
                                         progressDialog.dismiss();
                                     }
                                     Toast.makeText(Pick_Velocity_ReportActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                                 }
                             }

                             @Override
                             public void onFailure(Call<PostResponse> call, Throwable t) {

                                 call.cancel();
                                 Log.e("response-failure", t.toString());
                                 Toast.makeText(Pick_Velocity_ReportActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                                 if (progressDialog.isShowing()) {
                                     progressDialog.dismiss();
                                 }

                             }
                         }

            );

        }
    }

    private void spinner(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(Pick_Velocity_ReportActivity.this,
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
                    Toast.makeText(Pick_Velocity_ReportActivity.this, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (order != null) {

                        ArrayList<SpinnerPojo> countryList = new ArrayList<>();

//                for spinner try
                        for (int i = 0; i < order.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = order.get(i).getCustomerName();
                            Long cus_id = order.get(i).getCustomerId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            countryList.add(order1);
                        }
                        Log.d("spinner_list", countryList.toString());

                        mspinner.setAdapter(new ArrayAdapter<SpinnerPojo>(Pick_Velocity_ReportActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, countryList));

                        mspinner.setCursorVisible(false);
                        mspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mspinner.showDropDown();
//                                selection = (String) parent.getItemAtPosition(position);

                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                                cus_id = sp.getCus_id();


                                Toast.makeText(Pick_Velocity_ReportActivity.this, "Cus ID: " + sp.getCus_id() + ",  " +
                                        " Name : " + sp.getName(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        mspinner.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mspinner.showDropDown();
                            }
                        });

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(Pick_Velocity_ReportActivity.this, d, Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(Pick_Velocity_ReportActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(Pick_Velocity_ReportActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(Pick_Velocity_ReportActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(Pick_Velocity_ReportActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrdGrpByCus> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(Pick_Velocity_ReportActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        });
    }


}
