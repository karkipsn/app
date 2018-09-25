package com.example.colors2web.zummix_app.Adapter.DashboardAdapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.DashboardDetailsActivity;
import com.example.colors2web.zummix_app.POJO.Cron_Details.CronDetailsResponse;
import com.example.colors2web.zummix_app.POJO.Cron_Details.Order;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalVipOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.VipOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekVipOrder;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class D_TotalVipAdapter extends RecyclerView.Adapter<D_TotalVipAdapter.VHolder> {
    private List<TotalVipOrder> WVList;
    private Context mContext;
    private String order_type = "2";
    private String customer_id = "all";
    private String from, to, order_status;

    public D_TotalVipAdapter(List<TotalVipOrder> WVList, Context mContext) {
        this.WVList = WVList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_dashboard_total_adapter, parent, false);

        return new VHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {

        TotalVipOrder ship = WVList.get(position);

//        Long overdue = ship.getmTotalBatchedOverdueOrders() + ship.getmTotalOnholdOverdueOrders();
//
//        holder.total.setText(String.valueOf(ship.getmNoOfOrders()));
//        holder.tover.setText(String.valueOf(overdue));
//
//        holder.pending.setText(String.valueOf(ship.getmTotalOpenOrders()));
//        holder.pover.setText(String.valueOf(ship.getmTotalOnholdOverdueOrders()));
//        holder.batch.setText(String.valueOf(ship.getmTotalBatchedOrders()));
//        holder.bover.setText(String.valueOf(ship.getmTotalBatchedOverdueOrders()));
//        holder.shipped.setText(String.valueOf(ship.getmTotalShippedOrders()));
//        holder.ior.setText(String.valueOf(ship.getmTotalIor()));

        Long overdue = ship.getmTotalBatchedOverdueOrders() + ship.getmTotalOnholdOverdueOrders();

        String toverdue = String.valueOf(overdue);
        String torder = String.valueOf(ship.getmNoOfOrders());
        String holding = String.valueOf(String.valueOf(ship.getmTotalOpenOrders()));
        String batched = String.valueOf(String.valueOf(ship.getmTotalBatchedOrders()));
        String hoverdue = String.valueOf(String.valueOf(ship.getmTotalOnholdOverdueOrders()));
        String boverdue = String.valueOf(String.valueOf(ship.getmTotalBatchedOverdueOrders()));
        String shipped = String.valueOf(String.valueOf(ship.getmTotalShippedOrders()));

        holder.total.setText(torder);

        if (torder != null && !torder.equals("0")) {

            holder.total.setTextColor(holder.total.getResources().getColor(R.color.colorPrimaryDark));
            holder.total.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "totalOrders";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }

        holder.tover.setText(toverdue);
        if (toverdue != null && !toverdue.equals("0")) {

            holder.tover.setTextColor(holder.tover.getResources().getColor(R.color.colorPrimaryDark));

            holder.tover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "totalOverDue";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });
        }

        holder.pending.setText(holding);
        if (holding != null && !holding.equals("0")) {

            holder.pending.setTextColor(holder.pending.getResources().getColor(R.color.colorPrimaryDark));

            holder.pending.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "onHold";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }
        holder.pover.setText(hoverdue);

        if (hoverdue != null && !hoverdue.equals("0")) {
            holder.pover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "onHoldOverDue";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }
        holder.batch.setText(batched);
        if (batched != null && !batched.equals("0")) {
            holder.batch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "batched";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }
        holder.bover.setText(boverdue);
        if (boverdue != null && !boverdue.equals("0")) {
            holder.bover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "batchedOverDue";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }
        holder.shipped.setText(shipped);
        if (shipped != null && !shipped.equals("0")) {
            holder.shipped.setTextColor(holder.shipped.getResources().getColor(R.color.colorPrimaryDark));
            holder.shipped.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "shipped";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }
        holder.ior.setText(String.valueOf(ship.getmTotalIor()));


    }

    private void call_details_api(String order_status, String customer_id, String order_type, String from, String to) {

        final ArrayList<Order> UList = new ArrayList<>();

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        final ProgressDialog progressDialog = new ProgressDialog(mContext,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<CronDetailsResponse> call = apiInterface.getCronJobsDetails
                (email, password, order_status, customer_id, order_type, from, to);

        call.enqueue(new Callback<CronDetailsResponse>() {
            @Override
            public void onResponse(Call<CronDetailsResponse> call, Response<CronDetailsResponse> response) {

                CronDetailsResponse response1 = response.body();
                if (response.isSuccessful()) {

                    if (response1.getReturnType().equals("success")) {

                        List<Order> order = response1.getOrders();
                        for (int i = 0; i < order.size(); i++) {

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
//                        padapter.updateAnswers(UList);
                        Intent intent = new Intent(mContext, DashboardDetailsActivity.class);
                        intent.putParcelableArrayListExtra("UList", UList);
                        mContext.startActivity(intent);


                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(mContext, " No data for th date Range", Toast.LENGTH_SHORT).show();


                    }

                } else if (response.code() == 401) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(mContext, "InValid Web Address", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(mContext, "Internal Server Error", Toast.LENGTH_SHORT).show();
                } else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();

                }

            }


            @Override
            public void onFailure(Call<CronDetailsResponse> call, Throwable t) {

                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });

    }
    
    @Override
    public int getItemCount() {
        return WVList.size();
    }

    public void updateAnswers(ArrayList<TotalVipOrder> logs1, String from1, String to1) {
        WVList = logs1;
        from = from1;
        to = to1;
        notifyDataSetChanged();
    }

    public class VHolder extends RecyclerView.ViewHolder {

        TextView total, tover, pending, pover, batch, bover, shipped, ior;

        public VHolder(View itemView) {
            super(itemView);
            total = itemView.findViewById(R.id.dashboard_total);
            tover = itemView.findViewById(R.id.dashboard_overdue);
            pending = itemView.findViewById(R.id.dashboard_pending);
            pover = itemView.findViewById(R.id.dashboard_pending_overdue);
            batch = itemView.findViewById(R.id.dashboard_batched);
            bover = itemView.findViewById(R.id.dashboard_batched_overdue);
            shipped = itemView.findViewById(R.id.dashboard_shipped);
            ior = itemView.findViewById(R.id.dashboard_IOR);
        }
    }
}
