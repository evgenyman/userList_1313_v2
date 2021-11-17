package com.example.userList_1313_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoUserActivity extends AppCompatActivity {
    TextView fioUserInfo;
    TextView phoneUserInfo;
    Button editBtn;
    Button deleteBtn;
    Button backBtn;
    Users users;
    User user;
    int userPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        fioUserInfo = findViewById(R.id.fioUserInfo);
        phoneUserInfo = findViewById(R.id.phoneUserInfo);
        editBtn = findViewById(R.id.editBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        backBtn = findViewById(R.id.backBtn);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                users.deleteUser(user.getUuid());
                onBackPressed();
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        userPosition = getIntent().getIntExtra("userPosition", 0);
        users = new Users(InfoUserActivity.this);
        user = users.getUserList().get(userPosition);
        String fio = user.getUserName()+" "+user.getUserLastName();
        fioUserInfo.setText(fio);
        phoneUserInfo.setText(user.getPhone());
    }
}