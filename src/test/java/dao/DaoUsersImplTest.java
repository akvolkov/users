package dao;

import entities.User;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class DaoUsersImplTest {
    private final DaoUsersImpl daoUsers = new DaoUsersImpl();
    private String firstName = "Aleksey";
    private String secondName = "Volkov";
    private String email = "akvolkov@mail.ru";
    private String[] roles = {"developer"};
    private String[] phones = {"37500 1234567"};

    @Test
    public void addAndGetUser() {
        User expectedUser = new User(firstName, secondName, email, roles, phones);
        daoUsers.addUser(firstName, secondName, email, roles, phones);
        User actualUser = daoUsers.getUser("akvolkov@mail.ru");
        Assert.assertEquals(expectedUser, actualUser);
        daoUsers.remove(email);
    }

    @Test
    public void getUsers() {
        final int expectedSize = daoUsers.getUsers().size();
        daoUsers.addUser(firstName, secondName, email, roles, phones);
        final int actualSize = daoUsers.getUsers().size();
        Assert.assertEquals(expectedSize, actualSize - 1);
        daoUsers.remove(email);
    }

    @Test
    public void updateUser() {
        User expectedUser = new User("Alex", secondName, email, roles, phones);
        daoUsers.addUser(firstName, secondName, email, roles, phones);
        HashMap<String, String> map = new HashMap<>();
        map.put("firstName", "Alex");
        map.put("secondName", "");
        map.put("email", "");
        map.put("roles", "");
        map.put("phones", "");
        daoUsers.updateUser("akvolkov@mail.ru", map);
        User actualUser = daoUsers.getUser("akvolkov@mail.ru");
        Assert.assertEquals(expectedUser, actualUser);
        daoUsers.remove(email);
    }

    @Test
    public void remove() {
        User expectedUser = new User(firstName, secondName, email, roles, phones);
        daoUsers.addUser(firstName, secondName, email, roles, phones);
        User actualUser = daoUsers.getUser("akvolkov@mail.ru");
        Assert.assertEquals(expectedUser, actualUser);
        daoUsers.remove(email);
    }
}