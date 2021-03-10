package com.example.jpamybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jpamybatisplusdemo.entity.Person;
import com.example.jpamybatisplusdemo.mapper.PersonMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends ServiceImpl<PersonMapper, Person> {
}
