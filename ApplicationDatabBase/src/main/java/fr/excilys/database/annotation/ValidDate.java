package fr.excilys.database.annotation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.constraints.Pattern;


@Documented
@Retention(RUNTIME)
@Target(FIELD)
@Pattern(regexp = "^([0-2][0-9]|(3)[0-1])(\\\\/)(((0)[0-9])|((1)[0-2]))(\\\\/)\\\\d{4}$")
public @interface ValidDate {
	String comments();
}
