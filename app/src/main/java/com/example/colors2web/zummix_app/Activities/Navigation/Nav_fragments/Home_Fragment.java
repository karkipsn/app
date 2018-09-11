package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.GroupByCusADapter;
import com.example.colors2web.zummix_app.ItemDecoration.GridSpacingItemDecoration;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment {

    android.support.v7.widget.Toolbar toolbar;

    APIInterface apiInterface;
    RecyclerView mrecyclerView;
    GroupByCusADapter radapter;

    ImageView img;
    String spine;

    List<Order> CusList = new ArrayList<>();


    public Home_Fragment(){

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nav_home,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        radapter = new GroupByCusADapter(getContext(), CusList);

        mrecyclerView = getActivity().findViewById(R.id.recycle_view);

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(layoutManager);

        mrecyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(radapter);
        loadAdapter(email, password);


    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    private void loadAdapter(String email, String password) {

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

//                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {

                            Order dis = new Order();

                            String name = order.get(i).getCustomerName();
                            String vip = String.valueOf(order.get(i).getNoOfVipDeliveryOrders());
                            String shipto = String.valueOf(order.get(i).getNoOfShipToOrders());
                            String field = String.valueOf(order.get(i).getNoOfWillCallsOrders());
                            String cus_id = String.valueOf(order.get(i).getCustomerId());

                            dis.setCustomerName(name);
                            dis.setNoOfVipDeliveryOrders(Long.valueOf(vip));
                            dis.setNoOfShipToOrders(Long.valueOf(shipto));
                            dis.setNoOfWillCallsOrders(Long.valueOf(field));
                            dis.setCustomerId(Long.valueOf(cus_id));

                            CusList.add(dis); // must be the object of empty list initiated
                        }


                        radapter.updateAnswers(CusList);//adapter's content is updated and update function is called;
                        //send parameters according to urs adapter view setup.);

                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        if(progressDialog.isShowing()){
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 401) {

                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }
                else if (response.code() == 500) {

                    if(progressDialog.isShowing()){
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }
                else {
                    if(progressDialog.isShowing()){
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
                if(progressDialog.isShowing()){
                    progressDialog.dismiss();
                }

            }
        });
    }
}
