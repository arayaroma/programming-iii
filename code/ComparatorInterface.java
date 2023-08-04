package code;

import java.util.Comparator;
import java.util.List;

public class ComparatorInterface {

    public static void main(String[] args) {
        Driver();
    }

    public static void Driver() {
        List<User> users = setFixedUsers();

        System.out.println("Users List before sorting:");
        showUsers(users);

        users.sort(new CompareAge());

        System.out.println("Users list after sorting:");
        showUsers(users);

    }

    public static List<User> setFixedUsers() {
        List<User> users = new java.util.ArrayList<>();
        users.add(new User("John", 25));
        users.add(new User("Adam", 23));
        users.add(new User("Andrew", 24));
        users.add(new User("Peter", 22));
        users.add(new User("Paul", 21));
        return users;
    }

    public static void showUsers(List<User> users) {
        for (User user : users) {
            System.out.println(user.getName() + " " + user.getAge());
        }
        System.out.println("\n");
    }

}

class User {
    private String name;
    private int age;

    public User(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

}

class CompareAge implements Comparator<User> {

    @Override
    public int compare(User arg0, User arg1) {
        return arg0.getAge() - arg1.getAge();
    }

}
