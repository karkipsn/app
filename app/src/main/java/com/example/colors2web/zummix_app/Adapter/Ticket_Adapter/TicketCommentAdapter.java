package com.example.colors2web.zummix_app.Adapter.Ticket_Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.colors2web.zummix_app.POJO.TicketDetails.Comments;
import com.example.colors2web.zummix_app.POJO.TicketDetails.User;
import com.example.colors2web.zummix_app.R;

import java.util.ArrayList;
import java.util.List;

public class TicketCommentAdapter extends RecyclerView.Adapter<TicketCommentAdapter.CommentHolder> {

    Context mContext;
    List<Comments> CommentList;
    List<User>UsetList;

    public TicketCommentAdapter(Context mContext, List<Comments> commentList) {
        this.mContext = mContext;
        CommentList = commentList;
    }

    @NonNull
    @Override
    public TicketCommentAdapter.CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_ticket_commrnts_adapter,parent,false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketCommentAdapter.CommentHolder holder, int position) {
        Comments comments = CommentList.get(position);

        String createdby=String.valueOf(comments.getCreatedBy());

        for(int i=0;i<UsetList.size();i++){

//            User user = UsetList.get(i);
            String id = String.valueOf(UsetList.get(i).getId());

            if(id.equals(createdby)){
                String user_name = UsetList.get(i).getName();
                holder.user.setText(String.valueOf(user_name));
                holder.user.setTextColor(holder.user.getResources().getColor(R.color.colorPrimary));

            }
        }

//        holder.user.setText(String.valueOf(comments.getCreatedBy()));
        holder.comment.setText(comments.getComment());

    }

    @Override
    public int getItemCount() {
        return CommentList.size();
    }

    public void updateAdapter(ArrayList<Comments> comments, ArrayList<User> users) {
        CommentList = comments;
        UsetList = users;
        notifyDataSetChanged();
    }

    public class CommentHolder extends RecyclerView.ViewHolder {
        TextView user,comment;
        public CommentHolder(View itemView) {
            super(itemView);
            user = itemView.findViewById(R.id.comment_user);
            comment = itemView.findViewById(R.id.comment_comment);
        }
    }
}
