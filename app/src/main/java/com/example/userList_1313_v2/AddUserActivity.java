package com.example.userList_1313_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity {
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);
        addBtn = findViewById(R.id.addBtn);


        int userPosition = getIntent().getIntExtra("userPosition", -1);

        if (userPosition != -1){
        // Редактируем пользователя
        Users users = new Users(AddUserActivity.this);
        User user = users.getUserList().get(userPosition);
        editTextName.setText(user.getUserName());
        editTextLastName.setText(user.getUserLastName());
        editTextPhone.setText(user.getPhone());
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user.setUserName(editTextName.getText().toString());
                user.setUserLastName(editTextLastName.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                users.updateUser(user);
                onBackPressed();
            }
        });
        }else {
            // Добавляем пользователя
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    User user = new User();
                    user.setUserName(editTextName.getText().toString());
                    user.setUserLastName(editTextLastName.getText().toString());
                    user.setPhone(editTextPhone.getText().toString());
                    Users users = new Users(AddUserActivity.this);
                    users.addUser(user);
                    onBackPressed();    // возвращаемся на главную активность
                }
            });
        }
    }
}