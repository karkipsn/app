package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.customers.Customers;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class ParentAdapter_customer extends RecyclerView.Adapter<ParentAdapter_customer.ParentHolder> {
    Context mContext;
    List<Customers> Plist;

    public ParentAdapter_customer(Context context,List<Customers> plist)
    {
        this.mContext = context;
        this.Plist = plist;
    }

    @NonNull
    @Override
    public ParentAdapter_customer.ParentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.customer_parentid_child, parent, false);
        return new ParentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter_customer.ParentHolder holder, int position) {
        Customers customers = Plist.get(position);

        String cname = customers.getCompanyName();
        String cemail = customers.getCompanyEmail();
        String cadd = customers.getCompanyAddress1() + "|" + customers.getCompanyCity() + "|"
                + customers.getCompanyState() + "|" + customers.getCompanyZip();
        String coname = customers.getContactFname() + customers.getContactMname() + customers.getContactLname();
        String coadd = customers.getContactEmail();

        holder.t1.setText(cname);
        holder.t2.setText(cemail);
        holder.t3.setText(cadd);
        holder.t4.setText(coname);
        holder.t5.setText(coadd);
        final String id = String.valueOf(customers.getId());

        holder.t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,ByCustomerId.class);
                intent.putExtra("cid",id);
                mContext.startActivity(intent);
                
            }
        });

    }

    @Override
    public int getItemCount() {
        return Plist.size();
    }

    public void updateAnswers(List<Customers> cList) {
        Plist =cList;
        notifyDataSetChanged();
    }

    public class ParentHolder extends RecyclerView.ViewHolder {
        TextView t1, t2, t3, t4, t5;

        public ParentHolder(View itemView) {
            super(itemView);

            t1 = itemView.findViewById(R.id.cus_parent_cname);
            t3 = itemView.findViewById(R.id.cus_parent_cadd);
            t2 = itemView.findViewById(R.id.cus_parent_cemail);
            t4 = itemView.findViewById(R.id.cus_parent_coname);
            t5 = itemView.findViewById(R.id.cus_parent_coemail);
        }
    }
}
