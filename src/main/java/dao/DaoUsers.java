package dao;

import entities.User;

import java.util.List;
import java.util.Map;

public interface DaoUsers {
    void addUser(String firstName, String secondName, String email, String[] roles, String[] phones);
    User getUser(String email);
    List<User> getUsers();
    void updateUser(String email, Map<String, String> params);
    void remove(String email);
}
