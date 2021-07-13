package com.boot.demo.feign;

import com.boot.demo.model.CurrencyConverterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The interface Currency conversion proxy.
 * FeignClient - currency-conversion-service/api/v2/from/{from}/to/{to}.
 * FeignClient supports Load Balancing.
 * FeignClient uses Eureka server to resolve URL of different service for communication.
 *
 * @author JayendraB  Created on 08/07/21
 */
@FeignClient(name = "currency-conversion-service")
@RequestMapping(value = "/api/v1")
public interface CurrencyConversionProxy {

    /**
     * Convert currency currency converter dto.
     *
     * @param from the from
     * @param to   the to
     * @return the currency converter dto
     */
    @GetMapping(value = "/from/{from}/to/{to}")
    CurrencyConverterDTO convertCurrency(@PathVariable String from, @PathVariable String to);

    /**
     * Message string.
     *
     * @return the string
     */
    @GetMapping("/message")
    String message();
}
