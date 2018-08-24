package com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.FieldOffice;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.OrderShippingAddress;
import com.example.colors2web.zummix_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShippingAddress_frag extends Fragment {


    @BindView(R.id.box_ship_oid)
    TextView mboxid;

    @BindView(R.id.box_ship_name)
    TextView mfname;

    @BindView(R.id.box_ship_email)
    TextView mfemail;

    @BindView(R.id.box_ship_phone)
    TextView mfphone;

    @BindView(R.id.box_ship_address)
    TextView mfadd;

    private Bundle bundle;
    OrderShippingAddress add;

    public ShippingAddress_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.box_shippindaddress, container, false);
        ButterKnife.bind(this, view);
        loadAdapter();
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void loadAdapter() {

        if (getArguments() != null) {

            OrderShippingAddress address = (OrderShippingAddress) getArguments().getSerializable("shipping_address");

            String oid = String.valueOf(address.getOrderId());
            String email = address.getCustomerEmail();
            String name = address.getCustomerFname() +" "+address.getCustomerMname() +" "+address.getCustomerLname();
            String add = address.getCustomerAddress1() +" "+ address.getCustomerCity() +" "+ address.getCustomerState() +" "+ address.getCustomerZip()
                    +" "+address.getCustomerCountry();
            String phone = address.getCustomerPhone1();

            mboxid.setText(oid);
            mfname.setText(name);
            mfemail.setText(email);
            mfphone.setText(phone);
            mfadd.setText(add);
        }
    }
}

