package com.boot.demo.service;

import com.boot.demo.dao.CurrencyConverterRepository;
import com.boot.demo.model.CurrencyConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

/**
 * The type Currency converter service.
 *
 * @author JayendraB  Created on 10/07/21
 */
@Service
public class CurrencyConverterService {

    @Autowired
    private CurrencyConverterRepository converterRepository;

    @Autowired
    private Environment env;

    /**
     * Perform convert currency currency converter.
     *
     * @param from the from
     * @param to   the to
     * @return the currency converter
     */
    public CurrencyConverter performConvertCurrency(String from, String to){

        CurrencyConverter converter = new CurrencyConverter(null, from, to, null,null);
        Example<CurrencyConverter> conversionFilter = Example.of(converter);
        CurrencyConverter responseEntity = converterRepository.findOne(conversionFilter).orElse(null);
        if(null != responseEntity)
            responseEntity.setPort(env.getProperty("server.port"));
        return responseEntity;
    }
}
