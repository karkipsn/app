package com.example.colors2web.zummix_app.Activities.TicketActivity;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Ticket_Adapter.TicketCommentAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Comments;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Store;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Support;
import com.example.colors2web.zummix_app.POJO.TicketDetails.User;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;

public class Fragment_ticket_details extends Fragment {
    TextView ticket_id, ticket_type, order, customer, customer_email, title, store_name, description, tracking, shipping_cost;
    TextView comment_textview, mark_closed, no_comments;
    RecyclerView comment_recycleview;
    CheckBox close_checkbox;
    TicketCommentAdapter cadapter;
    ArrayList<Comments> comments;
    ArrayList<User> users;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ticket_details, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ticket_id = view.findViewById(R.id.ticket_details_id);
        ticket_type = view.findViewById(R.id.ticket_details_type);
        order = view.findViewById(R.id.ticket_details_order_id);
        customer = view.findViewById(R.id.ticket_details_customer);
        customer_email = view.findViewById(R.id.ticket_details_customer_email);
        title = view.findViewById(R.id.ticket_details_title);
        store_name = view.findViewById(R.id.ticket_details_store_name);
        description = view.findViewById(R.id.ticket_details_description);
        tracking = view.findViewById(R.id.ticket_details_tracking);
        shipping_cost = view.findViewById(R.id.ticket_details_shipping_cost);
        comment_textview = view.findViewById(R.id.text_recycleview);
        comment_recycleview = view.findViewById(R.id.recyccleview_comments);
        mark_closed = view.findViewById(R.id.text_markclosed);
        no_comments = view.findViewById(R.id.text_no_comments);
        close_checkbox = view.findViewById(R.id.checkbox_mark_close);
        cadapter = new TicketCommentAdapter(getContext(), comments);

        if (getArguments() != null) {

            Support support = (Support) getArguments().getSerializable("support");
            Store store = (Store) getArguments().getSerializable("store");
            users = getArguments().getParcelableArrayList("User");
            comments = getArguments().getParcelableArrayList("Comments");

            if (store != null) {

                store_name.setText(store.getStoreName());

            }
            if (support != null) {
                ticket_id.setText(support.getTicketNumber());
                ticket_type.setText(String.valueOf(support.getTicketType()));
                title.setText(support.getTicketTitle());
                description.setText(support.getTicketDescription());
                customer.setText(support.getCustomerName());
                customer_email.setText(support.getCustomerEmail());
                order.setText(support.getOrder());
                tracking.setText(support.getTrackingNumber());
                shipping_cost.setText(support.getShippingCost());

                String statuss = support.getTicketStatus();
                //check ticket status for check box or textview of mark closed option
                if (statuss.equals("0")) {
                    mark_closed.setVisibility(View.VISIBLE);
                    close_checkbox.setVisibility(View.GONE);
                    //comment option implememntation to be done
                } else {
                    mark_closed.setVisibility(View.GONE);
                    close_checkbox.setVisibility(View.VISIBLE);
                    //comment option invivsible implementation if needed to implement
                }
            }

            if (comments.size() > 0) {
//                comment_textview.setVisibility(View.VISIBLE);
                comment_recycleview.setVisibility(View.VISIBLE);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mrecycleView.setHasFixedSize(true);
                comment_recycleview.setLayoutManager(mLayoutManager);

                comment_recycleview.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
                comment_recycleview.setItemAnimator(new DefaultItemAnimator());
                comment_recycleview.setAdapter(cadapter);
                cadapter.updateAdapter(comments, users);
                no_comments.setVisibility(View.GONE);


            } else {
//                comment_textview.setVisibility(View.GONE);
                comment_recycleview.setVisibility(View.GONE);
                no_comments.setVisibility(View.VISIBLE);
                no_comments.setText("You have no comments");
            }

            //checkbox_operations
            close_checkbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (close_checkbox.isChecked()) {

                        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                        alertDialogBuilder.setMessage("Are you sure,You wanted tomark CLosed ?");

                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

                                        Toast.makeText(getContext(), "You clicked yes button", Toast.LENGTH_LONG).show();
                                        //codes ..................

                                    }
                                });

                        alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                        finish();
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alertDialog = alertDialogBuilder.create();
                        alertDialog.show();

                        Button buttonbackground = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
//                buttonbackground.setBackgroundColor(Color.BLUE);
                        buttonbackground.setTextColor(Color.BLUE);

                        Button buttonbackground1 = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
//                buttonbackground1.setBackgroundColor(Color.BLUE);
                        buttonbackground1.setTextColor(Color.BLUE);

                    }
                }
            });


        }

    }
}
