package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;

import com.example.colors2web.zummix_app.Adapter.DashboardAdapters.DetailsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Cron_Details.CronDetailsResponse;
import com.example.colors2web.zummix_app.POJO.Cron_Details.Order;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class dashboard_details_fragment extends Fragment {

    DetailsAdapter padapter;
    RecyclerView mrecycleView;
    List<Order> UList = new ArrayList<>();
    APIInterface apiInterface;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_five, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        padapter = new DetailsAdapter(UList, getActivity());

        mrecycleView = view.findViewById(R.id.recycler_view5);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mLayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

        String ordertype = null;
        String customer_id = null; //or all
        final String order_status = null;
        String from = null;
        String to = null;


        loadAdapter(order_status,customer_id,ordertype,from,to);
    }

    private void loadAdapter(String order_status, String customer_id, String ordertype, String from, String to) {

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();



        Call<CronDetailsResponse> call = apiInterface.getCronJobsDetails
                (email, password, order_status, customer_id, ordertype, from, to);

        call.enqueue(new Callback<CronDetailsResponse>() {
            @Override
            public void onResponse(Call<CronDetailsResponse> call, Response<CronDetailsResponse> response) {

                CronDetailsResponse response1 = response.body();
                if (response.isSuccessful()) {

                    if (response1.getReturnType().equals("success")) {

                        List<Order> order =  response1.getOrders();
                        for (int i =0;i<order.size();i++){

                            Order o = new Order();

                            String ono = order.get(i).getOrderNumber();
                            String state = order.get(i).getCustomerState();
                            String country = order.get(i).getCustomerCountry();
                            String zip = order.get(i).getCustomerZip();
                            String ord_status = order.get(i).getOrderStatus();
                            String sip_method = order.get(i).getShipMethod();
                            String ord_date = order.get(i).getOrderDate();

                            o.setOrderNumber(ono);
                            o.setCustomerState(state);
                            o.setCustomerCountry(country);
                            o.setCustomerZip(zip);
                            o.setOrderStatus(ord_status);
                            o.setShipMethod(sip_method);
                            o.setOrderDate(ord_date);

                            UList.add(o);

                        }
                        padapter.updateAnswers(UList);


                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getContext(), " No data for th date Range", Toast.LENGTH_SHORT).show();


                    }

                } else if (response.code() == 401) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                } else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }

            }


            @Override
            public void onFailure(Call<CronDetailsResponse> call, Throwable t) {

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
