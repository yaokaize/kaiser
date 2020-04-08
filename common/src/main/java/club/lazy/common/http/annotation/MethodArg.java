package club.lazy.common.http.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MethodArg {

    String name() default "";
    String description() default "";
    boolean required() default true;
}
