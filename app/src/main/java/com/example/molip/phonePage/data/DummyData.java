package com.example.molip.phonePage.data;

import java.util.ArrayList;

public class DummyData {
    public static ArrayList<PhoneData> dummyList = new ArrayList<PhoneData>() {
        {
            add(new PhoneData("girl", "이지원", "01011111111"));
            add(new PhoneData("boy", "정우진", "01022222222"));
            add(new PhoneData("puppy", "차다윤", "01033333333"));
        }
    };
}
