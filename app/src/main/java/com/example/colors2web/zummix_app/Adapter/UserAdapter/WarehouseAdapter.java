package com.example.colors2web.zummix_app.Adapter.UserAdapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.User_fragment.User_Fragment;
import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.User_fragment.Warehouse_Fragment;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SProgramResponse;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SpecialProgram;
import com.example.colors2web.zummix_app.POJO.Users.Group;
import com.example.colors2web.zummix_app.POJO.Users.GroupResponse;
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

public class WarehouseAdapter extends RecyclerView.Adapter<WarehouseAdapter.WareHolder> {

    private List<User> WareList;
    private Activity mContext;
    private APIInterface apiInterface;
    private Warehouse_Fragment fragment;

    private String s_customer, s_program, s_activity, s_groups;
    private String s_cus1, s_pr1, s_act1, fname1, lname1, s_email1, pass1, cpass1;
    private Long cus_id;


    ArrayList<SpinnerPojo> prgList = new ArrayList<>();
    private EditText e_fname, e_lname, e_pass, e_cpass, e_email;

    private Spinner s_cus, s_act, s_prg, s_grp;

    public WarehouseAdapter(List<User> userList, Activity mContext, Warehouse_Fragment wfragment) {
        WareList = userList;
        this.mContext = mContext;
        this.fragment = wfragment;
    }

    public WarehouseAdapter(List<User> wareList, Activity mContext) {
        WareList = wareList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public WareHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_adapter, parent, false);
        return new WareHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final WareHolder holder, int position) {

        User user = WareList.get(position);

        holder.name.setText(user.getFirstName() + user.getLastName());
        holder.email.setText(user.getEmail());
        List<String> ug = user.getUserGroup();
//         String ug1 = ug.get(position);
        holder.groups.setText(ug.toString().replace("[", "").replace("]", ""));
        holder.created.setText(user.getCreatedAt());

        final String u_id = String.valueOf(user.getId());
        final String ufname = user.getFirstName();
        final String ulname = user.getLastName();
        final String uemail = user.getEmail();


        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_user_create, null);

                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(holder.update, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                final WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
                final String email = preferences.getString("email", "");
                final String password = preferences.getString("password", "");
                apiInterface = APIClient.getClient().create(APIInterface.class);


                Button submit, cancle;


                s_cus = popupView.findViewById(R.id.user_modal_customer);
                s_prg = popupView.findViewById(R.id.user_modal_special_program);
                s_act = popupView.findViewById(R.id.user_modal_user_activated);
                e_fname = popupView.findViewById(R.id.user_modal_first_name);
                e_fname.setText(ufname);
                e_lname = popupView.findViewById(R.id.user_modal_last_name);
                e_lname.setText(ulname);

                e_pass = popupView.findViewById(R.id.user_modal_password);
                e_cpass = popupView.findViewById(R.id.user_modal_confirm_password);

                e_email = popupView.findViewById(R.id.user_modal_email);
                e_email.setText(uemail);

                s_grp = popupView.findViewById(R.id.user_modal_spinner_groups);

                submit = popupView.findViewById(R.id.user_modal_submit);
                cancle = popupView.findViewById(R.id.user_modal_cancel);

                loadspinner_customer(email, password);
                loadspinner_groups(email, password);

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
                        s_groups = String.valueOf(sp.getCus_id());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        SpinnerPojo sp = (SpinnerPojo) parent.getItemAtPosition(0);
                        s_groups = String.valueOf(sp.getCus_id());

                    }
                });

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

                        s_email1 = e_email.getText().toString();
                        pass1 = e_cpass.getText().toString();
                        cpass1 = e_pass.getText().toString();

//                        for (int k=0;k<1;k++){
//                            s_grp1[k] = String.valueOf(s_groups);}
                        List<String> groups = new ArrayList<>();
                        groups.add(s_groups);

                        List<String> permission = new ArrayList<>();

                        UserCreatePOJO pojo = new UserCreatePOJO(s_cus1, s_pr1, s_act1, fname1,
                                lname1, s_email1, pass1, cpass1, groups, permission);

                        update_users(email, password, pojo, popup, u_id);
                    }

                });
            }
        });

    }

    private void update_users(String email, String password, UserCreatePOJO pojo, final PopupWindow popup, String u_id) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();


        Call<UsersResponse> call = apiInterface.updateusers(email, password, u_id, pojo);
        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {

                if (response.isSuccessful()) {

                    UsersResponse resp1 = response.body();

                    if (resp1.getReturnType().equals("success")) {

                        Toast.makeText(mContext, resp1.getMessage(), Toast.LENGTH_SHORT).show();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();

                        }
                        popup.dismiss();
                        fragment.refresh();


                    } else {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                        Toast.makeText(mContext, resp1.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, " User Create operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }


    private void loadspinner_programs(String email, String password, Long cus_id1) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext,
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

//                    Toast.makeText(mContext, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

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

                    ArrayAdapter<SpinnerPojo> adp2 = new ArrayAdapter<SpinnerPojo>(mContext,
                            android.R.layout.simple_spinner_dropdown_item, prgList);
                    adp2.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    s_prg.setAdapter(adp2);
                    s_prg.setPrompt("Select Programs");
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }


                } else if (response.code() == 401) {


                    Toast.makeText(mContext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 404) {


                    Toast.makeText(mContext, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SProgramResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void loadspinner_groups(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext,
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

                    Toast.makeText(mContext, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    ArrayList<SpinnerPojo> groupList = new ArrayList<>();

                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = cus.get(i).getName();
                            Long cus_id = cus.get(i).getId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            groupList.add(order1);// must be the object of empty list initiated

                        }

                    } else {
                        String d = response.body().getMessage();

                    }
                    ArrayAdapter<SpinnerPojo> adp3 = new ArrayAdapter<SpinnerPojo>(mContext,
                            android.R.layout.simple_spinner_dropdown_item, groupList);

                    adp3.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                    s_grp.setAdapter(adp3);
                    s_grp.setPrompt("Select Groups");

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 401) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();

                    }
                    Toast.makeText(mContext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<GroupResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadspinner_customer(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext,
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

                    Toast.makeText(mContext, resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        ArrayList<SpinnerPojo> countryList = new ArrayList<>();

                        for (int i = 0; i < cus.size(); i++) {

                            SpinnerPojo order1 = new SpinnerPojo();

                            String name = cus.get(i).getCompanyName();
                            Long cus_id = cus.get(i).getId();

                            order1.setCus_id(cus_id);
                            order1.setName(name);

                            countryList.add(order1);// must be the object of empty list initiated
                        }

                        ArrayAdapter<SpinnerPojo> adp1 = new ArrayAdapter<SpinnerPojo>(mContext,
                                android.R.layout.simple_spinner_dropdown_item, countryList);
                        adp1.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                        s_cus.setAdapter(adp1);
                        s_cus.setPrompt("Select Customers");

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

                    Toast.makeText(mContext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(mContext, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(mContext, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<CustomerResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return WareList.size();
    }


    public void updateAnswers(ArrayList<User> logs) {
        WareList = logs;
        notifyDataSetChanged();
    }

    public void reloadfragment() {
        WareList.clear();
        notifyDataSetChanged();
    }

    public class WareHolder extends RecyclerView.ViewHolder {

        TextView name, email, groups, created;
        Button update;

        public WareHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.user_adpt_name);
            email = itemView.findViewById(R.id.user_adpt_email);
            groups = itemView.findViewById(R.id.user_adpt_groups);
            created = itemView.findViewById(R.id.user_adpt_created);
            update = itemView.findViewById(R.id.user_adpt_update);
        }
    }
}