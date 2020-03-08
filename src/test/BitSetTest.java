package test;

import main.java.Bitset;

import java.security.cert.TrustAnchor;
import java.util.*;

import org.junit.jupiter.api.*;


public class BitSetTest {
    Random rand = new Random();

    public static void print(Object line) {
        System.out.println(line);
    }

    @Test
    void addTest() {
        Bitset<Float> data = new Bitset<>();
        for (int i = 0; i < 1000; i++) {
            int in = (int) (rand.nextFloat() * 100);
            float fl = ((float) in) / 100;
            data.add(fl);
        }
        ArrayList<Float> a = data.toList();
        Collections.sort(a);
        for (int i = 0; i < a.size() - 1; i++)
            Assertions.assertNotEquals(a.get(i), a.get(i + 1));
    }

    @Test
    void removeTest() {
        Bitset<Integer> data = new Bitset<>();
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            int rand_int = rand.nextInt();
            data.add(rand_int);
            numbers.add(rand_int);
        }
        for (int i : numbers){
            data.remove(i);
            Assertions.assertFalse(data.contains(i));
        }
    }

    @Test
    void intersectionTest(){
        Bitset<Float> data1 = new Bitset<>();
        for (int i = 0; i < 1000; i++) {
            int in = (int) (rand.nextFloat() * 2000);
            float fl = ((float) in) / 2000;
            data1.add(fl);
        }

        Bitset<Float> data2 = new Bitset<>();
        for (int i = 0; i < 1000; i++) {
            int in = (int) (rand.nextFloat() * 2000);
            float fl = ((float) in) / 2000;
            data2.add(fl);
        }

        ArrayList<Float> a1 = new ArrayList<>(data1.toList());
        a1.removeAll(data2.toList());

        ArrayList<Float> a2 = new ArrayList<>(data1.toList());
        a2.removeAll(a1);
        Collections.sort(a2);

        data1.intersection(data2);
        a1 = new ArrayList<>(data1.toList());
        Collections.sort(a1);
        Assertions.assertIterableEquals(a1, a2);
    }

    @Test
    void unionTest(){
        Bitset<Float> data1 = new Bitset<>();
        for (int i = 0; i < 1000; i++) {
            int in = (int) (rand.nextFloat() * 2000);
            float fl = ((float) in) / 2000;
            data1.add(fl);
        }

        Bitset<Float> data2 = new Bitset<>();
        for (int i = 0; i < 1000; i++) {
            int in = (int) (rand.nextFloat() * 2000);
            float fl = ((float) in) / 2000;
            data2.add(fl);
        }

        HashSet<Float> hash1 = new HashSet<>(data1.toList());
        HashSet<Float> hash2 = new HashSet<>(data2.toList());
        hash1.addAll(hash2);
        ArrayList<Float> a1 = new ArrayList<>(hash1);
        Collections.sort(a1);

        data1.union(data2);
        ArrayList<Float> a2 = new ArrayList<>(data1.toList());
        Collections.sort(a2);
        Assertions.assertIterableEquals(a1, a2);
    }

}
