package com.atguigu.cloud.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration是Spring框架中的一个注解，它用于标记一个类作为bean定义的来源。被@Configuration注解的类通常包含一个或多个@Bean注解的方法，这些方法将会被Spring容器处理，以便在运行时生成Spring beans。
 * <p>
 * Spring IOC 的全称是 Spring Inversion of Control（控制反转）。这是 Spring 框架提供的一个核心特性，用于实现对象的依赖关系和管理。控制反转意味着将对象创建和依赖关系的管理从编码直接控制转移到外部容器（如 Spring 容器），实现了代码的解耦。在 Spring 中，IOC 容器负责实例化、配置和组装对象。这通常通过配置文件（XML或注解）完成，开发者不需要手动创建对象，而是通过容器自动装配。IOC 主要通过依赖注入（Dependency Injection, DI）实现，DI 是一种特定的 IOC 实现方式，它允许类的依赖项在运行时或类的构造时被注入，而不是类自己内部创建。
 * <p>
 * 在Spring框架中，bean是由Spring IoC（Inversion of Control）容器管理的对象。Spring IoC容器负责创建bean、管理bean的生命周期，以及配置bean之间的依赖关系。  @Bean注解用于标记一个方法，该方法返回一个对象，这个对象应该被Spring IoC容器管理。@Bean注解的方法可以包含任意的复杂配置逻辑。
 */
@Configuration
public class Swagger3Config {
    @Bean
    public GroupedOpenApi PayApi() {
        return GroupedOpenApi.builder().group("支付微服务模块").pathsToMatch("/pay/**").build();
    }

    @Bean
    public GroupedOpenApi OtherApi() {
        return GroupedOpenApi.builder().group("其它微服务模块").pathsToMatch("/other/**", "/others").build();
    }
    /*@Bean
    public GroupedOpenApi CustomerApi()
    {
        return GroupedOpenApi.builder().group("客户微服务模块").pathsToMatch("/customer/**", "/customers").build();
    }*/

    @Bean
    public OpenAPI docsOpenApi() {
        return new OpenAPI()
                .info(new Info().title("cloud2024")
                        .description("通用设计rest")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("www.atguigu.com")
                        .url("https://yiyan.baidu.com/"));
    }
}