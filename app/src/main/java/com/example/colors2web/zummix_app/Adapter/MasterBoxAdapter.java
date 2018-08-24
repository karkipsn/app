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
import com.example.colors2web.zummix_app.POJO.Order2POJO.MasterBox;
import com.example.colors2web.zummix_app.R;

import java.util.List;


    public class MasterBoxAdapter extends RecyclerView.Adapter<MasterBoxAdapter.MasterBoxHolder> {
        List<MasterBox> mboxList;
        private Context mContext;


        public MasterBoxAdapter(List<MasterBox> mList) {
            this.mboxList =mList;
        }

        @NonNull

        @Override
        public MasterBoxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.actitvity_adapter_boxes,parent,false);
            return new MasterBoxHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MasterBoxHolder holder, int position) {
            MasterBox box = mboxList.get(position);

            holder.box_no.setText(box.getBoxNumber());
            holder.created.setText(box.getCreatedAt());

            String url = box.getBarcodeFileName();
            String url1 =" http://5840c423.ngrok.io/zummix-api/public/barcodes/";

            Glide.with(mContext).load(url1+url).into(holder.barcode);
            Log.d("url",url1+url);

//        Picasso.get().load(url1+url).into(holder.barcode);

        }

        @Override
        public int getItemCount() {
            return mboxList.size();
        }

        public void updateAnswers(List<MasterBox> List) {
            mboxList =List;
            notifyDataSetChanged();

        }

        public class MasterBoxHolder extends RecyclerView.ViewHolder {
            TextView box_no, created;
            ImageView barcode;
            public MasterBoxHolder(View itemView) {
                super(itemView);

                box_no=itemView.findViewById(R.id.rv_boxno);
                created=itemView.findViewById(R.id.rv_created_at);
                barcode=itemView.findViewById(R.id.box_imageview);

            }
        }
    }


