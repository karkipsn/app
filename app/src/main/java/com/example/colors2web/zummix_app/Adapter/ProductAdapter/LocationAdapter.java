package com.example.colors2web.zummix_app.Adapter.ProductAdapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.ProductSearch.DrShipmentItemLocations;
import com.example.colors2web.zummix_app.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationHolder> {

    List<DrShipmentItemLocations> LocationList ;
    Context mContext;

    public LocationAdapter(List<DrShipmentItemLocations> locationList, Context mContext) {
        LocationList = locationList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_product_location,parent,false);
        return new LocationHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder holder, int position) {

        DrShipmentItemLocations dr = LocationList.get(position);

        String loc = dr.getWarehouseName()+" "+ dr.getAisleName()+" "+ dr.getBinNumber()+" "+dr.getBinLevel()+" "+dr.getBin();
        holder.location.setText(loc);
        holder.pallet.setText(dr.getPalletCount());
        holder.quantity.setText(String.valueOf(dr.getItemCount()));

    }

    @Override
    public int getItemCount() {
        return LocationList.size();
    }

    public void updateAnswers(ArrayList<DrShipmentItemLocations> ship) {
        LocationList =ship;
    }


    public class LocationHolder extends RecyclerView.ViewHolder {
        TextView location, pallet, quantity;

        public LocationHolder(View itemView) {

            super(itemView);
            location = itemView.findViewById(R.id.frag_pick_location);
            pallet = itemView.findViewById(R.id.frag_pick_pallet);
            quantity = itemView.findViewById(R.id.frag_pick_quantity);
        }
    }
}
