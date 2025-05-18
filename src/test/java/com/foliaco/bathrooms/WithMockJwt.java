package com.foliaco.bathrooms;

import org.springframework.security.test.context.support.WithSecurityContext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = JwtSecurityContextFactory.class)
public @interface WithMockJwt {

    String value() default "";
    String username() default "testUser";
    String[] roles() default {"USER"};

}
