package services;

import dao.ActivityDao;
import dao.GroupDao;
import models.Activity;
import models.Group;
import models.Student;

import java.util.List;


public class GroupService {


    private GroupService(){
    }

    private static GroupService instance = new GroupService();

    public static GroupService getInstance(){
        return instance;
    }

    // single responsiblity



    private GroupDao groupDao = GroupDao.getInstance();
    private StudentService studentService  =  StudentService.getInstance();
    private ActivityService activityService = ActivityService.getInstance();

    public Group getGroup(String groupId){
        return groupDao.findById(groupId);
    }


    public void addGroup(Group group) throws  Exception{
        groupDao.save(group);
    }

    public List<Group> getGroups() throws Exception {
        return groupDao.findAll();
    }



    public void removeStudentFromGroup(int studentId, String groupName){
        Student s = studentService.getStudent(studentId);
        Group g = groupDao.findById(groupName);

        if(g == null ) {
            System.out.println("non valid group id");
            return;
        }

        if(s == null){
            System.out.println("non valid student id");
            return;
        }

        g.removeStudent(s);
        groupDao.save(g);

    }



    public void addStudentToGroup(int studentId, String groupName) {
        Student s = studentService.getStudent(studentId);
        Group g = groupDao.findById(groupName);

        if(g == null ) {
            System.out.println("non valid group id");
            return;
        }

        if(s == null){
            System.out.println("non valid student id");
            return;
        }

        g.addStudent(s);
        groupDao.save(g);
    }

    public void addActivityToGroup(int activityId, String groupName){
        Activity a = activityService.getActivity(activityId);
        Group g = groupDao.findById(groupName);
        g.addActivity(a);
        groupDao.save(g);
    }

    public void removeGroup(String id)  {

       activityService.deleteActivityByGroupId(id);
       studentService.deleteActivityByGroupId(id);
        groupDao.deleteById(id);

    }

}
