package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.items_inventory;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments.Frag_Elementary;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Items_Inventory_Fragment extends Fragment {

    private static final String TAG_ELEMENTORY_FRAGMENT = "Items_Elementory_FRAGMENT";
    Spinner spinner1;
        Button submit;
    Long cus_id;
//    SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_special_program_frag, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        spinner1 = view.findViewById(R.id.special_program_spinner);
        submit = view.findViewById(R.id.special_program_btn_submit);
        submit.setVisibility(View.GONE);
//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        spinner(email, password, apiInterface);

//        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
//                cus_id = sp.getCus_id();
//
//                Bundle bundle = new Bundle();
//                bundle.putString("ciid", String.valueOf(cus_id));
//                Frag_Elementary inActive = new Frag_Elementary();
//                inActive.setArguments(bundle);
//                getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//
//                getActivity().getSupportFragmentManager().beginTransaction().
//                        add(R.id.frame_special_program, inActive).
//                        addToBackStack(TAG_ELEMENTORY_FRAGMENT).
//                        commit();
//
//                Toast.makeText(getContext(),
//                        " Name : " + sp.getName(), Toast.LENGTH_SHORT).show();
//                Log.d("cus_id", String.valueOf(cus_id));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(0);
//                cus_id = sp.getCus_id();
//
//                Bundle bundle = new Bundle();
//                bundle.putString("ciid", String.valueOf(cus_id));
//                Frag_Elementary inActive = new Frag_Elementary();
//                inActive.setArguments(bundle);
//                getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//
//                getActivity().getSupportFragmentManager().beginTransaction().
//                        add(R.id.frame_special_program, inActive).
//                        addToBackStack(TAG_ELEMENTORY_FRAGMENT).
//                        commit();
//            }
//        });

//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                call_final();
//
//
//            }
//        });


//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                call_final();
//                mSwipeRefreshLayout.setRefreshing(false);
//
//            }
//        });

    }

//    private void call_final() {
//
//        Bundle bundle = new Bundle();
//        bundle.putString("ciid", String.valueOf(cus_id));
//        Frag_Elementary inActive = new Frag_Elementary();
//        inActive.setArguments(bundle);
//        getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//
//        getActivity().getSupportFragmentManager().beginTransaction().
//                add(R.id.frame_special_program, inActive).
//                addToBackStack(TAG_ELEMENTORY_FRAGMENT).
//                commit();
////                Not checked the null condition or empty condition as i think is not necessary
////                Bcoz we are displaying it in this same child fragment so I am replacing the frame layout
//    }

    private void spinner(String email, String password, APIInterface apiInterface) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<OrdGrpByCus> call = apiInterface.getOrdersbyGroup(email, password);
        call.enqueue(new Callback<OrdGrpByCus>() {
            @Override
            public void onResponse(Call<OrdGrpByCus> call, Response<OrdGrpByCus> response) {

                if (response.isSuccessful()) {
                    OrdGrpByCus resp1 = response.body();

                    List<Order> order = resp1.getOrders();
                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (order != null) {

                        ArrayList<SpinnerPojo> countryList = new ArrayList<>();

                        for (int i = 0; i < order.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = order.get(i).getCustomerName();
                            Long cus_id = order.get(i).getCustomerId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            countryList.add(order1);
//                            spin.add(name);
//                            spin_value.add(cus_id);
                        }
                        Log.d("spinner_list", countryList.toString());


                        ArrayAdapter<SpinnerPojo> adapter2 = new ArrayAdapter<SpinnerPojo>(getContext(),
                                android.R.layout.simple_spinner_item, countryList);

                        adapter2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                        spinner1.setAdapter(adapter2);

                        spinner1.setPrompt("Select Customers");
                        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                                cus_id = sp.getCus_id();


                                Bundle bundle = new Bundle();
                                bundle.putString("ciid", String.valueOf(cus_id));
                                Frag_Elementary inActive = new Frag_Elementary();
                                inActive.setArguments(bundle);
                                getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

                                getActivity().getSupportFragmentManager().beginTransaction().
                                        add(R.id.frame_special_program, inActive).
                                        addToBackStack(TAG_ELEMENTORY_FRAGMENT).
                                        commit();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(0);
                                cus_id = sp.getCus_id();


                                Bundle bundle = new Bundle();
                                bundle.putString("ciid", String.valueOf(cus_id));
                                Frag_Elementary inActive = new Frag_Elementary();
                                inActive.setArguments(bundle);
                                getActivity().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

                                getActivity().getSupportFragmentManager().beginTransaction().
                                        add(R.id.frame_special_program, inActive).
                                        addToBackStack(TAG_ELEMENTORY_FRAGMENT).
                                        commit();
                            }
                        });

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getContext(), d, Toast.LENGTH_SHORT).show();
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
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrdGrpByCus> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });
    }

}
