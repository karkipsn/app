package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.User_fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
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
import android.widget.Spinner;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.UserAdapter.UserAdapter;
import com.example.colors2web.zummix_app.Adapter.UserAdapter.WarehouseAdapter;
import com.example.colors2web.zummix_app.Adapter.ViewPagerAdapter;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.POJO.Users.Group;
import com.example.colors2web.zummix_app.POJO.Users.GroupResponse;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SProgramResponse;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SpecialProgram;
import com.example.colors2web.zummix_app.POJO.Users.User;
import com.example.colors2web.zummix_app.POJO.Users.UserCreatePOJO;
import com.example.colors2web.zummix_app.POJO.Users.UsersResponse;
import com.example.colors2web.zummix_app.POJO.customers.CustomerResponse;
import com.example.colors2web.zummix_app.POJO.customers.Customers;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Fragment extends Fragment {
    RecyclerView mrecycleView;
    Activity mContext;
    APIInterface apiInterface;
    Button create;

    UserAdapter uadapter;
    UserAdapter useradapter;

    WarehouseAdapter warehouseAdapter;
    SwipeRefreshLayout mSwipeRefreshLayout;

    String s_customer, s_program, s_activity;
    String s_cus1, s_pr1, s_act1, fname1, lname1, s_email1, pass1, cpass1;
    Long cus_id, s_groups;

    ArrayList<User> UserList = new ArrayList<>();
    ArrayList<User> WareList = new ArrayList<>();
    ArrayList<SpinnerPojo> prgList = new ArrayList<>();
    ArrayList<SpinnerPojo> groupList = new ArrayList<>();
    ArrayList<SpinnerPojo> spin_cus_list = new ArrayList<>();

    EditText e_fname, e_lname, e_pass, e_cpass, e_email;

    Spinner s_cus, s_act, s_prg, s_grp;

    ViewPager viewPager;
    TabLayout tabLayout;

    public User_Fragment() {
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_users, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        create = getActivity().findViewById(R.id.uom_create);
        tabLayout = getActivity().findViewById(R.id.tabs_user);
        viewPager = getActivity().findViewById(R.id.viewpager_user);

        warehouseAdapter = new WarehouseAdapter(WareList, mContext);

        uadapter = new UserAdapter(UserList, mContext);
        useradapter = new UserAdapter(UserList, getContext(), User_Fragment.this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");

        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);



        loadspinner_customer(email, password);
        loadspinner_groups(email, password);
        loadAdapter(email, password);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                UserList.clear();
                WareList.clear();
                loadAdapter(email, password);
                mSwipeRefreshLayout.setRefreshing(false);

            }
        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_user_create, null);

                WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(create, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                final WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                Button submit, cancle;

                s_cus = popupView.findViewById(R.id.user_modal_customer);
                s_prg = popupView.findViewById(R.id.user_modal_special_program);
                s_act = popupView.findViewById(R.id.user_modal_user_activated);
                e_fname = popupView.findViewById(R.id.user_modal_first_name);
                e_lname = popupView.findViewById(R.id.user_modal_last_name);
                e_pass = popupView.findViewById(R.id.user_modal_email);
                e_cpass = popupView.findViewById(R.id.user_modal_password);
                e_email = popupView.findViewById(R.id.user_modal_confirm_password);
                s_grp = popupView.findViewById(R.id.user_modal_spinner_groups);

                submit = popupView.findViewById(R.id.user_modal_submit);
                cancle = popupView.findViewById(R.id.user_modal_cancel);


                loadcustomer();
                loadgroups();

                s_cus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                        cus_id = sp.getCus_id();

                        if (cus_id != null) {
                            loadspinner_programs(email, password, cus_id);
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                s_prg.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                        s_program = sp.getName();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
//                        s_program = null;

                    }
                });


                s_grp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                        Becuae we are loading value from spinner
                        SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(position);
                        s_groups = sp.getCus_id();
                        Toast.makeText(getContext(), sp.getName(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(0);
                        s_groups = sp.getCus_id();

                    }
                });


                loadActivation();
                s_act.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        s_activity = parent.getItemAtPosition(position).toString();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
//                        s_activity = parent.getItemAtPosition(0).toString();

                    }
                });


                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        s_cus1 = String.valueOf(cus_id);
                        s_pr1 = s_program;

                        if (s_activity.equals("Yes")) {
                            s_act1 = String.valueOf(1);
                        } else {
                            s_act1 = String.valueOf(0);
                        }
                        fname1 = e_fname.getText().toString();
                        lname1 = e_lname.getText().toString();
                        s_email1 = e_pass.getText().toString();
                        pass1 = e_cpass.getText().toString();
                        cpass1 = e_email.getText().toString();

//                        for (int k=0;k<2;k++){
//                        s_grp1[k] = String.valueOf(s_groups);
//                        }

                        List<String> list = new ArrayList<>();
                        list.add(String.valueOf(s_groups));

                        List<String> permission = null;

//                        List<String>sg2 = String.valueOf(s_groups);

                        UserCreatePOJO pojo = new UserCreatePOJO(s_cus1, s_pr1, s_act1, fname1,
                                lname1, s_email1, pass1, cpass1, list, permission);

                        create_users(email, password, pojo, popup);
                    }

                });
            }
        });

    }

    private void loadActivation() {
        List<String>active = new ArrayList<>();
        active.add("Yes");
        active.add("No");

            ArrayAdapter<String> adp1 = new ArrayAdapter<String>(getContext(),
                    android.R.layout.simple_spinner_dropdown_item,active );
            adp1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
            s_act.setAdapter(adp1);
            s_act.setPrompt("Select Activation");
    }

    private void loadgroups() {

        ArrayAdapter<SpinnerPojo> adp3 = new ArrayAdapter<SpinnerPojo>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, groupList);

        adp3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        s_grp.setAdapter(adp3);
        s_grp.setPrompt("Select Groups");
    }

    private void loadcustomer() {

        ArrayAdapter<SpinnerPojo> adp1 = new ArrayAdapter<SpinnerPojo>(getContext(),
                android.R.layout.simple_spinner_dropdown_item, spin_cus_list);
        adp1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        s_cus.setAdapter(adp1);
        s_cus.setPrompt("Select Customers");
    }

    private void create_users(String email, String password, UserCreatePOJO pojo, final PopupWindow popup) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<UsersResponse> call = apiInterface.createUsers(email, password, pojo);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                if (response.isSuccessful()) {

                    UsersResponse resp1 = response.body();

                    if (resp1.getReturnType().equals("success")) {

                        Toast.makeText(getContext(), resp1.getMessage(), Toast.LENGTH_SHORT).show();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();

                        }
                        popup.dismiss();
                        refresh();


                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(getContext(), resp1.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), " User Create operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }


    public void refresh() {

//   this Users tag is declared in activity// nac=vig home activity as TAG_USERS

        warehouseAdapter.reloadfragment();
        uadapter.reloadfragment();
        Fragment frg1 = getFragmentManager().findFragmentByTag("Users");
        frg1.onDestroy();
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg1).attach(frg1).commit();

    }

    public void refresh1() {

//   this Users tag is declared in activity// nac=vig home activity as TAG_USERS

//        warehouseAdapter1.reloadfragment();
//        uadapter1.reloadfragment();
        Fragment frg1 = getFragmentManager().findFragmentByTag("Users");
        frg1.onDestroy();
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg1).attach(frg1).commit();

    }
    private void loadspinner_groups(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<GroupResponse> call = apiInterface.getGroups(email, password);

        call.enqueue(new Callback<GroupResponse>() {
            @Override
            public void onResponse(Call<GroupResponse> call, Response<GroupResponse> response) {

                if (response.isSuccessful()) {

                    GroupResponse resp1 = response.body();

                    List<Group> cus = resp1.getGroups();

                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();


                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = cus.get(i).getName();
                            Long cus_id = cus.get(i).getId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            groupList.add(order1);// must be the object of empty list initiated

                        }
//                        ArrayAdapter<SpinnerPojo> adp3 = new ArrayAdapter<SpinnerPojo>(getContext(),
//                                android.R.layout.simple_spinner_dropdown_item, groupList);
//
//                        adp3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//                        s_grp.setAdapter(adp3);
//                        s_grp.setPrompt("Select Groups");

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(getContext(), d, Toast.LENGTH_SHORT).show();

                    }

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();

                    }
                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();


                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void loadspinner_programs(String email, String password, Long cus_id1) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<SProgramResponse> call = apiInterface.getSpecialPrograms(email, password, String.valueOf(cus_id1));

        call.enqueue(new Callback<SProgramResponse>() {
            @Override
            public void onResponse(Call<SProgramResponse> call, Response<SProgramResponse> response) {

                if (response.isSuccessful()) {

                    SProgramResponse resp1 = response.body();

                    List<SpecialProgram> cus = resp1.getSpecialPrograms();

//                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = cus.get(i).getProgramName();
                            Long cus_id = cus.get(i).getId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            prgList.add(order1);// must be the object of empty list initiated

                        }
                    } else {
//                        String d = response.body().getMessage();
//                        prgList.add(null);

                    }

                    ArrayAdapter<SpinnerPojo> adp2 = new ArrayAdapter<SpinnerPojo>(getContext(),
                            android.R.layout.simple_spinner_dropdown_item, prgList);
                    adp2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    s_prg.setAdapter(adp2);
                    s_prg.setPrompt("Select Programs");
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }


                } else if (response.code() == 401) {


                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 404) {


                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                } else if (response.code() == 500) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();


                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SProgramResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadspinner_customer(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

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

                            spin_cus_list.add(order1);// must be the object of empty list initiated
                        }

//                        ArrayAdapter<SpinnerPojo> adp1 = new ArrayAdapter<SpinnerPojo>(getContext(),
//                                android.R.layout.simple_spinner_dropdown_item, spin_cus_list);
//                        adp1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//                        s_cus.setAdapter(adp1);
//                        s_cus.setPrompt("Select Customers");

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

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
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void loadAdapter(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<UsersResponse> call = apiInterface.getUsers(email, password);

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                if (response.isSuccessful()) {

                    UsersResponse resp1 = response.body();

                    List<User> order = resp1.getUsers();

                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {

//                            User user = order.get(i).getUser();
                            User uom = new User();
//                            Always needs to create fresh instance//if POJO instance is taken outside

                            Long customerId = order.get(i).getUser().getCustomerId();

                            Long id = order.get(i).getUser().getId();
                            String fname = order.get(i).getUser().getFirstName();
                            String lname = order.get(i).getUser().getLastName();
                            String memail = order.get(i).getUser().getEmail();
                            String mcreated = order.get(i).getUser().getCreatedAt();
                            List<String> ug = order.get(i).getUserGroup();


                            uom.setId(id);
                            uom.setFirstName(fname);
                            uom.setLastName(lname);
                            uom.setEmail(memail);
                            uom.setCreatedAt(mcreated);
                            uom.setUserGroup(ug);

                            if (customerId == Long.valueOf(0)) {

                                WareList.add(uom);

                            } else {

                                UserList.add(uom);

                            }

                            tabLayout.setupWithViewPager(viewPager);

                            ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());


                            Bundle bundle1 = new Bundle();
                            Warehouse_Fragment pickf = new Warehouse_Fragment();
                            bundle1.putParcelableArrayList("WareList", WareList);
                            bundle1.putParcelableArrayList("GroupList", groupList);
                            bundle1.putParcelableArrayList("CustomerList", spin_cus_list);
                            pickf.setArguments(bundle1);
                            viewPagerAdapter.addFrag(pickf, "Warehouse ");


                            ClientPortal_Fragment loc = new ClientPortal_Fragment();
                            bundle1.putParcelableArrayList("UserList", UserList);
                            bundle1.putParcelableArrayList("GroupList", groupList);
                            bundle1.putParcelableArrayList("CustomerList", spin_cus_list);
                            loc.setArguments(bundle1);
                            viewPagerAdapter.addFrag(loc, "ClientPortal Users");

                            viewPager.setAdapter(viewPagerAdapter);

//                        padapter.updateAdapter(UserList);

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }


                        }
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
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

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
