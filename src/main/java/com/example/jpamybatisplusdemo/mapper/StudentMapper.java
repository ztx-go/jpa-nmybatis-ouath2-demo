package com.example.jpamybatisplusdemo.mapper;

import com.example.jpamybatisplusdemo.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StudentMapper {

    @Select("select * from student_test where id=#{id}")
    Student findById(Integer id);
}
