package com.example.colors2web.zummix_app.Activities.ProblemSKU_Activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemInput;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemResponse;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemSKUs;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.UOM;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.Country;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.SpinnerPojo;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProblemAdapter extends RecyclerView.Adapter<ProblemAdapter.ProblemHolder> {

    Activity mActivity ;
    private TextView mweight;
    private Spinner spinner,
            spinnerCountry;

    private String uom, country,country1;

    private RadioButton prb;
    private String sku_remove, radio;


    List<ProblemSKUs> ProblemList;

//    public ProblemAdapter(Context context, List<ProblemSKUs> pList) {
//        this.mActivity = context;
//        this.ProblemList = pList;
//    }

    public ProblemAdapter(Activity mActivity, List<ProblemSKUs> problemList) {
        this.mActivity = mActivity;
        ProblemList = problemList;
    }

    @NonNull
    @Override
    public ProblemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.problem_adapter, parent, false);
        return new ProblemHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final ProblemHolder holder, int position) {
        ProblemSKUs problemSKUs = ProblemList.get(position);

        holder.mCustomer.setText(problemSKUs.getCompanyName());

        final String item_name = problemSKUs.getItemName();
        holder.mpname.setText(item_name);

        final String item_sku = problemSKUs.getItemSku();
        final String cus_id = String.valueOf(problemSKUs.getCustomerId());

        final String id = String.valueOf(problemSKUs.getId());

        holder.mpsku.setText(item_sku);

        holder.moquant.setText(problemSKUs.getOrderQty());
        holder.mtorder.setText(problemSKUs.getTotalOrder());

        final String wt = problemSKUs.getWeight();
        holder.mweight.setText(wt);

        final String cost =problemSKUs.getPrice();
        holder.mprice.setText(cost);

        holder.mUOM.setText(problemSKUs.getUom());
        holder.mcountry.setText(problemSKUs.getCountry());


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mActivity);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String group_type = preferences.getString("group_type", "");
        final String l_id = preferences.getString("l_id", "");


        holder.mupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_problem_insert, null);

                WindowManager wm = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width*0.9), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(holder.mupdate, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                final EditText price, weight;
                TextView i_name;
                final RadioGroup radioGroup;
                Button post, cancel;

                i_name = popupView.findViewById(R.id.pop_prob_name);
                i_name.setText(item_name);

                spinner = popupView.findViewById(R.id.uom_spinner);
                spinnerCountry = popupView.findViewById(R.id.country_spinner);

                price = popupView.findViewById(R.id.pop_prob_price);
                price.setText(cost);

                weight = popupView.findViewById(R.id.pop_prob_weight);
                weight.setText(wt);

                post = popupView.findViewById(R.id.pop_prob_submit);
                cancel = popupView.findViewById(R.id.prop_prob_cancel);

                final String qprice = price.getText().toString();
                final String qweight = weight.getText().toString();

                radioGroup = popupView.findViewById(R.id.toggle_prob_adapter);
                radioGroup.check(R.id.yes);

                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        switch (checkedId) {
                            case R.id.yes:

                                prb = popupView.findViewById(checkedId);
                                radio = prb.getText().toString();
                                sku_remove = "1";
                                break;

                            case R.id.no:
                                prb = popupView.findViewById(checkedId);
                                radio = prb.getText().toString();
                                sku_remove = "0";
                                break;
                        }
                    }
                });

//        loading SPinners
               loadspinner(email, password);
               loadCountrySpinner();
                Log.d("spinner",spinner.toString());
                Log.d("spinnerc",spinnerCountry.toString());


//                String[] firstNames = { "Dennis", "Grace", "Bjarne", "James" };
//
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity,
//                        android.R.layout.simple_spinner_item, firstNames);
//
//                adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
//                spinner.setAdapter(adapter);

                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                      uom = parent.getItemAtPosition(position).toString();

//                        UOM sp = (UOM) parent.getItemAtPosition(position);
//                        uom = sp.getUnit();
//
//                        Toast.makeText(mActivity, "Cus ID: " + sp.getUnit(),
//                                 Toast.LENGTH_SHORT).show();
                        Log.d("uom", String.valueOf(uom));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        uom = parent.getItemAtPosition(0).toString();

                        Log.d("uom1", String.valueOf(uom));

                    }
                });



                spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        Country sp = (Country) parent.getItemAtPosition(position);
                        country = sp.getCode();
                        Log.d("Country", country);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        Country sp = (Country) parent.getItemAtPosition(0);
                        country = sp.getCode();
                        Log.d("Country1", country);
                    }
                });


                post.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        ProblemInput input = new ProblemInput(qprice, qweight, uom, country);
                        insert_popup(email, password, l_id, group_type, cus_id, item_sku, input, sku_remove, popup, id);
                    }
                });


                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });
            }
        });

    }

    private void insert_popup(final String email, final String password, String l_id, String group_type,
                              String cus_id, String item_sku, final ProblemInput input, final String sku_remove,
                              final PopupWindow popup, final String id) {

        final APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<ProblemResponse> call = apiInterface.postProblem(email, password, cus_id, item_sku, input);
        call.enqueue(new Callback<ProblemResponse>() {
            @Override
            public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

                if (response.isSuccessful()) {
                    ProblemResponse resp1 = response.body();

                    if (resp1.getReturnType().equals("success")) {
                        Toast.makeText(mActivity, resp1.getMessage(), Toast.LENGTH_SHORT).show();
                        if (sku_remove.equals("1")) {
                            call_delete(email, password, id, apiInterface, popup);
//                            delete API
                        } else {
//                            update API
                            call_update_problem(email, password, id, input, apiInterface, popup);
                        }

                        popup.dismiss();


                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(mActivity, d, Toast.LENGTH_SHORT).show();

                    }
                } else if (response.code() == 401) {

                    Toast.makeText(mActivity, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {


                    Toast.makeText(mActivity, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(mActivity, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    Toast.makeText(mActivity, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProblemResponse> call, Throwable t) {
                call.cancel();

                Log.e("response-failure", t.toString());
                Toast.makeText(mActivity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void call_update_problem(final String email, final String password, final String id, final ProblemInput input,
                                     final APIInterface apiInterface, final PopupWindow popup) {

        Call<ProblemResponse> call = apiInterface.updateProblemsku(email, password, id, input);

        call.enqueue(new Callback<ProblemResponse>() {
            @Override
            public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

                if (response.isSuccessful()) {
                    ProblemResponse resp1 = response.body();

                    if (resp1.getReturnType().equals("success")) {

                        Toast.makeText(mActivity, resp1.getMessage(), Toast.LENGTH_SHORT).show();
                        popup.dismiss();

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(mActivity, d, Toast.LENGTH_SHORT).show();


                    }
                } else if (response.code() == 401) {


                    Toast.makeText(mActivity, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {


                    Toast.makeText(mActivity, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(mActivity, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    Toast.makeText(mActivity, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProblemResponse> call, Throwable t) {
                call.cancel();

                Log.e("response-failure", t.toString());
                Toast.makeText(mActivity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void call_delete(final String email, final String password, final String id, final APIInterface apiInterface, final PopupWindow popup) {
        Call<ProblemResponse> call = apiInterface.deleteProblemsku(email, password, id);
        call.enqueue(new Callback<ProblemResponse>() {
            @Override
            public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

                if (response.isSuccessful()) {
                    ProblemResponse resp1 = response.body();

                    if (resp1.getReturnType().equals("success")) {
                        Toast.makeText(mActivity, resp1.getMessage(), Toast.LENGTH_SHORT).show();

                        popup.dismiss();

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(mActivity, d, Toast.LENGTH_SHORT).show();


                    }
                } else if (response.code() == 401) {


                    Toast.makeText(mActivity, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {


                    Toast.makeText(mActivity, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(mActivity, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    Toast.makeText(mActivity, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProblemResponse> call, Throwable t) {
                call.cancel();

                Log.e("response-failure", t.toString());
                Toast.makeText(mActivity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadCountrySpinner() {

        ArrayList<Country> countryList = new ArrayList<>();

        Resources res = mActivity.getResources();
        Country country = new Country();

        String my_countries[] = res.getStringArray(R.array.Countries_full_array);
        String my_code[] = res.getStringArray(R.array.Countries_code_array);

        String cont;
        String code ;
        String[] arr1 =new String[my_countries.length];
        String[] arr2 =new String[my_code.length];

        for (int i = 0; i < my_countries.length; i++) {

            cont = my_countries[i];
            country.setName(cont);
            arr1[i] = cont;

            code = my_code[i];
            country.setCode(code);
            arr2[i] = code;

        }

        for (int j=0; j<arr1.length;j++){

            country.setName(arr1[j]);
            country.setCode(arr2[j]);

        }
        countryList.add(country);
        Log.d("clist",countryList.toString());

        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(mActivity,
                android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerCountry.setAdapter(adapter);

        Log.d("spinner_country",countryList.toString());

    }

    private void loadspinner(String email, String password) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        final ProgressDialog progressDialog = new ProgressDialog(mActivity,
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

                    List<String> uomlist = new ArrayList<String>();


                    if (order != null) {

                        for (int i = 0; i < order.size(); i++) {
//                            UOM u = new UOM();

                            String mUnit = order.get(i).getUnit();
//                            u.setUnit(mUnit);
                            uomlist.add(mUnit);
                        }

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(mActivity,
                                android.R.layout.simple_spinner_item, uomlist);

                        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
                        spinner.setAdapter(adapter);

                        Log.d("uom",uomlist.toString());
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        String d = response.body().getMessage();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                } else if (response.code() == 404) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(mActivity, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(mActivity, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else {
                    Toast.makeText(mActivity, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ProblemResponse> call, Throwable t) {
                call.cancel();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(mActivity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public int getItemCount() {
        return ProblemList.size();
    }

    public void updateAnswers(List<ProblemSKUs> pList) {
        ProblemList = pList;
        notifyDataSetChanged();
    }

    public class ProblemHolder extends RecyclerView.ViewHolder {

        TextView mCustomer, mpname, mpsku, mtorder, mweight, moquant, mprice, mUOM, mcountry;
        Button mupdate;

        public ProblemHolder(View itemView) {
            super(itemView);

            mCustomer = itemView.findViewById(R.id.problem_customer);
            mpname = itemView.findViewById(R.id.problem_item_name);
            mpsku = itemView.findViewById(R.id.problem_item_sku);
            mtorder = itemView.findViewById(R.id.problem_torder);
            moquant = itemView.findViewById(R.id.problem_oquantity);
            mweight = itemView.findViewById(R.id.problem_weight);
            mprice = itemView.findViewById(R.id.problem_price);
            mUOM = itemView.findViewById(R.id.problem_UOM);
            mcountry = itemView.findViewById(R.id.problem_country);
            mupdate = itemView.findViewById(R.id.btn_problem_insert);
        }
    }
}