package com.example.colors2web.zummix_app.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.ProblemSKU.UOM;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class UOMAdapter extends RecyclerView.Adapter<UOMAdapter.UOMHolder> {

    Context mContext;
    List<UOM> UOMList;

    public UOMAdapter(Context mContext, List<UOM> UOMList) {
        this.mContext = mContext;
        this.UOMList = UOMList;
    }

    @NonNull
    @Override
    public UOMAdapter.UOMHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_uom_adapter,parent,false);
        return new UOMHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UOMAdapter.UOMHolder holder, int position) {
        UOM u = UOMList.get(position);

        holder.uom.setText(u.getUnit());

    }

    @Override
    public int getItemCount() {
        return UOMList.size();
    }

    public void updateAdapter(List<UOM> uList) {
        UOMList = uList;
        notifyDataSetChanged();
    }

    public void resetdata() {
        UOMList.clear();
        notifyDataSetChanged();
    }

    public class UOMHolder extends RecyclerView.ViewHolder {
        TextView uom;

        public UOMHolder(View itemView) {
            super(itemView);

            uom = itemView.findViewById(R.id.uom_uom);
        }
    }
}
