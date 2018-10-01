package com.example.colors2web.zummix_app.Activities;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.colors2web.zummix_app.Fragments.Frag_AddAndItems;
import com.example.colors2web.zummix_app.Fragments.Frag_Box;
import com.example.colors2web.zummix_app.Fragments.Frag_MasterBox;
import com.example.colors2web.zummix_app.Fragments.Frag_OrderLogs;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Box;
import com.example.colors2web.zummix_app.POJO.Order2POJO.ItemDetail;
import com.example.colors2web.zummix_app.POJO.Order2POJO.MasterBox;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response2;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response3;
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderLog;
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderShippingAddressesDetail;
import com.example.colors2web.zummix_app.POJO.OrderEdit.EditExpedite;
import com.example.colors2web.zummix_app.POJO.OrderEdit.EditOrderType;
import com.example.colors2web.zummix_app.POJO.OrderEdit.EditShipMethod;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderCancel;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditResponse;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderDetails;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderSearchResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSearch2Activity extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "SEARCH_FRAGMENT";
    APIInterface apiInterface;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;


    @BindView(R.id.ord2_storename)
    TextView storename;

    @BindView(R.id.ord2_ship_method)
    TextView mship_method;

    @BindView(R.id.cancel_button)
    Button cancel_button;

    @BindView(R.id.btn_edit_ship_method)
    Button btn_ship_method;

    @BindView(R.id.btn_edit_shipping_type)
    Button btn_ship_type;

    @BindView(R.id.btn_expedite)
    Button mexpedite;


    @BindView(R.id.ord2_ship_type)
    TextView mship_type;

    @BindView(R.id.ord2_status)
    TextView status;

    @BindView(R.id.paused)
    TextView paused;

    @BindView(R.id.ready_to_batch)
    TextView ready;

    @BindView(R.id.refunded)
    TextView refunded;

    @BindView(R.id.ord2_sdate1)
    TextView date;

    @BindView(R.id.spp_request)
    TextView pp;

    @BindView(R.id.expedite)
    TextView expedite;


    @BindView(R.id.ship_it_now_batch)
    TextView ready_now;

    @BindView(R.id.batch_printed)
    TextView printed;

//    @BindView(R.id.btn_shipmethod2)
//    Button ship_btn;


    String selection;
    String pshipp;

    SwipeRefreshLayout mSwipeRefreshLayout;

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
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersearch2);

        btn_ship_method = findViewById(R.id.btn_edit_ship_method);
        btn_ship_type = findViewById(R.id.btn_edit_shipping_type);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
//        btn_ship_type.setVisibility(View.VISIBLE);

        ButterKnife.bind(this);

        //for toolbarsetup with back arrow
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderSearch2Activity.super.onBackPressed();
                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
            }
        });

        //  tablayout with pageviewer set up
        tabLayout.setupWithViewPager(viewPager);


        apiInterface = APIClient.getClient().create(APIInterface.class);
//        cancel_button.setVisibility(View.GONE);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String group_type = preferences.getString("group_type", "");
        final String l_id = preferences.getString("l_id", "");


        Log.d("PathMail", email);

        final Intent i = getIntent();

        if (i != null) {

            final String Path = i.getExtras().getString("OPath");
            if (Path != null) {
                call(group_type, email, password, Path);
            }

            final String Path2 = i.getExtras().getString("O_no_by_group");
            if (Path2 != null) {
                call_next(group_type, email, password, Path2);
            }

            final String Path3 = i.getExtras().getString("id1");
            if (Path3 != null) {
                call_next(group_type, email, password, Path3);
            }

            final String Path4 = i.getExtras().getString("tadpt_id");
            if (Path3 != null) {
                call_next(group_type, email, password, Path4);
            }

            final String Path5 = i.getExtras().getString("edit_id");
            if (Path5 != null) {
                call_next(group_type, email, password, Path5);
            }

            final String back = i.getExtras().getString("back_id");
            if (back != null) {
                call_next(group_type, email, password, back);
                Log.d("back", back);
            }
        }


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                if (i != null) {

                    final String Path = i.getExtras().getString("OPath");
                    if (Path != null) {
                        call(group_type, email, password, Path);
                    }

                    final String Path2 = i.getExtras().getString("O_no_by_group");
                    if (Path2 != null) {
                        call_next(group_type, email, password, Path2);
                    }

                    final String Path3 = i.getExtras().getString("id1");
                    if (Path3 != null) {
                        call_next(group_type, email, password, Path3);
                    }

                    final String Path4 = i.getExtras().getString("tadpt_id");
                    if (Path3 != null) {
                        call_next(group_type, email, password, Path4);
                    }

                    final String Path5 = i.getExtras().getString("edit_id");
                    if (Path5 != null) {
                        call_next(group_type, email, password, Path5);
                    }

                    final String back = i.getExtras().getString("back_id");
                    if (back != null) {
                        call_next(group_type, email, password, back);
                        Log.d("back", back);
                    }
                }

                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

    }

    private void call(final String group_type, final String email, final String password, String Path) {
        final ProgressDialog progressDialog = new ProgressDialog(OrderSearch2Activity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<OrderSearchResponse> call = apiInterface.getOrder1(email, password, Path);
        call.enqueue(new Callback<OrderSearchResponse>() {
            @Override
            public void onResponse(Call<OrderSearchResponse> call, Response<OrderSearchResponse> response) {

                if (response.isSuccessful()) {
                    OrderSearchResponse resp1 = response.body();

                    OrderDetails order = resp1.getOrderDetails();

                    if (order != null) {

                        Long id1 = order.getId();
                        call_next(group_type, email, password, String.valueOf(id1));
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    } else {
                        String d = response.body().getMessage();
//                        head.setText(d);
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }


                } else if (response.code() == 404) {

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(OrderSearch2Activity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<OrderSearchResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(OrderSearch2Activity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void call_next(final String group_type, final String email, final String password, final String id) {
        final ProgressDialog progressDialog = new ProgressDialog(OrderSearch2Activity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<JsonElement> call = apiInterface.getsecsearch(email, password, id);
        Log.d("final_id", String.valueOf(id));

        call.enqueue(new Callback<JsonElement>() {
            @Override
            public void onResponse(Call<JsonElement> call, Response<JsonElement> response) {

                if (response.isSuccessful()) {

                    Toast.makeText(getApplicationContext(), "Data is accessed", Toast.LENGTH_SHORT).show();
                    JsonElement element = response.body();

                    if (element.getAsJsonObject().getAsJsonObject("orderDetails").get("boxes") instanceof JsonArray) {
//                        checking if boxes is an instance of array

                        if (element.getAsJsonObject().getAsJsonObject("orderDetails").get("boxes") instanceof JsonArray &&
                                element.getAsJsonObject().getAsJsonObject("orderDetails").get("masterBoxes") instanceof JsonArray) {

                            Order2Response3 response_three = new Gson().fromJson(element.getAsJsonObject(), Order2Response3.class);

                            Order2Response3.OrderDetails order = response_three.getOrderDetails();
                            OrderDetails orda = new OrderDetails();

                            if (order != null) {

                                String id = String.valueOf(order.getOrder().getId());
                                String ords_no = String.valueOf(order.getOrder().getOrderNumber());

                                toolbar.setTitle(ords_no);

//                                head.setText(String.valueOf(order.getOrder().getOrderNumber()));

//                                Long value1 = Long.valueOf(0);
//                                Long value = order.getOrder().getBatchNumber();
//                                if (value1.longValue() != value.longValue()) {
//                                    batch.setText("Batch Number : " + value);
//                                }

                                String store_name = order.getOrder().getCustomerName();
                                String store_email = order.getOrder().getCustomerEmail();
                                String order_no = order.getOrder().getOrderNumber();
                                String cus_id = String.valueOf(order.getOrder().getCustomerId());
                                String special_inst = order.getOrder().getOrderSpecialInstruction();
                                String order_date = order.getOrder().getOrderDate();
                                String ship_method = order.getOrder().getShipMethod();
                                String edit_shipping_address = order.getOrder().getEditShippingAddress();
                                String order_type = order.getOrder().getOrderType();
                                String order_status = order.getOrder().getOrderStatus();
                                String emp_id = order.getOrder().getEmployeeId();

                                String ispaused = order.getOrder().getIsPaused();
                                String isready = order.getOrder().getIsReadyToBatch();
                                String ispp = String.valueOf(order.getOrder().getIsPpRequestCreated());
                                String isexpedite = order.getOrder().getmIsExpedite();
                                String isready_now = order.getOrder().getIsReadyToBatch();
                                String isprinted = String.valueOf(order.getOrder().getIsBatchPrinted());
                                String isrefund = order.getOrder().getIsRefunded();


                                storename.setText(store_name);
                                date.setText(order_date);

                                if (order_type.equals("0")) {
                                    mship_method.setText("Type: " + "Field Office Delivery");
                                } else if (ship_method.equals("1")) {
                                    mship_method.setText("Type: " + "Ship To");
                                } else {
                                    mship_method.setText("Type: " + "VIP Delivery");
                                }
                                mship_type.setText(order_type);

                                orda.setId(Long.valueOf(id));
                                orda.setOrderStatus(order_status);
                                orda.setOrderType(order_type);
                                orda.setEditShippingAddress(edit_shipping_address);

                                switch (order_status) {

                                    case "Order Created":
                                        status.setText("Order Created");
                                        status.setTextColor(status.getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Cancelled":
                                        status.setText("Cancelled");
                                        status.setTextColor(status.getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Batch Created":
                                        status.setText("Batch Created");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Picked":
                                        status.setText("Picked");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Packed":
                                        status.setText("Packed");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.blue));
                                        break;

                                    case "IOR":
                                        status.setText("IOR");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Shipped":
                                        status.setText("Shipped");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_dark));
                                        break;

                                    case "Edited Shipping Address":
                                        status.setText("Edited Shipping Address");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.purple));
                                        break;


                                    case "Pick List Printed":
                                        status.setText("Pick List Printed");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Cancelled Shipment":
                                        status.setText("Cancelled Shipment");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Pick Started":
                                        status.setText("Pick Started");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.purple));
                                        break;

                                    case "Pick Ended":
                                        status.setText("Pick Ended");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_dark));

                                        break;

                                    case "Pack Started":
                                        status.setText("Pack Started");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.purple));
                                        break;

                                    case "Pack Ended":
                                        status.setText("Pack Ended");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Unboxed":
                                        status.setText("Unboxed");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Paused":
                                        status.setText("Paused");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Unpaused":
                                        status.setText("Unpaused");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Edited Deadline":
                                        status.setText("Edited Deadline");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Unbatched":
                                        status.setText("Unbatched");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Return Label":
                                        status.setText("Return Label");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Quick Batch/Pack":
                                        status.setText("Quick Batch/Pack");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Edited Shipping Method":
                                        status.setText("Edited Shipping Method");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Expedite Order":
                                        status.setText("Expedite Order");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Order Tupe Changed ":
                                        status.setText("Order Tupe Changed ");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "On Hold":
                                        status.setText("On Hold");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    default:
                                        break;
                                }

//                                status.setText(order_status);
                                if(ispaused != null){
                                if (ispaused.equals("1")) {
                                    paused.setVisibility(View.VISIBLE);
                                }}

                                if (isready != null &&isready.equals("1")) {
                                    ready.setVisibility(View.VISIBLE);
                                }

                                if (ispp != null && ispp.equals("1")) {
                                    pp.setVisibility(View.VISIBLE);
                                }

                                if(isexpedite != null){
                                if (isexpedite.equals("1")) {
                                    expedite.setVisibility(View.VISIBLE);
                                } else {
                                    mexpedite.setVisibility(View.VISIBLE);
                                    makeExpedite(ords_no, id);
                                }}

                                if (isready != null &&isready_now.equals("1")) {
                                    ready.setVisibility(View.VISIBLE);
                                }

                                if (isprinted != null &&isprinted.equals("1")) {
                                    printed.setVisibility(View.VISIBLE);
                                }

                                if (isrefund != null && isrefund.equals("1")) {
                                    refunded.setVisibility(View.VISIBLE);
                                }

                                String type = group_type;

                                if (type != null) {
                                    if (type.equals("Admin") || order_status.equals("On Hold")) {

                                        cancel_button.setVisibility(View.VISIBLE);
                                        showCancelButton(ords_no, id, cus_id, order_status, type, email, password);

                                    } else if (type.equals("Super Admin")) {

                                        if (order_status.equals("Shipped")) {
                                            cancel_button.setVisibility(View.GONE);
                                            mexpedite.setVisibility(View.GONE);

                                        } else if (order_status.equals("Cancelled")) {
                                            cancel_button.setVisibility(View.GONE);

                                        } else {
                                            cancel_button.setVisibility(View.VISIBLE);
                                            showCancelButton(ords_no, id, cus_id, order_status, type, email, password);
                                        }
                                    } else {
                                        cancel_button.setVisibility(View.GONE);
                                    }
                                }

                                if (type.equals("Admin") || order_status.equals("On Hold")) {

                                    btn_ship_type.setVisibility(View.VISIBLE);
                                    btn_ship_method.setVisibility(View.VISIBLE);

                                    showEditShipMethod(ords_no, id, ship_method);
                                    showEditShipType(ords_no, id, order_type);
                                    ;

                                }

                                if (type.equals("Super Admin")) {

                                    if (order_status.equals("Shipped")) {

                                        btn_ship_type.setVisibility(View.GONE);
                                        btn_ship_method.setVisibility(View.GONE);

                                    } else {
                                        btn_ship_type.setVisibility(View.VISIBLE);
                                        btn_ship_method.setVisibility(View.VISIBLE);

                                        showEditShipMethod(ords_no, id, ship_method);
                                        showEditShipType(ords_no, id, order_type);
                                    }
                                }

                                ArrayList<ItemDetail> ItmList = new ArrayList<>();
                                List<ItemDetail> deps = order.getItemDetails();

                                for (int i = 0; i < deps.size(); i++) {

                                    ItemDetail dis = new ItemDetail();

                                    String sku = deps.get(i).getItemSku();
                                    String name = deps.get(i).getItemName();
                                    String quantity = String.valueOf(deps.get(i).getItemQuantity());

                                    dis.setItemSku(sku);
                                    dis.setItemName(name);
                                    dis.setItemQuantity(Long.valueOf(quantity));

                                    ItmList.add(dis); // must be the object of empty list initiated
                                }


                                ArrayList<OrderShippingAddressesDetail> ShipList = new ArrayList<>();
                                List<OrderShippingAddressesDetail> ord1 = order.getOrderShippingAddressesDetails();

                                String customer_country = null;
                                String customer_city = null;
                                String customer_state = null;
                                String customer_zip = null;
                                for (int i = 0; i < ord1.size(); i++) {

                                    OrderShippingAddressesDetail ship = new OrderShippingAddressesDetail();

                                    String fname = ord1.get(i).getCustomerFname();
                                    String lname = ord1.get(i).getCustomerLname();
                                    String phone = ord1.get(i).getCustomerPhone1();
                                    String address = ord1.get(i).getCustomerAddress1();
                                    String email = ord1.get(i).getCustomerEmail();
                                    String office = ord1.get(i).getCustomerOfficeName();
                                    customer_country = ord1.get(i).getCustomerCountry();
                                    customer_city = ord1.get(i).getCustomerCity();
                                    customer_state = ord1.get(i).getCustomerState();
                                    customer_zip = ord1.get(i).getCustomerZip();


                                    ship.setCustomerFname(fname);
                                    ship.setCustomerLname(lname);
                                    ship.setCustomerPhone1(phone);
                                    ship.setCustomerAddress1(address);
                                    ship.setCustomerEmail(email);
                                    ship.setCustomerOfficeName(office);


                                    ship.setCustomerCountry(customer_country);
                                    ship.setCustomerCity(customer_city);
                                    ship.setCustomerState(customer_state);
                                    ship.setCustomerZip(customer_zip);

                                    ShipList.add(ship);
                                }

                                ArrayList<OrderLog> LogList = new ArrayList<>();
                                List<OrderLog> logs = order.getOrderLogs();

                                for (int j = 0; j < logs.size(); j++) {

                                    OrderLog log1 = new OrderLog();

                                    String ord_no = logs.get(j).getOrderNumber();
                                    String ord_status = logs.get(j).getOrderStatus();
                                    String event_by = logs.get(j).getEventBy();
                                    String event_at = logs.get(j).getUpdatedAt();

                                    log1.setOrderNumber(ord_no);
                                    log1.setOrderStatus(ord_status);
                                    log1.setUpdatedAt(event_at);
                                    log1.setEventBy(event_by);

                                    LogList.add(log1); // must be the object of empty list initiated
                                }

                                ArrayList<Box> BoxList = new ArrayList<>();
                                List<Box> box = order.getBoxes();

                                for (int k = 0; k < box.size(); k++) {

                                    Box box1 = new Box();

                                    String box_no = box.get(k).getBoxNumber();
                                    String created_at = box.get(k).getCreatedAt();
                                    String tracking_code = box.get(k).getTrackingCode();
                                    String barcode = box.get(k).getBarcodeFileName();

                                    box1.setBoxNumber(box_no);
                                    box1.setCreatedAt(created_at);
                                    box1.setTrackingCode(tracking_code);
                                    box1.setBarcodeFileName(barcode);

                                    BoxList.add(box1); // must be the object of empty list initiated
                                }

                                ArrayList<MasterBox> MBoxList = new ArrayList<>();
                                List<MasterBox> mbox = order.getMasterBoxes();

                                for (int k = 0; k < mbox.size(); k++) {

                                    MasterBox mbox1 = new MasterBox();

                                    String box_no = mbox.get(k).getBoxNumber();
                                    String created_at = mbox.get(k).getCreatedAt();
                                    String barcode = mbox.get(k).getBarcodeFileName();

                                    mbox1.setBoxNumber(box_no);
                                    mbox1.setCreatedAt(created_at);
                                    mbox1.setBarcodeFileName(barcode);

                                    MBoxList.add(mbox1); // must be the object of empty list initiated

                                }

                                //pageviewer set up

                                setupViewPager(viewPager, id, store_name, store_email, order_no, special_inst, order_date,
                                        ship_method, order_type, order_status, edit_shipping_address, emp_id, ItmList, ShipList,
                                        LogList, BoxList, MBoxList, orda);
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }

                            } else {
                                Log.d("data", "No data");
                                Toast.makeText(getApplicationContext(), "No data to display", Toast.LENGTH_SHORT).show();

                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            }

                        }
//                        for box present but masterbox null
                        else {
                            Order2Response response_one = new Gson().fromJson(element.getAsJsonObject(), Order2Response.class);

                            Order2Response.OrderDetails order = response_one.getOrderDetails();
                            OrderDetails ordP = new OrderDetails();

                            if (order != null) {

                                String id = String.valueOf(order.getOrder().getId());
                                String ords_no = String.valueOf(order.getOrder().getOrderNumber());

                                toolbar.setTitle(ords_no);

//                                head.setText(String.valueOf(order.getOrder().getOrderNumber()));
//
//                                Long value1 = Long.valueOf(0);
//                                Long value = order.getOrder().getBatchNumber();
//                                if (value1.longValue() != value.longValue()) {
//                                    batch.setText("Batch Number : " + value);
//                                }

                                String store_name = order.getOrder().getCustomerName();
                                Log.d("store_name", store_name);
                                String store_email = order.getOrder().getCustomerEmail();
                                String order_no = order.getOrder().getOrderNumber();
                                String cus_id = String.valueOf(order.getOrder().getCustomerId());
                                String special_inst = order.getOrder().getOrderSpecialInstruction();
                                String order_date = order.getOrder().getOrderDate();
                                String ship_method = order.getOrder().getShipMethod();
                                String order_type = order.getOrder().getOrderType();
                                String edit_shipping_address = order.getOrder().getEditShippingAddress();
                                String order_status = order.getOrder().getOrderStatus();
                                String emp_id = order.getOrder().getEmployeeId();

                                ordP.setOrderStatus(order_status);
                                ordP.setOrderType(order_type);
                                ordP.setId(Long.valueOf(id));
                                ordP.setEditShippingAddress(edit_shipping_address);


                                String ispaused = order.getOrder().getIsPaused();
                                String isready = order.getOrder().getIsReadyToBatch();
                                String ispp = String.valueOf(order.getOrder().getIsPpRequestCreated());
                                String isexpedite = order.getOrder().getmIsExpedite();
                                String isready_now = order.getOrder().getIsReadyToBatch();
                                String isprinted = String.valueOf(order.getOrder().getIsBatchPrinted());
                                String isrefund = order.getOrder().getIsRefunded();


                                storename.setText(store_name);
                                date.setText(order_date);

                                if (order_type.equals("0")) {
                                    mship_type.setText("Type: " + "Field Office Delivery");
                                } else if (ship_method.equals("1")) {
                                    mship_type.setText("Type: " + "Ship To");
                                } else {
                                    mship_type.setText("Type: " + "VIP Delivery");
                                }
                                mship_method.setText(ship_method);
//                                ship_btn.setVisibility(View.VISIBLE);
//                                ship_type2.setVisibility(View.VISIBLE);

                                switch (order_status) {

                                    case "Order Created":
                                        status.setText("Order Created");
                                        status.setTextColor(status.getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Cancelled":
                                        status.setText("Cancelled");
                                        status.setTextColor(status.getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Batch Created":
                                        status.setText("Batch Created");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Picked":
                                        status.setText("Picked");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Packed":
                                        status.setText("Packed");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.blue));
                                        break;

                                    case "IOR":
                                        status.setText("IOR");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Shipped":
                                        status.setText("Shipped");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_dark));
                                        break;

                                    case "Edited Shipping Address":
                                        status.setText("Edited Shipping Address");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.purple));
                                        break;


                                    case "Pick List Printed":
                                        status.setText("Pick List Printed");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Cancelled Shipment":
                                        status.setText("Cancelled Shipment");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.colorPrimaryDark));
                                        break;

                                    case "Pick Started":
                                        status.setText("Pick Started");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.purple));
                                        break;

                                    case "Pick Ended":
                                        status.setText("Pick Ended");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_dark));

                                        break;

                                    case "Pack Started":
                                        status.setText("Pack Started");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.purple));
                                        break;

                                    case "Pack Ended":
                                        status.setText("Pack Ended");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Unboxed":
                                        status.setText("Unboxed");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Paused":
                                        status.setText("Paused");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Unpaused":
                                        status.setText("Unpaused");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Edited Deadline":
                                        status.setText("Edited Deadline");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Unbatched":
                                        status.setText("Unbatched");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Return Label":
                                        status.setText("Return Label");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Quick Batch/Pack":
                                        status.setText("Quick Batch/Pack");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Edited Shipping Method":
                                        status.setText("Edited Shipping Method");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Expedite Order":
                                        status.setText("Expedite Order");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "Order Type Changed ":
                                        status.setText("Order Type Changed ");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    case "On Hold":
                                        status.setText("On Hold");
                                        status.setTextColor(status
                                                .getResources().getColor(R.color.green_light));
                                        break;

                                    default:
                                        break;
                                }

//                                status.setText(order_status);
                                if(ispaused != null){
                                    if (ispaused.equals("1")) {
                                        paused.setVisibility(View.VISIBLE);
                                    }}

                                if (isready != null &&isready.equals("1")) {
                                    ready.setVisibility(View.VISIBLE);
                                }

                                if (ispp != null && ispp.equals("1")) {
                                    pp.setVisibility(View.VISIBLE);
                                }

                                if(isexpedite != null){
                                    if (isexpedite.equals("1")) {
                                        expedite.setVisibility(View.VISIBLE);
                                    } else {
                                        mexpedite.setVisibility(View.VISIBLE);
                                        makeExpedite(ords_no, id);
                                    }}

                                if (isready != null &&isready_now.equals("1")) {
                                    ready.setVisibility(View.VISIBLE);
                                }

                                if (isprinted != null &&isprinted.equals("1")) {
                                    printed.setVisibility(View.VISIBLE);
                                }

                                if (isrefund != null && isrefund.equals("1")) {
                                    refunded.setVisibility(View.VISIBLE);
                                }

                                String type1 = group_type;
                                if (type1 != null) {

                                    if (type1.equals("Admin") || order_status.equals("On Hold")) {

                                        cancel_button.setVisibility(View.VISIBLE);
                                        showCancelButton(ords_no, id, cus_id, order_status, type1, email, password);

                                    } else if (type1.equals("Super Admin")) {

                                        if (order_status.equals("Shipped")) {

                                            cancel_button.setVisibility(View.GONE);
                                            mexpedite.setVisibility(View.GONE);

                                        } else if (order_status.equals("Cancelled")) {

                                            cancel_button.setVisibility(View.GONE);
                                        } else {
                                            cancel_button.setVisibility(View.VISIBLE);
                                            showCancelButton(ords_no, id, cus_id, order_status, type1, email, password);
                                        }
                                    }
                                } else {
                                    cancel_button.setVisibility(View.GONE);
                                }

                                if (type1.equals("Admin") || order_status.equals("On Hold")) {

                                    btn_ship_type.setVisibility(View.VISIBLE);
                                    btn_ship_method.setVisibility(View.VISIBLE);

                                    showEditShipMethod(ords_no, id, ship_method);
                                    showEditShipType(ords_no, id, order_type);
                                    ;

                                }

                                if (type1.equals("Super Admin")) {

                                    if (order_status.equals("Shipped")) {

                                        btn_ship_type.setVisibility(View.GONE);
                                        btn_ship_method.setVisibility(View.GONE);

                                    } else {
                                        btn_ship_type.setVisibility(View.VISIBLE);
                                        btn_ship_method.setVisibility(View.VISIBLE);

                                        showEditShipMethod(ords_no, id, ship_method);
                                        showEditShipType(ords_no, id, order_type);
                                    }
                                }


                                ArrayList<ItemDetail> ItmList = new ArrayList<>();
                                List<ItemDetail> deps = order.getItemDetails();

                                for (int i = 0; i < deps.size(); i++) {

                                    ItemDetail dis = new ItemDetail();

                                    String sku = deps.get(i).getItemSku();
                                    String name = deps.get(i).getItemName();
                                    String quantity = String.valueOf(deps.get(i).getItemQuantity());

                                    dis.setItemSku(sku);
                                    dis.setItemName(name);
                                    dis.setItemQuantity(Long.valueOf(quantity));

                                    ItmList.add(dis); // must be the object of empty list initiated
                                }

                                ArrayList<OrderShippingAddressesDetail> ShipList = new ArrayList<>();
                                List<OrderShippingAddressesDetail> ord1 = order.getOrderShippingAddressesDetails();

                                for (int i = 0; i < ord1.size(); i++) {

                                    OrderShippingAddressesDetail ship = new OrderShippingAddressesDetail();

                                    String fname = ord1.get(i).getCustomerFname();
                                    String lname = ord1.get(i).getCustomerLname();
                                    String phone = ord1.get(i).getCustomerPhone1();
                                    String address = ord1.get(i).getCustomerAddress1();
                                    String email = ord1.get(i).getCustomerEmail();
                                    String office = ord1.get(i).getCustomerOfficeName();

                                    ship.setCustomerFname(fname);
                                    ship.setCustomerLname(lname);
                                    ship.setCustomerPhone1(phone);
                                    ship.setCustomerAddress1(address);
                                    ship.setCustomerEmail(email);
                                    ship.setCustomerOfficeName(office);

                                    String customer_country = ord1.get(i).getCustomerCountry();
                                    String customer_city = ord1.get(i).getCustomerCity();
                                    String customer_state = ord1.get(i).getCustomerState();
                                    String customer_zip = ord1.get(i).getCustomerZip();

                                    ship.setCustomerCountry(customer_country);
                                    ship.setCustomerCity(customer_city);
                                    ship.setCustomerState(customer_state);
                                    ship.setCustomerZip(customer_zip);

                                    ShipList.add(ship);
                                }

                                ArrayList<OrderLog> LogList = new ArrayList<>();
                                List<OrderLog> logs = order.getOrderLogs();

                                for (int i = 0; i < logs.size(); i++) {

                                    OrderLog log1 = new OrderLog();

                                    String ord_no = logs.get(i).getOrderNumber();
                                    String ord_status = logs.get(i).getOrderStatus();
                                    String event_by = logs.get(i).getEventBy();
                                    String event_at = logs.get(i).getUpdatedAt();

                                    log1.setOrderNumber(ord_no);
                                    log1.setOrderStatus(ord_status);
                                    log1.setUpdatedAt(event_at);
                                    log1.setEventBy(event_by);

                                    LogList.add(log1); // must be the object of empty list initiated
                                }

                                ArrayList<Box> BoxList = new ArrayList<>();
                                List<Box> box = order.getBoxes();

                                for (int i = 0; i < box.size(); i++) {

                                    Box box1 = new Box();

                                    String box_no = box.get(i).getBoxNumber();
                                    String created_at = box.get(i).getCreatedAt();
                                    String tracking_code = box.get(i).getTrackingCode();
                                    String barcode = box.get(i).getBarcodeFileName();

                                    box1.setBoxNumber(box_no);
                                    box1.setCreatedAt(created_at);
                                    box1.setTrackingCode(tracking_code);
                                    box1.setBarcodeFileName(barcode);

                                    BoxList.add(box1); // must be the object of empty list initiated
                                }

                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }

                                //pageviewer set up
                                setupViewPager1(viewPager, id, store_name, store_email, order_no, special_inst,
                                        order_date, ship_method, edit_shipping_address,
                                        order_type, order_status, emp_id, ItmList, ShipList, LogList, BoxList, ordP);

                            } else {
                                Log.d("data2", "No data");
                                Toast.makeText(getApplicationContext(), "No data to display", Toast.LENGTH_SHORT).show();
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            }

                        }
                    } else {
//                        not masbox and masterbox

                        Order2Response2 response_two = new Gson().fromJson(element.getAsJsonObject(), Order2Response2.class);
                        Order2Response2.OrderDetails order2 = response_two.getM2OrderDetails();
                        OrderDetails ordZ = new OrderDetails();

                        if (order2 != null) {

                            String id = String.valueOf(order2.getOrder().getId());
                            String ords_no = String.valueOf(order2.getOrder().getOrderNumber());

                            toolbar.setTitle(ords_no);

//                            head.setText(ords_no);
//                            batch.setText("Batch Number : " + order2.getOrder().getBatchNumber());
//                            Not needed because batch number already will be Zero

                            String store_name = order2.getOrder().getCustomerName();
                            Log.d("store_name", store_name);
                            String store_email = order2.getOrder().getCustomerEmail();
                            String order_no = order2.getOrder().getOrderNumber();
                            String cus_id = String.valueOf(order2.getOrder().getCustomerId());
                            String special_inst = order2.getOrder().getOrderSpecialInstruction();
                            String order_date = order2.getOrder().getOrderDate();
                            String ship_method = order2.getOrder().getShipMethod();
                            String order_type = order2.getOrder().getOrderType();
                            String edit_shipping_address = order2.getOrder().getEditShippingAddress();

                            String order_status = order2.getOrder().getOrderStatus();
                            String emp_id = order2.getOrder().getEmployeeId();

                            ordZ.setId(Long.valueOf(id));
                            ordZ.setOrderStatus(order_status);
                            ordZ.setOrderType(order_type);
                            ordZ.setEditShippingAddress(edit_shipping_address);

                            String ispaused = order2.getOrder().getIsPaused();
                            String isready = order2.getOrder().getIsReadyToBatch();
                            String ispp = String.valueOf(order2.getOrder().getIsPpRequestCreated());
                            String isexpedite = order2.getOrder().getmIsExpedite();
                            String isready_now = order2.getOrder().getIsReadyToBatch();
                            String isprinted = String.valueOf(order2.getOrder().getIsBatchPrinted());
                            String isrefund = order2.getOrder().getIsRefunded();


                            storename.setText(store_name);
                            date.setText(order_date);
                            mship_method.setText(ship_method);

                            if (order_type.equals("0")) {
                                mship_type.setText("Type: " + "Field Office Delivery");
                            } else if (ship_method.equals("1")) {
                                mship_type.setText("Type: " + "Ship To");
                            } else {
                                mship_type.setText("Type: " + "VIP Delivery");
                            }


                            switch (order_status) {

                                case "Order Created":
                                    status.setText("Order Created");
                                    status.setTextColor(status.getResources().getColor(R.color.colorPrimaryDark));
                                    break;

                                case "Cancelled":
                                    status.setText("Cancelled");
                                    status.setTextColor(status.getResources().getColor(R.color.colorPrimaryDark));
                                    break;

                                case "Batch Created":
                                    status.setText("Batch Created");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.colorPrimaryDark));
                                    break;

                                case "Picked":
                                    status.setText("Picked");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Packed":
                                    status.setText("Packed");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.blue));
                                    break;

                                case "IOR":
                                    status.setText("IOR");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.colorPrimaryDark));
                                    break;

                                case "Shipped":
                                    status.setText("Shipped");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_dark));
                                    break;

                                case "Edited Shipping Address":
                                    status.setText("Edited Shipping Address");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.purple));
                                    break;


                                case "Pick List Printed":
                                    status.setText("Pick List Printed");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Cancelled Shipment":
                                    status.setText("Cancelled Shipment");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.colorPrimaryDark));
                                    break;

                                case "Pick Started":
                                    status.setText("Pick Started");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.purple));
                                    break;

                                case "Pick Ended":
                                    status.setText("Pick Ended");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_dark));

                                    break;

                                case "Pack Started":
                                    status.setText("Pack Started");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.purple));
                                    break;

                                case "Pack Ended":
                                    status.setText("Pack Ended");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Unboxed":
                                    status.setText("Unboxed");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Paused":
                                    status.setText("Paused");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Unpaused":
                                    status.setText("Unpaused");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Edited Deadline":
                                    status.setText("Edited Deadline");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Unbatched":
                                    status.setText("Unbatched");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Return Label":
                                    status.setText("Return Label");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Quick Batch/Pack":
                                    status.setText("Quick Batch/Pack");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Edited Shipping Method":
                                    status.setText("Edited Shipping Method");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Expedite Order":
                                    status.setText("Expedite Order");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "Order Tupe Changed ":
                                    status.setText("Order Tupe Changed ");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                case "On Hold":
                                    status.setText("On Hold");
                                    status.setTextColor(status
                                            .getResources().getColor(R.color.green_light));
                                    break;

                                default:
                                    break;
                            }
//                            status.setText(order_status);

                            if(ispaused != null){
                                if (ispaused.equals("1")) {
                                    paused.setVisibility(View.VISIBLE);
                                }}

                            if (isready != null &&isready.equals("1")) {
                                ready.setVisibility(View.VISIBLE);
                            }

                            if (ispp != null && ispp.equals("1")) {
                                pp.setVisibility(View.VISIBLE);
                            }

                            if(isexpedite != null){
                                if (isexpedite.equals("1")) {
                                    expedite.setVisibility(View.VISIBLE);
                                } else {
                                    mexpedite.setVisibility(View.VISIBLE);
                                    makeExpedite(ords_no, id);
                                }}

                            if (isready != null &&isready_now.equals("1")) {
                                ready.setVisibility(View.VISIBLE);
                            }

                            if (isprinted != null &&isprinted.equals("1")) {
                                printed.setVisibility(View.VISIBLE);
                            }

                            if (isrefund != null && isrefund.equals("1")) {
                                refunded.setVisibility(View.VISIBLE);
                            }

                            String type = group_type;
                            if (type != null) {

//                                if (type.equals("Admin") || order_status.equals("On Hold")) {
//
//                                    cancel_button.setVisibility(View.VISIBLE);
//                                    showCancelButton(ords_no, id, cus_id, order_status, type, email, password);
//
//                                } else if (type.equals("Super Admin") || !order_status.equals("Shipped") ||
//                                        !order_status.equals("Cancelled")) {
//
//                                    cancel_button.setVisibility(View.VISIBLE);
//                                    btn_ship_type.setVisibility(View.VISIBLE);
//                                    btn_ship_method.setVisibility(View.VISIBLE);
//                                    showCancelButton(ords_no, id, cus_id, order_status, type, email, password);
//                                    showEditShipMethod(ords_no,id);
//                                    showEditShipType(ords_no,id);
//
//                                } else {
//                                    cancel_button.setVisibility(View.GONE);
//                                    btn_ship_type.setVisibility(View.GONE);
//                                    btn_ship_method.setVisibility(View.GONE);
//                                }
                                if (type.equals("Admin") || order_status.equals("On Hold")) {

                                    cancel_button.setVisibility(View.VISIBLE);
                                    showCancelButton(ords_no, id, cus_id, order_status, type, email, password);

                                } else if (type.equals("Super Admin")) {

                                    if (order_status.equals("Shipped")) {

                                        cancel_button.setVisibility(View.GONE);

                                    } else if (order_status.equals("Cancelled")) {
                                        cancel_button.setVisibility(View.GONE);

                                    } else if (order_status.equals("On Hold")) {

                                        cancel_button.setVisibility(View.VISIBLE);
                                        showCancelButton(ords_no, id, cus_id, order_status, type, email, password);
                                    } else {
                                        cancel_button.setVisibility(View.VISIBLE);
                                        showCancelButton(ords_no, id, cus_id, order_status, type, email, password);
                                    }
                                } else {
                                    cancel_button.setVisibility(View.GONE);
                                }
                            }

                            if (type.equals("Admin") || order_status.equals("On Hold")) {

                                btn_ship_type.setVisibility(View.VISIBLE);
                                btn_ship_method.setVisibility(View.VISIBLE);

                                showEditShipMethod(ords_no, id, ship_method);
                                showEditShipType(ords_no, id, order_type);
                                ;

                            }

                            if (type.equals("Super Admin")) {

                                if (order_status.equals("Shipped")) {

                                    btn_ship_type.setVisibility(View.GONE);
                                    btn_ship_method.setVisibility(View.GONE);

                                } else {
                                    btn_ship_type.setVisibility(View.VISIBLE);
                                    btn_ship_method.setVisibility(View.VISIBLE);

                                    showEditShipMethod(ords_no, id, ship_method);
                                    showEditShipType(ords_no, id, order_type);
                                }
                            }


                            ArrayList<ItemDetail> ItmList = new ArrayList<>();
                            List<ItemDetail> deps = order2.getItemDetails();

                            for (int i = 0; i < deps.size(); i++) {

                                ItemDetail dis = new ItemDetail();

                                String sku = deps.get(i).getItemSku();
                                String name = deps.get(i).getItemName();
                                String quantity = String.valueOf(deps.get(i).getItemQuantity());


                                dis.setItemSku(sku);
                                dis.setItemName(name);
                                dis.setItemQuantity(Long.valueOf(quantity));

                                ItmList.add(dis); // must be the object of empty list initiated
                            }


                            ArrayList<OrderLog> LogList = new ArrayList<>();
                            List<OrderLog> logs = order2.getOrderLogs();

                            for (int i = 0; i < logs.size(); i++) {

                                OrderLog log1 = new OrderLog();

                                String ord_no = logs.get(i).getOrderNumber();
                                String ord_status = logs.get(i).getOrderStatus();
                                String event_by = logs.get(i).getEventBy();
                                String event_at = logs.get(i).getUpdatedAt();

                                log1.setOrderNumber(ord_no);
                                log1.setOrderStatus(ord_status);
                                log1.setUpdatedAt(event_at);
                                log1.setEventBy(event_by);

                                LogList.add(log1); // must be the object of empty list initiated
                            }

                            ArrayList<OrderShippingAddressesDetail> ShipList = new ArrayList<>();
                            List<OrderShippingAddressesDetail> ord1 = order2.getOrderShippingAddressesDetails();

                            for (int i = 0; i < ord1.size(); i++) {

                                OrderShippingAddressesDetail ship = new OrderShippingAddressesDetail();

                                String fname = ord1.get(i).getCustomerFname();
                                String lname = ord1.get(i).getCustomerLname();
                                String phone = ord1.get(i).getCustomerPhone1();
                                String address = ord1.get(i).getCustomerAddress1();
                                String email = ord1.get(i).getCustomerEmail();
                                String office = ord1.get(i).getCustomerOfficeName();

                                ship.setCustomerFname(fname);
                                ship.setCustomerLname(lname);
                                ship.setCustomerPhone1(phone);
                                ship.setCustomerAddress1(address);
                                ship.setCustomerEmail(email);
                                ship.setCustomerOfficeName(office);

                                String customer_country = ord1.get(i).getCustomerCountry();
                                String customer_city = ord1.get(i).getCustomerCity();
                                String customer_state = ord1.get(i).getCustomerState();
                                String customer_zip = ord1.get(i).getCustomerZip();

                                ship.setCustomerCountry(customer_country);
                                ship.setCustomerCity(customer_city);
                                ship.setCustomerState(customer_state);
                                ship.setCustomerZip(customer_zip);

                                ShipList.add(ship);
                            }

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            //pageviewer set up
                            setupViewPager2(viewPager, id, store_name, store_email, order_no, special_inst, order_date,
                                    ship_method, order_type, edit_shipping_address, order_status, emp_id, ItmList, ShipList,
                                    LogList, ordZ);

                        } else {
                            Log.d("store_name1", "No data");
                            Toast.makeText(getApplicationContext(), "No data to display", Toast.LENGTH_SHORT).show();

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    }
//                        pageviewer setup
//                       setupViewPager(viewPager, id, store_name, store_email, order_no, special_inst, order_date, ship_method, order_type, order_status, emp_id);

                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), "  Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }


                } else if (response.code() == 404) {

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 500) {

                    Toast.makeText(getApplicationContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    Toast.makeText(OrderSearch2Activity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                    Log.d("Api_error_default", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonElement> call, Throwable t) {

                if (t instanceof IOException) {
                    call.cancel();

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(OrderSearch2Activity.this, "Network Error", Toast.LENGTH_SHORT).show();
                } else {
                    call.cancel();
                    Log.e("response-failure", t.toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(OrderSearch2Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }

            }
        });

    }


    private void makeExpedite(String ords_no, final String id) {

        mexpedite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderSearch2Activity.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to make Expedite ?");

                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

//                                        Toast.makeText(OrderSearch2Activity.this,"You clicked yes button",Toast.LENGTH_LONG).show();

                                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(OrderSearch2Activity.this);
                                        final String email = preferences.getString("email", "");
                                        final String password = preferences.getString("password", "");
                                        final String l_id = preferences.getString("l_id", "");
//                                        String or_id = id;
                                        String is_exped = "1";


                                        EditExpedite sm1 = new EditExpedite(is_exped, l_id);
                                        Call<OrderEditResponse> call = apiInterface.putmarkExpedite(email, password, id, sm1);

                                        call.enqueue(new Callback<OrderEditResponse>() {
                                            @Override
                                            public void onResponse(Call<OrderEditResponse> call, Response<OrderEditResponse> response) {
                                                if (response.isSuccessful()) {

                                                    Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                    finish();
                                                    startActivity(getIntent());
                                                } else {
                                                    Toast.makeText(getApplicationContext(), "Fails to Upload", Toast.LENGTH_SHORT).show();

                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<OrderEditResponse> call, Throwable t) {

                                                Toast.makeText(getApplicationContext(), "Fails to Upload", Toast.LENGTH_SHORT).show();
                                                call.cancel();
                                            }
                                        });

                                    }
                                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
                        dialog.dismiss();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                Button buttonbackground = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//                buttonbackground.setBackgroundColor(Color.BLUE);
                buttonbackground.setTextColor(Color.BLUE);

                Button buttonbackground1 = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//                buttonbackground1.setBackgroundColor(Color.BLUE);
                buttonbackground1.setTextColor(Color.BLUE);

            }
        });
    }

    private void showEditShipType(final String ords_no, final String id, final String order_type) {

        btn_ship_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(getApplicationContext(), "I am Clicked", Toast.LENGTH_SHORT).show();

                showpopup1_order_type(ords_no, id, order_type);


            }
        });
    }

    private void showpopup1_order_type(String pship, final String id, String order_type) {


        LayoutInflater inflater = (LayoutInflater) OrderSearch2Activity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.modal_edit_order_type, null);

        WindowManager wm = (WindowManager) OrderSearch2Activity.this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();


        int height = display.getHeight();
        int width = display.getWidth();

        final PopupWindow popup = new PopupWindow(popupView,
                (int) (width * 0.8), WindowManager.LayoutParams.WRAP_CONTENT);
//                (int) (height / 2.5)
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.setAnimationStyle(android.R.style.Animation_Dialog);

//                popup.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popup.showAtLocation(tabLayout, Gravity.CENTER, 0, 0); //Displaying popup

        View container = (View) popup.getContentView().getParent();
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.7f;
        wm.updateViewLayout(container, p);


        TextView psubmit, pcancel;
        final TextView pship_type;
        final AutoCompleteTextView aspinner;
        Spinner lastoption;
//                TODO:AutoCOmplete TextVIew Or Spinner Implementation

        psubmit = popupView.findViewById(R.id.pop1_submit);
        pcancel = popupView.findViewById(R.id.pop1_cancel);
        pship_type = popupView.findViewById(R.id.pop1_ship_type);

//        aspinner = popupView.findViewById(R.id.pop1_spinner);
        lastoption = popupView.findViewById(R.id.pop2_spinner);


        switch (order_type) {
            case "0":

                pship_type.setText("Field Office Delivery");
                break;

            case "1":
                pship_type.setText("Ship To");
                break;

            case "2":
                pship_type.setText("VIP Delivery");
                break;
        }

        lastoption.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//Display the same Value
                Toast.makeText(OrderSearch2Activity.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
                selection = pship_type.getText().toString();
            }
        });

        pcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });


        psubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(OrderSearch2Activity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();


                if (selection.equals("Field Office Delivery")) {
                    pshipp = "0";
                } else if (selection.equals("Ship To")) {
                    pshipp = "1";
                } else {
                    pshipp = "2";
                }

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(OrderSearch2Activity.this);
                final String email = preferences.getString("email", "");
                final String password = preferences.getString("password", "");
                final String l_id = preferences.getString("l_id", "");
                String or_id = id;

                EditShipMethod sm = new EditShipMethod(pshipp, l_id);

                Call<OrderEditResponse> call = apiInterface.puteditShipMethod(email, password, or_id, sm);

                call.enqueue(new Callback<OrderEditResponse>() {
                    @Override
                    public void onResponse(Call<OrderEditResponse> call, Response<OrderEditResponse> response) {
                        if (response.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                            popup.dismiss();

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Fails to Upload", Toast.LENGTH_SHORT).show();
                            popup.dismiss();
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                        }

                    }

                    @Override
                    public void onFailure(Call<OrderEditResponse> call, Throwable t) {

                        Toast.makeText(getApplicationContext(), "Failed to Update." + "\n" + " No Internet Connection", Toast.LENGTH_SHORT).show();
                        popup.dismiss();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }
                });


            }
        });

    }

    private void showEditShipMethod(final String ords_no, final String id, final String ship_method) {

        btn_ship_method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "I am Clicked1", Toast.LENGTH_SHORT).show();

                showpop2_ship_method(ords_no, id, ship_method);
            }
        });
    }

    private void showpop2_ship_method(String ords_no, final String id, String ship_method) {

        LayoutInflater inflater = (LayoutInflater) OrderSearch2Activity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.modal_edit_shippingmethod, null);

        WindowManager wm = (WindowManager) OrderSearch2Activity.this.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int height = display.getHeight();
        int width = display.getWidth();

        final PopupWindow popup = new PopupWindow(popupView,
                (int) (width * 0.8), LinearLayout.LayoutParams.WRAP_CONTENT);
//                (int) (height / 2.5)
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.setAnimationStyle(android.R.style.Animation_Dialog);

        popup.showAtLocation(tabLayout, Gravity.CENTER, 0, 0); //Displaying popup

        View container = (View) popup.getContentView().getParent();
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.7f;
        wm.updateViewLayout(container, p);

        TextView psubmit, pcancel;
        final EditText pship_type;

        psubmit = popupView.findViewById(R.id.pop2_submit);
        pcancel = popupView.findViewById(R.id.pop2_cancel);
        pship_type = popupView.findViewById(R.id.pop2_order_type);
        pship_type.setText(ship_method);
        Log.d("Ship_method", ship_method);


        pcancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
            }
        });

        psubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog progressDialog = new ProgressDialog(OrderSearch2Activity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                String p = pship_type.getText().toString();

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(OrderSearch2Activity.this);
                final String email = preferences.getString("email", "");
                final String password = preferences.getString("password", "");
                final String l_id = preferences.getString("l_id", "");
                String or_id = id;


                EditOrderType sm = new EditOrderType(p, l_id);

                Call<OrderEditResponse> call = apiInterface.puteditOrderType(email, password, or_id, sm);

                call.enqueue(new Callback<OrderEditResponse>() {
                    @Override
                    public void onResponse(Call<OrderEditResponse> call, Response<OrderEditResponse> response) {
                        if (response.isSuccessful()) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(getIntent());
                            popup.dismiss();
                        } else {
                            Toast.makeText(getApplicationContext(), "Edit Failed", Toast.LENGTH_SHORT).show();
                            popup.dismiss();
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                        }
                    }

                    @Override
                    public void onFailure(Call<OrderEditResponse> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Operation Failed." + "\n" + " No Internet Connection", Toast.LENGTH_SHORT).show();
                        popup.dismiss();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }
                });

            }
        });
    }

    private void setupViewPager(ViewPager viewPager, String id, String store_name,
                                String store_email, String order_no, String special_inst, String order_date,
                                String ship_method, String order_type, String order_status, String edit_shipping_address, String emp_id, ArrayList<ItemDetail> itmList,
                                ArrayList<OrderShippingAddressesDetail> shipList, ArrayList<OrderLog> logList,
                                ArrayList<Box> boxList, ArrayList<MasterBox> mBoxList, OrderDetails ordP) {


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        Bundle b = new Bundle();
        Frag_AddAndItems add = new Frag_AddAndItems();
        b.putString("b_id", id);
        b.putString("order_status", order_status);
        b.putString("order_type", order_type);
        b.putString("edit_shipping_address", edit_shipping_address);
        b.putParcelableArrayList("shipping_detail", shipList);
        b.putParcelableArrayList("item_detail", itmList);
        b.putParcelable("OrderP", ordP);
        add.setArguments(b);
        adapter.addFrag(add, "Order Details");


        Frag_OrderLogs three = new Frag_OrderLogs();
        b.putParcelableArrayList("logs_detail", logList);
        three.setArguments(b);
        adapter.addFrag(three, "Order Logs");


        Frag_Box five = new Frag_Box();
        b.putParcelableArrayList("box_detail", boxList);
        five.setArguments(b);
        adapter.addFrag(five, "Boxes");


        Frag_MasterBox six = new Frag_MasterBox();
        b.putParcelableArrayList("masterbox_detail", mBoxList);
        six.setArguments(b);
        adapter.addFrag(six, "Master Box");

        viewPager.setAdapter(adapter);

    }


    private void setupViewPager1(ViewPager viewPager, String id, String store_name, String store_email, String order_no,
                                 String special_inst, String order_date, String ship_method, String edit_shipping_address,
                                 String order_type, String order_status, String emp_id, ArrayList<ItemDetail> itmList,
                                 ArrayList<OrderShippingAddressesDetail> shipList, ArrayList<OrderLog> logList,
                                 ArrayList<Box> boxList, OrderDetails ordP) {

//        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
//
//        Bundle bundle = new Bundle();
//        OneFragment one = new OneFragment();
//        bundle.putString("store_name", store_name);
//        bundle.putString("store_email", store_email);
//        bundle.putString("order_no", order_no);
//        bundle.putString("special_inst", special_inst);
//        bundle.putString("order_date", order_date);
//        bundle.putString("ship_method", ship_method);
//        bundle.putString("order_type", order_type);
//        bundle.putString("order_status", order_status);
//        bundle.putString("emp_id", emp_id);
//
//        one.setArguments(bundle);
//        adapter.addFrag(one, "Order Details");
//
//        Bundle b = new Bundle();
//        b.putString("b_id", id);
//        b.putString("order_status", order_status);
//        b.putString("order_type", order_type);
//        b.putString("edit_shipping_address", edit_shipping_address);
//        Frag_Shipping_Adderss two = new Frag_Shipping_Adderss();
//        b.putParcelableArrayList("shipping_detail", shipList);
//        two.setArguments(b);
//        adapter.addFrag(two, "Shipping Add");
//
//        Frag_OrderLogs three = new Frag_OrderLogs();
//        b.putParcelableArrayList("logs_detail", logList);
//        three.setArguments(b);
//        adapter.addFrag(three, "Order Logs");
//
//        Frag_ItemDetails four = new Frag_ItemDetails();
//        b.putParcelableArrayList("item_detail", itmList);
//        four.setArguments(b);
//        Log.d("display", itmList.toString());
//        adapter.addFrag(four, "Line Items");
//
//        Frag_Box five = new Frag_Box();
//        b.putParcelableArrayList("box_detail", boxList);
//        five.setArguments(b);
//        adapter.addFrag(five, "Boxes");


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        Bundle b = new Bundle();
        Frag_AddAndItems add = new Frag_AddAndItems();
        b.putString("b_id", id);
        b.putString("order_status", order_status);
        b.putString("order_type", order_type);
        b.putString("edit_shipping_address", edit_shipping_address);
        b.putParcelableArrayList("shipping_detail", shipList);
        b.putParcelableArrayList("item_detail", itmList);
        b.putParcelable("OrderP", ordP);
        add.setArguments(b);
        adapter.addFrag(add, "Order Details");


        Frag_OrderLogs three = new Frag_OrderLogs();
        b.putParcelableArrayList("logs_detail", logList);
        three.setArguments(b);
        adapter.addFrag(three, "Order Logs");


        Frag_Box five = new Frag_Box();
        b.putParcelableArrayList("box_detail", boxList);
        five.setArguments(b);
        adapter.addFrag(five, "Boxes");

        viewPager.setAdapter(adapter);
    }

    private void setupViewPager2(ViewPager viewPager, String id, String store_name, String store_email, String order_no,
                                 String special_inst, String order_date, String ship_method, String order_type,
                                 String edit_shipping_address, String order_status, String emp_id,
                                 ArrayList<ItemDetail> itmList, ArrayList<OrderShippingAddressesDetail> shipList,
                                 ArrayList<OrderLog> logList, OrderDetails ordP) {


        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());


        Bundle b = new Bundle();
        Frag_AddAndItems add = new Frag_AddAndItems();
        b.putString("b_id", id);
        b.putString("order_status", order_status);
        b.putString("order_type", order_type);
        b.putString("edit_shipping_address", edit_shipping_address);
        b.putParcelableArrayList("shipping_detail", shipList);
        b.putParcelableArrayList("item_detail", itmList);
        b.putParcelable("OrderP", ordP);
        add.setArguments(b);
        adapter.addFrag(add, "Order Details");


        Frag_OrderLogs three = new Frag_OrderLogs();
        b.putParcelableArrayList("logs_detail", logList);
        three.setArguments(b);
        adapter.addFrag(three, "Order Logs");


        viewPager.setAdapter(adapter);
    }

    private void showCancelButton(final String ords_no, final String id, final String cus_id, final String order_status, final String type,
                                  final String email, final String password) {

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
          public void onClick(View v) {
////                Alert Dailog adding
//
//                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(OrderSearch2Activity.this);
//                alertDialogBuilder.setMessage("Are you sure,You wanted to cancel Order?");
//
//                alertDialogBuilder.setPositiveButton("Proceed",
//                                new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface arg0, int arg1) {
////                                        Toast.makeText(OrderSearch2Activity.this,"You clicked yes
////                                                button",Toast.LENGTH_LONG).show();
//                                    }
//                                });
//
//                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
//                    Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        finish();
//                    }
//                });
//
//                AlertDialog alertDialog = alertDialogBuilder.create();
//                alertDialog.show();
//            }




                String ad;

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(OrderSearch2Activity.this);
                final String l_id = preferences.getString("l_id", "");


                if (type.equals("Super Admin")) {
                    ad = String.valueOf(1);
                } else {
                    ad = String.valueOf(0);
                }

                String status = "Cancelled";
                OrderCancel cancel = new OrderCancel(cus_id, status, ad, l_id, l_id);

                final ProgressDialog progressDialog = new ProgressDialog(OrderSearch2Activity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                Call<OrderCancel> call = apiInterface.putCancel(email, password, ords_no, cancel);

                Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                call.enqueue(new Callback<OrderCancel>() {
                    @Override
                    public void onResponse(Call<OrderCancel> call, Response<OrderCancel> response) {

                        OrderCancel cres = response.body();

                        if (response.isSuccessful()) {
                            String returntype = cres.getmReturnType();

                            if (returntype.equals("success")) {
//                                cancel_button.setVisibility(View.GONE);
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
//                                call_next(type, email, password, order);
//                                call(type, email, password, ords_no);

                                finish();
                                startActivity(getIntent());

                            } else {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(getApplicationContext(), cres.getmMessage(), Toast.LENGTH_SHORT).show();
                            }

                        } else if (response.code() == 401) {

                            Toast.makeText(getApplicationContext(), "  Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }


                        } else if (response.code() == 404) {

                            Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                        } else if (response.code() == 500) {

                            Toast.makeText(getApplicationContext(), "Server Broken", Toast.LENGTH_LONG).show();
                            Log.d("Error", response.errorBody().toString());

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                        } else {
                            Toast.makeText(OrderSearch2Activity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                            Log.d("Api_error_default", response.errorBody().toString());

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                        }
                    }


                    @Override
                    public void onFailure(Call<OrderCancel> call, Throwable t) {
                        if (t instanceof IOException) {
                            call.cancel();

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(OrderSearch2Activity.this, "Network Error", Toast.LENGTH_SHORT).show();
                        } else {
                            call.cancel();
                            Log.e("response-failure", t.toString());
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(OrderSearch2Activity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                            // todo log to some central bug tracking service
                        }

                    }
                });

            }
        });

    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();


        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            super.destroyItem(container, position, object);
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
