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
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.week_page_fragments.page_fragments.Week_FieldOffice_fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.week_page_fragments.page_fragments.Week_ShipTo_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.week_page_fragments.page_fragments.Week_Total_Filed_Office_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.week_page_fragments.page_fragments.Week_Total_Ship_To_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.week_page_fragments.page_fragments.Week_Total_Vip_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments.week_page_fragments.page_fragments.Week_Vip_Fragment;
import com.example.colors2web.zummix_app.Adapter.VIewPagerAdapterProduct;
import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekOfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekShipToOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekTotalOfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekTotalShipToOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekTotalVipOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekVipOrder;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;

public class Weekly_Fragment extends Fragment {

    ViewPager viewPager;
    TabLayout tabLayout;
    String from1, to1;

    ArrayList<WeekOfficeOrder> WeekOfficeList;
    ArrayList<WeekShipToOrder> WeekShipTOList;
    ArrayList<WeekTotalOfficeOrder> cuWeekTotalOfficeList;
    ArrayList<WeekTotalShipToOrder> WeekTotalShipTOList;
    ArrayList<WeekTotalVipOrder> WeekTotalVipList;
    ArrayList<WeekVipOrder> WeekVipList;

    public Weekly_Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weekly, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = getActivity().findViewById(R.id.tabs_weekly);
        viewPager = getActivity().findViewById(R.id.viewpager_weekly);

        if (getArguments() != null) {

            WeekShipTOList = getArguments().getParcelableArrayList("WeekShipTOList");
            WeekTotalShipTOList = getArguments().getParcelableArrayList("WeekTotalShipTOList");
            WeekOfficeList = getArguments().getParcelableArrayList("WeekOfficeList");
            cuWeekTotalOfficeList = getArguments().getParcelableArrayList("WeekTotalOfficeList");
            WeekVipList = getArguments().getParcelableArrayList("WeekVipList");
            WeekTotalVipList = getArguments().getParcelableArrayList("WeekTotalVipList");
            from1 = getArguments().getString("from");
            to1 = getArguments().getString("to");


        }
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

//        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        VIewPagerAdapterProduct adapter = new VIewPagerAdapterProduct(getFragmentManager());


        Bundle bundle1 = new Bundle();

        Week_ShipTo_Fragment ship = new Week_ShipTo_Fragment();
        bundle1.putParcelableArrayList("WeekShipTOList", WeekShipTOList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        ship.setArguments(bundle1);
        adapter.addFrag(ship, "Ship To Orders");

        Week_Total_Ship_To_Fragment total_ship = new Week_Total_Ship_To_Fragment();
        bundle1.putParcelableArrayList("WeekTotalShipTOList", WeekTotalShipTOList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        total_ship.setArguments(bundle1);
        adapter.addFrag(total_ship, "Ship To Orders");

        Week_FieldOffice_fragment office = new Week_FieldOffice_fragment();
        bundle1.putParcelableArrayList("WeekOfficeList", WeekOfficeList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        office.setArguments(bundle1);
        adapter.addFrag(office, "Field Office Orders");

        Week_Total_Filed_Office_Fragment total_office = new Week_Total_Filed_Office_Fragment();
        bundle1.putParcelableArrayList("WeekTotalOfficeList", cuWeekTotalOfficeList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        total_office.setArguments(bundle1);
        adapter.addFrag(total_office, "Total Field Office Orders ");

        Week_Vip_Fragment vip = new Week_Vip_Fragment();
        bundle1.putParcelableArrayList("WeekVipList", WeekVipList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        vip.setArguments(bundle1);
        adapter.addFrag(vip, "VIP Orders");

        Week_Total_Vip_Fragment total_vip = new Week_Total_Vip_Fragment();
        bundle1.putParcelableArrayList("WeekTotalVipList", WeekTotalVipList);
        bundle1.putString("from", from1);
        bundle1.putString("to", to1);
        total_vip.setArguments(bundle1);
        adapter.addFrag(total_vip, "Total VIP Orders ");

        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);

    }
}
