package com.yjymh.penny.sys.annotate;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @author yjymh
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Component
public @interface Command {

}
