package com.example.userList_1313_v2;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.example.userList_1313_v2.database.UserDBSchema;

import java.util.SplittableRandom;
import java.util.UUID;

public class UserCursorWrapper extends CursorWrapper {
    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public User getUser(){
        String uuidSrting = getString(getColumnIndex(UserDBSchema.Cols.UUID));
        String userName = getString(getColumnIndex(UserDBSchema.Cols.USERNAME));
        String userLastName = getString(getColumnIndex(UserDBSchema.Cols.USERLASTNAME));
        String phone = getString(getColumnIndex(UserDBSchema.Cols.PHONE));
        User user = new User(UUID.fromString(uuidSrting));
        user.setUserName(userName);
        user.setUserLastName(userLastName);
        user.setPhone(phone);
        return user;
    }
}
