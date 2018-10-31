package com.example.colors2web.zummix_app.Adapter.Order_Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.InactiveCustomers.StoreInactiveItem;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class InActiveAdapter extends RecyclerView.Adapter<InActiveAdapter.InActiveHolder> {
    List<StoreInactiveItem> InList;

    public InActiveAdapter(List<StoreInactiveItem> inList) {

        InList = inList;
    }

    @NonNull
    @Override
    public InActiveAdapter.InActiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_inactive_adapter, parent, false);
        return new InActiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InActiveAdapter.InActiveHolder holder, int position) {

        StoreInactiveItem item = InList.get(position);


        holder.company.setText(item.getCompanyName());
        holder.sku.setText(item.getItemSkuNumber());
        holder.name.setText(item.getItemName());

        String stat = item.getItemStatus();
//        switch (stat){
//            case String.valueOf(stat.equals("0")):
//                holder.status.setText("InActive");
//                break;
//        }

        if (stat.equals("0")) {
            holder.status.setText("InActive");
        } else {
            holder.status.setText("Active");
        }

        holder.lastdate.setText(item.getUpdatedAt());

        holder.pbalance.setText(String.valueOf(item.getmPickBalance()));
        holder.pick.setText(String.valueOf(item.getmPick()));
        holder.rep.setText(String.valueOf(item.getmReplenish()));

        Long qoh1 = item.getmPick() + item.getmReplenish();
        holder.qoh.setText(String.valueOf(qoh1));

        holder.oqty.setText(String.valueOf(String.valueOf(item.getmOrderedQuantity())));
        holder.commit.setText(String.valueOf(item.getmRequestedQuantity()));

        Long as = qoh1 - item.getmRequestedQuantity() - item.getmOrderedQuantity();
        if(as>0){
        holder.a2s.setText(String.valueOf(as));}
        else{
            holder.a2s.setText("0");

        }

//        pick + replenish =qoh
//        committed = requesteditem
//        $replenish + $pick - $requestedQuantity - $orderedQuantity

    }

    @Override
    public int getItemCount() {
        return InList.size();
    }

    public void updateAnswers(List<StoreInactiveItem> itmList) {
        InList = itmList;
        notifyDataSetChanged();
    }

    public class InActiveHolder extends RecyclerView.ViewHolder {

        TextView company, sku, name, status, rep, pick, qoh, pbalance, oqty, commit, a2s, lastdate;

        public InActiveHolder(View itemView) {
            super(itemView);

            company = itemView.findViewById(R.id.inactive_adpt_companyname);
            sku = itemView.findViewById(R.id.inactive_adpt_itemsku);
            name = itemView.findViewById(R.id.inactive_adpt_item_name);
            status = itemView.findViewById(R.id.inactive_adpt_status);
            pick = itemView.findViewById(R.id.inactive_adpt_pick);
            qoh = itemView.findViewById(R.id.inactive_adpt_qoh);
            rep = itemView.findViewById(R.id.inactive_adpt_replenish);
            pbalance = itemView.findViewById(R.id.inactive_adpt_pick_balance);
            oqty = itemView.findViewById(R.id.inactive_adpt_ord_qty);
            commit = itemView.findViewById(R.id.inactive_adpt_committed);
            a2s = itemView.findViewById(R.id.inactive_adpt_a2s);
            lastdate = itemView.findViewById(R.id.inactive_adpt_lastup);
        }
    }

}
