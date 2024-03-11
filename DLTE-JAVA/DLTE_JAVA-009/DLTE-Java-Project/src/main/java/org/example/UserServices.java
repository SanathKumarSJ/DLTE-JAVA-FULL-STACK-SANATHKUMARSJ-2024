package org.example;

import jdk.jfr.consumer.RecordedObject;


public class UserServices {
    UserFileRepository userFileRepository;

    public UserServices(StorageTarget storageTarget) {
        userFileRepository = storageTarget.getUserFileRepository();
    }

    public void callSave(User user) {
        try {
            userFileRepository.save(user);
        } catch (UserException userException) {
            throw userException;
        }
    }

    public User callFindById(String username) {
        try {
            return userFileRepository.findById(username);
        } catch (UserException userException) {
            throw userException;
        }
    }

    public User callFindByName(String username) {
        try {
            System.out.println("service entered");
            return userFileRepository.findByName(username);
        } catch (UserException userException) {
            throw userException;
        }
    }
}
