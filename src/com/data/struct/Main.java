package com.data.struct;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        MyArrayList<Integer> array = new MyArrayList<Integer>();
        array.add(1);
        array.add(3);
        array.add(-1);
        array.add(2, 2);
        array.add(0, 100);
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        };
        array.timSort(comparator);

        System.out.println(array.binarySearch(new Integer(100), comparator));
        array.print();
    }


}
