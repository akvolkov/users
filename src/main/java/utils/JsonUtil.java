package utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.User;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * utility class Json
 */
public final class JsonUtil {
    /**
     * write users to file
     * @param path - file path
     * @param users - list of users
     * @exception  IOException  If an I/O error occurs
     */
    public static void toJSON(String path, List<User> users) {
        try {
            new ObjectMapper().writerWithDefaultPrettyPrinter().writeValue(new File(path), users);
        } catch (IOException e) {
            System.out.println("error writing to file");
            e.printStackTrace();
        }
    }

    /**
     * read users to JavaObjects
     * @param path - file path
     * @return list of users
     * @exception IOException  If an I/O error occurs
     */
    public static List<User> toJavaObjects(String path) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return Arrays.asList(mapper.readValue(new File(path), User[].class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
