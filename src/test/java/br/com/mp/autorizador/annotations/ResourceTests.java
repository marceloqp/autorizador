package br.com.mp.autorizador.annotations;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.annotation.AliasFor;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.test.annotation.Rollback;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@Rollback
@WebMvcTest
@EnableSpringDataWebSupport
public @interface ResourceTests {

    @AliasFor(value = "controllers", annotation = WebMvcTest.class)
    Class<?>[] value() default {};
}