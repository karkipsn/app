package com.example.colors2web.zummix_app.Fragments;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderShippingAddressesDetail;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class SAdapter extends RecyclerView.Adapter<SAdapter.ShippingHolder> {
    List<OrderShippingAddressesDetail> ShippingList;

    public SAdapter
(List<OrderShippingAddressesDetail> shippingList) {
        ShippingList = shippingList;
    }

    @NonNull

    @Override
    public SAdapter
.ShippingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_sadapter,parent,false);
        return new ShippingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SAdapter.ShippingHolder holder, int position) {

        OrderShippingAddressesDetail detail=ShippingList.get(position);
        holder.sname.setText(detail.getCustomerFname() + detail.getCustomerLname());
        holder.sphone.setText(detail.getCustomerPhone1());
        holder.semail.setText(detail.getCustomerEmail());
        String address = detail.getCustomerAddress1()+ "\n"
                + detail.getCustomerCity()+", "+detail.getCustomerState()+
                "\n"+detail.getCustomerCountry()+", "+detail.getCustomerZip();
        holder.saddress.setText(address);

    }

    @Override
    public int getItemCount() {
        return ShippingList.size();
    }

    public void updateAnswers(ArrayList<OrderShippingAddressesDetail> sarraylist) {
        ShippingList =sarraylist;
        notifyDataSetChanged();
    }

    public class ShippingHolder extends RecyclerView.ViewHolder {
        TextView sname,sphone,saddress ,semail,soffice;
        public ShippingHolder(View itemView) {
            super(itemView);

            sname=itemView.findViewById(R.id.sadpt_name);
            sphone=itemView.findViewById(R.id.sadpt_phone);
            saddress=itemView.findViewById(R.id.sadpt_address);
            semail=itemView.findViewById(R.id.sadpt_email);
        }
    }
}
