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

import com.example.colors2web.zummix_app.Adapter.BoxesAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.SimpleItemDecoration;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Box;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Frag_Box extends Fragment {

    BoxesAdapter badapter;
    RecyclerView brecyclerView;
    List<Box> boxList = new ArrayList<>();

    public Frag_Box() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_five, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        badapter = new BoxesAdapter(getContext(),boxList);

        brecyclerView = getActivity().findViewById(R.id.recycler_view5);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        brecyclerView.setHasFixedSize(true);
        brecyclerView.setLayoutManager(mLayoutManager);

//        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        brecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
        brecyclerView.setItemAnimator(new DefaultItemAnimator());

        brecyclerView.setAdapter(badapter);
        loadAdapter();
    }

    private void loadAdapter() {
        if (getArguments() != null) {

            ArrayList<Box> barraylist = getArguments().getParcelableArrayList("box_detail");
            Log.d("box_detail", barraylist.toString());
            badapter.updateAnswers(barraylist);
        }
    }


}
