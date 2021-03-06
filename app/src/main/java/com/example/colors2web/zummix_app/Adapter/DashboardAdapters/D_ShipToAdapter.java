package com.example.colors2web.zummix_app.Adapter.DashboardAdapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.DashboardDetailsActivity;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.dashboard_details_fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.page_fragments.ShipTo_Fragment;
import com.example.colors2web.zummix_app.POJO.Cron_Details.CronDetailsResponse;
import com.example.colors2web.zummix_app.POJO.Cron_Details.Order;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.ShipToOrder;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class D_ShipToAdapter extends RecyclerView.Adapter<D_ShipToAdapter.ShipHolder> {

    private List<ShipToOrder> ShipList;

    private Context mContext;
    private String from, to, order_status;
    private String order_type = "1";
    ShipTo_Fragment frag;


    public D_ShipToAdapter(List<ShipToOrder> shipList, Context mContext,ShipTo_Fragment frag) {
        ShipList = shipList;
        this.mContext = mContext;
        this.frag = frag;


    }

    @NonNull
    @Override
    public D_ShipToAdapter.ShipHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_dashboard_adapter, parent, false);

        return new ShipHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull D_ShipToAdapter.ShipHolder holder, int position) {

        ShipToOrder ship = ShipList.get(position);

        final String customer_id = String.valueOf(ship.getmCustomerId());

        holder.customer.setText(ship.getmCustomerName());

        Long overdue = ship.getmTotalBatchedOverdueOrders() + ship.getmTotalOnholdOverdueOrders();

        String toverdue = String.valueOf(overdue);
        String torder = String.valueOf(ship.getmNoOfOrders());
        String holding = String.valueOf(ship.getmTotalOpenOrders());
        String batched = String.valueOf(ship.getmTotalBatchedOrders());
        String hoverdue =String.valueOf(ship.getmTotalOnholdOverdueOrders());
        String boverdue = String.valueOf(ship.getmTotalBatchedOverdueOrders());
        String shipped1 = String.valueOf(ship.getmTotalShippedOrders());


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

        if (toverdue != null && !toverdue.equals("0") && Long.valueOf(toverdue)>0) {

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

        if (holding != null && !holding.equals("0") && ship.getmTotalOpenOrders()>0) {

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

        if (hoverdue != null && !hoverdue.equals("0") && ship.getmTotalOnholdOverdueOrders()>0) {
            holder.pover.setTextColor(holder.pover.getResources().getColor(R.color.colorPrimaryDark));

            holder.pover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "onHoldOverDue";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }
        holder.batch.setText(batched);
        if (batched != null && !batched.equals("0")&& ship.getmTotalBatchedOrders()>0) {

            holder.batch.setTextColor(holder.batch.getResources().getColor(R.color.colorPrimaryDark));

            holder.batch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "batched";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }
        holder.bover.setText(boverdue);
        if (boverdue != null && !boverdue.equals("0") && ship.getmTotalBatchedOverdueOrders()>0) {

            holder.bover.setTextColor(holder.bover.getResources().getColor(R.color.colorPrimaryDark));

            holder.bover.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    order_status = "batchedOverDue";
                    call_details_api(order_status, customer_id, order_type, from, to);

                }
            });

        }
        holder.shipped.setText(shipped1);

        if (shipped1 != null && !shipped1.equals("0") &&ship.getmTotalShippedOrders()>0) {

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

        Intent intent = new Intent(mContext, DashboardDetailsActivity.class);
//        intent.putParcelableArrayListExtra("UList", UList);
        intent.putExtra("order_status",order_status);
        intent.putExtra("customer_id",customer_id);
        intent.putExtra("order_type",order_type);
        intent.putExtra("from",from);
        intent.putExtra("to",to);
        mContext.startActivity(intent);


//        final ArrayList<Order> UList = new ArrayList<>();
//
//       APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
//
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
//        final String email = preferences.getString("email", "");
//        final String password = preferences.getString("password", "");
//
//        final ProgressDialog progressDialog = new ProgressDialog(mContext,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();
//
//
//        Call<CronDetailsResponse> call = apiInterface.getCronJobsDetails
//                (email, password, order_status, customer_id, order_type, from, to);
//
//        call.enqueue(new Callback<CronDetailsResponse>() {
//            @Override
//            public void onResponse(Call<CronDetailsResponse> call, Response<CronDetailsResponse> response) {
//
//                CronDetailsResponse response1 = response.body();
//                if (response.isSuccessful()) {
//
//                    if (response1.getReturnType().equals("success")) {
//
//                        List<Order> order = response1.getOrders();
//                        for (int i = 0; i < order.size(); i++) {
//
//                            Order o = new Order();
//
//                            String ono = order.get(i).getOrderNumber();
//                            String state = order.get(i).getCustomerState();
//                            String country = order.get(i).getCustomerCountry();
//                            String zip = order.get(i).getCustomerZip();
//                            String ord_status = order.get(i).getOrderStatus();
//                            String sip_method = order.get(i).getShipMethod();
//                            String ord_date = order.get(i).getOrderDate();
//
//                            o.setOrderNumber(ono);
//                            o.setCustomerState(state);
//                            o.setCustomerCountry(country);
//                            o.setCustomerZip(zip);
//                            o.setOrderStatus(ord_status);
//                            o.setShipMethod(sip_method);
//                            o.setOrderDate(ord_date);
//
//                            UList.add(o);
//                        }
////                        padapter.updateAnswers(UList);
//                        Intent intent = new Intent(mContext, DashboardDetailsActivity.class);
//                        intent.putParcelableArrayListExtra("UList", UList);
//                        mContext.startActivity(intent);
//
//
//                    } else {
//                        if (progressDialog.isShowing()) {
//                            progressDialog.dismiss();
//                        }
//                        Toast.makeText(mContext, " No data for th date Range", Toast.LENGTH_SHORT).show();
//
//
//                    }
//
//                } else if (response.code() == 401) {
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                    Toast.makeText(mContext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
//
//                } else if (response.code() == 404) {
//
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                    Toast.makeText(mContext, "InValid Web Address", Toast.LENGTH_SHORT).show();
//                } else if (response.code() == 500) {
//
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//
//                    Toast.makeText(mContext, "Internal Server Error", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    if (progressDialog.isShowing()) {
//                        progressDialog.dismiss();
//                    }
//                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();
//
//                }

//            }
//
//
//            @Override
//            public void onFailure(Call<CronDetailsResponse> call, Throwable t) {
//
//                call.cancel();
//                Log.e("response-failure", t.toString());
//                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
//                if (progressDialog.isShowing()) {
//                    progressDialog.dismiss();
//                }
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return ShipList.size();
    }

    public void updateAnswers(ArrayList<ShipToOrder> logs1, String from1, String to1) {

        ShipList = logs1;
        notifyDataSetChanged();
        from = from1;
        to = to1;

    }

    public void cleardata() {

        final int size = ShipList.size();
        ShipList.clear();
        notifyItemRangeRemoved(0, size);

    }

    public class ShipHolder extends RecyclerView.ViewHolder {
        TextView customer, total, tover, pending, pover, batch, bover, shipped, ior;

        public ShipHolder(View itemView) {
            super(itemView);

            customer = itemView.findViewById(R.id.dashboard_customer);
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
