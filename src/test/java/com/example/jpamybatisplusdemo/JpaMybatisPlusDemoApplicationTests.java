package com.example.jpamybatisplusdemo;

import com.alibaba.druid.sql.visitor.functions.Char;
import com.example.jpamybatisplusdemo.entity.Person;
import com.example.jpamybatisplusdemo.entity.Student;
import com.example.jpamybatisplusdemo.entity.Student;
import com.example.jpamybatisplusdemo.entity.StudentES;
import com.example.jpamybatisplusdemo.mapper.PersonMapper;
import com.example.jpamybatisplusdemo.mapper.StudentMapper;
//import com.example.jpamybatisplusdemo.repository.StudentESRepository;
import com.example.jpamybatisplusdemo.repository.StudentRepository;
import com.example.jpamybatisplusdemo.service.PersonService;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.hibernate.query.criteria.internal.predicate.NegatedPredicateWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

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
//    @Resource
//    private StudentESRepository studentESRepository;

    @Test
    public void test01() {
        Student student = new Student("张三", 21);
        studentRepository.save(student);
    }

    @Test
    public void test02() {
        Student student = studentRepository.findById(1).orElse(null);
        System.out.println(student + "@@@@");

        studentRepository.saveAndFlush(new Student("李四", 32));
        List<Student> list1 = studentRepository.findByNameNative("张三");
        System.out.println(list1.toString() + "......");

        List<Student> list2 = studentRepository.findByNameNativeTwo("张三");
        System.out.println(list2.toString() + "-=-=-=-");

        List<Student> result = studentRepository.findByAgeAndName(21, "张三");
        System.out.println(result + "=====");

        List<Student> list3 = studentRepository.findByNameNativeThree("张三");
        System.out.println(list3 + "|||");

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

//    @Test
//    public void test05() {
////        es没有显示生成id会自动分配一个字符串id
////        StudentES studentES = new StudentES("李四" + Math.random() * 100, (int) (Math.random() * 100));
////        studentESRepository.save(studentES);
//        Optional<StudentES> byId = studentESRepository.findById("vVj7FHgB0RDM5oIukFm6");
//        System.out.println(byId + "=========byId========");
//        Iterable<StudentES> all = studentESRepository.findAll();
//        for (StudentES studentES1 : all) {
//            System.out.println(studentES1 + "========|||||======");
//        }
//    }
//
//    @Test
//    public void test06() {
//        StudentES studentES = new StudentES("李四" + Math.random() * 100, (int) (Math.random() * 100));
////        studentES.setId((int)System.currentTimeMillis());
//        studentESRepository.save(studentES);
//    }
//
//    @Test
//    public void test07() {
//        studentESRepository.deleteAll();
//    }

    @Test
    public void test08() {
        ThreadPoolExecutor th = new ThreadPoolExecutor(4, 8, 1000, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<>());

        String[] lastName = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "张", "刘"};
        String[] name = {"胜", "向", "建", "法", "我", "给", "儿", "西", "将", "咯"};
        String[] des = {"地方", "微软", "会突然", "阿松大", "让他人", "解决", "重复", "校验", "颇为", "空位"};
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            String n1 = lastName[random.nextInt(lastName.length)];
            String n2 = name[random.nextInt(lastName.length)];
            String n3 = name[random.nextInt(lastName.length)];
            String n4 = des[random.nextInt(lastName.length)];
            Person p = new Person();
            p.setName(n1 + n2 + n3);
            p.setLastName(n4 + n3 + n2);
            p.setAge(random.nextInt(40));
            personMapper.insert(p);

        }
    }

    @Test
    public void test09() {
        List<String> list1 = new ArrayList<>();
//        list1.add("1");
//        list1.add("2");
//        list1.add("3");
//        list1.add("5");
//        list1.add("6");

        List<String> list2 = new ArrayList<>();
        list2.add("2");
        list2.add("3");
        list2.add("7");
        list2.add("8");

        List<String> collect111 = list1.stream().filter(item -> list2.contains(item)).collect(toList());

        // 交集
        List<String> intersection = list1.stream().filter(item -> list2.contains(item)).collect(toList());
        System.out.println("---交集 intersection---");
//        parallelStream并行处理
        intersection.parallelStream().forEach(System.out::println);
        System.out.println(intersection.toString());

        // 差集 (list1 - list2)
        List<String> reduce1 = list1.stream().filter(item -> !list2.contains(item)).collect(toList());
        System.out.println("---差集 reduce1 (list1 - list2)---");
        reduce1.parallelStream().forEach(System.out::println);

        // 差集 (list2 - list1)
        List<String> reduce2 = list2.stream().filter(item -> !list1.contains(item)).collect(toList());
        System.out.println("---差集 reduce2 (list2 - list1)---");
        reduce2.parallelStream().forEach(System.out::println);

        // 并集
        List<String> listAll = list1.parallelStream().collect(toList());
        List<String> listAll2 = list2.parallelStream().collect(toList());
        listAll.addAll(listAll2);
        System.out.println("---并集 listAll---");
        listAll.parallelStream().forEachOrdered(System.out::println);

        // 去重并集
        List<String> listAllDistinct = listAll.stream().distinct().collect(toList());
        System.out.println("---得到去重并集 listAllDistinct---");
        listAllDistinct.parallelStream().forEachOrdered(System.out::println);

        System.out.println("---原来的List1---");
        list1.parallelStream().forEachOrdered(System.out::println);
        System.out.println("---原来的List2---");
        list2.parallelStream().forEachOrdered(System.out::println);

    }

    @Test
    public void test10() throws Exception {
        List<String> list = new ArrayList<>();
        String dateStart = "2016-01-01";
        String dateEnd = "2016-12-31";
        SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
        long startTime = date.parse(dateStart).getTime();//start
        long endTime = date.parse(dateEnd).getTime();//end
        long day = 1000 * 60 * 60 * 24;
        for (long i = startTime; i <= endTime; i += day) {
            String format = date.format(new Date(i));
            String[] split = format.split("-");
            String dateString = split[1] + "/" + split[2];
            list.add(dateString);
            System.out.println(dateString);
        }
    }

    @Test
    public void test11() {
        String ss = "abc.jjj";
        String[] split = ss.split("\\.");
        System.out.println(Arrays.toString(split));
        Set<String> set;
        LocalDate now = LocalDate.now();
        System.out.println(now);


        Random rand = new Random(47);
        int c = rand.nextInt(26) + 'a';
        System.out.println((char) c);


    }


    //    获取一年的所有日期
    @Test
    public void test12() throws Exception {
        Set<String> allTimeSet = new HashSet<>();
        if (allTimeSet == null || allTimeSet.size() == 0) {
            String dateStart = "2016-01-01";
            String dateEnd = "2016-12-31";
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            long startTime = date.parse(dateStart).getTime();//start
            long endTime = date.parse(dateEnd).getTime();//end
            long day = 1000 * 60 * 60 * 24;
            for (long i = startTime; i <= endTime; i += day) {
                String format = date.format(new Date(i));
                String[] split = format.split("-");
                String dateString = split[1] + "-" + split[2];
                allTimeSet.add(dateString);
            }
        }
    }

    //    对集合进行排序
    @Test
    public void test13() {
        List<String> collect = new ArrayList<>();
        collect.add("01-02");
        collect.add("04-17");
        collect.add("12-24");
        Collections.sort(collect, (obj1, obj2) -> {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate beginCompare = LocalDate.parse("2016-" + obj1, df);
            LocalDate endCompare = LocalDate.parse("2016-" + obj2, df);
            if (beginCompare.compareTo(endCompare) > 0) {
                return 1;
            } else {
                return -1;
            }
        });
    }

    @Test
    public void test14() {
        String idNumber = "412727199311022037";
        Validate.isTrue(idNumber.length() == 18 || idNumber.length() == 15, "身份证号码有误");
        System.out.println("ddd");
        String substring = idNumber.substring(idNumber.length() - 6);
        System.out.println(substring);
    }


    @Test
    public void test15() {
        boolean b = testString();
        System.out.println(b);
    }

    public boolean testString() {

        String s = "(){)()";
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if ('(' == (s.charAt(i)) || '{' == (s.charAt(i))) {
                stack.push(s.charAt(i));
            } else {
                if (stack.empty() || map.get(s.charAt(i)) != stack.pop()) {
                    return false;
                }
            }
        }
        if (!stack.empty()) {
            return false;
        }
        return true;
    }

    @Test
    public void test16() {
        LocalDate now = LocalDate.now();
        System.out.println(now);
        System.out.println(now.toString().replace("-", "") + "====");
        String s = "202104270001";
        System.out.println(s.substring(0, 8));
        System.out.println(s.substring(8, 12));
        int i = Integer.parseInt("0099");
        DecimalFormat decimalFormat = new DecimalFormat("0000");
        i = i + 1;
        String k = decimalFormat.format(i);
        System.out.println(k);
    }

    @Test
    public void test17() {
        LocalDate todayDate = LocalDate.now();
        System.out.println(todayDate.toString());

        Date chainTime = new Date(1620820854000L);
        System.out.println(chainTime);
    }

    @Test
    public void test18() {
        String strDateFormat = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        Date date = new Date(1620820854000L);
        String format = sdf.format(date);
        System.out.println(format);
        AtomicInteger atomicInteger = new AtomicInteger(1);

    }

    @Test
    public void test19() {

    }

}
