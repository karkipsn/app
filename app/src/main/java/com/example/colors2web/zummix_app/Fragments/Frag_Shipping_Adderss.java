package com.example.colors2web.zummix_app.Fragments;

import android.content.Intent;
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
import android.widget.Button;

import com.example.colors2web.zummix_app.Activities.OrderActivity.OrderShippingEditActivity;
import com.example.colors2web.zummix_app.Activities.OrderActivity.OrderShippingLogsActivity;
import com.example.colors2web.zummix_app.Adapter.Order_Adapters.ShippingAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderShippingAddressesDetail;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Frag_Shipping_Adderss extends Fragment {

    ShippingAdapter sadapter;
    RecyclerView srecycleview;

    @BindView(R.id.btn_edit_shipf)
    Button edit;

    @BindView(R.id.btn_view_shiplogs)
    Button logs;

    String o_id, order_status, order_type, edit_shipping_address;

    List<OrderShippingAddressesDetail> ShipList;

    public Frag_Shipping_Adderss() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this, view);
        logs.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String type = preferences.getString("group_type", "");


        if (getArguments() != null) {

            o_id = getArguments().getString("b_id");
            order_status = getArguments().getString("order_status");
            order_type = getArguments().getString("order_type");
            Log.d("order_type",order_type);
            Log.d("order_status",order_status);
            Log.d("o_id",o_id);


            edit_shipping_address = getArguments().getString("edit_shipping_address");

            if (type != null) {
//                     Session::get('user')->group_type == 'Super Admin') &&
//                  ($orderDetails->order_status != 'Shipped') && ($orderDetails->order_type == 1))

                if (type.equals("Super Admin")|| order_type.equals("1")) {
//                edit button display

                    if (order_status.equals("Shipped")) {
                        edit.setVisibility(View.GONE);
                    }
//                    else if ()) {
//                        edit.setVisibility(View.VISIBLE);
//                   }
                   else {
                        edit.setVisibility(View.VISIBLE);
                    }
                }
            }

            if(edit_shipping_address!=null){
                Log.d("edit_shipping_address",edit_shipping_address);
            }

            if (edit_shipping_address.equals("1")) {
//                   logs button to display
                logs.setVisibility(View.VISIBLE);
            }

            edit = getActivity().findViewById(R.id.btn_edit_shipf);
            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                Pass order id alongside the intent
                    Intent editintent = new Intent(getContext(), OrderShippingEditActivity.class);
                    editintent.putExtra("o_id_edit", o_id);
                    startActivity(editintent);

                }
            });
            logs = getActivity().findViewById(R.id.btn_view_shiplogs);
            logs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//               Pass order id alongside the intent
                    Intent logsintent = new Intent(getContext(), OrderShippingLogsActivity.class);
                    logsintent.putExtra("o_id_logs", o_id);
                    startActivity(logsintent);

                }
            });

            sadapter = new ShippingAdapter(ShipList);
            srecycleview = getActivity().findViewById(R.id.recycler_view_frag_two);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            srecycleview.setHasFixedSize(true);
            srecycleview.setLayoutManager(mLayoutManager);

            srecycleview.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
//        srecycleview.addItemDecoration(new SimpleItemDecoration(getContext()));
            srecycleview.setItemAnimator(new DefaultItemAnimator());

            srecycleview.setAdapter(sadapter);

            loadAdapter();
        }
    }


    private void loadAdapter() {
        if (getArguments() != null) {

            ArrayList<OrderShippingAddressesDetail> sarraylist = getArguments().getParcelableArrayList("shipping_detail");
            sadapter.updateAnswers(sarraylist);
        }
    }

}
