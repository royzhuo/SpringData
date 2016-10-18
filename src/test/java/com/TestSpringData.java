package com;

import com.spring.Student;
import com.spring.StudentRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;

/**
 * @author roy.zhuo
 */
public class TestSpringData {
    private ApplicationContext applicationContext = null;
    private DataSource dataSource;
    private StudentRepository studentRepository;

    {
        applicationContext = new ClassPathXmlApplicationContext("SpringData.xml");
    }

    @Test
    public void testSpringData1() {
        studentRepository = applicationContext.getBean(StudentRepository.class);
        Student student = studentRepository.getStudentByName("roy");
        System.out.println(student);
    }

    @Test
    public void testConnectionSuceess() {
        dataSource = applicationContext.getBean(DataSource.class);
        System.out.println(dataSource.getClass());
    }
}
