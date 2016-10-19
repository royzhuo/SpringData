package com.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    @Transactional
    public void savePersons(List<Student> students) {
        studentRepository.save(students);
        
    }

    public Page<Student> pageingAndSortingRepository(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }
}
