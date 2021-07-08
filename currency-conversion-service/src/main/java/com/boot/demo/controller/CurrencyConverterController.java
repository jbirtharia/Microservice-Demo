package com.boot.demo.controller;

import com.boot.demo.dao.CurrencyConverterRepository;
import com.boot.demo.model.CurrencyConverter;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.domain.Example;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JayendraB
 * Created on 02/07/21
 */
@RestController
@RequestMapping("/api/v1")
@RefreshScope
public class CurrencyConverterController {

    @Autowired
    private CurrencyConverterRepository converterRepository;

    @Value("${app.message}")
    private String message;

    @Autowired
    private Environment env;

    @GetMapping("/from/{from}/to/{to}")
    public CurrencyConverter convertCurrency(@PathVariable String from, @PathVariable String to){

        CurrencyConverter converter = new CurrencyConverter(null, from, to, null,null);
        Example<CurrencyConverter> conversionFilter = Example.of(converter);
        CurrencyConverter responseEntity = converterRepository.findOne(conversionFilter).orElse(null);
        if(null != responseEntity)
            responseEntity.setPort(env.getProperty("server.port"));
        return responseEntity;
    }

        @GetMapping("/message")
    public String getMessage(){
        return this.message;
    }
}
