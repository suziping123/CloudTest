package org.cloud.order.interceptor;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import java.util.UUID;


public class XTokenRequestInterceptor implements RequestInterceptor {
    /**
     * 请求拦截器
     * @param template 请求模板
     */
    @Override
    public void apply(RequestTemplate template) {
        System.out.println("请求拦截器启动……");
        template.header("X-Token", UUID.randomUUID().toString());
    }
}
