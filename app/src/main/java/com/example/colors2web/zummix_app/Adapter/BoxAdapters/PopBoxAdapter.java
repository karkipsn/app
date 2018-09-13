package com.example.colors2web.zummix_app.Adapter.BoxAdapters;

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
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.LineItems;
import com.example.colors2web.zummix_app.POJO.MasterBoxSearch.MasterBoxResponse;
import com.example.colors2web.zummix_app.R;
import com.example.colors2web.zummix_app.api.APIClient;
import com.example.colors2web.zummix_app.api.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PopBoxAdapter extends RecyclerView.Adapter<PopBoxAdapter.PopBoxHolder> {
    List<Boxes> BoxList;
    Context mContext;
    List<LineItems> LineList = new ArrayList<>();

    public PopBoxAdapter( Context mContext, List<Boxes> boxList) {
        this.mContext = mContext;
        BoxList = boxList;
    }

    @NonNull
    @Override
    public PopBoxAdapter.PopBoxHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mbox_modal_adapter, parent, false);
        return new PopBoxHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PopBoxAdapter.PopBoxHolder holder, int position) {
        Boxes boxes = BoxList.get(position);

        final String box_no=boxes.getBoxNumber();
        holder.package_no.setText(box_no);
        holder.order_no.setText(boxes.getOrderNumber());


        holder.package_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


//                    PopBox2Adapter kadapter;
//                    kadapter = new PopBox2Adapter(mContext,LineList);
//                    loadAdapter(box_no,kadapter);


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

                RecyclerView rv = popupView.findViewById(R.id.recycle_view);
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
                loadAdapter(box_no,kadapter);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                        kadapter.updateAnswers2();
                    }
                });

                }
            });

    }

    private void loadAdapter(String box_no, final PopBox2Adapter kadapter) {
//        api adapter loading
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
        String email = preferences.getString("email","");
        String password = preferences.getString("password","");

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

        Call<MasterBoxResponse> call  =apiInterface.getMboxBoxItems(email,password,box_no);
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
        return BoxList.size();
    }

    public void updateAnswers(List<Boxes> b) {
        BoxList =b;
        notifyDataSetChanged();
    }

    public void updateAnswer2() {
        BoxList.clear();
        notifyDataSetChanged();
    }

    public class PopBoxHolder extends RecyclerView.ViewHolder {

        TextView package_no, order_no;
        public PopBoxHolder(View itemView) {
            super(itemView);
            package_no = itemView.findViewById(R.id.pop_box_adp_pack_no);
            order_no = itemView.findViewById(R.id.pop_box_adp_orderno);
        }
    }
}
