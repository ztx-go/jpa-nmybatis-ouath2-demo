package com.example.jpamybatisplusdemo.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.example.jpamybatisplusdemo.common.OwnAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @OwnAnnotation(name = "dd", age = 24, score = {1, 2})
    @GetMapping("/test01")
    public String test01() throws Exception {
        return "success";
    }

    //    这里为了实现自动刷新配置的功能，我们也无法使用 @Value 注解，而是使用 @NacosValue 替代
    //    @Value("${test}")
    @NacosValue(value = "${test}", autoRefreshed = true)
    private String test;

    @GetMapping("/test")
    public String test() {
        return test;
    }

//    @Autowired
//    private TestProperties1 testProperties;
//
//    @GetMapping("/test_properties")
//    public TestProperties1 testProperties() {
//        return testProperties;
//    }

    private Logger logger = LoggerFactory.getLogger(getClass());

    @GetMapping("/logger")
    public void logger() {
//        默认日志级别为info，修改日志为debug后才会打印
        logger.debug("[logger][测试一下]");
    }
}
