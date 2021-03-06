package models;


import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Group {

    private String name;
    private List<Activity> activities;
    private List<Student> students;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Group group = (Group) o;
        return name.equals(group.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(Student s ){
        if(students == null) students = new LinkedList<>();
        students.add(s);
    }

    public void removeStudent(Student s) {
        students.remove(s);
    }


    public void addActivity(Activity activity){
        if(activities == null) activities = new LinkedList<>();
        activities.add(activity);
    }

    @Override
    public String toString() {
        return "Group{" +
                "name='" + name +  '\'' +
                ", students=" + students +
                ", activities=" + activities +
                '}';
    }
}
