package com.atguigu.cloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Lombok是一个Java库，它可以通过注解的方式，帮助我们自动地生成常用的代码，如getter/setter方法，构造函数，`equals`，`hashCode`和`toString`方法等，从而减少模板代码的编写，提高开发效率。
 * <p>
 * 在你的代码中，使用了两个Lombok的注解：`@Data`和`@Accessors`。
 * <p>
 * `@Data`是一个方便的注解，它包含了以下几个Lombok的注解：`@ToString`，`@EqualsAndHashCode`，`@Getter`，`@Setter`，和`@RequiredArgsConstructor`。使用`@Data`注解后，Lombok会为类的所有字段自动生成对应的getter/setter方法，以及`equals`，`hashCode`和`toString`方法。
 * <p>
 * `@Accessors(chain = true)`注解用于配置setter方法的返回值。当`chain`属性设置为`true`时，生成的setter方法返回的是`this`，也就是说，setter方法返回的是当前对象。这样我们就可以链式地调用setter方法，例如：
 * <p>
 * ```java
 * ResultData<String> resultData = new ResultData<>();
 * resultData.setCode("200").setMessage("success").setData("data");
 * ```
 * <p>
 * 这种链式调用方式可以使代码更加简洁，易读。
 * <p></p>
 * 当你在类上使用了Lombok的`@Data`注解，Lombok会自动为这个类生成`toString`，`equals`和`hashCode`方法，你不需要显式地写出这些方法。这就是Lombok的优点之一，它可以帮助我们减少模板代码的编写，提高开发效率。
 */
@Data
@Accessors(chain = true)
public class ResultData<T> {

    private String code;
    /**
     * 结果状态 ,具体状态码参见枚举类ReturnCodeEnum.java
     */
    private String message;
    private T data;
    private long timestamp;


    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }

    public static <T> ResultData<T> fail(String code, String message) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setCode(code);
        resultData.setMessage(message);

        return resultData;
    }

}