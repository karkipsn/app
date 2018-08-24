package com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.FieldOffice;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FieldOffice_frag extends Fragment {


    @BindView(R.id.box_field_id)
    TextView mboxid;

    @BindView(R.id.box_field_name)
    TextView mfname;

    @BindView(R.id.box_field_email)
    TextView mfemail;

    @BindView(R.id.box_filed_phone)
    TextView mfphone;

    @BindView(R.id.box_filed_address)
    TextView mfadd;

    public FieldOffice_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.box_fieldoffice, container, false);
        ButterKnife.bind(this, view);
        loadAdapter();
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private void loadAdapter() {

        if (getArguments() != null) {

            FieldOffice office = (FieldOffice) getArguments().getSerializable("fieldOffice");
            if(office!=null){
            Log.d("office",office.toString());}else{
                Log.d("office","error");
            }

            String boxid = String.valueOf(office.getId());
            String email = office.getManager1Email();
            String name = office.getManager1Name();
            String add = office.getAddress1() +" "+ office.getCity() +" "+
                    office.getState() +" "+ office.getZip()+" "+office.getCountry();
            String phone = office.getManager1Phone();

            mboxid.setText(boxid);
            mfname.setText(name);
            mfemail.setText(email);
            mfphone.setText(phone);
            mfadd.setText(add);

        }
}}
