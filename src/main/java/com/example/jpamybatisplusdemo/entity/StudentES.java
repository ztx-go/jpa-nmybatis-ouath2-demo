package com.example.jpamybatisplusdemo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.*;


@Data
//@Entity
@Document(indexName = "student")
@Table(name="student_es")
public class StudentES {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private String id;

    @Field(type = FieldType.Keyword)
    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    public StudentES() {
    }

    public StudentES(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "StudentES{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
