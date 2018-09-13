package com.example.colors2web.zummix_app.Adapter.Customer_Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.colors2web.zummix_app.Activities.GroupCusDetails;
import com.example.colors2web.zummix_app.POJO.OrderByCus.Order;
import com.example.colors2web.zummix_app.R;

import java.util.List;


public class GroupByCusADapter extends RecyclerView.Adapter<GroupByCusADapter.GroupHolder> {
    private Context mcontext;
    List<Order> orderGroup;

    public GroupByCusADapter(Context mcontext, List<Order> orderGroup) {
        this.mcontext = mcontext;
        this.orderGroup = orderGroup;
    }

    @NonNull
    @Override
    public GroupByCusADapter.GroupHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_group_cus, null, false);
        return new GroupHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupByCusADapter.GroupHolder holder, int position) {
        Order ord = orderGroup.get(position);

        final String store = ord.getCustomerName();
        final Long th1 = ord.getNoOfVipDeliveryOrders();
        final Long th2 = ord.getNoOfShipToOrders();
        final Long th3 = ord.getNoOfWillCallsOrders();

        final Long th4 = th1 + th2 + th3;

        holder.total.setText(String.valueOf(th4));
        holder.store.setText(store);
        Log.d("store", store);
        holder.dview.setText("View Details");
        holder.dview.setTextColor(holder.dview.getResources().getColor(R.color.colorPrimary));

        final String cus_id = String.valueOf(ord.getCustomerId());
        Log.d("idcus", String.valueOf(ord.getCustomerId()));

        holder.dview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mcontext, GroupCusDetails.class);
                i.putExtra("cus_id", cus_id);
                Log.d("cus_id", cus_id);
                mcontext.startActivity(i);
            }
        });

        holder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.activity_popup, null);

                WindowManager wm = (WindowManager)mcontext.getSystemService(Context.WINDOW_SERVICE);
                Display display = wm.getDefaultDisplay();
//                Display display = mcontext.getWindowManager().getDefaultDisplay();


                int height = display.getHeight();
                int width = display.getWidth();

                final PopupWindow popup = new PopupWindow(popupView,
                        (int) (width * 0.8), LinearLayout.LayoutParams.WRAP_CONTENT);
//                (int) (height / 2.5)
                popup.setFocusable(true);
                popup.setOutsideTouchable(true);
                popup.setAnimationStyle(android.R.style.Animation_Dialog);

//                popup.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
                popup.showAtLocation(holder.menu, Gravity.CENTER, 0, 0); //Displaying popup

                View container = (View) popup.getContentView().getParent();
                WindowManager.LayoutParams p = (WindowManager.LayoutParams) container.getLayoutParams();
                p.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
                p.dimAmount = 0.7f;
                wm.updateViewLayout(container, p);


//                ColorDrawable dialogColor = new ColorDrawable( Color.BLACK);
//                dialogColor.setAlpha(150); //(0-255) 0 means fully transparent, and 255 means fully opaque
//                popup.getWindow.setBackgroundDrawable(ColorDrawable);


//                final PopupWindow popup = new PopupWindow(popupView, LinearLayout.LayoutParams.FILL_PARENT,
//                        LinearLayout.LayoutParams.WRAP_CONTENT, true);

//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    popup.setBackgroundDrawable(new ColorDrawable(mcontext.getColor(R.color.viewBack)));
//                }


                TextView t1, t2, t3, t4, t5, t6;

                t1 = popupView.findViewById(R.id.pop1);
                t2 = popupView.findViewById(R.id.pop2);
                t3 = popupView.findViewById(R.id.pop3);
                t4 = popupView.findViewById(R.id.pop4);
                t5 = popupView.findViewById(R.id.pop5);
                t6 = popupView.findViewById(R.id.pop6);

                t1.setText(store);
                t2.setText(String.valueOf(th1));
                t3.setText(String.valueOf(th2));
                t4.setText(String.valueOf(th3));
                t5.setText(String.valueOf(th4));

                t6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popup.dismiss();
                    }
                });


//                AlertDialog.Builder builder = new AlertDialog.Builder(
//                        mcontext);
//                LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View root =inflater.inflate(
//                        (R.layout.activity_popup),null);
//
//
//                // set dialog message
//                builder
//                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,int id) {
//                                // if this button is clicked, close
//                                // current activity
////                                mcontext.finish();
//
//                            }
//                        })
//                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog,int id) {
//                                // if this button is clicked, just close
//                                // the dialog box and do nothing
//                                dialog.cancel();
//                            }
//                        });
//
//                // create alert dialog
//                final AlertDialog alertDialog = builder.create();
//                // show it
//                builder.setView(root);
//
//                TextView t1, t2, t3, t4, t5, t6;
//
//                t1 = root.findViewById(R.id.pop1);
//                t2 = root.findViewById(R.id.pop2);
//                t3 = root.findViewById(R.id.pop3);
//                t4 = root.findViewById(R.id.pop4);
//                t5 = root.findViewById(R.id.pop5);
//                t6 = root.findViewById(R.id.pop6);
//
//                t1.setText(store);
//                t2.setText(String.valueOf(th1));
//                t3.setText(String.valueOf(th2));
//                t4.setText(String.valueOf(th3));
//                t5.setText(String.valueOf(th4));
//
//                t6.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        alertDialog.dismiss();
//                    }
//                });
//
//                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return orderGroup.size();
    }

    public void updateAnswers(List<Order> cus) {
        orderGroup = cus;
        notifyDataSetChanged();
    }

    public class GroupHolder extends RecyclerView.ViewHolder {
        TextView store, total, dview;
        ImageView menu;

        public GroupHolder(View itemView) {
            super(itemView);
            store = itemView.findViewById(R.id.rv_store);
            total = itemView.findViewById(R.id.rv_total_orders);
            dview = itemView.findViewById(R.id.rv_view_details);
            menu = itemView.findViewById(R.id.rv_popup);

//            menu.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                   show_popup();
//                }
//            });

        }

        private void show_popup() {

            LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View popupView = inflater.inflate(R.layout.activity_popup, null);

            final PopupWindow popup = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT, true);

//                popup.setAnimationStyle(android.R.style.Animation_Dialog);
            popup.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
            popup.setOutsideTouchable(true);
            popup.setFocusable(true);


            popup.showAtLocation(itemView, Gravity.CENTER, 0, 0); // Displaying popup

            TextView t1, t2, t3, t4, t5, t6;

            t1 = popupView.findViewById(R.id.pop1);
            t2 = popupView.findViewById(R.id.pop2);
            t3 = popupView.findViewById(R.id.pop3);
            t4 = popupView.findViewById(R.id.pop4);
            t5 = popupView.findViewById(R.id.pop5);
            t6 = popupView.findViewById(R.id.pop6);

            t1.setText("HI");
            t2.setText(String.valueOf(15));
            t3.setText(String.valueOf(15));
            t4.setText(String.valueOf(12));
            t5.setText(String.valueOf(42));

            t6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popup.dismiss();
                }
            });


        }
    }
}
