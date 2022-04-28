package com.huaweicloud.samples;


import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.Target;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Configuration
@ConfigurationProperties(prefix = "feign.interceptor")
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
        byte[] body = template.body();
        String url = template.url();
        String method = template.method();
        System.out.println("通过feign请求接口, method: " + method + ", url: " + url + ", body: " + (body == null ? "" : new String(body)));
//        Target.HardCodedTarget target = (Target.HardCodedTarget) template.feignTarget();
//        template.feignTarget(new Target.HardCodedTarget(target.type(), target.name(), feignMapUrl.get(target.name())));
    }


    public Map<String, String> getFeignMapUrl() {
        return feignMapUrl;
    }

    public void setFeignMapUrl(Map<String, String> feignMapUrl) {
        this.feignMapUrl = feignMapUrl;
    }
}
