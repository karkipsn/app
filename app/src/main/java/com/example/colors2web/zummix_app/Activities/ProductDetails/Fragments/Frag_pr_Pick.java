package com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.ProductSearch.PickLocation;
import com.example.colors2web.zummix_app.R;

import java.io.Serializable;

public class Frag_pr_Pick extends Fragment {

    TextView pick, location;

    public Frag_pr_Pick() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_pick, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pick = getActivity().findViewById(R.id.adpt_pick_pick);
        location = getActivity().findViewById(R.id.adpt_pick_location);

        if (getArguments() != null) {

            PickLocation location1 = (PickLocation) getArguments().getSerializable("plocation");


//            String warehouse_name = getArguments().getString("warehouse_name");
//            String pick_aisle_name = getArguments().getString("pick_aisle_name");
//            String section_level = getArguments().getString("section_level");
//            String section_lnumber = getArguments().getString("section_lnumber");
//            String pick_bin = getArguments().getString("pick_bin");
//            String pick1 = getArguments().getString("pick");


            String warehouse_name = location1.getWarehouseName();
            String pick_aisle_name = location1.getPickAisleName();
            String section_level = location1.getSectionLevel();
            String section_lnumber = location1.getSectionLevel();
            String pick_bin = location1.getPickBin();
            String pick1 = location1.getPicku();



            pick.setText(pick1);
            location.setText(warehouse_name + " "+ pick_aisle_name +" "+section_level+section_lnumber+pick_bin);
        }
    }

//    public void loaddata(String warehouse_name, String pick_aisle_name, String section_level,
//                         String section_lnumber, String pickBin, String pick_bin) {
//
//        pick.setText(pick_bin);
//        location.setText(warehouse_name + " "+ pick_aisle_name +" "+section_level+section_lnumber+pickBin);
//    }
}
