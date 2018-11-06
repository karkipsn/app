package com.example.colors2web.zummix_app.Activities.TicketActivity.Ticket_Order_Details.OrderFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.ShippingDetails;
import com.example.colors2web.zummix_app.R;

public class Fragment_ticket_order_Shipping extends Fragment {
    ShippingDetails od;
    TextView name,street,city,state,zip,country;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ticket_shipping_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.tb_name);
        street = view.findViewById(R.id.tb_street_address);
        city = view.findViewById(R.id.tb_city);
        state = view.findViewById(R.id.tb_state);
        zip = view.findViewById(R.id.tb_zip);
        country = view.findViewById(R.id.tb_country);

        if (getArguments() != null) {

            od = (ShippingDetails) getArguments().getSerializable("sdetails");

        name.setText(od.getFirstName() + " " + od.getLastName());
        street.setText(od.getAddress1() + " " +od.getAddress2());
        city.setText(od.getCity());
        state.setText(od.getState());
        zip.setText(od.getZip());
        country.setText(od.getCountry());
        }

    }
}
