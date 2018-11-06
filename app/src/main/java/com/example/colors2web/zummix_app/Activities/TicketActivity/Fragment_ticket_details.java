package com.example.colors2web.zummix_app.Activities.TicketActivity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.colors2web.zummix_app.Activities.TicketActivity.Ticket_Order_Details.TicketOrderDetails;
import com.example.colors2web.zummix_app.Adapter.Ticket_Adapter.TicketCommentAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.TicketDetails.CommentInput;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Comments;
import com.example.colors2web.zummix_app.POJO.TicketDetails.MarkInput;
import com.example.colors2web.zummix_app.POJO.TicketDetails.ReplyResponse;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Store;
import com.example.colors2web.zummix_app.POJO.TicketDetails.Support;
import com.example.colors2web.zummix_app.POJO.TicketDetails.User;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient1;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_ticket_details extends Fragment {
    TextView ticket_id, ticket_type, order, customer, customer_email, title, store_name, description, tracking, shipping_cost;
    TextView comment_textview, mark_closed, no_comments,message,order_details,re_open;
    EditText comment;
    CardView cardView1;
    RecyclerView comment_recycleview;
    CheckBox close_checkbox;
    TicketCommentAdapter cadapter;
    ArrayList<Comments> comments;
    ArrayList<User> users;
    String s_comment,ticket_no,support_id,order_no,store_id,ticket_title;
    Button comment_submit;
    APIInterface apiInterface;
     MarkInput markInput;
     Toolbar toolbar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        toolbar = getActivity().findViewById(R.id.toolbar);
        return inflater.inflate(R.layout.fragment_ticket_details, container, false);

    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
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
//        tracking = view.findViewById(R.id.ticket_details_tracking);
//        shipping_cost = view.findViewById(R.id.ticket_details_shipping_cost);
        comment_textview = view.findViewById(R.id.text_comment_title);
        comment_recycleview = view.findViewById(R.id.recyccleview_comments);
        mark_closed = view.findViewById(R.id.text_markclosed);
        no_comments = view.findViewById(R.id.text_no_comments);
        close_checkbox = view.findViewById(R.id.checkbox_mark_close);
        message = view.findViewById(R.id.text_message);
        comment = view.findViewById(R.id.text_comment);
        cardView1 = view.findViewById(R.id.card_view1);
        comment_submit = view.findViewById(R.id.comment_submit);
        order_details = view.findViewById(R.id.text_odetails);
        re_open = view.findViewById(R.id.text_reopen);
        cadapter = new TicketCommentAdapter(getContext(), comments);
        apiInterface = APIClient1.getClient().create(APIInterface.class);


        if (getArguments() != null) {

            Support support = (Support) getArguments().getSerializable("support");
            Store store = (Store) getArguments().getSerializable("store");
            users = getArguments().getParcelableArrayList("User");
            comments = getArguments().getParcelableArrayList("Comments");

            if (store != null) {

                store_name.setText(store.getStoreName());
                store_id = String.valueOf(store.getId());


            }
            if (support != null) {
                support_id = String.valueOf(support.getId());

                ticket_no =support.getTicketNumber();
                order_no =support.getOrder();
                ticket_id.setText(ticket_no);
                ticket_title = support.getTicketTitle();

//                toolbar.setTitle(ticket_title);


                String tt =String.valueOf(support.getTicketType());
                switch (tt){
                    case "1":
                        ticket_type.setText("Order Issue");
                        break;
                    case "2":
                        ticket_type.setText("Return Issue");
                        break;
                    case "3":
                        ticket_type.setText("Payment Issue");
                        break;
                    case "4":
                        ticket_type.setText("Shipping Issue");
                        break;
                    case "5":
                        ticket_type.setText("Other");
                        break;
                }

                title.setText(support.getTicketTitle());
                description.setText(support.getTicketDescription());
                customer.setText(support.getCustomerName());
                customer_email.setText(support.getCustomerEmail());
                order.setText(support.getOrder());
//                tracking.setText(support.getTrackingNumber());
//                shipping_cost.setText(support.getShippingCost());

                String statuss = support.getTicketStatus();
                //check ticket status for check box or textview of mark closed option
                if (statuss.equals("0")) {

                    mark_closed.setVisibility(View.VISIBLE);
                    close_checkbox.setVisibility(View.GONE);
                    message.setVisibility(View.GONE);
                    cardView1.setVisibility(View.GONE);
                    //comment option implememntation to be done
                } else {
                    mark_closed.setVisibility(View.GONE);
                    close_checkbox.setVisibility(View.VISIBLE);
                    message.setVisibility(View.VISIBLE);
                    cardView1.setVisibility(View.VISIBLE);
                    //comment option invisible implementation if needed to implement



                    comment_submit.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            submit_comment();
                        }
                    });

                }
            }

            order_details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(),TicketOrderDetails.class);
                    intent.putExtra("storeid",store_id);
                    intent.putExtra("supportid",support_id);
                    intent.putExtra("order_number",order_no);
                    startActivity(intent);
                }
            });
            


            if (comments.size() > 0) {
//                comment_textview.setVisibility(View.VISIBLE);
                comment_recycleview.setVisibility(View.VISIBLE);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mrecycleView.setHasFixedSize(true);
                comment_recycleview.setLayoutManager(mLayoutManager);

                comment_recycleview.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
                comment_recycleview.setItemAnimator(new DefaultItemAnimator());
                comment_recycleview.setAdapter(cadapter);
                cadapter.updateAdapter(comments, users,support);
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
                        alertDialogBuilder.setMessage("Are you sure you want to Mark Closed?");

                        alertDialogBuilder.setPositiveButton("yes",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface arg0, int arg1) {

//                                        Toast.makeText(getContext(), "You clicked yes button", Toast.LENGTH_LONG).show();
                                        mark_input();

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

//            REOPEN FUNCTION:

            re_open.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext());
                    alertDialogBuilder.setMessage("Are you sure you want to Re-Open?");

                    alertDialogBuilder.setPositiveButton("yes",
                            new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface arg0, int arg1) {

//                                    Toast.makeText(getContext(), "You clicked yes button", Toast.LENGTH_LONG).show();
                                    reopen_ticket();

                                }
                            });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();

                    Button buttonbackground = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    buttonbackground.setTextColor(Color.BLUE);

                    Button buttonbackground1 = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    buttonbackground1.setTextColor(Color.BLUE);


                }
            });

        }

    }

    private void reopen_ticket() {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");



        Call<ReplyResponse>call = apiInterface.getReopen(email,password,support_id);
        call.enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {

                if (response.isSuccessful()) {

                    ReplyResponse response1 = response.body();

                    if(response1.getReturnType().equals("success")){

                        ((TicketDetailsActivity)getActivity()).refresh_from_Fragment();

                    }else{
                        Toast.makeText(getContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();

                    }
//                        comment_submit.setFocusable(true);

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
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
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void mark_input() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        s_comment = comment.getText().toString();

        String reply = "1";
        String store_url ="";
        String user_id ="3";
        String ticket_status="0";

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        if(s_comment.equals(null)){

         markInput = new MarkInput(s_comment,reply,store_url,ticket_no,ticket_status,user_id);}
        else{
            markInput = new MarkInput(reply,store_url,ticket_no,ticket_status,user_id);}

        Call<ReplyResponse>call = apiInterface.putMarkClose(email,password,support_id,markInput);
        call.enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {

                if (response.isSuccessful()) {

                    ReplyResponse response1 = response.body();

                    if(response1.getReturnType().equals("success")){

                        comment.setText("");
                        comment.clearFocus();
                        comment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                if (!hasFocus) {
                                    hideKeyboard(v);
                                }
                            }
                        });

                        ((TicketDetailsActivity)getActivity()).refresh_from_Fragment();

                    }else{
                        Toast.makeText(getContext(), response1.getMessage(), Toast.LENGTH_SHORT).show();

                    }
//                        comment_submit.setFocusable(true);

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
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
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void submit_comment() {


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        s_comment = comment.getText().toString();

        String reply = "1";
        String store_url ="";
        String user_id ="3";

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        final CommentInput input = new CommentInput(s_comment,reply,store_url,ticket_no,user_id);

        Call<ReplyResponse>call = apiInterface.putCommentReply(email,password,support_id,input);
        call.enqueue(new Callback<ReplyResponse>() {
            @Override
            public void onResponse(Call<ReplyResponse> call, Response<ReplyResponse> response) {

                if (response.isSuccessful()) {

                    ReplyResponse response1 = response.body();

                    if(response1.getReturnType().equals("success")){

                        comment.setText("");
                        comment.clearFocus();
                        comment.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                            @Override
                            public void onFocusChange(View v, boolean hasFocus) {
                                if (!hasFocus) {
                                    hideKeyboard(v);
                                }
                            }
                        });

                        ((TicketDetailsActivity)getActivity()).refresh_from_Fragment();

                    }else{
                        Toast.makeText(getContext(),response1.getMessage(),Toast.LENGTH_SHORT).show();
                    }

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
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
            public void onFailure(Call<ReplyResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void hideKeyboard(View v) {
        InputMethodManager inputMethodManager =(InputMethodManager)getActivity().getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);

    }
}
