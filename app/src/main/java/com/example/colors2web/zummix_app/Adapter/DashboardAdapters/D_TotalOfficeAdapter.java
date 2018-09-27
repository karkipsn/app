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
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalOfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekTotalOfficeOrder;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class D_TotalOfficeAdapter extends RecyclerView.Adapter<D_TotalOfficeAdapter.OfficeHolder> {

    List<TotalOfficeOrder>WTOList;
    Context mContext;
    private String order_type = "0";
    private String customer_id ="all";
    private String from,to,order_status ;

    public D_TotalOfficeAdapter(List<TotalOfficeOrder> WTOList, Context mContext) {
        this.WTOList = WTOList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public D_TotalOfficeAdapter.OfficeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_dashboard_total_adapter,parent,false);

        return new OfficeHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull D_TotalOfficeAdapter.OfficeHolder holder, int position) {

        TotalOfficeOrder ship = WTOList.get(position);

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
        String holding = String.valueOf(ship.getmTotalOpenOrders());
        String batched = String.valueOf(ship.getmTotalBatchedOrders());
        String hoverdue = String.valueOf(ship.getmTotalOnholdOverdueOrders());
        String boverdue = String.valueOf(ship.getmTotalBatchedOverdueOrders());
        String shipped = String.valueOf(ship.getmTotalShippedOrders());

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
        return WTOList.size();
    }

    public void updateAnswers(ArrayList<TotalOfficeOrder> logs1, String from1, String to1) {
        WTOList.clear();
        WTOList =logs1;
        from = from1;
        to = to1;
        notifyDataSetChanged();
    }

    public class OfficeHolder extends RecyclerView.ViewHolder {
        TextView total, tover, pending, pover, batch, bover, shipped, ior;

        public OfficeHolder(View itemView) {

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
