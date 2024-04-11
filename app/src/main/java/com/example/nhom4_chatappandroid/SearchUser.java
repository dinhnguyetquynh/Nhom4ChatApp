package com.example.nhom4_chatappandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.nhom4_chatappandroid.Adapter.SearchUserRecyclerAdapter;
import com.example.nhom4_chatappandroid.model.UserModel;
import com.example.nhom4_chatappandroid.utils.FirebaseUtil;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.Query;

public class SearchUser extends AppCompatActivity {
    ImageButton search_btnBack;
    EditText search_username_input;
    ImageButton search_btnTimKiem;

    RecyclerView rcvSeacrh;

    SearchUserRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);
        search_btnBack = findViewById(R.id.search_btnBack);
        search_username_input=findViewById(R.id.seach_username_input);
        search_btnTimKiem=findViewById(R.id.search_btnTimKiem);
        rcvSeacrh=findViewById(R.id.rcv_Search);

        search_username_input.requestFocus();

        search_btnBack.setOnClickListener(v -> {
            onBackPressed();
        });

        search_btnTimKiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchFriend = search_username_input.getText().toString();
                if(searchFriend.isEmpty()||searchFriend.length()<3){
                    search_username_input.setError("Email khong hop le");
                    return;
                }
                setupSearchRecyclerView(searchFriend);
            }
        });
    }

    private void setupSearchRecyclerView(String searchFriend) {
        Query query = FirebaseUtil.allUserCollectionReference()
                .whereEqualTo("email",searchFriend);
        FirestoreRecyclerOptions< UserModel> options = new FirestoreRecyclerOptions.Builder<UserModel>()
                .setQuery(query, UserModel.class).build();

        adapter = new SearchUserRecyclerAdapter(options,getApplicationContext());
        rcvSeacrh.setLayoutManager(new LinearLayoutManager(this));
        rcvSeacrh.setAdapter(adapter);
        adapter.startListening();

    }

    @Override
    protected void onStart() {
        super.onStart();
        if(adapter!=null){
            adapter.startListening();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(adapter!=null){
            adapter.stopListening();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(adapter!=null){
            adapter.startListening();
        }
    }
}