package com.boot.demo.dao;

import com.boot.demo.model.CurrencyConverter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Currency converter repository.
 *
 * @author JayendraB  Created on 02/07/21
 */
public interface CurrencyConverterRepository extends JpaRepository<CurrencyConverter, Long> {

}
