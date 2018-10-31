package com.example.colors2web.zummix_app.Activities.TicketActivity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.Adapter.Ticket_Adapter.TicketLineItemsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.TicketDetails.ReturnedItem;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class Fragment_ticket_items extends Fragment {

    TicketLineItemsAdapter padapter;
    RecyclerView mrecycleView;
    List<ReturnedItem> UList = new ArrayList<>();
    Context mContext;
    TextView text;
    SwipeRefreshLayout mswipeRefreshLayout;

    public Fragment_ticket_items() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_ticket_items, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        padapter = new TicketLineItemsAdapter(getContext(),UList);
        text = view.findViewById(R.id.display_null);

        mswipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mswipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mswipeRefreshLayout.setEnabled(false);

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

            ArrayList<ReturnedItem> logs1 = getArguments().getParcelableArrayList("ReturnedList");

            if(logs1.size()>0){
                padapter.updateAnswers(logs1);
                text.setVisibility(View.GONE);
            }else{
                text.setVisibility(View.VISIBLE);
                text.setText("You have no return items");
            }
        }
    }

    public void refresh() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }
}

