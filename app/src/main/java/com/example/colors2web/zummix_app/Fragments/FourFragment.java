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

public class FourFragment extends Fragment{

    RecyclerView mrecyclerView;

    LineItemsAdapter ladapter;
    APIInterface apiInterface;

    List<ItemDetail> ItmList = new ArrayList<>();
    public FourFragment() {
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
        return inflater.inflate(R.layout.fragment_four, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        apiInterface = APIClient.getClient().create(APIInterface.class);
        ladapter = new LineItemsAdapter(ItmList);

        mrecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view1);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);

//        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());

        mrecyclerView.setAdapter(ladapter);
        loadAdapter();
    }

    private void loadAdapter() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getContext());


        final Long id = preferences1.getLong("id", 0);
        Log.d("id",String.valueOf(id));


        Call<Order2Response> call = apiInterface.getsecsearch(email, password, id);

        call.enqueue(new Callback<Order2Response>() {
            @Override
            public void onResponse(Call<Order2Response> call, Response<Order2Response> response) {

                if (response.isSuccessful()) {
                    Order2Response resp1 = response.body();

                    com.example.colors2web.zummix_app.POJO.Order2POJO.OrderDetails order = resp1.getOrderDetails();

                    if (order != null) {

                        Order2Response dep = response.body();

                        List<ItemDetail> deps = dep.getOrderDetails().getItemDetails();

                        for (int i = 0; i < deps.size(); i++) {

                            ItemDetail dis = new ItemDetail();

                            String sku = deps.get(i).getItemSku();
                            String name = deps.get(i).getItemName();
                            String quantity = String.valueOf(deps.get(i).getItemQuantity());


                            dis.setItemSku(sku);
                            dis.setItemName(name);
                            dis.setItemQuantity(Long.valueOf(quantity));

                            ItmList.add(dis); // must be the object of empty list initiated
                        }

                        ladapter.updateAnswers(ItmList);//adapter's content is updated and update function is called;
                        //send parameters according to urs adapter view setup.

                    } else {
                        Toast.makeText(getContext(), "No data to display", Toast.LENGTH_SHORT).show();
                        Log.d("Error", response.errorBody().toString());


                    }

                } else if (response.code() == 401) {

                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Order2Response> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
