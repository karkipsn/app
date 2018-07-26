package com.example.colors2web.zummix_app.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.colors2web.zummix_app.R;

import butterknife.BindViews;
import butterknife.ButterKnife;

public class ForgotActivity extends AppCompatActivity {
//    @BindViews(R.id.forgot_password)
//    TextView forgot;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        ButterKnife.bind(this);
    }
}

//package com.example.colors2web.zummix_app.Activities;
//
//import android.content.Intent;
//import android.content.SharedPreferences;
//import android.os.Bundle;
//import android.preference.PreferenceManager;
//import android.support.annotation.Nullable;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.example.colors2web.zummix_app.POJO.CustomerItem;
//import com.example.colors2web.zummix_app.POJO.OrderDetails;
//import com.example.colors2web.zummix_app.POJO.OrderSearchResponse;
//import com.example.colors2web.zummix_app.POJO.ProSearchRes;
//import com.example.colors2web.zummix_app.R;
//import com.example.colors2web.zummix_app.api.APIClient;
//import com.example.colors2web.zummix_app.api.APIInterface;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.BindViews;
//import butterknife.ButterKnife;
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class HomePageActivity extends AppCompatActivity {
//    @BindView(R.id.btn_item_search)
//    Button mitemsearch;
//
//    @BindView(R.id.btn_order_search)
//    Button mordersearch;
//
//    @BindView(R.id.display)
//    TextView mdisplay;
//    APIInterface apiInterface;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);
//        ButterKnife.bind(this);
//
//        apiInterface = APIClient.getClient().create(APIInterface.class);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
//        final String email = preferences.getString("email", "");
//        final String password = preferences.getString("password", "");
//
//        mitemsearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Call<ProSearchRes> call = apiInterface.getProduct(email, password);
//                call.enqueue(new Callback<ProSearchRes>() {
//                    @Override
//                    public void onResponse(Call<ProSearchRes> call, Response<ProSearchRes> response) {
//
//                        if (response.isSuccessful()) {
//                            ProSearchRes resp = response.body();
//                            Toast.makeText(getApplicationContext(), resp.getMessage().toString(), Toast.LENGTH_SHORT).show();
//
//                            List<CustomerItem> itemList = (List<CustomerItem>) resp.getCustomerItems();
//                            Log.d("index", String.valueOf(itemList.size()));
//                            String displayResponse = "";
//
//
//                            for (CustomerItem item : itemList) {
//
//
//                                displayResponse = "Product Details:" + "\n" +
//                                        "id: " + item.getId() + "\n" +
//                                        "customer_id: " + item.getCustomerId() + "\n"
//                                        + "item_sku_number: " + item.getItemSkuNumber() + "\n"
//                                        + "item_name: " + item.getItemName() + "\n"
//                                        + "item_description: " + item.getItemDescription() + "\n"
//                                        + "item_reorder_trigger: " + item.getItemReorderTrigger() + "\n"
//                                        + "item_status: " + item.getItemStatus() + "\n"
//                                        + "item_image: " + item.getItemImage() + "\n"
//                                        + "created_at: " + item.getCreatedAt() + "\n"
//                                        + "updated_at: " + item.getUpdatedAt() + "\n"
//                                        + "created_by: " + item.getCreatedBy() + "\n"
//                                        + "updated_by: " + item.getUpdatedBy() + "\n"
//                                        + "tr_quantity: " + item.getTrQuantity() + "\n"
//                                        + "min_quantity: " + item.getMinQuantity() + "\n"
//                                        + "is_low_inventory_notifications_sent: " + item.getIsLowInventoryNotificationsSent() + "\n"
//                                        + "is_paused: " + item.getIsPaused() + "\n"
//                                        + "cycle_count_list: " + item.getCycleCountList() + "\n"
//                                        + "is_cpg: " + item.getIsCpg() + "\n"
//                                        + "cpg_sku_number: " + item.getCpgSkuNumber() + "\n"
//                                        + "zummix_sku_number: " + item.getZummixSkuNumber() + "\n"
//                                        + "barcode_file_name: " + item.getBarcodeFileName() + "\n"
//                                        + "price: " + item.getPrice() + "\n"
//                                        + "weight: " + item.getWeight() + "\n"
//                                        + "UOM: " + item.getUOM() + "\n"
//                                        + "country_origin: " + item.getCountryOrigin() + "\n"
//                                        + "is_over_size_product: " + item.getIsOverSizeProduct() + "\n"
//                                        + "special_program_number: " + item.getSpecialProgramNumber() + "\n"
//                                        + "is_retire_product: " + item.getIsRetireProduct() + "\n" +
//                                        "is_one_time_product: " + item.getIsOneTimeProduct() + "\n" +
//                                        "Company_name: " + item.getCompanyName() + "\n";
//
//                                mdisplay.setText(displayResponse);
//
//                            }
//
//                        } else if (response.code() == 401) {
//
//                            Toast.makeText(getApplicationContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
//                            Log.d("Error", response.errorBody().toString());
//
//
//                        } else {
//                            Toast.makeText(HomePageActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ProSearchRes> call, Throwable t) {
//                        call.cancel();
//                        Log.e("response-failure", t.toString());
//                        Toast.makeText(HomePageActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//
//            }
//        });
//
//        mordersearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Call<OrderSearchResponse> call = apiInterface.getOrder(email, password);
//                call.enqueue(new Callback<OrderSearchResponse>() {
//                    @Override
//                    public void onResponse(Call<OrderSearchResponse> call, Response<OrderSearchResponse> response) {
//
//                        if (response.isSuccessful()) {
//                            OrderSearchResponse resp1 = response.body();
//
//                            OrderDetails order = resp1.getOrderDetails();
//                            Toast.makeText(getApplicationContext(), resp1.getMessage().toString(), Toast.LENGTH_SHORT).show();
//
//                            String displayResponse = "";
//
////                             for (OrderDetails order : orderList) {
//
//                            displayResponse = "Order Details:" + "\n" +
//                                    "id: " + order.getId() + "\n" +
//                                    "customer_name: " + order.getCustomerName() + "\n"
//                                    + "customer_email: " + order.getCustomerEmail() + "\n"
//                                    + "order_number: " + order.getOrderNumber() + "\n"
//                                    + "order_special_instruction: " + order.getOrderSpecialInstruction() + "\n"
//                                    + "ship_method: " + order.getShipMethod() + "\n"
//                                    + "order_date: " + order.getOrderDate() + "\n"
//                                    + "created_at: " + order.getCreatedAt() + "\n"
//                                    + "updated_at: " + order.getUpdatedAt() + "\n"
//                                    + "created_by: " + order.getCreatedBy() + "\n"
//                                    + "updated_by: " + order.getUpdatedBy() + "\n"
//                                    + "customer_id: " + order.getCustomerId() + "\n"
//                                    + "order_status: " + order.getOrderStatus() + "\n"
//                                    + "batch_number: " + order.getBatchNumber() + "\n"
//                                    + "order_type: " + order.getOrderType() + "\n"
//                                    + "order_token: " + order.getOrderToken() + "\n"
//                                    + "was_token_order: " + order.getWasTokenOrder() + "\n"
//                                    + "shopping_list_number: " + order.getShoppingListNumber() + "\n"
//                                    + "is_batch_printed: " + order.getIsBatchPrinted() + "\n"
//                                    + "is_pp_request_created: " + order.getIsPpRequestCreated() + "\n"
//                                    + "employee_id: " + order.getEmployeeId() + "\n"
//                                    + "department: " + order.getDepartment() + "\n"
//                                    + "is_ship_it_now_batch: " + order.getIsShipItNowBatch() + "\n"
//                                    + "commit: " + order.getCommit() + "\n"
//                                    + "problem_order: " + order.getProblemOrder() + "\n"
//                                    + "applied_credit: " + order.getAppliedCredit() + "\n"
//                                    + "is_refunded: " + order.getIsRefunded() + "\n"
//                                    + "refund_note: " + order.getRefundNote() + "\n" +
//                                    "refund_type: " + order.getRefundType() + "\n" +
//                                    "refunded_user_id: " + order.getRefundedUserId() + "\n" +
//                                    "refunded_user_name: " + order.getRefundedUserName() + "\n" +
//                                    "refunded_user_email: " + order.getRefundedUserEmail() + "\n" +
//                                    "print_item: " + order.getPrintItem() + "\n" +
//                                    "is_ready_to_batch: " + order.getIsReadyToBatch() + "\n" +
//                                    "refunded_at: " + order.getRefundedAt() + "\n" +
//                                    "group_ship_id: " + order.getGroupShipId() + "\n" +
//                                    "edit_shipping_address: " + order.getEditShippingAddress() + "\n" +
//                                    "is_claimed: " + order.getIsClaimed() + "\n" +
//                                    "pre_sale: " + order.getPreSale() + "\n" +
//                                    "special_program_order: " + order.getSpecialProgramOrder() + "\n" +
//                                    "donot_arrive_before: " + order.getDonotArriveBefore() + "\n" +
//                                    "in_hands_by: " + order.getInHandsBy() + "\n" +
//                                    "customer_company_name: " + order.getCustomerCompanyName() + "\n" +
//                                    "csv_action_log_id: " + order.getCsvActionLogId() + "\n" +
//                                    "is_paused: " + order.getIsPaused() + "\n" +
//                                    "order_channel: " + order.getOrderChannel() + "\n" +
//                                    "is_return_label: " + order.getIsReturnLabel() + "\n" +
//                                    "tag: " + order.getTag() + "\n";
//
//                            mdisplay.setText(displayResponse);
//
////                            }
//                        } else if (response.code() == 401) {
//
//                            Toast.makeText(getApplicationContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
//                            Log.d("Error", response.errorBody().toString());
//
//
//                        } else {
//                            Toast.makeText(HomePageActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<OrderSearchResponse> call, Throwable t) {
//                        call.cancel();
//                        Log.e("response-failure", t.toString());
//                        Toast.makeText(HomePageActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
//
//                    }
//                });
//
//            }
//        });
//    }
//}
