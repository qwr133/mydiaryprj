package com.mydiary.mydiaryprj.common;

public class Stringer {

    public static Boolean isEmpty(final String s) {
        if (s == null) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    public static Boolean isBlank(final String s) {
        if (!isEmpty(s) && s.trim().length() > 0) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }
    }
}