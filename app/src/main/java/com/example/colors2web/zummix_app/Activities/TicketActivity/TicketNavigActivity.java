package com.example.colors2web.zummix_app.Activities.TicketActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.colors2web.zummix_app.Activities.LoginActivity;
import com.example.colors2web.zummix_app.Activities.Navigation.HomeActivity;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments.ReturnTicket_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments.Ticket_Fragment;
import com.example.colors2web.zummix_app.Adapter.Ticket_Adapter.TicketAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Tickets.TicketResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.Tickets;
import com.example.colors2web.zummix_app.R;
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

public class TicketNavigActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private View navHeader;
    private FloatingActionButton fab;
    private TextView nav_email, post;
    private ImageView imgProfile;
    RelativeLayout layout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    ArrayList<Tickets> SearchList = new ArrayList<>();
//    TextView textView;

    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";

    // index to identify current nav menu item
    public static int navItemIndex = 0;


    private static final String TAG_TICKETS = "Tickets";
    private static final String TAG_RETURNTICKETS = "ReturnTickets";
    private static final String TAG_VOX_DASHBOARD = "Vox_Dashboard";

    public static String CURRENT_TAG = TAG_TICKETS;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navbar_tickets);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mHandler = new Handler();
        fab = findViewById(R.id.fab);

        navHeader = navigationView.getHeaderView(0);

        post = navHeader.findViewById(R.id.nav_post);
        imgProfile = navHeader.findViewById(R.id.img_profile);
        nav_email = navHeader.findViewById(R.id.nav_email_name);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                startActivity(new Intent(TicketNavigActivity.this, HomeActivity.class));
                isDestroyed();
//                finish();
            }
        });


        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_ticket_titles);

        // load nav menu header data
        loadNavHeader();

        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_TICKETS;
            loadHomeFragment();
        }
    }

    private void loadNavHeader() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String email = preferences.getString("email", "");
        String postie = preferences.getString("group_type", "");

        if (postie != null) {
            post.setText(postie);
        }

        if (email != null) {
            nav_email.setText(email);
        }

        // Loading profile image
        Glide.with(this).load(R.mipmap.ic_launcher_voxship).apply(RequestOptions.circleCropTransform()).
                into(imgProfile);
//        Glide.with(this).load(R.drawable.logo).
//                apply(RequestOptions.bitmapTransform(new BrightnessFilterTransformation())).into(imgProfile);

    }

    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {


                    case R.id.nav_tickets:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_TICKETS;
                        break;

                    case R.id.nav_return_tickets:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_RETURNTICKETS;
                        break;


                    case R.id.nav_main_dashboard:
                        startActivity(new Intent(TicketNavigActivity.this, HomeActivity.class));
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        drawerLayout.closeDrawers();
                        finish();
                        break;


                    case R.id.nav_logout:
                        startActivity(new Intent(TicketNavigActivity.this, LoginActivity.class));
                        drawerLayout.closeDrawers();
                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TicketNavigActivity.this);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.clear().apply();
                        finish();
//                        Clearing the session data left !!! Call it to method and clear it
                        break;

                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                loadHomeFragment();

                return true;
            }
        });

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }

    private void loadHomeFragment() {

        // selecting appropriate nav menu item
        selectNavMenu();
        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawerLayout.closeDrawers();
            // show or hide the fab button
            toggleFab();
            return;
        }

        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        //Closing drawer on item click
        drawerLayout.closeDrawers();

        // show or hide the fab button
        toggleFab();

        // refresh toolbar menu
        invalidateOptionsMenu();

    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setToolbarTitle() {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }


    private Fragment getHomeFragment() {
        switch (navItemIndex) {


            case 0:
                // unit of measurement
                Ticket_Fragment ticketfragment = new Ticket_Fragment();
                return ticketfragment;

            case 1:
                // unit of measurement
                ReturnTicket_Fragment rticketfragment = new ReturnTicket_Fragment();
                return rticketfragment;


            default:
                return new Ticket_Fragment();
        }
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_TICKETS;
                loadHomeFragment();
                return;
            }
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
                Toast.makeText(TicketNavigActivity.this, "SearchOnQueryTextSubmit: " + query, Toast.LENGTH_SHORT).show();
                if (!searchView.isIconified()) {
                    searchView.setIconified(true);
                }

                final ProgressDialog progressDialog = new ProgressDialog(TicketNavigActivity.this,
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
                                Toast.makeText(TicketNavigActivity.this, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();
//                                textView.setVisibility(View.GONE);
//
                                Intent intent = new Intent(TicketNavigActivity.this, TicketSearchActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("SearchedTicketList", SearchList);
                                intent.putExtra("query", query);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            } else {
                                String d = response.body().getMessage();
                                Toast.makeText(TicketNavigActivity.this, d, Toast.LENGTH_LONG).show();
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

                            Toast.makeText(TicketNavigActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());


                        } else if (response.code() == 404) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketNavigActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        } else if (response.code() == 500) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketNavigActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        } else {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(TicketNavigActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TicketResponse> call, Throwable t) {
                        call.cancel();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Log.e("response-failure", t.toString());
                        Toast.makeText(TicketNavigActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
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

    // show or hide the fab
    private void toggleFab() {
//        if (navItemIndex == 0)
//            fab.show();
//        else
//            fab.hide();
        fab.show();
    }


}
