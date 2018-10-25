package com.example.colors2web.zummix_app.Adapter.Ticket_Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TicketDetailsAdapter extends RecyclerView.Adapter<TicketDetailsAdapter.DetailsHolder> {
    @NonNull
    @Override
    public TicketDetailsAdapter.DetailsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketDetailsAdapter.DetailsHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class DetailsHolder extends RecyclerView.ViewHolder {
        public DetailsHolder(View itemView) {
            super(itemView);
        }
    }
}
