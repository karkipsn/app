package com.example.colors2web.zummix_app.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Box;
import com.example.colors2web.zummix_app.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BoxesAdapter extends RecyclerView.Adapter<BoxesAdapter.BoxesHolder> {
    List<Box> boxList;
    private Context mContext;

    public BoxesAdapter(Context mContext,List<Box> boxList) {
        this.mContext = mContext;
        this.boxList = boxList;
    }

    @NonNull

    @Override
    public BoxesAdapter.BoxesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actitvity_adapter_boxes,parent,false);
        return new BoxesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxesAdapter.BoxesHolder holder, int position) {
        Box box = boxList.get(position);

        holder.box_no.setText(box.getBoxNumber());
        holder.created.setText(box.getCreatedAt());
        holder.tracking_code.setText(box.getTrackingCode());

        String url = box.getBarcodeFileName();
        String url1 =" http://5840c423.ngrok.io/zummix-api/public/barcodes/";

        Glide.with(mContext).load(url1+url).into(holder.barcode);
        Log.d("url",url1+url);

//        Picasso.get().load(url1+url).into(holder.barcode);

    }

    @Override
    public int getItemCount() {
        return boxList.size();
    }

    public void updateAnswers(List<Box> List) {
        boxList =List;
        notifyDataSetChanged();

    }

    public class BoxesHolder extends RecyclerView.ViewHolder {
        TextView box_no, created,tracking_code;
        ImageView barcode;
        public BoxesHolder(View itemView) {
            super(itemView);

            box_no=itemView.findViewById(R.id.rv_boxno);
            created=itemView.findViewById(R.id.rv_created_at);
            tracking_code=itemView.findViewById(R.id.rv_tracking_code);
            barcode=itemView.findViewById(R.id.box_imageview);

        }
    }
}
