package com.example.colors2web.zummix_app.Adapter.Ticket_Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.Activities.TicketActivity.TicketDetailsActivity;
import com.example.colors2web.zummix_app.POJO.Tickets.ReturnTicket;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class ReturnAdapter extends RecyclerView.Adapter<ReturnAdapter.ReturnHolder> {

    Context mContext;
    List<ReturnTicket> ReturnList;

    public ReturnAdapter(Context mContext, List<ReturnTicket> returnList) {
        this.mContext = mContext;
        ReturnList = returnList;
    }

    @NonNull
    @Override
    public ReturnAdapter.ReturnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_return_adapter, parent, false);
        return new ReturnHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReturnAdapter.ReturnHolder holder, int position) {
        ReturnTicket rticket = ReturnList.get(position);

        holder.store.setText(rticket.getStoreName());
        holder.created.setText(rticket.getUpdatedAt());
        holder.customer.setText(rticket.getCustomerName());
        holder.ticket.setText(rticket.getTicketNumber());
        holder.sales.setText(rticket.getSaleNumber());
        holder.quantity.setText(String.valueOf(rticket.getQuantity()));
        holder.reason.setText(rticket.getReason());

        String statuss = rticket.getStatus();
        switch (statuss) {
            case "0":
                holder.action.setText("Open");
                holder.action.setBackground(holder.action.getResources().getDrawable(R.drawable.flag_gardient));
                break;

        }

        final String storeid = String.valueOf(rticket.getStoreId());
        final String supportid = String.valueOf(rticket.getId());


        holder.ticket.setTextColor(Color.BLUE);
        holder.ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, TicketDetailsActivity.class);
                intent.putExtra("storeid", storeid);
                intent.putExtra("supportid", supportid);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return ReturnList.size();
    }

    public void updateAnswers(ArrayList<ReturnTicket> logs1) {
        ReturnList = logs1;
        notifyDataSetChanged();
    }

    public class ReturnHolder extends RecyclerView.ViewHolder {
        TextView store, created, customer, ticket, sales, quantity, reason, action;

        public ReturnHolder(View itemView) {
            super(itemView);

            store = itemView.findViewById(R.id.returnticket_sname);
            created = itemView.findViewById(R.id.returnticket_created_at);
            customer = itemView.findViewById(R.id.returnticket_cname);
            ticket = itemView.findViewById(R.id.returnticket_ticket);
            sales = itemView.findViewById(R.id.returnticket_sale);
            quantity = itemView.findViewById(R.id.returnticket_quantity);
            reason = itemView.findViewById(R.id.returnticket_reason_to_cancel);
            action = itemView.findViewById(R.id.returnticket_action);
        }
    }
}
