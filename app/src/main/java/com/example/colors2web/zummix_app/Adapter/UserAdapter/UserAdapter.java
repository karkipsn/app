package com.example.colors2web.zummix_app.Adapter.UserAdapter;

import android.content.Context;
import android.icu.util.ULocale;
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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    List<User>UserList;
    Context mContext;

    public UserAdapter(List<User> userList, Context mContext) {
        UserList = userList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_user_adapter,parent,false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder holder, int position) {

        User user = UserList.get(position);

        Long cus_id = user.getCustomerId();
        String name1 = user.getFirstName() +" " +user.getLastName();
        String email1 = user.getEmail();
        String c1 = user.getCreatedAt();
        List<String> ug = user.getUserGroup();

//      if (cus_id != Long.valueOf(0)) {

            holder.name.setText(name1);
            holder.email.setText(email1);

//         String ug1 = ug.get(position);
            holder.groups.setText(ug.toString().replace("[", "").replace("]", ""));
            holder.created.setText(c1);
//      }

    }

    @Override
    public int getItemCount() {
        return UserList.size();
    }

//    public void updateAdapter(List<User> uList) {
//        UserList =uList;
//        notifyDataSetChanged();
//    }

    public void updateAnswers(ArrayList<User> logs1) {
        UserList =logs1;
        notifyDataSetChanged();
    }

    public class UserHolder extends RecyclerView.ViewHolder {
        TextView name,email,groups,created;
        public UserHolder(View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.user_adpt_name);
            email = itemView.findViewById(R.id.user_adpt_email);
            groups = itemView.findViewById(R.id.user_adpt_groups);
            created = itemView.findViewById(R.id.user_adpt_created);
        }
    }
}
