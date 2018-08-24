package com.example.colors2web.zummix_app.Activities.MasterBoxSearch;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBox;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class MBoxAdapter extends RecyclerView.Adapter<MBoxAdapter.MBoxHolder> {
    List<MasterBox> MList;

    public MBoxAdapter(List<MasterBox> MList) {
        this.MList = MList;
    }

    @NonNull
    @Override
    public MBoxAdapter.MBoxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.box_masterbox, parent, false);
        return new MBoxHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MBoxAdapter.MBoxHolder holder, int position) {
        MasterBox masterBox = MList.get(position);

        holder.bid.setText(masterBox.getOrderId());
        holder.mbarno.setText(masterBox.getBarcodeNumber());
        holder.mboxno.setText(masterBox.getBoxNumber());
        holder.mmboxno.setText(masterBox.getMasterBoxCode());
        holder.mtrcode.setText(masterBox.getMasterBoxTrackingCode());
        holder.mcusname.setText(masterBox.getCompanyName());
        holder.memail.setText(masterBox.getCompanyEmail());


    }

    @Override
    public int getItemCount() {
        return MList.size();
    }

    public void updateAnswers(ArrayList<MasterBox> boxes) {
        MList=boxes;
        notifyDataSetChanged();
    }

    public class MBoxHolder extends RecyclerView.ViewHolder {
        TextView bid, mbarno, mboxno, mmboxno, mtrcode, mcusname, memail;

        public MBoxHolder(View itemView) {

            super(itemView);
            bid = itemView.findViewById(R.id.box_box_oid);
            mbarno = itemView.findViewById(R.id.box_box_bar_no);
            mboxno = itemView.findViewById(R.id.box_box_no);
            mmboxno = itemView.findViewById(R.id.box_mbox_no);
            mtrcode = itemView.findViewById(R.id.box_track_code);
            mcusname = itemView.findViewById(R.id.box_cus_name);
            memail = itemView.findViewById(R.id.box_email);
        }
    }
}
