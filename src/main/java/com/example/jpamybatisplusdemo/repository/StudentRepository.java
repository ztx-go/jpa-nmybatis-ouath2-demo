package com.example.jpamybatisplusdemo.repository;

import com.example.jpamybatisplusdemo.entity.Student;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentRepository")
public interface StudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

//    SpringBoot2.0之后，之前使用的findOne()方法的替代方法findById()方法的使用
//        Optional<T> findOne(@Nullable Specification<T> var1);

//    jpa自带方法
//    Optional<Student> findById(Integer integer);

    //    通过By属性And属性查询，可以进行自定义方法
    List<Student> findByAgeAndName(Integer age, String name);

    //    原生sql
//    参数绑定1
    @Query(value = "select * from student_test where name=?1", nativeQuery = true)
    List<Student> findByNameNative(String name);

    //    参数绑定2
    @Query(value = "select * from student_test where name=:name", nativeQuery = true)
    List<Student> findByNameNativeTwo(@Param("name") String name);

    //    HQL语法，from后跟的是实体类名
    @Query(value = "select s from Student s where s.name=:name")
    List<Student> findByNameNativeThree(@Param("name") String name);

}
