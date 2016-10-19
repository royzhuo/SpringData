package com;

import com.spring.Student;
import com.spring.StudentRepository;
import com.spring.StudentService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;
import java.util.ArrayList;
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

    //为特定Repository扩展自定义方法
    @Test
    public void testStudentRepositoryMethod() {
        studentRepository.test();
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

    @Test
    public void testAddStudent() {
        List<Student> students = new ArrayList<Student>();
        for (int i = 'a'; i < 'z'; i++) {
            Student student = new Student();
            student.setName((char) i + (char) i + "");
            student.setEmail((char) i + (char) i + "@qq.com");
            student.setAddress((char) i + 1 + "");
            students.add(student);


        }
        studentService.savePersons(students);
    }

    @Test
    public void testPagingAndSortingRepository() {
        //分页
        int pageNow = 0; //当前页 从0开始，所以要减一
        int pageSize = 3;
        Sort.Order order = new Sort.Order(Sort.Direction.DESC, "email");
        Sort.Order order1 = new Sort.Order(Sort.Direction.DESC, "name");
        Sort sort = new Sort(order, order1);
        PageRequest pageRequest = new PageRequest(pageNow, pageSize, sort);
        Page<Student> page = studentService.pageingAndSortingRepository(pageRequest);
        System.out.println("所有的原素:" + page.getTotalElements());
        System.out.println("当前页的个数:" + page.getNumberOfElements());
        System.out.println("总共页数:" + page.getTotalPages());
        System.out.println("当前页:" + (page.getNumber() + 1));
        System.out.println("当前页的原素" + page.getContent());
        System.out.println("获取原素的个数" + page.getSize());
    }

    @Test
    public void testJpaSpecificationExecutor() {
        int pageNow = 0; //当前页 从0开始，所以要减一
        int pageSize = 3;
        PageRequest pageRequest = new PageRequest(pageNow, pageSize);
        Specification<Student> studentSpecification = new Specification<Student>() {
            /*root:查询实体类对象
            *criteriaQuery:查询条件
             *CriteriaBuilder 可以创建Predicate  */
            @Override
            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Path path = root.get("id");
                Predicate predicate = criteriaBuilder.gt(path, 5);
                return predicate;
            }
        };
        Page<Student> page = studentRepository.findAll(studentSpecification, pageRequest);
        System.out.println("所有的原素:" + page.getTotalElements());
        System.out.println("当前页的个数:" + page.getNumberOfElements());
        System.out.println("总共页数:" + page.getTotalPages());
        System.out.println("当前页:" + (page.getNumber() + 1));
        System.out.println("当前页的原素" + page.getContent());
        System.out.println("获取原素的个数" + page.getSize());
    }


}
