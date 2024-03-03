package org.genericfiles;

import java.util.Arrays;

public class MyBankDatabase<T> implements Activity{
    T[] myObjects;

    public void viewAll() {
        System.out.println(Arrays.toString(myObjects));
    }

    @Override
    public void create(Object obj) {

    }
}
