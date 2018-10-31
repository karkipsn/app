package com.example.colors2web.zummix_app.Fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Order_Adapters.InActiveAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.InactiveCustomers.InactiveResponse;
import com.example.colors2web.zummix_app.POJO.InactiveCustomers.StoreInactiveItem;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag_InActive extends Fragment {

    RecyclerView mrecyclerView;
    InActiveAdapter iadapter;
    String cus_id;
    String pbalance,pick,request ,replenish,orderedq;
    TextView textView;

    SwipeRefreshLayout mSwipeRefreshLayout;

    List<StoreInactiveItem> ItmList = new ArrayList<>();

    public Frag_InActive() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_inactive_child, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        iadapter = new InActiveAdapter(ItmList);

        mrecyclerView = getActivity().findViewById(R.id.recycleview_inactive);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeToRefresh);
        textView = view.findViewById(R.id.inventory_null);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);

        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setAdapter(iadapter);


        if (getArguments() != null) {
            Bundle bundle = this.getArguments();
            cus_id = bundle.getString("cus_id");
            Log.d("cus_id",cus_id);
        }else{
            Log.d("cus_id","null babey");
        }

//        if(ItmList != null){
//            ItmList.clear();
//            iadapter.notifyDataSetChanged();
//        }
        loadAdapter(cus_id);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ItmList.clear();
                iadapter.notifyDataSetChanged();
                loadAdapter(cus_id);
                mSwipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getContext(),"Successfully Refreshed",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadAdapter(String cus_id) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<InactiveResponse> call = apiInterface.getInactiveFilteredResponse(email, password, cus_id);

        final ProgressDialog progressDialog = new ProgressDialog(getContext(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        call.enqueue(new Callback<InactiveResponse>() {
            @Override
            public void onResponse(Call<InactiveResponse> call, Response<InactiveResponse> response) {

                if (response.isSuccessful()) {
                    InactiveResponse resp1 = response.body();

                    List<StoreInactiveItem> cus = resp1.getStoreInactiveItems();

//                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {

                            StoreInactiveItem item = new StoreInactiveItem();

                            String company = cus.get(i).getCompanyName();
                            String sku = cus.get(i).getItemSkuNumber();
                            String name = cus.get(i).getItemName();
                            String status = cus.get(i).getItemStatus();
                            String lastdate = cus.get(i).getUpdatedAt();

                            if (cus.get(i).getmPickBalance() != null) {
                                 pbalance = String.valueOf(cus.get(i).getmPickBalance());
                            } else {
                                 pbalance = "0";

                            }

                            if (cus.get(i).getmPick() != null) {
                                 pick = String.valueOf(cus.get(i).getmPick());
                            } else {
                                 pick = "0";

                            }

                            if (cus.get(i).getmRequestedQuantity() != null) {
                                 request = String.valueOf(cus.get(i).getmRequestedQuantity());
                            } else {
                                 request = "0";

                            }

                            if (cus.get(i).getmReplenish() != null) {

                                 replenish = String.valueOf(cus.get(i).getmReplenish());
                                item.setmReplenish(Long.valueOf(replenish));
                            } else {
                                 replenish = "0";
                                item.setmReplenish(Long.valueOf("0"));

                            }

                            if (cus.get(i).getmOrderedQuantity() != null) {
                                orderedq = String.valueOf(cus.get(i).getmOrderedQuantity());
                            } else {
                                orderedq = "0";

                            }

                            item.setCompanyName(company);
                            item.setItemSkuNumber(sku);
                            item.setItemName(name);
                            item.setItemStatus(status);
                            item.setUpdatedAt(lastdate);
                            item.setmOrderedQuantity(Long.valueOf(orderedq));
//                            item.setmReplenish(Long.valueOf(replenish));
                            item.setmPick(Long.valueOf(pick));
                            item.setmPickBalance(Long.valueOf(pbalance));
                            item.setmRequestedQuantity(Long.valueOf(request));

                            ItmList.add(item);
                        }
                        Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();

                        iadapter.updateAnswers(ItmList);
                        textView.setVisibility(View.GONE);


                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        textView.setText(d);
                        Toast.makeText(getContext(), d, Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<InactiveResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

//    private void log(){
//        if (getArguments() != null) {
//
//            ArrayList<ItemDetail> arraylist = getArguments().getParcelableArrayList("item_detail");
//            ladapter.updateAnswers(arraylist);
//        }
//    }

}
