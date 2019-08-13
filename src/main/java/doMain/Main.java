package doMain;

import services.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        final String SEPARATOR = "===============================================================================" +
                "===================================";
        final String WELCOME = "Welcome to Primitive console application for creating / editing / viewing users and saving changes to a file!\n" +
                "It has the ability to create user with parameters: first name, last name, email, roles, phones and save it to a file.\n" +
                "Number of roles and phones from 1 to 3.\n" +
                "It has the ability to edit user.\n" +
                "It has the ability to remove user.\n" +
                "It has the ability to get information about the user, his roles and phones. \n" +
                "Enter \"help\" to get help.\n" + SEPARATOR;
        final String HELP = "Enter \"create\" to create User.\n" +
                "Enter \"edit\" to edit User.\n" +
                "Enter \"remove\" to remove User.\n" +
                "Enter \"get\" to get information about User, his roles and phones.\n" +
                "Enter \"exit\" to exit application.\n" + SEPARATOR;
        System.out.println(WELCOME);
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            boolean isExit = false;
            while (!isExit) {
                System.out.println("Enter command!");
                System.out.print("> ");
                String line = reader.readLine();
                final UserService service = new UserService();
                switch (line) {
                    case "exit":
                        isExit = true;
                        break;
                    case "help":
                        System.out.println(HELP);
                        break;
                    case "create":
                        service.createUser(reader);
                        break;
                    case "edit":
                        service.editUser(reader);
                        break;
                    case "remove":
                        service.removeUser(reader);
                        break;
                    case "get":
                        service.getUser(reader);
                        break;
                    default:
                        System.out.println("Unexpected value: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
