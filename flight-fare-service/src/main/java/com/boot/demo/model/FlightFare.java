package com.boot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * @author JayendraB
 * Created on 08/07/21
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightFare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String flightCode;
    private BigDecimal fare;
    private String currency;
}
