package com.lekhraj.java.spring.SBCustomizeConfiguration.CustomCondition;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class MyComplexCondition1 implements Condition {
    @Value("${sb.customize.configuration.feature2}") String feature2Flag;
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        return feature2Flag!=null && feature2Flag.equals("enabled") ? true : false;
    }
}
