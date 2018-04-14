package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 
 * @author bin.wang
 *	扫描不能重复了，所以不能放在springboot启动项的子母录下
 */

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
@Configuration
public class RibbonConfig {
	
//	@Autowired
//	private IClientConfig config;
//	
	@Bean
	public IRule getIRule(IClientConfig config){
		return new RandomRule();//表示随机
	}
}
