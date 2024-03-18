package org.parse.xml;

import org.database.User;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MyInterface extends Remote {
    List<String> fetchByBalance() throws RemoteException;
}
