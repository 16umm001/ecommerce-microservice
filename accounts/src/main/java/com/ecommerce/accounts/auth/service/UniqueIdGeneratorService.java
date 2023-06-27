package com.ecommerce.accounts.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UniqueIdGeneratorService {

    @Value("${unique_id.uri}")
    private String uri;

    @Autowired
    @LoadBalanced
    private RestTemplate restTemplate;

    public long getId(){
        return restTemplate.getForObject(uri, Long.class);
    }

}
