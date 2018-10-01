package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.InActive_fragment;

import android.app.ProgressDialog;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.InActiveActivity;
import com.example.colors2web.zummix_app.Fragments.Frag_InActive;
import com.example.colors2web.zummix_app.POJO.OrderByCus.OrdGrpByCus;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Inactive_Fragment extends Fragment {

    @BindView(R.id.real_spinner)
    Spinner spinner1;

    @BindView(R.id.btn_submit_inactive)
    Button mbutton;

    APIInterface apiInterface;
    Long cus_id;

    public Inactive_Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inactive,container,false);
        ButterKnife.bind(this,view);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        spinner(email, password);

        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = new Bundle();
                bundle.putString("cus_id", String.valueOf(cus_id));
                Frag_InActive inActive = new Frag_InActive();
                inActive.setArguments(bundle);
//                getContext().overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);

                getFragmentManager().beginTransaction().
                        add(R.id.frame_inactive, inActive).
                        commit();
            }
        });




    }

    private void spinner(String email, String password) {

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


////                        try autocomplete
//                        ArrayAdapter<SpinnerPojo> adapter1 = new ArrayAdapter<SpinnerPojo>(getContext(),
//                                android.R.layout.simple_list_item_single_choice, countryList);
//                         mspinner.setAdapter(adapter1);

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

                                Toast.makeText(getContext(), "Cus ID: " + sp.getCus_id() + ",  " +
                                        " Name : " + sp.getName(), Toast.LENGTH_SHORT).show();
                                Log.d("cus_id", String.valueOf(cus_id));
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(0);
                                cus_id = sp.getCus_id();
                            }
                        });


//                        WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
//                        Display display = wm.getDefaultDisplay();
//                        int width = display.getWidth();
//                        double width1 = width * 0.8;
//                        int fin = (int) width1;
//
//
//                        mspinner.setDropDownWidth(fin);
//                        mspinner.setCursorVisible(false);
//                        mspinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                            @Override
//                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                                mspinner.showDropDown();
////                                selection = (String) parent.getItemAtPosition(position);
//
//                                SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
//                                cus_id = sp.getCus_id();
//
//
//
//                                Toast.makeText(getContext(), "Cus ID: " + sp.getCus_id() + ",  " +
//                                        " Name : " + sp.getName(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                        mspinner.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                mspinner.showDropDown();
//                            }
//                        });

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
