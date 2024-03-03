package org.genericscrud;

import java.util.Arrays;

public abstract class MyBankDatabase<T> {
    T[] myObjects;

    // generic method with argument
    public abstract String insertNewRecord(T objects);

    // generic method with return type
    public abstract T read(int index);

    public abstract String delete(int index);

    public abstract void update(int index, T object);

    public void viewAll() {
        System.out.println(Arrays.toString(myObjects));
    }
}
