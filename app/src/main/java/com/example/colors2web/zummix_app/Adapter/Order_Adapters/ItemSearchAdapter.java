package com.example.colors2web.zummix_app.Adapter.Order_Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.ProductSearch.CustomerItem;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class ItemSearchAdapter extends RecyclerView.Adapter<ItemSearchAdapter.ItemHolder>{
    List<CustomerItem> itemList;
    Context mcontext;
    @NonNull
    @Override
    public ItemSearchAdapter.ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item_search, parent, false);

        return new ItemHolder(view);
    }

    public ItemSearchAdapter(Context mcontext, List<CustomerItem> itemList) {
        this.mcontext = mcontext;
        this.itemList = itemList;
    }

    @Override
    public void onBindViewHolder(@NonNull ItemSearchAdapter.ItemHolder holder, int position) {
        CustomerItem item = itemList.get(position);

        holder.cus_name.setText(item.getCompanyName());
        holder.sku.setText(item.getItemSkuNumber());
        holder.name.setText(item.getItemName());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void updateAnswers(List<CustomerItem> itm) {
        itemList =itm;
        notifyDataSetChanged();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        TextView sku;
        TextView name;
        TextView cus_name;

        public ItemHolder(View itemView) {
            super(itemView);
            sku = itemView.findViewById(R.id.itm_ad_view2);
            name = itemView.findViewById(R.id.itm_ad_view3);
            cus_name = itemView.findViewById(R.id.itm_ad_view1);
        }
    }
}
