package com.example.colors2web.zummix_app.Adapter.BoxAdapters;

import android.app.Activity;
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

import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.MFragments.MasterBoxes_frag;
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
    private Activity mContext;
    private List<MasterBox> MasterList;
    private MasterBoxes_frag frag;
    private List<Boxes> BList = new ArrayList<>();

    public MBoxAdapter(Activity context, List<MasterBox> MList) {
        this.mContext = context;
        this.MasterList = MList;

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

        final String master_box_no1 = MasterList.get(0).getMasterBoxCode();
        loadAdapter(master_box_no1);

        MasterBox masterBox = MasterList.get(position);
        holder.barcode.setText(masterBox.getBarcodeNumber());
        final String master_box_no = masterBox.getMasterBoxCode();

        holder.master_box.setText(master_box_no);
        holder.master_box.setTextColor(holder.master_box.getResources().getColor(R.color.colorPrimary));
        holder.tracking_code.setText(masterBox.getMasterBoxTrackingCode());


        holder.master_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (BList.size() > 0) {


                    LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    final View popupView = inflater.inflate(R.layout.mbox_modal_masterbox, null);

                    WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                    Display display = wm.getDefaultDisplay();

                    int height = display.getHeight();
                    int width = display.getWidth();

                    final PopupWindow popup = new PopupWindow(popupView,
                            (int) (width * 0.9), WindowManager.LayoutParams.WRAP_CONTENT);
                    popup.setFocusable(true);
                    popup.setOutsideTouchable(true);
                    popup.setAnimationStyle(android.R.style.Animation_Dialog);

                    popup.showAtLocation(holder.master_box, Gravity.CENTER, 0, 0); //Displaying popup

                    final View container = (View) popup.getContentView().getParent();
                    WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                    p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                    p.dimAmount = 0.7f;
                    wm.updateViewLayout(container, p);

                    RecyclerView rv = popupView.findViewById(R.id.recycle_view);
                    TextView cancel = popupView.findViewById(R.id.pop_up_cancel);

                    final PopBoxAdapter kadapter;
                    kadapter = new PopBoxAdapter(mContext, BList);

                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                    rv.setLayoutManager(mLayoutManager);

                    rv.addItemDecoration(new MyDividerItemDecoration(mContext, LinearLayoutManager.HORIZONTAL, 16));
                    rv.setItemAnimator(new DefaultItemAnimator());

                    rv.setAdapter(kadapter);

                    kadapter.updateAnswers(BList, popup);

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popup.dismiss();
//                        kadapter.updateAnswer2();

                        }
                    });

                } else {
                    Toast.makeText(mContext, "NO data is present", Toast.LENGTH_SHORT).show();
                }
            }


        });

    }

    private void loadAdapter(String master_box_no) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String email = preferences.getString("email", "");
        String password = preferences.getString("password", "");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<MasterBoxResponse> call = apiInterface.getMboxBox(email, password, master_box_no);
        call.enqueue(new Callback<MasterBoxResponse>() {
            @Override
            public void onResponse(Call<MasterBoxResponse> call, Response<MasterBoxResponse> response) {
                if (response.isSuccessful()) {

                    MasterBoxResponse res = response.body();

                    if (res.getReturnType().equals("success")) {

                        List<Boxes> boxes = res.getmBoxes();

                        for (int i = 0; i < boxes.size(); i++) {
                            Boxes b = new Boxes();

                            String mpack = boxes.get(i).getBoxNumber();
                            String mord = boxes.get(i).getOrderNumber();

                            b.setBoxNumber(mpack);
                            b.setOrderNumber(mord);
                            BList.add(b);
                        }


                    } else {
                        Toast.makeText(mContext, res.getReturnType(), Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(mContext, "Operation Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MasterBoxResponse> call, Throwable t) {
                Toast.makeText(mContext, "Network Failure", Toast.LENGTH_SHORT).show();

                call.cancel();

            }
        });

    }

    @Override
    public int getItemCount() {
        return MasterList.size();
    }

    public void updateAnswers(ArrayList<MasterBox> boxes) {
        MasterList = boxes;
        notifyDataSetChanged();
    }

    public class MBoxHolder extends RecyclerView.ViewHolder {
        TextView barcode, master_box, tracking_code;

        public MBoxHolder(View itemView) {

            super(itemView);

            barcode = itemView.findViewById(R.id.box_box_bar_no);
            master_box = itemView.findViewById(R.id.box_mbox_no);
            tracking_code = itemView.findViewById(R.id.box_track_code);

        }
    }
}
