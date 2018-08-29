package com.example.colors2web.zummix_app.Activities.PostActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
import com.example.colors2web.zummix_app.POJO.PostSearch.DrShipment;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostResponse;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostServer;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Item_DR_HistoryActivity extends AppCompatActivity {

    APIInterface apiInterface;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.item_dr_spinner)
    AutoCompleteTextView mspinner;

    @BindView(R.id.item_dr_sku)
    TextView msku;

    @BindView(R.id.item_dr_from)
    TextView mform;

    @BindView(R.id.item_dr_to)
    TextView mto;

    @BindView(R.id.btn_item_dr_submit)
    Button btn_submit;

    ArrayList<String> spin;
    ArrayList<Long> spin_value;
    String selection;

    Integer mYear, mMonth, mDay;
    String mform1, mto1;
    ImageView img;
    Long cus_id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_item_dr);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        spinner(email, password);

        mform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Item_DR_HistoryActivity.this,
                        R.style.AppTheme_Light_DatePicker,new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mform.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            }

                        }, mYear, mMonth, mDay);

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                mform1 = format.format(c.getTime());
                datePickerDialog.show();
            }
        });

        mto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(Item_DR_HistoryActivity.this,
                        R.style.AppTheme_Light_DatePicker,new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                mto.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                            }

                        }, mYear, mMonth, mDay);

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                mto1 = format.format(c.getTime());
                datePickerDialog.show();
            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item_sku = msku.getText().toString();
                String from = mform.getText().toString();
                String to = mto.getText().toString();
                Long customer = cus_id;
                call_item_dr_history(email, password, item_sku, customer, from, to);
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

    private void call_item_dr_history(String email, String password, String item_sku, Long customer, String from, String to) {
        if (customer != null) {

            String cus_id = String.valueOf(customer);
            PostServer post = new PostServer(item_sku, cus_id, from, to);

            final ProgressDialog progressDialog = new ProgressDialog(Item_DR_HistoryActivity.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            Call<PostResponse> call = apiInterface.postItemDrHistory(email, password, post);
            call.enqueue(new Callback<PostResponse>() {
                             @Override
                             public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {
                                 if (response.isSuccessful()) {
                                     PostResponse res = response.body();

                                     List<DrShipment> DrList = new ArrayList<>();
                                     List<DrShipment> shipments = res.getmDrShipments();

                                     if (shipments != null) {


                                         for (int i = 0; i < shipments.size(); i++) {

                                             DrShipment drShipment = new DrShipment();

                                             String sku = shipments.get(i).getItemSkuNumber();
                                             String customer = shipments.get(i).getCustomerName();
                                             String item_name = shipments.get(i).getItemName();
                                             String dr = shipments.get(i).getDrType();
                                             Long pallets = shipments.get(i).getSaidPalletCount();
                                             Long palleta = shipments.get(i).getActualPalletCount();
                                             Long boxs = shipments.get(i).getSaidBoxCount();
                                             Long boxa = shipments.get(i).getActualBoxCount();
                                             Long items = shipments.get(i).getSaidItemCount();
                                             Long itema = shipments.get(i).getActualItemCount();
                                             String delivery = shipments.get(i).getEstDeliveryDate();

                                             drShipment.setItemSkuNumber(sku);
                                             drShipment.setCustomerName(customer);
                                             drShipment.setItemName(item_name);
                                             drShipment.setDrNumber(Long.valueOf(dr));
                                             drShipment.setSaidPalletCount(pallets);
                                             drShipment.setActualPalletCount(palleta);
                                             drShipment.setSaidBoxCount(boxs);
                                             drShipment.setActualBoxCount(boxa);
                                             drShipment.setActualItemCount(itema);
                                             drShipment.setEstDeliveryDate(delivery);

                                             DrList.add(drShipment);

                                         }
                                         Intent intent = new Intent(Item_DR_HistoryActivity.this,View_dr_historyActivity.class);
                                         intent.putExtra("DrList", (Serializable) DrList);
                                         startActivity(intent);

                                         Log.d("DrList", DrList.toString());
                                         if (progressDialog.isShowing()) {
                                             progressDialog.dismiss();
                                         }
                                     } else {
                                         Toast.makeText(getApplicationContext(), response.body().getmMessage(), Toast.LENGTH_LONG).show();
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
                                     Toast.makeText(Item_DR_HistoryActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                                 }
                             }

                             @Override
                             public void onFailure(Call<PostResponse> call, Throwable t) {

                                 call.cancel();
                                 Log.e("response-failure", t.toString());
                                 Toast.makeText(Item_DR_HistoryActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                                 if (progressDialog.isShowing()) {
                                     progressDialog.dismiss();
                                 }

                             }
                         }

            );

        }
    }
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

    private void spinner(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(Item_DR_HistoryActivity.this,
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

//                        spin = new ArrayList<String>();
//                        spin_value = new ArrayList<Long>();
//                for spinner try
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

                        mspinner.setAdapter(new ArrayAdapter<SpinnerPojo>(Item_DR_HistoryActivity.this,
                                android.R.layout.simple_spinner_dropdown_item, countryList));

                        mspinner.setCursorVisible(false);
                        mspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                mspinner.showDropDown();
//                                selection = (String) parent.getItemAtPosition(position);

                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                                cus_id = sp.getCus_id();


                                Toast.makeText(getApplicationContext(), "Cus ID: " + sp.getCus_id() + ",  " +
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
                    Toast.makeText(Item_DR_HistoryActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrdGrpByCus> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(Item_DR_HistoryActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        });
    }

}
