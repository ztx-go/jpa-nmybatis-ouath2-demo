package com.example.jpamybatisplusdemo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.jpamybatisplusdemo.entity.Person;
import com.example.jpamybatisplusdemo.mapper.PersonMapper;
import org.springframework.stereotype.Service;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class PersonService extends ServiceImpl<PersonMapper, Person> {

    private ReentrantLock reentrantLock = new ReentrantLock();

    public void test01() {
        reentrantLock.lock();
        reentrantLock.unlock();
    }
}
