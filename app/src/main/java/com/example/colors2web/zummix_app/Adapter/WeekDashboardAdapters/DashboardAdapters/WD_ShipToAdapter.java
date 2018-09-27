package com.example.colors2web.zummix_app.Adapter.WeekDashboardAdapters.DashboardAdapters;

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
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekShipToOrder;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WD_ShipToAdapter extends RecyclerView.Adapter<WD_ShipToAdapter.ShipHolder> {

    List<WeekShipToOrder> ShipList;

    Context mContext;
    private String from, to, order_status;
    private String order_type = "1";
    private APIInterface apiInterface;

    public WD_ShipToAdapter(List<WeekShipToOrder> shipList, Context mContext) {
        ShipList = shipList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public WD_ShipToAdapter.ShipHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_dashboard_adapter, parent, false);

        return new ShipHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WD_ShipToAdapter.ShipHolder holder, int position) {

        WeekShipToOrder ship = ShipList.get(position);

        final String customer_id = String.valueOf(ship.getmCustomerId());

        holder.customer.setText(ship.getmCustomerName());

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
        if (batched != null && !batched.equals("0")) {

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
        if (boverdue != null && !boverdue.equals("0")) {

            holder.bover.setTextColor(holder.bover.getResources().getColor(R.color.colorPrimaryDark));

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

        Intent intent = new Intent(mContext, DashboardDetailsActivity.class);
        intent.putExtra("order_status",order_status);
        intent.putExtra("customer_id",customer_id);
        intent.putExtra("order_type",order_type);
        intent.putExtra("from",from);
        intent.putExtra("to",to);
        mContext.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return ShipList.size();
    }

    public void updateAnswers(ArrayList<WeekShipToOrder> logs1, String from1, String to1) {
        ShipList = logs1;
        notifyDataSetChanged();
        from = from1;
        to = to1;
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
