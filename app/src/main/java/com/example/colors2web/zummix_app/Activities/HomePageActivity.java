package com.example.colors2web.zummix_app.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.POJO.CompanyDetails;
import com.example.colors2web.zummix_app.POJO.CustomerItem;
import com.example.colors2web.zummix_app.POJO.FieldOffice;
import com.example.colors2web.zummix_app.POJO.MasterBox;
import com.example.colors2web.zummix_app.POJO.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.OrderDetails;
import com.example.colors2web.zummix_app.POJO.OrderSearchResponse;
import com.example.colors2web.zummix_app.POJO.OrderShippingAddress;
import com.example.colors2web.zummix_app.POJO.OrderTDetails;
import com.example.colors2web.zummix_app.POJO.OrdertrackResponse;
import com.example.colors2web.zummix_app.POJO.ProSearchRes;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageActivity extends AppCompatActivity {
    @BindView(R.id.btn_item_search)
    Button mitemsearch;

    @BindView(R.id.btn_order_search)
    Button mordersearch;

    @BindView(R.id.btn_otrack_search)
    Button motracksearch;

    @BindView(R.id.btn_box_search)
    Button mboxsearch;

    @BindView(R.id.input)
    EditText minput;

    @BindView(R.id.display)
    TextView mdisplay;

    @BindView(R.id.toolbar)
    Toolbar toolbar;


    @BindView(R.id.display2)
    TextView mdisplay2;


    APIInterface apiInterface;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        apiInterface = APIClient.getClient().create(APIInterface.class);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");


        mitemsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Path = minput.getText().toString();
                Call<ProSearchRes> call = apiInterface.getProduct1(email, password, Path);
                call.enqueue(new Callback<ProSearchRes>() {
                    @Override
                    public void onResponse(Call<ProSearchRes> call, Response<ProSearchRes> response) {

                        if (response.isSuccessful()) {
                            ProSearchRes resp = response.body();
                            Toast.makeText(getApplicationContext(), resp.getMessage().toString(), Toast.LENGTH_SHORT).show();

                            List<CustomerItem> itemList = (List<CustomerItem>) resp.getCustomerItems();
                            //   Log.d("index", String.valueOf(itemList.size()));
                            String displayResponse = "";

                            if (itemList != null) {
                                for (CustomerItem item : itemList) {

                                    displayResponse = "Product Details:" + "\n" +
                                            "id: " + item.getId() + "\n" +
                                            "customer_id: " + item.getCustomerId() + "\n"
                                            + "item_sku_number: " + item.getItemSkuNumber() + "\n"
                                            + "item_name: " + item.getItemName() + "\n"
                                            + "item_description: " + item.getItemDescription() + "\n"
                                            + "item_reorder_trigger: " + item.getItemReorderTrigger() + "\n"
                                            + "item_status: " + item.getItemStatus() + "\n"
                                            + "item_image: " + item.getItemImage() + "\n"
                                            + "created_at: " + item.getCreatedAt() + "\n"
                                            + "updated_at: " + item.getUpdatedAt() + "\n"
                                            + "created_by: " + item.getCreatedBy() + "\n"
                                            + "updated_by: " + item.getUpdatedBy() + "\n"
                                            + "tr_quantity: " + item.getTrQuantity() + "\n"
                                            + "min_quantity: " + item.getMinQuantity() + "\n"
                                            + "is_low_inventory_notifications_sent: " + item.getIsLowInventoryNotificationsSent() + "\n"
                                            + "is_paused: " + item.getIsPaused() + "\n"
                                            + "cycle_count_list: " + item.getCycleCountList() + "\n"
                                            + "is_cpg: " + item.getIsCpg() + "\n"
                                            + "cpg_sku_number: " + item.getCpgSkuNumber() + "\n"
                                            + "zummix_sku_number: " + item.getZummixSkuNumber() + "\n"
                                            + "barcode_file_name: " + item.getBarcodeFileName() + "\n"
                                            + "price: " + item.getPrice() + "\n"
                                            + "weight: " + item.getWeight() + "\n"
                                            + "UOM: " + item.getUOM() + "\n"
                                            + "country_origin: " + item.getCountryOrigin() + "\n"
                                            + "is_over_size_product: " + item.getIsOverSizeProduct() + "\n"
                                            + "special_program_number: " + item.getSpecialProgramNumber() + "\n"
                                            + "is_retire_product: " + item.getIsRetireProduct() + "\n" +
                                            "is_one_time_product: " + item.getIsOneTimeProduct() + "\n" +
                                            "Company_name: " + item.getCompanyName() + "\n";

                                    mdisplay.setText(displayResponse);

                                }
                            } else {
                                displayResponse = response.body().getMessage();
                                mdisplay.setText(displayResponse);

                            }

                        } else if (response.code() == 401) {

                            Toast.makeText(getApplicationContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());


                        } else {
                            Toast.makeText(HomePageActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<ProSearchRes> call, Throwable t) {
                        call.cancel();
                        Log.e("response-failure", t.toString());
                        Toast.makeText(HomePageActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                    }
                });


            }
        });

        mordersearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Path = minput.getText().toString();
                Intent i = new Intent(HomePageActivity.this, OrderSearch2Activity.class);
                i.putExtra("Path",Path);
                startActivity(i);

            }
        });


        motracksearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Path = minput.getText().toString();
                Call<OrdertrackResponse> call = apiInterface.getOrderByTrack(email, password, Path);
                call.enqueue(new Callback<OrdertrackResponse>() {
                    @Override
                    public void onResponse(Call<OrdertrackResponse> call, Response<OrdertrackResponse> response) {

                        if (response.isSuccessful()) {
                            OrdertrackResponse resp1 = response.body();
                            List<OrderTDetails> orderList = (List<OrderTDetails>) resp1.getOrderDetails();

                            String displayResponse = "";

                            if (orderList != null) {
                                for (OrderTDetails order : orderList) {

                                    displayResponse = "Order Details:" + "\n" +
                                            "id: " + order.getId() + "\n" +
                                            "customer_name: " + order.getCustomerName() + "\n"
                                            + "customer_email: " + order.getCustomerEmail() + "\n"
                                            + "order_number: " + order.getOrderNumber() + "\n"
                                            + "order_special_instruction: " + order.getOrderSpecialInstruction() + "\n"
                                            + "ship_method: " + order.getShipMethod() + "\n"
                                            + "order_date: " + order.getOrderDate() + "\n"
                                            + "created_at: " + order.getCreatedAt() + "\n"
                                            + "updated_at: " + order.getUpdatedAt() + "\n"
                                            + "created_by: " + order.getCreatedBy() + "\n"
                                            + "updated_by: " + order.getUpdatedBy() + "\n"
                                            + "customer_id: " + order.getCustomerId() + "\n"
                                            + "order_status: " + order.getOrderStatus() + "\n"
                                            + "batch_number: " + order.getBatchNumber() + "\n"
                                            + "order_type: " + order.getOrderType() + "\n"
                                            + "order_token: " + order.getOrderToken() + "\n"
                                            + "was_token_order: " + order.getWasTokenOrder() + "\n"
                                            + "shopping_list_number: " + order.getShoppingListNumber() + "\n"
                                            + "is_batch_printed: " + order.getIsBatchPrinted() + "\n"
                                            + "is_pp_request_created: " + order.getIsPpRequestCreated() + "\n"
                                            + "employee_id: " + order.getEmployeeId() + "\n"
                                            + "department: " + order.getDepartment() + "\n"
                                            + "is_ship_it_now_batch: " + order.getIsShipItNowBatch() + "\n"
                                            + "commit: " + order.getCommit() + "\n"
                                            + "problem_order: " + order.getProblemOrder() + "\n"
                                            + "applied_credit: " + order.getAppliedCredit() + "\n"
                                            + "is_refunded: " + order.getIsRefunded() + "\n"
                                            + "refund_note: " + order.getRefundNote() + "\n" +
                                            "refund_type: " + order.getRefundType() + "\n" +
                                            "refunded_user_id: " + order.getRefundedUserId() + "\n" +
                                            "refunded_user_name: " + order.getRefundedUserName() + "\n" +
                                            "refunded_user_email: " + order.getRefundedUserEmail() + "\n" +
                                            "print_item: " + order.getPrintItem() + "\n" +
                                            "is_ready_to_batch: " + order.getIsReadyToBatch() + "\n" +
                                            "refunded_at: " + order.getRefundedAt() + "\n" +
                                            "group_ship_id: " + order.getGroupShipId() + "\n" +
                                            "edit_shipping_address: " + order.getEditShippingAddress() + "\n" +
                                            "is_claimed: " + order.getIsClaimed() + "\n" +
                                            "pre_sale: " + order.getPreSale() + "\n" +
                                            "special_program_order: " + order.getSpecialProgramOrder() + "\n" +
                                            "donot_arrive_before: " + order.getDonotArriveBefore() + "\n" +
                                            "in_hands_by: " + order.getInHandsBy() + "\n" +
                                            "customer_company_name: " + order.getCustomerCompanyName() + "\n" +
                                            "csv_action_log_id: " + order.getCsvActionLogId() + "\n" +
                                            "is_paused: " + order.getIsPaused() + "\n" +
                                            "order_channel: " + order.getOrderChannel() + "\n" +
                                            "is_return_label: " + order.getIsReturnLabel() + "\n" +
                                            "tag: " + order.getTag() + "\n" +
                                            "tracking_code: " + order.getTrackingCode() + "\n" +
                                            "customer_office_name: " + order.getCustomerOfficeName() + "\n" +
                                            "ship_to: " + order.getShipTo() + "\n";

                                    mdisplay.setText(displayResponse);

                                }
                            } else {
                                displayResponse = response.body().getMessage();
                                mdisplay.setText(displayResponse);
                            }
                        } else if (response.code() == 401) {

                            Toast.makeText(getApplicationContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());


                        } else {
                            Toast.makeText(HomePageActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<OrdertrackResponse> call, Throwable t) {
                        call.cancel();
                        Log.e("response-failure", t.toString());
                        Toast.makeText(HomePageActivity.this, "Network Error", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        mboxsearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Path = minput.getText().toString();
                Call<MasterBoxResponse> call = apiInterface.getMasterBoxes(email, password, Path);
                call.enqueue(new Callback<MasterBoxResponse>() {
                    @Override
                    public void onResponse(Call<MasterBoxResponse> call, Response<MasterBoxResponse> response) {
                        if (response.isSuccessful()) {
                            MasterBoxResponse resp = response.body();

                            OrderShippingAddress osa = resp.getOrderShippingAddress();
                            List<MasterBox> mList = resp.getMasterBoxes();
                            CompanyDetails cd = resp.getCompanyDetails();
                            FieldOffice fo = resp.getFieldOffice();
                            String displayresponse = "";
                            String displayresponse1 = "";

                            if (osa != null || mList != null || cd != null || fo != null) {
                                displayresponse = "Field Office Details:" + "\n" + "\n" +
                                        "id: " + fo.getId() + "\n" +
                                        "bin: " + fo.getBin() + "\n"
                                        + "created_at: " + fo.getCreatedAt() + "\n"
                                        + "updated_at: " + fo.getUpdatedAt() + "\n"
                                        + "created_by: " + fo.getCreatedBy() + "\n"
                                        + "updated_by: " + fo.getUpdatedBy() + "\n"
                                        + "customer_id: " + fo.getCustomerId() + "\n"
                                        + "manager1_name: " + fo.getManager1Name() + "\n"
                                        + "manager1_email: " + fo.getManager1Email() + "\n"
                                        + "manager1_phone: " + fo.getManager1Phone() + "\n"
                                        + "manager2_name: " + fo.getManager2Name() + "\n"
                                        + "manager2_email: " + fo.getManager2Email() + "\n"
                                        + "manager2_phone: " + fo.getManager2Phone() + "\n"
                                        + "ship_to_name: " + fo.getShipToName() + "\n"
                                        + "address1: " + fo.getAddress1() + "\n"
                                        + "address2: " + fo.getAddress2() + "\n"
                                        + "city: " + fo.getCity() + "\n"
                                        + "country: " + fo.getCountry() + "\n"
                                        + "state: " + fo.getState() + "\n"
                                        + "zip: " + fo.getZip() + "\n" + "\n"
                                        + "Order Shipping Address:" + "\n" + "\n" +
                                        "id: " + osa.getId() + "\n" +
                                        "order_id: " + osa.getId() + "\n"
                                        + "customer_fname: " + osa.getCustomerFname() + "\n"
                                        + "customer_mname: " + osa.getCustomerMname() + "\n"
                                        + "customer_lname: " + osa.getCustomerLname() + "\n"
                                        + "customer_email: " + osa.getCustomerEmail() + "\n"
                                        + "customer_phone1: " + osa.getCustomerPhone1() + "\n"
                                        + "customer_phone2: " + osa.getCustomerPhone2() + "\n"
                                        + "customer_address1: " + osa.getCustomerAddress1() + "\n"
                                        + "customer_address2: " + osa.getCustomerAddress2() + "\n"
                                        + "customer_city: " + osa.getCustomerCity() + "\n"
                                        + "customer_state: " + osa.getCustomerState() + "\n"
                                        + "customer_zip: " + osa.getCustomerZip() + "\n"
                                        + "customer_country: " + osa.getCustomerCountry() + "\n"
                                        + "dept_code: " + osa.getDeptCode() + "\n"
                                        + "created_at: " + osa.getCreatedAt() + "\n"
                                        + "updated_at: " + osa.getUpdatedAt() + "\n"
                                        + "created_by: " + osa.getCreatedBy() + "\n"
                                        + "updated_by: " + osa.getUpdatedBy() + "\n"
                                        + "Customer_Office: " + osa.getCustomerOfficeName() + "\n"
                                        + "Note: " + osa.getNote() + "\n" + "\n"
                                        + "Company Details: " + "\n" + "\n"
                                        + "id: " + cd.getId() + "\n"
                                        + "parent_id: " + cd.getParentId() + "\n"
                                        + "company_name: " + cd.getCompanyName() + "\n"
                                        + "company_alias: " + cd.getCompanyAlias() + "\n" +
                                        "company_emails" + cd.getCompanyEmails() + "\n"
                                        + "company_address1: " + cd.getCompanyAddress1() + "\n"
                                        + "company_address2: " + cd.getCompanyAddress2() + "\n"
                                        + "company_city: " + cd.getCompanyCity() + "\n"
                                        + "company_state: " + cd.getCompanyState() + "\n"
                                        + "company_zip: " + cd.getCompanyZip() + "\n"
                                        + "company_country: " + cd.getCompanyCountry() + "\n"
                                        + "contact_fname: " + cd.getContactFname() + "\n"
                                        + "contact_mname: " + cd.getContactMname() + "\n"
                                        + "contact_lname: " + cd.getContactLname() + "\n"
                                        + "contact_phone_primary: " + cd.getContactPhonePrimary() + "\n"
                                        + "contact_phone_secondary: " + cd.getContactPhoneSecondary() + "\n"
                                        + "contact_fax: " + cd.getContactFax() + "\n"
                                        + "contact_email: " + cd.getCompanyEmail() + "\n"
                                        + "created_at: " + cd.getCreatedAt() + "\n"
                                        + "updated_at: " + cd.getUpdatedAt() + "\n"
                                        + "created_by: " + cd.getCreatedBy() + "\n"
                                        + "updated_by: " + cd.getUpdatedBy() + "\n"
                                        + "tr_quantity: " + cd.getTrQuantity() + "\n"
                                        + "min_quantity: " + cd.getMinQuantity() + "\n"
                                        + "company_email: " + cd.getCompanyEmail() + "\n"
                                        + "ship_company: " + cd.getShipCompany() + "\n"
                                        + "ship_company_type: " + cd.getShipCompanyType() + "\n"
                                        + "account_number: " + cd.getAccountNumber() + "\n"
                                        + "username: " + cd.getUsername() + "\n"
                                        + "password: " + cd.getPassword() + "\n"
                                        + "post_back_url: " + cd.getPostBackUrl() + "\n"
                                        + "secret_api_key: " + cd.getSecretApiKey() + "\n"
                                        + "product_post_back_url: " + cd.getProductPostBackUrl() + "\n"
                                        + "ior_emails: " + cd.getIorEmails() + "\n"
                                        + "handeling_fee: " + cd.getHandelingFee() + "\n"
                                        + "zummix_markup: " + cd.getZummixMarkup() + "\n"
                                        + "store_markup: " + cd.getStoreMarkup() + "\n"
                                        + "store_type: " + cd.getStoreType() + "\n"
                                        + "rep_name: " + cd.getRepName() + "\n"
                                        + "rep_email: " + cd.getRepEmail() + "\n"
                                        + "rep_cell_no: " + cd.getRepCellNo() + "\n"
                                        + "rep_office_phone_no: " + cd.getRepOfficePhoneNo() + "\n"
                                        + "customer_city_bins: " + cd.getCustomerCityBins() + "\n"
                                        + "return_company_name: " + cd.getReturnCompanyName() + "\n"
                                        + "return_company_address1: " + cd.getReturnCompanyAddress1() + "\n"
                                        + "return_company_address2: " + cd.getReturnCompanyAddress2() + "\n"
                                        + "return_company_city: " + cd.getReturnCompanyCity() + "\n"
                                        + "return_company_state: " + cd.getReturnCompanyState() + "\n"
                                        + "return_company_zip: " + cd.getReturnCompanyZip() + "\n"
                                        + "return_company_country: " + cd.getReturnCompanyCountry() + "\n"
                                        + "return_company_url: " + cd.getReturnCompanyUrl() + "\n"
                                        + "return_company_phone: " + cd.getReturnCompanyPhone() + "\n"
                                        + "store_int_markup: " + cd.getStoreIntMarkup() + "\n"
                                        + "shipping_fee: " + cd.getShippingFee() + "\n";


                                mdisplay.setText(displayresponse);

                                for (MasterBox mb : mList) {

                                    displayresponse1 += "MASTER BOX  " + "\n" + "\n" +
                                            "barcode_number: " + mb.getBarcodeNumber() + "\n" +
                                            "barcode_file_name: " + mb.getBarcodeFileName() + "\n"
                                            + "id: " + mb.getId() + "\n"
                                            + "city_bin_id: " + mb.getCityBinId() + "\n"
                                            + "customer_id: " + mb.getCustomerId() + "\n"
                                            + "box_number: " + mb.getBoxNumber() + "\n"
                                            + "master_box_code: " + mb.getMasterBoxCode() + "\n"
                                            + "order_id: " + mb.getOrderId() + "\n"
                                            + "master_box_tracking_code: " + mb.getMasterBoxTrackingCode() + "\n"
                                            + "is_ready_to_ship: " + mb.getIsReadyToShip() + "\n"
                                            + "created_at: " + mb.getCreatedAt() + "\n"
                                            + "updated_at: " + mb.getUpdatedAt() + "\n"
                                            + "created_by: " + mb.getCreatedBy() + "\n"
                                            + "updated_by: " + mb.getUpdatedBy() + "\n"
                                            + "is_zummix_ownwed: " + mb.getIsZummixOwnwed() + "\n"
                                            + "is_special: " + mb.getIsSpecial() + "\n"
                                            + "shipping_cost: " + mb.getShippingCost() + "\n"
                                            + "shipping_label :" + mb.getShippingLabel() + "\n"
                                            + "zummix_shipping_cost: " + mb.getZummixShippingCost() + "\n"
                                            + "commercial_invoice: " + mb.getCommercialInvoice() + "\n"
                                            + "auxiallary_label: " + mb.getAuxiallaryLabel() + "\n"
                                            + "markup_percentage: " + mb.getMarkupPercentage() + "\n"
                                            + "weight: " + mb.getWeight() + "\n"
                                            + "height: " + mb.getHeight() + "\n"
                                            + "width: " + mb.getWidth() + "\n"
                                            + "length: " + mb.getLength() + "\n"
                                            + "shipping_method: " + mb.getShipMethod() + "\n"
                                            + "shipping_company: " + mb.getShippingCompany() + "\n"
                                            + "reason_for_shipping_outside: " + mb.getReasonForShippingOutside() + "\n"
                                            + "company_name: " + mb.getCompanyName() + "\n"
                                            + "company_email: " + mb.getCompanyEmail() + "\n"
                                            + "city_bin: " + mb.getCityBin() + "\n"
                                            + "ship_to_name: " + mb.getShipToName() + "\n"
                                            + "ship_to_email: " + mb.getShipToEmail() + "\n"
                                            + "order_number: " + mb.getOrderNumber() + "\n"
                                            + "ship_method: " + mb.getShipMethod() + "\n"
                                            + "shipper: " + mb.getShipper() + "\n" + "\n";

                                    mdisplay2.setText(displayresponse1);


                                }

                            } else {
                                String displayResponse = response.body().getMessage();
                                String displayResponse1 = null;
                                mdisplay.setText(displayResponse);
                                mdisplay2.setText(displayResponse1);

                            }

                        } else if (response.code() == 401) {

                            Toast.makeText(getApplicationContext(), "Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                            Log.d("Error", response.errorBody().toString());


                        } else {
                            Toast.makeText(HomePageActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onFailure(Call<MasterBoxResponse> call, Throwable t) {
                        call.cancel();
                        Log.e("response-failure", t.toString());
                        Toast.makeText(HomePageActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}
