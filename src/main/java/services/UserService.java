package services;

import dao.DaoUsersImpl;
import entities.User;
import utils.ChecksUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * user entity services
 */
public class UserService {
    private DaoUsersImpl daoUsers = new DaoUsersImpl();
    private final String ENTER_ROLES_AND_PHONES = "Enter %s through \", \". For example: %s";

    /**
     * user creation
     * @param reader - object of bufferedReader
     */
    public void createUser(BufferedReader reader) {
        try {
            daoUsers.addUser(
                    readName(reader, "create user> First Name: "),
                    readName(reader, "create user> Second Name: "),
                    readEmail(reader, "create"),
                    readRoles(reader, "create").split(", "),
                    readPhones(reader, "create").split(", ")
            );
        } catch (IOException e) {
            System.out.println("I/O error occurs");
            e.printStackTrace();
        }
    }

    /**
     * edit user
     * @param reader - object of bufferedReader
     */
    public void editUser(BufferedReader reader) {
        try {
            String existEmail = "";
            boolean isExistEmail = false;
            while (!isExistEmail) {
                existEmail = readEmail(reader, "edit");
                isExistEmail = ChecksUtil.emailExistence(existEmail);
            }
            System.out.println("Enter parametrs editable user. If parameter does not change, then do not enter anything.");
            HashMap<String, String> param = new HashMap<String, String>();
            param.put("firstName", readName(reader, "edit user> First Name: "));
            param.put("secondName", readName(reader, "edit user> Second Name: "));
            param.put("email", readEmail(reader, "edit"));
            param.put("phones", readPhones(reader, "edit"));
            param.put("roles", readRoles(reader, "edit"));
            daoUsers.updateUser(existEmail, param);
        } catch (IOException e) {
            System.out.println("I/O error occurs");
            e.printStackTrace();
        }
    }

    /**
     * get users from json file
     * @return list of users
     */
    public List<User> getUsers() {
        return daoUsers.getUsers();
    }

    /**
     * read email from console
     * @param reader - object of bufferedReader
     * @param operation - method from which is called
     * @return email
     * @throws IOException - I/O error occurs
     */
    private String readEmail(BufferedReader reader, String operation) throws IOException {
        boolean isCorrectEmail = false;
        String email = "";
        while (!isCorrectEmail) {
            System.out.print(operation + " user> Email: ");
            email = reader.readLine();
            isCorrectEmail = ((operation.equals("edit") || operation.equals("remove")) && email.equals("")) || ChecksUtil.emailValidation(email);
        }
        return email;
    }

    /**
     * read roles from console
     * @param reader - object of bufferedReader
     * @param operation - method from which is called
     * @return roles as string
     * @throws IOException - I/O error occurs
     */
    private String readRoles(BufferedReader reader, String operation) throws IOException {
        boolean isCorrectRoles = false;
        String roles = "";
        while (!isCorrectRoles) {
            System.out.println(String.format(ENTER_ROLES_AND_PHONES, "roles", "tester, developer, admin"));
            System.out.print(operation + " user> Roles: ");
            roles = reader.readLine();
            isCorrectRoles = ChecksUtil.lengthCheck(roles.split(", "));
        }
        return roles;
    }

    /**
     * read phones from console
     * @param reader - object of bufferedReader
     * @param operation - method from which is called
     * @return phones as string
     * @throws IOException - I/O error occurs
     */
    private String readPhones(BufferedReader reader, String operation) throws IOException {
        boolean isCorrectPhones = false;
        String phones = "";
        while (!isCorrectPhones) {
            System.out.println(String.format(ENTER_ROLES_AND_PHONES, "phones", "37500 1234567, 37501 2345678, 37502 3456789"));
            System.out.print(operation + " user> Phones: ");
            phones = reader.readLine();
            isCorrectPhones = (operation.equals("edit") && phones.equals("")) ||
                    (ChecksUtil.lengthCheck(phones.split(", ")) && ChecksUtil.phonesValidation(phones.split(", ")));
        }
        return phones;
    }

    /**
     * read First name or Second name
     * @param reader - object of bufferedReader
     * @param text string for console output
     * @return First name or Second name
     * @throws IOException - I/O error occurs
     */
    private String readName (BufferedReader reader, String text) throws IOException {
        System.out.print(text);
        return reader.readLine();
    }

    /**
     * remove user
     * @param reader - object of bufferedReader
     * @throws IOException - I/O error occurs
     */
    public void removeUser(BufferedReader reader) throws IOException {
        daoUsers.remove(readEmail(reader, "remove"));
    }

    /**
     * get user
     * @param reader - object of bufferedReader
     * @throws IOException - I/O error occurs
     */
    public void getUser(BufferedReader reader) throws IOException {
        User user = daoUsers.getUser(readEmail(reader, "get"));
        if (user != null) {
            System.out.println(user.toString());
        } else {
            System.out.println("user with this email does not exist");
        }
    }
}
