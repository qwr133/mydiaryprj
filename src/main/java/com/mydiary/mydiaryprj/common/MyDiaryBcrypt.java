package com.mydiary.mydiaryprj.common;

import org.mindrot.jbcrypt.BCrypt;

public class MyDiaryBcrypt {

    public static String hashpw(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
                                        //2명이 1234로 비번해놔도 해커가 알아보지 못함
    }

    public static boolean checkpw(String password, String hashedPassword){
        return BCrypt.checkpw(password, hashedPassword);
    }
}
