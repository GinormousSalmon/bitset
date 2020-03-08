package test;

import main.java.Bitset;

import java.util.*;

public class BitSetTest {
    public static void print(Object line) {
        System.out.println(line);
    }

    public static void main(String[] args) {
        Bitset<Integer> data = new Bitset<>();
        Integer[] b = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        data.add(b);
        for (Integer i : data)
            print(i);
        print(data.contains(1));
        print(data.contains(1331));
        data.remove(2);
        for (Integer i : data)
            print(i);
    }
}
