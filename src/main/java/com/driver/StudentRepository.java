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
        // your code goes here
        if(student!=null){
            studentMap.put(student.getName(),student);
        }
    }

    public void saveTeacher(Teacher teacher){
        // your code goes here
        if(teacher!=null){
            teacherMap.put(teacher.getName(),teacher);
        }
    }

    public void saveStudentTeacherPair(String student, String teacher){
        if(studentMap.containsKey(student) && teacherMap.containsKey(teacher)){
            // your code goes here
            if(!teacherStudentMapping.containsKey(teacher)) {
                teacherStudentMapping.put(teacher, new LinkedList<>());
            }
            teacherStudentMapping.get(teacher).add(student);
        }
    }

    public Student findStudent(String student){
        // your code goes here
        return studentMap.get(student);
    }

    public Teacher findTeacher(String teacher){
        // your code goes here
        return teacherMap.get(teacher);
    }

    public List<String> findStudentsFromTeacher(String teacher){
        // your code goes here
        // find student list corresponding to a teacher
        return teacherStudentMapping.get(teacher);
    }

    public List<String> findAllStudents(){
        // your code goes here
        List<String> list = new LinkedList<>();
        for (Map.Entry<String, Student> entry : studentMap.entrySet()) {
            list.add(entry.getKey());
        }
        return list;
    }

    public void deleteTeacher(String teacher){
        // your code goes here
        teacherMap.remove(teacher);
        for(String student:teacherStudentMapping.get(teacher)) {
            if(studentMap.containsKey(student))
                studentMap.remove(student);
        }
        teacherStudentMapping.remove(teacher);
    }

    public void deleteAllTeachers(){
        // your code goes here

        teacherMap.clear();
        for(String teacher : teacherStudentMapping.keySet()) {
            for(String student:teacherStudentMapping.get(teacher)) {
                if(studentMap.containsKey(student))
                    studentMap.remove(student);
            }

        }
        teacherStudentMapping.clear();
    }
}