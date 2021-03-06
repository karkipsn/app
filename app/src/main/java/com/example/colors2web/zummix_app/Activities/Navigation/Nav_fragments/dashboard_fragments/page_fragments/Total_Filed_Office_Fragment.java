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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.Adapter.DashboardAdapters.D_TotalOfficeAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalOfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekTotalOfficeOrder;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class Total_Filed_Office_Fragment extends Fragment {

    D_TotalOfficeAdapter padapter;
    RecyclerView mrecycleView;
    List<TotalOfficeOrder> UList = new ArrayList<>();
    Context mContext;
    TextView nulldisplay;
    String from1,to1;


    public Total_Filed_Office_Fragment() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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

        padapter = new D_TotalOfficeAdapter(UList, getContext());

        nulldisplay = view.findViewById(R.id.null_textview);

        mrecycleView = view.findViewById(R.id.recycler_view_shipping);
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

            ArrayList<TotalOfficeOrder> logs1 = getArguments().getParcelableArrayList("WeekTotalOfficeList");
            from1 =getArguments().getString("from");
            to1 =getArguments().getString("to");
            padapter.updateAnswers(logs1,from1,to1);

            if(logs1 ==null){
                nulldisplay.setVisibility(View.VISIBLE);
            }else{
                nulldisplay.setVisibility(View.VISIBLE);
            }


        }
    }

    public void refresh() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}

