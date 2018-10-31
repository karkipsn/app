package com.example.colors2web.zummix_app.Adapter.ReportsAdapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.PostSearch.Boxes;
import com.example.colors2web.zummix_app.POJO.PostSearch.CustomerItems;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.MergeBCI;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class Pick_Velocity_BoxAdapter extends RecyclerView.Adapter<Pick_Velocity_BoxAdapter.VelociyHolder> {

    List<Boxes> BoxList;
    List<CustomerItems> ItemsList;
    Context mcontext;
    List<MergeBCI>CombList;

//    public Pick_Velocity_BoxAdapter(Context mcontext,List<CustomerItems> cusList, List<Boxes> boxList) {
//        this.mcontext = mcontext;
//        ItemsList = cusList;
//        BoxList = boxList;
//    }

    public Pick_Velocity_BoxAdapter(List<MergeBCI> combList) {
        CombList = combList;
    }


//    public Pick_Velocity_BoxAdapter(List<Boxes> boxList, List<CustomerItems> itemsList) {
//        BoxList = boxList;
//        ItemsList = itemsList;
//    }

    @NonNull
    @Override
    public Pick_Velocity_BoxAdapter.VelociyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_post_pick_velocity_box_adapter, parent, false);
        return new VelociyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pick_Velocity_BoxAdapter.VelociyHolder holder, int position) {

        MergeBCI ItmList = CombList.get(position);

        holder.sku.setText(ItmList.getCi().getItemSkuNumber());

        holder.sku.setText(ItmList.getCi().getItemSkuNumber());
        holder.name.setText(ItmList.getCi().getItemName());
        holder.replenish.setText(ItmList.getCi().getReplenish());
        holder.pick.setText(String.valueOf(ItmList.getCi().getPick()));

        String loc = ItmList.getCi().getWarehouseName() + " " + ItmList.getCi().getPickAisleName() + "" +
                ItmList.getCi().getSectionNumber() + "" + ItmList.getCi().getSectionLevel() + "" + ItmList.getCi().getPickBin();

        String qoh1 = ItmList.getCi().getReplenish();
        Long pick1 = ItmList.getCi().getPick();

        if (qoh1 != null && pick1!=null ) {
            Long qohh = pick1 + Long.valueOf(qoh1);
            holder.qoh.setText(String.valueOf(qohh));

        }else if (qoh1 != null && pick1 == null ) {
            holder.qoh.setText(qoh1);
        }
        else{
            holder.qoh.setText(String.valueOf(pick1));
        }

        holder.location.setText(loc);

       if(ItmList.getBi()!= null){

        holder.total_picked.setText(ItmList.getBi().getTotalPickedQty());
        holder.pdate.setText(ItmList.getBi().getCreatedAt());
        holder.unique.setText(String.valueOf(ItmList.getBi().getUniqueOrders()));}
        else {
           holder.total_picked.setText("");
           holder.pdate.setText("");
           holder.unique.setText("");


       }

//
//        holder.sku.setText(ItmList.getItemSkuNumber());
//        holder.name.setText(ItmList.getItemName());
//        holder.replenish.setText(ItmList.getReplenish());
//        holder.pick.setText(String.valueOf(ItmList.getPick()));
//
//        String loc = ItmList.getWarehouseName() + " " + ItmList.getPickAisleName() + "" +
//                ItmList.getSectionNumber() + "" + ItmList.getSectionLevel() + "" + ItmList.getPickBin();
//
//        String qoh1 = ItmList.getReplenish();
//        Long pick1 = ItmList.getPick();
//
//        if (qoh1 != null && pick1!=null ) {
//            Long qohh = pick1 + Long.valueOf(qoh1);
//            holder.qoh.setText(String.valueOf(qohh));
//
//        }else if (qoh1 != null && pick1 == null ) {
//            holder.qoh.setText(qoh1);
//        }
//        else{
//            holder.qoh.setText(String.valueOf(pick1));
//        }
//
//        holder.location.setText(loc);
//
//        Boxes blist = BoxList.get(position);
//        if(ItmList.getItemSkuNumber().equals( blist.getItemSku())){
//
//            holder.total_picked.setText(blist.getTotalPickedQty());
//            holder.pdate.setText(blist.getCreatedAt());
//            holder.unique.setText(String.valueOf(blist.getUniqueOrders()));}
//        else {
//            holder.total_picked.setText("");
//            holder.pdate.setText("");
//            holder.unique.setText("");
//
//        }
    }

    public void updateAnswers( List<Boxes> BItem,List<CustomerItems> CItem) {
        BoxList = BItem;
        ItemsList = CItem;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return CombList.size();
    }


    public void updateAnswers(List<MergeBCI> newarray) {
        CombList =newarray;
        notifyDataSetChanged();
    }

    public class VelociyHolder extends RecyclerView.ViewHolder {
        TextView sku, name, replenish, pick, location, qoh, total_picked, pdate, unique;

        public VelociyHolder(View itemView) {
            super(itemView);
            sku = itemView.findViewById(R.id.adpt_pick_sku);
            name = itemView.findViewById(R.id.adpt_pick_item_name);
            replenish = itemView.findViewById(R.id.adpt_pick_replnish);
            pick = itemView.findViewById(R.id.adpt_pick_pick);
            location = itemView.findViewById(R.id.adptr_pick_pick_location);
            qoh = itemView.findViewById(R.id.adptr_pick_qoh);
            total_picked = itemView.findViewById(R.id.adptr_pick_totalpick);
            pdate = itemView.findViewById(R.id.adptr_pick_lastpicked);
            unique = itemView.findViewById(R.id.adptr_pick_uniqueord);
        }
    }
}

