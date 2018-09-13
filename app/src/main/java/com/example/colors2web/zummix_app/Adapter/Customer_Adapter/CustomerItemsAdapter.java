package com.example.colors2web.zummix_app.Adapter.Customer_Adapter;

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
    Long qoh1;

    public CustomerItemsAdapter(List<CustomerItem> cList) {
        this.CList = cList;
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
        holder.item_sku.setText(customerItem.getItemSkuNumber());
        holder.item_name.setText(customerItem.getItemName());

        String stat = customerItem.getItemStatus();

        if (stat.equals("1")) {
            holder.status.setText("Active");
        } else {
            holder.status.setText("InActive");
        }


        holder.pbalance.setText(String.valueOf(customerItem.getPickBalance()));
        holder.pick.setText(String.valueOf(customerItem.getPick()));
        holder.rep.setText(String.valueOf(customerItem.getReplenish()));

        String pick =String.valueOf(customerItem.getPick());
        String reps =String.valueOf(customerItem.getReplenish());

        Long qoh1 = customerItem.getPick() + customerItem.getReplenish();
        holder.qoh.setText(String.valueOf(qoh1));
//
//
//        if(pick.equals(null)|| reps.equals(null))
//        {
//            qoh1 = Long.valueOf(0);
//
//            holder.qoh.setText("0");
//
//        }else  if(pick.equals(null)|| !reps.equals(null)){
//            qoh1 = Long.valueOf(reps);
//            holder.qoh.setText(reps);
//        }else if(!pick.equals(null)|| reps.equals(null)){
//            qoh1 = Long.valueOf(pick);
//
//            holder.qoh.setText(pick);
//        }else{
//             qoh1 = Long.valueOf(customerItem.getPick()) + Long.valueOf(customerItem.getReplenish());
//            holder.qoh.setText(String.valueOf(qoh1));
//        }



        holder.oqty.setText(String.valueOf(customerItem.getOrderedQuantity()));
        holder.commit.setText(String.valueOf(customerItem.getRequestedQuantity()));



        Long as = qoh1 - customerItem.getRequestedQuantity() - customerItem.getOrderedQuantity();
        holder.a2s.setText(String.valueOf(as));
    }


//        holder.oqty.setText(String.valueOf(String.valueOf(item.getmOrderedQuantity())));
//        holder.commit.setText(String.valueOf(item.getmRequestedQuantity()));
//
//        Long as = qoh1 - item.getmRequestedQuantity() - item.getmOrderedQuantity();
//        holder.a2s.setText(String.valueOf(as));


    @Override
    public int getItemCount() {
        return CList.size();
    }

    public void updateAnswers(List<CustomerItem> cList) {
        CList = cList;
        notifyDataSetChanged();
    }

    public class CustomHolder extends RecyclerView.ViewHolder {

        TextView item_sku, item_name, status, rep, pick, qoh, pbalance, oqty, commit, a2s;

        public CustomHolder(View itemView) {
            super(itemView);
            item_sku = itemView.findViewById(R.id.cus_list_sku);
            item_name = itemView.findViewById(R.id.cus_list_name);
            status = itemView.findViewById(R.id.inactive_adpt_status);
            pick = itemView.findViewById(R.id.inactive_adpt_pick);
            qoh = itemView.findViewById(R.id.inactive_adpt_qoh);
            rep = itemView.findViewById(R.id.inactive_adpt_replenish);
            pbalance = itemView.findViewById(R.id.inactive_adpt_pick_balance);
            oqty = itemView.findViewById(R.id.inactive_adpt_ord_qty);
            commit = itemView.findViewById(R.id.inactive_adpt_committed);
            a2s = itemView.findViewById(R.id.inactive_adpt_a2s);

        }
    }
}
