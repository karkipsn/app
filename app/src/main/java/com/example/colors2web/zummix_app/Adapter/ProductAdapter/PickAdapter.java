package com.example.colors2web.zummix_app.Adapter.ProductAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.R;

public class PickAdapter extends RecyclerView.Adapter<PickAdapter.PickHolderView> {

    Activity mContext;

    @NonNull
    @Override
    public PickAdapter.PickHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.fragment_product_pick, parent, false);
        return new PickHolderView(view);
    }


    @Override
    public void onBindViewHolder(@NonNull PickAdapter.PickHolderView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PickHolderView extends RecyclerView.ViewHolder {

        TextView pick,location;
        public PickHolderView(View itemView)
        {
            super(itemView);
            pick =itemView.findViewById(R.id.adpt_pick_pick);
            location =itemView.findViewById(R.id.adpt_pick_location);
        }
    }
}
