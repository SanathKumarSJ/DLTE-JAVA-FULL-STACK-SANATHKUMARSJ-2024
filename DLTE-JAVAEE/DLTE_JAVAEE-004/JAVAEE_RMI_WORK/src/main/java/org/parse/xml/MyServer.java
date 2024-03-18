package org.parse.xml;

import org.database.DatabaseTarget;
import org.database.StorageTarget;
import org.database.User;
import org.database.UserServices;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

public class MyServer extends UnicastRemoteObject implements MyInterface, Serializable {
    private static Context context;
    private Registry registry;
    private UserServices userServices;


    @Override
    public List<String> fetchByBalance() throws RemoteException{
        List<String> namelist=new ArrayList<>();
        List<User> userList=userServices.callFindAll().stream().filter(each-> each.getBalance()>5000.00).collect(Collectors.toList());
        for (User iterate:userList){
            namelist.add(iterate.getUsername());
        }
        return namelist;

    }


    public MyServer() throws RemoteException{
        super();
        userServices= new UserServices(new DatabaseTarget());
        try {
            registry = LocateRegistry.createRegistry(3030);
            Hashtable properties = new Hashtable();
            properties.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.rmi.registry.RegistryContextFactory");
            properties.put(Context.PROVIDER_URL, "rmi://localhost:3030");
            context=new InitialContext(properties);
        } catch (RemoteException | NamingException e) {
            e.printStackTrace();
        }
    }

    //binding
    public static void main(String[] args) throws NamingException, RemoteException {
        MyServer myServer=new MyServer();
        context.bind("java:/user-balance",myServer);
    }
}
