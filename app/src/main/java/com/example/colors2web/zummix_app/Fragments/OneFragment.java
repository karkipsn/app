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
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderLog;
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
//        if (getArguments() != null) {
//            String store_name =getArguments().getString("store_name");
//            Log.d("store_name",store_name);
//
//            String store_email =getArguments().getString("store_email");
//            String order_no =getArguments().getString("order_no");
//            String special_inst =getArguments().getString("special_inst");
//            String order_date =getArguments().getString("order_date");
//            String ship_method =getArguments().getString("ship_method");
//            String order_status =getArguments().getString("order_status");
//            String order_type = getArguments().getString("order_type");
//            String emp_id =getArguments().getString("emp_id");
//
//
//            msname.setText(store_name);
//            msemail.setText(store_email);
//            morder_no.setText(order_no);
//            mspecial_instruction.setText(special_inst);
//            morder_date.setText(order_date);
//            morder_type.setText(order_type);
//            morder_status.setText(order_status);
//            memp_id.setText(emp_id);
//            mship_method.setText(ship_method);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_one, container, false);
        ButterKnife.bind(this, view);
        apiInterface = APIClient.getClient().create(APIInterface.class);
       loadAdapter1();
        return view;


    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);

//        loadAdapter();

    }

    private void loadAdapter1() {

        final Bundle bundle =  getArguments();

        if (bundle != null)
        {

            String store_name =bundle.getString("store_name");
            Log.d("store_name1",store_name);
            String store_email =bundle.getString("store_email");
            String order_no =bundle.getString("order_no");
            String special_inst =bundle.getString("special_inst");
            String order_date =bundle.getString("order_date");
            String ship_method =bundle.getString("ship_method");
            String order_status =bundle.getString("order_status");
            String order_type = bundle.getString("order_type");
            String emp_id =bundle.getString("emp_id");


            msname.setText(store_name);
            msemail.setText(store_email);
            morder_no.setText(order_no);
            mspecial_instruction.setText(special_inst);
            morder_date.setText(order_date);
            morder_type.setText(order_type);
            morder_status.setText(order_status);
            memp_id.setText(emp_id);
            mship_method.setText(ship_method);

        }
        else {
            loadAdapter();
        }
    }

    private void loadAdapter() {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        SharedPreferences preferences1 = PreferenceManager.getDefaultSharedPreferences(getContext());

        final Long id = preferences1.getLong("id", 0);
        Log.d("id", String.valueOf(id));


        Call<Order2Response> call = apiInterface.getsecsearch(email, password, id);

        call.enqueue(new Callback<Order2Response>() {
            @Override
            public void onResponse(Call<Order2Response> call, Response<Order2Response> response) {

                if (response.isSuccessful()) {
                    Order2Response resp1 = response.body();

                    com.example.colors2web.zummix_app.POJO.Order2POJO.OrderDetails order = resp1.getOrderDetails();

                    if (order != null) {

                        Order ord = order.getOrder();

                        String store_name = ord.getCustomerName();
                        String store_email = ord.getCustomerEmail();
                        String order_no = ord.getOrderNumber();
                        String special_inst = ord.getOrderSpecialInstruction();
                        String order_date = ord.getOrderDate();
                        String ship_method = ord.getShipMethod();
                        String order_status = ord.getOrderStatus();
                        String order_type = ord.getOrderType();
                        String emp_id = ord.getEmployeeId();

                        msname.setText(store_name);
                        msemail.setText(store_email);
                        morder_no.setText(order_no);
                        mspecial_instruction.setText(special_inst);
                        morder_date.setText(order_date);
                        morder_type.setText(order_type);
                        memp_id.setText(emp_id);
                        mship_method.setText(ship_method);
                        
                        switch (order_status){

                            case "Order Created":
                                morder_status.setText("Order Created");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.colorPrimaryDark));
                                break;

                            case "Cancelled":
                                morder_status.setText("Cancelled");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.colorPrimaryDark));
                                break;

                            case "Batch Created":
                                morder_status.setText("Batch Created");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.colorPrimaryDark));
                                break;

                            case "Picked":
                                morder_status.setText("Picked");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.green_light));
                                break;

                            case "Packed":
                                morder_status.setText("Packed");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.blue));
                                break;

                            case "IOR":
                                morder_status.setText("IOR");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.colorPrimaryDark));
                                break;

                            case "Shipped":
                                morder_status.setText("Shipped");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.green_dark));
                                break;

                            case "Edited Shipping Address":
                                morder_status.setText("Edited Shipping Address");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.purple));
                                break;


                            case "Pick List Printed":
                                morder_status.setText("Pick List Printed");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.green_light));
                                break;

                            case "Cancelled Shipment":
                                morder_status.setText("Cancelled Shipment");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.colorPrimaryDark));
                                break;

                            case "Pick Started":
                                morder_status.setText("Pick Started");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.purple));
                                break;

                            case "11":
                                morder_status.setText("Pick Ended");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.green_dark));

                                break;

                            case "12":
                                morder_status.setText("Pack Started");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.purple));
                                break;

                            case "13":
                                morder_status.setText("Pack Ended");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.green_light));
                                break;

                            default:
                                morder_status.setText("Default");
                                morder_status.setTextColor( morder_status.getResources().getColor(R.color.yellow));
                                break;
                        }
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


