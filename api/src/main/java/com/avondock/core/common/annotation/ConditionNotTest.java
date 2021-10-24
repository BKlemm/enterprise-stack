package com.avondock.core.common.annotation;

import com.avondock.core.common.annotation.impl.IsNotTestEnvCondition;
import org.springframework.context.annotation.Conditional;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional(IsNotTestEnvCondition.class)
public @interface ConditionNotTest {
}
