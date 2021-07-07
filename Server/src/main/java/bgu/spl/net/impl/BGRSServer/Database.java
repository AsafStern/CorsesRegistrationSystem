package bgu.spl.net.impl.BGRSServer;


import bgu.spl.net.api.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Passive object representing the Database where all courses and users are stored.
 * <p>
 * This class must be implemented safely as a thread-safe singleton.
 * You must not alter any of the given public methods of this class.
 * <p>
 * You can add private fields and methods to this class as you see fit.
 */
public class Database {
    private HashMap<String, Course> courses;  // key: courseNum val: course
    private ConcurrentHashMap<String, User> users; // Todo: changed to concurrent

    public User logIn(String username, String password) {
        if(!users.containsKey(username))
            return null;
        User u = users.get(username);
        if(!u.getPassword().equals(password) || u.isLoggedIn())
            return null;
        u.logIn();
        return u;
    }

    public boolean logOut(String username) {
        if (!users.containsKey(username))
            return false;
        users.get(username).Terminate();
        return true;
    }

    public boolean regCourse(String courseId, User u) {
        // either course isnt exist (V), no room in course (V), not reg to all Kdams , not logged in (V)
        if(!users.containsKey(u.getUsername()) || !u.isLoggedIn())
            return false;

        if (!courses.containsKey(courseId)) return false; // course found
        Course course = courses.get(courseId);

        Set<String> userCourses = u.getCourses();
        Set<String> kdams = course.getKdams();
        for(String courseNum: kdams){ // check if he/she has all kdams
            if(!userCourses.contains(courseNum))
                return false;
        }
        return course.Register(u); // register the user and updating the num of registered, return true if there's room, else return false.

    }

    public List<String> getKdams(String courseNum){
        if (!courses.containsKey(courseNum))
            return null;
        Set<String> kdams = courses.get(courseNum).getKdams();
        return orderCourses(kdams);
    }

    public List<String> orderCourses(Set<String> unOrdered){
        List<String> ordered = new ArrayList<>();
        for (String cNum: courses.keySet()){
            if(unOrdered.contains(cNum))
                ordered.add(cNum);
        }
        return ordered;
    }

    public String getCourseStat(String courseNum) {
        if(!courses.containsKey(courseNum))
            return null;
        Course course = courses.get(courseNum);
        StringBuilder sb = new StringBuilder();
        sb.append("Course: (" + courseNum + ") ");
        sb.append(course.getName());
        sb.append("\nSeats Available: " + course.AvailableSeats() + "/" + course.getMaxStud());
        sb.append("\nStudents Registered: ");
        List<User> students = course.getStudents();
        if (!students.isEmpty())
            Collections.sort(students, Comparator.comparing(User::getUsername));
        sb.append(students.toString());
        return sb.toString();
    }

    public boolean isExist(String courseNum) {
        if(!courses.containsKey(courseNum))
            return false;
        return true;
    }

    public boolean unRegister(String courseNum, User u) {
        if(!courses.containsKey(courseNum))
            return false;
        return courses.get(courseNum).unRegister(u);
    }
    public User getUser(String username){
        if(!users.containsKey(username))
            return null;
        return users.get(username);
    }

    public boolean Registerd(String name, String password, User.Permission permission){
        if (users.containsKey(name))
            return false;
        User u = new User(name,password);
        u.setPermission(permission);
        users.put(name, u);
        return true;
    }

    @Override
    public String toString() {
        return "Database{\n" +
                "users=\n" + users +
                "}\n";
    }

    private static class DatabaseHolder{
        private static  Database instance = new Database();
    }

    //to prevent user from creating new Database
    private Database() {
        // TODO: implement
        boolean initialized = initialize("./Courses.txt");
        if (!initialized) {
            System.out.println("couldn't load file");
            return;
        }
    }

    /**
     * Retrieves the single instance of this class.
     */
    public static Database getInstance() {
        return DatabaseHolder.instance;
    }

    /**
     * loads the courses from the file path specified
     * into the Database, returns true if successful.
     */
    boolean initialize(String coursesFilePath) {
//		users = new HashMap<>();
        users = new ConcurrentHashMap<>();
        courses = new LinkedHashMap<>(); // LinkedHashMap maintains the order.
        try(Scanner scanner = new Scanner(new File(coursesFilePath));){
            scanner.useDelimiter("\n");
            while(scanner.hasNext()) {
                Course course = new Course(scanner.next());
                courses.put(course.getNum(), course);
            }
            return true;
        }catch(FileNotFoundException e ){e.printStackTrace();}
        return false;
    }
}