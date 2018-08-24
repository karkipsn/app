package com.example.colors2web.zummix_app.Adapter;

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

public class ShippingAdapter extends RecyclerView.Adapter<ShippingAdapter.ShippingHolder> {
    List<OrderShippingAddressesDetail> ShippingList;

    public ShippingAdapter(List<OrderShippingAddressesDetail> shippingList) {
        ShippingList = shippingList;
    }

    @NonNull

    @Override
    public ShippingAdapter.ShippingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.frag_two_ship_adapter,parent,false);
        return new ShippingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShippingAdapter.ShippingHolder holder, int position) {

        OrderShippingAddressesDetail detail=ShippingList.get(position);
        holder.sname.setText(detail.getCustomerFname() + detail.getCustomerLname());
        holder.sphone.setText(detail.getCustomerPhone1());
        holder.saddress.setText(detail.getCustomerAddress1());
        holder.semail.setText(detail.getCustomerEmail());
        holder.soffice.setText(detail.getCustomerOfficeName());

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
            sname=itemView.findViewById(R.id.dis_sname);
            sphone=itemView.findViewById(R.id.dis_sphone);
            saddress=itemView.findViewById(R.id.dis_saddress);
            semail=itemView.findViewById(R.id.dis_semail);
            soffice=itemView.findViewById(R.id.dis_soffice);
        }
    }
}
