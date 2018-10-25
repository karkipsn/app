package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.colors2web.zummix_app.Adapter.VIewPagerAdapterProduct;
import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.OfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.ShipToOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalOfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalShipToOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.TotalVipOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.VipOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekOfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekShipToOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekTotalOfficeOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekTotalShipToOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekTotalVipOrder;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.WeekVipOrder;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;

public class dashboard_fragment1 extends Fragment {

    TabLayout tableLayout;
    ViewPager viewPager;

    ArrayList<OfficeOrder> OfficeList;
    ArrayList<ShipToOrder> ShipTOList;
    ArrayList<TotalOfficeOrder> TotalOfficeList;
    ArrayList<TotalShipToOrder> TotalShipTOList;
    ArrayList<TotalVipOrder> TotalVipList;
    ArrayList<VipOrder> VipList;
    ArrayList<WeekOfficeOrder> WeekOfficeList;
    ArrayList<WeekShipToOrder> WeekShipTOList;
    ArrayList<WeekTotalOfficeOrder> WeekTotalOfficeList;
    ArrayList<WeekTotalShipToOrder> WeekTotalShipTOList;
    ArrayList<WeekTotalVipOrder> WeekTotalVipList;
    ArrayList<WeekVipOrder> WeekVipList;
    String from,to;
    SwipeRefreshLayout swipe;

    private boolean shouldRefreshOnResume = false;

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public dashboard_fragment1() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dashboard, container, false);
    }
    @Override
    public void onDestroyView() {


        try{
            FragmentTransaction transaction = getActivity().getSupportFragmentManager()
                    .beginTransaction();

            transaction.remove(this);
            transaction.commit();
        }catch(Exception e){
        }

        super.onDestroyView();
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.viewpager_dashboard);
        tableLayout = view.findViewById(R.id.tabs_dashboard);

        swipe =getActivity().findViewById(R.id.swipeToRefresh);

        if(swipe.isActivated()){
            swipe.setEnabled(false);
        }


        if (getArguments() != null) {


            OfficeList = getArguments().getParcelableArrayList("OfficeList");
            ShipTOList = getArguments().getParcelableArrayList("ShipTOList");
            TotalOfficeList = getArguments().getParcelableArrayList("TotalOfficeList");
            TotalShipTOList = getArguments().getParcelableArrayList("TotalShipTOList");
            TotalVipList = getArguments().getParcelableArrayList("TotalVipList");
            VipList = getArguments().getParcelableArrayList("VipList");

            WeekOfficeList = getArguments().getParcelableArrayList("WeekOfficeList");
            WeekShipTOList = getArguments().getParcelableArrayList("WeekShipTOList");
            WeekTotalOfficeList = getArguments().getParcelableArrayList("WeekTotalOfficeList");
            WeekTotalShipTOList = getArguments().getParcelableArrayList("WeekTotalShipTOList");
            WeekTotalVipList = getArguments().getParcelableArrayList("WeekTotalVipList");
            WeekVipList = getArguments().getParcelableArrayList("WeekVipList");
            from = getArguments().getString("from");
            to = getArguments().getString("to");

       }

        viewpagerloader();
        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled( int position, float v, int i1 ) {
            }

            @Override
            public void onPageSelected( int position ) {
            }

            @Override
            public void onPageScrollStateChanged( int state ) {
                enableDisableSwipeRefresh( state == ViewPager.SCROLL_STATE_IDLE );
            }
        } );
    }

    private void enableDisableSwipeRefresh(boolean enable) {
        if (swipe != null) {
            swipe.setEnabled(enable);
        }
    }
//    @Override
//    public void onResume() {
//        super.onResume();
//        // Check should we need to refresh the fragment
//        if(shouldRefreshOnResume){
//            // refresh fragment
//            FragmentTransaction ft = getFragmentManager().beginTransaction();
//            ft.detach(this).attach(this).commit();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        shouldRefreshOnResume = true;
//    }

    private void viewpagerloader() {

        tableLayout.setupWithViewPager(viewPager);

        VIewPagerAdapterProduct viewPagerAdapter = new VIewPagerAdapterProduct(getChildFragmentManager());


        Bundle bundle1 = new Bundle();
        Daily_fragment daily = new Daily_fragment();

        bundle1.putParcelableArrayList("OfficeList", OfficeList);
        bundle1.putParcelableArrayList("ShipTOList", ShipTOList);
        bundle1.putParcelableArrayList("TotalOfficeList", TotalOfficeList);
        bundle1.putParcelableArrayList("TotalShipTOList", TotalShipTOList);
        bundle1.putParcelableArrayList("TotalVipList", TotalVipList);
        bundle1.putParcelableArrayList("VipList", VipList);
        bundle1.putString("from",from);
        bundle1.putString("to",to);

        daily.setArguments(bundle1);
        viewPagerAdapter.addFrag(daily, "Today Reports ");


        Bundle bundle2 = new Bundle();
        Weekly_Fragment weekly = new Weekly_Fragment();
        bundle2.putParcelableArrayList("WeekOfficeList", WeekOfficeList);
        bundle2.putParcelableArrayList("WeekShipTOList", WeekShipTOList);
        bundle2.putParcelableArrayList("WeekTotalOfficeList", WeekTotalOfficeList);
        bundle2.putParcelableArrayList("WeekTotalShipTOList", WeekTotalShipTOList);
        bundle2.putParcelableArrayList("WeekTotalVipList", WeekTotalVipList);
        bundle2.putParcelableArrayList("WeekVipList", WeekVipList);
        bundle2.putString("from",from);
        bundle2.putString("to",to);

        weekly.setArguments(bundle2);
        viewPagerAdapter.addFrag(weekly, "Weekly Reports");

        viewPagerAdapter.notifyDataSetChanged();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setOffscreenPageLimit(1);
//        viewPager.setSaveFromParentEnabled(false);


    }
}
