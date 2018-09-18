package com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments;

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

import com.example.colors2web.zummix_app.Adapter.ProductAdapter.LocationAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.ProductSearch.DrShipmentItemLocations;
import com.example.colors2web.zummix_app.POJO.customers.ShippingMethods;
import com.example.colors2web.zummix_app.R;


import java.util.ArrayList;
import java.util.List;

public class Frag_pr_Location extends Fragment {

    LocationAdapter ladapter;
    RecyclerView recycleView;
    List<DrShipmentItemLocations> LoList = new ArrayList<>();

    public Frag_pr_Location() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

//        View view = inflater.inflate(R.layout.frag_cus_shipping_method, container, false);
//        return view;
        return inflater.inflate(R.layout.frag_cus_shipping_method, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ladapter = new LocationAdapter(LoList, getContext());

        recycleView = view.findViewById(R.id.recycler_view_shipping);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recycleView.setHasFixedSize(true);
        recycleView.setLayoutManager(mLayoutManager);

        recycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        recycleView.setItemAnimator(new DefaultItemAnimator());
        recycleView.setAdapter(ladapter);
        loadAdapter();
    }

    private void loadAdapter() {
        if (getArguments() != null) {

//            Serializable ship = getArguments().getSerializable("ItmList");
            ArrayList<DrShipmentItemLocations> ship = getArguments().getParcelableArrayList("ItmList");
            ladapter.updateAnswers(ship);
            Log.d("ship",ship.toString());

        }
    }
}
