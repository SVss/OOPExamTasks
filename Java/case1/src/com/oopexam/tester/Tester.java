package com.oopexam.tester;

import com.oopexam.structures.TList;

public class Tester {

    public static void TestStructures() {
        System.out.println("Testing list...");
        TestList();
        System.out.println("ok");
    }

    private static <T> void printList(TList<T> list) {
        if (list.getCount() == 0) {
            System.out.println("<list is empty>");
        } else {
            for (int i = 0; i < list.getCount(); ++i) {
                System.out.println(list.getAt(i));
            }
        }
        System.out.println();
    }

    private static void TestList() {
        TList<String> strList = new TList<String>();

        strList.insert(0, "1");
        strList.insert(1, "2");
        strList.insert(0, "3");
        printList(strList);

        strList.insert(strList.getCount(), "heh");
        printList(strList);

        strList.insert(0, "first");
        printList(strList);

        strList.insert(0, "now me");
        printList(strList);

        strList.delete(3);
        printList(strList);

        TList<String> list2 = new TList<>();


        strList.moveTo(list2);

        System.out.println("strList:");
        printList(strList);

        System.out.println("list2:");
        printList(list2);
    }
}
