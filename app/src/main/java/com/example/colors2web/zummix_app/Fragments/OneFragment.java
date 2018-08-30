package com.example.colors2web.zummix_app.Fragments;

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

import com.example.colors2web.zummix_app.POJO.Order2POJO.Order;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Order2Response;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class OneFragment extends Fragment {

    APIInterface apiInterface;

    @BindView(R.id.dis_store_name)
    TextView msname;

    @BindView(R.id.dis_store_email)
    TextView msemail;

    @BindView(R.id.dis_strnum)
    TextView morder_no;

    @BindView(R.id.dis_osi)
    TextView mspecial_instruction;

    @BindView(R.id.dis_o_date)
    TextView morder_date;

    @BindView(R.id.dis_o_type)
    TextView morder_type;

    @BindView(R.id.dis_ship_m)
    TextView mship_method;

    @BindView(R.id.dis_o_status)
    TextView morder_status;

    @BindView(R.id.dis_emp_id)
    TextView memp_id;

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        loadAdapter1();
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);
//       loadAdapter1();

    }

    private void loadAdapter1() {
        if (getArguments() != null) {
            String store_name = getArguments().getString("store_name");
            Log.d("store_name1", store_name);

            String store_email = getArguments().getString("store_email");
            String order_no = getArguments().getString("order_no");
            String special_inst = getArguments().getString("special_inst");
            String order_date = getArguments().getString("order_date");
            String ship_method = getArguments().getString("ship_method");
            String order_status = getArguments().getString("order_status");
            String order_type = getArguments().getString("order_type");
            String emp_id = getArguments().getString("emp_id");
            Log.d("order_status",order_status);
            Log.d("emp_id",emp_id);



            msname.setText(store_name);
            msemail.setText(store_email);
            morder_no.setText(order_no);
            mspecial_instruction.setText(special_inst);
            morder_date.setText(order_date);
            morder_type.setText(order_type);
//           morder_status.setText(order_status);
            memp_id.setText(emp_id);
            mship_method.setText(ship_method);
        }
    }

}


