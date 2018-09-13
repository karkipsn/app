package com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colors2web.zummix_app.Adapter.BoxAdapters.MBoxAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBox;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class MasterBoxes_frag extends Fragment {

    RecyclerView srecycleview;
    MBoxAdapter kadapter;

    List<MasterBox> MBoxList = new ArrayList<>();

    public MasterBoxes_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_cus_shipping_method, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        kadapter = new MBoxAdapter(getContext(),MBoxList);

        srecycleview = getActivity().findViewById(R.id.recycler_view_shipping);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//            mrecyclerView.setHasFixedSize(true);
        srecycleview.setLayoutManager(mLayoutManager);

        srecycleview.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
//            mrecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
        srecycleview.setItemAnimator(new DefaultItemAnimator());

        srecycleview.setAdapter(kadapter);
        loadAdapter();
    }


    private void loadAdapter() {


        if (getArguments() != null) {

            ArrayList<MasterBox> boxes = (ArrayList<MasterBox>)getArguments().getSerializable("masterBoxList");
            kadapter.updateAnswers(boxes);
            Log.d("masterboxList",boxes.toString());
}}}
