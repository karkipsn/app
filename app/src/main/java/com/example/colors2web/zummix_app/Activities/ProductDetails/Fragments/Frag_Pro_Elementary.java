package com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.ProductSearch.CustomPOJO;
import com.example.colors2web.zummix_app.POJO.ProductSearch.PickLocation;
import com.example.colors2web.zummix_app.R;

import butterknife.BindView;

public class Frag_Pro_Elementary extends Fragment {
    TextView replenish,pick,qoh,pick_balance,o_quantity,committed,a2s;

    public Frag_Pro_Elementary() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_product_elementary, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        pick = getActivity().findViewById(R.id.ipr_cus_pick);
        qoh = getActivity().findViewById(R.id.ipr_cus_qoh);
        pick_balance = getActivity().findViewById(R.id.ipr_cus_pickbalance);
        o_quantity = getActivity().findViewById(R.id.ipr_cus_ord);
        committed = getActivity().findViewById(R.id.ipr_cus_committed);
        a2s = getActivity().findViewById(R.id.ipr_cus_a2s);
        replenish = getActivity().findViewById(R.id.ipr_cus_replenish);


        if (getArguments() != null) {

            CustomPOJO pojo = (CustomPOJO) getArguments().getSerializable("detail");


            String pick1 = pojo.getPick();
            String qoh1 = pojo.getQoh();
            String pick_balance1 = pojo.getPick_balance();
            String o_quantity1 = pojo.getO_quantity();
            String committed1 = pojo.getCommitted();
            String a2s1 = pojo.getA2s();
            String replenish1 = pojo.getReplenish();



            pick.setText(pick1);
            qoh.setText(qoh1);
            pick_balance.setText(pick_balance1);
            o_quantity.setText(o_quantity1);
            committed.setText(committed1);
            a2s.setText(a2s1);
            replenish.setText(replenish1);
        }
    }}

