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

    Activity mActivity;
    private TextView mweight;
    private Spinner spinner,
            spinnerCountry;

    private String uom, country;

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

        final String cost = problemSKUs.getPrice();
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
                        (int) (width * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
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




                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        uom = parent.getItemAtPosition(position).toString();


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

//        Resources res = mActivity.getResources();
//        Country country = new Country();


//        String my_countries[] = res.getStringArray(R.array.Countries_full_array);
//        String my_code[] = res.getStringArray(R.array.Countries_code_array);
//
//        String cont;
//        String code ;
//        String[] arr1 =new String[my_countries.length];
//        String[] arr2 =new String[my_code.length];
//
//        for (int i = 0; i < my_countries.length; i++) {
//
//            cont = my_countries[i];
//            country.setName(cont);
//            arr1[i] = cont;
//
//            code = my_code[i];
//            country.setCode(code);
//            arr2[i] = code;
//
//        }
//
//        for (int j=0; j<arr1.length;j++){
//
//            country.setName(arr1[j]);
//            country.setCode(arr2[j]);
//
//        }

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

        ArrayAdapter<Country> adapter = new ArrayAdapter<Country>(mActivity,
                android.R.layout.simple_spinner_item, countryList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinnerCountry.setAdapter(adapter);

        Log.d("spinner_country", countryList.toString());

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

                        Log.d("uom", uomlist.toString());
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