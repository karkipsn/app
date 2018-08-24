package com.example.colors2web.zummix_app.Activities.CustomersSearchActivity.Cus_Fragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;
import butterknife.BindView;
import butterknife.ButterKnife;


public class Frag_customer extends Fragment {

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

    public Frag_customer() {
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


            String cname = getArguments().getString("cname");
            String coadd = getArguments().getString("coadd");
            String cemail = getArguments().getString("cemail");
            String cadd = getArguments().getString("cadd");
            String coname = getArguments().getString("coname");

            mname.setText(cname);
            memail.setText(cemail);
            madd.setText(cadd);
            mcemail.setText(coadd);
            mcname.setText(coname);
        }
    }
    }




