package com.example.colors2web.zummix_app.Activities.MasterBoxSearch;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.Boxes;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBox;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MBoxAdapter extends RecyclerView.Adapter<MBoxAdapter.MBoxHolder> {
    Context mContext;
    List<MasterBox> MList;
    List<Boxes>BList = new ArrayList<>();

    public MBoxAdapter(Context context,List<MasterBox> MList) {
        this.mContext =context;
        this.MList = MList;
    }

    @NonNull
    @Override
    public MBoxAdapter.MBoxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.box_masterbox_adapter, parent, false);
        return new MBoxHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final MBoxAdapter.MBoxHolder holder, int position) {
        MasterBox masterBox = MList.get(position);

        holder.bid.setText(masterBox.getOrderId());
        holder.mbarno.setText(masterBox.getBarcodeNumber());
        holder.mboxno.setText(masterBox.getBoxNumber());
        final String master_box_no =masterBox.getMasterBoxCode();
        holder.mmboxno.setText(master_box_no);
        holder.mtrcode.setText(masterBox.getMasterBoxTrackingCode());
        holder.mcusname.setText(masterBox.getCompanyName());
        holder.memail.setText(masterBox.getCompanyEmail());

        holder.mmboxno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.mbox_modal_masterbox, null);

                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width*0.8), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(holder.mmboxno, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                RecyclerView rv = popupView.findViewById(R.id.recycle_view);
                Button cancel = popupView.findViewById(R.id.pop_up_cancel);


                final PopBoxAdapter kadapter;
                kadapter = new PopBoxAdapter(mContext,BList);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
//            mrecyclerView.setHasFixedSize(true);
                rv.setLayoutManager(mLayoutManager);

                rv.addItemDecoration(new MyDividerItemDecoration(mContext, LinearLayoutManager.HORIZONTAL, 16));
//            mrecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
                rv.setItemAnimator(new DefaultItemAnimator());

                rv.setAdapter(kadapter);
                loadAdapter(master_box_no,kadapter);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                        kadapter.updateAnswer2();

                    }
                });
            }
        });

    }

    private void loadAdapter(String master_box_no, final PopBoxAdapter kadapter) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String email = preferences.getString("email","");
        String password = preferences.getString("password","");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<MasterBoxResponse> call  =apiInterface.getMboxBox(email,password,master_box_no);
        call.enqueue(new Callback<MasterBoxResponse>() {
            @Override
            public void onResponse(Call<MasterBoxResponse> call, Response<MasterBoxResponse> response) {
                if(response.isSuccessful()){

                    MasterBoxResponse res = response.body();

                    if(res.getReturnType().equals("success")){

                   List <Boxes> boxes = res.getmBoxes();
//                   List<Boxes>arraylist = new ArrayList<>();
                   Boxes b = new Boxes();

                   for(int i = 0;i<boxes.size();i++){

                       String mpack = boxes.get(i).getBoxNumber();
                       String mord = boxes.get(i).getOrderNumber();

                       b.setBoxNumber(mpack);
                       b.setOrderNumber(mord);
                       BList.add(b);
                   }

                   kadapter.updateAnswers(BList);
                }
                else{
                        Toast.makeText(mContext,res.getReturnType(),Toast.LENGTH_SHORT).show();

                    }}
                else {
                    Toast.makeText(mContext,"Operation Failed",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MasterBoxResponse> call, Throwable t) {
                Toast.makeText(mContext,"Network Failure",Toast.LENGTH_SHORT).show();

                call.cancel();

            }
        });

    }

    @Override
    public int getItemCount() {
        return MList.size();
    }

    public void updateAnswers(ArrayList<MasterBox> boxes) {
        MList=boxes;
        notifyDataSetChanged();
    }

    public class MBoxHolder extends RecyclerView.ViewHolder {
        TextView bid, mbarno, mboxno, mmboxno, mtrcode, mcusname, memail;

        public MBoxHolder(View itemView) {

            super(itemView);
            bid = itemView.findViewById(R.id.box_box_oid);
            mbarno = itemView.findViewById(R.id.box_box_bar_no);
            mboxno = itemView.findViewById(R.id.box_box_no);
            mmboxno = itemView.findViewById(R.id.box_mbox_no);
            mtrcode = itemView.findViewById(R.id.box_track_code);
            mcusname = itemView.findViewById(R.id.box_cus_name);
            memail = itemView.findViewById(R.id.box_email);
        }
    }
}
