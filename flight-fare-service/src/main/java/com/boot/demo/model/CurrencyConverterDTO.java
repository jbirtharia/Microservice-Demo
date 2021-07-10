package com.boot.demo.model;

import lombok.*;
import java.math.BigDecimal;

/**
 * The type Currency converter dto.
 *
 * @author JayendraB  Created on 08/07/21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyConverterDTO {
    private Long id;
    private String currencyFrom;
    private String currencyTo;
    private BigDecimal conversionRate;
    private String port;
}
