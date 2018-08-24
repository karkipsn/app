package com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.CompanyDetails;
import com.example.colors2web.zummix_app.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyDetails_frag extends Fragment {


    @BindView(R.id.box_cd_id)
    TextView mid;

    @BindView(R.id.box_cd_pid)
    TextView mpid;

    @BindView(R.id.box_cd_alais)
    TextView malais;

    @BindView(R.id.box_cd_name)
    TextView mfname;

    @BindView(R.id.box_cd_email)
    TextView memail;

    @BindView(R.id.box_cd_phone)
    TextView mphone;

    @BindView(R.id.box_cd_address)
    TextView mfadd;

    public CompanyDetails_frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.box_customeritems_child, container, false);
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

            CompanyDetails address = (CompanyDetails) getArguments().getSerializable("companyDetails");

            String oid = String.valueOf(address.getId());
            String pid = String.valueOf(address.getParentId());
            String alais = address.getCompanyAlias();
            String email = address.getCompanyEmail();
            String name = address.getContactFname() +" "+address.getContactMname() +" "+address.getContactLname();
            String add = address.getCompanyAddress1() +" "+ address.getCompanyCity() +" "+ address.getCompanyState() +" "+
                    address.getCompanyZip()+" "+address.getCompanyCity();
            String phone = address.getContactPhonePrimary();

            mid.setText(oid);
            mpid.setText(pid);
            malais.setText(alais);
            mfname.setText(name);
            memail.setText(email);
            mphone.setText(phone);
            mfadd.setText(add);

        }
    }
}

