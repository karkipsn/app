package com.example.colors2web.zummix_app.Activities.TicketActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.colors2web.zummix_app.Activities.Navigation.HomeActivity;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments.Archeived_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments.Return_Fragment;
import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Comments;
import com.example.colors2web.zummix_app.POJO.TicketDetails.ReturnedItem;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Store;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Support;
import com.example.colors2web.zummix_app.POJO.TicketDetails.TicketDetialsResponse;
import com.example.colors2web.zummix_app.POJO.TicketDetails.User;
import com.example.colors2web.zummix_app.POJO.Tickets.TicketResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.Tickets;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIClient1;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketDetailsActivity extends AppCompatActivity {

    String storeid, supportid;
    ArrayList<ReturnedItem> ReturnedList = new ArrayList<>();
    ArrayList<User> User = new ArrayList<>();
    ArrayList<Comments> CommentList = new ArrayList<>();
    Support support1;
    Store store1;
    APIInterface apiInterface;
    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager viewPager;
    FloatingActionButton fab;
    SwipeRefreshLayout mSwipeRefreshLayout;
    ArrayList<Tickets> SearchList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_details);

        toolbar = findViewById(R.id.ticket_details_toolbar);
        fab = findViewById(R.id.fab);

        //for toolbarsetup with back arrow
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
                startActivity(new Intent(TicketDetailsActivity.this, HomeActivity.class));
                isDestroyed();

            }
        });

        apiInterface = APIClient1.getClient().create(APIInterface.class);
        tabLayout = findViewById(R.id.tabs_ticket_details);
        viewPager = findViewById(R.id.viewpager_ticket_details);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TicketDetailsActivity.this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");


        final Intent intent = getIntent();

        if (intent != null) {

            storeid = intent.getExtras().getString("storeid");
            supportid = intent.getExtras().getString("supportid");

            loadData(email, password, storeid, supportid);
        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                ReturnedList.clear();
                User.clear();
                CommentList.clear();

                loadData(email, password, storeid, supportid);
                mSwipeRefreshLayout.setRefreshing(false);

                Toast.makeText(TicketDetailsActivity.this, "Refreshed", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void loadData(String email, String password, String storeid, String supportid) {

        final ProgressDialog progressDialog = new ProgressDialog(TicketDetailsActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<TicketDetialsResponse> call = apiInterface.getTicketDetails(email, password, storeid, supportid);
        call.enqueue(new Callback<TicketDetialsResponse>() {
            @Override
            public void onResponse(Call<TicketDetialsResponse> call, Response<TicketDetialsResponse> response) {

                if (response.isSuccessful()) {

                    TicketDetialsResponse resp1 = response.body();
                    List<ReturnedItem> returnedItems = resp1.getReturnedItems();
                    Store store = resp1.getStore();
                    Support support = resp1.getSupport();
                    List<User> user = resp1.getUsers();
                    List<Comments> comments = resp1.getmComments();

                    if (store != null) {

                        store1 = new Store();


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

                        support1 = new Support();

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
                    if (returnedItems != null) {

                        for (int i = 0; i < returnedItems.size(); i++) {

                            ReturnedItem item = new ReturnedItem();

                            String created_at = returnedItems.get(i).getCreatedAt();
                            Long id = returnedItems.get(i).getId();
                            String product_name = returnedItems.get(i).getProductName();
                            Long quantity = returnedItems.get(i).getQuantity();
                            String reason = returnedItems.get(i).getReason();
                            String rma_number = returnedItems.get(i).getRmaNumber();
                            String sale_number = returnedItems.get(i).getSaleNumber();
                            String sku_number = returnedItems.get(i).getSkuNumber();
                            String status = returnedItems.get(i).getStatus();
                            String ticket_number = returnedItems.get(i).getTicketNumber();
                            String updated_at = returnedItems.get(i).getUpdatedAt();

                            item.setCreatedAt(created_at);
                            item.setId(id);
                            item.setProductName(product_name);
                            item.setQuantity(quantity);
                            item.setRmaNumber(rma_number);
                            item.setReason(reason);
                            item.setSaleNumber(sale_number);
                            item.setSkuNumber(sku_number);
                            item.setStatus(status);
                            item.setTicketNumber(ticket_number);
                            item.setUpdatedAt(updated_at);

                            ReturnedList.add(item);
                        }

                        if (user != null) {
                            for (int i = 0; i < user.size(); i++) {

                                User user1 = new User();

                                String created_at = user.get(i).getCreatedAt();
                                String email = user.get(i).getEmail();
                                Long id = user.get(i).getId();
                                String name = user.get(i).getName();
                                String phone_number = user.get(i).getPhoneNumber();
                                String role = user.get(i).getRole();
                                String updated_at = user.get(i).getUpdatedAt();

                                user1.setCreatedAt(created_at);
                                user1.setEmail(email);
                                user1.setId(id);
                                user1.setName(name);
                                user1.setPhoneNumber(phone_number);
                                user1.setRole(role);
                                user1.setUpdatedAt(updated_at);

                                User.add(user1);
                            }
                        }

                        if (comments != null) {

                            for (int i = 0; i < comments.size(); i++) {

                                Comments comments1 = new Comments();

                                Long id = comments.get(i).getId();
                                Long support_id = comments.get(i).getSupportId();
                                String comment = comments.get(i).getComment();
                                String created_at = comments.get(i).getCreatedAt();
                                Long created_by = comments.get(i).getCreatedBy();
                                String updated_at = comments.get(i).getUpdatedAt();
                                Long updated_by = comments.get(i).getUpdatedBy();

                                comments1.setId(id);
                                comments1.setSupportId(support_id);
                                comments1.setComment(comment);
                                comments1.setCreatedAt(created_at);
                                comments1.setCreatedBy(created_by);
                                comments1.setUpdatedAt(updated_at);
                                comments1.setUpdatedBy(updated_by);

                                CommentList.add(comments1);

                                Collections.sort(CommentList, new Comparator<Comments>(){
                                    public int compare(Comments obj1, Comments obj2) {
                                        // ## Ascending order
//                                        return obj2.getCreatedAt().compareToIgnoreCase(obj1.getCreatedAt()); // To compare string values
                                        if (obj1.getCreatedAt() == null || obj2.getCreatedAt() == null)
                                            return 0;
                                        return obj2.getCreatedAt().compareTo(obj1.getCreatedAt());

                                    }
                                });
                            }

                        }
                    }


                    tabLayout.setupWithViewPager(viewPager);

                    ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(TicketDetailsActivity.this.getSupportFragmentManager());


                    Bundle bundle1 = new Bundle();

                    Fragment_ticket_details pickf = new Fragment_ticket_details();
                    bundle1.putSerializable("support", support1);
                    bundle1.putSerializable("store", store1);
                    bundle1.putParcelableArrayList("User", User);
                    bundle1.putParcelableArrayList("Comments", CommentList);
                    pickf.setArguments(bundle1);
                    viewPagerAdapter.addFrag(pickf, "Ticket Details ");

                    if (returnedItems.size() > 0) {

                        Fragment_ticket_items loc = new Fragment_ticket_items();
                        bundle1.putParcelableArrayList("ReturnedList", ReturnedList);
                        loc.setArguments(bundle1);
                        viewPagerAdapter.addFrag(loc, "Returned Items");

                    }

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
                             enableDisableSwipeRefresh( state == ViewPager.SCROLL_STATE_IDLE );                                }
                     });

//                        padapter.updateAdapter(UserList);

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 401) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(TicketDetailsActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(TicketDetailsActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(TicketDetailsActivity.this, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(TicketDetailsActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<TicketDetialsResponse> call, Throwable t) {

                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(TicketDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

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
                Toast.makeText(TicketDetailsActivity.this, "SearchOnQueryTextSubmit: " + query, Toast.LENGTH_SHORT).show();
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }

                final ProgressDialog progressDialog = new ProgressDialog(TicketDetailsActivity.this,
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
                                Toast.makeText(TicketDetailsActivity.this, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                textView.setVisibility(View.GONE);
//
                                Intent intent = new Intent(TicketDetailsActivity.this, TicketSearchActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("SearchedTicketList", SearchList);
                                intent.putExtra("query", query);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            } else {
                                String d = response.body().getMessage();
                                Toast.makeText(TicketDetailsActivity.this, d, Toast.LENGTH_LONG).show();
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

                            Toast.makeText(TicketDetailsActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());


                        } else if (response.code() == 404) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketDetailsActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        } else if (response.code() == 500) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketDetailsActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        } else {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(TicketDetailsActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TicketResponse> call, Throwable t) {
                        call.cancel();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Log.e("response-failure", t.toString());
                        Toast.makeText(TicketDetailsActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
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


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();

            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent( event );
    }

    @Override
    public void onBackPressed() {
//       moveTaskToBack(true);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        super.onBackPressed();
    }

    public void refresh_from_Fragment() {

        finish();
        startActivity(getIntent());
    }
}
