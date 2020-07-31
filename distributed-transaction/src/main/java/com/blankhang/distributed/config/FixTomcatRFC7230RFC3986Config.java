package com.blankhang.distributed.config;

import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

/**
 * 解决异常信息：
 *    java.lang.IllegalArgumentException:
 *        Invalid character found in the request target. The valid characters are defined in RFC 7230 and RFC 3986
 * <p>
 * 这是因为Tomcat严格按照 RFC 3986规范进行访问解析，而 RFC 3986规范定义了Url中只允许包含英文字母（a-zA-Z）、数字（0-9）、-_.~4个特殊字符以及所有保留字符(RFC3986中指定了以下字符为保留字符：! * ’ ( ) ; : @ & = + $ , / ? # [ ])。
 * 传入的参数中有"{"不在RFC3986中的保留字段中，所以会报这个错。
 *
 * @author blank
 * @date 2020-07-03 上午 11:36
 */
public class FixTomcatRFC7230RFC3986Config {

    @Bean
    public ServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory fa = new TomcatServletWebServerFactory();
        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedPathChars", "[]{}"));
        fa.addConnectorCustomizers(connector -> connector.setProperty("relaxedQueryChars", "[]{}"));
        return fa;
    }
}