package org.dlj.annotation;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 定义请求路径的java annotation
 * @author zhxg
 *
 */
@Retention(RUNTIME)
@Target(METHOD)
public @interface RequestMapping {

	public String value() default "";
}
