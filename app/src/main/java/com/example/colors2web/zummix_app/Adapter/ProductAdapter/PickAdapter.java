package com.example.colors2web.zummix_app.Adapter.ProductAdapter;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class PickAdapter extends RecyclerView.Adapter<PickAdapter.PickHolderView> {

    Activity mContext;

    @NonNull
    @Override
    public PickAdapter.PickHolderView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PickAdapter.PickHolderView holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PickHolderView extends RecyclerView.ViewHolder {
        public PickHolderView(View itemView) {
            super(itemView);
        }
    }
}
