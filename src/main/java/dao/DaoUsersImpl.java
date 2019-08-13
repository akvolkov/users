package dao;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import entities.User;
import utils.JsonUtil;

import java.util.*;

public class DaoUsersImpl implements DaoUsers {
    final String PATH = "src/main/resources/users.json";

    /**
     * add user in json file
     * @param firstName - username
     * @param secondName - last name of user
     * @param email - email of user
     * @param roles - roles of user
     * @param phones - phones of user
     */
    public void addUser(String firstName, String secondName, String email, String[] roles, String[] phones) {
        List<User> list = getUsers();
        List<User> users = list == null ? new ArrayList<>() : new ArrayList<>(getUsers());
        User user = new User(firstName, secondName, email, roles, phones);
        users.add(user);
        JsonUtil.toJSON(PATH, users);
    }

    /**
     * get user
     * @param email - email of user
     * @return user
     */
    public User getUser(String email) {
        List<User> users = getUsers();
        for (User user: users
             ) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * get users
     * @return list of users
     */
    public List<User> getUsers() {
        List<User> users;
        try {
            users = JsonUtil.toJavaObjects(PATH);
        } catch (Exception e) {
            System.out.println("");
            e.printStackTrace();
            return null;
        }
        return users;
    }

    /**
     * edit User
     * @param email - exist Email
     * @param params - parametrs for updating
     */
    public void updateUser(String email, Map<String, String> params) {
        List<User> users = getUsers();
        for (User user: users
        ) {
            if (user.getEmail().equals(email)) {
                if (!params.get("firstName").equals("")) {
                    user.setFirstName(params.get("firstName"));
                }
                if (!params.get("secondName").equals("")) {
                    user.setSecondName(params.get("secondName"));
                }
                if (!params.get("email").equals("")) {
                    user.setEmail(params.get("email"));
                }
                if (!params.get("phones").equals("")) {
                    user.setPhones(params.get("phones").split(", "));
                }
                if (!params.get("roles").equals("")) {
                    user.setRoles(params.get("roles").split(", "));
                }
            }
        }
        JsonUtil.toJSON(PATH, users);
    }

    /**
     * to remove User
     * @param email - exist Email
     */
    public void remove(String email) {
        List<User> users = new ArrayList<>(getUsers());
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User next = iterator.next();
            if (next.getEmail().equals(email)) {
                iterator.remove();
            }
        }
        JsonUtil.toJSON(PATH, users);
    }
}
