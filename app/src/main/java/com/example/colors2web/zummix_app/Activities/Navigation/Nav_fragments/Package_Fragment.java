package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.ProblemSKU_Activity.PackageActivity;
import com.example.colors2web.zummix_app.Adapter.Admin_Tools_Adapters.PackageAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.PackageInput;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.Packages;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemResponse;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.POJO.customers.Customers;
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

public class Package_Fragment extends Fragment {

    @BindView(R.id.recycle_view)
    RecyclerView mrecycleview ;

    @BindView(R.id.btn_common)
    Button create;

    APIInterface apiInterface;

    @BindView(R.id.swipeToRefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    PackageAdapter padapter;

    List<Packages> PList =new ArrayList<>();
    ArrayList<SpinnerPojo> countryList = new ArrayList<>();
    Spinner Customer;
    Long cus_id;
    String radio1,radio,status,package_type,customer_id;
    RadioButton prb,prb1;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_package,container,false);
        ButterKnife.bind(this,view);
        return  view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String group_type = preferences.getString("group_type", "");
        final String l_id = preferences.getString("l_id", "");

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_create_package, null);

                WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(create, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                Button submit,cancle;
                final EditText pname,psku,pweight,plength,pwidth,pheight,pcost;
                final RadioGroup pradio,pradio1;


                Customer =popupView.findViewById(R.id.pack_customer_spinner);

                ArrayAdapter<SpinnerPojo> adp1 =new ArrayAdapter<SpinnerPojo>(getContext(),
                        android.R.layout.simple_spinner_dropdown_item, countryList);
                adp1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);

                Customer.setAdapter(adp1);

                Customer.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        Becuae we are loading value from spinner
                        SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                        cus_id = sp.getCus_id();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(0);
                        cus_id = sp.getCus_id();

                    }
                });

                pname =popupView.findViewById(R.id.pack_adpt_name);
                psku =popupView.findViewById(R.id.pack_sku);
                pweight =popupView.findViewById(R.id.pack_adpt_weight);
                plength =popupView.findViewById(R.id.pack_adpt_length);
                pwidth =popupView.findViewById(R.id.pack_adpt_width);
                pheight =popupView.findViewById(R.id.pack_adpt_height);
                pcost =popupView.findViewById(R.id.pack_adpt_cost);


//                radiogroup //send these radiogroup inside the button

                pradio =popupView.findViewById(R.id.toggle_update);
                pradio.check(R.id.yes);


                pradio1 =popupView.findViewById(R.id.toggle1);
                pradio1.check(R.id.yes1);

                pradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId){
                            case R.id.yes:
                                prb = popupView.findViewById(checkedId);
                                radio = prb.getText().toString();
                                break;

                            case R.id.no:
                                prb = popupView.findViewById(checkedId);
                                radio = prb.getText().toString();
                                break;
                        }
                    }
                });

                pradio1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId1) {
                        switch (checkedId1){
                            case R.id.yes1:
                                prb1 = popupView.findViewById(checkedId1);
                                radio1 = prb1.getText().toString();
                                break;

                            case R.id.no1:
                                prb1 = popupView.findViewById(checkedId1);
                                radio1 = prb1.getText().toString();
                                break;
                        }
                    }
                });
//                buttons
                cancle = popupView.findViewById(R.id.btn_pack_close);
                submit = popupView.findViewById(R.id.btn_pack_submit);


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        final ProgressDialog progressDialog = new ProgressDialog(getActivity(), R.style.AppTheme_Dark_Dialog);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();

                        String qname =pname.getText().toString();
                        String qsku =psku.getText().toString();
                        String qweight =pweight.getText().toString();
                        String qlength =plength.getText().toString();
                        String qwidth =pwidth.getText().toString();
                        String qheight =pheight.getText().toString();
                        String qcost =pcost.getText().toString();

                        if (!Validation(qname)) {
                            if (!Validation(qsku)) {
                                if (!Validation(qweight)) {
                                    if (!Validation(qlength)) {
                                        if (!Validation(qwidth)) {
                                            if (!Validation(qheight)) {
                                                if (!Validation(qcost)) {
                                                    pcost.setError("Invalid Cost");
                                                }
                                                pheight.setError("Invalid Height");
                                            }
                                            pwidth.setError("Invalid Width");
                                        }
                                        plength.setError("Invalid Length");
                                    }
                                    pweight.setError("Invalid Weight");
                                }
                                psku.setError("Invalid Sku");
                            }
                            pname.setError("Invalid Name");
                        }

                        if (pradio.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            radio = "YES";

                        } else {
                            // one of the radio buttons is checked
//                            sku_remove1 =sku_remove;
                            int selectedId = pradio.getCheckedRadioButtonId();
                            switch (selectedId) {
                                case R.id.yes:

                                    prb = popupView.findViewById(selectedId);
                                    radio = "YES";
                                    break;

                                case R.id.no:
                                    prb = popupView.findViewById(selectedId);
                                    radio = "NO";
                                    break;
                            }
                        }

                        if (pradio1.getCheckedRadioButtonId() == -1) {
                            // no radio buttons are checked
                            radio1 = "YES";

                        } else {
                            // one of the radio buttons is checked
//                            sku_remove1 =sku_remove;
                            int selectedId1 = pradio1.getCheckedRadioButtonId();
                            switch (selectedId1) {
                                case R.id.yes1:

                                    prb = popupView.findViewById(selectedId1);
                                    radio1 = "YES";
                                    break;

                                case R.id.no1:
                                    prb = popupView.findViewById(selectedId1);
                                    radio1 = "NO";
                                    break;
                            }
                        }

                        if(radio.equals("YES")){
                            status ="1";
                        }else{
                            status="0";
                        }

                        if(radio1.equals("YES")){
                            package_type ="1";
                        }else{
                            package_type="0";
                        }
                        if(cus_id!=null){
                            customer_id =cus_id.toString();}

                            Log.d("radio1",radio1);
                            Log.d("radio",radio);


                        PackageInput input = new PackageInput(customer_id,qname,qlength,qwidth,qheight,l_id,l_id,
                                qweight,qsku,qcost,status,package_type);

                       postActivity(email,password,group_type,l_id,input,progressDialog,popup);


                    }
                });

                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });
            }
        });

        padapter = new PackageAdapter(getActivity(),Package_Fragment.this,PList);

        apiInterface = APIClient.getClient().create(APIInterface.class);


        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getContext());
        mrecycleview.setHasFixedSize(true);
        mrecycleview.setLayoutManager(mlayoutManager);

        mrecycleview.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecycleview.setItemAnimator(new DefaultItemAnimator());

        mrecycleview.setAdapter(padapter);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                PList.clear();
                padapter.notifyDataSetChanged();

                loadAdapter( email, password);
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });

        loadAdapter( email, password);
        loadSpinner(email,password);
    }

    private void postActivity(String email, String password, String group_type, String l_id, PackageInput input,
                              final ProgressDialog progressDialog, final PopupWindow popup) {


        Call<ProblemResponse> call = apiInterface.postPackage(email, password,input);

        call.enqueue(new Callback<ProblemResponse>() {
            @Override
            public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

                if (response.isSuccessful()) {
                    ProblemResponse resp1 = response.body();
                    if(resp1.getReturnType().equals("success")){
                        Toast.makeText(getContext(),resp1.getMessage(),Toast.LENGTH_SHORT).show();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        popup.dismiss();
                        refresh();

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getContext(),d,Toast.LENGTH_SHORT).show();

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

                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProblemResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void refresh() {

        PList.clear();
        padapter.notifyDataSetChanged();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    private boolean Validation(String qname) {

        if (qname != null ) {
            return true;
        }
        return false;
    }

    private void loadSpinner(String email, String password) {

        Call<CustomerResponse> call = apiInterface.getActiveCustomers(email, password);
        call.enqueue(new Callback<CustomerResponse>() {
            @Override
            public void onResponse(Call<CustomerResponse> call, Response<CustomerResponse> response) {

                if (response.isSuccessful()) {
                    CustomerResponse resp1 = response.body();

                    List<Customers> cus = resp1.getmCustomer();

                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {


                        for (int i = 0; i < cus.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = cus.get(i).getCompanyName();
                            Long cus_id = cus.get(i).getId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            countryList.add(order1);// must be the object of empty list initiated

                        }

//                        ArrayAdapter<SpinnerPojo> adp1 =new ArrayAdapter<SpinnerPojo>(getContext(),
//                                android.R.layout.simple_spinner_dropdown_item, countryList);
//                        adp1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//
//                        Customer.setAdapter(adp1);
//                        Customer.setAdapter(new ArrayAdapter<SpinnerPojo>(getContext(),
//                                android.R.layout.simple_spinner_dropdown_item, countryList));



                    } else {
                        String d = response.body().getMessage();

                    }
                } else if (response.code() == 401) {


                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {



                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());



                } else {

                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                call.cancel();

                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";
    private void loadAdapter( String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(), R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<ProblemResponse> call = apiInterface.getPackages(email, password);

        call.enqueue(new Callback<ProblemResponse>() {
            @Override
            public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

                if (response.isSuccessful()) {
                    ProblemResponse resp1 = response.body();
                    Log.d("msg",resp1.getMessage().toString());

                    List<Packages> order = resp1.getmPackages();

                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {

                            Packages dis = new Packages();

                            Long id = order.get(i).getId();
                            String mCustomer = order.get(i).getCompanyName();
                            String mpname = order.get(i).getPackageName();
                            String mpsku = order.get(i).getPackageSku();
                            String mwidth = order.get(i).getWidth();
                            String mlength = order.get(i).getLength();
                            String mweight = order.get(i).getWeight();
                            String mheight = order.get(i).getHeight();
                            String mcost = order.get(i).getPackageCost();
                            String mstatus = order.get(i).getStatus();

                            dis.setId(id);
                            dis.setCompanyName(mCustomer);
                            dis.setPackageName(mpname);
                            dis.setPackageSku(mpsku);
                            dis.setWidth(mwidth);
                            dis.setLength(mlength);
                            dis.setWeight(mweight);
                            dis.setHeight(mheight);
                            dis.setPackageCost(mcost);
                            dis.setStatus(mstatus);

                            PList.add(dis); // must be the object of empty list initiated
                        }
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        padapter.updateAnswers(PList);//adapter's content is updated and update function is called;
                        //send parameters according to urs adapter view setup.);


                    } else {
                        String d = response.body().getMessage();
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

                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ProblemResponse> call, Throwable t) {
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

