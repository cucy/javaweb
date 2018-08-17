package org.dlj.annotation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * 自定义Controller注解
 * @author zhxg
 *
 */
@Retention(RUNTIME)
@Target(TYPE)
public @interface Controller {

	public String value() default "";
}
