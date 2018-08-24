package com.example.colors2web.zummix_app.Activities.OrderActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.OrderSearch2Activity;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditPut;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderEditActivity extends AppCompatActivity {

    @BindView(R.id.customer_fname)
    EditText mcustomer_fname;

    @BindView(R.id.customer_lname)
    EditText mcustomer_lname;

    @BindView(R.id.customer_email)
    EditText mcustomer_email;

    @BindView(R.id.customer_phone1)
    EditText mcustomer_phone1;

    @BindView(R.id.customer_address1)
    EditText mcustomer_address1;

    @BindView(R.id.customer_address2)
    EditText mcustomer_address2;

    @BindView(R.id.customer_city)
    EditText mcustomer_city;

    @BindView(R.id.customer_country)
    EditText mcustomer_country;

    @BindView(R.id.customer_state)
    EditText mcustomer_state;

    @BindView(R.id.customer_zip)
    EditText mcustomer_zip;

    @BindView(R.id.comment)
    EditText mcomment;

    @BindView(R.id.btn_order_edit_submit)
    Button moedit;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String odr;

    String updated_by, customer_fname, customer_lname, customer_email, customer_phone1,
            customer_address1, customer_address2, customer_city, customer_country,
            customer_state, customer_zip, comment;

    APIInterface apiInterface;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_edit);
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        //for toolbarsetup with back arrow
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        moedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customer_fname = mcustomer_fname.getText().toString();
                customer_lname = mcustomer_lname.getText().toString();
                customer_email = mcustomer_email.getText().toString();
                customer_phone1 = mcustomer_phone1.getText().toString();
                customer_address1 = mcustomer_address1.getText().toString();
                customer_address2 = mcustomer_address2.getText().toString();
                customer_city = mcustomer_city.getText().toString();
                customer_country = mcustomer_country.getText().toString();
                customer_state = mcustomer_state.getText().toString();
                customer_zip = mcustomer_zip.getText().toString();
                comment = mcomment.getText().toString();

                doputorder();

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu2, menu);
        ImageView img;

        img = (ImageView) menu.findItem(R.id.image).getActionView();
        img.setImageResource(android.R.drawable.ic_menu_search);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_toolbar, new SearchFragment()).commit();
            }
        });

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {

            case R.id.image:

                getSupportFragmentManager().beginTransaction().
                        replace(R.id.main_toolbar, new SearchFragment()).addToBackStack(null).commit();

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doputorder() {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String group_type = preferences.getString("group_type", "");

        updated_by = preferences.getString("email", "");
//        final String Order = String.valueOf(10044);

//        ToDO: Intent ma tybata Order_number ysma pthauni
//        Yo bata ni tei page mai pathauni

        Intent i = getIntent();
        if (i != null) {
            odr = i.getExtras().getString("o_id_edit", "");
            Log.d("edit_order_no", odr);

        }

        OrderEditPut editPut = new OrderEditPut(customer_fname, customer_lname, customer_email, customer_phone1,
                customer_address1, customer_address2
                , customer_city, customer_country, customer_state, customer_zip, comment, updated_by);

        final ProgressDialog progressDialog = new ProgressDialog(OrderEditActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Loading...");
        progressDialog.show();

        Call<OrderEditResponse> call = apiInterface.putEditOrder(email, password, odr, editPut);

        call.enqueue(new Callback<OrderEditResponse>() {
            @Override
            public void onResponse(Call<OrderEditResponse> call, Response<OrderEditResponse> response) {

                if (response.isSuccessful()) {
                    OrderEditResponse resp1 = response.body();

                    String returnType = resp1.getReturnType();

                    switch (returnType) {

                        case "success":
                            Toast.makeText(getApplicationContext(), resp1.getMessage(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(OrderEditActivity.this, OrderSearch2Activity.class);
                            intent.putExtra("edit_id", odr);
                            startActivity(intent);
                            break;

                        case "error":
                            Toast.makeText(getApplicationContext(), resp1.getMessage() + "\n" + "Please Review Order and Try again", Toast.LENGTH_SHORT).show();
                            break;
                    }

                } else if (response.code() == 401) {

                    Toast.makeText(getApplicationContext(), " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }


                } else if (response.code() == 404) {

                    Toast.makeText(getApplicationContext(), "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                } else if (response.code() == 500) {

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                    Toast.makeText(getApplicationContext(), "Internal Server Error", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else {
                    Toast.makeText(OrderEditActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }
            }

            @Override
            public void onFailure(Call<OrderEditResponse> call, Throwable t) {
                call.cancel();
                Log.e("response-failure", t.toString());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(OrderEditActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }

        });


    }
}
