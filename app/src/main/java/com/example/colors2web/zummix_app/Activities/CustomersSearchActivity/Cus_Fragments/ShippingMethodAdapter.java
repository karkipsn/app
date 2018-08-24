package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.customers.ShippingMethods;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class ShippingMethodAdapter extends RecyclerView.Adapter<ShippingMethodAdapter.ShippingHolder> {

    List<ShippingMethods> ShipList;

    public ShippingMethodAdapter(List<ShippingMethods> shipList) {
        this.ShipList =shipList;
    }

    @NonNull
    @Override
    public ShippingMethodAdapter.ShippingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.acticity_fragment_shipping_method_child,parent,false);
        return new ShippingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShippingMethodAdapter.ShippingHolder holder, int position) {

        ShippingMethods shippingMethods = ShipList.get(position);

        holder.id.setText(String.valueOf(shippingMethods.getId()));
        holder.cus_id.setText(String.valueOf(shippingMethods.getCustomerId()));
        holder.ac_type.setText(shippingMethods.getShippingAccountType());
        holder.co_type.setText(shippingMethods.getShippingCompanyType());
        holder.ac.setText(shippingMethods.getAccountNumber());

    }

    @Override
    public int getItemCount() {
        return ShipList.size();
    }

    public void updateAnswers(List<ShippingMethods> shipList1) {
        ShipList =shipList1;
        notifyDataSetChanged();
    }


    public class ShippingHolder extends RecyclerView.ViewHolder {
        TextView id,cus_id,ac_type,co_type,ac;
        public ShippingHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.cus_shippin_ad_id);
            cus_id = itemView.findViewById(R.id.cus_shippin_ad_cus_id);
            ac_type = itemView.findViewById(R.id.cus_shippin_ad_ac_type);
            co_type = itemView.findViewById(R.id.cus_shippin_ad_company_type);
            ac = itemView.findViewById(R.id.cus_shippin_ad_account);
        }
    }
}
