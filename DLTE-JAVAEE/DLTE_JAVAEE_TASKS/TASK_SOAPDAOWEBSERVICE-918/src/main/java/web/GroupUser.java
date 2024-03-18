package web;

import org.database.User;

import java.util.List;

public class GroupUser {
    private List<User> userList;

    @Override
    public String toString() {
        return "GroupUser{" +
                "userList=" + userList +
                '}';
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public GroupUser(List<User> userList) {
        this.userList = userList;
    }
}
