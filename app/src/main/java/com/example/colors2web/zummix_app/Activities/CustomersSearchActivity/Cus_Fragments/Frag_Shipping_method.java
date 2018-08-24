package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.POJO.customers.ShippingMethods;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag_Shipping_method extends Fragment {


    RecyclerView srecycleview;
    ShippingMethodAdapter kadapter;

    List<ShippingMethods> ShipList = new ArrayList<>();

    public Frag_Shipping_method() {
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

        kadapter = new ShippingMethodAdapter(ShipList);

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

            ArrayList<ShippingMethods> ship = getArguments().getParcelableArrayList("cus_ship_list");
            kadapter.updateAnswers(ship);
        }
    }
}




