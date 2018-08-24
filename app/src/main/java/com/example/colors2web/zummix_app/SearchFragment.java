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
import android.support.v7.app.AppCompatActivity;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.BarcodeActivity.BarcodeMain;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.ByActiveCustomers;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.ByCustomerId;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.ByCustomerItems;
import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.ByParentId;
import com.example.colors2web.zummix_app.Activities.ItemSearchActivity;
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MasterBoxSearchActivity;
import com.example.colors2web.zummix_app.Activities.OrderGroupByCustomerActivity;
import com.example.colors2web.zummix_app.Activities.OrderSearch2Activity;
import com.example.colors2web.zummix_app.Activities.PostActivity.Item_DR_HistoryActivity;
import com.example.colors2web.zummix_app.Activities.PostActivity.Item_Sales_HistoryActivity;
import com.example.colors2web.zummix_app.Activities.PostActivity.Pick_Velocity_ReportActivity;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.OrderShippingAddress;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderDetails;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderSearchResponse;
import com.example.colors2web.zummix_app.POJO.OrderTrack.OrderDetail;
import com.example.colors2web.zummix_app.POJO.OrderTrack.OrdertrackResponse;
import com.example.colors2web.zummix_app.POJO.ProductSearch.ProSearchRes;
import com.example.colors2web.zummix_app.POJO.customers.CustomerItem;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.POJO.customers.Customers;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


@TargetApi(Build.VERSION_CODES.LOLLIPOP)
public class SearchFragment extends Fragment {
    String spine;
    APIInterface apiInterface;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View rootView = inflater.inflate(R.layout.activity_search_fragment, container, false);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);

//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setTitle("Search");


        Spinner spinner = getActivity().findViewById(R.id.mainspinnerz);
        spinner.setVisibility(View.VISIBLE);
        spinner.setPopupBackgroundResource(R.color.colorPrimary);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spine = parent.getItemAtPosition(position).toString();

                if (spine != null) {
                    Log.d("Spine", spine);
                    switch (spine) {

                        case "Active_Cus":
                            Intent active = new Intent(getActivity(), ByActiveCustomers.class);
                            startActivity(active);
                            break;

                        case "Dr_Shipment":
                            Intent intentz = new Intent(getContext(), Item_DR_HistoryActivity.class);
                            startActivity(intentz);
                            break;

                        case "Sales_History":
                            Intent intenty = new Intent(getContext(), Item_Sales_HistoryActivity.class);
                            startActivity(intenty);
                            break;

                        case "Velocity Report":
                            Intent intentt = new Intent(getContext(), Pick_Velocity_ReportActivity.class);
                            startActivity(intentt);
                            break;

                        case "Home":
                            Intent intenth = new Intent(getContext(), OrderGroupByCustomerActivity.class);
                            startActivity(intenth);
                            break;

                        default:
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

        TextView textView = getActivity().findViewById(R.id.maintextview);
        textView.setVisibility(View.VISIBLE);

        final EditText editText = getActivity().findViewById(R.id.main_search_text);
        editText.setVisibility(View.VISIBLE);
        editText.setHint("Search....");
        editText.setFocusableInTouchMode(true);

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


//        editText.setOnTouchListener(new DrawableClickListener.RightDrawableClickListener(editText) {
//
//            @Override
//            public boolean onDrawableClick() {
//                editText.getText().clear();
//                editText.setShowSoftInputOnFocus(false);
//                editText.onEditorAction(EditorInfo.IME_ACTION_DONE);
//                editText.setCursorVisible(false);
//
//
//
////                editText.setShowSoftInputOnFocus(false);
////
////                View view = getActivity().getCurrentFocus();
////                if (view != null) {
////                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
////                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
////                }
//
////                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
////                imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(),InputMethodManager.RESULT_UNCHANGED_HIDDEN);
//                return true;
//            }
//
//        });
//


//        toolbar.inflateMenu(R.menu.search_menu);
//        toolbar.setOnMenuItemClickListener(this);

        return rootView;
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

                    case "Item":
                        call_item(email, password, OPath);
                        break;

                    case "Tracking Number":
                        call_tracking_number(email, password, OPath);
                        break;

                    case "Boxes":
                        call_box(email, password, OPath);
                        break;

                    case "Parent_Id":
                        call_parent(email, password, OPath);
                        break;

                    case "Cus_Id":
                        call_customer_id(email, password, OPath);
                        break;

                    case "Cus_Items":
                        call_customer_items(email, password, OPath);
                        break;


                }
            }
        }
    }

    private void call_customer_items(String email, String password, final String oPath) {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<CustomerResponse> call = apiInterface.getCustomerItems(email, password, oPath);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {

                if (response.isSuccessful()) {
                    CustomerResponse resp1 = response.body();

                    List<CustomerItem> cus = resp1.getCustomerItems();

                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        Intent intent7 = new Intent(getContext(), ByCustomerItems.class);
                        intent7.putExtra("ciid", oPath);

                        startActivity(intent7);
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
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void call_customer_id(String email, String password, final String oPath) {
        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<CustomerResponse> call = apiInterface.getCustomer(email, password, oPath);
        call.enqueue(new Callback<CustomerResponse>() {

            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {

                if (response.isSuccessful()) {
                    CustomerResponse resp1 = response.body();

                    Customers customers = resp1.getM1Customer();

                    if (customers != null) {

                        Intent intent6 = new Intent(getContext(), ByCustomerId.class);
                        intent6.putExtra("cid", oPath);
                        startActivity(intent6);
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getContext(), resp1.getMessage(), Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void call_parent(String email, String password, final String oPath) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<CustomerResponse> call = apiInterface.getParentCustomer(email, password, oPath);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {

                if (response.isSuccessful()) {
                    CustomerResponse resp1 = response.body();

                    List<Customers> cus = resp1.getmCustomer();

                    if (cus != null) {

                        Intent intent5 = new Intent(getContext(), ByParentId.class);
                        intent5.putExtra("pid", oPath);
                        startActivity(intent5);
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
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
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

                    if (Ordlist!= null) {

                        Intent intent3 = new Intent(getContext(), MasterBoxSearchActivity.class);
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

                        Intent intent2 = new Intent(getContext(), BarcodeMain.class);
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

                        Intent intent1 = new Intent(getContext(), ItemSearchActivity.class);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//               Toolbar toolbar = getActivity().findViewById(R.id.toolbar_baccho);
//        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//        toolbar.setTitle("search");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    //    @Override
//    public boolean onMenuItemClick(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_search:
//                //do sth here
//                return true;
//
//            case R.id.mspinner:
//            //do sth here
//            return true;
//
//            case R.id.image:
//                //do sth here
//                return true;
//        }
//        return false;
//    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.search_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);


        ImageView img = (ImageView) menu.findItem(R.id.image).getActionView();
        img.setImageResource(R.drawable.icon_barcode1);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Barcode Clicked", Toast.LENGTH_SHORT).show();

                Intent barcode = new Intent(getActivity(), BarcodeMain.class);
                barcode.putExtra("categories", spine);
                startActivity(barcode);
//                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//                getActivity().overridePendingTransition(getResources().getAnimation(R.anim.pus), R.anim.push_left_out);

            }
        });
    }
}


//////        spinner.setDropDownWidth(180);
////        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
////                R.array.spinner_array, android.R.layout.simple_spinner_item);
////        adapter.setDropDownViewResource(R.layout.spinner_item);
////        spinner.setAdapter(adapter);
////        spinner.setPopupBackgroundResource(R.color.base);

//
////        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
////            @Override
////            public boolean onQueryTextSubmit(String query) {
////
////                }
////                return false;
////            }
////
////            @Override
////            public boolean onQueryTextChange(String newText) {
////                return false;
////            }
////
////        });
