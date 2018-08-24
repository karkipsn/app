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
import com.example.colors2web.zummix_app.POJO.customers.ShipStationCredential;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class frag_shipstation_credential extends Fragment {


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

    public frag_shipstation_credential() {
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

            String sidn = getArguments().getString("sidn");
            String sid = getArguments().getString("sid");
            String ssid = getArguments().getString("ssid");
            String scid = getArguments().getString("scid");
            String sca = getArguments().getString("sca");

            mname.setText(sidn);
            memail.setText(sid);
            madd.setText(ssid);
            mcemail.setText(scid);
            mcname.setText(sca);


        }else{
            Toast.makeText(getContext(), "NO data to display", Toast.LENGTH_SHORT).show();
        }

    }
}





