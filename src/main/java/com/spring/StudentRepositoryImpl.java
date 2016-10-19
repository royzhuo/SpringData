package com.spring;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author roy.zhuo
 */
//1.接口实现
//2.提供该接口的实现类: 类名需符合EntityNameRepositoryImpl格式, 并提供方法的实现
public class StudentRepositoryImpl implements StudentDao {

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void test() {
        Student student = entityManager.find(Student.class, 2);
        System.out.println(student);
    }
}
