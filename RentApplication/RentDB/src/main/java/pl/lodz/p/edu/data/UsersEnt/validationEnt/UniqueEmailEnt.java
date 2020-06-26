package pl.lodz.p.edu.data.UsersEnt.validationEnt;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueEmailValidatorEnt.class)
public @interface UniqueEmailEnt {
    String message() default "{No unique}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
