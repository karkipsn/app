package com.example.colors2web.zummix_app.Fragments;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.LineItemsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.Order2POJO.ItemDetail;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag_ItemDetails extends Fragment{
    private View rootView ;

    RecyclerView mrecyclerView;
    LineItemsAdapter ladapter;

    APIInterface apiInterface;

    List<ItemDetail> ItmList = new ArrayList<>();

    public Frag_ItemDetails() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        if (rootView != null) {
//            ((ViewGroup) rootView.getParent()).removeView(rootView);
////            ((ViewGroup) rootView.re)
//
//
//            // Initialise your layout here
//
////        } else {
////            ((ViewGroup) rootView.getParent()).removeView(rootView);
////        }
//        }
        rootView = inflater.inflate(R.layout.fragment_four, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);


        apiInterface = APIClient.getClient().create(APIInterface.class);
        ladapter = new LineItemsAdapter(ItmList);

        mrecyclerView = getActivity().findViewById(R.id.recycler_view1);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);

       mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
//        mrecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(ladapter);
        log();
    }

    private void log(){
        if (getArguments() != null) {

            ArrayList<ItemDetail> arraylist = getArguments().getParcelableArrayList("item_detail");
            ladapter.updateAnswers(arraylist);
        }
    }

}
