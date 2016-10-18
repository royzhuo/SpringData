package com.spring;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author roy.zhuo
 */
/*
* 1.Repository是个空接口
* 2.可以通过get find 关键字来执行查询语句
* 3.必须要有数据字段，第一个必须是大写
* 4.@RepositoryDefinition(domainClass = Student.class, idClass = Integer.class)可以通过这个注解来注入Repository对象
*
* */
@RepositoryDefinition(domainClass = Student.class, idClass = Integer.class)
public interface StudentRepository /*extends Repository<Student, Integer>*/ {

    Student getStudentByName(String name);


    //1.通过query注解来进行查询测试 占位符
    @Query("from Student where id=?")
    Student getStudentWithId(Integer id);

    //模糊查询 占位符
    //@Query("from Student where name like ?1% or address like ?2% ") 可以在sql上用％号
    @Query("from Student where name like ?1 or address like ?2 ")
    Student getStudentMoHu1(String name, String address);

    //模糊插叙 以命名参数方式
    @Query("from Student where name like %:name% and address like %:address%")
    List<Student> getStudentMohu2(@Param("address") String address, @Param("name") String name);

    //query支持本地化sql
    @Query(value = "select count(id) from Student ", nativeQuery = true)
    int getStudentSize();

    /*可以使用jpql进行update delete 但不支持 insert
    * 要加Modifying注解
    * 要加事物*/
    @Modifying
    @Query("update Student set email=:email where id=:id")
    void upateStudentByJPQL(@Param("id") Integer id, @Param("email") String email);
}