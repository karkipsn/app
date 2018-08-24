package com.example.colors2web.zummix_app.Activities.CityBins.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.CityBins.CreateCityBinsActivity;
import com.example.colors2web.zummix_app.Activities.CityBins.UpdateCityBinsActivity;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBins;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class BinAdapter extends RecyclerView.Adapter<BinAdapter.BinViewHolder> {
    List<CityBins> BinList;
    Context mcontext;

    public BinAdapter(List<CityBins> binList, Context mcontext) {
        BinList = binList;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public BinAdapter.BinViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_bin_bin,parent,false);
        return new BinViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BinAdapter.BinViewHolder holder, int position) {
        final CityBins bins = BinList.get(position);
        holder.city.setText(bins.getCity());
        holder.name.setText(bins.getCompanyName());
        holder.created.setText(bins.getCreatedAt());

        holder.up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customer_id = String.valueOf(bins.getCustomerId());
                String bin_id = String.valueOf(bins.getId());

                Intent intent = new Intent(mcontext, UpdateCityBinsActivity.class);
                intent.putExtra("customer_id",customer_id);
                intent.putExtra("bin_id",bin_id);
                Log.d("bin_id_home",bin_id);
                mcontext.startActivity(intent);
            }
        });
        holder.master.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mcontext,"Further Action Remaining",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return BinList.size();
    }

    public void updateAnswers(List<CityBins> binList) {
        BinList = binList;
        notifyDataSetChanged();
    }

    public class BinViewHolder extends RecyclerView.ViewHolder {
        TextView city,name,created;
        Button up,master;
        public BinViewHolder(View itemView) {
            super(itemView);
            city =itemView.findViewById(R.id.bin_bin_citybin);
            name =itemView.findViewById(R.id.bin_bin_storename);
            created =itemView.findViewById(R.id.bin_bin_created);
            up =itemView.findViewById(R.id.bin_bin_bin_update);
            master =itemView.findViewById(R.id.bin_bin_master);
        }
    }
}
