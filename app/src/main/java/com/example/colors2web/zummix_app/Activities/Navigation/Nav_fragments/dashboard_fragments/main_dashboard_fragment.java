package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.dashboard_fragments;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.Cron_jobs.CronJobResponse;
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
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class main_dashboard_fragment extends Fragment {


    private static final String TAG_DASHBOARD1_FRAGMENT = "Dashboard1";
    APIInterface apiInterface;
    Context mContext;


    SwipeRefreshLayout mSwipeRefreshLayout;

    TextView mform, mto;
    Button btn_submit;
//    TabLayout tableLayout;
//    ViewPager viewPager;

    Integer mYear, mMonth, mDay;
    String mform1, mto1, from, to;

    ArrayList<OfficeOrder> OfficeList = new ArrayList<>();
    ArrayList<ShipToOrder> ShipTOList = new ArrayList<>();
    ArrayList<TotalOfficeOrder> TotalOfficeList = new ArrayList<>();
    ArrayList<TotalShipToOrder> TotalShipTOList = new ArrayList<>();
    ArrayList<TotalVipOrder> TotalVipList = new ArrayList<>();
    ArrayList<VipOrder> VipList = new ArrayList<>();
    ArrayList<WeekOfficeOrder> WeekOfficeList = new ArrayList<>();
    ArrayList<WeekShipToOrder> WeekShipTOList = new ArrayList<>();
    ArrayList<WeekTotalOfficeOrder> WeekTotalOfficeList = new ArrayList<>();
    ArrayList<WeekTotalShipToOrder> WeekTotalShipTOList = new ArrayList<>();
    ArrayList<WeekTotalVipOrder> WeekTotalVipList = new ArrayList<>();
    ArrayList<WeekVipOrder> WeekVipList = new ArrayList<>();


    public main_dashboard_fragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inf = inflater.inflate(R.layout.fragment_main_dashboard, container, false);
        return inf;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mform = getActivity().findViewById(R.id.dashboard_from);
        mto = getActivity().findViewById(R.id.dashboard_to);

        btn_submit = getActivity().findViewById(R.id.dashboard_submit);

//        viewPager = getActivity().findViewById(R.id.viewpager_dashboard);
//        tableLayout = getActivity().findViewById(R.id.tabs_dashboard);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");


        mform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        R.style.AppTheme_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mform.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }

                }, mYear, mMonth, mDay);

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                mform1 = format.format(c.getTime());
                datePickerDialog.show();
            }
        });

        mto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        R.style.AppTheme_Light_DatePicker, new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        mto.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }

                }, mYear, mMonth, mDay);

                DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                mto1 = format.format(c.getTime());
                datePickerDialog.show();

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                from = mform.getText().toString();
                to = mto.getText().toString();

                call_cron_jobs(email, password, from, to);
            }
        });


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                call_cron_jobs(email, password, from, to);

                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

    }


    private void call_cron_jobs(String email, String password, final String from, final String to) {


        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        if (OfficeList != null) {
            OfficeList.clear();
        }
        if (ShipTOList != null) {
            ShipTOList.clear();
        }
        if (TotalOfficeList != null) {
            TotalOfficeList.clear();
        }
        if (TotalShipTOList != null) {
            TotalShipTOList.clear();
        }
        if (TotalVipList != null) {
            TotalVipList.clear();
        }
        if (VipList != null) {
            VipList.clear();
        }
        if (WeekOfficeList != null) {
            WeekOfficeList.clear();
        }
        if (WeekShipTOList != null) {
            WeekShipTOList.clear();
        }
        if (WeekTotalOfficeList != null) {
            WeekTotalOfficeList.clear();
        }
        if (WeekTotalShipTOList != null) {
            WeekTotalShipTOList.clear();
        }
        if (WeekTotalVipList != null) {
            WeekTotalVipList.clear();
        }
        if (WeekVipList != null) {
            WeekVipList.clear();
        }

        Call<CronJobResponse> call = apiInterface.getCronJobs(email, password, from, to);
        call.enqueue(new Callback<CronJobResponse>() {
            @Override
            public void onResponse(Call<CronJobResponse> call, Response<CronJobResponse> response) {
                if (response.isSuccessful()) {

                    CronJobResponse res = response.body();

                    if (res.getmReturnType().equals("success")) {

                        List<OfficeOrder> office = res.getmOfficeOrders();
                        List<ShipToOrder> ship = res.getmShipToOrders();
                        List<TotalOfficeOrder> total_office = res.getmTotalOfficeOrders();
                        List<TotalShipToOrder> total_ship = res.getmTotalShipToOrders();
                        List<TotalVipOrder> total_vip = res.getmTotalVipOrders();
                        List<VipOrder> vip = res.getmVipOrders();
                        List<WeekOfficeOrder> week_office = res.getmWeeklyOfficeOrders();
                        List<WeekShipToOrder> week_ship = res.getmWeeklyShipToOrders();
                        List<WeekTotalOfficeOrder> week_total_office = res.getmWeeklyTotalOfficeOrders();
                        List<WeekTotalShipToOrder> week_total_ship = res.getmWeeklyTotalShipToOrders();
                        List<WeekTotalVipOrder> week_total_vip = res.getmWeeklyTotalVipOrders();
                        List<WeekVipOrder> week_vip = res.getmWeeklyVipOrders();

                        if (office != null) {

                            for (int i = 0; i < office.size(); i++) {

                                OfficeOrder of = new OfficeOrder();

                                String mcustomer_name = office.get(i).getmCustomerName();
                                Long mcustomer_id = office.get(i).getmCustomerId();
                                Long mno_of_orders = office.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = office.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = office.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = office.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = office.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = office.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = office.get(i).getmTotalIor();

                                of.setmCustomerName(mcustomer_name);
                                of.setmCustomerId(mcustomer_id);
                                of.setmNoOfOrders(mno_of_orders);
                                of.setmTotalOpenOrders(mtotal_open_orders);
                                of.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                of.setmTotalBatchedOrders(mtotal_batched_orders);
                                of.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                of.setmTotalShippedOrders(mtotal_shipped_orders);
                                of.setmTotalIor(mtotal_ior);


                                OfficeList.add(of);
                            }


                        }
                        if (ship != null) {

                            for (int i = 0; i < ship.size(); i++) {
                                ShipToOrder sipto = new ShipToOrder();

                                String mcustomer_name1 = ship.get(i).getmCustomerName();
                                Long mcustomer_id1 = ship.get(i).getmCustomerId();
                                Long mno_of_orders1 = ship.get(i).getmNoOfOrders();
                                Long mtotal_open_orders1 = ship.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders1 = ship.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders1 = ship.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders1 = ship.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders1 = ship.get(i).getmTotalShippedOrders();
                                Long mtotal_ior1 = ship.get(i).getmTotalIor();

                                sipto.setmCustomerName(mcustomer_name1);
                                sipto.setmCustomerId(mcustomer_id1);
                                sipto.setmNoOfOrders(mno_of_orders1);
                                sipto.setmTotalOpenOrders(mtotal_open_orders1);
                                sipto.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders1);
                                sipto.setmTotalBatchedOrders(mtotal_batched_orders1);
                                sipto.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders1);
                                sipto.setmTotalShippedOrders(mtotal_shipped_orders1);
                                sipto.setmTotalIor(mtotal_ior1);

                                ShipTOList.add(sipto);
                            }

                        }
                        if (total_office != null) {

                            for (int i = 0; i < total_office.size(); i++) {

                                TotalOfficeOrder tot = new TotalOfficeOrder();

                                Long mno_of_orders = total_office.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = total_office.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = total_office.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = total_office.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = total_office.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = total_office.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = total_office.get(i).getmTotalIor();

                                tot.setmNoOfOrders(mno_of_orders);
                                tot.setmTotalOpenOrders(mtotal_open_orders);
                                tot.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                tot.setmTotalBatchedOrders(mtotal_batched_orders);
                                tot.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                tot.setmTotalShippedOrders(mtotal_shipped_orders);
                                tot.setmTotalIor(mtotal_ior);

                                TotalOfficeList.add(tot);
                            }

                        }
                        if (total_ship != null) {


                            for (int i = 0; i < total_ship.size(); i++) {

                                TotalShipToOrder tsto = new TotalShipToOrder();


                                Long mno_of_orders = total_ship.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = total_ship.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = total_ship.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = total_ship.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = total_ship.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = total_ship.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = total_ship.get(i).getmTotalIor();

                                tsto.setmNoOfOrders(mno_of_orders);
                                tsto.setmTotalOpenOrders(mtotal_open_orders);
                                tsto.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                tsto.setmTotalBatchedOrders(mtotal_batched_orders);
                                tsto.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                tsto.setmTotalShippedOrders(mtotal_shipped_orders);
                                tsto.setmTotalIor(mtotal_ior);


                                TotalShipTOList.add(tsto);
                            }
                        }
                        if (total_vip != null) {


                            for (int i = 0; i < total_vip.size(); i++) {

                                TotalVipOrder vipz = new TotalVipOrder();

                                Long mno_of_orders = total_vip.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = total_vip.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = total_vip.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = total_vip.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = total_vip.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = total_vip.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = total_vip.get(i).getmTotalIor();

                                vipz.setmNoOfOrders(mno_of_orders);
                                vipz.setmTotalOpenOrders(mtotal_open_orders);
                                vipz.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                vipz.setmTotalBatchedOrders(mtotal_batched_orders);
                                vipz.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                vipz.setmTotalShippedOrders(mtotal_shipped_orders);
                                vipz.setmTotalIor(mtotal_ior);

                                TotalVipList.add(vipz);

                            }
                        }
                        if (vip != null) {

                            for (int i = 0; i < vip.size(); i++) {

                                VipOrder vip1 = new VipOrder();

                                String mcustomer_name = vip.get(i).getmCustomerName();
                                Long mcustomer_id = vip.get(i).getmCustomerId();
                                Long mno_of_orders = vip.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = vip.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = vip.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = vip.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = vip.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = vip.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = vip.get(i).getmTotalIor();

                                vip1.setmCustomerName(mcustomer_name);
                                vip1.setmCustomerId(mcustomer_id);
                                vip1.setmNoOfOrders(mno_of_orders);
                                vip1.setmTotalOpenOrders(mtotal_open_orders);
                                vip1.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                vip1.setmTotalBatchedOrders(mtotal_batched_orders);
                                vip1.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                vip1.setmTotalShippedOrders(mtotal_shipped_orders);
                                vip1.setmTotalIor(mtotal_ior);

                                VipList.add(vip1);
                            }

                        }
                        if (week_office != null) {

                            for (int i = 0; i < week_office.size(); i++) {
                                WeekOfficeOrder of = new WeekOfficeOrder();

                                String mcustomer_name = week_office.get(i).getmCustomerName();
                                Long mcustomer_id = week_office.get(i).getmCustomerId();
                                Long mno_of_orders = week_office.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = week_office.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = week_office.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = week_office.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = week_office.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = week_office.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = week_office.get(i).getmTotalIor();

                                of.setmCustomerName(mcustomer_name);
                                of.setmCustomerId(mcustomer_id);
                                of.setmNoOfOrders(mno_of_orders);
                                of.setmTotalOpenOrders(mtotal_open_orders);
                                of.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                of.setmTotalBatchedOrders(mtotal_batched_orders);
                                of.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                of.setmTotalShippedOrders(mtotal_shipped_orders);
                                of.setmTotalIor(mtotal_ior);


                                WeekOfficeList.add(of);
                            }

                        }
                        if (week_ship != null) {

                            for (int i = 0; i < week_ship.size(); i++) {

                                WeekShipToOrder sipto = new WeekShipToOrder();

                                String mcustomer_name1 = week_ship.get(i).getmCustomerName();
                                Long mcustomer_id1 = week_ship.get(i).getmCustomerId();
                                Long mno_of_orders1 = week_ship.get(i).getmNoOfOrders();
                                Long mtotal_open_orders1 = week_ship.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders1 = week_ship.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders1 = week_ship.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders1 = week_ship.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders1 = week_ship.get(i).getmTotalShippedOrders();
                                Long mtotal_ior1 = week_ship.get(i).getmTotalIor();

                                sipto.setmCustomerName(mcustomer_name1);
                                sipto.setmCustomerId(mcustomer_id1);
                                sipto.setmNoOfOrders(mno_of_orders1);
                                sipto.setmTotalOpenOrders(mtotal_open_orders1);
                                sipto.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders1);
                                sipto.setmTotalBatchedOrders(mtotal_batched_orders1);
                                sipto.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders1);
                                sipto.setmTotalShippedOrders(mtotal_shipped_orders1);
                                sipto.setmTotalIor(mtotal_ior1);

                                WeekShipTOList.add(sipto);
                            }

                        }
                        if (week_total_office != null) {


                            for (int i = 0; i < week_total_office.size(); i++) {

                                WeekTotalOfficeOrder tot = new WeekTotalOfficeOrder();

                                Long mno_of_orders = week_total_office.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = week_total_office.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = week_total_office.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = week_total_office.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = week_total_office.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = week_total_office.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = week_total_office.get(i).getmTotalIor();

                                tot.setmNoOfOrders(mno_of_orders);
                                tot.setmTotalOpenOrders(mtotal_open_orders);
                                tot.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                tot.setmTotalBatchedOrders(mtotal_batched_orders);
                                tot.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                tot.setmTotalShippedOrders(mtotal_shipped_orders);
                                tot.setmTotalIor(mtotal_ior);


                                WeekTotalOfficeList.add(tot);
                            }
                        }
                        if (week_total_ship != null) {

                            for (int i = 0; i < week_total_ship.size(); i++) {

                                WeekTotalShipToOrder tsto = new WeekTotalShipToOrder();

                                Long mno_of_orders = total_ship.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = total_ship.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = total_ship.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = total_ship.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = total_ship.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = total_ship.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = total_ship.get(i).getmTotalIor();

                                tsto.setmNoOfOrders(mno_of_orders);
                                tsto.setmTotalOpenOrders(mtotal_open_orders);
                                tsto.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                tsto.setmTotalBatchedOrders(mtotal_batched_orders);
                                tsto.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                tsto.setmTotalShippedOrders(mtotal_shipped_orders);
                                tsto.setmTotalIor(mtotal_ior);

                                WeekTotalShipTOList.add(tsto);
                            }

                        }
                        if (week_total_vip != null) {

                            for (int i = 0; i < week_total_vip.size(); i++) {
                                WeekTotalVipOrder vipz = new WeekTotalVipOrder();

                                Long mno_of_orders = week_total_vip.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = week_total_vip.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = week_total_vip.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = week_total_vip.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = week_total_vip.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = week_total_vip.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = week_total_vip.get(i).getmTotalIor();

                                vipz.setmNoOfOrders(mno_of_orders);
                                vipz.setmTotalOpenOrders(mtotal_open_orders);
                                vipz.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                vipz.setmTotalBatchedOrders(mtotal_batched_orders);
                                vipz.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                vipz.setmTotalShippedOrders(mtotal_shipped_orders);
                                vipz.setmTotalIor(mtotal_ior);

                                WeekTotalVipList.add(vipz);
                            }

                        }
                        if (week_vip != null) {

                            for (int i = 0; i < week_vip.size(); i++) {

                                WeekVipOrder vip1 = new WeekVipOrder();

                                String mcustomer_name = week_vip.get(i).getmCustomerName();
                                Long mcustomer_id = week_vip.get(i).getmCustomerId();
                                Long mno_of_orders = week_vip.get(i).getmNoOfOrders();
                                Long mtotal_open_orders = week_vip.get(i).getmTotalOpenOrders();
                                Long mtotal_onhold_overdue_orders = week_vip.get(i).getmTotalOnholdOverdueOrders();
                                Long mtotal_batched_orders = week_vip.get(i).getmTotalBatchedOrders();
                                Long mtotal_batched_overdue_orders = week_vip.get(i).getmTotalBatchedOverdueOrders();
                                Long mtotal_shipped_orders = week_vip.get(i).getmTotalShippedOrders();
                                Long mtotal_ior = week_vip.get(i).getmTotalIor();

                                vip1.setmCustomerName(mcustomer_name);
                                vip1.setmCustomerId(mcustomer_id);
                                vip1.setmNoOfOrders(mno_of_orders);
                                vip1.setmTotalOpenOrders(mtotal_open_orders);
                                vip1.setmTotalOnholdOverdueOrders(mtotal_onhold_overdue_orders);
                                vip1.setmTotalBatchedOrders(mtotal_batched_orders);
                                vip1.setmTotalBatchedOverdueOrders(mtotal_batched_overdue_orders);
                                vip1.setmTotalShippedOrders(mtotal_shipped_orders);
                                vip1.setmTotalIor(mtotal_ior);

                                WeekVipList.add(vip1);
                            }
                        }

                        Bundle bundle = new Bundle();
                        bundle.putParcelableArrayList("OfficeList", OfficeList);
                        bundle.putParcelableArrayList("ShipTOList", ShipTOList);
                        Log.d("ship_main", ShipTOList.toString());
                        bundle.putParcelableArrayList("TotalOfficeList", TotalOfficeList);
                        bundle.putParcelableArrayList("TotalShipTOList", TotalShipTOList);
                        bundle.putParcelableArrayList("TotalVipList", TotalVipList);
                        bundle.putParcelableArrayList("VipList", VipList);

                        bundle.putParcelableArrayList("WeekOfficeList", WeekOfficeList);
                        bundle.putParcelableArrayList("WeekShipTOList", WeekShipTOList);
                        bundle.putParcelableArrayList("WeekTotalOfficeList", WeekTotalOfficeList);
                        bundle.putParcelableArrayList("WeekTotalShipTOList", WeekTotalShipTOList);
                        bundle.putParcelableArrayList("WeekTotalVipList", WeekTotalVipList);
                        bundle.putParcelableArrayList("WeekVipList", WeekVipList);
                        bundle.putString("from", from);
                        bundle.putString("to", to);

                        dashboard_fragment1 dash = new dashboard_fragment1();
                        dash.setArguments(bundle);
////                        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//
                        getChildFragmentManager().beginTransaction().
                                replace(R.id.frame_dashboard, dash).
                                commit();


//                        Fragment f = getChildFragmentManager().findFragmentById(R.id.frame_dashboard);
//                        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
//
//                        if (f == null) {
//                            Log.d("TAG", "onCreateView: fragment doesn't exist");
//                            dashboard_fragment1 dash = new dashboard_fragment1();
//                            dash.setArguments(bundle);
//                            transaction.add(R.id.frame_dashboard, dash);
//                        } else {
//                            Log.d("TAG", "onCreateView: fragment already exists");
//                            transaction.replace(R.id.frame_dashboard, f);
//                        }
//                        transaction.commit();

//                        getChildFragmentManager() is called it is child fragment of another fragment and also
//                        fragmentstatepageadapter is used instead of page adapter
//                        getActivity().getSupportFragmentManager().executePendingTransactions();
//                        If you're targeting sdk 24 and above you can use:
//
//FragmentTransaction.commitNow()
//instead of commit()
//If you're targeting older versions, try calling:
//FragmentManager.executePendingTransactions()
//after the call to commit()

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getContext(), " No data for th date Range", Toast.LENGTH_SHORT).show();

                    }


                } else if (response.code() == 401) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();

                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                } else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<CronJobResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

        });

    }

    public void refresh() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}


