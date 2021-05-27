package com.example.jpamybatisplusdemo.config.aop;

import com.example.jpamybatisplusdemo.common.OwnAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class OwnAnnotationAOP {
    private volatile static Map<String, String> map = new ConcurrentHashMap<>();

    //    使用切面去匹配注解了OwnAnnotation 的方法/controller
    @Around("execution(* com.chainboard..*Controller.*(..)) && @annotation(nrs)")
    public Object arround(ProceedingJoinPoint pjp, OwnAnnotation nrs) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String sessionId = RequestContextHolder.getRequestAttributes().getSessionId();
        HttpServletRequest request = attributes.getRequest();
        String key = sessionId + "-" + request.getServletPath();
        // 如果缓存中有这个url视为重复提交
        Object o = pjp.proceed();
//        map.put("","");
        return null;
    }

    public void getAno() {
        try {
            //获取 标注了 注解的类的 Class对象
            Class stuClass = Class.forName("pojos.Student");

            //说明一下，这里形参不能写成Integer.class，应写为int.class
            Method stuMethod = stuClass.getMethod("study", int.class);

            if (stuMethod.isAnnotationPresent(OwnAnnotation.class)) {
                System.out.println("Student类上配置了CherryAnnotation注解！");
                //获取该元素上指定类型的注解
                OwnAnnotation own = stuMethod.getAnnotation(OwnAnnotation.class);
                System.out.println("name: " + own.name() + ", age: " + own.age()
                        + ", score: " + own.score()[0]);
            } else {
                System.out.println("Student类上没有配置CherryAnnotation注解！");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
