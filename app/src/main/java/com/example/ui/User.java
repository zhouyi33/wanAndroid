package com.example.ui;

import android.content.Intent;

public class User {
    String[] userIds = new String[200];
    int size1 = 0;
    String[] userPasswords = new String[200];
    int size2 = 0;

    public void register(String userId,String userPassword){
        userIds[size1++] = userId;
        userPasswords[size2++] = userPassword;
    }

    public boolean login(String userId,String userPassword){
        for(int i = 0;i<size1;i++){
            if(userIds[i].equals(userId)) {
                return userPasswords[i].equals(userPassword);
            }
        }
        return false;
    }
    private User(){}
    private static User Instance;
    public static User getInstance(){
        if(Instance == null){
            Instance = new User();
        }
        return  Instance;
    }
}
