package com.example.colors2web.zummix_app.Activities.PostActivity.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.PostSearch.Orders;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class Sales_History_Adapter extends RecyclerView.Adapter<Sales_History_Adapter.SalesHolder> {
    List<Orders> OrderList;

    public Sales_History_Adapter(List<Orders> orderList) {
        OrderList = orderList;
    }

    @NonNull
    @Override
    public Sales_History_Adapter.SalesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_post_sales_adapter,parent,false);
        return new SalesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Sales_History_Adapter.SalesHolder holder, int position) {
        Orders orders =OrderList.get(position);
        holder.sku.setText(orders.getItemSku());
        holder.item_name.setText(orders.getItemName());
        holder.customer.setText(orders.getCustomerName());
        holder.order.setText(orders.getOrderNumber());
        holder.order_status.setText(orders.getOrderStatus());
        holder.odate.setText(orders.getOrderDate());
        holder.iqty.setText(String.valueOf(orders.getItemQuantity()));
        holder.unbox_total.setText(String .valueOf(orders.getUnboxTotal()));
        holder.refund.setText(orders.getRefundedQuantity());

    }
     public void updateAnswers(List<Orders>OList){
        OrderList = OList;
        notifyDataSetChanged();

    }
    @Override
    public int getItemCount() {
        return OrderList.size();
    }

    public class SalesHolder extends RecyclerView.ViewHolder {
        TextView sku,item_name,customer,order,order_status,odate,iqty,unbox_total,refund;

        public SalesHolder(View itemView) {
            super(itemView);
            sku = itemView.findViewById(R.id.adpt_sales_sku);
            item_name = itemView.findViewById(R.id.adpt_sales_itemname);
            customer = itemView.findViewById(R.id.adpt_sales_customer);
            order = itemView.findViewById(R.id.adpt_sales_order);
            order_status = itemView.findViewById(R.id.adptr_sales_ostatus);
            odate = itemView.findViewById(R.id.adptr_sales_odate);
            iqty = itemView.findViewById(R.id.adptr_sales_itmqty);
            unbox_total = itemView.findViewById(R.id.adptr_sales_unbox);
            refund = itemView.findViewById(R.id.adptr_sales_refund_qty);
        }
    }
}
