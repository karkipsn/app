package com.example.colors2web.zummix_app.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.PopBoxAdapter;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.Boxes;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Box;
import com.example.colors2web.zummix_app.POJO.Order2POJO.MasterBox;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MasterBoxAdapter extends RecyclerView.Adapter<MasterBoxAdapter.MasterBoxHolder> {
        List<MasterBox> mboxList;
        private Context mContext;
    List<Boxes>BList = new ArrayList<>();


        public MasterBoxAdapter(Context context,

                                List<MasterBox> mList) {
            this.mContext =context;
            this.mboxList =mList;
        }

        @NonNull

        @Override
        public MasterBoxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.actitvity_adapter_boxes,parent,false);
            return new MasterBoxHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final MasterBoxHolder holder, int position) {
            MasterBox box = mboxList.get(position);

            holder.box_no.setText(box.getBoxNumber());
            holder.created.setText(box.getCreatedAt());

            String url = box.getBarcodeFileName();
            String url1 =" http://5840c423.ngrok.io/zummix-api/public/barcodes/";

            Glide.with(mContext).load(url1+url).into(holder.barcode);
            Log.d("url",url1+url);

//        Picasso.get().load(url1+url).into(holder.barcode);

            final String mbox_number = box.getBoxNumber();
            holder.box_no.setOnClickListener(new View.OnClickListener() {
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

                    popup.showAtLocation(holder.box_no, Gravity.CENTER, 0, 0); //Displaying popup

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
                    loadAdapter(mbox_number,kadapter);

                    cancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            popup.dismiss();
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
            return mboxList.size();
        }

        public void updateAnswers(List<MasterBox> List) {
            mboxList =List;
            notifyDataSetChanged();

        }

        public class MasterBoxHolder extends RecyclerView.ViewHolder {
            TextView box_no, created;
            ImageView barcode;
            public MasterBoxHolder(View itemView) {
                super(itemView);

                box_no=itemView.findViewById(R.id.rv_boxno);
                created=itemView.findViewById(R.id.rv_created_at);
                barcode=itemView.findViewById(R.id.box_imageview);

            }
        }
    }


