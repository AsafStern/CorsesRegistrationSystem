package bgu.spl.net.api;

import java.util.HashSet;
import java.util.Set;


public class User {
    public enum Permission{
        student,
        admin
    }

    private String username;
    private String password;
    private Set<String> courses;
    private boolean loggedIn;
    private Permission permission;
    private boolean shouldTerminate;

    public User(String username, String password){
        this.username = username;
        this.password = password;
        courses = new HashSet<>();
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }


    public void logIn() {
        shouldTerminate = false;
        this.loggedIn = true;
    }

    public Set<String> getCourses() {
        return courses;
    }

    public void AddCourse(String courseNum){
        courses.add(courseNum);
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void Terminate() {
        this.loggedIn = false;
        this.shouldTerminate = true;
    }

    public boolean ShouldTerminate() {
        return shouldTerminate;
    }

    @Override
    public String toString() {
        return username;
//        return "User{" +
//                "username='" + username + '\'' +
//                ", password='" + password + '\'' +
//                "\ncourses=" + courses +
//                "\nloggedIn=" + loggedIn +
//                ", permission=" + permission +
//                ", shouldTerminate=" + shouldTerminate +
//                "}\n";
    }
}
