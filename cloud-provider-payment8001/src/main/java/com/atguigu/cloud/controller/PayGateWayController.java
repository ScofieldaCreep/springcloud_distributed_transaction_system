package com.atguigu.cloud.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Enumeration;


@RestController
public class PayGateWayController {
    @Resource
    PayService payService;

    @GetMapping(value = "/pay/gateway/get/{id}")
    public ResultData<Pay> getById(@PathVariable("id") Integer id) {
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/gateway/info")
    public ResultData<String> getGatewayInfo() {
        return ResultData.success("gateway info test：" + IdUtil.simpleUUID());
    }

    // X-Request-atguigu1和X-Request-atguigu2这两个请求头是在Spring Cloud Gateway的路由配置中添加的。在你提供的application.yml文件中，有一个路由规则定义了一个过滤器，这个过滤器会向所有匹配该路由的请求添加X-Request-atguigu1和X-Request-atguigu2这两个请求头
    // 在Spring Cloud Gateway中，过滤器可以用于修改进入或离开Gateway的HTTP请求或响应。在这个例子中，AddRequestHeader过滤器用于向HTTP请求添加额外的请求头。
    // 你可以通过添加自定义的请求头来传递额外的信息，例如身份验证信息，跟踪信息等。
    @GetMapping(value = "/pay/gateway/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request) {
        StringBuilder result = new StringBuilder();
        Enumeration<String> headers = request.getHeaderNames();
        while (headers.hasMoreElements()) {
            String headName = headers.nextElement();
            String headValue = request.getHeader(headName);
            System.out.println("请求头名：" + headName + "\t\t\t 请求头值:" + headValue);
            if (headName.equalsIgnoreCase("X-Request-atguigu1")
                    || headName.equalsIgnoreCase("X-Request-atguigu2")) {
                result.append(headName).append("\t").append(headValue).append(" ");
            }
        }
        return ResultData.success("gatewayFilter 过滤器 test：" + result + "\t" + DateUtil.now());
    }
}