package com.example.colors2web.zummix_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.POJO.OrderDetails;
import com.example.colors2web.zummix_app.POJO.OrderSearchResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderSearchActivity extends AppCompatActivity {
    APIInterface apiInterface;

    @BindView(R.id.dis_head)
    TextView head;

    @BindView(R.id.dis_o_status)
    TextView o_status;

    @BindView(R.id.dis_emp_id)
    TextView emp_id;

    @BindView(R.id.dis_s_no)
    TextView s_no;

    @BindView(R.id.dis_s_email)
    TextView s_email;

    @BindView(R.id.dis_o_no)
    TextView o_no;

    @BindView(R.id.dis_o_date)
    TextView o_date;

    @BindView(R.id.dis_ship_m)
    TextView ship_m;

    @BindView(R.id.dis_o_type)
    TextView o_type;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.dis_batch)
    TextView batch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordersearch);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        Log.d("PathMail", email);

        Intent i = getIntent();
        final String Path = i.getExtras().getString("Path");
        Log.d("Path", Path);


        Call<OrderSearchResponse> call = apiInterface.getOrder1(email, password, Path);
        call.enqueue(new Callback<OrderSearchResponse>() {
            @Override
            public void onResponse(Call<OrderSearchResponse> call, Response<OrderSearchResponse> response) {

                if (response.isSuccessful()) {
                    OrderSearchResponse resp1 = response.body();

                    OrderDetails order = resp1.getOrderDetails();
                    Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();

                    String displayResponse = "";

//                             for (OrderDetails order : orderList) {
                    if (order != null) {

                        head.setText("Order Details Of " + order.getOrderNumber());
                        batch.setText("Batch Number : " + order.getOrderNumber());
                        s_email.setText(order.getCustomerEmail());
                        o_no.setText(order.getOrderNumber());
                        o_date.setText(order.getOrderDate());
                        ship_m.setText(order.getShipMethod());
                        o_type.setText(order.getOrderType());
                        o_status.setText(order.getOrderStatus());
                        emp_id.setText(order.getEmployeeId());


//                        displayResponse = "Order Details:" + "\n" +
//                                "id: " + order.getId() + "\n" +
//                                "customer_name: " + order.getCustomerName() + "\n"
//                                + "customer_email: " + order.getCustomerEmail() + "\n"
//                                + "order_number: " + order.getOrderNumber() + "\n"
//                                + "order_special_instruction: " + order.getOrderSpecialInstruction() + "\n"
//                                + "ship_method: " + order.getShipMethod() + "\n"
//                                + "order_date: " + order.getOrderDate() + "\n"
//                                + "created_at: " + order.getCreatedAt() + "\n"
//                                + "updated_at: " + order.getUpdatedAt() + "\n"
//                                + "created_by: " + order.getCreatedBy() + "\n"
//                                + "updated_by: " + order.getUpdatedBy() + "\n"
//                                + "customer_id: " + order.getCustomerId() + "\n"
//                                + "order_status: " + order.getOrderStatus() + "\n"
//                                + "batch_number: " + order.getBatchNumber() + "\n"
//                                + "order_type: " + order.getOrderType() + "\n"
//                                + "order_token: " + order.getOrderToken() + "\n"
//                                + "was_token_order: " + order.getWasTokenOrder() + "\n"
//                                + "shopping_list_number: " + order.getShoppingListNumber() + "\n"
//                                + "is_batch_printed: " + order.getIsBatchPrinted() + "\n"
//                                + "is_pp_request_created: " + order.getIsPpRequestCreated() + "\n"
//                                + "employee_id: " + order.getEmployeeId() + "\n"
//                                + "department: " + order.getDepartment() + "\n"
//                                + "is_ship_it_now_batch: " + order.getIsShipItNowBatch() + "\n"
//                                + "commit: " + order.getCommit() + "\n"
//                                + "problem_order: " + order.getProblemOrder() + "\n"
//                                + "applied_credit: " + order.getAppliedCredit() + "\n"
//                                + "is_refunded: " + order.getIsRefunded() + "\n"
//                                + "refund_note: " + order.getRefundNote() + "\n" +
//                                "refund_type: " + order.getRefundType() + "\n" +
//                                "refunded_user_id: " + order.getRefundedUserId() + "\n" +
//                                "refunded_user_name: " + order.getRefundedUserName() + "\n" +
//                                "refunded_user_email: " + order.getRefundedUserEmail() + "\n" +
//                                "print_item: " + order.getPrintItem() + "\n" +
//                                "is_ready_to_batch: " + order.getIsReadyToBatch() + "\n" +
//                                "refunded_at: " + order.getRefundedAt() + "\n" +
//                                "group_ship_id: " + order.getGroupShipId() + "\n" +
//                                "edit_shipping_address: " + order.getEditShippingAddress() + "\n" +
//                                "is_claimed: " + order.getIsClaimed() + "\n" +
//                                "pre_sale: " + order.getPreSale() + "\n" +
//                                "special_program_order: " + order.getSpecialProgramOrder() + "\n" +
//                                "donot_arrive_before: " + order.getDonotArriveBefore() + "\n" +
//                                "in_hands_by: " + order.getInHandsBy() + "\n" +
//                                "customer_company_name: " + order.getCustomerCompanyName() + "\n" +
//                                "csv_action_log_id: " + order.getCsvActionLogId() + "\n" +
//                                "is_paused: " + order.getIsPaused() + "\n" +
//                                "order_channel: " + order.getOrderChannel() + "\n" +
//                                "is_return_label: " + order.getIsReturnLabel() + "\n" +
//                                "tag: " + order.getTag() + "\n";

                        // mdisplay.setText(displayResponse);

//                            }
                    } else {
                        String d = response.body().getMessage();
                        head.setText(d);
                    }
                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());


                } else if (response.code() == 404) {

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(OrderSearchActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<OrderSearchResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                Toast.makeText(OrderSearchActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
