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
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderShippingAddressesDetail;
import com.example.colors2web.zummix_app.POJO.OrderShippingAddress;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TwoFragment extends Fragment {

    APIInterface apiInterface;

    @BindView(R.id.dis_sname)
    TextView sname;

    @BindView(R.id.dis_sphone)
    TextView sphone;

    @BindView(R.id.dis_saddress)
    TextView saddress;

    @BindView(R.id.dis_semail)
    TextView semail;

    @BindView(R.id.dis_soffice)
    TextView soffice;


    public TwoFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_two, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        apiInterface = APIClient.getClient().create(APIInterface.class);

        loadAdapter();
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

                      List<OrderShippingAddressesDetail> ord =  order.getOrderShippingAddressesDetails();

                      for (OrderShippingAddressesDetail ord1:ord){

                        String name = ord1.getCustomerFname()+" "+ord1.getCustomerLname();
                        String phone = ord1.getCustomerPhone1();
                        String address = ord1.getCustomerAddress1();
                        String email = ord1.getCustomerEmail();
                        String office = ord1.getCustomerOfficeName();


                        sname.setText(name);
                        sphone.setText(phone);
                        saddress.setText(address);
                        semail.setText(email);
                        soffice.setText(office);


                    } }else {
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
