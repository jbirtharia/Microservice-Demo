package com.boot.demo.service;

import com.boot.demo.dao.FlightFareRepository;
import com.boot.demo.feign.CurrencyConversionProxy;
import com.boot.demo.model.CurrencyConverterDTO;
import com.boot.demo.model.FlightFare;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The type Flight service.
 *
 * @author JayendraB  Created on 10/07/21
 */
@Service
@Slf4j
public class FlightService {

    @Autowired
    private FlightFareRepository repository;

    @Autowired
    private CurrencyConversionProxy currencyConversionProxy;

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Get single fare ticket flight fare.
     *
     * @param baseCurrency the base currency
     * @param toCurrency   the to currency
     * @param flightCode   the flight code
     * @return the flight fare
     */
    public FlightFare getSingleFareTicket(String baseCurrency, String toCurrency, String flightCode){
        FlightFare fare = this.getFlightFare(flightCode);
        fare.setCurrency(toCurrency);

        if (!baseCurrency.equals(toCurrency)) {
            fare.setFare(fare.getFare().multiply(getConversionBigDecimalWithFeignClient(baseCurrency, toCurrency)));
        }
        return fare;
    }

    /**
     * Get message from currency conversion string.
     *
     * @return the string
     */
    public String getMessageFromCurrencyConversion(){
        return currencyConversionProxy.message();
    }

    private FlightFare getFlightFare(String flightCode) {

        FlightFare flight = new FlightFare(null, flightCode, null,null);
        Example<FlightFare> flightFare = Example.of(flight);
        return repository.findOne(flightFare).orElse(new FlightFare());
    }

    private BigDecimal getConversionBigDecimalWithFeignClient(String baseCurrency, String toCurrency) {

        // Using OpenFeign client to communicate currency-conversion-service
        CurrencyConverterDTO responseEntity = currencyConversionProxy.convertCurrency(baseCurrency, toCurrency);
        log.info("Response Entity : {}",responseEntity);
        return Objects.requireNonNull(responseEntity).getConversionRate();
    }

    // Call the currency conversion service here
    // RESTTemplate class
    private BigDecimal getConversionBigDecimal(String baseCurrency, String toCurrency) {

        String conversionURL = "http://currency-conversion-service/api/v1/from/{from}/to/{to}";
        // set the map object to store the path variables
        Map<String, String> urlPathVariables = new HashMap<>();
        urlPathVariables.put("from", baseCurrency);
        urlPathVariables.put("to", toCurrency);

        // Hostname will be resolved by Eureka server of conversionURL by using Eureka Registry
        ResponseEntity<CurrencyConverterDTO> responseEntity = restTemplate.getForEntity(conversionURL,
                CurrencyConverterDTO.class, urlPathVariables);
        log.info("Response Entity : {}",responseEntity);
        CurrencyConverterDTO converter = responseEntity.getBody();
        return Objects.requireNonNull(converter).getConversionRate();
    }
}
