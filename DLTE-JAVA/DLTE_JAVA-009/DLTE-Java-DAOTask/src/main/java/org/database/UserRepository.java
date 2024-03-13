package org.database;

public interface UserRepository {
    void save(User user);
    User findById(String username);
}
