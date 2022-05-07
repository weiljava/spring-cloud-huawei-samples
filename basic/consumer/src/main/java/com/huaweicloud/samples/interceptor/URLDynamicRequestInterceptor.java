package com.huaweicloud.samples.interceptor;


import com.alibaba.fastjson.JSON;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@Component
@Configuration
@ConfigurationProperties(prefix = "feign.interceptor")
@Slf4j
public class URLDynamicRequestInterceptor implements RequestInterceptor {

    private Map<String, String> feignMapUrl;

    /**
     * 本方法每个请求都会调用，可以通过RequestTemplate上的方法加入数据或处理逻辑。
     * Called for every request. Add data using methods on the supplied {@link RequestTemplate}.
     *
     * @param template
     */
    @Override
    public void apply(RequestTemplate template) {
        String url = template.url();
        String method = template.method();

//        template.target(feignMapUrl.get(template.feignTarget().name()));


        //header信息传递
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                template.header(name, values);
            }
            log.debug("feign interceptor header:{}", JSON.toJSONString(template.headers()));
        }

        // 转发参数
        Enumeration<String> bodyNames = request.getParameterNames();
        StringBuilder body = new StringBuilder();
        if (bodyNames != null) {
            while (bodyNames.hasMoreElements()) {
                String name = bodyNames.nextElement();
                String values = request.getParameter(name);
                body.append(name).append("=").append(values).append("&");
            }
        }
//        if (body.length() != 0) {
//            body.deleteCharAt(body.length() - 1);
//            template.body(body.toString());
//            log.info("feign interceptor body:{}", body.toString());
//        }

        log.info("通过feign请求接口, method: " + method + ", url: " + url + ", body: " + (body == null ? "" : body.toString()));
    }


    public Map<String, String> getFeignMapUrl() {
        return feignMapUrl;
    }

    public void setFeignMapUrl(Map<String, String> feignMapUrl) {
        this.feignMapUrl = feignMapUrl;
    }
}
