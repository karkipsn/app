package com.example.colors2web.zummix_app.Adapter.ReportsAdapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.SpecialPOJO.MergeBCI;
import com.example.colors2web.zummix_app.R;

import java.util.List;

public class Pick_Velocity_BoxAdapter extends RecyclerView.Adapter<Pick_Velocity_BoxAdapter.VelociyHolder> {

//    List<Boxes> BoxList;
//    List<CustomerItems> ItemsList;
    List<MergeBCI>CombList;

//    public Pick_Velocity_BoxAdapter(Context mcontext, List<Boxes> boxList) {
//        this.mcontext = mcontext;
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
        if (qoh1 != null) {
            Long qohh = ItmList.getCi().getPick() + Long.valueOf(qoh1);
            holder.qoh.setText(String.valueOf(qohh));
        } else {

            Long qohh = ItmList.getCi().getPick();
            holder.qoh.setText(String.valueOf(qohh));
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


//                holder.sku.setText(ItmList.getItemSkuNumber());
//        holder.name.setText(ItmList.getItemName());
//        holder.replenish.setText(ItmList.getReplenish());
//        holder.pick.setText(String.valueOf(ItmList.getPick()));
//
//        String loc = ItmList.getWarehouseName() + " " + ItmList.getPickAisleName() + "" +
//                ItmList.getSectionNumber() + "" + ItmList.getSectionLevel() + "" + ItmList.getPickBin();
//
//        String qoh1 = ItmList.getReplenish();
//        if (qoh1 != null) {
//            Long qohh = ItmList.getPick() + Long.valueOf(qoh1);
//            holder.qoh.setText(String.valueOf(qohh));
//        } else {
//
//            Long qohh = ItmList.getPick();
//            holder.qoh.setText(String.valueOf(qohh));
//        }
//
//        holder.location.setText(loc);
//        Log.d("position",String.valueOf(position));
//
//        for(int i =0;i<position;i++){
//            Boxes boxList = BoxList.get(i);
//
//        if(ItmList.getItemSkuNumber().equals(BoxList.get(i).getItemSku())){
//
//            holder.total_picked.setText(boxList.getTotalPickedQty());
//            holder.pdate.setText(boxList.getCreatedAt());
//            holder.unique.setText(String.valueOf(boxList.getUniqueOrders()));
//        }}

    }

//    public void updateAnswers( List<Boxes> BItem,List<CustomerItems> CItem) {
//        BoxList = BItem;
//        ItemsList = CItem;
//        notifyDataSetChanged();
//    }
    public void updateAnswers(List<MergeBCI>BItem){
        CombList = BItem;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return CombList.size();
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

