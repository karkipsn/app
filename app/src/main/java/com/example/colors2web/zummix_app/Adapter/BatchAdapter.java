package com.example.colors2web.zummix_app.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.BatchNumber.BatchOrder;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class BatchAdapter extends RecyclerView.Adapter<BatchAdapter.BatchHolder> {
    List<BatchOrder> BatchList;

    public BatchAdapter(Context mContext, List<BatchOrder> batchList) {
        this.mContext = mContext;
        BatchList = batchList;

    }

    Context mContext;

    @NonNull
    @Override
    public BatchAdapter.BatchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_batch_adapter, parent, false);

        return new BatchHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull BatchAdapter.BatchHolder holder, int position) {
        BatchOrder order = BatchList.get(position);

        holder.store.setText(order.getShipToName());
        holder.ono.setText(order.getOrderNumber());
        holder.shipto.setText(order.getShipToName());
        holder.otype.setText(order.getOrderType());

        int total = Integer.parseInt(order.getTotalQty());
        int totalremaining = Integer.parseInt(order.getTotalRemainingQty());
        String ord_status = order.getOrderStatus();
        String is_refunded = order.getIsRefunded();


        if (order.getTotalQty() != null || order.getTotalRemainingQty() != null) {

            int pack = total - totalremaining;
            holder.pqt.setText(String.valueOf(pack));
        } else {
            holder.pqt.setText(String.valueOf(order.getTotalQty()));
        }

        holder.tqt.setText(order.getTotalQty());
        holder.rqt.setText(order.getTotalRemainingQty());
        holder.create.setText(order.getCreatedAt());
        holder.flag.setText(ord_status);
     String  rem = null;

        if(ord_status.equals("Shipped")){
            rem ="Order is already shipped.";
        }else if(ord_status.equals("Batch Created")){
            rem ="Go to pack order section.";
        }else if(ord_status.equals("Picked")){
           rem ="Process to Pack the Order";
//           Pack Button with api in web
        }else if(ord_status.equals("Batch Created")){
            rem ="Order is already shipped.";
        }else if(ord_status.equals("Packed") && (is_refunded.equals("1"))){
            rem ="Order is already packed with" + totalremaining +"refunded.";
        }else if(ord_status.equals("Packed") && (totalremaining == 0)){
            rem ="Order  already packed.";
        }else if(ord_status.equals("Packed") && (totalremaining > 0)){
            rem ="Process to Pack the Order";
            //           Pack Button with api in web
        }else if(ord_status.equals("Cancelled")){
            rem="Order already cancelled.";
            holder.flag.setBackgroundColor(holder.flag.getResources().getColor(R.color.pink));
        }else if(is_refunded.equals("1")){
            rem ="Order is already packed with " + totalremaining +"refunded.";
        }

        holder.remarks.setText(rem);


    }

    @Override
    public int getItemCount() {
        return BatchList.size();
    }

    public void updateAnswers(List<BatchOrder> batchList) {
        BatchList = batchList;
    }

    public class BatchHolder extends RecyclerView.ViewHolder {

        TextView store, ono, shipto, otype, tqt, pqt, rqt, create, flag, remarks;

        public BatchHolder(View itemView) {
            super(itemView);

            store = itemView.findViewById(R.id.batch_adp_storename);
            ono = itemView.findViewById(R.id.batch_adp_orderno);
            shipto = itemView.findViewById(R.id.batch_adp_shipto);
            otype = itemView.findViewById(R.id.batch_adp_ordertype);
            tqt = itemView.findViewById(R.id.batch_adp_tqty);
            pqt = itemView.findViewById(R.id.batch_adp_pack_qty);
            rqt = itemView.findViewById(R.id.batch_adp_rem_qty);
            create = itemView.findViewById(R.id.batch_adp_created);
            flag = itemView.findViewById(R.id.batch_adp_flag);
            remarks = itemView.findViewById(R.id.batch_adp_remarks);


        }
    }
}
