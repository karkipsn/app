package com.example.colors2web.zummix_app.Fragments;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colors2web.zummix_app.Adapter.OrderLogsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderLog;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;


public class Frag_OrderLogs extends Fragment {

    RecyclerView mrecyclerView;
    OrderLogsAdapter radapter;

    List<OrderLog> LogList = new ArrayList<>();

    public Frag_OrderLogs() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
       setRetainInstance(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    //First of create the view and inflate it and override the created view and do the recycleview stuff there
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        radapter = new OrderLogsAdapter(LogList);

        mrecyclerView = getActivity().findViewById(R.id.recycle_view);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);

        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
//        mrecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(radapter);
        loadAdapter();

    }

    private void loadAdapter() {

        if (getArguments() != null) {

            ArrayList<OrderLog> arraylist = getArguments().getParcelableArrayList("logs_detail");
            Log.d("logs_detail", arraylist.toString());
            radapter.updateAnswers(arraylist);
        }}}