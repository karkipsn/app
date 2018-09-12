package com.example.colors2web.zummix_app.Activities.OrderActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.CityBins.CreateCityBinsActivity;
import com.example.colors2web.zummix_app.Activities.OrderSearch2Activity;
import com.example.colors2web.zummix_app.POJO.Order2POJO.OrderShippingAddressesDetail;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditPut;
import com.example.colors2web.zummix_app.POJO.OrderEdit.OrderEditResponse;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.Country;
import com.example.colors2web.zummix_app.POJO.SpecialPOJO.StatesPOJO;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.SearchFragment;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderShippingEditActivity extends AppCompatActivity {

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


    @BindView(R.id.customer_state)
    EditText mcustomer_state;

    @BindView(R.id.customer_zip)
    EditText mcustomer_zip;

    @BindView(R.id.comment)
    EditText mcomment;

    @BindView(R.id.btn_order_edit_submit)
    Button moedit;

    @BindView(R.id.country_spinner)
    Spinner countrySpinner;

    @BindView(R.id.us_state_spinner)
    Spinner us_Spinner;

    @BindView(R.id.mx_state_spinner)
    Spinner mx_Spinner;

    @BindView(R.id.ca_state_spinner)
    Spinner ca_Spinner;

    @BindView(R.id.citybins_spineheader)
    TextView sheader;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    String odr;
    String cus_id, s_state, state;
    String s_country = null;

    String updated_by, customer_fname, customer_lname, customer_email, customer_phone1,
            customer_address1, customer_address2, customer_city, customer_country,
            customer_state, customer_zip, comment;

    APIInterface apiInterface;

    @Override
    public void onBackPressed() {
//        moveTaskToBack(true);
        super.onBackPressed();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_edit);
        ButterKnife.bind(this);

        apiInterface = APIClient.getClient().create(APIInterface.class);

        //for toolbarsetup with back arrow
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Intent i = getIntent();
        if (i != null) {
            odr = i.getExtras().getString("o_id_edit", "");
//            Parcelable[] detail = i.getExtras().getParcelableArray("details");
            Log.d("edit_order_no", odr);
//            Log.d("detail", detail.toString());
        }




        loadCountrySpinner();
        countrySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Country sp = (Country) parent.getItemAtPosition(position);
                s_country = sp.getCode();
                Log.d("Country", s_country);

                if (s_country.equals("CA")) {

                    sheader.setVisibility(View.VISIBLE);
                    ca_Spinner.setVisibility(View.VISIBLE);
                    us_Spinner.setVisibility(View.GONE);
                    mx_Spinner.setVisibility(View.GONE);
                    mcustomer_state.setVisibility(View.GONE);


                } else if (s_country.equals("US")) {

                    sheader.setVisibility(View.VISIBLE);
                    ca_Spinner.setVisibility(View.GONE);
                    us_Spinner.setVisibility(View.VISIBLE);
                    mx_Spinner.setVisibility(View.GONE);
                    mcustomer_state.setVisibility(View.GONE);

                } else if (s_country.equals("MX")) {

                    sheader.setVisibility(View.VISIBLE);
                    mx_Spinner.setVisibility(View.VISIBLE);
                    us_Spinner.setVisibility(View.GONE);
                    ca_Spinner.setVisibility(View.GONE);
                    mcustomer_state.setVisibility(View.GONE);

                } else {

                    sheader.setVisibility(View.GONE);
                    mcustomer_state.setVisibility(View.VISIBLE);
                    mx_Spinner.setVisibility(View.GONE);
                    us_Spinner.setVisibility(View.GONE);
                    ca_Spinner.setVisibility(View.GONE);
                }

                load_ca_stateSPinner();
                load_us_stateSPinner();
                load_mx_stateSPinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Country sp = (Country) parent.getItemAtPosition(0);
                s_country = sp.getCode();

            }
        });

//      if(s_country != null && !s_country.isEmpty()){

        us_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StatesPOJO sp = (StatesPOJO) parent.getItemAtPosition(position);
                s_state = sp.getCode();
                state = s_state;
                Log.d("State", s_state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ca_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StatesPOJO sp = (StatesPOJO) parent.getItemAtPosition(position);
                s_state = sp.getCode();
                state = s_state;
                Log.d("State", s_state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {


            }
        });

        mx_Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                StatesPOJO sp = (StatesPOJO) parent.getItemAtPosition(position);
                s_state = sp.getCode();
                state = s_state;
                Log.d("State", s_state);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        moedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                customer_fname = mcustomer_fname.getText().toString();
                customer_lname = mcustomer_lname.getText().toString();
                customer_email = mcustomer_email.getText().toString();
                customer_phone1 = mcustomer_phone1.getText().toString();
                customer_address1 = mcustomer_address1.getText().toString();
                customer_address2 = mcustomer_address2.getText().toString();
                customer_country = s_country;
                if (s_country.equals("US") || s_country.equals("MX") || s_country.equals("CA")) {
                    customer_state = s_state;
                } else {
                    customer_state = mcustomer_state.getText().toString();
                }
                customer_city = mcustomer_city.getText().toString();
                customer_zip = mcustomer_zip.getText().toString();
                comment = mcomment.getText().toString();

                final ProgressDialog progressDialog = new ProgressDialog(OrderShippingEditActivity.this,
                        R.style.AppTheme_Dark_Dialog);
                progressDialog.setIndeterminate(true);
                progressDialog.setCancelable(false);
                progressDialog.setMessage("Loading...");
                progressDialog.show();


                doputorder(progressDialog);

            }
        });
    }

    private void load_mx_stateSPinner() {

        ArrayList<StatesPOJO> stateList = new ArrayList<>();
        stateList.add(new StatesPOJO("Aguascalientes", "AG"));

        stateList.add(new StatesPOJO("Baja California", "BC"));
        stateList.add(new StatesPOJO("Baja California Sur", "BS"));
        stateList.add(new StatesPOJO("Chihuahua", "CH"));
        stateList.add(new StatesPOJO("Colima", "CL"));
        stateList.add(new StatesPOJO("Campeche", "CM"));
        stateList.add(new StatesPOJO("Coahuila", "CO"));
        stateList.add(new StatesPOJO("Chiapas", "CS"));
        stateList.add(new StatesPOJO("Federal District", "DF"));
        stateList.add(new StatesPOJO("Durango", "DG"));
        stateList.add(new StatesPOJO("Guerrero", "GR"));
        stateList.add(new StatesPOJO("Guanajuato", "GT"));
        stateList.add(new StatesPOJO("Hidalgo", "HG"));
        stateList.add(new StatesPOJO("México State", "JA"));
        stateList.add(new StatesPOJO("Michoacán", "MI"));
        stateList.add(new StatesPOJO("Morelos", "MO"));
        stateList.add(new StatesPOJO("Nayarit", "NA"));
        stateList.add(new StatesPOJO("Nuevo León", "NL"));
        stateList.add(new StatesPOJO("Oaxaca", "OA"));
        stateList.add(new StatesPOJO("Puebla", "PB"));
        stateList.add(new StatesPOJO("Querétaro", "QE"));
        stateList.add(new StatesPOJO("Quintana Roo", "QR"));
        stateList.add(new StatesPOJO("Sinaloa", "SI"));
        stateList.add(new StatesPOJO("San Luis Potosí", "SL"));
        stateList.add(new StatesPOJO("Sonora", "SO"));
        stateList.add(new StatesPOJO("Tabasco", "TB"));
        stateList.add(new StatesPOJO("Tlaxcala", "TL"));
        stateList.add(new StatesPOJO("Tamaulipas", "TM"));
        stateList.add(new StatesPOJO("Veracruz", "VE"));
        stateList.add(new StatesPOJO("Yucatán", "YU"));
        stateList.add(new StatesPOJO("Zacatecas", "ZA"));

        ArrayAdapter<StatesPOJO> madapter = new ArrayAdapter<StatesPOJO>(OrderShippingEditActivity.this,
                android.R.layout.simple_spinner_item, stateList);
        madapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        mx_Spinner.setAdapter(madapter);

    }

    private void load_us_stateSPinner() {

        ArrayList<StatesPOJO> stateList = new ArrayList<>();

        stateList.add(new StatesPOJO("Alabama", "AL"));
        stateList.add(new StatesPOJO("Alaska", "AK"));
        stateList.add(new StatesPOJO("Arizona", "AZ"));
        stateList.add(new StatesPOJO("Arkansas", "AR"));
        stateList.add(new StatesPOJO("California", "CA"));
        stateList.add(new StatesPOJO("Colorado", "CO"));
        stateList.add(new StatesPOJO("Connecticut", "CT"));
        stateList.add(new StatesPOJO("Delaware", "DE"));
        stateList.add(new StatesPOJO("District Of Columbia", "DC"));
        stateList.add(new StatesPOJO("Florida", "FL"));
        stateList.add(new StatesPOJO("Georgia", "GA"));
        stateList.add(new StatesPOJO("Hawaii", "HI"));
        stateList.add(new StatesPOJO("Idaho", "ID"));
        stateList.add(new StatesPOJO("Illinois", "IL"));
        stateList.add(new StatesPOJO("Indiana", "IN"));
        stateList.add(new StatesPOJO("Iowa", "IA"));
        stateList.add(new StatesPOJO("Kansas", "KS"));
        stateList.add(new StatesPOJO("Kentucky", "KY"));
        stateList.add(new StatesPOJO("Louisiana", "LA"));
        stateList.add(new StatesPOJO("Maine", "ME"));
        stateList.add(new StatesPOJO("Maryland", "MD"));
        stateList.add(new StatesPOJO("Massachusetts", "MA"));
        stateList.add(new StatesPOJO("Michigan", "MI"));
        stateList.add(new StatesPOJO("Minnesota", "MN"));
        stateList.add(new StatesPOJO("Mississippi", "MS"));
        stateList.add(new StatesPOJO("Missouri", "MO"));
        stateList.add(new StatesPOJO("Montana", "MT"));
        stateList.add(new StatesPOJO("Nebraska", "NE"));
        stateList.add(new StatesPOJO("Nevada", "NV"));
        stateList.add(new StatesPOJO("New Hampshire", "NH"));
        stateList.add(new StatesPOJO("New Jersey", "NJ"));
        stateList.add(new StatesPOJO("New Mexico", "NM"));
        stateList.add(new StatesPOJO("New York", "NY"));
        stateList.add(new StatesPOJO("North Carolina", "NC"));
        stateList.add(new StatesPOJO("North Dakota", "ND"));
        stateList.add(new StatesPOJO("Ohio", "OH"));
        stateList.add(new StatesPOJO("Oklahoma", "OK"));
        stateList.add(new StatesPOJO("Oregon", "OR"));
        stateList.add(new StatesPOJO("Pennsylvania", "PA"));
        stateList.add(new StatesPOJO("Puerto Rico", "PR"));
        stateList.add(new StatesPOJO("Rhode Island", "RI"));
        stateList.add(new StatesPOJO("South Carolina", "SC"));
        stateList.add(new StatesPOJO("South Dakota", "SD"));
        stateList.add(new StatesPOJO("Tennessee", "TN"));
        stateList.add(new StatesPOJO("Texas", "TX"));
        stateList.add(new StatesPOJO("Utah", "UT"));
        stateList.add(new StatesPOJO("Vermont", "VT"));
        stateList.add(new StatesPOJO("Virginia", "VA"));
        stateList.add(new StatesPOJO("Washington", "WA"));
        stateList.add(new StatesPOJO("West Virginia", "WV"));
        stateList.add(new StatesPOJO("Wisconsin", "WI"));
        stateList.add(new StatesPOJO("Wyoming", "WY"));

        ArrayAdapter<StatesPOJO> uadapter = new ArrayAdapter<StatesPOJO>(OrderShippingEditActivity.this,
                android.R.layout.simple_spinner_item, stateList);
        uadapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        us_Spinner.setAdapter(uadapter);

    }

    private void load_ca_stateSPinner() {

        ArrayList<StatesPOJO> stateList = new ArrayList<>();
        stateList.add(new StatesPOJO("Alberta", "AB"));
        stateList.add(new StatesPOJO("British Columbia", "BC"));
        stateList.add(new StatesPOJO("Manitoba", "MB"));
        stateList.add(new StatesPOJO("New Brunswick", "NB"));
        stateList.add(new StatesPOJO("Newfoundland", "NL"));
        stateList.add(new StatesPOJO("Nova Scotia", "NS"));
        stateList.add(new StatesPOJO("Ontario", "ON"));
        stateList.add(new StatesPOJO("Prince Edward Island", "PE"));
        stateList.add(new StatesPOJO("Quebec", "QC"));
        stateList.add(new StatesPOJO("Saskatchewan", "SK"));
        stateList.add(new StatesPOJO("Northwest Territories", "NT"));
        stateList.add(new StatesPOJO("Nunavut", "NU"));
        stateList.add(new StatesPOJO("Yukon", "YT"));

        ArrayAdapter<StatesPOJO> cadapter = new ArrayAdapter<StatesPOJO>(OrderShippingEditActivity.this,
                android.R.layout.simple_spinner_item, stateList);
        cadapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        ca_Spinner.setAdapter(cadapter);

    }

    private void loadCountrySpinner() {

        ArrayList<Country> countryList = new ArrayList<>();

        countryList.add(new Country("Afghanistan", "AF"));
        countryList.add(new Country("Åland Islands", "AX"));
        countryList.add(new Country("Albania", "AL"));
        countryList.add(new Country("Algeria", "DZ"));
        countryList.add(new Country("American Samoa", "AS"));
        countryList.add(new Country("Andorra", "AD"));
        countryList.add(new Country("Angola", "AO"));
        countryList.add(new Country("Anguilla", "AI"));
        countryList.add(new Country("Antarctica", "AQ"));
        countryList.add(new Country("Antigua and Barbuda", "AG"));
        countryList.add(new Country("Argentina", "AR"));
        countryList.add(new Country("Armenia", "AM"));
        countryList.add(new Country("Aruba", "AW"));
        countryList.add(new Country("Australia", "AU"));
        countryList.add(new Country("Austria", "AT"));
        countryList.add(new Country("Azerbaijan", "AZ"));
        countryList.add(new Country("Bahamas", "BS"));
        countryList.add(new Country("Bahrain", "BH"));
        countryList.add(new Country("Bangladesh", "BD"));
        countryList.add(new Country("Barbados", "BB"));
        countryList.add(new Country("Belarus", "BY"));
        countryList.add(new Country("Belgium", "BE"));
        countryList.add(new Country("Belize", "BZ"));
        countryList.add(new Country("Benin", "BJ"));
        countryList.add(new Country("Bermuda", "BM"));
        countryList.add(new Country("Bhutan", "BT"));
        countryList.add(new Country("Bolivia, Plurinational State of", "BO"));
        countryList.add(new Country("Bonaire, Sint Eustatius and Saba", "BQ"));
        countryList.add(new Country("Bosnia and Herzegovina", "BA"));
        countryList.add(new Country("Botswana", "BW"));
        countryList.add(new Country("Bouvet Island", "BV"));
        countryList.add(new Country("Brazil", "BR"));
        countryList.add(new Country("British Indian Ocean Territory", "IO"));
        countryList.add(new Country("Brunei Darussalam", "BN"));
        countryList.add(new Country("Bulgaria", "BG"));
        countryList.add(new Country("Burkina Faso", "BF"));
        countryList.add(new Country("Burundi", "BI"));
        countryList.add(new Country("Cambodia", "KH"));
        countryList.add(new Country("Cameroon", "CM"));
        countryList.add(new Country("Canada", "CA"));
        countryList.add(new Country("Cape Verde", "CV"));
        countryList.add(new Country("Cayman Islands", "KY"));
        countryList.add(new Country("Central African Republic", "CF"));
        countryList.add(new Country("Chad", "TD"));
        countryList.add(new Country("Chile", "CL"));
        countryList.add(new Country("China", "CN"));
        countryList.add(new Country("Christmas Island", "CX"));
        countryList.add(new Country("Cocos (Keeling) Islands", "CC"));
        countryList.add(new Country("Colombia", "CO"));
        countryList.add(new Country("Comoros", "KM"));
        countryList.add(new Country("Congo", "CG"));
        countryList.add(new Country("Congo, The Democratic Republic of the", "CD"));
        countryList.add(new Country("Cook Islands", "CK"));
        countryList.add(new Country("Costa Rica", "CR"));
        countryList.add(new Country("Cote D\'Ivoire", "CI"));
        countryList.add(new Country("Croatia", "HR"));
        countryList.add(new Country("Cuba", "CU"));
        countryList.add(new Country("Curaçao", "CW"));
        countryList.add(new Country("Cyprus", "CY"));
        countryList.add(new Country("Czech Republic", "CZ"));
        countryList.add(new Country("Denmark", "DK"));
        countryList.add(new Country("Djibouti", "DJ"));
        countryList.add(new Country("Dominica", "DM"));
        countryList.add(new Country("Dominican Republic", "DO"));
        countryList.add(new Country("Ecuador", "EC"));
        countryList.add(new Country("Egypt", "EG"));
        countryList.add(new Country("El Salvador", "SV"));
        countryList.add(new Country("Equatorial Guinea", "GQ"));
        countryList.add(new Country("Eritrea", "ER"));
        countryList.add(new Country("Estonia", "EE"));
        countryList.add(new Country("Ethiopia", "ET"));
        countryList.add(new Country("Falkland Islands (Malvinas)", "FK"));
        countryList.add(new Country("Faroe Islands", "FO"));
        countryList.add(new Country("Fiji", "FJ"));
        countryList.add(new Country("Finland", "FI"));
        countryList.add(new Country("France", "FR"));
        countryList.add(new Country("French Guiana", "GF"));
        countryList.add(new Country("French Polynesia", "PF"));
        countryList.add(new Country("French Southern Territories", "TF"));
        countryList.add(new Country("Gabon", "GA"));
        countryList.add(new Country("Gambia", "GM"));
        countryList.add(new Country("Georgia", "GE"));
        countryList.add(new Country("Germany", "DE"));
        countryList.add(new Country("Ghana", "GH"));
        countryList.add(new Country("Gibraltar", "GI"));
        countryList.add(new Country("Greece", "GR"));
        countryList.add(new Country("Greenland", "GL"));
        countryList.add(new Country("Grenada", "GD"));
        countryList.add(new Country("Guadeloupe", "GP"));
        countryList.add(new Country("Guam", "GU"));
        countryList.add(new Country("Guatemala", "GT"));
        countryList.add(new Country("Guernsey", "GG"));
        countryList.add(new Country("Guinea", "GN"));
        countryList.add(new Country("Guinea-Bissau", "GW"));
        countryList.add(new Country("Guyana", "GY"));
        countryList.add(new Country("Haiti", "HT"));
        countryList.add(new Country("Heard Island and Mcdonald Islands", "HM"));
        countryList.add(new Country("Holy See (Vatican City State)", "VA"));
        countryList.add(new Country("Honduras", "HN"));
        countryList.add(new Country("Hong Kong", "HK"));
        countryList.add(new Country("Hungary", "HU"));
        countryList.add(new Country("Iceland", "IS"));
        countryList.add(new Country("India", "IN"));
        countryList.add(new Country("Indonesia", "ID"));
        countryList.add(new Country("Iran, Islamic Republic Of", "IR"));
        countryList.add(new Country("Iraq", "IQ"));
        countryList.add(new Country("Ireland", "IE"));
        countryList.add(new Country("Isle of Man", "IM"));
        countryList.add(new Country("Israel", "IL"));
        countryList.add(new Country("Italy", "IT"));
        countryList.add(new Country("Jamaica", "JM"));
        countryList.add(new Country("Japan", "JP"));
        countryList.add(new Country("Jersey", "JE"));
        countryList.add(new Country("Jordan", "JO"));
        countryList.add(new Country("Kazakhstan", "KZ"));
        countryList.add(new Country("Kenya", "KE"));
        countryList.add(new Country("Kiribati", "KI"));
        countryList.add(new Country("Korea, Democratic People\'s Republic of", "KP"));
        countryList.add(new Country("Korea, Republic of", "KR"));
        countryList.add(new Country("Kuwait", "KW"));
        countryList.add(new Country("Kyrgyzstan", "KG"));
        countryList.add(new Country("Lao People\'s Democratic Republic", "LA"));
        countryList.add(new Country("Latvia", "LV"));
        countryList.add(new Country("Lebanon", "LB"));
        countryList.add(new Country("Lesotho", "LS"));
        countryList.add(new Country("Liberia", "LR"));
        countryList.add(new Country("Libya", "LY"));
        countryList.add(new Country("Liechtenstein", "LI"));
        countryList.add(new Country("Lithuania", "LT"));
        countryList.add(new Country("Luxembourg", "LU"));
        countryList.add(new Country("Macao", "MO"));
        countryList.add(new Country("Macedonia, The Former Yugoslav Republic of", "MK"));
        countryList.add(new Country("Madagascar", "MG"));
        countryList.add(new Country("Malawi", "MW"));
        countryList.add(new Country("Malaysia", "MY"));
        countryList.add(new Country("Maldives", "MV"));
        countryList.add(new Country("Mali", "ML"));
        countryList.add(new Country("Malta", "MT"));
        countryList.add(new Country("Marshall Islands", "MH"));
        countryList.add(new Country("Martinique", "MQ"));
        countryList.add(new Country("Mauritania", "MR"));
        countryList.add(new Country("Mauritius", "MU"));
        countryList.add(new Country("Mayotte", "YT"));
        countryList.add(new Country("Mexico", "MX"));
        countryList.add(new Country("Micronesia, Federated States of", "FM"));
        countryList.add(new Country("Moldova, Republic of", "MD"));
        countryList.add(new Country("Monaco", "MC"));
        countryList.add(new Country("Mongolia", "MN"));
        countryList.add(new Country("Montenegro", "ME"));
        countryList.add(new Country("Montserrat", "MS"));
        countryList.add(new Country("Morocco", "MA"));
        countryList.add(new Country("Mozambique", "MZ"));
        countryList.add(new Country("Myanmar", "MM"));
        countryList.add(new Country("Namibia", "NA"));
        countryList.add(new Country("Nauru", "NR"));
        countryList.add(new Country("Nepal", "NP"));
        countryList.add(new Country("Netherlands", "NL"));
        countryList.add(new Country("New Caledonia", "NC"));
        countryList.add(new Country("New Zealand", "NZ"));
        countryList.add(new Country("Nicaragua", "NI"));
        countryList.add(new Country("Niger", "NE"));
        countryList.add(new Country("Nigeria", "NG"));
        countryList.add(new Country("Niue", "NU"));
        countryList.add(new Country("Norfolk Island", "NF"));
        countryList.add(new Country("Northern Mariana Islands", "MP"));
        countryList.add(new Country("Norway", "NO"));
        countryList.add(new Country("Oman", "OM"));
        countryList.add(new Country("Pakistan", "PK"));
        countryList.add(new Country("Palau", "PW"));
        countryList.add(new Country("Palestinian Territory, Occupied", "PS"));
        countryList.add(new Country("Panama", "PA"));
        countryList.add(new Country("Papua New Guinea", "PG"));
        countryList.add(new Country("Paraguay", "PY"));
        countryList.add(new Country("Peru", "PE"));
        countryList.add(new Country("Philippines", "PH"));
        countryList.add(new Country("Pitcairn", "PN"));
        countryList.add(new Country("Poland", "PL"));
        countryList.add(new Country("Portugal", "PT"));
        countryList.add(new Country("Puerto Rico", "PR"));
        countryList.add(new Country("Qatar", "QA"));
        countryList.add(new Country("Reunion", "RE"));
        countryList.add(new Country("Romania", "RO"));
        countryList.add(new Country("Russian Federation", "RU"));
        countryList.add(new Country("Rwanda", "RW"));
        countryList.add(new Country("Saint Barthélemy", "BL"));
        countryList.add(new Country("Saint Helena, Ascension and Tristan da Cunha", "SH"));
        countryList.add(new Country("Saint Kitts and Nevis", "KN"));
        countryList.add(new Country("Saint Lucia", "LC"));
        countryList.add(new Country("Saint Martin (French part)", "MF"));
        countryList.add(new Country("Saint Pierre and Miquelon", "PM"));
        countryList.add(new Country("Saint Vincent and the Grenadines", "VC"));
        countryList.add(new Country("Samoa", "WS"));
        countryList.add(new Country("San Marino", "SM"));
        countryList.add(new Country("Sao Tome and Principe", "ST"));
        countryList.add(new Country("Saudi Arabia", "SA"));
        countryList.add(new Country("Senegal", "SN"));
        countryList.add(new Country("Serbia", "RS"));
        countryList.add(new Country("Seychelles", "SC"));
        countryList.add(new Country("Sierra Leone", "SL"));
        countryList.add(new Country("Singapore", "SG"));
        countryList.add(new Country("Sint Maarten (Dutch part)", "SX"));
        countryList.add(new Country("Slovakia", "SK"));
        countryList.add(new Country("Slovenia", "SI"));
        countryList.add(new Country("Solomon Islands", "SB"));
        countryList.add(new Country("Somalia", "SO"));
        countryList.add(new Country("South Africa", "ZA"));
        countryList.add(new Country("South Georgia and the South Sandwich Islands", "GS"));
        countryList.add(new Country("South Sudan", "SS"));
        countryList.add(new Country("Spain", "ES"));
        countryList.add(new Country("Sri Lanka", "LK"));
        countryList.add(new Country("Sudan", "SD"));
        countryList.add(new Country("Suriname", "SR"));
        countryList.add(new Country("Svalbard and Jan Mayen", "SJ"));
        countryList.add(new Country("Swaziland", "SZ"));
        countryList.add(new Country("Sweden", "SE"));
        countryList.add(new Country("Switzerland", "CH"));
        countryList.add(new Country("Syrian Arab Republic", "SY"));
        countryList.add(new Country("Taiwan, Province of China", "TW"));
        countryList.add(new Country("Tajikistan", "TJ"));
        countryList.add(new Country("Tanzania, United Republic of", "TZ"));
        countryList.add(new Country("Thailand", "TH"));
        countryList.add(new Country("Timor-Leste", "TL"));
        countryList.add(new Country("Togo", "TG"));
        countryList.add(new Country("Tokelau", "TK"));
        countryList.add(new Country("Tonga", "TO"));
        countryList.add(new Country("Trinidad and Tobago", "TT"));
        countryList.add(new Country("Tunisia", "TN"));
        countryList.add(new Country("Turkey", "TR"));
        countryList.add(new Country("Turkmenistan", "TM"));
        countryList.add(new Country("Turks and Caicos Islands", "TC"));
        countryList.add(new Country("Tuvalu", "TV"));
        countryList.add(new Country("Uganda", "UG"));
        countryList.add(new Country("Ukraine", "UA"));
        countryList.add(new Country("United Arab Emirates", "AE"));
        countryList.add(new Country("United Kingdom", "GB"));
        countryList.add(new Country("United States", "US"));
        countryList.add(new Country("United States Minor Outlying Islands", "UM"));
        countryList.add(new Country("Uruguay", "UY"));
        countryList.add(new Country("Uzbekistan", "UZ"));
        countryList.add(new Country("Vanuatu", "VU"));
        countryList.add(new Country("Venezuela", "VE"));
        countryList.add(new Country("Vietnam", "VN"));
        countryList.add(new Country("Virgin Islands, British", "VG"));
        countryList.add(new Country("Virgin Islands, U.S.", "VI"));
        countryList.add(new Country("Wallis and Futuna", "WF"));
        countryList.add(new Country("Western Sahara", "EH"));
        countryList.add(new Country("Yemen", "YE"));
        countryList.add(new Country("Zambia", "ZM"));
        countryList.add(new Country("Zimbabwe", "ZW"));

        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(OrderShippingEditActivity.this,
                android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        countrySpinner.setAdapter(adapter);

        Log.d("spinner_country", countryList.toString());

    }

    private final static String TAG_FRAGMENT = "SEARCH_FRAGMENT";


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
                        replace(R.id.frame_toolbar, new SearchFragment()).
                        addToBackStack(TAG_FRAGMENT).commit();
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
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        return super.onPrepareOptionsMenu(menu);
    }


    private void doputorder(final ProgressDialog progressDialog) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        final String email = preferences.getString("email", "");
        final String password = preferences.getString("password", "");
        final String group_type = preferences.getString("group_type", "");

        updated_by = preferences.getString("email", "");
//        final String Order = String.valueOf(10044);

//        ToDO: Intent ma tybata Order_number ysma pthauni
//        Yo bata ni tei page mai pathauni



        OrderEditPut editPut = new OrderEditPut(customer_fname, customer_lname, customer_email, customer_phone1,
                customer_address1, customer_address2
                , customer_city, customer_country, customer_state, customer_zip, comment, updated_by);

//        final ProgressDialog progressDialog = new ProgressDialog(OrderShippingEditActivity.this,
//                R.style.AppTheme_Dark_Dialog);
//        progressDialog.setIndeterminate(true);
//        progressDialog.setCancelable(false);
//        progressDialog.setMessage("Loading...");
//        progressDialog.show();

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
                            Intent intent = new Intent(OrderShippingEditActivity.this, OrderSearch2Activity.class);
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
                    Toast.makeText(OrderShippingEditActivity.this, "Operation Failed", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(OrderShippingEditActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }

        });


    }
}
