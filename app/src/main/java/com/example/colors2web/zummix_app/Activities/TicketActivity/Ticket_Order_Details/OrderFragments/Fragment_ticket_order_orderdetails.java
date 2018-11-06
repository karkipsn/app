package com.example.colors2web.zummix_app.Activities.TicketActivity.Ticket_Order_Details.OrderFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.colors2web.zummix_app.POJO.TicketOrderDetails.OrderDetails;
import com.example.colors2web.zummix_app.R;

public class Fragment_ticket_order_orderdetails extends Fragment {
    TextView o_date, order, ship_method, ship_cost, capplied, cused, gtotal;
    OrderDetails od;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ticket_orderdetails, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        o_date = view.findViewById(R.id.tod_order_date);
        order = view.findViewById(R.id.tod_order);
        ship_method = view.findViewById(R.id.tod_ship_method);
        ship_cost = view.findViewById(R.id.tod_ship_cost);
        capplied = view.findViewById(R.id.tod_credit_applied);
        cused = view.findViewById(R.id.tod_credit_used);
        gtotal = view.findViewById(R.id.tod_grand_total);

        if (getArguments() != null) {

            od = (OrderDetails) getArguments().getSerializable("odetails");

        o_date.setText(od.getCreatedAt());
        order.setText(od.getSaleNumber());
        ship_method.setText(od.getShipMethod());
        ship_cost.setText(od.getShipCost());
        capplied.setText(od.getCreditTypeName());
        cused.setText(od.getCreditUsed());
        gtotal.setText(od.getGrandTotal());
    }}
}
