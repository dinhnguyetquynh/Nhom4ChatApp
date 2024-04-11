package com.example.nhom4_chatappandroid;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom4_chatappandroid.Adapter.ChatRecyclerAdapter;
import com.example.nhom4_chatappandroid.Adapter.SearchUserRecyclerAdapter;
import com.example.nhom4_chatappandroid.model.ChatMessageModel;
import com.example.nhom4_chatappandroid.model.ChatRoomModel;
import com.example.nhom4_chatappandroid.model.UserModel;
import com.example.nhom4_chatappandroid.utils.AndroidUtil;
import com.example.nhom4_chatappandroid.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.Query;

import java.util.Arrays;

public class ChatActivity extends AppCompatActivity {

    UserModel otherUser;
    String chatroomId;


    EditText messageInput;
    ImageButton sendMessageBtn;
    ImageButton backBtn;
    TextView otherUsername;
    RecyclerView recyclerView;
    ChatRoomModel chatroomModel;
    ChatRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        otherUser = AndroidUtil.getUserModelFromIntent(getIntent());
        chatroomId= FirebaseUtil.getChatroomId(FirebaseUtil.currentUserId(),otherUser.getUserId());
        messageInput=findViewById(R.id.chat_message_input);
        sendMessageBtn = findViewById(R.id.message_send_btn);
        backBtn = findViewById(R.id.back_btn);
        otherUsername = findViewById(R.id.other_username);
        recyclerView = findViewById(R.id.chat_recycler_view);

        backBtn.setOnClickListener((v)->{
            Intent intent = new Intent(this,SearchUser.class);
            startActivity(intent);
            finish();
        });
        otherUsername.setText(otherUser.getHoTen());



        sendMessageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageInput.getText().toString().trim();
                if(message.isEmpty())
                    return;
                sendMessageToUser(message);
            }
        });
        getOrCreateChatRoom();
        setupChatRecyclerView();
    }
    void setupChatRecyclerView(){

        Query query = FirebaseUtil.getChatroomMessageReference(chatroomId)
                .orderBy("timestamp",Query.Direction.DESCENDING);
        FirestoreRecyclerOptions< ChatMessageModel> options = new FirestoreRecyclerOptions.Builder<ChatMessageModel>()
                .setQuery(query, ChatMessageModel.class).build();

        adapter = new ChatRecyclerAdapter(options,getApplicationContext());
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setReverseLayout(true);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.startListening();
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                recyclerView.smoothScrollToPosition(0);
            }
        });

    }

    void sendMessageToUser(String message){
        chatroomModel.setLastMessageTimestamp(Timestamp.now());
        chatroomModel.setLastMessageSenderId(FirebaseUtil.currentUserId());
        FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);
        ChatMessageModel chatMessageModel = new ChatMessageModel(message,FirebaseUtil.currentUserId(),Timestamp.now());

        FirebaseUtil.getChatroomMessageReference(chatroomId).add(chatMessageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    messageInput.setText("");
                }
            }
        });




    }

     void getOrCreateChatRoom() {
         FirebaseUtil.getChatroomReference(chatroomId).get().addOnCompleteListener(task -> {
             if(task.isSuccessful()){
                 chatroomModel = task.getResult().toObject(ChatRoomModel.class);
                 if(chatroomModel==null){
                     //first time chat
                     chatroomModel = new ChatRoomModel(
                             chatroomId,
                             Arrays.asList(FirebaseUtil.currentUserId(),otherUser.getUserId()),
                             Timestamp.now(),
                             ""
                     );
                     FirebaseUtil.getChatroomReference(chatroomId).set(chatroomModel);
                 }
             }
         });
    }
}