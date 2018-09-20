package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.User_fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colors2web.zummix_app.Adapter.UserAdapter.WarehouseAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Users.User;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class Warehouse_Fragment extends Fragment {
    WarehouseAdapter padapter;
    RecyclerView mrecycleView;
    List<User> WList = new ArrayList<>();

    public Warehouse_Fragment() {
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
        return inflater.inflate(R.layout.frag_cus_inventory_logs, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        padapter = new WarehouseAdapter(WList,getActivity(),Warehouse_Fragment.this);

        mrecycleView =view.findViewById(R.id.recycler_view_shipping);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mLayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);
        loadAdapter();
    }

    private void loadAdapter() {
        if (getArguments() != null) {

            ArrayList<User> logs = getArguments().getParcelableArrayList("WareList");
            Log.d("warelist",logs.toString());
            padapter.updateAnswers(logs);
        }
    }

    public void refresh() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

}
