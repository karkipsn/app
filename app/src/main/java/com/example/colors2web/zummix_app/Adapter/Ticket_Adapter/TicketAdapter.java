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
import com.example.colors2web.zummix_app.POJO.Tickets.Tickets;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketHolder> {

    Context mContext;
    List<Tickets> TList;

    public TicketAdapter(Context mContext, List<Tickets> TList) {
        this.mContext = mContext;
        this.TList = TList;
    }

    @NonNull
    @Override
    public TicketAdapter.TicketHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_tickets_adapter, parent, false);
        return new TicketHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.TicketHolder holder, int position) {

        Tickets tickets = TList.get(position);

        holder.store.setText(tickets.getMstore_name());
        holder.created.setText(tickets.getMcreated_at());
        holder.customer.setText(tickets.getMcustomer_name());
        holder.order.setText(tickets.getMorder());

        holder.ticket.setText(tickets.getMticket_number());
        holder.ticket.setTextColor(Color.BLUE);

        holder.updated.setText(tickets.getMupdated_at());
        holder.status.setText(tickets.getMticket_status());

        final String storeid = tickets.getMstore_id();
        final String supportid = tickets.getMid();

        holder.ticket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, TicketDetailsActivity.class);
                intent.putExtra("storeid",storeid);
                intent.putExtra("supportid",supportid);
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return TList.size();
    }

    public void updateAnswers(List<Tickets> cList) {
        TList = cList;
        notifyDataSetChanged();
    }

    public class TicketHolder extends RecyclerView.ViewHolder {
        TextView store, created, customer, order, ticket, updated, status;

        public TicketHolder(View itemView) {
            super(itemView);

            store = itemView.findViewById(R.id.ticket_sname);
            created = itemView.findViewById(R.id.ticket_created_at);
            customer = itemView.findViewById(R.id.ticket_cname);
            order = itemView.findViewById(R.id.ticket_ticket);
            ticket = itemView.findViewById(R.id.ticket_order);
            updated = itemView.findViewById(R.id.ticket_updated_at);
            status = itemView.findViewById(R.id.ticket_status);


        }
    }
}
