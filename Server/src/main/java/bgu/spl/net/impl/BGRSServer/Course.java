package bgu.spl.net.impl.BGRSServer;

import bgu.spl.net.api.User;

import java.util.*;

public class Course {
    private String num;
    private String name;
    private Set<String> kdams;
    private int maxStud;
    private int registered = 0;
    private List<User> students; // been added for COURSESTAT

    public Course(String all){
        String[] parts = all.trim().split("\\|");
        num = parts[0];
        name = parts[1];
        kdams = new HashSet<>(Arrays.asList(parts[2].substring(1,parts[2].length() - 1).split(",")));
        kdams.remove(""); // Todo: not elegant, the problem was that it saved "" as well.
        maxStud = Integer.parseInt(parts[3]);
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getMaxStud() {
        return maxStud;
    }

    public String getNum() {
        return num;
    }

    public Set<String> getKdams() {
        return kdams;
    }

    public synchronized boolean Register(User user) {
        if (registered == maxStud || students.contains(user))
            return false;
        registered++;
        user.AddCourse(num);
        students.add(user);
        return true;
    }

    public synchronized boolean unRegister(User user) {
        if(!students.contains(user))
            return false;
        students.remove(user);
        registered--;
        return true;
    }

    public int AvailableSeats() {
        return maxStud - registered;
    }

    public List<User> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "Course{" +
                "num='" + num + '\'' +
                ", name='" + name + '\'' +
                ", kdams=" + kdams +
                ", maxStud=" + maxStud +
                ", registered=" + registered +
                ", students=" + students +
                '}';
    }
}
