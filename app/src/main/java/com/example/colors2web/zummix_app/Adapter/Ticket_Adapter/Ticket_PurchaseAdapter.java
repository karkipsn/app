package com.example.colors2web.zummix_app.Adapter.Ticket_Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.PurchasedProduct;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class Ticket_PurchaseAdapter extends RecyclerView.Adapter<Ticket_PurchaseAdapter.PHolder> {

    List<PurchasedProduct>ProductList;
    Context mContext;

    public Ticket_PurchaseAdapter(List<PurchasedProduct> productList, Context mContext) {
        ProductList = productList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public PHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ticket_purchased_products,parent,false);
        return new PHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PHolder holder, int position) {
        PurchasedProduct product = ProductList.get(position);
        holder.pname.setText(product.getProductName());
        holder.psku.setText(product.getProductSkuNumber());
        holder.quantity.setText(String.valueOf(product.getOrderedQuantity()));
        holder.pprice.setText(product.getProductPrice());
        holder.total.setText(String.valueOf(product.getItemTotalPrice()));
    }

    @Override
    public int getItemCount() {
        return ProductList.size();
    }

    public void updateAnswers(ArrayList<PurchasedProduct> logs1) {
        ProductList = logs1;
        notifyDataSetChanged();
    }

    public class PHolder extends RecyclerView.ViewHolder {

        TextView pname,psku,quantity,pprice,total;
        ImageView pimage;
        public PHolder(View itemView) {
            super(itemView);

            pname =itemView.findViewById(R.id.tpp_product_name);
            psku =itemView.findViewById(R.id.tpp_product_sku);
            quantity =itemView.findViewById(R.id.tpp_quantity);
            pprice =itemView.findViewById(R.id.tpp_product_price);
            total =itemView.findViewById(R.id.tpp_total);

        }
    }
}
