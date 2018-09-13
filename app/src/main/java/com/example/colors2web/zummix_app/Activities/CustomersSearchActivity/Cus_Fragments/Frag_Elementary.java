package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Customer_Adapter.CustomerItemsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.customers.CustomerItem;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag_Elementary extends Fragment {
    RecyclerView mrecyclerView;
    CustomerItemsAdapter iadapter;
    List<CustomerItem> ItmList = new ArrayList<>();
    String cus_id;
    String pbalance,pick,request ,replenish,orderedq;


    public Frag_Elementary() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_inactive, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        iadapter = new CustomerItemsAdapter(ItmList);

        mrecyclerView = getActivity().findViewById(R.id.recycleview_inactive);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
//        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);

        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL, 16));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setAdapter(iadapter);


        if (getArguments() != null) {
            Bundle bundle = this.getArguments();
            cus_id = bundle.getString("ciid");
            Log.d("cus_id",cus_id);
        }else{
            Log.d("cus_id","null babey");
        }

        loadAdapter(cus_id);
    }

    private void loadAdapter(String cus_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(getActivity(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<CustomerResponse> call = apiInterface.getCustomerItems(email, password, cus_id);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {

                if (response.isSuccessful()) {
                    CustomerResponse resp1 = response.body();

                    List<CustomerItem> cus = resp1.getCustomerItems();

                    Toast.makeText(getActivity(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {

                            CustomerItem cus1 = new CustomerItem();

                            String p_id = String.valueOf(cus.get(i).getCustomerId());


                            String id = String.valueOf(cus.get(i).getId());
                            String sku = cus.get(i).getItemSkuNumber();
                            String name = cus.get(i).getItemName();
                            String price = cus.get(i).getPrice();
                            Long quan = cus.get(i).getTrQuantity();
                            String status = String.valueOf(cus.get(i).getItemStatus());
//                            Long pick = cus.get(i).getPick();
//                            Long pick_balance = cus.get(i).getPickBalance();
//                            Long rep = cus.get(i).getReplenish();
//                            Long o_qty = cus.get(i).getOrderedQuantity();
//                            Long commit =cus.get(i).getRequestedQuantity();



//                            customer_id.setText("Parent Id:" + p_id);
//                            toolbar.setTitle("Parent Id :" + p_id);

                            if (cus.get(i).getPickBalance() != null) {
                                pbalance = String.valueOf(cus.get(i).getPickBalance());
                            } else {
                                pbalance = "0";

                            }

                            if (cus.get(i).getPick() != null) {
                                pick = String.valueOf(cus.get(i).getPick());
                            } else {
                                pick = "0";

                            }

                            if (cus.get(i).getRequestedQuantity() != null) {
                                request = String.valueOf(cus.get(i).getRequestedQuantity());
                            } else {
                                request = "0";

                            }

                            if (cus.get(i).getReplenish() != null) {

                                replenish = String.valueOf(cus.get(i).getReplenish());
                                cus1.setReplenish(Long.valueOf(replenish));
                            } else {
                                replenish = "0";
                                cus1.setReplenish(Long.valueOf("0"));

                            }

                            if (cus.get(i).getOrderedQuantity() != null) {
                                orderedq = String.valueOf(cus.get(i).getOrderedQuantity());
                            } else {
                                orderedq = "0";

                            }

                            cus1.setId(Long.valueOf(id));
                            cus1.setItemSkuNumber(sku);
                            cus1.setItemName(name);
                            cus1.setPrice(price);
                            cus1.setTrQuantity(Long.valueOf(quan));
                            cus1.setReplenish(Long.valueOf(replenish));
                            cus1.setPick(Long.parseLong(pick));
                            cus1.setPickBalance(Long.valueOf(pbalance));
                            cus1.setItemStatus(status);
                            cus1.setOrderedQuantity(Long.valueOf(orderedq));
                            cus1.setRequestedQuantity(Long.valueOf(request));

                            ItmList.add(cus1); // must be the object of empty list initiated

                        }

                        iadapter.updateAnswers(ItmList);//adapter's content is updated and update function is called;
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        //send parameters according to urs adapter view setup.);

                    } else {
                        String d = response.body().getMessage();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getActivity(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getActivity(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getActivity(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }
                else {
                    Toast.makeText(getActivity(), "Operation Failed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getActivity(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
