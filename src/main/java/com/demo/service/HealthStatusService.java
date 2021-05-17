/**
 * copyright (C), 2021, ImagineAltaria
 *
 * @program eureka-provider
 * @description
 * @author liuda
 * @create 2021/5/14 9:18
 * @version 1.0.0
 * <author>                <time>                  <version>                   <description>
 * liuda         2021/5/14 9:18             1.0
 */

package com.demo.service;




import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;

import org.springframework.stereotype.Service;

@Service
public class HealthStatusService implements HealthIndicator {

    boolean status=true;



    @Override
    public Health health() {

        if (this.isStatus()){
            return new Health.Builder().up().build();
        }
        return new Health.Builder().down().build();
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}