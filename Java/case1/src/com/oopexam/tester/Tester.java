package com.oopexam.tester;

import com.oopexam.structures.TList;

public class Tester {

    public static void TestStructures() {
        System.out.print("Testing list...");
        TestList();
        System.out.println("ok");
    }

    private static void TestList() {
        TList<String> strList = new TList<String>();

        strList.insert(0, "1");
        strList.insert(1, "2");
        strList.insert(0, "3");

        strList.insert(strList.getCount(), "heh");

        strList.insert(0, "first");
        strList.insert(0, "now me");

        strList.delete(3);
    }
}
