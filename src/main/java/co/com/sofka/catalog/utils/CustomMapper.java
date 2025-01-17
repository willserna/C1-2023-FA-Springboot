package co.com.sofka.catalog.utils;

import co.com.sofka.catalog.dto.CourseDTO;
import co.com.sofka.catalog.dto.StudentDTO;
import co.com.sofka.catalog.entity.Course;
import co.com.sofka.catalog.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class CustomMapper{

    public static Course course(CourseDTO courseDTO){
        Course c = new Course();
        c.setId(courseDTO.getId());
        c.setName(courseDTO.getName());
        c.setCoach(courseDTO.getCoach());
        c.setLevel(courseDTO.getLevel());
        c.setLastUpdated(courseDTO.getLastUpdated());
        c.setStudentList(courseDTO.getStudentListDTO().stream().map(CustomMapper::student).collect(Collectors.toList()));


        return c;

    }


    public static Student student(StudentDTO studentDTO){
        Student s = new Student();
        s.setId(studentDTO.getId());
        s.setName(studentDTO.getName());
        s.setIdNum(studentDTO.getIdNum());
        s.setAge(studentDTO.getAge());
        s.setMail(studentDTO.getMail());
        s.setCourse(course(studentDTO.getCourse()));
        //s.setNumCourses(studentDTO.getNumCourses());

        return s;

    }


    public static CourseDTO courseDTO(Course course){
        CourseDTO c = new CourseDTO();
        c.setId(course.getId());
        c.setName(course.getName());
        c.setCoach(course.getCoach());
        c.setLevel(course.getLevel());
        c.setLastUpdated(course.getLastUpdated());
        List<StudentDTO> studentListDTO = new ArrayList<>();
        for(Student student : course.getStudentList()){
            studentListDTO.add(nestStudent(student));
        }
        c.setStudentListDTO(studentListDTO);
        //c.setStudentListDTO(course.getStudentList().stream().map(CustomMapper::studentDTO).collect(Collectors.toList()));

        return c;

    }

    private static CourseDTO nestCourse(Course course){
        CourseDTO cs = new CourseDTO();
        cs.setId(course.getId());
        cs.setName(course.getName());
        cs.setCoach(course.getCoach());
        cs.setLevel(course.getLevel());
        cs.setLastUpdated(course.getLastUpdated());
        //cs.setStudentListDTO(course.getStudentList());
        return cs;

    }


    public static StudentDTO studentDTO(Student student){
        StudentDTO s = new StudentDTO();
        s.setId(student.getId());
        s.setName(student.getName());
        s.setIdNum(student.getIdNum());
        s.setAge(student.getAge());
        s.setMail(student.getMail());
        CourseDTO courseDTO = new CourseDTO();
        courseDTO = nestCourse(student.getCourse());
        s.setCourse(courseDTO);
        //s.setCourse(courseDTO(student.getCourse()));

        //s.setNumCourses(student.getNumCourses());

        return s;

    }

    private static StudentDTO nestStudent(Student student) {
        StudentDTO s = new StudentDTO();
        s.setId(student.getId());
        s.setName(student.getName());
        s.setIdNum(student.getIdNum());
        s.setAge(student.getAge());
        s.setMail(student.getMail());

        return s;


    }


}
