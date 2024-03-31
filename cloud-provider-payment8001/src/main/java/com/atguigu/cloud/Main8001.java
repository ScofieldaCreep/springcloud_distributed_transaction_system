package com.atguigu.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 理解 `@SpringBootApplication` 注解和 `SpringApplication.run(Main8001.class, args);` 调用过程中的关键，在于理解 Spring Boot 应用的启动机制和 Java 的类加载与执行机制。
 * <p>
 * 这里涉及的并不是“自己调用自己”的递归调用，而是利用了 Java 的静态方法特性和 Spring Boot 的启动机制来启动应用程序。让我们逐步解析：
 * <p>
 * ### 1. @SpringBootApplication 注解
 * <p>
 * `@SpringBootApplication` 是一个方便的注解，它包含了 `@Configuration`、`@EnableAutoConfiguration` 和 `@ComponentScan` 三个注解。这意味着 Spring Boot 会启动自动配置，扫描当前包（和子包）中的 Bean，并根据添加的依赖自动配置它们。
 * <p>
 * ### 2. SpringApplication.run 方法
 * <p>
 * - **静态方法调用**：`SpringApplication.run(Main8001.class, args);` 是一个静态方法的调用。它告诉 Spring Boot 根据 `Main8001.class` 指定的主类来启动应用。这个调用初始化 Spring 应用上下文（ApplicationContext），加载配置，启动 Spring 容器，然后创建和注册所有的 Bean。传递给 `run` 方法的参数 (`args`) 被用来配置应用，比如可以通过命令行参数定制行为。
 * <p>
 * - **执行流程**：在 `main` 方法中调用 `SpringApplication.run` 并不是“自己调用自己”。这个方法是程序的入口点，它仅仅是利用了 Java 的静态方法调用机制来启动 Spring 应用。一旦 `run` 方法被执行，Spring Boot 的启动流程就开始了，这个过程是线性和单向的，没有递归或循环调用的情况。
 * <p>
 * ### 3. Java 程序的启动
 * <p>
 * - **程序入口**：Java 程序的执行从 `main` 方法开始。这是所有 Java 程序的入口点。当你执行一个 Java 应用时，JVM（Java虚拟机）查找并调用 `main` 方法。
 * <p>
 * - **main 方法的特殊性**：`main` 方法是一个静态方法，这意味着它可以在没有创建类实例的情况下直接被调用。在这个方法中调用 `SpringApplication.run` 是启动 Spring 应用的标准方式，这并不构成任何形式的逻辑循环或是“自己调用自己”。
 * <p>
 * 总结来说，`@SpringBootApplication` 注解和 `SpringApplication.run(Main8001.class, args);` 语句共同工作，以配置和启动 Spring Boot 应用。这个过程是标准的 Java 应用程序启动方式，遵循 Java 的执行机制，利用了 Spring Framework 提供的便利性和自动配置能力。
 */
@SpringBootApplication
@MapperScan("com.atguigu.cloud.mapper")
public class Main8001 {
    public static void main(String[] args) {
        SpringApplication.run(Main8001.class, args);
    }
}