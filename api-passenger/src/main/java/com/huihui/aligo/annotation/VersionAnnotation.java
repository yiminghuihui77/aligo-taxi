package com.huihui.aligo.annotation;

import java.lang.annotation.*;

/**
 * 版本号注解
 *
 * @author minghui.y
 * @create 2021-06-24 6:46 下午
 **/
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface VersionAnnotation {
}
