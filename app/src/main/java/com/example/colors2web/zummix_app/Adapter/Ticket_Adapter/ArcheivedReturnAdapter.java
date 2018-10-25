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

public class ArcheivedReturnAdapter extends RecyclerView.Adapter<ArcheivedReturnAdapter.ArcheiveHolder> {

    private Context mContext;
    List<ReturnTicket>ReturnList;

    public ArcheivedReturnAdapter(Context mContext, List<ReturnTicket> REturnList) {
        this.mContext = mContext;
        this.ReturnList = REturnList;
    }

    @NonNull
    @Override
    public ArcheivedReturnAdapter.ArcheiveHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_return_adapter,parent,false);
        return new ArcheiveHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArcheivedReturnAdapter.ArcheiveHolder holder, int position) {

        ReturnTicket rticket = ReturnList.get(position);

        holder.store.setText(rticket.getStoreName());
        holder.created.setText(rticket.getUpdatedAt());
        holder.customer.setText(rticket.getCustomerName());
        holder.ticket.setText(rticket.getTicketNumber());
        holder.sales.setText(rticket.getSaleNumber());
        holder.quantity.setText(String.valueOf(rticket.getQuantity()));
        holder.reason.setText(rticket.getReason());

        String statuss = rticket.getStatus();
        switch (statuss){
            case "1":
                holder.action.setText("Return Cancelled");
                holder.action.setBackground(holder.action.getResources().getDrawable(R.drawable.flag_gardient));

                break;

            case "2":
                holder.action.setText("Return Approved");
                holder.action.setBackground(holder.action.getResources().getDrawable(R.drawable.flag_gradient_red));

                break;
        }

        final String store_id = String.valueOf(rticket.getStoreId());
        final String support_id = String.valueOf(rticket.getId());

        holder.ticket.setTextColor(Color.BLUE);
        holder.ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, TicketDetailsActivity.class);
                intent.putExtra("storeid", store_id);
                intent.putExtra("supportid", support_id);
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
    }

    public class ArcheiveHolder extends RecyclerView.ViewHolder {
        TextView store,created,customer,ticket,sales,quantity,reason,action;

        public ArcheiveHolder(View itemView) {
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
