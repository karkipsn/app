package com.example.colors2web.zummix_app.Adapter.Order_Adapters;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
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
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.OrderActivity.OrderShippingEditActivity;
import com.example.colors2web.zummix_app.Activities.OrderActivity.OrderShippingLogsActivity;
import com.example.colors2web.zummix_app.Activities.OrderActivity.ShippingLogsAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderShippingAddressesDetail;
import com.example.colors2web.zummix_app.POJO.OrderSearch.OrderDetails;
import com.example.colors2web.zummix_app.POJO.OrderShippingAddressLogs.OrderShippingAddressLog;
import com.example.colors2web.zummix_app.POJO.OrderShippingAddressLogs.ShippingLogResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SAdapter extends RecyclerView.Adapter<SAdapter.ShippingHolder> {
    private ArrayList<OrderShippingAddressesDetail> ShippingList;
    private OrderDetails Details;
    ArrayList<OrderShippingAddressLog> LogList = new ArrayList<>();

    private Context mContext;


    public SAdapter(Context context, ArrayList<OrderShippingAddressesDetail> shippingList) {
        mContext = context;
        ShippingList = shippingList;
    }

    @NonNull

    @Override
    public SAdapter.ShippingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.activity_sadapter, parent, false);
        return new ShippingHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SAdapter.ShippingHolder holder, int position) {

        OrderShippingAddressesDetail detail = ShippingList.get(position);

        holder.sname.setText(detail.getCustomerFname() + detail.getCustomerLname());
        holder.sphone.setText(detail.getCustomerPhone1());
        holder.semail.setText(detail.getCustomerEmail());
        String address = detail.getCustomerAddress1() + "\n"
                + detail.getCustomerCity() + ", " + detail.getCustomerState() +
                "\n" + detail.getCustomerCountry() + ", " + detail.getCustomerZip();
        holder.saddress.setText(address);

        String office = detail.getCustomerOfficeName();
        Log.d("officeis", office);

        if (office.equals("")) {
            holder.soffice.setVisibility(View.GONE);
        } else if (office.equals("-")) {
            holder.soffice.setVisibility(View.GONE);
        } else {

            holder.soffice.setVisibility(View.VISIBLE);
            holder.soffice.setText(office);

        }

    }

    @Override
    public int getItemCount() {
        return ShippingList.size();
    }

    public void updateAnswers(ArrayList<OrderShippingAddressesDetail> sarraylist, OrderDetails details) {
        ShippingList = sarraylist;
        Details = details;
        notifyDataSetChanged();
    }

    public class ShippingHolder extends RecyclerView.ViewHolder {
        TextView sname, sphone, saddress, semail, soffice, edit, logs;
        String o_id, o_type, o_status, o_edit_shipping_add;

        public ShippingHolder(View itemView) {
            super(itemView);

            sname = itemView.findViewById(R.id.sadpt_name);
            sphone = itemView.findViewById(R.id.sadpt_phone);
            saddress = itemView.findViewById(R.id.sadpt_address);
            soffice = itemView.findViewById(R.id.sadpt_office);
            semail = itemView.findViewById(R.id.sadpt_email);

            edit = itemView.findViewById(R.id.sadpt_edit);
            logs = itemView.findViewById(R.id.sadpt_logs);

            o_id = String.valueOf(Details.getId());
            o_type = Details.getOrderType();
            o_status = Details.getOrderStatus();
            o_edit_shipping_add = Details.getEditShippingAddress();

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            final String type = preferences.getString("group_type", "");

            if (type != null) {

                if (type.equals("Super Admin") || o_type.equals("1")) {
//               edit button display

                    if (o_status.equals("Shipped")) {
                        edit.setVisibility(View.GONE);
                    } else {
                        edit.setVisibility(View.VISIBLE);
                    }
                }
            }


            if (o_edit_shipping_add.equals("1")) {
//                  logs button to display
                logs.setVisibility(View.VISIBLE);
            }

            edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //  Pass order id alongside the intent
                    Intent editintent = new Intent(mContext, OrderShippingEditActivity.class);
                    editintent.putExtra("o_id_edit", o_id);
                    editintent.putParcelableArrayListExtra("ShippingList",ShippingList);
                    mContext.startActivity(editintent);
                }

            });

            logs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    see_logs(o_id, logs);

//                    ////  Pass order id alongside the intent
//                    Intent logsintent = new Intent(mContext, OrderShippingLogsActivity.class);
//                    logsintent.putExtra("o_id_logs", o_id);
//                    mContext.startActivity(logsintent);
                }
            });

        }
    }

    private void see_logs(String o_id, final TextView logs) {

        final ProgressDialog progressDialog = new ProgressDialog(mContext,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<ShippingLogResponse> call = apiInterface.getOrderShippingLogs(email, password, o_id);
        call.enqueue(new Callback<ShippingLogResponse>() {
            @Override
            public void onResponse(Call<ShippingLogResponse> call, Response<ShippingLogResponse> response) {

                if (response.isSuccessful()) {

                    ShippingLogResponse response1 = response.body();
                    String returntype = response1.getReturnType();
                    switch (returntype) {

                        case "success":

                            List<OrderShippingAddressLog> log = response1.getOrderShippingAddressLogs();

                            if (log != null) {
                                if(log.size() >1){
                                for (OrderShippingAddressLog os : log) {

                                    OrderShippingAddressLog mList = new OrderShippingAddressLog();
                                    String fname = os.getCustomerFname();
                                    String lname = os.getCustomerLname();
                                    String email = os.getCustomerEmail();
                                    String phone = os.getCustomerPhone1();
                                    String add1 = os.getCustomerAddress1();
                                    String add2 = os.getCustomerAddress2();
                                    String city = os.getCustomerCity();
                                    String state = os.getCustomerState();
                                    String zip = os.getCustomerZip();
                                    String country = os.getCustomerCountry();
                                    String comment = os.getComment();

                                    mList.setCustomerFname(fname);
                                    mList.setCustomerLname(lname);
                                    mList.setCustomerEmail(email);
                                    mList.setCustomerPhone1(phone);
                                    mList.setCustomerAddress1(add1);
                                    mList.setCustomerAddress2(add2);
                                    mList.setCustomerCity(city);
                                    mList.setCustomerState(state);
                                    mList.setCustomerZip(zip);
                                    mList.setCustomerCountry(country);
                                    mList.setComment(comment);
                                    LogList.add(mList);
                                }
//                                radapter.updateAnswers(LogList);
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }

                                call_pop_up(logs);}
                                else{
                                    Toast.makeText(mContext,"No Logs to Display",Toast.LENGTH_SHORT).show();
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }

                                }

                            }
                            break;

                        case "error":

                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            Toast.makeText(mContext, response1.getMessage(), Toast.LENGTH_SHORT).show();
                            break;
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
            public void onFailure(Call<ShippingLogResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }

            }
        });
    }

    private void call_pop_up(TextView logs) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View popupView = inflater.inflate(R.layout.modal_fragment_logs, null);

        WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int height = display.getHeight();
        int width = display.getWidth();

        final PopupWindow popup = new PopupWindow(popupView,
                (int) (width * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
        popup.setFocusable(true);
        popup.setOutsideTouchable(true);
        popup.setAnimationStyle(android.R.style.Animation_Dialog);

        popup.showAtLocation(logs, Gravity.CENTER, 0, 0); //Displaying popup

        final View container = (View) popup.getContentView().getParent();
        WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
        p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        p.dimAmount = 0.7f;
        wm.updateViewLayout(container, p);

        RecyclerView rv = popupView.findViewById(R.id.recycle_view);
        TextView cancel = popupView.findViewById(R.id.pop_up_cancel);

        final ShippingLogsAdapter kadapter;
        kadapter = new ShippingLogsAdapter(mContext, LogList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rv.setLayoutManager(mLayoutManager);

        rv.addItemDecoration(new MyDividerItemDecoration(mContext, LinearLayoutManager.HORIZONTAL, 16));
        rv.setItemAnimator(new DefaultItemAnimator());

        rv.setAdapter(kadapter);

        kadapter.updateAnswers(LogList);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup.dismiss();
                       kadapter.updateAnswer2();

            }
        });
    }

    private void deleteItem(int position) {
        ShippingList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, ShippingList.size());
//        holder.itemView.setVisibility(View.GONE);
    }
}

