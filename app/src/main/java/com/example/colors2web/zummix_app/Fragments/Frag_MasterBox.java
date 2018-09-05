package com.example.colors2web.zummix_app.Fragments;

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

import com.example.colors2web.zummix_app.Adapter.MasterBoxAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.Order2POJO.MasterBox;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class Frag_MasterBox extends Fragment {
    MasterBoxAdapter radapter;
    RecyclerView mrecyclerView;
    List<MasterBox>MList;
    public Frag_MasterBox() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
       
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        radapter = new MasterBoxAdapter(getContext(),MList);

        mrecyclerView = getActivity().findViewById(R.id.recycle_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);

//        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(radapter);
        loadAdapter();
    }

    private void loadAdapter() {
        if (getArguments() != null) {

            ArrayList<MasterBox> barraylist = getArguments().getParcelableArrayList("box_detail");
            Log.d("masterbox_detail", barraylist.toString());
            radapter.updateAnswers(barraylist);
        }
    }
}
