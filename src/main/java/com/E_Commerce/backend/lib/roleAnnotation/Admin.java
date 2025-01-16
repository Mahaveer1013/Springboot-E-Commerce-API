package com.E_Commerce.backend.lib.roleAnnotation;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)  // This annotation will be used on methods
@Retention(RetentionPolicy.RUNTIME)  // Available at runtime
@PreAuthorize("hasAuthority('ADMIN')")
public @interface Admin {
}
