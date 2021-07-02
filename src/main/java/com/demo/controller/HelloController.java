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

import com.demo.entity.Person;
import com.demo.service.HealthStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
public class HelloController {

    @Value("${server.port}")
    String port;

    @Autowired
    HealthStatusService healthStatusService;


    AtomicInteger callCount=new AtomicInteger();



    @GetMapping("/alive")
    public Object alive(){

        callCount.incrementAndGet();
        System.out.println("service:"+port+" count: "+callCount);
        return "service:"+port+" count: "+callCount;
    }


    @PostMapping("/gateway/alive")
    public Object aliveOnlyToGateway(){

        callCount.incrementAndGet();
        System.out.println("service:"+port+" count: "+callCount);
        return "service:"+port+" count: "+callCount;
    }


    @GetMapping("/hello")
    public String sayHello(){

        return port+": "+"hello world,hello ms!";
    }


    @GetMapping("/status")
    public boolean status(@RequestParam("status")boolean status){

        healthStatusService.setStatus(status);
      return healthStatusService.isStatus();
    }

    /**
     *
     *
     * @description 使用map定义远程调用所需要的参数.
     * @params
     * @return
     * @author
     * @date 22:36 2021/5/19
     *
     */
    @GetMapping("/helloWithMap")
    public Map<String, String> helloWithMap(@RequestParam Map<String,String> map){

        System.out.println(map.get("name"));
        String val= port+": "+"hello "+map.get("name");;
        return Collections.singletonMap(map.get("name"),val);
    }

    @PostMapping("/helloByObject")
    public Map<String,String> helloByObject(@RequestBody Person person){

        HashMap<String,String> map=new HashMap<>();
        map.put("hello","hello, "+person.getName());
        map.put("id", person.getId());
        return map;
    }
}