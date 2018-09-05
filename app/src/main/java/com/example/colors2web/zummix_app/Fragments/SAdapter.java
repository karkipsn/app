package com.example.colors2web.zummix_app.Fragments;

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

import com.example.colors2web.zummix_app.Activities.OrderActivity.OrderShippingEditActivity;
import com.example.colors2web.zummix_app.Activities.OrderActivity.OrderShippingLogsActivity;
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderShippingAddressesDetail;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderDetails;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class SAdapter extends RecyclerView.Adapter<SAdapter.ShippingHolder> {
    List<OrderShippingAddressesDetail> ShippingList;
    OrderDetails Details;
    Context mContext;


    public SAdapter(Context context,List<OrderShippingAddressesDetail> shippingList) {
        mContext =context;
        ShippingList = shippingList;
    }

    @NonNull

    @Override
    public SAdapter.ShippingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_sadapter, parent, false);
        return new ShippingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SAdapter.ShippingHolder holder, int position) {

        OrderShippingAddressesDetail detail = ShippingList.get(position);

        holder.sname.setText(detail.getCustomerFname() + detail.getCustomerLname());
        holder.sphone.setText(detail.getCustomerPhone1());
        holder.semail.setText(detail.getCustomerEmail());
        String address = detail.getCustomerAddress1() + "\n"
                + detail.getCustomerCity() + ", " + detail.getCustomerState() +
                "\n" + detail.getCustomerCountry() + ", " + detail.getCustomerZip();
        holder.saddress.setText(address);

        String office = detail.getCustomerOfficeName();
        Log.d("officeis", office);

        if (office.equals("")) {
            holder.soffice.setVisibility(View.GONE);
        } else if (office.equals("-")) {
            holder.soffice.setVisibility(View.GONE);
        } else {

            holder.soffice.setVisibility(View.VISIBLE);
            holder.soffice.setText(office);

//            ShippingList.remove(position);
//            notifyItemChanged(position);
//            notifyItemRangeRemoved(position, 1);
//            holder.soffice.setVisibility(View.GONE);

//            dataset.removeAt(position);
//            notifyItemChanged(position);
//            notifyItemRangeRemoved(position, 1);

//            int removeIndex = 4;
//            ShippingList.remove(removeIndex);
//            notifyItemRemoved(removeIndex);
        }

    }

    @Override
    public int getItemCount() {
        return ShippingList.size();
    }

    public void updateAnswers(ArrayList<OrderShippingAddressesDetail> sarraylist, OrderDetails details) {
        ShippingList = sarraylist;
        Details = details;
        notifyDataSetChanged();
    }

    public class ShippingHolder extends RecyclerView.ViewHolder {
        TextView sname, sphone, saddress, semail, soffice, edit, logs;
        String o_id, o_type, o_status, o_edit_shipping_add;

        public ShippingHolder(View itemView) {
            super(itemView);

            sname = itemView.findViewById(R.id.sadpt_name);
            sphone = itemView.findViewById(R.id.sadpt_phone);
            saddress = itemView.findViewById(R.id.sadpt_address);
            soffice = itemView.findViewById(R.id.sadpt_office);
            semail = itemView.findViewById(R.id.sadpt_email);

            edit = itemView.findViewById(R.id.sadpt_edit);
            logs = itemView.findViewById(R.id.sadpt_logs);

            o_id = String.valueOf(Details.getId());
            o_type = Details.getOrderType();
            o_status = Details.getOrderStatus();
            o_edit_shipping_add = Details.getEditShippingAddress();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            final String type = preferences.getString("group_type", "");
            if(type!=null){
                Log.d("type",type);
            }


            if (type != null) {

                if (type.equals("Super Admin") || o_type.equals("1")) {
//               edit button display

                    if (o_status.equals("Shipped")) {
                        edit.setVisibility(View.GONE);
                    } else {
                        edit.setVisibility(View.VISIBLE);
                    }
                }
            }


            if (o_edit_shipping_add.equals("1")) {
//                  logs button to display
                logs.setVisibility(View.VISIBLE);
            }

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //  Pass order id alongside the intent
                    Intent editintent = new Intent(mContext, OrderShippingEditActivity.class);
                    editintent.putExtra("o_id_edit", o_id);
                    mContext.startActivity(editintent);
                }

            });

            logs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    ////  Pass order id alongside the intent
                    Intent logsintent = new Intent(mContext, OrderShippingLogsActivity.class);
                    logsintent.putExtra("o_id_logs", o_id);
                    mContext.startActivity(logsintent);
                }
            });

        }
    }

    private void deleteItem(int position) {
        ShippingList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, ShippingList.size());
//        holder.itemView.setVisibility(View.GONE);
    }
}


//            edit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                Pass order id alongside the intent
//                    Intent editintent = new Intent(getContext(), OrderShippingEditActivity.class);
//                    editintent.putExtra("o_id_edit", o_id);
//                    startActivity(editintent);
//
//                }
//            });
//            logs.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////               Pass order id alongside the intent
//                    Intent logsintent = new Intent(getContext(), OrderShippingLogsActivity.class);
//                    logsintent.putExtra("o_id_logs", o_id);
//                    startActivity(logsintent);
//
//                }
//            });
