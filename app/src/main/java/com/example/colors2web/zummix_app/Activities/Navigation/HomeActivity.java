package com.example.colors2web.zummix_app.Activities.Navigation;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.colors2web.zummix_app.Activities.CityBins.BinHomeActivity;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.ByCustomerItems;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.ByParentId;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.ItemsElementory;
import com.example.colors2web.zummix_app.Activities.InActiveActivity;
import com.example.colors2web.zummix_app.Activities.LoginActivity;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.Dr_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.Home_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.ActiveFragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.InActive_fragment.Inactive_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.Package_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.Pick_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.Problem_SKU_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.Sales_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.SpecialPrograms.SpecialProgram_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.UOMFragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.User_fragment.User_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.city_bins_fragments.City_bins_fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.main_dashboard_fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.fragment_customers_list.Customer_list_fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.items_inventory.Items_Inventory_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments.ReturnTicket_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments.Ticket_Fragment;
import com.example.colors2web.zummix_app.Activities.ProblemSKU_Activity.PackageActivity;
import com.example.colors2web.zummix_app.Activities.ProblemSKU_Activity.ProblemSKU;
import com.example.colors2web.zummix_app.Activities.TicketActivity.TicketActivity;
import com.example.colors2web.zummix_app.Activities.TicketActivity.TicketNavigActivity;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.UOM;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;

public class HomeActivity extends AppCompatActivity {

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

    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";

    // index to identify current nav menu item
    public static int navItemIndex = 0;
    public static int tryIndex = 0;

    // tags used to attach the fragments
    private static final String TAG_HOME = "Dashboard";
    private static final String TAG_GROUP = "Customer Group";
    private static final String TAG_DR = "Item DrHistory";
    private static final String TAG_Sales = "Item Sales report";
    private static final String TAG_PICK = "Pick velocity Report";
    private static final String TAG_UOM = "Unit Of Measurement";
    private static final String TAG_USER = "Users";
    private static final String TAG_SPECIAL = "Special Programs";
    private static final String TAG_INACTIVE = "Inactive Customers";
    private static final String TAG_CITYBINS = "City Bins";
    private static final String TAG_CUSTOMERLIST = "Customer List";
    private static final String TAG_CUSTOMERITEMS = "Items Inventory";
    private static final String TAG_PACKAGES = "Packages";
    private static final String TAG_PROBLEM ="Problem SKU";
    private static final String TAG_TICKETS ="Tickets";
    private static final String TAG_RETURNTICKETS ="ReturnTickets";

    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navbar_main);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        mHandler = new Handler();
        fab = findViewById(R.id.fab);

        navHeader = navigationView.getHeaderView(0);
//        For Text color change
//       MenuItem menuitem = navigationView.getMenu().findItem(R.id.navig_group_logout);
//        Spannable spannable = new SpannableString(menuitem.getTitle());
//        spannable.setSpan(new BackgroundColorSpan(getResources().getColor(R.color.colorPrimaryDark)), 0, menuitem.getTitle().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        menuitem.setTitle(spannable);

        post = navHeader.findViewById(R.id.nav_post);
        imgProfile = navHeader.findViewById(R.id.img_profile);
        nav_email = navHeader.findViewById(R.id.nav_email_name);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
                startActivity(new Intent(HomeActivity.this, TicketNavigActivity.class));
                isDestroyed();
            }
        });

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // load nav menu header data
        loadNavHeader();



        // initializing navigation menu
        setUpNavigationView();

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
//        } else {
//            navItemIndex = 4;
//            CURRENT_TAG = TAG_UOM;
//            loadHomeFragment();
//        }
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


                    case R.id.nav_dashboard:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;

                    case R.id.nav_customers_group:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_GROUP;
                        break;

                    case R.id.nav_inactive_items:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_INACTIVE;
                        break;

                    case R.id.nav_pick_velocity:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_DR;
                        break;


                    case R.id.nav_dr_shipment:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_Sales;
                        break;

                    case R.id.nav_sales_report:
                        navItemIndex = 5;
                        CURRENT_TAG = TAG_PICK;
                        break;

                    case R.id.nav_special_programs:
                        navItemIndex =6;
                        CURRENT_TAG = TAG_SPECIAL;
                        break;

                    case R.id.nav_users:
                        navItemIndex =7;
                        CURRENT_TAG = TAG_USER;
                        break;

                    case R.id.nav_uom:
                        navItemIndex =8;
                        CURRENT_TAG = TAG_UOM;
                        break;

                    case R.id.nav_parent_id:
                        navItemIndex =9;
                        CURRENT_TAG = TAG_CUSTOMERLIST;
                        break;

                    case R.id.nav_customer_items:
                        navItemIndex = 10;
                        CURRENT_TAG = TAG_CUSTOMERITEMS;
                        break;

                    case R.id.nav_city_bins:
                        navItemIndex = 11;
                        CURRENT_TAG = TAG_CITYBINS;
                        break;

                    case R.id.nav_packages:
                        navItemIndex = 12;
                        CURRENT_TAG = TAG_PACKAGES;
                        break;

                    case R.id.nav_problem_sku:
                        navItemIndex = 13;
                        CURRENT_TAG = TAG_PROBLEM;
                        break;

//                    case R.id.nav_tickets:
//                        navItemIndex = 14;
//                        CURRENT_TAG = TAG_TICKETS;
//                        break;

//                    case R.id.nav_return_tickets:
//                        navItemIndex = 15;
//                        CURRENT_TAG = TAG_RETURNTICKETS;
//                        break;


//                    case R.id.nav_city_bins:
//                        startActivity(new Intent(HomeActivity.this, BinHomeActivity.class));
//                        drawerLayout.closeDrawers();
//                        break;



                    case R.id.nav_tickets:
                        startActivity(new Intent(HomeActivity.this, TicketNavigActivity.class));
                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                        drawerLayout.closeDrawers();
                        break;

//                    case R.id.nav_customer_items:
//                        startActivity(new Intent(HomeActivity.this, ItemsElementory.class));
//                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                        drawerLayout.closeDrawers();
//                        break;


//                    case R.id.nav_packages:
//                        startActivity(new Intent(HomeActivity.this, PackageActivity.class));
//                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                        drawerLayout.closeDrawers();
//                        break;

//                    case R.id.nav_problem_sku:
//                        startActivity(new Intent(HomeActivity.this, ProblemSKU.class));
//                        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                        drawerLayout.closeDrawers();
//                        break;

                    case R.id.nav_logout:
                        startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                        drawerLayout.closeDrawers();
                        SharedPreferences preferences =PreferenceManager.getDefaultSharedPreferences(HomeActivity.this);
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
                // home// Customers Groups
                main_dashboard_fragment d_fragment = new main_dashboard_fragment();
                return d_fragment;

            case 1:
                // Customers Group
                Home_Fragment hfragment = new Home_Fragment();
                return  hfragment;

            case 2:
                // unit of measurement
                Inactive_Fragment ifragment = new Inactive_Fragment();
                return  ifragment;

            case 3:
                // pick fragment
                Pick_Fragment pick_fragment = new Pick_Fragment();
                return pick_fragment;

            case 4:
//                De Shipment fragment
                Dr_Fragment dr_fragment = new Dr_Fragment();
                return dr_fragment;

            case 5:
                // sales fragment
                Sales_Fragment sales_fragment = new Sales_Fragment();
                return sales_fragment;

            case 6:
                // special program
                SpecialProgram_Fragment spFragment = new SpecialProgram_Fragment();
                return spFragment;

            case 7:
                // warehouse users
                User_Fragment uFragment = new User_Fragment();
                return uFragment;

            case 8:
                // unit of measurement
                UOMFragment uomFragment = new UOMFragment();
                return  uomFragment;

            case 9:
                // unit of measurement
                Customer_list_fragment clfragment = new Customer_list_fragment();
                return  clfragment;

            case 10:
                // item inventory //
                Items_Inventory_Fragment ivfragment = new Items_Inventory_Fragment();
                return  ivfragment;

            case 11:
                // unit of measurement
                 City_bins_fragment cfragment = new City_bins_fragment();
                return  cfragment;

            case 12:
                // unit of measurement
                Package_Fragment packfragment = new Package_Fragment();
                return  packfragment;

            case 13:
                // unit of measurement
                Problem_SKU_Fragment probfragment = new Problem_SKU_Fragment();
                return  probfragment;

//            case 14:
//                // unit of measurement
//                Ticket_Fragment ticketfragment = new Ticket_Fragment();
//                return  ticketfragment;

//            case 15:
//                // unit of measurement
//                ReturnTicket_Fragment rticketfragment = new ReturnTicket_Fragment();
//                return  rticketfragment;



            default:
                return new ActiveFragment();
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
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu2, menu);
        ImageView img, try2;

//        MenuItem item = menu.getItem(0);
//        final MenuItem item1 = menu.getItem(1);
//
//        item.setVisible(true);
//        item1.setVisible(false);

        img = (ImageView) menu.findItem(R.id.image).getActionView();
//        try2 = (ImageView) menu.findItem(R.id.image_try).getActionView();
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
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
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

    // show or hide the fab
    private void toggleFab() {
//        if (navItemIndex == 0)
//            fab.show();
//        else
//            fab.hide();
        fab.show();
    }
}
