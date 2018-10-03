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

import com.example.colors2web.zummix_app.Adapter.Order_Adapters.LineItemsAdapter;
import com.example.colors2web.zummix_app.Adapter.Order_Adapters.SAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Order2POJO.ItemDetail;
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderShippingAddressesDetail;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderDetails;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class Frag_AddAndItems extends Fragment {

    RecyclerView mrecyclerView;
    RecyclerView mrecyclerView1;

    LineItemsAdapter ladapter;
    SAdapter sAdapter;
//    ANothe adapter

    List<ItemDetail> ItmList = new ArrayList<>();
    ArrayList<OrderShippingAddressesDetail> ShipList =new ArrayList<>();

    String o_id,order_status,order_type,edit_shipping_address;


    public Frag_AddAndItems() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_frag_addanditems, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        ladapter = new LineItemsAdapter(ItmList);

        mrecyclerView = getActivity().findViewById(R.id.recycle_viewc2);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);
        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setAdapter(ladapter);

        sAdapter = new SAdapter(getContext(),ShipList);
        mrecyclerView1 = getActivity().findViewById(R.id.recycle_viewc1);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mrecyclerView1.setHasFixedSize(true);
        mrecyclerView1.setLayoutManager(layoutManager);
        mrecyclerView1.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
//        mrecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
        mrecyclerView1.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView1.setAdapter(sAdapter);
        log();
    }

    private void log(){
        if (getArguments() != null) {

            o_id = getArguments().getString("b_id");
            order_status = getArguments().getString("order_status");
            order_type = getArguments().getString("order_type");
            edit_shipping_address = getArguments().getString("edit_shipping_address");

            ArrayList<ItemDetail> arraylist = getArguments().getParcelableArrayList("item_detail");
            ladapter.updateAnswers(arraylist);


            OrderDetails details = getArguments().getParcelable("OrderP");

            ArrayList<OrderShippingAddressesDetail> sarraylist = getArguments().getParcelableArrayList("shipping_detail");
            sAdapter.updateAnswers(sarraylist,details);
        }
    }

}