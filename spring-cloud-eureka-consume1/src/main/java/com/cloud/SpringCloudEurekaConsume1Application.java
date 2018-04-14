package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.config.RibbonConfig;
/**
 * mem-server这个是服务的名称，而RibbonConfig是配置的类，在自定义ribbon负载均衡策略时
 * RibbonConfig这个类不能放在启动项的子目录和他的同一个目录下，不能回呗扫描两次
 * 这个类在启动的时候是不进行实例化的，只有在调用的时候实例化RibbonConfig
 * 如果一定要把这个类写在启动项的子目录下，可以自己写一个注解(ExcludeCompentScan)，
 * @ComponentScan(excludeFilters=
 * {@ComponentScan.Filter(type=FilterType.ANNOTATION,
 * value=ExcludeCompentScan.class)})
 * 在通过这个来排除掉ExcludeCompentScan他所注解的类
 * @author bin.wang
 *
 */
@EnableAutoConfiguration
@EnableEurekaClient
@SpringBootApplication
@RibbonClient(name="mem-server",configuration=RibbonConfig.class)
public class SpringCloudEurekaConsume1Application {

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	public static void main(String[] args) {
		SpringApplication.run(SpringCloudEurekaConsume1Application.class, args);
	}
}
