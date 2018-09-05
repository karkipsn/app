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
import com.example.colors2web.zummix_app.Activities.MasterBoxSearch.PopBox2Adapter;
import com.example.colors2web.zummix_app.Constant;
import com.example.colors2web.zummix_app.ItemDecoration.MyDividerItemDecoration;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.LineItems;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.POJO.Order2POJO.Box;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoxesAdapter extends RecyclerView.Adapter<BoxesAdapter.BoxesHolder> {

    List<Box> boxList;
    private Context mContext;
    List<LineItems> LineList = new ArrayList<>();

    public BoxesAdapter(Context mContext,List<Box> boxList) {
        this.mContext = mContext;
        this.boxList = boxList;
    }

    @NonNull

    @Override
    public BoxesAdapter.BoxesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.actitvity_adapter_boxes,parent,false);
        return new BoxesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoxesAdapter.BoxesHolder holder, int position) {
        Box box = boxList.get(position);


         final String box_number = box.getBoxNumber();
        holder.box_no.setText(box_number);


        holder.created.setText(box.getCreatedAt());
        holder.tracking_code.setText(box.getTrackingCode());

        String url2 = box.getBarcodeFileName();

        String url1 = Constant.url+"/barcodes/";

        Glide.with(mContext).load(url1+url2).into(holder.barcode);
        Log.d("url",url1+url2);

//        Picasso.get().load(url1+url).into(holder.barcode);

        holder.box_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final View popupView = inflater.inflate(R.layout.mbox_modal_line, null);

                WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();

                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width*0.8), WindowManager.LayoutParams.WRAP_CONTENT);
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

                popup.showAtLocation(popupView, Gravity.CENTER, 0, 0); //Displaying popup

                final View container = (View) popup.getContentView().getParent();
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);

                final RecyclerView rv = popupView.findViewById(R.id.recycle_view);
                Button cancel = popupView.findViewById(R.id.pop_up_cancel);


                final PopBox2Adapter kadapter;
                kadapter = new PopBox2Adapter(mContext,LineList);

                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
//            mrecyclerView.setHasFixedSize(true);
                rv.setLayoutManager(mLayoutManager);

                rv.addItemDecoration(new MyDividerItemDecoration(mContext, LinearLayoutManager.HORIZONTAL, 16));
//            mrecyclerView.addItemDecoration(new SimpleItemDecoration(getContext()));
                rv.setItemAnimator(new DefaultItemAnimator());

                rv.setAdapter(kadapter);
                loadAdapter(box_number,kadapter);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
//                        to clear the date stored in the adapterlist of the adapter
//                        call the function get access to the list and clear it
                        kadapter.updateAnswers2();


                    }
                });
            }
        });


    }

    private void loadAdapter(String box_number, final PopBox2Adapter kadapter) {

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String email = preferences.getString("email","");
        String password = preferences.getString("password","");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<MasterBoxResponse> call  =apiInterface.getMboxBoxItems(email,password,box_number);
        call.enqueue(new Callback<MasterBoxResponse>() {
            @Override
            public void onResponse(Call<MasterBoxResponse> call, Response<MasterBoxResponse> response) {
                if(response.isSuccessful()){

                    MasterBoxResponse res = response.body();

                    if(res.getReturnType().equals("success")){

                        List<LineItems>items = res.getmLineItems();
//                   List<Boxes>arraylist = new ArrayList<>();
                        LineItems b = new LineItems();

                        for(int i = 0;i<items.size();i++){

                            String msku = items.get(i).getItemSku();
                            String mname = items.get(i).getItemName();
                            String mqty = items.get(i).getBoxQuantity();

                            b.setItemSku(msku);
                            b.setItemName(mname);
                            b.setBoxQuantity(mqty);
                            LineList.add(b);
                        }

                        kadapter.updateAnswers(LineList);
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
        return boxList.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void updateAnswers(List<Box> List) {
        boxList =List;
        notifyDataSetChanged();

    }

    public class BoxesHolder extends RecyclerView.ViewHolder {
        TextView box_no, created,tracking_code;
        ImageView barcode;
        public BoxesHolder(View itemView) {
            super(itemView);

            box_no=itemView.findViewById(R.id.rv_boxno);

            created=itemView.findViewById(R.id.rv_created_at);
            tracking_code=itemView.findViewById(R.id.rv_tracking_code);
            barcode=itemView.findViewById(R.id.box_imageview);

        }
    }
}
