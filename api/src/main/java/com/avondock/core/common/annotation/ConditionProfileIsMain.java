package com.avondock.core.common.annotation;

import com.avondock.core.common.annotation.impl.IsNotTestEnvCondition;
import com.avondock.core.common.annotation.impl.ProfileIsMainCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(ProfileIsMainCondition.class)
public @interface ConditionProfileIsMain {
}
