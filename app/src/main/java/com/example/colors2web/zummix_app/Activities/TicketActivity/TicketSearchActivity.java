package com.example.colors2web.zummix_app.Activities.TicketActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Ticket_Adapter.TicketAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Tickets.TicketResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.Tickets;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketSearchActivity  extends AppCompatActivity{

    RecyclerView mrecycleView;
    TicketAdapter padapter;
    TextView textView;
    List<Tickets> TicketList = new ArrayList<>();
    //    Toolbar toolbar;
//    SwipeRefreshLayout mSwipeRefreshLayout;
    Toolbar toolbar;
    String email,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_activity);

//        apiInterface = APIClient.getClient().create(APIInterface.class);
        textView= findViewById(R.id.display_null);

        toolbar = findViewById(R.id.ticket_details_toolbar);

        //for toolbarsetup with back arrow
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String query = getIntent().getStringExtra("query");

        if(query!=null){

            getSupportActionBar().setTitle("Tickets of  " + query);
        }else{
            getSupportActionBar().setTitle("Tickets" );
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//                super.onBackPressed();
            }
        });

//        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mrecycleView = findViewById(R.id.recycler_view_customer);
        padapter = new TicketAdapter(TicketSearchActivity.this,TicketList);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(TicketSearchActivity.this);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(TicketSearchActivity.this, LinearLayoutManager.HORIZONTAL, 16));

        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TicketSearchActivity.this);
        email = preferences.getString("email", "");
        password = preferences.getString("password", "");

        loadTickets(email, password );

//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                CList.clear();
//                padapter.notifyDataSetChanged();
//                loadTickets(email,password);
//                mSwipeRefreshLayout.setRefreshing(false);
//            }
//        });
    }

    private void loadTickets(String email, String password) {


        if (getIntent() != null) {

            ArrayList<Tickets> tickets = getIntent().getParcelableArrayListExtra("SearchedTicketList");

            if(tickets.size()>0){

                padapter.cleardata();
                padapter.updateAnswers(tickets);
                textView.setVisibility(View.GONE);
            }else{
                textView.setVisibility(View.VISIBLE);
                textView.setText("You have no tickets");
            }

        }
    }{
    }
}
