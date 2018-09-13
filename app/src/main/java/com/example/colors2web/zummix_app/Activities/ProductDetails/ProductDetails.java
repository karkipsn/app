package com.example.colors2web.zummix_app.Activities.ProductDetails;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_Inv_Logs;
import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_pr_Location;
import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_pr_Pick;
import com.example.colors2web.zummix_app.Adapter.Order_Adapters.ItemSearchAdapter;
import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.ProductSearch.CustomerItemsDetail;
import com.example.colors2web.zummix_app.POJO.ProductSearch.DrShipmentItemLocations;
import com.example.colors2web.zummix_app.POJO.ProductSearch.InventoryLogs;
import com.example.colors2web.zummix_app.POJO.ProductSearch.ProductDetailsResponse;
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

public class ProductDetails extends AppCompatActivity {

    private static final String TAG_FRAGMENT = "SEARCH_FRAGMENT";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
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

    @BindView(R.id.ipr_cus_replenish)
    TextView replenish;

    @BindView(R.id.ipr_cus_pick)
    TextView pick;

    @BindView(R.id.ipr_cus_qoh)
    TextView qoh;

    @BindView(R.id.ipr_cus_pickbalance)
    TextView pickbalance;

    @BindView(R.id.ipr_cus_ord)
    TextView ordq;

    @BindView(R.id.ipr_cus_committed)
    TextView reqq;

    @BindView(R.id.ipr_cus_a2s)
    TextView a2s;

    String c_name, c_item_sku, c_item_name, c_status, c_price, weight, uom, c_country, c_replenish, c_pick, c_ord_qty,
            c_req_qty, c_pickbalance, c_A2S;


    APIInterface apiInterface;
    ArrayList<DrShipmentItemLocations>ItmList = new ArrayList<>();
    ArrayList<InventoryLogs>LogList = new ArrayList<>();


    String item_id, customer_id, item_sku_no;
    String email, password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDetails.super.onBackPressed();
                overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
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
            loadlocation();
            loadlogs();

        }
        setViewPager(viewPager);
    }

    private void setViewPager(ViewPager viewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        Frag_pr_Location loc = new Frag_pr_Location();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("ItmList", ItmList);
        loc.setArguments(bundle);
        adapter.addFrag(loc, "Location");


//        Frag_pr_Pick pick = new Frag_pr_Pick();
//        Bundle bundle1 = new Bundle();
//        bundle1.putSerializable("ItmList",ItmList);
//        pick.setArguments(bundle1);
//        adapter.addFrag(pick, "Pick");


        Frag_Inv_Logs logs = new Frag_Inv_Logs();
        Bundle bundle3 = new Bundle();
        bundle3.putParcelableArrayList("LogList",LogList);
        logs.setArguments(bundle3);
        adapter.addFrag(logs, "Logs");

        viewPager.setAdapter(adapter);

    }

    private void loadlogs() {
        final ProgressDialog progressDialog = new ProgressDialog(ProductDetails.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ProductDetailsResponse> call = apiInterface.getProductLogs(email, password, customer_id,item_sku_no);
        call.enqueue(new Callback<ProductDetailsResponse>() {
            @Override
            public void onResponse(Call<ProductDetailsResponse> call, Response<ProductDetailsResponse> response) {
                if (response.isSuccessful()) {

                    ProductDetailsResponse resp = response.body();

                    List<InventoryLogs> items = resp.getmInventoryLogs();

                    Toast.makeText(getApplicationContext(), resp.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (items != null) {

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

    private void loadlocation() {

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
//                        iadapter.updateAnswers(ItmList);
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
                            weight = items.get(0).getWeight();
                            uom = items.get(0).getUOM();
                            c_country = items.get(0).getCountryOrigin();
                            c_replenish = items.get(0).getReplenish();
                            c_pick = items.get(0).getPick();
                            c_ord_qty = items.get(0).getOrderedQuantity();
                            c_req_qty = items.get(0).getRequestedQuantity();

                        }
                        c_pickbalance = String.valueOf(resp.getPickBalance());
                        c_A2S = String.valueOf(resp.getA2S());

                        cus_name.setText(c_item_name);
                        product.setText(c_item_sku);
                        price.setText(c_price);
                        wt_uom.setText(weight + "/" + uom);
                        country.setText(c_country);
                        replenish.setText(c_replenish);
                        pick.setText(c_pick);
                        Long qoh1 = Long.valueOf(c_replenish) + Long.valueOf(c_pick);
                        qoh.setText(String.valueOf(qoh1));
                        pickbalance.setText(c_pickbalance);
                        ordq.setText(c_ord_qty);
                        reqq.setText(c_req_qty);
                        a2s.setText(c_A2S);

                        if (c_status != null) {
                            switch (c_status) {
                                case "1":
                                    radiogroup.check(R.id.pr_cus_yes);
                                    break;

                                case "0":
                                    radiogroup.check(R.id.pr_cus_no);
                                    break;
                            }
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
