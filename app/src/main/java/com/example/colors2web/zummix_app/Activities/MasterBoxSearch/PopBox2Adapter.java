package com.example.colors2web.zummix_app.Activities.MasterBoxSearch;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.LineItems;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class PopBox2Adapter extends RecyclerView.Adapter<PopBox2Adapter.Pop2Holder> {

    Context mContext;
    List<LineItems> LineList;

    public PopBox2Adapter(Context mContext, List<LineItems> lineList) {
        this.mContext = mContext;
        LineList = lineList;
    }

    @NonNull
    @Override
    public PopBox2Adapter.Pop2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mbox_modal_line_adapter, parent, false);
        return new Pop2Holder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PopBox2Adapter.Pop2Holder holder, int position) {

        LineItems items = LineList.get(position);
        holder.sku.setText(items.getItemSku());
        holder.name.setText(items.getItemName());
        holder.qty.setText(items.getBoxQuantity());
    }

    @Override
    public int getItemCount() {
        return LineList.size();
    }

    public void updateAnswers(List<LineItems> lineList) {
//        TO check the duplicated data try only
//        lineList.clear();  //but it clears all the data
        LineList =lineList;
        notifyDataSetChanged();
    }

    public void updateAnswers2() {
        LineList.clear();
        notifyDataSetChanged();
    }

    public class Pop2Holder extends RecyclerView.ViewHolder {

        TextView sku,name,qty;

        public Pop2Holder(View itemView) {
            super(itemView);
            sku = itemView.findViewById(R.id.pop_box_adp2_sku);
            name = itemView.findViewById(R.id.pop_box_adp2_name);
            qty = itemView.findViewById(R.id.pop_box_adp2_quantity);
        }
    }
}
