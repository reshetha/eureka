package com.capgemini.eurekaclient1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@RestController
public class EurekaClient1Controller {
	@Autowired
	private EurekaClient eurekaclient1;
	private RestTemplate restTemplate=new RestTemplate();
	@GetMapping("/greeting")
	public String getGreetingMessage() {
		Application application =eurekaclient1.getApplication("helloIndia");
		InstanceInfo instanceInfo = application.getInstances().get(0);
		String url="http://"+instanceInfo.getIPAddr()+ ":"+instanceInfo.getPort()+"/"+"message";
		return restTemplate.getForObject(url, String.class);
	}
}
