package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author roy.zhuo
 */
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Transactional
    public void updateStudentByJPQL(Integer id, String email) {
        studentRepository.upateStudentByJPQL(id, email);
    }
}
