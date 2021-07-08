package com.boot.demo.feign;

import com.boot.demo.model.CurrencyConverterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author JayendraB
 * Created on 08/07/21
 */

// FeignClient - currency-conversion-service/api/v2/from/{from}/to/{to}
// FeignClient supports Load Balancing
// FeignClient uses Eureka server to resolve URL of different service for communication
@FeignClient(name = "currency-conversion-service")
@RequestMapping(value = "/api/v1")
public interface CurrencyConversionProxy {

    @GetMapping(value = "/from/{from}/to/{to}")
    public CurrencyConverterDTO convertCurrency(@PathVariable String from, @PathVariable String to);

    @GetMapping("/message")
    public String message();
}
