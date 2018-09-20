package com.example.colors2web.zummix_app.Adapter.Admin_Tools_Adapters;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.Activities.Navigation.Nav_fragments.SpecialPrograms.Special_display_Frag;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SpecialProgram;
import com.example.colors2web.zummix_app.POJO.SpecialProgram.SpecialProgramResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.security.AccessController.getContext;

public class Special_ProgramAdapter extends RecyclerView.Adapter<Special_ProgramAdapter.SpecialHolder> {

    List<SpecialProgram> SpList;
    Activity mContext;
    Special_display_Frag frag1;


    public Special_ProgramAdapter(List<SpecialProgram> spList, Activity mContext,Special_display_Frag frag) {

        SpList = spList;
        this.mContext = mContext;
        this.frag1 = frag;
    }

    @NonNull
    @Override
    public Special_ProgramAdapter.SpecialHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.activity_special_program_adapter, parent, false);
        return new SpecialHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final Special_ProgramAdapter.SpecialHolder holder, int position) {

        SpecialProgram program = SpList.get(position);

        holder.program.setText(program.getSpecialProgramNumber());
        holder.name.setText(program.getProgramName());
        holder.desc.setText(program.getProgramDescription());
        final String special_id = String.valueOf(program.getId());
        final String programnumber = program.getSpecialProgramNumber();
        final int index = SpList.indexOf(program);

       holder.edit.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
//               ALertDailog implement Paxi

               LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               final View popupView = inflater.inflate(R.layout.modal_update_special_program, null);

               WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
               Display display = wm.getDefaultDisplay();

               int height = display.getHeight();
               int width = display.getWidth();

               final PopupWindow popup = new PopupWindow(popupView,
                       (int) (width*0.9), WindowManager.LayoutParams.WRAP_CONTENT);
               popup.setFocusable(true);
               popup.setOutsideTouchable(true);
               popup.setAnimationStyle(android.R.style.Animation_Dialog);

               popup.showAtLocation(holder.edit, Gravity.CENTER, 0, 0); //Displaying popup

               final View container = (View) popup.getContentView().getParent();
               WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
               p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
               p.dimAmount = 0.7f;
               wm.updateViewLayout(container, p);

               Button submit,cancle;
               final EditText pname,pdetails;
               TextView pnum;

               pnum = popupView.findViewById(R.id.sp_spnumber);
               pnum.setText(programnumber);

               pname = popupView.findViewById(R.id.sp_spname);
               pdetails = popupView.findViewById(R.id.sp_spdetails);
               
               submit = popupView.findViewById(R.id.sp_sp_btnsubmit);
               cancle = popupView.findViewById(R.id.sp_sp_btncancel);

               cancle.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {
                       popup.dismiss();
                   }
               });

               submit.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View v) {

                       String des = pdetails.getText().toString();
                       String name = pname.getText().toString();

                       SpecialProgram prog = new SpecialProgram(des,name);

                       final ProgressDialog progressDialog = new ProgressDialog(mContext,
                               R.style.AppTheme_Dark_Dialog);
                       progressDialog.setIndeterminate(true);
                       progressDialog.setCancelable(false);
                       progressDialog.setMessage("Loading...");
                       progressDialog.show();

                       loadApi(prog,special_id,progressDialog,popup,index);
                   }
               });
           }
       });

//        holder.delete.setText();

    }

    private void loadApi(SpecialProgram prog, String special_id, final ProgressDialog progressDialog, final PopupWindow popup, final int index) {


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");
        
        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
        Call<SpecialProgramResponse>call = apiInterface.updateSpecialPrograms(email,password,special_id,prog);
        call.enqueue(new Callback<SpecialProgramResponse>() {
            @Override
            public void onResponse(Call<SpecialProgramResponse> call, Response<SpecialProgramResponse> response) {

                if (response.isSuccessful()) {

                    SpecialProgramResponse resp1 = response.body();

                    if (resp1.getReturnType().equals("success")) {

                        Toast.makeText(mContext, resp1.getMessage(), Toast.LENGTH_LONG).show();
                         popup.dismiss();
                        SpList.clear();
                         frag1.refresh();


                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }


                    } else {
                        Toast.makeText(mContext, resp1.getMessage(), Toast.LENGTH_LONG).show();

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }


                } else if (response.code() == 401) {


                    Toast.makeText(mContext, " Authentication Error:" + "\n" + "Account Not Found", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                } else if (response.code() == 404) {


                    Toast.makeText(mContext, "InValid Web Address", Toast.LENGTH_SHORT).show();
                    Log.d("Error", response.errorBody().toString());
                } else if (response.code() == 500) {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "Server Broken", Toast.LENGTH_LONG).show();
                    Log.d("Error", response.errorBody().toString());


                } else {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SpecialProgramResponse> call, Throwable t) {
                call.cancel();

                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Log.e("response-failure", t.toString());
                Toast.makeText(mContext, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public int getItemCount() {
        return SpList.size();
    }

    @Override
    public void onViewRecycled(@NonNull SpecialHolder holder) {
        super.onViewRecycled(holder);
    }

    public void updateAnswers(List<SpecialProgram> prgList) {
        SpList = prgList;
        notifyDataSetChanged();
    }

    public void cleardata() {
        SpList.clear();
        notifyDataSetChanged();
    }

    public class SpecialHolder extends RecyclerView.ViewHolder {
        Button edit, delete;
        TextView program, name, desc;

        public SpecialHolder(View itemView) {
            super(itemView);

            program = itemView.findViewById(R.id.special_adapter_program);
            name = itemView.findViewById(R.id.special_adapter_s_programname);
            desc = itemView.findViewById(R.id.special_adapter_s_program_desc);
            edit = itemView.findViewById(R.id.special_adapter_btnup);
            delete = itemView.findViewById(R.id.special_adapter_del);
        }
    }
}
