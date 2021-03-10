package com.example.jpamybatisplusdemo;

import com.example.jpamybatisplusdemo.entity.Person;
import com.example.jpamybatisplusdemo.entity.Student;
import com.example.jpamybatisplusdemo.entity.Student;
import com.example.jpamybatisplusdemo.entity.StudentES;
import com.example.jpamybatisplusdemo.mapper.PersonMapper;
import com.example.jpamybatisplusdemo.mapper.StudentMapper;
import com.example.jpamybatisplusdemo.repository.StudentESRepository;
import com.example.jpamybatisplusdemo.repository.StudentRepository;
import com.example.jpamybatisplusdemo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaMybatisPlusDemoApplicationTests {

    @Resource
    StudentRepository studentRepository;
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private PersonMapper personMapper;
    @Resource
    private PersonService personService;
    @Resource
    private StudentESRepository studentESRepository;

    @Test
    public void test01() {
        Student student = new Student("张三", 21);
        studentRepository.save(student);
    }

    @Test
    public void test02() {
        Student student = studentRepository.findById(1).orElse(null);
        System.out.println(student+"@@@@");

        studentRepository.saveAndFlush(new Student("李四",32));
        List<Student> list1 = studentRepository.findByNameNative("张三");
        System.out.println(list1.toString()+"......");

        List<Student> list2 = studentRepository.findByNameNativeTwo("张三");
        System.out.println(list2.toString()+"-=-=-=-");

        List<Student> result = studentRepository.findByAgeAndName(21, "张三");
        System.out.println(result+"=====");

        List<Student> list3 = studentRepository.findByNameNativeThree("张三");
        System.out.println(list3+"|||");

    }

    @Test
    public void test03() {
        Student Student = studentMapper.findById(1);
        System.out.println(Student);
        Person person = personMapper.selectById(1);
        System.out.println(person);
    }

    @Test
    public void test04() {
        Person person = new Person("李四", 32, "李");
        personService.save(person);
    }

    @Test
    public void test05() {
//        es没有显示生成id会自动分配一个字符串id
//        StudentES studentES = new StudentES("李四" + Math.random() * 100, (int) (Math.random() * 100));
//        studentESRepository.save(studentES);
        Optional<StudentES> byId = studentESRepository.findById("vVj7FHgB0RDM5oIukFm6");
        System.out.println(byId + "=========byId========");
        Iterable<StudentES> all = studentESRepository.findAll();
        for (StudentES studentES1 : all) {
            System.out.println(studentES1 + "========|||||======");
        }
    }

    @Test
    public void test06() {
        StudentES studentES = new StudentES("李四" + Math.random() * 100, (int) (Math.random() * 100));
//        studentES.setId((int)System.currentTimeMillis());
        studentESRepository.save(studentES);
    }

    @Test
    public void test07() {
        studentESRepository.deleteAll();
    }
}
