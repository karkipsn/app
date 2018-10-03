package com.example.colors2web.zummix_app.Adapter.Order_Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.Activities.OrderSearch2Activity;
import com.example.colors2web.zummix_app.POJO.OrderTrack.OrderDetail;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class TrackingAdapter extends RecyclerView.Adapter<TrackingAdapter.TrackingHolder> {
    List<OrderDetail>Olist;
    Context mcontext;

    public TrackingAdapter(List<OrderDetail> olist, Context mcontext) {
        Olist = olist;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public TrackingAdapter.TrackingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_tracking_details,parent,false);
        return new TrackingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackingAdapter.TrackingHolder holder, int position) {
        OrderDetail od = Olist.get(position);

        holder.order_no.setText(od.getOrderNumber());
        holder.office.setText(od.getCustomerOfficeName());
        holder.emp_id.setText(od.getEmployeeId());
        holder.ship_to.setText(od.getShipTo());
        holder.ship_method.setText(od.getShipMethod());
        holder.tracking.setText(od.getTrackingCode());
        holder.date.setText(od.getOrderDate());

        final String tadpt_id =String.valueOf(od.getId());

        holder.order_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent in = new Intent(mcontext, OrderSearch2Activity.class);
                in.putExtra("tadpt_id",tadpt_id);
                mcontext.startActivity(in);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Olist.size();
    }

    public void updateAnswers(List<OrderDetail> orderList) {
         Olist = orderList ;
        notifyDataSetChanged();
    }

    public class TrackingHolder extends RecyclerView.ViewHolder {
        TextView order_no,office,emp_id,ship_to,ship_method,tracking,date;
        public TrackingHolder(View itemView) {
            super(itemView);
            order_no = itemView.findViewById(R.id.tr_ad_view1);
            office = itemView.findViewById(R.id.tr_ad_view2);
            emp_id = itemView.findViewById(R.id.tr_ad_view3);
            ship_to = itemView.findViewById(R.id.tr_ad_view4);
            ship_method = itemView.findViewById(R.id.tr_ad_view5);
            tracking = itemView.findViewById(R.id.tr_ad_view6);
            date = itemView.findViewById(R.id.tr_ad_view7);
        }
    }
}
