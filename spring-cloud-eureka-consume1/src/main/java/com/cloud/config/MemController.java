package com.cloud.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MemController {

	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/rest/{id}")
	public String way(@PathVariable("id")Integer id){
		return restTemplate.getForObject("http://mem-server/query/{id}", String.class, id);
		
	}
	@Autowired
	private LoadBalancerClient client;
	
	@GetMapping("/test")
	public String getClient(){
		ServiceInstance instance = client.choose("mem-server");
		System.out.println(instance.getHost()+":"+instance.getPort()+":"+instance.getServiceId());
		ServiceInstance instance1 = client.choose("mem-server1");
		System.out.println(instance1.getHost()+":"+instance1.getPort()+":"+instance1.getServiceId());
		return "1";
	}
}
