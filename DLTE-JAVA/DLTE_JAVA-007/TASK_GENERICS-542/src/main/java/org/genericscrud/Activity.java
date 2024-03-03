package org.genericscrud;

public interface Activity<T> {
    void create(T obj);
    T read(int transactionID);
    void update(T obj);
    void delete(int transactionID,T obj);


}
