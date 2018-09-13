package com.example.colors2web.zummix_app.Adapter.BinsAdapter;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.Activities.CityBins.BinBinActivity;
import com.example.colors2web.zummix_app.POJO.CityBins.CityBins;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    Activity mActivity;List<CityBins> BinList;


    public HomeAdapter(Activity mActivity, List<CityBins> binList) {
        this.mActivity = mActivity;
        BinList = binList;
    }
    
//    public HomeAdapter(List<CityBins> binList, Context mActivity) {
//        BinList = binList;
//        this.mActivity = mActivity;
//    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_homebin_adapter, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        final CityBins bins = BinList.get(position);

        holder.name.setText(bins.getCompanyName());
        holder.quantity.setText(String.valueOf(bins.getmNoOfBins()));

//        holder.bins.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("bins","Further to DO");
//
//            }
//        });
        holder.bin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String customer_id = String.valueOf(bins.getCustomerId());
                Intent intent = new Intent(mActivity, BinBinActivity.class);
                intent.putExtra("customer_id",customer_id);
                Log.d("customer_id",customer_id);
                mActivity.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return BinList.size();
    }

    public void updateAnswers(List<CityBins> binList) {
        BinList =binList;
        notifyDataSetChanged();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView name, quantity,bin;
//        Button bins;

        public HomeViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.bin_store);
            quantity = itemView.findViewById(R.id.bin_binquantity);
            bin = itemView.findViewById(R.id.bin_bin);
        }
    }


}
