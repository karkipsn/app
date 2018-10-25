package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.tickets_fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.Adapter.Ticket_Adapter.ReturnAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Tickets.ReturnTicket;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class Return_Fragment extends Fragment {

    ReturnAdapter padapter;
    RecyclerView mrecycleView;
    List<ReturnTicket> UList = new ArrayList<>();
    Context mContext;
    TextView text;

    public Return_Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_customer_list, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        padapter = new ReturnAdapter(getContext(),UList);
        text = view.findViewById(R.id.display_null);

        mrecycleView = view.findViewById(R.id.recycler_view_customer);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mLayoutManager);

        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);
        loadAdapter();
    }

    private void loadAdapter() {

        if (getArguments() != null) {

            ArrayList<ReturnTicket> logs1 = getArguments().getParcelableArrayList("ReturnList");

            if(logs1.size()>0){
                padapter.updateAnswers(logs1);
                text.setVisibility(View.GONE);
            }else{
                text.setVisibility(View.VISIBLE);
                text.setText("You have no return tickets");
            }

        }
    }

    public void refresh() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}
