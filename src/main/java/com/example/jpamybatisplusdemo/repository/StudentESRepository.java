package com.example.jpamybatisplusdemo.repository;

import com.example.jpamybatisplusdemo.entity.StudentES;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository("StudentESRepository")
public interface StudentESRepository extends ElasticsearchRepository<StudentES, String>
//        , JpaRepository<StudentES,String>,
//        JpaSpecificationExecutor<StudentES>
{

}
