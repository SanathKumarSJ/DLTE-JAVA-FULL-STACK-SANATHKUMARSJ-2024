package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class UserFileRepository implements UserRepository {
    private String filePath;
    private ResourceBundle resourceBundle = ResourceBundle.getBundle("user");
    private static Logger logger= LoggerFactory.getLogger(UserFileRepository.class);
    private List<User> userList = new ArrayList<>();

    public UserFileRepository(String url) {
        filePath = url;
        //System.setProperty("system.output.ansi.enabled", "always");
    }

    private void writeIntoFile() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(filePath));
            objectOutputStream.writeObject(userList);
            //System.out.println(userList.toString());
            objectOutputStream.close();
            fileOutputStream.close();

        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            System.out.println(e);
        } catch (IOException e) {
//            e.printStackTrace();
//            System.out.println(e);
        }
    }

    private void readFromFile() {
        //System.out.println("reading object--entry");

        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            userList = (List<User>) objectInputStream.readObject();
            //System.out.println("reading object--exit");
            objectInputStream.close();
            fileInputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void save(User user) {
        readFromFile();
        User object = userList.stream().filter(each -> each.getUsername().equals(user.getUsername())).findFirst().orElse(null);
        if (object != null) {
            logger.info(user.getUsername()+" " + resourceBundle.getString("user.exist"));
            throw new UserException();
        }
        userList.add(user);
        writeIntoFile();
        logger.info(user.getUsername()+" " + resourceBundle.getString("user.saved"));
        //System.out.println(user.getUserName()+" " + resourceBundle.getString("user.saved"));


    }

    @Override
    public User findById(String username) {
        readFromFile();
        //System.out.println("inside find after read");
        User object = userList.stream().filter(each -> each.getUsername().equals(username)).findFirst().orElse(null);
//        System.out.println(object);
        if (object == null) {
            logger.info(username+" " + resourceBundle.getString("user.notExists"));
            //System.out.println(username + " " + resourceBundle.getString("user.notExists"));
            throw new UserException();
        }
//        logger.info(username+" " + resourceBundle.getString("user.exist.ok"));
        return object;
    }

    @Override
    public User findByName(String username) {
        System.out.println(username);
        readFromFile();
        System.out.println("after file");
        User object = userList.stream().filter(each -> each.getUsername().equals(username)).findFirst().orElse(null);
//        System.out.println(object.getUserName());
        if (object==null) {
            System.out.println("INSIDE");
            logger.info( username+" " + resourceBundle.getString("user.Exists"));
            System.out.println(username + " " + resourceBundle.getString("user.Exists"));
            throw new UserException();
        }
        System.out.println("User NOT exist");
        return object;
    }
}
