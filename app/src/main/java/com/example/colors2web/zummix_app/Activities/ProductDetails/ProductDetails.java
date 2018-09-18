package com.example.colors2web.zummix_app.Activities.ProductDetails;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
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
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_Inv_Logs;
import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_Pro_Elementary;
import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_pr_Location;
import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_pr_Pick;
import com.example.colors2web.zummix_app.Adapter.VIewPagerAdapterProduct;
import com.example.colors2web.zummix_app.POJO.ProductSearch.CustomPOJO;
import com.example.colors2web.zummix_app.POJO.ProductSearch.CustomerItemsDetail;
import com.example.colors2web.zummix_app.POJO.ProductSearch.DrShipmentItemLocations;
import com.example.colors2web.zummix_app.POJO.ProductSearch.InventoryLogs;
import com.example.colors2web.zummix_app.POJO.ProductSearch.PickLocation;
import com.example.colors2web.zummix_app.POJO.ProductSearch.ProductDetailsResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "SEARCH_FRAGMENT";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager_product)
    ViewPager viewPager;

    @BindView(R.id.pr_cus_name)
    TextView cus_name;

    @BindView(R.id.pr_cus_product)
    TextView product;

    @BindView(R.id.radio_pr_cus)
    RadioGroup radiogroup;

    @BindView(R.id.pr_cus_yes)
    RadioButton yes_button;

    @BindView(R.id.pr_cus_no)
    RadioButton no_button;

    @BindView(R.id.pr_cus_price)
    TextView price;

    @BindView(R.id.pr_cus_wt_uom)
    TextView wt_uom;

    @BindView(R.id.ipr_cus_country)
    TextView country;

    @BindView(R.id.switch_status)
    Switch switch_status;

    @BindView(R.id.ipr_cus_view_more)
    TextView view_more;


//    @BindView(R.id.ipr_cus_a2s)
//    TextView a2s;

    String c_name, c_item_sku, c_item_name, c_status, c_price, c_weight, c_uom, c_country, c_replenish, c_pick, c_ord_qty,
            c_req_qty, c_pickbalance, c_A2S;
    Long qoh11;
    String d_replenish,d_req;
    String warehouse_name, pick_aisle_name, section_level, section_lnumber, pick_bin;


    APIInterface apiInterface;


    String item_id, customer_id, item_sku_no;
    String email, password;
    CustomPOJO detail = new CustomPOJO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetails.super.onBackPressed();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

            }
        });


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        email = preferences.getString("email", "");
        password = preferences.getString("password", "");
        apiInterface = APIClient.getClient().create(APIInterface.class);


        if (getIntent() != null) {
            item_id = getIntent().getExtras().getString("id");
            customer_id = getIntent().getExtras().getString("customer_id");
            item_sku_no = getIntent().getExtras().getString("item_sku_no");

            loadaddress();

            tabLayout.setupWithViewPager(viewPager);

        }
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition(), true);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(), true);

            }
        });

        //  tablayout with pageviewer set up

    }

    private void loadaddress() {
        final ProgressDialog progressDialog = new ProgressDialog(ProductDetails.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<ProductDetailsResponse> call = apiInterface.getProductitems(email, password, item_id);
        call.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                if (response.isSuccessful()) {

                    ProductDetailsResponse resp = response.body();

                    List<CustomerItemsDetail> items = resp.getCustomerItemsDetail();

                    Toast.makeText(getApplicationContext(), resp.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (items != null) {

                        for (int i = 0; i < items.size(); i++) {

                            c_name = items.get(0).getCompanyName();
                            c_item_sku = items.get(0).getItemSkuNumber();
                            c_item_name = items.get(0).getItemName();
                            c_status = items.get(0).getItemStatus();
                            c_price = items.get(0).getPrice();
                            c_weight = items.get(0).getWeight();
                            c_uom = items.get(0).getUOM();
                            c_country = items.get(0).getCountryOrigin();
                            c_replenish = items.get(0).getReplenish();
                            c_pick = items.get(0).getPick();
                            c_ord_qty = items.get(0).getOrderedQuantity();
                            c_req_qty = items.get(0).getRequestedQuantity();


                        }
                        c_pickbalance = String.valueOf(resp.getPickBalance());
                        c_A2S = String.valueOf(resp.getA2S());

                        cus_name.setText(c_item_name);
                        toolbar.setTitle("Item: " +c_item_sku);
                        product.setText(c_item_sku);
                        price.setText(c_price);

                        wt_uom.setText(c_weight + "/" + c_uom);
                        country.setText(c_country);


//                        replenish.setText(c_replenish);
//                        pick.setText(c_pick);

                        if(c_replenish.equals(null)){
                            d_replenish = "0";
                        }
                        else{
                            d_replenish = c_replenish;
                        }
                        qoh11 = Long.valueOf(d_replenish) + Long.valueOf(c_pick);



                        detail.setReplenish(d_replenish);
                        detail.setPick(c_pick);
                        if (Long.valueOf(c_pickbalance) > 0) {
                            detail.setPick_balance(c_pickbalance);
                        } else {
                            detail.setPick_balance("0");
                        }
                        detail.setA2s(c_A2S);
                        detail.setO_quantity(c_ord_qty);
                        detail.setQoh(String.valueOf(qoh11));
                        detail.setCommitted(c_req_qty);



                        if (c_status != null) {
                            switch (c_status) {
                                case "1":
                                    radiogroup.check(R.id.pr_cus_yes);
                                    switch_status.setChecked(true);

                                    break;

                                case "0":
                                    radiogroup.check(R.id.pr_cus_no);
                                    switch_status.setChecked(false);
                                    break;
                            }
                        }

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        view_more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                                final View popupView = inflater.inflate(R.layout.activity_product_details_modal, null);

                                WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                                Display display = wm.getDefaultDisplay();

                                int height = display.getHeight();
                                int width = display.getWidth();

                                final PopupWindow popup = new PopupWindow(popupView,
                                        (int) (width * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
                                popup.setFocusable(true);
                                popup.setOutsideTouchable(true);
                                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                                popup.showAtLocation(view_more, Gravity.CENTER, 0, 0); //Displaying popup

                                final View container = (View) popup.getContentView().getParent();
                                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                                p.dimAmount = 0.7f;
                                wm.updateViewLayout(container, p);

                                Button cus_cancle;
                                final TextView cus_name1, country1, replenish1, pick1, qoh1, pickbalance1, ordq1, reqq1, product1,
                                        price1, wt_uom1, a2s1, commited1;

                                cus_name1 = popupView.findViewById(R.id.pr_cus_name);
                                cus_name1.setText(c_item_name);

                                product1 = popupView.findViewById(R.id.pr_cus_product);
                                product1.setText("Product:" + c_item_sku);

                                price1 = popupView.findViewById(R.id.pr_cus_price);
                                price1.setText(c_price);
                                wt_uom1 = popupView.findViewById(R.id.pr_cus_wt_uom);
                                wt_uom1.setText(c_weight + "/" + c_uom);
                                country1 = popupView.findViewById(R.id.ipr_cus_country);
                                country1.setText(c_country);
                                replenish1 = popupView.findViewById(R.id.ipr_cus_replenish);
                                replenish1.setText(c_replenish);
                                pick1 = popupView.findViewById(R.id.ipr_cus_pick);
                                pick1.setText(c_pick);
                                qoh1 = popupView.findViewById(R.id.ipr_cus_qoh);
                                qoh1.setText(String.valueOf(qoh11));
                                pickbalance1 = popupView.findViewById(R.id.ipr_cus_pickbalance);
                                if (Long.valueOf(c_pickbalance) > 0) {
                                    pickbalance1.setText(c_pickbalance);
                                } else {
                                    pickbalance1.setText("0");
                                }
                                ordq1 = popupView.findViewById(R.id.ipr_cus_ord);
                                ordq1.setText(c_ord_qty);
                                commited1 = popupView.findViewById(R.id.ipr_cus_committed);
                                commited1.setText(c_req_qty);
                                a2s1 = popupView.findViewById(R.id.ipr_cus_a2s);
                                a2s1.setText(c_A2S);
                                cus_cancle = popupView.findViewById(R.id.product_dismiss);

                                cus_cancle.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        popup.dismiss();
                                    }
                                });
                            }
                        });

                    }

                    PickLocation plocation = new PickLocation();

                    PickLocation location = resp.getPickLocation();

                    warehouse_name = location.getWarehouseName();
                    pick_aisle_name = location.getPickAisleName();
                    section_level = location.getSectionLevel();
                    section_lnumber = location.getSectionNumber();
                    pick_bin = location.getPickBin();

                    plocation.setWarehouseName(warehouse_name);
                    plocation.setPickAisleName(pick_aisle_name);
                    plocation.setSectionLevel(section_level);
                    plocation.setSectionNumber(section_lnumber);
                    plocation.setPickBin(pick_bin);
                    plocation.setPicku(String.valueOf(c_pick));

                    loadlocation(plocation);

//                    VIewPagerAdapterProduct vIewPagerAdapter = new VIewPagerAdapterProduct(getSupportFragmentManager());
//                    Frag_pr_Pick pickf = new Frag_pr_Pick();
//                    Bundle bundle1 = new Bundle();
//                    bundle1.putString("warehouse_name",warehouse_name);
//                    bundle1.putString("pick_aisle_name",pick_aisle_name);
//                    bundle1.putString("section_level",section_level);
//                    bundle1.putString("section_lnumber",section_lnumber);
//                    bundle1.putString("pick_bin",pick_bin);
//                    bundle1.putString("pick", String.valueOf(c_pick));
//                    pickf.setArguments(bundle1);
//                    pickf.loaddata(warehouse_name ,pick_aisle_name ,section_level ,section_lnumber ,pick_bin ,c_pick);


//                    vIewPagerAdapter.addFrag(pickf, "Pick",0);
//                    vIewPagerAdapter.notifyDataSetChanged();


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
                    Toast.makeText(ProductDetails.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(ProductDetails.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void loadlocation(final PickLocation plocation) {

        final ProgressDialog progressDialog = new ProgressDialog(ProductDetails.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ProductDetailsResponse> call = apiInterface.getProductLocations(email, password, item_sku_no, customer_id);
        call.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                if (response.isSuccessful()) {

                    ProductDetailsResponse resp = response.body();

                    List<DrShipmentItemLocations> items = resp.getmDrShipmentItemLocations();

                    Toast.makeText(getApplicationContext(), resp.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (items != null) {
                        ArrayList<DrShipmentItemLocations> ItmList = new ArrayList<>();


                        for (int i = 0; i < items.size(); i++) {

                            DrShipmentItemLocations itemz = new DrShipmentItemLocations();

                            Long item_count = items.get(i).getItemCount();
                            String pallet_count = items.get(i).getPalletCount();
                            String warehouse_name = items.get(i).getWarehouseName();
                            String aisle_name = items.get(i).getAisleName();
                            String bin_number = items.get(i).getBinNumber();
                            String bin_level = items.get(i).getBinLevel();
                            String bin = items.get(i).getBin();


                            itemz.setIsCase(item_count);
                            itemz.setPalletCount(pallet_count);
                            itemz.setWarehouseName(warehouse_name);
                            itemz.setAisleName(aisle_name);
                            itemz.setBinNumber(bin_number);
                            itemz.setBinLevel(bin_level);
                            itemz.setBin(bin);

                            ItmList.add(itemz);

                        }
                        loadlogs(plocation, ItmList);

//                        Frag_pr_Location loc = new Frag_pr_Location();
//                        Bundle bundle = new Bundle();
//                        bundle.putParcelableArrayList("ItmList", ItmList);
//                        Log.d("product_ship", ItmList.toString());
//                        loc.setArguments(bundle);
//                        viewPagerAdapter.addFrag(loc, "Location",1);
//                        viewPagerAdapter.notifyDataSetChanged();
////                        viewPager.setAdapter(viewPagerAdapter);


                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
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
                    Toast.makeText(ProductDetails.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(ProductDetails.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void loadlogs(final PickLocation plocation, final ArrayList<DrShipmentItemLocations> itmList) {
        final ProgressDialog progressDialog = new ProgressDialog(ProductDetails.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ProductDetailsResponse> call = apiInterface.getProductLogs(email, password, customer_id, item_sku_no);
        call.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                if (response.isSuccessful()) {


                    ProductDetailsResponse resp = response.body();
                    List<InventoryLogs> items = resp.getmInventoryLogs();

                    Toast.makeText(getApplicationContext(), resp.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (items != null) {

                        ArrayList<InventoryLogs> LogList = new ArrayList<>();

                        for (int i = 0; i < items.size(); i++) {

                            InventoryLogs itemz = new InventoryLogs();

                            Long id = items.get(i).getId();
                            String event_type = items.get(i).getEventType();
                            Long old_pallet_number = items.get(i).getOldPalletNumber();
                            Long new_pallet_number = items.get(i).getNewPalletNumber();
                            Long current_qty = items.get(i).getCurrentQty();
                            Long update_qty = items.get(i).getUpdateQty();
                            Long total_qty = items.get(i).getTotalQty();
                            String comment = items.get(i).getComment();
                            String created_at = items.get(i).getCreatedAt();
                            String from_location = items.get(i).getFromLocation();
                            String to_location = items.get(i).getToLocation();


                            itemz.setId(id);
                            itemz.setEventType(event_type);
                            itemz.setOldPalletNumber(old_pallet_number);
                            itemz.setNewPalletNumber(new_pallet_number);
                            itemz.setCurrentQty(current_qty);
                            itemz.setUpdateQty(update_qty);
                            itemz.setTotalQty(total_qty);
                            itemz.setComment(comment);
                            itemz.setCreatedAt(created_at);
                            itemz.setFromLocation(from_location);
                            itemz.setToLocation(to_location);

                            LogList.add(itemz);

                        }

                        setupViewPager(viewPager, plocation, itmList, LogList);


//                        Frag_Inv_Logs ilogs = new Frag_Inv_Logs();
//                        Bundle b = new Bundle();
//                        b.putParcelableArrayList("LogList", LogList);
//                        ilogs.setArguments(b);
//                        Log.d("product_log", LogList.toString());
//                        viewPagerAdapter.addFrag(ilogs, "Inventory Logs",2);
//                        viewPagerAdapter.notifyDataSetChanged();

//                        viewPager.setAdapter(viewPagerAdapter);

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
                        Toast.makeText(ProductDetails.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                    }
                }
            }

            @Override
            public void onFailure(Call<ProductDetailsResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(ProductDetails.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


    private void loadpick(VIewPagerAdapterProduct viewPagerAdapter) {

        Frag_pr_Pick pick = new Frag_pr_Pick();
        Bundle bundle1 = new Bundle();
        bundle1.putString("warehouse_name", warehouse_name);
        bundle1.putString("pick_aisle_name", pick_aisle_name);
        bundle1.putString("section_level", section_level);
        bundle1.putString("section_lnumber", section_lnumber);
        bundle1.putString("pick_bin", pick_bin);
        bundle1.putString("pick", String.valueOf(c_pick));
        pick.setArguments(bundle1);
        viewPagerAdapter.addFrag(pick, "Pick");
        viewPagerAdapter.notifyDataSetChanged();


//        viewPager.setAdapter(viewPagerAdapter);
    }


    private void setupViewPager(ViewPager viewPager, PickLocation plocation, ArrayList<DrShipmentItemLocations> itmList,
                                ArrayList<InventoryLogs> logList) {

        VIewPagerAdapterProduct viewPagerAdapter = new VIewPagerAdapterProduct(getSupportFragmentManager());


        Bundle bundle1 = new Bundle();
        Frag_Pro_Elementary pro = new Frag_Pro_Elementary();
        bundle1.putSerializable("detail", (Serializable) detail);
        pro.setArguments(bundle1);
        viewPagerAdapter.addFrag(pro, "Elementory");


        Bundle bundle2 = new Bundle();
        Frag_pr_Pick pickf = new Frag_pr_Pick();
        bundle2.putSerializable("plocation", (Serializable) plocation);
        pickf.setArguments(bundle2);
        viewPagerAdapter.addFrag(pickf, "Pick Location");


        Bundle bundle3 = new Bundle();
        Frag_pr_Location loc = new Frag_pr_Location();
        bundle3.putParcelableArrayList("ItmList", itmList);
        Log.d("product_ship", itmList.toString());
        loc.setArguments(bundle3);
        viewPagerAdapter.addFrag(loc, "Transfer to Pick");

        viewPager.setAdapter(viewPagerAdapter);


//        It is functional and it is only hided
//        Frag_Inv_Logs ilogs = new Frag_Inv_Logs();
//        bundle1.putParcelableArrayList("LogList", logList);
//        ilogs.setArguments(bundle1);
//        Log.d("product_log", logList.toString());
//        viewPagerAdapter.addFrag(ilogs, "Inventory Logs");


//       loadpick();
//        Fall into problem of only oveerding last fragments only(costs my hours)
//        It is due to binding the activity in recycleview instead of binding the view of fragment in fragments
//        getActivity().findview()  which have to be view.findview()

//        viewPager.setOffscreenPageLimit(3);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
                overridePendingTransition(R.anim.search_push_left_in, R.anim.search_push_left_out);

            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.image:
                return true;

            case R.id.image_try:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }

}
