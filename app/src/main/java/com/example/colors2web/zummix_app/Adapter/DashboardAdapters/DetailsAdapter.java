package com.example.colors2web.zummix_app.Adapter.DashboardAdapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.OrderSearch2Activity;
import com.example.colors2web.zummix_app.POJO.Cron_Details.Order;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderDetails;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderSearchResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsAdapter extends RecyclerView.Adapter<DetailsAdapter.DetailHolder> {

    List<Order> OrderList;
    Activity mCOntext;

    public DetailsAdapter(List<Order> uList, FragmentActivity activity) {
        OrderList =uList;
        this.mCOntext =activity;
    }

    @NonNull
    @Override
    public DetailsAdapter.DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_cron_details_adapter, parent, false);

        return new DetailHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.DetailHolder holder, int position) {
        Order ship = OrderList.get(position);

        final String ord_no =ship.getOrderNumber();

        holder.order.setText(ord_no);
        if(ord_no !=null){
        holder.order.setTextColor(holder.order.getResources().getColor(R.color.colorPrimary));
        }
        if(holder.order !=null){
            holder.order.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

//                    Intent intent = new Intent(mCOntext, OrderSearch2Activity.class);
//                    intent.putExtra("dash_order",ord_no);
//                    mCOntext.startActivity(intent);
                    call_order2(ord_no);

                }
            });
        }

        String state = ship.getCustomerState();
        String country = ship.getCustomerCountry();
        String zip = ship.getCustomerZip();
        String add = state + " | "+ country +" | " + zip;

        holder.address.setText(add);

        holder.status.setText(ship.getOrderStatus());

        holder.ship_method.setText(ship.getShipMethod());
        holder.date.setText(ship.getOrderDate());

        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                call_order2(ord_no);
            }
        });


    }

    private void call_order2(String ord_no) {

            final ProgressDialog progressDialog = new ProgressDialog(mCOntext,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setMessage("Loading...");
            progressDialog.show();
            final String Path = ord_no;

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mCOntext);
        String email = preferences.getString("email","");
        String password = preferences.getString("password","");


            Call<OrderSearchResponse> call = apiInterface.getOrder1(email, password, Path);
            call.enqueue(new Callback<OrderSearchResponse>() {
                @Override
                public void onResponse(Call<OrderSearchResponse> call, Response<OrderSearchResponse> response) {

                    if (response.isSuccessful()) {

                        OrderDetails order = response.body().getOrderDetails();



                        if (response.body().getReturnType().equals("success") && order != null) {

                            Intent intent = new Intent(mCOntext, OrderSearch2Activity.class);
                            intent.putExtra("OPath", Path);
                            Log.d("path_barcode", Path);
                            mCOntext.startActivity(intent);

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                        } else {
                            String d = response.body().getMessage();

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(mCOntext, d, Toast.LENGTH_LONG).show();

                        }

                    } else if (response.code() == 401) {

                        Toast.makeText(mCOntext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                        Log.d("Error", response.errorBody().toString());
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else if (response.code() == 404) {

                        Toast.makeText(mCOntext, "InValid Web Address", Toast.LENGTH_SHORT).show();
                        Log.d("Error", response.errorBody().toString());
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    } else if (response.code() == 500) {

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        Toast.makeText(mCOntext, "Internal Server Error", Toast.LENGTH_SHORT).show();
                        Log.d("Error", response.errorBody().toString());
                    } else {
                        Toast.makeText(mCOntext, "Operation Failed", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(mCOntext, "Network Error", Toast.LENGTH_SHORT).show();

                }
            });
    }

    @Override
    public int getItemCount() {
        return OrderList.size();
    }

    public void updateAnswers(List<Order> uList) {
        OrderList = uList;
        notifyDataSetChanged();
    }

    public class DetailHolder extends RecyclerView.ViewHolder {

        TextView order, address, status, ship_method, date;
        Button more;

        public DetailHolder(View itemView) {
            super(itemView);

            order = itemView.findViewById(R.id.dashboard_customer);
            address = itemView.findViewById(R.id.dashboard_total);
            status = itemView.findViewById(R.id.dashboard_overdue);
            ship_method = itemView.findViewById(R.id.dashboard_pending);
            date = itemView.findViewById(R.id.dashboard_pending_overdue);
            more = itemView.findViewById(R.id.cron_adapter_btn_more);
        }
    }
}
