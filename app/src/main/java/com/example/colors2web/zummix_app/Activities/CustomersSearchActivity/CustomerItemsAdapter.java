package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.customers.CustomerItem;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class CustomerItemsAdapter extends RecyclerView.Adapter<CustomerItemsAdapter.CustomHolder> {
    List<CustomerItem> CList;
    public CustomerItemsAdapter(List<CustomerItem> cList) {
        this.CList=cList;
    }

    @NonNull
    @Override
    public CustomerItemsAdapter.CustomHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.customer_customeritems_child, parent, false);
        return new CustomHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerItemsAdapter.CustomHolder holder, int position) {
        CustomerItem customerItem = CList.get(position);
        holder.t1.setText(String.valueOf(customerItem.getCustomerId()));
        holder.t2.setText(customerItem.getItemSkuNumber());
        holder.t3.setText(customerItem.getItemName());
        holder.t4.setText(customerItem.getPrice());
        holder.t5.setText(String.valueOf(customerItem.getTrQuantity()));

    }

    @Override
    public int getItemCount() {
        return CList.size();
    }

    public void updateAnswers(List<CustomerItem> cList) {
        CList=cList;
        notifyDataSetChanged();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {

        TextView t1, t2, t3, t4, t5;

        public CustomHolder(View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.cus_list_id);
            t3 = itemView.findViewById(R.id.cus_list_sku);
            t2 = itemView.findViewById(R.id.cus_list_name);
            t4 = itemView.findViewById(R.id.cus_list_price);
            t5 = itemView.findViewById(R.id.cus_list_quantity);
        }
    }
}
