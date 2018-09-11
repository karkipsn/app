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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Adapter.UOMAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemResponse;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.UOM;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UOMFragment extends Fragment {

    RecyclerView mrecycleView;
    Context mContext;
    APIInterface apiInterface;
    TextView create;
    UOMAdapter padapter;
    String uominput;
    int navItemIndex;
    private Bundle savedState = null;
    List<UOM> UList = new ArrayList<>();

    public UOMFragment() {
    }

    ;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_uom, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        create = getActivity().findViewById(R.id.uom_create);

        mrecycleView = getActivity().findViewById(R.id.recycle_view_uom);
        padapter = new UOMAdapter(mContext, UList);

        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager(getActivity());
        mrecycleView.setHasFixedSize(true);
        mrecycleView.setLayoutManager(mlayoutManager);

//        mrecycleView.addItemDecoration(new SimpleItemDecoration(this));
        mrecycleView.addItemDecoration(new MyDividerItemDecoration(getActivity(), LinearLayoutManager.HORIZONTAL, 16));
        mrecycleView.setItemAnimator(new DefaultItemAnimator());
        mrecycleView.setAdapter(padapter);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String l_id = preferences.getString("l_id", "");

        loadAdapter(email, password);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_uom, null);

                WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width * 0.8), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(create, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                final EditText uom;
                Button submit, cancle;
                uom = popupView.findViewById(R.id.uom_uom_create);

                cancle = popupView.findViewById(R.id.btn_pack_close);
                submit = popupView.findViewById(R.id.btn_pack_submit);


                cancle.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });


                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        uominput = uom.getText().toString();
                        create_uom(email, password, l_id, uominput, popup);
                    }

                });


            }
        });

    }

    private void create_uom(String email, String password, String l_id, final String uominput, final PopupWindow popup) {

        if (l_id != null) {

            Long id = Long.valueOf(l_id);
            UOM uom = new UOM(id, uominput, id);
            Call<ProblemResponse> call = apiInterface.postUOM(email, password, uom);
            call.enqueue(new Callback<ProblemResponse>() {
                @Override
                public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

                    ProblemResponse res = response.body();
                    if (res.getReturnType().equals("success")) {
                        Toast.makeText(getContext(), "UOM " + uominput + " " + res.getMessage(), Toast.LENGTH_SHORT).show();
                        popup.dismiss();
                        padapter.resetdata();

                       refresh();
//                        savedState = saveState();

                    } else {
                        Toast.makeText(getContext(), res.getMessage() + "\n" + "Please try Again", Toast.LENGTH_SHORT).show();

                    }

                }

                @Override
                public void onFailure(Call<ProblemResponse> call, Throwable t) {

                    call.cancel();
                    Toast.makeText(getContext(), "Network Error", Toast.LENGTH_SHORT).show();
                }
            });

        }

    }

    private void refresh() {

//        FragmentTransaction ft = getFragmentManager().beginTransaction();
//        ft.detach(this).attach(this).commit();
//        Both works and this first is the better aproach


        Fragment frg = null;
        frg = getFragmentManager().findFragmentByTag("Unit Of Measurement");
        frg.onDestroy();
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.detach(frg).attach(frg).commit();

    }

//    private Bundle saveState() { /* called either from onDestroyView() or onSaveInstanceState() */
//        Bundle state = new Bundle();
//        state.putCharSequence("TAG_UOM", "Unit Of Measurement");
//        this.setRetainInstance(true);
//        return state;
//    }
//
//
//
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        navItemIndex =4;
//    }

    private void loadAdapter(String email, String password) {

        final ProgressDialog progressDialog = new ProgressDialog(getContext(),
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<ProblemResponse> call = apiInterface.getUOM(email, password);

        call.enqueue(new Callback<ProblemResponse>() {
            @Override
            public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

                if (response.isSuccessful()) {

                    ProblemResponse resp1 = response.body();

                    List<UOM> order = resp1.getUOMs();


                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {
                            UOM uom = new UOM();

                            String mUnit = order.get(i).getUnit();

                            uom.setUnit(mUnit);
                            UList.add(uom);

                        }


                        padapter.updateAdapter(UList);
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
