package com.example.colors2web.zummix_app.Adapter.Ticket_Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.TicketDetails.ReturnedItem;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class TicketLineItemsAdapter extends RecyclerView.Adapter<TicketLineItemsAdapter.LineHolder> {

    Context mContext;
    List<ReturnedItem> ItemList;

    public TicketLineItemsAdapter(Context mContext, List<ReturnedItem> itemList) {
        this.mContext = mContext;
        ItemList = itemList;
    }

    @NonNull
    @Override
    public TicketLineItemsAdapter.LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ticket_lines_adapter, parent, false);
        return new LineHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketLineItemsAdapter.LineHolder holder, int position) {

        ReturnedItem item = ItemList.get(position);

        holder.item.setText(item.getProductName());
        holder.order.setText(item.getSaleNumber());
        holder.number.setText(String.valueOf(item.getQuantity()));
        holder.reason.setText(item.getReason());
//        holder.status.setText(item.getStatus());
//         @if($returnedItem->status == 0)
//          		Pending
//          		@elseif($returnedItem->status == 1)
//          		Return Cancelled
//          		@else
//          		Return Approved
        String status = item.getStatus();
        switch (status){
            case "0":
                holder.status.setText("Pending ");
                break;

            case "1":
                holder.status.setText("Return Cancelled");
                break;

            case "2":
                holder.status.setText("Return Approved");
                break;

        }
        holder.rma.setText(item.getRmaNumber());

    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    public void updateAnswers(ArrayList<ReturnedItem> logs1) {
        ItemList =logs1;
        notifyDataSetChanged();
    }

    public class LineHolder extends RecyclerView.ViewHolder {
        TextView item, order, number, reason, status, rma;

        public LineHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.ticket_item_rma_item);
            order = itemView.findViewById(R.id.ticket_item_rma_order);
            number = itemView.findViewById(R.id.ticket_item_rma_number);
            reason = itemView.findViewById(R.id.ticket_item_rma_reason_to_cancel);
            status = itemView.findViewById(R.id.ticket_item_rma_status);
            rma = itemView.findViewById(R.id.ticket_item_rma);
        }
    }
}
