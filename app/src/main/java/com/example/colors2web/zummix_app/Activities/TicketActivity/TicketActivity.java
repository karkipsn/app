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
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Ticket_Adapter.TicketAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Tickets.TicketResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.Tickets;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketActivity extends AppCompatActivity {
    
    APIInterface apiInterface;
    RecyclerView mrecycleView;
    TicketAdapter padapter;
    TextView textView;
    List<Tickets> CList = new ArrayList<>();
    ArrayList<Tickets> SearchList = new ArrayList<>();
    //    Toolbar toolbar;
    SwipeRefreshLayout mSwipeRefreshLayout;
    Toolbar toolbar;
    String email,password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_activity);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        textView= findViewById(R.id.display_null);

        toolbar = findViewById(R.id.ticket_details_toolbar);

        //for toolbarsetup with back arrow
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tickets");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//                super.onBackPressed();
            }
        });

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mrecycleView = findViewById(R.id.recycler_view_customer);
        padapter = new TicketAdapter(TicketActivity.this,CList);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(TicketActivity.this);
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(TicketActivity.this, LinearLayoutManager.HORIZONTAL, 16));

        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(TicketActivity.this);
         email = preferences.getString("email", "");
         password = preferences.getString("password", "");

        loadTickets(email, password );

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CList.clear();
                padapter.notifyDataSetChanged();
                loadTickets(email,password);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadTickets(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(TicketActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<TicketResponse> call = apiInterface.getTickets(email, password);
        call.enqueue(new Callback<TicketResponse>() {
            @Override
            public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {

                if (response.isSuccessful()) {

                    TicketResponse resp1 = response.body();

                    List<Tickets> cus = resp1.getTickets();

                    if (cus != null && cus.size()>0) {

                        for (int i = 0; i < cus.size(); i++) {

                            Tickets tickets = new Tickets();

                            String id = cus.get(i).getMid();
                            String store_id = cus.get(i).getMstore_id();
                            String cname = cus.get(i).getMcustomer_name();
                            String sname = cus.get(i).getMstore_name();
                            String surl = cus.get(i).getMstore_url();
                            String created = cus.get(i).getMcreated_at();
                            String updated = cus.get(i).getMupdated_at();
                            String ticket_no = cus.get(i).getMticket_number();
                            String ticket_status = cus.get(i).getMticket_status();
                            String order = cus.get(i).getMorder();


                            tickets.setMid(id);
                            tickets.setMstore_id(store_id);
                            tickets.setMcustomer_name(cname);
                            tickets.setMstore_name(sname);
                            tickets.setMstore_url(surl);
                            tickets.setMcreated_at(created);
                            tickets.setMupdated_at(updated);
                            tickets.setMticket_number(ticket_no);
                            tickets.setMticket_status(ticket_status);
                            tickets.setMorder(order);

                            CList.add(tickets); // must be the object of empty list initiated
                        }
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(TicketActivity.this, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        textView.setVisibility(View.GONE);
                        padapter.updateAnswers(CList);//adapter's content is updated and update function is called;

                        //send parameters according to urs adapter view setup.);

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(TicketActivity.this,d,Toast.LENGTH_LONG).show();
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(d);
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(TicketActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(TicketActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }
                else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(TicketActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(TicketActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(TicketActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }{
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {

        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ticket_menu, menu);
//        ImageView img;
//
//        img = (ImageView) menu.findItem(R.id.image).getActionView();
//        img.setImageResource(android.R.drawable.ic_menu_search);
//
//        img.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                getSupportFragmentManager().beginTransaction().
//                        replace(R.id.main_toolbar, new SearchFragment()).commit();
//            }
//        });

//        return true;

        final MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
        final SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setQueryHint("Enter Ticket Number......");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                // Toast like print
                Toast.makeText( TicketActivity.this,"SearchOnQueryTextSubmit: " + query,Toast.LENGTH_SHORT).show();
                if( ! searchView.isIconified()) {
                    searchView.setIconified(true);
                }

                final ProgressDialog progressDialog = new ProgressDialog(TicketActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();

                Call<TicketResponse> call = apiInterface.getSearchTickets(email, password,query);
                call.enqueue(new Callback<TicketResponse>() {
                    @Override
                    public void onResponse(Call<TicketResponse> call, Response<TicketResponse> response) {

                        if (response.isSuccessful()) {

                            TicketResponse resp1 = response.body();

                            List<Tickets> cus = resp1.getTickets();

                            if (cus != null && cus.size()>0) {

                                for (int i = 0; i < cus.size(); i++) {

                                    Tickets tickets = new Tickets();

                                    String id = cus.get(i).getMid();
                                    String store_id = cus.get(i).getMstore_id();
                                    String cname = cus.get(i).getMcustomer_name();
                                    String sname = cus.get(i).getMstore_name();
                                    String surl = cus.get(i).getMstore_url();
                                    String created = cus.get(i).getMcreated_at();
                                    String updated = cus.get(i).getMupdated_at();
                                    String ticket_no = cus.get(i).getMticket_number();
                                    String ticket_status = cus.get(i).getMticket_status();
                                    String order = cus.get(i).getMorder();


                                    tickets.setMid(id);
                                    tickets.setMstore_id(store_id);
                                    tickets.setMcustomer_name(cname);
                                    tickets.setMstore_name(sname);
                                    tickets.setMstore_url(surl);
                                    tickets.setMcreated_at(created);
                                    tickets.setMupdated_at(updated);
                                    tickets.setMticket_number(ticket_no);
                                    tickets.setMticket_status(ticket_status);
                                    tickets.setMorder(order);

                                    SearchList.add(tickets); // must be the object of empty list initiated
                                }
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(TicketActivity.this, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                textView.setVisibility(View.GONE);
//
                                Intent intent = new Intent(TicketActivity.this,TicketSearchActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putParcelableArrayList("SearchedTicketList",SearchList);
                                intent.putExtra("query",query);
                                intent.putExtras(bundle);
                                startActivity(intent);

                            } else {
                                String d = response.body().getMessage();
                                Toast.makeText(TicketActivity.this,d,Toast.LENGTH_LONG).show();
                                textView.setVisibility(View.VISIBLE);
                                textView.setText(d);
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                            }
                        } else if (response.code() == 401) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketActivity.this, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());


                        } else if (response.code() == 404) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketActivity.this, "InValid Web Address", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        }
                        else if (response.code() == 500) {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }

                            Toast.makeText(TicketActivity.this, "Internal Server Error", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());
                        }else {

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(TicketActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<TicketResponse> call, Throwable t) {
                        call.cancel();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Log.e("response-failure", t.toString());
                        Toast.makeText(TicketActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });

                myActionMenuItem.collapseActionView();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
                return false;
            }
        });
        return true;
    }


//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        switch (item.getItemId()) {
//
//            case R.id.image:
//
////                getSupportFragmentManager().beginTransaction().
////                        replace(R.id.main_toolbar, new SearchFragment()).addToBackStack(null).commit();
//
//                return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }

    @Override
    public void onBackPressed() {
//       moveTaskToBack(true);
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
        super.onBackPressed();
    }
}
