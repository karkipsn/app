package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.Activities.PostActivity.View_Pick_VelocityActivity;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.POJO.PostSearch.Boxes;
import com.example.colors2web.zummix_app.POJO.PostSearch.CustomerItems;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostResponse;
import com.example.colors2web.zummix_app.POJO.PostSearch.PostServer;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Pick_Fragment extends Fragment {

    APIInterface apiInterface;
    Context mContext;

    Spinner mspinner;

    TextView msku,mform,mto;
    Button btn_submit;

    Integer mYear, mMonth, mDay;
    String mform1, mto1;
    ImageView img;
    Long cus_id;
    SwipeRefreshLayout mSwipeRefreshLayout;


    public Pick_Fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_nav_dr, container, false);
        return inf;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mspinner = getActivity().findViewById(R.id.dr_spinner);

        msku = getActivity().findViewById(R.id.item_dr_sku);
        msku.setVisibility(View.GONE);


        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);


        mform = getActivity().findViewById(R.id.item_dr_from);

        mto = getActivity().findViewById(R.id.item_dr_to);

        btn_submit = getActivity().findViewById(R.id.btn_item_dr_submit);


        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        R.style.AppTheme_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mform.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }

                }, mYear, mMonth, mDay);

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                mform1 = format.format(c.getTime());
                datePickerDialog.show();
                mto.requestFocus();
            }
        });

        mto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        R.style.AppTheme_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mto.setText(dayOfMonth + "-" + (month + 1) + "-" + year);
                    }

                }, mYear, mMonth, mDay);

                DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                mto1 = format.format(c.getTime());
                datePickerDialog.show();
                mto.clearFocus();
            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mform.setText(null);
                mto.setText(null);

                Toast.makeText(getContext(), "Refreshed", Toast.LENGTH_SHORT).show();
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });


        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String item_sku = msku.getText().toString();
                String from = mform.getText().toString();
                String to = mto.getText().toString();
                Long customer = cus_id;
                call_pick_velocity(email, password, item_sku, customer, from, to);
            }
        });

    }

    private void call_pick_velocity(String email, String password, String item_sku, Long customer, 
                                    String from, String to) {
        if (customer != null) {

            String cus_id = String.valueOf(customer);
            PostServer post = new PostServer(cus_id, from, to);

            final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

            Call<PostResponse> call = apiInterface.postPickVelocityReport(email, password, post);
            call.enqueue(new Callback<PostResponse>() {
                             @Override
                             public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {

                                 if (response.isSuccessful()) {
                                     PostResponse res = response.body();
                                     List<CustomerItems> shipments = res.getmCustomerItems();

                                     List<Boxes> BList = new ArrayList<>();
                                     List<CustomerItems> CItems = new ArrayList<>();

                                     if (shipments != null) {

                                         for (int i = 0; i < shipments.size(); i++) {

                                             CustomerItems items = new CustomerItems();

                                             String sku = shipments.get(i).getItemSkuNumber();
                                             String name = shipments.get(i).getItemName();
                                             String replenish = shipments.get(i).getReplenish();
                                             Long pick = shipments.get(i).getPick();
                                             String location1 = shipments.get(i).getWarehouseName();
                                             String location2 = shipments.get(i).getPickAisleName();
                                             String location3 = shipments.get(i).getSectionNumber();
                                             String location4 = shipments.get(i).getSectionLevel();
                                             String location5 = shipments.get(i).getPickBin();

                                             items.setItemSkuNumber(sku);
                                             items.setItemName(name);
                                             items.setReplenish(replenish);
                                             items.setPick(pick);
                                             items.setWarehouseName(location1);
                                             items.setPickAisleName(location2);
                                             items.setSectionNumber(location3);
                                             items.setSectionLevel(location4);
                                             items.setPickBin(location5);

                                             CItems.add(items);
                                         }
                                         List<Boxes> box = res.getmBoxes();
                                         if (box != null) {

                                             for (int i = 0; i < box.size(); i++) {

                                                 Boxes boxes = new Boxes();
                                                 String date = box.get(i).getCreatedAt();
                                                 String sku = box.get(i).getItemSku();
                                                 String total = box.get(i).getTotalPickedQty();
                                                 Long unique = box.get(i).getUniqueOrders();

                                                 boxes.setCreatedAt(date);
                                                 boxes.setItemSku(sku);
                                                 boxes.setTotalPickedQty(total);
                                                 boxes.setUniqueOrders(unique);

                                                 BList.add(boxes);
                                             }
                                         } else {

                                             if (progressDialog.isShowing()) {
                                                 progressDialog.dismiss();
                                                 Toast.makeText(getContext(), "NO boxes found", Toast.LENGTH_LONG).show();
                                             }
                                         }

                                         Intent intent = new Intent(getContext(), View_Pick_VelocityActivity.class);
                                         intent.putExtra("CList", (Serializable) CItems);
                                         intent.putExtra("BList", (Serializable) BList);
                                         if (progressDialog.isShowing()) {
                                             progressDialog.dismiss();
                                         }
                                         startActivity(intent);

                                     } else {
                                         Toast.makeText(getContext(), response.body().getmMessage(), Toast.LENGTH_LONG).show();
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
                             public void onFailure(Call<PostResponse> call, Throwable t) {

                                 call.cancel();
                                 Log.e("response-failure", t.toString());
                                 Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                                 if (progressDialog.isShowing()) {
                                     progressDialog.dismiss();
                                 }

                             }
                         }

            );

        }
    }

    private void spinner(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
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
                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

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
                        ArrayAdapter<SpinnerPojo>adapter =new ArrayAdapter<SpinnerPojo>(getContext(),
                                android.R.layout.simple_spinner_item, countryList);
                        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                        mspinner.setAdapter(adapter);
                        mspinner.setPrompt("Select Customers");


                        mspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                                cus_id = sp.getCus_id();

                                Toast.makeText(getContext(), "Cus ID: " + sp.getCus_id() + ",  " +
                                        " Name : " + sp.getName(), Toast.LENGTH_SHORT).show();
                                Log.d("cus_id", String.valueOf(cus_id));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(0);
                                cus_id = sp.getCus_id();
                            }
                        });

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getContext(), d, Toast.LENGTH_SHORT).show();
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
            public void onFailure(Call<OrdGrpByCus> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        });
    }

}
