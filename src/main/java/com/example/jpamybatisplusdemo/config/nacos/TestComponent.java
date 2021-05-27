//package com.example.jpamybatisplusdemo.config;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//@Component
//public class TestComponent {
//
//    @Component
//    public class OrderPropertiesCommandLineRunner implements CommandLineRunner {
//
//        private final Logger logger = LoggerFactory.getLogger(getClass());
//
//        @Autowired
//        private OrderProperties orderProperties;
//
//        @Override
//        public void run(String... args) {
//            logger.info("payTimeoutSeconds:" + orderProperties.getPayTimeoutSeconds());
//            System.out.println("payTimeoutSeconds:" + orderProperties.getPayTimeoutSeconds());
//            logger.info("createFrequencySeconds:" + orderProperties.getCreateFrequencySeconds());
//            System.out.println("createFrequencySeconds:" + orderProperties.getCreateFrequencySeconds());
//        }
//
//    }
//
//    @Component
//    public class ValueCommandLineRunner implements CommandLineRunner {
//
//        private final Logger logger = LoggerFactory.getLogger(getClass());
//
//        //        @NacosValue(value = "${order.pay-timeout-seconds}")
//        @Value(value = "${order.pay-timeout-seconds}")
//        private Integer payTimeoutSeconds;
//
//        //        @NacosValue(value = "${order.create-frequency-seconds}")
//        @Value(value = "${order.create-frequency-seconds}")
//        private Integer createFrequencySeconds;
//
//        @Override
//        public void run(String... args) {
//            logger.info("payTimeoutSeconds:" + payTimeoutSeconds);
//            System.out.println("payTimeoutSeconds:" + payTimeoutSeconds);
//            logger.info("createFrequencySeconds:" + createFrequencySeconds);
//            System.out.println("createFrequencySeconds:" + createFrequencySeconds);
//        }
//    }
//}
