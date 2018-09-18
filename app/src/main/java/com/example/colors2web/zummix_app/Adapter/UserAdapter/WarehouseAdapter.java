package com.example.colors2web.zummix_app.Adapter.UserAdapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.Users.User;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class WarehouseAdapter  extends RecyclerView.Adapter<WarehouseAdapter.WareHolder> {

    List<User> WareList;
    Context mContext;

    public WarehouseAdapter(List<User> userList, Context mContext) {
        WareList = userList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public WareHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_adapter,parent,false);
        return new WareHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull WareHolder holder, int position) {

        User user = WareList.get(position);

        holder.name.setText(user.getFirstName() + user.getLastName());
        holder.email.setText(user.getEmail());
        List<String> ug = user.getUserGroup();
//         String ug1 = ug.get(position);
        holder.groups.setText(ug.toString().replace("[", "").replace("]", ""));
        holder.created.setText(user.getCreatedAt());
    }

    @Override
    public int getItemCount() {
        return WareList.size();
    }


    public void updateAnswers(ArrayList<User> logs) {
        WareList =logs;
        notifyDataSetChanged();
    }

    public class WareHolder extends RecyclerView.ViewHolder {

        TextView name,email,groups,created;
        public WareHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.user_adpt_name);
            email = itemView.findViewById(R.id.user_adpt_email);
            groups = itemView.findViewById(R.id.user_adpt_groups);
            created = itemView.findViewById(R.id.user_adpt_created);
        }
    }
}