package cn.xuyj.gis.config;

import cn.hutool.core.util.StrUtil;
import lombok.SneakyThrows;
import lombok.extern.apachecommons.CommonsLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * @Author: xuyj
 * @Date: 2025/3/1 13:09
 * @Email: 1768335576@qq.com
 * @Desc：程序启动以后打印swagger地址
 */
@Component
@Slf4j
public class SwaggerPrintConfig implements ApplicationListener<WebServerInitializedEvent> {
    @SneakyThrows
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServerApplicationContext context = event.getApplicationContext();
        String hostAddress = InetAddress.getLocalHost().getHostAddress();
        int port = event.getWebServer().getPort();
        Environment environment = context.getEnvironment();
        String contextPath = environment.getProperty("server.servlet.context-path");
        String applicationName = environment.getProperty("spring.application.name");
        System.out.println("----------系统启动成功----------");
        System.out.println("Application name：" + applicationName);
        System.out.println("Swagger文档地址：http://" + hostAddress + ":" + port + contextPath);
        System.out.println("------------------------------");
    }
}
