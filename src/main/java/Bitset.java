package main.java;

import java.util.*;


class prog {
    public static void print(Object line) {
        System.out.print(line);
    }

    public static void println(Object line) {
        System.out.println(line);
    }

    public static void main(String[] args) {
        Bitset bitsetA = new Bitset(20);
        Bitset bitsetB = new Bitset(30);
        int[] a = {1, 2, 3, 4, 5, 10, 14, 15, 16};
        int[] b = {0, 2, 3, 11, 12, 14, 15, 16, 17, 19, 29};
        bitsetA.add(a);
        bitsetB.add(b);
        bitsetA.print();
        bitsetB.print();
        Bitset bitsetC = bitsetB.intersect(bitsetA);
        bitsetC.print();
    }
}


public class Bitset {//<T> implements Iterable<T> {
    private int length;
    private byte[] data;


    public Bitset(int N) {
        if (N > 0) {
            this.length = N;
            this.data = new byte[(int) Math.ceil((double) N / 8)];
            Arrays.fill(this.data, (byte) -128);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public int length() {
        return this.length;
    }

    //булевы флаги "упаковываются" в тип byte и хранятся в byte[]
    public boolean[] toBinary(byte number) {
        int n = number + 128;
        boolean[] bin = new boolean[8];
        for (int i = 0; i < 8; i++) {
            bin[7 - i] = n % 2 == 1;
            n /= 2;
        }
        return bin;
    }

    public byte toDecimal(boolean[] bin) {
        byte dec = -128;
        int[] coefs = {1, 2, 4, 8, 16, 32, 64, 128};
        for (int i = 0; i < 8; i++)
            if (bin[7 - i])
                dec += coefs[i];
        return dec;
    }

    public void add(int n) {
        if (n >= 0 && n < this.length) {
            int byteIndex = n / 8;
            boolean[] bin = toBinary(this.data[byteIndex]);
            bin[n % 8] = true;
            this.data[byteIndex] = toDecimal(bin);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void add(int[] numbers) {
        for (int n : numbers)
            if (n >= 0 && n < this.length) {
                int byteIndex = n / 8;
                boolean[] bin = toBinary(this.data[byteIndex]);
                bin[n % 8] = true;
                this.data[byteIndex] = toDecimal(bin);
            } else {
                throw new IllegalArgumentException();
            }
    }

    public void remove(int n) {
        if (n >= 0 && n < this.length) {
            int byteIndex = n / 8;
            boolean[] bin = toBinary(this.data[byteIndex]);
            bin[n % 8] = false;
            this.data[byteIndex] = toDecimal(bin);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void remove(int[] numbers) {
        for (int n : numbers)
            if (n >= 0 && n < this.length) {
                int byteIndex = n / 8;
                boolean[] bin = toBinary(this.data[byteIndex]);
                bin[n % 8] = false;
                this.data[byteIndex] = toDecimal(bin);
            } else {
                throw new IllegalArgumentException();
            }
    }

    public boolean contains(int n) {
        if (n >= 0 && n < this.length)
            return toBinary(this.data[n / 8])[n % 8];
        else
            throw new IllegalArgumentException();
    }

    public void print() {
        for (byte b : this.data) {
            for (boolean bit : toBinary(b))
                System.out.print(bit ? "1" : "0");
            System.out.print(".");
        }
        System.out.println();
    }

    public void difference() {
        for (int i = 0; i < this.data.length; i++) {
            this.data[i] = (byte) (-this.data[i] - (byte) 1);
        }
    }


    public Bitset union(Bitset bitset) {
        Bitset result;
        if (this.length > bitset.length) {
            result = this;
            for (int i = 0; i < bitset.data.length; i++)
                result.data[i] = (byte) (((result.data[i] + 128) | (bitset.data[i] + 128)) - 128);
        } else {
            result = bitset;
            for (int i = 0; i < this.data.length; i++)
                result.data[i] = (byte) (((result.data[i] + 128) | (this.data[i] + 128)) - 128);
        }
        return result;
    }

    public Bitset intersect(Bitset bitset) {
        Bitset result;
        if (bitset.length < this.length) {
            result = bitset;
            for (int i = 0; i < bitset.data.length; i++)
                result.data[i] = (byte) (((result.data[i] + 128) & (this.data[i] + 128)) - 128);
        } else {
            result = this;
            for (int i = 0; i < this.data.length; i++)
                result.data[i] = (byte) (((result.data[i] + 128) & (bitset.data[i] + 128)) - 128);
        }
        return result;
    }

    public Iterator<Boolean> iterator() {
        return new Iterator<>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < data.length;
            }

            @Override
            public Boolean next() {
                return contains(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
