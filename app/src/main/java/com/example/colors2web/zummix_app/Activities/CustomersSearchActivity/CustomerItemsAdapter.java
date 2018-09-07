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
        holder.t1.setText(String.valueOf(customerItem.getId()));
        holder.item_sku.setText(customerItem.getItemSkuNumber());
        holder.item_name.setText(customerItem.getItemName());
        holder.price.setText(customerItem.getPrice());
        holder.qty.setText(String.valueOf(customerItem.getTrQuantity()));

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

        TextView t1, item_sku, item_name, price, qty;

        public CustomHolder(View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.cus_list_id);
            item_sku = itemView.findViewById(R.id.cus_list_sku);
            item_name = itemView.findViewById(R.id.cus_list_name);
            price = itemView.findViewById(R.id.cus_list_price);
            qty = itemView.findViewById(R.id.cus_list_quantity);
        }
    }
}
