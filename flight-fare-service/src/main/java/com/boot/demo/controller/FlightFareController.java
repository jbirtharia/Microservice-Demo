package com.boot.demo.controller;

import com.boot.demo.dao.FlightFareRepository;
import com.boot.demo.feign.CurrencyConversionProxy;
import com.boot.demo.model.CurrencyConverterDTO;
import com.boot.demo.model.FlightFare;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author JayendraB
 * Created on 08/07/21
 */
@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class FlightFareController {

    @Autowired
    private FlightFareRepository repository;

    @Value("${base.currency}")
    private String baseCurrency;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private CurrencyConversionProxy currencyConversionProxy;

    @GetMapping("/flight/{flightcode}/fare/{currency}")
    public FlightFare getSingleFareTicket(@PathVariable("flightcode") String flightCode, @PathVariable String currency) {
        FlightFare fare = this.getFlightFare(flightCode);
        fare.setCurrency(currency);

        if (!baseCurrency.equals(currency)) {
            fare.setFare(fare.getFare().multiply(getConversionBigDecimalWithFeignClient(currency)));
        }
        return fare;
    }

    private FlightFare getFlightFare(String flightCode) {

        FlightFare flight = new FlightFare(null, flightCode, null,null);
        Example<FlightFare> flightFare = Example.of(flight);
        return repository.findOne(flightFare).orElse(new FlightFare());
    }

    private BigDecimal getConversionBigDecimalWithFeignClient(String toCurrency) {

        // Using OpenFeign client to communicate currency-conversion-service
        CurrencyConverterDTO responseEntity = currencyConversionProxy.convertCurrency(baseCurrency,toCurrency);
        log.info("Response Entity : {}",responseEntity);
        return Objects.requireNonNull(responseEntity).getConversionRate();
    }

    @GetMapping("/flight/message")
    public String getMessageFromCurrencyConversion() {

        return currencyConversionProxy.message();
    }

    // Call the currency conversion service here
    // RESTTemplate class
    private BigDecimal getConversionBigDecimal(String toCurrency) {

        String conversionURL = "http://currency-conversion-service/api/v1/from/{from}/to/{to}";
        // set the map object to store the path variables
        Map<String, String> urlPathVariables = new HashMap<>();
        urlPathVariables.put("from", baseCurrency);
        urlPathVariables.put("to", toCurrency);

        // Hostname will be resolved by Eureka server of Constant.CONVERSION_URL by using Eureka Registry
        // We need incoming response data to a type
        ResponseEntity<CurrencyConverterDTO> responseEntity = restTemplate.getForEntity(conversionURL,
                CurrencyConverterDTO.class, urlPathVariables);
        log.info("Response Entity : {}",responseEntity);
        CurrencyConverterDTO converter = responseEntity.getBody();
        return Objects.requireNonNull(converter).getConversionRate();
    }
}
