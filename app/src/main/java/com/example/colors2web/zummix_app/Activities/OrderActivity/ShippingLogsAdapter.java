package com.example.colors2web.zummix_app.Activities.OrderActivity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.OrderShippingAddressLogs.OrderShippingAddressLog;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class ShippingLogsAdapter extends RecyclerView.Adapter<ShippingLogsAdapter.LogViewHolder> {
    private Context mContext;

    public ShippingLogsAdapter(List<OrderShippingAddressLog> logList) {
        LogList = logList;
    }

   private List<OrderShippingAddressLog> LogList;

    public ShippingLogsAdapter(Context montext, ArrayList<OrderShippingAddressLog> logList) {
        this.mContext =montext;
        this.LogList =logList;
    }

    @NonNull
    @Override
    public ShippingLogsAdapter.LogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_order_shipping_logs, parent, false);
        return new LogViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShippingLogsAdapter.LogViewHolder holder, int position) {
        OrderShippingAddressLog log = LogList.get(position);
        String name = log.getCustomerFname() + " " +log.getCustomerLname();
        String address = log.getCustomerAddress1() + " " + log.getCustomerCity() + " " + log.getCustomerState() +
                " " + log.getCustomerCountry() + " " + log.getCustomerZip();

        holder.cname.setText(name);
        holder.email.setText(log.getCustomerEmail());
        holder.phone.setText(log.getCustomerPhone1());
        holder.address.setText(address);
        holder.comment.setText(log.getComment());
    }

    @Override
    public int getItemCount() {
        return LogList.size();
    }

    public void updateAnswers(ArrayList<OrderShippingAddressLog> logList) {
        LogList = logList;
        notifyDataSetChanged();
    }

    public void updateAnswer2() {
        LogList.clear();
        notifyDataSetChanged();
    }

    public class LogViewHolder extends RecyclerView.ViewHolder {
        TextView cname, email, phone, address, comment;

        public LogViewHolder(View itemView) {
            super(itemView);

            cname = itemView.findViewById(R.id.shipping_log_cname);
            email = itemView.findViewById(R.id.shipping_log_email);
            phone = itemView.findViewById(R.id.shipping_log_phone);
            address = itemView.findViewById(R.id.shipping_log_address);
            comment = itemView.findViewById(R.id.shipping_log_comment);

        }
    }
}
