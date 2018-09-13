package com.example.colors2web.zummix_app.Adapter.ProductAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.ProductSearch.InventoryLogs;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class ProductLogsAdapter extends RecyclerView.Adapter<ProductLogsAdapter.LogsHolder>{

    Context mContext;
    List<InventoryLogs> LogsList ;

    public ProductLogsAdapter(Context mContext, List<InventoryLogs> logsList) {
        this.mContext = mContext;
        LogsList = logsList;
    }


    @NonNull
    @Override
    public ProductLogsAdapter.LogsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.frag_inventry_logs,parent,false);
        return new LogsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductLogsAdapter.LogsHolder holder, int position) {
        InventoryLogs logs = LogsList.get(position);

        holder.id.setText(String.valueOf(logs.getId()));
        holder.e_type.setText(logs.getEventType());
        holder.e_date.setText(logs.getCreatedAt());
        holder.from.setText(logs.getFromLocation());
        holder.to.setText(logs.getToLocation());
        if(logs.getCurrentQty()!=null && logs.getUpdateQty()!=null){

        Long current =logs.getCurrentQty();
        Long up =logs.getUpdateQty();
        Long tot = current+up;
        holder.present.setText(String.valueOf(current));
        holder.moved.setText(String.valueOf(up));
        holder.total.setText(String.valueOf(tot));
        }
        holder.pallet.setText(String.valueOf(logs.getOldPalletNumber()));
        holder.comment.setText(logs.getComment());
        holder.newpallet.setText(String.valueOf(logs.getNewPalletNumber()));


    }

    @Override
    public int getItemCount() {
        return LogsList.size();
    }

    public void updateAnswers(ArrayList<InventoryLogs> logList) {
        LogsList = logList;
        notifyDataSetChanged();
    }

    public class LogsHolder extends RecyclerView.ViewHolder {

        TextView id,e_type,e_date,from,to,present,moved,total,pallet,comment,newpallet;
        public LogsHolder(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.frag_logs_id);
            e_type = itemView.findViewById(R.id.frag_logs_event_type);
            e_date = itemView.findViewById(R.id.frag_logs_event_date);
            from = itemView.findViewById(R.id.frag_logs_from);
            to = itemView.findViewById(R.id.frag_logs_to);
            present = itemView.findViewById(R.id.frag_logs_present_qty);
            moved = itemView.findViewById(R.id.frag_logs_moved_qty);
            total = itemView.findViewById(R.id.frag_logs_final_qty);
            pallet = itemView.findViewById(R.id.frag_logs_pallet);
            comment = itemView.findViewById(R.id.frag_logs_comment);
            newpallet = itemView.findViewById(R.id.frag_logs_new_pallet);
        }
    }
}
