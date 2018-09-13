package com.example.colors2web.zummix_app.Adapter.Admin_Tools_Adapters;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.POJO.ProblemSKU.PackageInput;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.Packages;
import com.example.colors2web.zummix_app.POJO.ProblemSKU.ProblemResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.PackageHolder> {
    Context mContext;
    List<Packages> PackList;
    RadioButton prb;
    String status, radio;

    public PackageAdapter(Context applicationContext, List<Packages> pList) {
        mContext = applicationContext;
        PackList = pList;
    }

    @NonNull
    @Override
    public PackageAdapter.PackageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_adapter, parent, false);
        return new PackageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PackageAdapter.PackageHolder holder, int position) {

        Packages pack = PackList.get(position);

        holder.mCustomer.setText(pack.getCompanyName());

        final String packname = pack.getPackageName();
        holder.mpname.setText(packname);

        holder.mpsku.setText(pack.getPackageSku());
        final String weight = pack.getWeight();
        holder.mweight.setText(weight);

        final String hight = pack.getHeight();
        holder.mheight.setText(hight);

        final String length = pack.getLength();
        holder.mlength.setText(length);

        final String cost = pack.getPackageCost();
        holder.mprice.setText(cost);

        final String with = pack.getWidth();
        holder.mwidth.setText(with);

        final String statuz = pack.getStatus();
        if (statuz.equals("0")) {
            holder.mstatus.setText("InActive");
        } else {
            holder.mstatus.setText("Active");
        }


        final Long id = pack.getId();

        holder.mupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.modal_update_packages, null);

                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
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

                Button submit, cancle;
                final EditText pname, psku, pweight, plength, pwidth, pheight, pcost;
                RadioGroup pradio;


                pname = popupView.findViewById(R.id.pack_adpt_name);
                pname.setText(packname);

                psku = popupView.findViewById(R.id.pack_sku);
                pweight = popupView.findViewById(R.id.pack_adpt_weight);
                pweight.setText(weight);

                plength = popupView.findViewById(R.id.pack_adpt_length);
                plength.setText(length);

                pwidth = popupView.findViewById(R.id.pack_adpt_width);
                pwidth.setText(with);
                pheight = popupView.findViewById(R.id.pack_adpt_height);
                pheight.setText(hight);
                pcost = popupView.findViewById(R.id.pack_adpt_cost);
                pcost.setText(cost);


//                radiogroup //send these radiogroup inside the button

                pradio = popupView.findViewById(R.id.toggle_update);

                if (statuz.equals("1")) {
                    pradio.check(R.id.yes);
                } else {
                    pradio.check(R.id.no);
                }

                pradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        switch (checkedId) {
                            case R.id.yes:

                                prb = popupView.findViewById(checkedId);
                                radio = prb.getText().toString();
                                Log.d("radio", radio);
                                break;

                            case R.id.no:
                                prb = popupView.findViewById(checkedId);
                                radio = prb.getText().toString();
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

                        String qname = pname.getText().toString();
                        String qweight = pweight.getText().toString();
                        String qlength = plength.getText().toString();
                        String qwidth = pwidth.getText().toString();
                        String qheight = pheight.getText().toString();
                        String qcost = pcost.getText().toString();

                        if (!Validation(qname)) {
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
                            pname.setError("Invalid Name");
                        }

                        if (radio.equals("YES")) {
                            status = "1";
                        } else {
                            status = "0";
                        }

                        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);

                        final String email = preferences.getString("email", "");
                        final String password = preferences.getString("password", "");
                        final String group_type = preferences.getString("group_type", "");
                        final String l_id = preferences.getString("l_id", "");

                        PackageInput input = new PackageInput(qname, qlength, qwidth, qheight, l_id, qweight, qcost, status);
                        putPackage(email, password, group_type, l_id, input, id, popup);

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

    }

    private void putPackage(String email, String password, String group_type, String l_id,
                            PackageInput input, Long id, final PopupWindow popup) {

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        String p_id = String.valueOf(id);
        Call<ProblemResponse> call = apiInterface.updatePackage(email, password, p_id, input);

        call.enqueue(new Callback<ProblemResponse>() {
            @Override
            public void onResponse(Call<ProblemResponse> call, Response<ProblemResponse> response) {

                if (response.isSuccessful()) {
                    ProblemResponse resp1 = response.body();
                    if (resp1.getReturnType().equals("success")) {
                        Toast.makeText(mContext, resp1.getMessage(), Toast.LENGTH_SHORT).show();
                        popup.dismiss();

//                        ((Activity)mContext).finish();
//                        ((Activity)mContext).startActivity(((Activity) mContext).getIntent());

                    } else {
                        String d = response.body().getMessage();
                        Toast.makeText(mContext, d, Toast.LENGTH_SHORT).show();


                    }
                } else if (response.code() == 401) {


                    Toast.makeText(mContext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {


                    Toast.makeText(mContext, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {

                    Toast.makeText(mContext, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ProblemResponse> call, Throwable t) {
                call.cancel();

                Log.e("response-failure", t.toString());
                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean Validation(String qname) {
        if (qname != null) {
            return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return PackList.size();
    }

    public void updateAnswers(List<Packages> pList) {
        PackList = pList;
        notifyDataSetChanged();
    }

    public class PackageHolder extends RecyclerView.ViewHolder {

        TextView mCustomer, mpname, mpsku, mheight, mweight, mlength, mprice, mwidth, mstatus;
        Button mupdate;

        public PackageHolder(View itemView) {
            super(itemView);

            mCustomer = itemView.findViewById(R.id.pack_customer);
            mpname = itemView.findViewById(R.id.pack_adpt_name);
            mpsku = itemView.findViewById(R.id.pack_sku);
            mweight = itemView.findViewById(R.id.pack_adpt_weight);
            mheight = itemView.findViewById(R.id.pack_height);
            mlength = itemView.findViewById(R.id.pack_adpt_length);
            mprice = itemView.findViewById(R.id.pack_adpt_cost);
            mwidth = itemView.findViewById(R.id.pack_adpt_width);
            mstatus = itemView.findViewById(R.id.pack_status);
            mupdate = itemView.findViewById(R.id.pack_update);
        }
    }
}
