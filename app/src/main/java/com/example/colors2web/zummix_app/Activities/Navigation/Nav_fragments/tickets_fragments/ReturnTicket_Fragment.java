package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.Tickets.ReturnTicket;
import com.example.colors2web.zummix_app.POJO.Tickets.ReturnTicketResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIClient1;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReturnTicket_Fragment extends Fragment {

    ArrayList<ReturnTicket> ReturnList = new ArrayList<>();
    ArrayList<ReturnTicket> ArcheiveList = new ArrayList<>();
    APIInterface apiInterface;
    TabLayout tabLayout;
    ViewPager viewPager;
    SwipeRefreshLayout mSwipeRefreshLayout;


    public ReturnTicket_Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_return_ticket, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        apiInterface = APIClient1.getClient().create(APIInterface.class);
        tabLayout = view.findViewById(R.id.tabs_returnticket);
        viewPager = view.findViewById(R.id.viewpager_returnticket);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        
        loadData(email,password);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                ReturnList.clear();
                ArcheiveList.clear();
                loadData(email,password);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadData( String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ReturnTicketResponse> call = apiInterface.getReturnTickets(email, password);

        call.enqueue(new Callback<ReturnTicketResponse>() {
            @Override
            public void onResponse(Call<ReturnTicketResponse> call, Response<ReturnTicketResponse> response) {

                if (response.isSuccessful()) {

                    ReturnTicketResponse resp1 = response.body();

                    List<ReturnTicket> order = resp1.getReturnTickets();

                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {

//                            ReturnTicket user = order.get(i).getUser();
                            ReturnTicket uom = new ReturnTicket();
//                            Always needs to create fresh instance//if POJO instance is taken outside

                            String storename= order.get(i).getStoreName();
                            String storeurl= order.get(i).getStoreUrl();
                            Long id =order.get(i).getId();
                            Long storeid =order.get(i).getStoreId();
                            String created = order.get(i).getUpdatedAt();
                            String cus_name = order.get(i).getCustomerName();
                            String ticket = order.get(i).getTicketNumber();
                            String sales = order.get(i).getSaleNumber();
                            String reason = order.get(i).getReason();
                            Long quantity = order.get(i).getQuantity();
                            String status = order.get(i).getStatus();


                            uom.setStoreName(storename);
                            uom.setStoreUrl(storeurl);
                            uom.setId(id);
                            uom.setStoreId(storeid);
                            uom.setUpdatedAt(created);
                            uom.setCustomerName(cus_name);
                            uom.setTicketNumber(ticket);
                            uom.setSaleNumber(sales);
                            uom.setReason(reason);
                            uom.setQuantity(quantity);
                            uom.setStatus(status);


                            if (status .equals("0")) {

                                ReturnList.add(uom);
                            }

                            if (status .equals("1") || status .equals("2")){

                                ArcheiveList.add(uom);

                            }

                            tabLayout.setupWithViewPager(viewPager);

                            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());


                            Bundle bundle1 = new Bundle();

                            Return_Fragment pickf = new Return_Fragment();
                            bundle1.putParcelableArrayList("ReturnList", ReturnList);
                            pickf.setArguments(bundle1);
                            viewPagerAdapter.addFrag(pickf, "Returned Ticket ");


                            Archeived_Fragment loc = new Archeived_Fragment();

                            bundle1.putParcelableArrayList("ArcheiveList", ArcheiveList);
                            loc.setArguments(bundle1);
                            viewPagerAdapter.addFrag(loc, "Archeived Ticket");

                            viewPager.setAdapter(viewPagerAdapter);

                            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                                @Override
                                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                                }

                                @Override
                                public void onPageSelected(int position) {

                                }

                                @Override
                                public void onPageScrollStateChanged(int state) {
                                    enableDisableSwipeRefresh( state == ViewPager.SCROLL_STATE_IDLE );                                }
                            });

//                        padapter.updateAdapter(UserList);

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }


                        }
                    } else {
                        String d = response.body().getMessage();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 401) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ReturnTicketResponse> call, Throwable t) {

                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    private void enableDisableSwipeRefresh(boolean b) {
        if (mSwipeRefreshLayout != null) {
            mSwipeRefreshLayout.setEnabled(b);
        }
    }
}
