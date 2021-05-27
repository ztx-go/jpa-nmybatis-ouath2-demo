package com.example.jpamybatisplusdemo.config.nacos;

import com.alibaba.nacos.api.config.ConfigType;
import com.alibaba.nacos.api.config.annotation.NacosConfigurationProperties;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
@NacosConfigurationProperties(prefix = "", dataId = "${nacos.config.data-id}", type = ConfigType.YAML, autoRefreshed = true)
public class TestProperties1 {
//    要使用nacos的自动刷新配置就必须使用 @NacosConfigurationProperties 注解，nacos 自己的注解

    private String test;

}
