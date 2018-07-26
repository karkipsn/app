package com.example.colors2web.zummix_app.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.CusGroupDetails.Order;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class CustomerDetailsAdapter extends RecyclerView.Adapter<CustomerDetailsAdapter.DetailsHolder> {
    List<Order> ordList;

    public CustomerDetailsAdapter(List<Order> ordList) {
        this.ordList = ordList;
    }

    @NonNull
    @Override
    public CustomerDetailsAdapter.DetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_customer_details,null,true);
        return new DetailsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerDetailsAdapter.DetailsHolder holder, int position) {
        Order ord = ordList.get(position);
        holder.t1.setText(ord.getOrderNumber());
        holder.t2.setText(ord.getTotalPieces());
        holder.t3.setText(String.valueOf(ord.getTotalUniqueItems()));
        holder.t5.setText(ord.getCustomerOfficeName());
        String address =ord.getCustomerState()+"|"+ord.getCustomerCountry()+"|"+ord.getCustomerZip();
        holder.t6.setText(address);
        holder.t7.setText(ord.getShipMethod());
        holder.t8.setText(ord.getOrderDate());

        String ord_type =ord.getOrderType();

        switch (ord_type){
            case "0":
                holder.t4.setText("Field Office Delivery");
                break;

            case "1":
                holder.t4.setText("Ship To");
                break;

            case "2":
                holder.t4.setText("VIP Delivery");
                break;

                default:
                    holder.t4.setText("");
                    break;

        }

    }

    @Override
    public int getItemCount() {
        return ordList.size();
    }

    public void updateAnswers(List<Order> orderList) {
        ordList =orderList;
        notifyDataSetChanged();
    }

    public class DetailsHolder extends RecyclerView.ViewHolder {
        TextView t1,t2,t3,t4,t5,t6,t7,t8;
        public DetailsHolder(View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.morders);
            t2 = itemView.findViewById(R.id.mtotal_pieces);
            t3 = itemView.findViewById(R.id.m_total_unique_items);
            t4 = itemView.findViewById(R.id.morder_type);
            t5 = itemView.findViewById(R.id.mfield_office);
            t6 = itemView.findViewById(R.id.maddress);
            t7 = itemView.findViewById(R.id.mship_method);
            t8 = itemView.findViewById(R.id.morder_date);
        }
    }
}
