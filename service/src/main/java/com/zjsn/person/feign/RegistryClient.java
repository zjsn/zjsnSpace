package com.zjsn.person.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient("${module.registry}")
public interface RegistryClient {
}
