package com.example.colors2web.zummix_app.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderLog;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class OrderLogsAdapter extends RecyclerView.Adapter<OrderLogsAdapter.LogViewHolder> {

    List<OrderLog> LogList;

    @NonNull
    @Override
    public OrderLogsAdapter.LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_adapter_logs, parent, false);

        return new LogViewHolder(view);
    }

    public OrderLogsAdapter(List<OrderLog> logList) {
        LogList = logList;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderLogsAdapter.LogViewHolder holder, int position) {
        OrderLog log = LogList.get(position);

        holder.ord_no.setText(log.getOrderNumber());

        switch (log.getOrderStatus()) {

            case "0":
                holder.ord_status.setText("Order Created");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.colorPrimaryDark));
                break;

            case "1":
                holder.ord_status.setText("Cancelled");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.colorPrimaryDark));
                break;

            case "2":
                holder.ord_status.setText("Batch Created");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.colorPrimaryDark));
                break;

            case "3":
                holder.ord_status.setText("Picked");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.green_light));
                break;

            case "4":
                holder.ord_status.setText("Packed");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.blue));
                break;

            case "5":
                holder.ord_status.setText("IOR");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.colorPrimaryDark));
                break;

            case "6":
                holder.ord_status.setText("Shipped");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.green_dark));
                break;

            case "7":
                holder.ord_status.setText("Edited Shipping Address");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.purple));
                break;


            case "8":
                holder.ord_status.setText("Pick List Printed");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.green_light));
                break;

            case "9":
                holder.ord_status.setText(" Cancelled Shipment");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.colorPrimaryDark));
                break;

            case "10":
                holder.ord_status.setText("Pick Started");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.purple));
                break;

            case "11":
                holder.ord_status.setText("Pick Ended");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.green_dark));

                break;

            case "12":
                holder.ord_status.setText("Pack Started");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.purple));
                break;

            case "13":
                holder.ord_status.setText(" Pack Ended");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.green_light));
                break;

            default:
                holder.ord_status.setText("Default");
                holder.ord_status.setTextColor( holder.ord_status.getResources().getColor(R.color.yellow));
                break;
        }
        // holder.ord_status.setText(log.getOrderStatus());


        holder.event_by.setText(log.getEventBy());
        holder.event_at.setText(log.getUpdatedAt());


    }

    @Override
    public int getItemCount() {
        return LogList.size();
    }

    public class LogViewHolder extends RecyclerView.ViewHolder {

        TextView ord_no, ord_status, event_at, event_by;

        public LogViewHolder(View itemView) {

            super(itemView);

            ord_no = itemView.findViewById(R.id.rv_ordno);
            ord_status = itemView.findViewById(R.id.rv_ordst);
            event_by = itemView.findViewById(R.id.rv_event_by);
            event_at = itemView.findViewById(R.id.rv_event_at);

        }
    }

    public void updateAnswers(List<OrderLog> logs) {
        LogList = logs;
        notifyDataSetChanged();
    }
}
