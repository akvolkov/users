package utils;

import entities.User;
import services.UserService;

import java.util.List;

/**
 * utility class with user checks
 */
public final class ChecksUtil {
    /**
     * length check {#strings}
     * @param strings - array of phones or roles
     * @return {@code true} if length from 1 to 3, else {@code false}
     */
    public static boolean lengthCheck(String[] strings) {
        if (strings.length <= 3 && strings.length > 0) {
            return true;
        } else {
            System.out.println("Invalid number of entries");
            return false;
        }
    }

    /**
     * checking phone number.
     * it should be: starts with "375", consist of 2 parts separated by space, length of first part = 5, length of second part = 7, parts are numbers
     * @param strings - array of phones or roles
     * @return {@code true} if phone number is valid else {@code false}
     */
    public static boolean phonesValidation(String[] strings) {
        boolean isValidation = true;
        for (String string : strings) {
            // check starts with "375"
            isValidation = isValidation && string.startsWith("375");
            String[] parts = string.split(" ");
            // consist of 2 parts separated by space
            boolean checkParts = parts.length == 2;
            isValidation = isValidation && checkParts;
            if (checkParts) {
                // length of first part = 5
                isValidation = isValidation && parts[0].length() == 5;
                // length of second part = 7
                isValidation = isValidation && parts[1].length() == 7;
                // first part are numbers
                isValidation = isValidation && parts[0].matches("^-?\\d+$");
                // second part are numbers
                isValidation = isValidation && parts[1].matches("^-?\\d+$");
            }
            if (!isValidation) {
                System.out.println("Unexpected value: " + string);
                return false;
            }
        }
        return true;
    }

    /**
     * checking email.
     * it should be: availability @ and point
     * @param email - E-mail address
     * @return {@code true} if email is valid else {@code false}
     */
    public static boolean emailValidation(String email) {
        String reg = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        boolean matches = email.matches(reg);
        if (!matches) {
            System.out.println("Unexpected value: " + email);
        }
        return matches;
    }

    /**
     * existence email in file
     * @param email - email for check
     * @return {@code true} if email existence else {@code false}
     */
    public static boolean emailExistence(String email) {
        List<User> users = new UserService().getUsers();
        for (User user: users
             ) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        System.out.println("Email not found");
        return false;
    }
}
