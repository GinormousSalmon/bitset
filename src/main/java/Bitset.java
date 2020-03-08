package main.java;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.*;


public class Bitset<T> implements Iterable<T> {
    private ArrayList<T> data = new ArrayList<>();

    public Bitset() {
    }

    public Bitset(T input) {
        this.add(input);
    }

    public Bitset(T[] input) {
        this.add(input);
    }

    public Bitset(Collection<T> input) {
        this.add(input);
    }

    public boolean contains(T elem) {
        return this.data.contains(elem);
    }

    public void add(T elem) {
        if (!this.data.contains(elem))
            this.data.add(elem);
    }

    public void add(T[] elem) {
        for (T i : elem)
            if (!this.data.contains(i))
                this.data.add(i);
    }

    public void add(Collection<T> elem) {
        for (T i : elem)
            if (!this.data.contains(i))
                this.data.add(i);
    }

    public void remove(T elem) {
        this.data.remove(elem);
    }

    public void remove(T[] elem) {
        for (T i : elem)
            this.data.remove(i);
    }

    public void remove(Collection<T> elem) {
        this.data.removeAll(elem);
    }

    public void intersection(Bitset<T> a) {
        for (T i : new Bitset<T>(this.toList()))
            if (!a.contains(i))
                this.remove(i);
    }

    public void union(Bitset<T> a) {
        for (T i : a)
            if (!this.contains(i))
                this.add(i);
    }

    public ArrayList<T> toList() {
        return this.data;
    }

    @NotNull
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < data.size();
            }

            @Override
            public T next() {
                return data.get(currentIndex++);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
