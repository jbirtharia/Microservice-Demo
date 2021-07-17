package com.boot.demo.controller;

import com.boot.demo.model.CurrencyConverter;
import com.boot.demo.service.CurrencyConverterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

/**
 * The type Currency converter controller.
 *
 * @author JayendraB Created on 02/07/21
 */
@RestController
@RequestMapping("/api/v1")
@RefreshScope
@Slf4j
public class CurrencyConverterController {

    @Value("${app.message}")
    private String message;

    @Autowired
    private CurrencyConverterService currencyConverterService;

    /**
     * Convert currency currency converter.
     *
     * @param from        the from
     * @param to          the to
     * @return the currency converter
     */
    @GetMapping("/from/{from}/to/{to}")
    public CurrencyConverter convertCurrency(@PathVariable String from, @PathVariable String to){

        return currencyConverterService.performConvertCurrency(from, to);
    }

    /**
     * Get message string.
     *
     * @return the string
     */
    @GetMapping("/message")
    public String getMessage(){
        return this.message;
    }
}
