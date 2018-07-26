package com.example.colors2web.zummix_app.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.Order2POJO.ItemDetail;
import com.example.colors2web.zummix_app.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LineItemsAdapter extends RecyclerView.Adapter<LineItemsAdapter.LineHolder> {

    List<ItemDetail> itemList;
    @NonNull
    @Override
    public LineItemsAdapter.LineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_adapter_line_items, parent, false);

        return new LineHolder(view);
    }

    public LineItemsAdapter(List<ItemDetail> itemList) {
        this.itemList = itemList;
    }

    @Override
    public void onBindViewHolder(@NonNull LineItemsAdapter.LineHolder holder, int position) {

        ItemDetail item = itemList.get(position);

        holder.sku.setText(item.getItemSku());
        holder.name.setText(item.getItemName());
        holder.quantity.setText(String.valueOf(item.getItemQuantity()));

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateAnswers(List<ItemDetail> itm) {
        itemList =itm;
        notifyDataSetChanged();
    }

    public class LineHolder extends RecyclerView.ViewHolder {
        TextView sku;
        TextView name;
        TextView quantity;

        public LineHolder(View itemView) {
            super(itemView);
            sku = itemView.findViewById(R.id.rv_item_sku);
            name = itemView.findViewById(R.id.rv_item_name);
            quantity = itemView.findViewById(R.id.rv_item_quantity);

//            ButterKnife.bind(itemView);
            //itemView sanga bind garna parxa so

        }
    }
}
