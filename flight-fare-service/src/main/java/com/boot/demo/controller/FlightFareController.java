package com.boot.demo.controller;

import com.boot.demo.model.FlightFare;
import com.boot.demo.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Flight fare controller.
 *
 * @author JayendraB Created on 08/07/21
 */
@RestController
@RequestMapping(value = "/api/v1")
public class FlightFareController {

    @Autowired
    private FlightService flightService;

    @Value("${base.currency}")
    private String baseCurrency;

    /**
     * Gets fare ticket.
     *
     * @param flightCode the flight code
     * @param currency   the currency
     * @return the fare ticket
     */
    @GetMapping("/flight/{flightcode}/fare/{currency}")
    public FlightFare getFareTicket(@PathVariable("flightcode") String flightCode, @PathVariable String currency) {

        return flightService.getSingleFareTicket(baseCurrency, currency, flightCode);
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    @GetMapping("/flight/message")
    public String getMessage() {

        return flightService.getMessageFromCurrencyConversion();
    }
}
