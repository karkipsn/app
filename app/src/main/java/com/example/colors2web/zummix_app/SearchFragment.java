package com.example.colors2web.zummix_app;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.BarcodeActivity.BarcodeMain;
import com.example.colors2web.zummix_app.Activities.BarcodeActivity.ScannerActivity;
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MasterBoxSearch2Activity;
import com.example.colors2web.zummix_app.Activities.Navigation.HomeActivity;
import com.example.colors2web.zummix_app.Activities.OrderActivity.TrackOrderSearchActivity;
import com.example.colors2web.zummix_app.Activities.ProductDetails.ProductSearchActivity;
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MasterBoxSearchActivity;
import com.example.colors2web.zummix_app.Activities.OrderActivity.BatchSearchActivity;
import com.example.colors2web.zummix_app.Activities.OrderSearch2Activity;
import com.example.colors2web.zummix_app.POJO.BatchNumber.BatchOrder;
import com.example.colors2web.zummix_app.POJO.BatchNumber.BatchResponse;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.OrderShippingAddress;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderDetails;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderSearchResponse;
import com.example.colors2web.zummix_app.POJO.OrderTrack.OrderDetail;
import com.example.colors2web.zummix_app.POJO.OrderTrack.OrdertrackResponse;
import com.example.colors2web.zummix_app.POJO.ProductSearch.ProSearchRes;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SearchFragment extends Fragment {
    String spine;
    APIInterface apiInterface;
    Spinner spinner;
    LinearLayout mlayout;
    TextView textView;
    EditText editText;
    ImageView bar;
    ImageView mb1, mb2;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;

    Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_search_fragment, container, false);
        mb1 = getActivity().findViewById(R.id.image);
        mb2 = getActivity().findViewById(R.id.image_try);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);

//         toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
////        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        drawerLayout =(DrawerLayout)getActivity().findViewById(R.id.drawer_layout);
//
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
////        toolbar.setTitle("Search");
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(getFragmentManager().getBackStackEntryCount() > 0) {
//
//                   getFragmentManager().popBackStack();
////                    getActivity().finish();
//                   ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//                            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//
//
//                    actionBarDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar,
//                            R.string.openDrawer, R.string.closeDrawer) {
//
//                        @Override
//                        public void onDrawerClosed(View drawerView) {
//                            // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
//                            super.onDrawerClosed(drawerView);
//                        }
//
//                        @Override
//                        public void onDrawerOpened(View drawerView) {
//                            // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
//                            super.onDrawerOpened(drawerView);
//                        }
//                    };
//                        //Setting the actionbarToggle to drawer layout
//                        drawerLayout.setDrawerListener(actionBarDrawerToggle);
//                        //calling sync state is necessary or else your hamburger icon wont show up
//                        actionBarDrawerToggle.syncState();
//                }
//            }
//        });


        spinner = getActivity().findViewById(R.id.fmainspinnerz);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.spinner_array,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter1);

        mlayout = getActivity().findViewById(R.id.veiw_main);
//        spinner.setPopupBackgroundResource(R.color.colorPrimary);


//        mb1.setVisibility(View.GONE);
//        mb2.setVisibility(View.VISIBLE);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spine = parent.getItemAtPosition(position).toString();

                if (spine != null) {
                    Log.d("Spine", spine);
                    switch (spine) {

                        case "Batch_No":
                            bar.setVisibility(View.GONE);
                            break;

                        default:
                            bar.setVisibility(View.VISIBLE);
                            break;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                spine = parent.getItemAtPosition(0).toString();
                if (spine != null) {
                    Log.d("Spine", spine);
                }
            }
        });

        textView = getActivity().findViewById(R.id.fmaintextview);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
                getActivity().getSupportFragmentManager().beginTransaction().remove(new SearchFragment()).commit();
            }
        });

        editText = getActivity().findViewById(R.id.fmain_search_text);
        bar = getActivity().findViewById(R.id.fmain_barcode);
        editText.setVisibility(View.VISIBLE);
        editText.setHint("Search....");
        editText.setFocusableInTouchMode(true);


        editText.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == KeyEvent.KEYCODE_BACK) {
                    editText.clearFocus();
                    return true;
                } else return false;
            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    String query = editText.getText().toString();
                    performSearch(query);

                    return true;
                }
                return false;
            }
        });


        bar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Barcode Clicked", Toast.LENGTH_SHORT).show();

//                Intent barcode = new Intent(getActivity(), BarcodeMain.class);
//                barcode.putExtra("categories", spine);
//                startActivity(barcode);
//              getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);

                IntentIntegrator.forSupportFragment(SearchFragment.this).setCaptureActivity(ScannerActivity.class).initiateScan();

            }
        });


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() == null) {
                Toast.makeText(getContext(), "Scan Cancelled", Toast.LENGTH_LONG).show();

            } else {
                //show dialogue with result// This is used to copy the contents from the clip
//                showResultDialogue(result.getContents());
                String barstring = result.getContents();
                Toast.makeText(getContext(), "Scanned result is :" + result.getContents(), Toast.LENGTH_LONG).show();

                if (spine != null) {

                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                    final String email = preferences.getString("email", "");
                    final String password = preferences.getString("password", "");

                    switch (spine) {

                        case "Order":
                            call_order2(email, password, barstring);
                            break;

                        case "Product":
                            call_item(email, password, barstring);
                            break;

                        case "Tracking Number":
                            call_tracking_number(email, password, barstring);
                            break;

                        case "Box/Master Box":
                            call_box(email, password, barstring);
                            break;
                    }
                } else {
                    Toast.makeText(getContext(), "Error in Process", Toast.LENGTH_SHORT).show();
                }
//                }
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    //    Do this same with some button
//    Try with floating action button

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {


        inflater.inflate(R.menu.search_menu2, menu);
        super.onCreateOptionsMenu(menu, inflater);

        MenuItem item = menu.getItem(0);
        MenuItem item1 = menu.getItem(1);

        item.setVisible(false);
        item1.setVisible(true);

        ImageView img, try2;


        try2 = (ImageView) menu.findItem(R.id.image_try).getActionView();

        try2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getFragmentManager().getBackStackEntryCount() > 0) {

                    getFragmentManager().popBackStack();

                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.image:
                // Not implemented here
                return false;
            case R.id.image_try:
                // Do Fragment menu item stuff here
                return true;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onResume() {

        super.onResume();
        new SearchFragment();
        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK) {

                    if (getFragmentManager().getBackStackEntryCount() > 0) {

                        getFragmentManager().popBackStack();
                        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

//                        getActivity().getFragmentManager().popBackStack();
//                        yo garda chai last ma iflate bhako item dekaune raixa
                    }

                    return true;
                }
                return false;
            }
        });
    }


    private void performSearch(String query) {
        Toast.makeText(getActivity(), "You opt for query search", Toast.LENGTH_SHORT).show();
        if (query != null) {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
            final String email = preferences.getString("email", "");
            final String password = preferences.getString("password", "");

            String OPath = query;
            Log.d("OPath", OPath);

            if (spine != null) {

                switch (spine) {

                    case "Order":
                        call_order2(email, password, OPath);
                        break;

                    case "Product":
                        call_item(email, password, OPath);
                        break;

                    case "Tracking Number":
                        call_tracking_number(email, password, OPath);
                        break;

                    case "Box/Master Box":
                        call_box(email, password, OPath);
                        break;

                    case "Batch Status":
                        call_batch(email, password, OPath);
                        break;

                }
            }
        }
    }

    private void call_batch(String email, String password, final String oPath) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<BatchResponse> call = apiInterface.getBatchResponse(email, password, oPath);
        call.enqueue(new Callback<BatchResponse>() {
            @Override
            public void onResponse(Call<BatchResponse> call, Response<BatchResponse> response) {

                if (response.isSuccessful()) {
                    BatchResponse resp1 = response.body();

                    List<BatchOrder> cus = resp1.getBatchOrders();

                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        Intent intentb = new Intent(getContext(), BatchSearchActivity.class);
                        intentb.putExtra("biid", oPath);

                        startActivity(intentb);
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getActivity(), d, Toast.LENGTH_LONG).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<BatchResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void call_box(String email, String password, final String oPath) {
//        TODO: BoxOrderSearch Implementation is left
//        Toast.makeText(getContext(), "Box not found", Toast.LENGTH_LONG).show();


        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<MasterBoxResponse> call = apiInterface.getMasterBoxes(email, password, oPath);
        call.enqueue(new Callback<MasterBoxResponse>() {
            @Override
            public void onResponse(Call<MasterBoxResponse> call, Response<MasterBoxResponse> response) {

                if (response.isSuccessful()) {
                    MasterBoxResponse resp1 = response.body();

                    String message = resp1.getMessage();
                    OrderShippingAddress Ordlist = resp1.getOrderShippingAddress();

                    if (Ordlist != null) {

                        Intent intent3 = new Intent(getContext(), MasterBoxSearch2Activity.class);
                        intent3.putExtra("OPath", oPath);
                        startActivity(intent3);
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();

                        Toast.makeText(getActivity(), d, Toast.LENGTH_LONG).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MasterBoxResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void call_tracking_number(String email, String password, String oPath) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        final String Path = oPath;

        Call<OrdertrackResponse> call = apiInterface.getOrderByTrack(email, password, oPath);

        call.enqueue(new Callback<OrdertrackResponse>() {
            @Override
            public void onResponse(Call<OrdertrackResponse> call, Response<OrdertrackResponse> response) {

                if (response.isSuccessful()) {

                    OrdertrackResponse resp1 = response.body();
                    List<OrderDetail> List = resp1.getOrderDetails();

                    if (List != null) {

                        Intent intent2 = new Intent(getContext(), TrackOrderSearchActivity.class);
                        intent2.putExtra("OPath", Path);
                        startActivity(intent2);

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    } else {
                        Toast.makeText(getContext(), resp1.getMessage(), Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
//                        head.setText(d);
                    }

                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());

                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrdertrackResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void call_item(String email, String password, String oPath) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        final String Path = oPath;

        Call<ProSearchRes> call = apiInterface.getProduct1(email, password, Path);
        call.enqueue(new Callback<ProSearchRes>() {
            @Override
            public void onResponse(Call<ProSearchRes> call, Response<ProSearchRes> response) {

                if (response.isSuccessful()) {

                    ProSearchRes resp = response.body();

                    List<com.example.colors2web.zummix_app.POJO.ProductSearch.CustomerItem> items = resp.getCustomerItems();

                    if (items != null) {

                        Intent intent1 = new Intent(getContext(), ProductSearchActivity.class);
                        intent1.putExtra("OPath", Path);
                        startActivity(intent1);

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    } else {
                        Toast.makeText(getContext(), resp.getMessage(), Toast.LENGTH_LONG).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                } else if (response.code() == 401) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());

                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ProSearchRes> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void call_order2(String email, String password, String oPath) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        final String Path = oPath;


        Call<OrderSearchResponse> call = apiInterface.getOrder1(email, password, Path);
        call.enqueue(new Callback<OrderSearchResponse>() {
            @Override
            public void onResponse(Call<OrderSearchResponse> call, Response<OrderSearchResponse> response) {

                if (response.isSuccessful()) {

                    OrderDetails order = response.body().getOrderDetails();

                    if (order != null) {

                        Intent intent = new Intent(getContext(), OrderSearch2Activity.class);
                        intent.putExtra("OPath", Path);
                        Log.d("path_barcode", Path);
                        startActivity(intent);

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    } else {
                        String d = response.body().getMessage();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getContext(), d, Toast.LENGTH_LONG).show();

                    }

                } else if (response.code() == 401) {

                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }


                } else if (response.code() == 404) {

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }


}


