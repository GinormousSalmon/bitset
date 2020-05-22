package test;

import jdk.jfr.Unsigned;
import main.java.Bitset;

import java.security.cert.TrustAnchor;
import java.util.*;

import org.junit.jupiter.api.*;


public class BitSetTest {
    Random rand = new Random();

    public static void println(Object line) {
        System.out.println(line);
    }

    public static void print(Object line) {
        System.out.print(line);
    }

//    public boolean[] toBinary(byte number) {
//        int n = number + 128;
//        boolean[] bin = new boolean[8];
//        for (int i = 0; i < 8; i++) {
//            bin[7 - i] = n % 2 == 1;
//            n /= 2;
//        }
//        return bin;
//    }
//
//    public byte toDecimal(boolean[] bin) {
//        byte dec = -128;
//        int[] coefs = {1, 2, 4, 8, 16, 32, 64, 128};
//        for (int i = 0; i < 8; i++)
//            if (bin[7 - i])
//                dec += coefs[i];
//        return dec;
//    }
//
//    @Test
//    void toBinaryTest() {
//        int intToByteDivider = (int) Math.pow(2, 24);
//        Bitset bitset = new Bitset(8);
//        for (int i = 0; i < 1000; i++) {
//            byte randomByte = (byte) (rand.nextInt() / intToByteDivider);
//            boolean[] bits = new boolean[8];
//            char[] chars = Integer.toBinaryString(randomByte + 128).toCharArray();
//            for (int j = 0; j < chars.length; j++)
//                bits[j + 8 - chars.length] = chars[j] == '1';
//            Assertions.assertArrayEquals(toBinary(randomByte), bits);
//        }
//    }
//
//    @Test
//    void toDecimalTest() {
//        Bitset bitset = new Bitset(8);
//        for (int i = 0; i < 1000; i++) {
//            boolean[] bits = new boolean[8];
//            String binString = "";
//            for (int j = 0; j < 8; j++) {
//                bits[j] = rand.nextBoolean();
//                binString = binString.concat(bits[j] ? "1" : "0");
//            }
//            int number = Integer.parseInt(binString, 2) - 128;
//            Assertions.assertEquals(toDecimal(bits), number);
//        }
//    }

    @Test
    void addRemoveTest() {
        for (int i = 0; i < 50; i++) {
            int size = (int) (rand.nextFloat() * 50000);
            Bitset bitset = new Bitset(size);
            for (int j = 0; j < 10000; j++) {
                int random = (int) (rand.nextFloat() * 1000000);
                if (random < size) {
                    bitset.add(random);
                    Assertions.assertTrue(bitset.contains(random));
                    bitset.remove(random);
                    Assertions.assertFalse(bitset.contains(random));
                } else {
                    Assertions.assertThrows(IllegalArgumentException.class, () -> bitset.add(random));
                    Assertions.assertThrows(IllegalArgumentException.class, () -> bitset.remove(random));
                }
            }
        }
    }

    @Test
    void differenceTest() {
        for (int a = 0; a < 100; a++) {
            int size = (int) (rand.nextFloat() * 50000);
            Bitset bitset = new Bitset(size);
            ArrayList<Integer> trueBits = new ArrayList<>();
            for (int j = 0; j < 10000; j++) {
                int random = (int) (rand.nextFloat() * 1000000);
                if (random < size) {
                    trueBits.add(random);
                    bitset.add(random);
                }
            }
            bitset.difference();
            for (int i = 0; i < size; i++)
                if (trueBits.contains(i))
                    Assertions.assertFalse(bitset.contains(i));
                else
                    Assertions.assertTrue(bitset.contains(i));
        }
    }

    @Test
    void unionTest() {
        for (int a = 0; a < 100; a++) {
            int size1 = (int) (rand.nextFloat() * 50000);
            int size2 = (int) (rand.nextFloat() * 50000);
            Bitset bitset1 = new Bitset(size1);
            Bitset bitset2 = new Bitset(size2);
            ArrayList<Integer> trueBits1 = new ArrayList<>();
            ArrayList<Integer> trueBits2 = new ArrayList<>();
            for (int j = 0; j < 10000; j++) {
                int random = (int) (rand.nextFloat() * 1000000);
                if (random < size1) {
                    trueBits1.add(random);
                    bitset1.add(random);
                }
            }
            for (int j = 0; j < 10000; j++) {
                int random = (int) (rand.nextFloat() * 1000000);
                if (random < size2) {
                    trueBits2.add(random);
                    bitset2.add(random);
                }
            }
            Bitset result = bitset1.union(bitset2);
            for (int i = 0; i < result.length(); i++)
                if (trueBits1.contains(i) || trueBits2.contains(i))
                    Assertions.assertTrue(result.contains(i));
                else
                    Assertions.assertFalse(result.contains(i));
        }
    }

    @Test
    void intersectTest() {
        for (int a = 0; a < 100; a++) {
            int size1 = (int) (rand.nextFloat() * 50000);
            int size2 = (int) (rand.nextFloat() * 50000);
            Bitset bitset1 = new Bitset(size1);
            Bitset bitset2 = new Bitset(size2);
            ArrayList<Integer> trueBits1 = new ArrayList<>();
            ArrayList<Integer> trueBits2 = new ArrayList<>();
            for (int j = 0; j < 10000; j++) {
                int random = (int) (rand.nextFloat() * 1000000);
                if (random < size1) {
                    trueBits1.add(random);
                    bitset1.add(random);
                }
            }
            for (int j = 0; j < 10000; j++) {
                int random = (int) (rand.nextFloat() * 1000000);
                if (random < size2) {
                    trueBits2.add(random);
                    bitset2.add(random);
                }
            }
            Bitset result = bitset1.intersect(bitset2);
            for (int i = 0; i < result.length(); i++)
                if (trueBits1.contains(i) && trueBits2.contains(i))
                    Assertions.assertTrue(result.contains(i));
                else
                    Assertions.assertFalse(result.contains(i));
        }
    }
}
