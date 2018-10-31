package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Ticket_Adapter.TicketAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Tickets.TicketResponse;
import com.example.colors2web.zummix_app.POJO.Tickets.Tickets;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIClient1;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Ticket_Fragment extends Fragment {



    APIInterface apiInterface;
    RecyclerView mrecycleView;
    TicketAdapter padapter;
    TextView textView;
    List<Tickets> CList = new ArrayList<>();
    //    Toolbar toolbar;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public Ticket_Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_customer_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = APIClient1.getClient().create(APIInterface.class);
        textView= getView().findViewById(R.id.display_null);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        mrecycleView = view.findViewById(R.id.recycler_view_customer);
        padapter = new TicketAdapter(getContext(),CList);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
        mrecycleView.setLayoutManager(mlayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));

        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

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

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
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
                        Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();
                        textView.setVisibility(View.GONE);
                        padapter.updateAnswers(CList);//adapter's content is updated and update function is called;

                        //send parameters according to urs adapter view setup.);

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getContext(),d,Toast.LENGTH_LONG).show();
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

                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }
                else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                }else {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TicketResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
        
    }

