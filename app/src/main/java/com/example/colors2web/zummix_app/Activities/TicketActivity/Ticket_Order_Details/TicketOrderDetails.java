package com.example.colors2web.zummix_app.Activities.TicketActivity.Ticket_Order_Details;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.Navigation.HomeActivity;
import com.example.colors2web.zummix_app.Activities.TicketActivity.Fragment_ticket_details;
import com.example.colors2web.zummix_app.Activities.TicketActivity.Fragment_ticket_items;
import com.example.colors2web.zummix_app.Activities.TicketActivity.TicketNavigActivity;
import com.example.colors2web.zummix_app.Activities.TicketActivity.TicketSearchActivity;
import com.example.colors2web.zummix_app.Activities.TicketActivity.Ticket_Order_Details.OrderFragments.Fragment_ticket_order_Purchase;
import com.example.colors2web.zummix_app.Activities.TicketActivity.Ticket_Order_Details.OrderFragments.Fragment_ticket_order_Shipping;
import com.example.colors2web.zummix_app.Activities.TicketActivity.Ticket_Order_Details.OrderFragments.Fragment_ticket_order_orderdetails;
import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.TicketDetails.User;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.BillingDetails;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.OrderDetails;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.PaymentDetails;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.PurchasedProduct;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.ShippingDetails;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.Store;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.Support;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.SupportOrderDetails;
import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.TicketOrderDetailsResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.TicketResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.Tickets;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIClient1;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketOrderDetails extends AppCompatActivity {

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    APIInterface apiInterface;
    String order_number, storeid, supportid;

    ArrayList<PurchasedProduct> PurchaseList = new ArrayList<>();
    OrderDetails odetails;
    ShippingDetails sdetails;
    ArrayList<Tickets> SearchList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_order_details);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
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
                startActivity(new Intent(TicketOrderDetails.this, HomeActivity.class));
                isDestroyed();

            }
        });

        apiInterface = APIClient1.getClient().create(APIInterface.class);

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TicketOrderDetails.this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");


        final Intent intent = getIntent();

        if (intent != null) {

            storeid = intent.getExtras().getString("storeid");
            supportid = intent.getExtras().getString("supportid");
            order_number = intent.getExtras().getString("order_number");

            loadData(email, password, storeid, supportid, order_number);
        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

//                ReturnedList.clear();
//                User.clear();
//                CommentList.clear();

                loadData(email, password, storeid, supportid, order_number);
                mSwipeRefreshLayout.setRefreshing(false);

                Toast.makeText(TicketOrderDetails.this, "Refreshed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loadData(String email, String password, String storeid, String supportid, String order_number1) {

        final ProgressDialog progressDialog = new ProgressDialog(TicketOrderDetails.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<TicketOrderDetailsResponse> call = apiInterface.getTicketOrderDetails(email, password, storeid, supportid, order_number1);
        call.enqueue(new Callback<TicketOrderDetailsResponse>() {
            @Override
            public void onResponse(Call<TicketOrderDetailsResponse> call, Response<TicketOrderDetailsResponse> response) {

                if (response.isSuccessful()) {

                    TicketOrderDetailsResponse resp1 = response.body();
                    SupportOrderDetails details = resp1.getSupportOrderDetails();

                    Store store = details.getStore();
                    Support support = details.getSupport();

                    OrderDetails orderDetails = details.getOrderDetails();
                    ShippingDetails shippingDetails = details.getShippingDetails();
                    List<PurchasedProduct> purchasedProducts = details.getPurchasedProducts();

                    BillingDetails billing = details.getBillingDetails();
                    PaymentDetails paymentDetails = details.getPaymentDetails();

                    if (store != null) {

                        Store store1 = new Store();

                        Long id = store.getId();
                        String store_name = store.getStoreName();
                        String store_url = store.getStoreUrl();
                        String store_email = store.getStoreEmail();
                        String username = store.getUsername();
                        String password = store.getPassword();
                        String postback_url = store.getPostbackUrl();
                        String contact_person_email = store.getContactPersonEmail();
                        String contact_person_name = store.getContactPersonName();
                        String contact_person_phone = store.getContactPersonPhone();
                        String created_at = store.getCreatedAt();
                        Long created_by = store.getCreatedBy();
                        String updated_at = store.getUpdatedAt();
                        Long updated_by = store.getUpdatedBy();
                        String store_logo = store.getStoreLogo();
                        String store_code = store.getStoreCode();


                        store1.setContactPersonEmail(contact_person_email);
                        store1.setContactPersonName(contact_person_name);
                        store1.setContactPersonPhone(contact_person_phone);
                        store1.setCreatedBy(created_by);
                        store1.setCreatedAt(created_at);
                        store1.setId(id);
                        store1.setPassword(password);
                        store1.setPostbackUrl(postback_url);
                        store1.setStoreCode(store_code);
                        store1.setStoreEmail(store_email);
                        store1.setStoreLogo(store_logo);
                        store1.setStoreName(store_name);
                        store1.setStoreUrl(store_url);
                        store1.setUpdatedAt(updated_at);
                        store1.setUpdatedBy(updated_by);
                        store1.setUsername(username);
                    }

                    if (support != null) {

                        Support support1 = new Support();

                        String address1 = support.getAddress1();
                        String address2 = support.getAddress2();
                        String attachment = support.getAttachment();
                        String city = support.getCity();
                        String country = support.getCountry();
                        String created_at = support.getCreatedAt();
                        Long created_by = support.getCreatedBy();
                        String customer_email = support.getCustomerEmail();
                        String customer_name = support.getCustomerName();
                        String escalate_to_corporate = support.getEscalateToCorporate();
                        Long id = support.getId();
                        String order = support.getOrder();
                        String reply = support.getReply();
                        String shipping_cost = support.getShippingCost();
                        String shipping_label = support.getShippingLabel();
                        Long store_id = support.getStoreId();
                        String ticket_description = support.getTicketDescription();
                        String ticket_number = support.getTicketNumber();
                        String ticket_status = support.getTicketStatus();
                        String ticket_title = support.getTicketTitle();
                        toolbar.setTitle("Ticket: "+ ticket_title);
                        Long ticket_type = support.getTicketType();
                        String tracking_number = support.getTrackingNumber();
                        String updated_at = support.getUpdatedAt();
                        Long updated_by = support.getUpdatedBy();
                        String zip = support.getZip();
                        String state = support.getState();

                        support1.setTrackingNumber(tracking_number);
                        support1.setShippingCost(shipping_cost);
                        support1.setCustomerEmail(customer_email);
                        support1.setAddress1(address1);
                        support1.setAddress2(address2);
                        support1.setAttachment(attachment);
                        support1.setCity(city);
                        support1.setCountry(country);
                        support1.setCreatedAt(created_at);
                        support1.setCreatedBy(created_by);
                        support1.setCustomerName(customer_name);
                        support1.setReply(reply);
                        support1.setEscalateToCorporate(escalate_to_corporate);
                        support1.setId(id);
                        support1.setOrder(order);
                        support1.setShippingLabel(shipping_label);
                        support1.setStoreId(store_id);
                        support1.setTicketDescription(ticket_description);
                        support1.setTicketNumber(ticket_number);
                        support1.setTicketStatus(ticket_status);
                        support1.setTicketTitle(ticket_title);
                        support1.setTicketType(ticket_type);
                        support1.setUpdatedAt(updated_at);
                        support1.setUpdatedBy(updated_by);
                        support1.setZip(zip);
                        support1.setState(state);

                    }
                    if (orderDetails != null) {

                        odetails = new OrderDetails();

                        String created_at = orderDetails.getCreatedAt();
                        String sale_number = orderDetails.getSaleNumber();
                        String ship_method = orderDetails.getShipMethod();
                        String ship_cost = orderDetails.getShipCost();
                        String credit_type_name = orderDetails.getCreditTypeName();
                        String credit_used = orderDetails.getCreditUsed();
                        String grand_total = orderDetails.getGrandTotal();

                        odetails.setCreatedAt(created_at);
                        odetails.setSaleNumber(sale_number);
                        odetails.setShipMethod(ship_method);
                        odetails.setShipCost(ship_cost);
                        odetails.setCreditTypeName(credit_type_name);
                        odetails.setCreditUsed(credit_used);
                        odetails.setGrandTotal(grand_total);

                    }

                    if (shippingDetails != null) {

                        sdetails = new ShippingDetails();

                        String first_name = shippingDetails.getFirstName();
                        String last_name = shippingDetails.getLastName();
                        String address1 = shippingDetails.getAddress1();
                        String address2 = shippingDetails.getAddress2();
                        String city = shippingDetails.getCity();
                        String state = shippingDetails.getState();
                        String zip = shippingDetails.getZip();
                        String country = shippingDetails.getCountry();

                        sdetails.setFirstName(first_name);
                        sdetails.setLastName(last_name);
                        sdetails.setAddress1(address1);
                        sdetails.setAddress2(address2);
                        sdetails.setCity(city);
                        sdetails.setState(state);
                        sdetails.setZip(zip);
                        sdetails.setCountry(country);
                    }

                    if (purchasedProducts != null) {

                        for (int i = 0; i < purchasedProducts.size(); i++) {

                            PurchasedProduct purchase = new PurchasedProduct();

                            String product_name = purchasedProducts.get(i).getProductName();
                            String product_sku_number = purchasedProducts.get(i).getProductSkuNumber();
//                              String store_url is needed to display the image
                            String product_image = purchasedProducts.get(i).getProductImage();
                            Long ordered_quantity = purchasedProducts.get(i).getOrderedQuantity();
                            String product_price = purchasedProducts.get(i).getProductPrice();
                            Long item_total_price = purchasedProducts.get(i).getItemTotalPrice();

                            purchase.setProductSkuNumber(product_sku_number);
                            purchase.setProductName(product_name);
                            purchase.setProductImage(product_image);
                            purchase.setOrderedQuantity(ordered_quantity);
                            purchase.setProductPrice(product_price);
                            purchase.setItemTotalPrice(item_total_price);

                            PurchaseList.add(purchase);
                        }

                    }


                    tabLayout.setupWithViewPager(viewPager);
//                    tabLayout.setSelectedTabIndicatorColor(Color.BLUE);



                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(TicketOrderDetails.this.getSupportFragmentManager());

                    Bundle bundle1 = new Bundle();

                    Fragment_ticket_order_orderdetails pickf = new Fragment_ticket_order_orderdetails();
                    bundle1.putSerializable("odetails", odetails);
                    pickf.setArguments(bundle1);
                    viewPagerAdapter.addFrag(pickf, "Order Details ");

                    Fragment_ticket_order_Shipping loc1 = new Fragment_ticket_order_Shipping();
//                bundle1.putParcelableArrayList("PurchaseList", PurchaseList);
                    bundle1.putSerializable("sdetails", sdetails);
                    loc1.setArguments(bundle1);
                    viewPagerAdapter.addFrag(loc1, "Shipping Address ");


                    Fragment_ticket_order_Purchase loc = new Fragment_ticket_order_Purchase();
                    bundle1.putParcelableArrayList("PurchaseList", PurchaseList);
                    loc.setArguments(bundle1);
                    viewPagerAdapter.addFrag(loc, "Purchased Products");


                    viewPager.setAdapter(viewPagerAdapter);

                    viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {
                            enableDisableSwipeRefresh(state == ViewPager.SCROLL_STATE_IDLE);
                        }
                    });

//                        padapter.updateAdapter(UserList);

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                } else if (response.code() == 401) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(TicketOrderDetails.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(TicketOrderDetails.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(TicketOrderDetails.this, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(TicketOrderDetails.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TicketOrderDetailsResponse> call, Throwable t) {

                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(TicketOrderDetails.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }


    private void enableDisableSwipeRefresh(boolean b) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setEnabled(b);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ticket_menu, menu);

        final MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setQueryHint("Enter Ticket Number......");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                // Toast like print
                Toast.makeText(TicketOrderDetails.this, "SearchOnQueryTextSubmit: " + query, Toast.LENGTH_SHORT).show();
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }

                final ProgressDialog progressDialog = new ProgressDialog(TicketOrderDetails.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                APIInterface apiInterface = APIClient1.getClient().create(APIInterface.class);
                String email = "email";
                String password = "password";

                Call<TicketResponse> call = apiInterface.getSearchTickets(email, password, query);
                call.enqueue(new Callback<TicketResponse>() {
                    @Override
                    public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {

                        if (response.isSuccessful()) {

                            TicketResponse resp1 = response.body();

                            List<Tickets> cus = resp1.getTickets();

                            if (cus != null && cus.size() > 0) {

                                for (int i = 0; i < cus.size(); i++) {

                                    Tickets tickets = new Tickets();

                                    String id = cus.get(i).getMid();
                                    String store_id = cus.get(i).getMstore_id();
                                    String cname = cus.get(i).getMcustomer_name();
                                    String sname = cus.get(i).getMstore_name();
                                    String surl = cus.get(i).getMstore_url();
                                    String created = cus.get(i).getMcreated_at();
                                    String updated = cus.get(i).getMupdated_at();
                                    String ticket_no = cus.get(i).getMticket_number();
                                    String ticket_status = cus.get(i).getMticket_status();
                                    String order = cus.get(i).getMorder();


                                    tickets.setMid(id);
                                    tickets.setMstore_id(store_id);
                                    tickets.setMcustomer_name(cname);
                                    tickets.setMstore_name(sname);
                                    tickets.setMstore_url(surl);
                                    tickets.setMcreated_at(created);
                                    tickets.setMupdated_at(updated);
                                    tickets.setMticket_number(ticket_no);
                                    tickets.setMticket_status(ticket_status);
                                    tickets.setMorder(order);

                                    SearchList.add(tickets); // must be the object of empty list initiated
                                }
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(TicketOrderDetails.this, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                textView.setVisibility(View.GONE);
//
                                Intent intent = new Intent(TicketOrderDetails.this, TicketSearchActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("SearchedTicketList", SearchList);
                                intent.putExtra("query", query);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            } else {
                                String d = response.body().getMessage();
                                Toast.makeText(TicketOrderDetails.this, d, Toast.LENGTH_LONG).show();
//                                textView.setVisibility(View.VISIBLE);
//                                textView.setText(d);
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            }
                        } else if (response.code() == 401) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketOrderDetails.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());


                        } else if (response.code() == 404) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketOrderDetails.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        } else if (response.code() == 500) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketOrderDetails.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        } else {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(TicketOrderDetails.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TicketResponse> call, Throwable t) {
                        call.cancel();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Log.e("response-failure", t.toString());
                        Toast.makeText(TicketOrderDetails.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });

                myActionMenuItem.collapseActionView();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        return true;
    }

}
