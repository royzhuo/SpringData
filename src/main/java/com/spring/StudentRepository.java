package com.spring;

import org.springframework.data.repository.Repository;

/**
 * @author roy.zhuo
 */
public interface StudentRepository extends Repository<Student, Integer > {

    Student getStudentByName(String name);
}
