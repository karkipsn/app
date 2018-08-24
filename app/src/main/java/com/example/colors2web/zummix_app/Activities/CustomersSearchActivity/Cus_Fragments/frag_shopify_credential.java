package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.POJO.customers.Customers;
import com.example.colors2web.zummix_app.POJO.customers.ShopifyCredential;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class frag_shopify_credential extends Fragment {

    APIInterface apiInterface;

    @BindView(R.id.cus_parent_cname)
    TextView mname;

    @BindView(R.id.cus_parent_cemail)
    TextView memail;

    @BindView(R.id.cus_parent_cadd)
    TextView madd;

    @BindView(R.id.cus_parent_coname)
    TextView mcname;

    @BindView(R.id.cus_parent_coemail)
    TextView mcemail;

    public frag_shopify_credential(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.frag_cus_cus, container, false);
        ButterKnife.bind(this, view);
        apiInterface = APIClient.getClient().create(APIInterface.class);
        loadAdapter();
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
    }

    private void loadAdapter() {

        if (getArguments() != null) {

            String sc_id = getArguments().getString("sc_id");
            String sc_c_at = getArguments().getString("sc_c_at");
            String sc_email = getArguments().getString("sc_email");
            String sc_vendor = getArguments().getString("sc_vendor");
            String sc_cus_id = getArguments().getString("sc_cus_id");


                            mname.setText(sc_id);
                            memail.setText(sc_c_at);
                            madd.setText(sc_email);
                            mcemail.setText(sc_vendor);
                            mcname.setText(sc_cus_id);

        }
        else{
            Toast.makeText(getContext(),"No data to display",Toast.LENGTH_SHORT).show();
        }
    }
}


