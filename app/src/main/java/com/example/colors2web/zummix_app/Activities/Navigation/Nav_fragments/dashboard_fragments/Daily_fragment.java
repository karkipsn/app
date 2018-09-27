package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.page_fragments.FieldOffice_fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.page_fragments.ShipTo_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.page_fragments.Total_Filed_Office_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.page_fragments.Total_Ship_To_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.page_fragments.Total_Vip_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.page_fragments.Vip_Fragment;
import com.example.colors2web.zummix_app.Adapter.VIewPagerAdapterProduct;
import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.OfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.ShipToOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalOfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalShipToOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalVipOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.VipOrder;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;

public class Daily_fragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;

    ArrayList<OfficeOrder> OfficeList;
    ArrayList<ShipToOrder> ShipTOListOfficeList = null;
    ArrayList<TotalOfficeOrder> TotalOfficeListOfficeList;
    ArrayList<TotalShipToOrder> TotalShipTOListOfficeList = null;
    ArrayList<TotalVipOrder> TotalVipListOfficeList;
    ArrayList<VipOrder> VipListOfficeList;
    String from1,to1;
    VIewPagerAdapterProduct adapter;

    public Daily_fragment() {
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
        return inflater.inflate(R.layout.fragment_today, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tabLayout = view.findViewById(R.id.tabs_today);
        viewPager = view.findViewById(R.id.viewpager_today);

        if (getArguments() != null) {


            ShipTOListOfficeList = getArguments().getParcelableArrayList("ShipTOList");
            TotalShipTOListOfficeList = getArguments().getParcelableArrayList("TotalShipTOList");
            OfficeList = getArguments().getParcelableArrayList("OfficeList");
            TotalOfficeListOfficeList = getArguments().getParcelableArrayList("TotalOfficeList");
            VipListOfficeList = getArguments().getParcelableArrayList("VipList");
            TotalVipListOfficeList = getArguments().getParcelableArrayList("TotalVipList");
            from1 = getArguments().getString("from");
            to1 = getArguments().getString("to");

        }

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);


         adapter = new VIewPagerAdapterProduct(getFragmentManager());
//        viewPager.removeAllViews();

        Bundle bundle1 = new Bundle();

        ShipTo_Fragment ship = new ShipTo_Fragment();
        bundle1.putParcelableArrayList("WeekShipTOList", ShipTOListOfficeList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        ship.setArguments(bundle1);
        adapter.addFrag(ship, "Ship To Orders");

        Total_Ship_To_Fragment total_ship = new Total_Ship_To_Fragment();
        bundle1.putParcelableArrayList("WeekTotalShipTOList", TotalShipTOListOfficeList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        total_ship.setArguments(bundle1);
        adapter.addFrag(total_ship, "Total Ship To Orders");

        FieldOffice_fragment office = new FieldOffice_fragment();
        bundle1.putParcelableArrayList("WeekOfficeList", OfficeList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        office.setArguments(bundle1);
        adapter.addFrag(office, "Field Office Orders");

        Total_Filed_Office_Fragment total_office = new Total_Filed_Office_Fragment();
        bundle1.putParcelableArrayList("WeekTotalOfficeList", TotalOfficeListOfficeList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        total_office.setArguments(bundle1);
        adapter.addFrag(total_office, "Total Field Office Orders ");

        Vip_Fragment vip = new Vip_Fragment();
        bundle1.putParcelableArrayList("WeekVipList", VipListOfficeList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        vip.setArguments(bundle1);
        adapter.addFrag(vip, "VIP Orders");

        Total_Vip_Fragment total_vip = new Total_Vip_Fragment();
        bundle1.putParcelableArrayList("WeekTotalVipList", TotalVipListOfficeList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        total_vip.setArguments(bundle1);
        adapter.addFrag(total_vip, "Total VIP Orders ");

        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
//        viewPager.setOffscreenPageLimit(1);
//        viewPager.setSaveFromParentEnabled(false);
    }
}
