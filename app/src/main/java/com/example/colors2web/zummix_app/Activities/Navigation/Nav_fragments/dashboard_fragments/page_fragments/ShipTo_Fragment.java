package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.page_fragments;

import android.content.Context;
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
import android.widget.TextView;

import com.example.colors2web.zummix_app.Adapter.DashboardAdapters.D_ShipToAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.ShipToOrder;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class ShipTo_Fragment extends Fragment {

    D_ShipToAdapter padapter;
    RecyclerView mrecycleView;
    List<ShipToOrder> UList = new ArrayList<>();
    Context mContext;
    TextView nulldisplay;
    String from1, to1;


    public ShipTo_Fragment() {
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
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mrecycleView = view.findViewById(R.id.recycler_view_shipping);

        if(padapter != null){
            padapter.onDetachedFromRecyclerView(mrecycleView);
            padapter.notifyDataSetChanged();
        }
        padapter = new D_ShipToAdapter(UList, getContext(),ShipTo_Fragment.this);


        nulldisplay = view.findViewById(R.id.null_textview);


        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mLayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

        loadAdapter();
    }

    private void loadAdapter() {

//        padapter.cleardata();

        if (getArguments() != null) {

            ArrayList<ShipToOrder> logs1 = getArguments().getParcelableArrayList("WeekShipTOList");
            from1 = getArguments().getString("from");
            to1 = getArguments().getString("to");
            UList.clear();
            padapter.notifyDataSetChanged();
            padapter.updateAnswers(logs1, from1, to1);

            if (logs1 == null) {
                nulldisplay.setVisibility(View.VISIBLE);
            } else {
                nulldisplay.setVisibility(View.VISIBLE);
            }

        }
    }

    public void refresh() {

//         warehouseAdapter.reloadfragment();
//        uadapter.reloadfragment();
//        Fragment frg1 = getFragmentManager().findFragmentByTag("Users");
//        frg1.onDestroy();
//        final FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.detach(frg1).attach(frg1).commit();

        padapter.cleardata();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}

