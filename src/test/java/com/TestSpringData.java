package com;

import com.spring.Student;
import com.spring.StudentRepository;
import com.spring.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author roy.zhuo
 */
public class TestSpringData {
    private ApplicationContext applicationContext = null;
    private DataSource dataSource;
    private StudentRepository studentRepository;
    private StudentService studentService;

    {
        applicationContext = new ClassPathXmlApplicationContext("SpringData.xml");
        studentRepository = applicationContext.getBean(StudentRepository.class);
        studentService = applicationContext.getBean(StudentService.class);
    }

    @Test
    public void testSpringData1() {

        Student student = studentRepository.getStudentByName("roy");
        System.out.println(student);
    }

    @Test
    public void testConnectionSuceess() {
        dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSource.getClass());
    }

    @Test
    public void testQuery1() {
        Student student = studentRepository.getStudentWithId(2);
        System.out.println(student);
    }

    @Test
    public void testQuery2() {
        Student student = studentRepository.getStudentMoHu1(null, "z%");
        System.out.println(student);
    }

    @Test
    public void testQuery3() {
        List<Student> students = studentRepository.getStudentMohu2("a", "o");
        System.out.println(students);

    }

    @Test
    public void testQuery4() {
        Integer size = studentRepository.getStudentSize();
        System.out.println(size);
    }

    @Test
    public void testUpdateStudentByJPQL() {
        studentService.updateStudentByJPQL(1, "sdkfjlsfkjcnxx");
    }
}
