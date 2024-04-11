package com.example.nhom4_chatappandroid.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom4_chatappandroid.ChatActivity;
import com.example.nhom4_chatappandroid.R;
import com.example.nhom4_chatappandroid.model.UserModel;
import com.example.nhom4_chatappandroid.utils.AndroidUtil;
import com.example.nhom4_chatappandroid.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchUserRecyclerAdapter extends FirestoreRecyclerAdapter<UserModel,SearchUserRecyclerAdapter.UserModelViewHolder> {

    Context context;

    public SearchUserRecyclerAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options,Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull UserModelViewHolder holder, int position, @NonNull UserModel model) {
        holder.user_name_text.setText(model.getHoTen());
        holder.email_text.setText(model.getEmail());
        if(model.getUserId().equals(FirebaseUtil.currentUserId())){
            holder.user_name_text.setText(model.getHoTen()+"(Me)");
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChatActivity.class);
                AndroidUtil.passUserModelAsIntent(intent,model);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });
    }

    @NonNull
    @Override
    public UserModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_user_recycler_row,parent,false);
        return new UserModelViewHolder(view);
    }

    class UserModelViewHolder extends RecyclerView.ViewHolder{
        TextView user_name_text;
        TextView email_text;
        ImageView profilePic;


        public UserModelViewHolder(@NonNull View itemView) {
            super(itemView);
            user_name_text=itemView.findViewById(R.id.user_name_text);
            email_text=itemView.findViewById(R.id.email_text);
            profilePic = itemView.findViewById(R.id.profile_pic_image_view);
        }
    }
}
