/**
 * copyright (C), 2021, ImagineAltaria
 *
 * @program eureka-provider
 * @description
 * @author liuda
 * @create 2021/5/12 9:07
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * liuda         2021/5/12 9:07             1.0
 */

package com.demo.controller;

import com.demo.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @Autowired
    HealthStatusService healthStatusService;

    @GetMapping("/hello")
    public String sayHello(){

        return port+": "+"hello world,hello ms!";
    }


    @GetMapping("/status")
    public boolean status(@RequestParam("status")boolean status){

        healthStatusService.setStatus(status);
      return healthStatusService.isStatus();
    }
}