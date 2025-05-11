package com.example.naher_farhsa.exammaster_fsvo.Enum;


public enum Hall {
    H1, H2, H3, H4, H5, H6, H7, H8;

    public static Hall fromValue(int value) {
        for (Hall h : Hall.values()) {
            if (h.ordinal() == value) return h;
        }
        throw new IllegalArgumentException("Invalid Hall ID: " + value);
    }

}
