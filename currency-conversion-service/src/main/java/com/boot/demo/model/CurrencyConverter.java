package com.boot.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.math.BigDecimal;

/**
 * @author JayendraB
 * Created on 02/07/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CurrencyConverter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal conversionRate;
}
