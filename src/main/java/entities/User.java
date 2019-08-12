package entities;

import java.util.Arrays;
import java.util.Objects;

public class User {
    private String firstName;
    private String secondName;
    private String email;
    private String[] roles;
    private String[] phones;

    public User() {
    }

    public User(String firstName, String secondName, String email, String[] roles, String[] phones) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
        this.roles = roles;
        this.phones = phones;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getPhones() {
        return phones;
    }

    public void setPhones(String[] phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", email='" + email + '\'' +
                ", roles=" + Arrays.toString(roles) +
                ", phones=" + Arrays.toString(phones) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) &&
                Objects.equals(secondName, user.secondName) &&
                Objects.equals(email, user.email) &&
                Arrays.equals(roles, user.roles) &&
                Arrays.equals(phones, user.phones);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(firstName, secondName, email);
        result = 31 * result + Arrays.hashCode(roles);
        result = 31 * result + Arrays.hashCode(phones);
        return result;
    }
}
