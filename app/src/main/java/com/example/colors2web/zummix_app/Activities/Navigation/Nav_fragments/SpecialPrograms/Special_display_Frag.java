package com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.SpecialPrograms;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.Admin_Tools_Adapters.Special_ProgramAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SProgramResponse;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SpecialProgram;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SpecialProgramResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Special_display_Frag extends Fragment {

    RecyclerView mrecyclerView;
    Special_ProgramAdapter iadapter;
    String cus_id;
    Button create;
    SwipeRefreshLayout mSwipeRefreshLayout;
    TextView textView;

    List<SpecialProgram> PrgList = new ArrayList<>();

    public Special_display_Frag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.activity_special_display_fragment, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        create = getActivity().findViewById(R.id.create_special_program);
        textView = view.findViewById(R.id.special_null);

        iadapter = new Special_ProgramAdapter(PrgList, getActivity(),Special_display_Frag.this);

        mrecyclerView = getActivity().findViewById(R.id.recycleview_inactive);

//        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeToRefresh);
//        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
//        mrecyclerView.setHasFixedSize(true);
        mrecyclerView.setLayoutManager(mLayoutManager);

        mrecyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL, 16));
        mrecyclerView.setItemAnimator(new DefaultItemAnimator());
        mrecyclerView.setAdapter(iadapter);


        if (getArguments() != null) {
            Bundle bundle = this.getArguments();
            cus_id = bundle.getString("cus_id");
            Log.d("cus_id", cus_id);
        } else {
            Log.d("cus_id", "null babey");
        }

        loadAdapter(cus_id);

//        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//
//                PrgList.clear();
//                loadAdapter(cus_id);
//                mSwipeRefreshLayout.setRefreshing(false);
//
//            }
//        });


        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_create_special_program, null);

                WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width*0.9), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(create, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                Button submit, cancle;
                final EditText pname, pnum, pdetails;

                pname = popupView.findViewById(R.id.sp_spname);
                pnum = popupView.findViewById(R.id.sp_spnumber);
                pdetails = popupView.findViewById(R.id.sp_spdetails);
                submit = popupView.findViewById(R.id.sp_sp_btnsubmit);
                cancle = popupView.findViewById(R.id.sp_sp_btncancel);

                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String des = pdetails.getText().toString();
                        String name = pname.getText().toString();
                        String num = pnum.getText().toString();
                        Long cid = Long.valueOf(cus_id);

                        SpecialProgram prog = new SpecialProgram(cid, des, name, num);

                        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                                R.style.AppTheme_Dark_Dialog);
                        progressDialog.setIndeterminate(true);
                        progressDialog.setCancelable(false);
                        progressDialog.setMessage("Loading...");
                        progressDialog.show();

                        loadApi(prog, progressDialog, popup);
                    }
                });
            }
        });
    }

    private void loadApi(SpecialProgram prog, final ProgressDialog progressDialog, final PopupWindow popup) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SpecialProgramResponse> call = apiInterface.createSpecialPrograms(email, password, prog);
        call.enqueue(new Callback<SpecialProgramResponse>() {
            @Override
            public void onResponse(Call<SpecialProgramResponse> call, Response<SpecialProgramResponse> response) {

                if (response.isSuccessful()) {

                    SpecialProgramResponse resp1 = response.body();

                    if (resp1.getReturnType().equals("success")) {

                        popup.dismiss();
                        Toast.makeText(getContext(), resp1.getMessage(), Toast.LENGTH_LONG).show();
                        iadapter.cleardata();
                        refresh();


                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        Toast.makeText(getContext(), resp1.getMessage(), Toast.LENGTH_LONG).show();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    }


                } else if (response.code() == 401) {


                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 404) {


                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<SpecialProgramResponse> call, Throwable t) {
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
//        Fragment frg = null;
//        frg = getFragmentManager().findFragmentByTag("DISPALY_FRAGMENT");
//        frg.onDestroy();
//        final FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.detach(frg).attach(frg).commit();

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this).commit();
    }

    private void loadAdapter(String cus_id) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<SProgramResponse> call = apiInterface.getSpecialPrograms(email, password, String.valueOf(cus_id));

        call.enqueue(new Callback<SProgramResponse>() {
            @Override
            public void onResponse(Call<SProgramResponse> call, Response<SProgramResponse> response) {

                if (response.isSuccessful()) {

                    SProgramResponse resp1 = response.body();

                    List<SpecialProgram> cus = resp1.getSpecialPrograms();

//                    Toast.makeText(getContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    if (cus != null) {

                        for (int i = 0; i < cus.size(); i++) {
                            textView.setVisibility(View.GONE);

                            SpecialProgram order1 = new SpecialProgram();

                            String prog = cus.get(i).getSpecialProgramNumber();
                            String p_name = cus.get(i).getProgramName();
                            String p_des = cus.get(i).getProgramDescription();
                            Long cus_id = cus.get(i).getId();

                            order1.setSpecialProgramNumber(prog);
                            order1.setProgramName(p_name);
                            order1.setProgramDescription(p_des);
                            order1.setId(cus_id);

                            PrgList.add(order1);// must be the object of empty list initiated

                        }

                        iadapter.updateAnswers(PrgList);
//                        textView.setVisibility(View.GONE);
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                    } else {
                        Toast.makeText(getContext(), resp1.getMessage(), Toast.LENGTH_LONG).show();
                        textView.setText(resp1.getMessage());
                        textView.setVisibility(View.VISIBLE);

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
//                        String d = response.body().getMessage();
//                        prgList.add(null);

                    }


                } else if (response.code() == 401) {


                    Toast.makeText(getContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 404) {


                    Toast.makeText(getContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(getContext(), "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


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
}
