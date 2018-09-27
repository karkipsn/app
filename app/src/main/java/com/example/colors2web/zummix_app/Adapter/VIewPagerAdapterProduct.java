package com.example.colors2web.zummix_app.Adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_Inv_Logs;
import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_pr_Location;
import com.example.colors2web.zummix_app.Activities.ProductDetails.Fragments.Frag_pr_Pick;

import java.util.ArrayList;
import java.util.List;

public class VIewPagerAdapterProduct extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public VIewPagerAdapterProduct(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return VIewPagerAdapterProduct.POSITION_NONE;

    }

    @Override
    public Fragment getItem(int position) {

      return mFragmentList.get(position);

    }

    @Override
    public void destroyItem(@NonNull View container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);
        mFragmentList.remove(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFrag(Fragment fragment, String title) {

        mFragmentList.add(fragment);
        mFragmentTitleList.add(title);
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mFragmentTitleList.get(position);
    }
}

