package com.xy.condition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @fileName:ConditionConfig
 * @author:xy
 * @date:2018/9/12
 * @description:
 */
@Configuration
public class ConditionConfig {
    @Bean
    @Conditional(WindowCondition.class)
    public Service m1(){
        return new WindowService();
    }
    @Bean
    @Conditional(LinuxCondition.class)
    public Service m2(){
        return new LinuxService();
    }
}
