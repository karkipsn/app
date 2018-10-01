package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.fragment_customers_list;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Customer_Adapter.ParentAdapter_customer;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.POJO.customers.Customers;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Customer_list_fragment extends Fragment {

    //    IMport from by parent Id class
    APIInterface apiInterface;
    RecyclerView mrecycleView;
    ParentAdapter_customer padapter;
    List<Customers> CList = new ArrayList<>();
//    Toolbar toolbar;
    SwipeRefreshLayout mSwipeRefreshLayout;

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

        apiInterface = APIClient.getClient().create(APIInterface.class);

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


//        toolbar =  getActivity().findViewById(R.id.toolbar);
//       ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        mrecycleView = view.findViewById(R.id.recycler_view_customer);
        padapter = new ParentAdapter_customer(getContext(),CList);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
//        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

//        mrecycleView.addItemDecoration(new SimpleItemDecoration(this));
        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));

        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        loadAdapter(email, password );
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                CList.clear();
                padapter.notifyDataSetChanged();
                loadAdapter(email,password);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    private void loadAdapter(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<CustomerResponse> call = apiInterface.getParentCustomer(email, password);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {

                if (response.isSuccessful()) {
                    CustomerResponse resp1 = response.body();

                    List<Customers> cus = resp1.getmCustomer();

                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {

                            Customers cus1 = new Customers();

                            String p_id = String.valueOf(cus.get(i).getParentId());
//                           toolbar.setTitle("Parent Id:"+ p_id);


                            long id = cus.get(i).getId();
                            String cname = cus.get(i).getCompanyName();
                            String cemail = cus.get(i).getCompanyEmail();
                            String add1 = cus.get(i).getCompanyAddress1();
                            String city = cus.get(i).getCompanyCity();
                            String state = cus.get(i).getCompanyState();
                            String country = cus.get(i).getCompanyCountry();
                            String zip = cus.get(i).getCompanyZip();
                            String fname = cus.get(i).getContactFname();
//                            String mname = cus.get(i).getContactMname();
                            String lname = cus.get(i).getContactLname();
                            String coemail = cus.get(i).getContactEmail();

//                            parentId.setText("Parent Id:" + p_id);

                            cus1.setId(id);
                            cus1.setCompanyName(cname);
                            cus1.setCompanyEmail(cemail);
                            cus1.setCompanyAddress1(add1);
                            cus1.setCompanyCity(city);
                            cus1.setCompanyState(state);
                            cus1.setCompanyCountry(country);
                            cus1.setCompanyZip(zip);
                            cus1.setContactFname(fname);
                            cus1.setContactMname("");
                            cus1.setContactLname(lname);
                            cus1.setContactEmail(coemail);

                            CList.add(cus1); // must be the object of empty list initiated
                        }
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        padapter.updateAnswers(CList);//adapter's content is updated and update function is called;

                        //send parameters according to urs adapter view setup.);

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getContext(),d,Toast.LENGTH_LONG).show();
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
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
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
