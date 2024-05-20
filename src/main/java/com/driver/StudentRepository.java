package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {

    private HashMap<String, Student> studentMap;
    private HashMap<String, Teacher> teacherMap;
    private HashMap<String, List<String>> teacherStudentMapping;

    public StudentRepository(){
        this.studentMap = new HashMap<String, Student>();
        this.teacherMap = new HashMap<String, Teacher>();
        this.teacherStudentMapping = new HashMap<String, List<String>>();
    }

    public void saveStudent(Student student){
        if(student!=null)
            studentMap.put(student.getName(), student);
        // your code goes here
    }

    public void saveTeacher(Teacher teacher){
        if(teacher!=null)
            teacherMap.put(teacher.getName(), teacher);
        // your code goes here
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            if(!teacherStudentMapping.containsKey(teacher)) {
                teacherStudentMapping.put(teacher, new LinkedList<>());
            }
            teacherStudentMapping.get(teacher).add(student);
            // your code goes here
        }
    }

    public Student findStudent(String student){

        return studentMap.get(student);
        // your code goes here
    }

    public Teacher findTeacher(String teacher){

        return teacherMap.get(teacher);
        // your code goes here
    }

    public List<String> findStudentsFromTeacher(String teacher){

        return teacherStudentMapping.get(teacher);
        // your code goes here
        // find student list corresponding to a teacher
    }

    public List<String> findAllStudents(){
        List<String> list = new LinkedList<>();
        for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
        // your code goes here
    }

    public void deleteTeacher(String teacher){
        teacherMap.remove(teacher);
        for(String student:teacherStudentMapping.get(teacher)) {
            if(studentMap.containsKey(student))
                studentMap.remove(student);
        }
        teacherStudentMapping.remove(teacher);

        // your code goes here
    }

    public void deleteAllTeachers(){
        teacherMap.clear();
        for(String teacher : teacherStudentMapping.keySet()) {
            for(String student:teacherStudentMapping.get(teacher)) {
                if(studentMap.containsKey(student))
                    studentMap.remove(student);
            }

        }
        teacherStudentMapping.clear();
        // your code goes here
    }
}